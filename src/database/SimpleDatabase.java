package database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SimpleDatabase {

	static class BuildCommand {

		private String command;
		private List <String> parameters;

		private BuildCommand(String command, List<String> parameters) {
			this.command = command;
			this.parameters = parameters;
		}

		public String getCommand() {
			return command;
		}

		public List<String> getParameters() {
			return parameters;
		}
		
		static boolean checkForNumber(String par) {
			try {
				Integer.parseInt(par);
			}
			catch (NumberFormatException e) {
				return false;
			}
			return true;
		}

		public static BuildCommand createCommand(String statement){
			String[] individualElements = statement.split(" ");
			if (individualElements.length > 3) {
				System.err.println("Maximum length for any command exceeded. Please check the validity of the command entered.");
				return null;
			}
			ArrayList<String> params = new ArrayList<String>();
			for (int i = 1; i < individualElements.length; i++)
				params.add(individualElements[i]);
			
			if (individualElements[0].equals("SET")){
				if (!checkForNumber(individualElements[2])){
					System.err.println("Please Enter a numbers as a value in the SET command");
					return null;
				}
			}
			if (individualElements[0].equals("NUMEQUALTO")) {
				if (!checkForNumber(individualElements[1])) {
					System.err.println("Please enter a number as parameter to the NUMEQUALTO command");
					return null;
				}
			}
			return new BuildCommand(individualElements[0], params);
		}
	}

	public HashMap<String,String> database;

	public HashMap<Integer, Integer> count_database;

	public boolean begin;
	public boolean rollback;

	public List<String> current_block_reverse_commands;
	public Stack<List<String>> trans_stack;

	public SimpleDatabase() {
		begin = false;
		rollback = true;
		
		database = new HashMap<String,String>();
		count_database = new HashMap<Integer, Integer>();
		
		current_block_reverse_commands = new ArrayList<String>();
		
		trans_stack = new Stack<List<String>>();
	}

	public String execute_command(String user_command) {
		
		String result = "";
		BuildCommand command = BuildCommand.createCommand(user_command);
		
		if(command != null){

			String comm = command.getCommand();

			if (comm.equalsIgnoreCase("get")) {
				result = (command_get(command.getParameters().get(0)) + "\n"); 
			}
			else if (comm.equalsIgnoreCase("set")){
				command_set(command.getParameters().get(0),command.getParameters().get(1));
			}
			else if (comm.equalsIgnoreCase("unset")){
				unsetCommand(command.getParameters().get(0));
			}
			else if (comm.equalsIgnoreCase("numequalto")){
				result = (numequaltoCommand(command.getParameters().get(0)) + "\n");
			}
			else if (comm.equalsIgnoreCase("end")){
				end();
			}
			else if (comm.equalsIgnoreCase("begin")){
				begin();
			}
			else if (comm.equalsIgnoreCase("rollback")){
				result = rollback();
			}
			else if (comm.equalsIgnoreCase("commit")){
				commit();
			}
			else {
				System.out.println("Invalid Command!");
			}
		}
		return result;	
	}

	String command_get(String variable_name) {
		if (database.get(variable_name) != null)
			return database.get(variable_name);
		return "NULL";
	}

	void command_set(String variable, String value) {
		String currentValue = database.get(variable);
		String backtrackCommand = "";
		Integer count = 0;

		// variable present in database.
		if (currentValue == null) {
			database.put(variable, value);	
			backtrackCommand = "UNSET " + variable;
		}
		else {
			count = count_database.get(Integer.parseInt(currentValue));
			count_database.put(Integer.parseInt(currentValue), count - 1);			
			database.put(variable, value);	
			backtrackCommand = "SET " + variable + " " + currentValue;
		}
		
		// add backtrack command to the current block of transactions. 
		// Incase of rollback these statements will be executed in the reverse order
		if (begin && rollback)
			current_block_reverse_commands.add(backtrackCommand);

		// count is updated in the count_database for the use of noequalto method.
		count = count_database.get(Integer.parseInt(value));
		if (count != null) {
			count_database.put(Integer.parseInt(value), ++count);			
		}
		else {
			count_database.put(Integer.parseInt(value), 1);
		}
	}

	void unsetCommand(String var){
		String value = database.get(var);
		Integer count = null;
		String backtrackCommand = "";

		if (value != null ) {
			database.remove(var);
			count = count_database.get(Integer.parseInt(value));
			count_database.put(Integer.parseInt(value), --count);

			if (begin && rollback) {
				backtrackCommand = "SET " + var + " " + value;
				current_block_reverse_commands.add(backtrackCommand);
			}
		}
		else 
			System.err.println(var + " doesn't exist in database.");

	}

	int numequaltoCommand(String value) {
		if (count_database.get(Integer.parseInt(value)) != null)
			return count_database.get(Integer.parseInt(value));
		return 0;
	}

	void begin(){
		if (!begin)
			begin = true;
		else {
			if (!current_block_reverse_commands.isEmpty()) {
				trans_stack.push(current_block_reverse_commands);
				current_block_reverse_commands = new ArrayList<String>();
			}
		}
	}
	
	String end(){
		for (int i = 0; i <= trans_stack.size();i++)
			commit();
		return null;
	}
	
	void commit(){
		begin = false;
		current_block_reverse_commands = new ArrayList<String>();
		trans_stack.removeAllElements();
	}
	
	String rollback(){
		if (begin){
			Collections.reverse(current_block_reverse_commands);
			rollback = false;
			for (String c1: current_block_reverse_commands)
				execute_command(c1);

			rollback = true;
			
			try {
				current_block_reverse_commands = trans_stack.pop();
			}
			catch (EmptyStackException e) {
				begin = false;
			}
		}
		else
			return ("NO TRANSACTION\n");
		return "";
	}

	public static void main(String[] args) {
		
		String userInput = "";
		Scanner in = new Scanner(System.in);
		
		System.out.println("Revanth's Simple Database");
		System.out.println("Following are the commands that are allowed on the database");
		System.out.println("SET [name] [value]: Sets a variable [name] to the value [value].");
		System.out.println("GET [name]: Prints out the value stored under the variable [name].");
		System.out.println("UNSET [name]: Unsets the variable [name]");
		System.out.println("NUMEQUALTO [value]: Returns the number of variables equal to [value].");
		System.out.println("END: Exits the program");
		System.out.println("\nPlease enter your commands below:");
		
		ArrayList<String> inputQueries = new ArrayList<String>();
		SimpleDatabase db = new SimpleDatabase();
		
		while (!userInput.equalsIgnoreCase("END")) {
			userInput = in.nextLine();
			inputQueries.add(userInput);
		}

		System.out.println("\nCommand Results:");
		for (String c1 : inputQueries){
			System.out.print(db.execute_command(c1));
			// Un comment the below lines to see step by step values of the database and the intermediate values after each command.
			/*System.out.println("Command: \t" + c1);
			System.out.println("Database: \t" + db.database);
			System.out.println("Count Database: \t" + db.count_database);
			System.out.println("Block of Transactions: \t" + db.current_block_reverse_commands);
			System.out.println("Stack: \t" + db.trans_stack);
			System.out.println("\n");*/
		}
	}
}
