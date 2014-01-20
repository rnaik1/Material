package database;

import java.util.ArrayList;
import java.util.List;

public class Command {

	private String command;
	private List <String> parameters;
	private Command(String command, List<String> parameters) {
		this.command = command;
		this.parameters = parameters;
	}	
	
	public String get_command() {
		return this.command;
	}

	public List<String> get_parameters(){
		return this.parameters;
	}
	
	public static Command form_command(String c1){
		String[] command_elements = c1.split(" ");
		ArrayList<String> parameters = new ArrayList<String>();
		
		if (command_elements.length > 3) {
			System.err.println("Too many parameters for the command.");
			return null;
		}
		
		for (int i = 1; i < command_elements.length; i++)
			parameters.add(command_elements[i]);
		
		if (command_elements[0].equals("SET")){
			if (!isNumber(command_elements[2])){
				System.err.println("Incorrect usage, should be: SET [variable_name->String] [value->Integer]");
				return null;
			}
		}
		
		if (command_elements[0].equals("NUMEQUALTO")) {
			if (!isNumber(command_elements[1])) {
				System.err.println("Incorrect usage, should be: NUMEQUALTO [value->Integer]");
				return null;
			}
		}
			
		return new Command(command_elements[0], parameters);
	}
	
	private static Boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
		}
		catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
