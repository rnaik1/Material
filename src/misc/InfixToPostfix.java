package misc;
// CpSc 102 - Class InfixToPostfix: Sample solution for Assignment 4
//
// Objects of this class convert infix expressions to postfix expressions.
// The possible operators are +, -, *, /, and ^.  The operands are floating-
// point constants.  No parentheses are allowed.
//
// This class was adapted from class PostfixInterpreter in the Standish text.

import java.util.*;

public class InfixToPostfix {

	private static final char ADD = '+', SUBTRACT = '-';
	private static final char MULTIPLY = '*', DIVIDE = '/', MODULO = '%';

	private static Stack<Integer> stack;

	public InfixToPostfix() {
		// TODO Auto-generated constructor stub
		stack = new Stack<Integer>();
	}

	private boolean isOperator(char c) { // Tell whether c is an operator.

		return c == '+'  ||  c == '-'  ||  c == '*'  ||  c == '/'  ||  c == '^'
				|| c=='(' || c==')';
	} 

	private boolean isSpace(char c) {
		return (c == ' ');
	}

	private boolean lowerPrecedence(char op1, char op2) {
		// Tell whether op1 has lower precedence than op2, where op1 is an
		// operator on the left and op2 is an operator on the right.
		// op1 and op2 are assumed to be operator characters (+,-,*,/,^).

		switch (op1) {

		case '+':
		case '-':
			return !(op2=='+' || op2=='-') ;

		case '*':
		case '/':
			return op2=='^' || op2=='(';

		case '^':
			return op2=='(';

		case '(': return true;

		default:  // (shouldn't happen)
			return false;
		}

	} // end lowerPrecedence


	// Method to convert infix to postfix:

	public String convertToPostfix(String infix) {
		// Return a postfix representation of the expression in infix.

		Stack operatorStack = new Stack();  // the stack of operators

		char c;       // the first character of a token

		StringTokenizer parser = new StringTokenizer(infix,"+-*/^() ",true);
		// StringTokenizer for the input string

		StringBuffer postfix = new StringBuffer(infix.length());  // result

		// Process the tokens.
		while (parser.hasMoreTokens()) {     

			String token = parser.nextToken();          // get the next token
			// and let c be
			c = token.charAt(0);         // the first character of this token

			// if token is an operator
			if ( (token.length() == 1) && isOperator(c) ) {

				// (Operator on the stack does not have lower precedence, so
				//  it goes before this one.)
				while (!operatorStack.empty() && !lowerPrecedence(((String)operatorStack.peek()).charAt(0), c))
					postfix.append(" ").append((String)operatorStack.pop());

				// Output the remaining operators in the parenthesized part.
				if (c==')') {
					String operator = (String)operatorStack.pop();
					while (operator.charAt(0)!='(') {
						postfix.append(" ").append(operator);
						operator = (String)operatorStack.pop();  
					}
				}
				else
					operatorStack.push(token);// Push this operator onto the stack.

			}
			else if ( (token.length() == 1) && isSpace(c) ) {    // else if
				// token was a space
				;                                                  // ignore it
			}
			else {  // (it is an operand)
				postfix.append(" ").append(token);  // output the operand
			}//end if

		}// end while for tokens

		// Output the remaining operators on the stack.
		while (!operatorStack.empty())
			postfix.append(" ").append((String)operatorStack.pop());

		// Return the result.

		return postfix.toString();


	}//end convertToPostfix

	
	//-----------------------------------------------------------------
	//  Evaluates the specified postfix expression. If an operand is
	//  encountered, it is pushed onto the stack. If an operator is
	//  encountered, two operands are popped, the operation is
	//  evaluated, and the result is pushed onto the stack.
	//-----------------------------------------------------------------
	public static int evaluate (String expr)
	{
		int op1, op2, result = 0;
		String token;
		StringTokenizer tokenizer = new StringTokenizer (expr);

		while (tokenizer.hasMoreTokens())
		{
			token = tokenizer.nextToken();

			if (isOperator(token))
			{
				op2 = (stack.pop()).intValue();
				op1 = (stack.pop()).intValue();
				result = evalSingleOp (token.charAt(0), op1, op2);
				stack.push (new Integer(result));
			}
			else
				stack.push (new Integer(Integer.parseInt(token)));
		}
		return result;
	}

	//-----------------------------------------------------------------
	//  Determines if the specified token is an operator.
	//-----------------------------------------------------------------
	private static boolean isOperator (String token)
	{
		return ( token.equals("+") || token.equals("-") ||
				token.equals("*") || token.equals("/") || token.equals("%") );
	}

	//-----------------------------------------------------------------
	//  Evaluates a single expression consisting of the specified
	//  operator and operands.
	//-----------------------------------------------------------------
	private static int evalSingleOp (char operation, int op1, int op2)
	{
		int result = 0;

		switch (operation)
		{
		case ADD:
			result = op1 + op2;
			break;
		case SUBTRACT:
			result = op1 - op2;
			break;
		case MULTIPLY:
			result = op1 * op2;
			break;
		case DIVIDE:
			result = op1 / op2;
			break;
		case MODULO:
			result = op1 % op2;
		}

		return result;
	}


	public static void main(String[] args) {  // Test method for the class.

		String[] testString = {"(12+24)*3"};

		InfixToPostfix converter = new InfixToPostfix();

		for (int i=0; i<testString.length; i++) {
			System.out.println("infix: " + testString[i]);
			System.out.println("postfix: " + converter.convertToPostfix(testString[i]));
			System.out.println();
		}

		//PostfixEvaluator pe = new PostfixEvaluator();
		System.out.println(evaluate("1 1 + 6 2 * 7 + *"));
	} // end main


}//end class InfixToPostfix

