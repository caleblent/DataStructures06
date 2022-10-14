package balancedSymbols;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Starting point for Project 6
 * 
 * 
 * @author Nathan Gossett
 * @version Spring 2021
 *
 */
public class BalancedSymbolChecker {
	
	/** String of text containing the code we're parsing */
	private String text;
	
	public BalancedSymbolChecker(String filename){
		
		text = "";
		try{
			BufferedReader bir = new BufferedReader(new FileReader(filename));
			while(bir.ready()) {
				text += bir.readLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + filename);
		}catch (IOException e) {
			System.err.println("Error reading from file: " + e.getMessage());
		}
	}
	
	/**
	 * Goes through this BalancedSymbolChecker object's text attribute character by character,
	 * adding opening and closing characters (parentheses, brackets, braces) to a stack object.
	 * Handles the logic to ensure the nesting is done properly.
	 * 
	 * Returns FALSE on first violation of nesting rules, and only returns TRUE if it manages
	 * to pass all of the checks and make it to the end of the method.
	 * @param String
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	public boolean verifyIfProperlyNested() throws IllegalArgumentException {
		if (text.length() == 0 || text == null)
			throw new IllegalArgumentException("String provided cannot be null or have a length of 0");
		
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		for (int i = 0; i < text.length(); i++) {
			char curr = text.charAt(i);
			
			// if curr is a slash, that might indicate the beginning of a code comment,
			// in which case we have to ignore that section
			if (curr == '/') {
				// 1. single line comment
				if (text.charAt(i+1) == '/') {
					// cycle through the file until a newline character (\n) is reached
					i++;
					while((text.charAt(i) != '\n') && i < text.length()) {
						i++;
					}
				}
				// 2. code comment block
				else if (text.charAt(i+1) == '*') {
					i++;
					while(!(text.charAt(i) == '*' && text.charAt(i+1) == '/') && i < text.length()) {
						i++;
					}
				}
			}
			
			// we also have to control for the case where we have single quotation marks
			// around the opening/closing characters
			else if (curr == '\'') {
				if (text.charAt(i+1) == '(' || text.charAt(i+1) == '[' || text.charAt(i+1) == '{' ||
					text.charAt(i+1) == ')' || text.charAt(i+1) == ']' || text.charAt(i+1) == '}') {
					if (text.charAt(i+2) == '\'') {
						i+=2;
					}
				}
			}
			
			// we need to handle string literals, as they could contain parentheses, brackets, and braces
			else if (curr == '"') {
				// cycle through the file until another closing string is found
				// TODO: maybe the double quotation marks could be added to the stack?
				i++;
				while((text.charAt(i) != '"') && i < text.length()) {
					i++;
				}
			}
			
			// if curr is an opening char, add to the stack
			else if (curr == '(' || curr == '[' || curr == '{') {
				stack.push(curr);
			} 
			// else if curr is a closing char, then we need to see if it closes the previous opening char
			// if it DOES NOT, then the string is NOT nested properly and we can return false
			// if it DOES, then we can pop the opening brace off the stack and continue
			else if (curr == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				if (stack.top() == '(') {
					stack.pop();
				} else {
					return false;
				}
			} else if (curr == ']') {
				if (stack.isEmpty()) {
					return false;
				}
				if (stack.top() == '[') {
					stack.pop();
				} else {
					return false;
				}
			} else if (curr == '}') {
				if (stack.isEmpty()) {
					return false;
				}
				if (stack.top() == '{') {
					stack.pop();
				} else {
					return false;
				}
			}
		}
		
		// now we must check to ensure there are no leftover opening chars that
		// still need to be closed
		if (!stack.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public static String findCharsOfInterest(String text) {
		String ret = "";
		int length = text.length();
		for (int i = 0; i < length; i++) {
			if (text.charAt(i) == '(' || text.charAt(i) == ')' ||
				text.charAt(i) == '[' || text.charAt(i) == ']' ||
				text.charAt(i) == '{' || text.charAt(i) == '}') {
				ret += text.charAt(i);
			}
		}
		return ret;
	}
	
	public static LinkedListStack<Character> createStackFromSpecialChars(String str) {
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		int length = str.length();
		for (int i = 0; i < length; i++) {
			stack.push(str.charAt(i));
		}
		
		return stack;
	}
	
	
	/**
	 * This method goes through the String passed to it character by character, outputting
	 * each character to the console and handling (, ), [, ], {, } character with a stack.
	 * Outputs an ERROR message if there is an invalid nesting within the file.
	 * @param String
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static void printEachCharUntilError(String str) throws IllegalArgumentException {
		if (str.length() == 0 || str == null)
			throw new IllegalArgumentException("String provided cannot be null or have a length of 0");
		
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		for (int i = 0; i < str.length(); i++) {
			char curr = str.charAt(i);
			
			// if curr is a slash, that might indicate the beginning of a code comment,
			// in which case we have to ignore that section
			if (curr == '/') {
				// 1. single line comment
				if (str.charAt(i+1) == '/') {
					// cycle through the file until a newline character (\n) is reached
					i++;
					while((str.charAt(i) != '\n') && i < str.length()) {
						i++;
					}
				}
				// 2. code comment block
				else if (str.charAt(i+1) == '*') {
					i++;
					while(!(str.charAt(i) == '*' && str.charAt(i+1) == '/') && i < str.length()) {
						i++;
					}
				}
			}
			
			// we also have to control for the case where we have single quotation marks
			// around the opening/closing characters
			else if (curr == '\'') {
				if (str.charAt(i+1) == '(' || str.charAt(i+1) == '[' || str.charAt(i+1) == '{' ||
					str.charAt(i+1) == ')' || str.charAt(i+1) == ']' || str.charAt(i+1) == '}') {
					if (str.charAt(i+2) == '\'') {
						// we still want to add these values to the return string, but
						// we DO NOT want to add the open/close chars to the stack
						System.out.print(curr+ str.charAt(i+1) + str.charAt(i+2));
						i+=2;
					}
				}
			}
			
			// we need to handle string literals, as they could contain parentheses, brackets, and braces
			else if (curr == '"') {
				// cycle through the file until another closing string is found
				// TODO: maybe the double quotation marks could be added to the stack?
				i++;
				while((str.charAt(i) != '"') && i < str.length()) {
					i++;
				}
			}
			
			// if curr is an opening char, add to the stack
			else if (curr == '(' || curr == '[' || curr == '{') {
				stack.push(curr);
				System.out.print(curr);
			} 
			// else if curr is a closing char, then we need to see if it closes the previous opening char
			// if it DOES NOT, then the string is NOT nested properly and we can return false
			// if it DOES, then we can pop the opening brace off the stack and continue
			else if (curr == ')') {
				if (stack.isEmpty()) {
					System.out.println("\nERROR: empty stack when trying to add '" + curr + "'");
					return;
				}
				if (stack.top() == '(') {
					stack.pop();
					System.out.print(curr);
				} else {
					//ERROR: cannot use } to close (
					System.out.print("\n\nERROR: cannot use '" + curr + "' to close '" + stack.top() + "'");
					return;
				}
			} else if (curr == ']') {
				if (stack.isEmpty()) {
					System.out.println("\nERROR: empty stack when trying to add '" + curr + "'");
					return;
				}
				if (stack.top() == '[') {
					stack.pop();
					System.out.print(curr);
				} else {
					System.out.print("\n\nERROR: cannot use '" + curr + "' to close '" + stack.top() + "'");
					return;
				}
			} else if (curr == '}') {
				if (stack.isEmpty()) {
					System.out.println("\nERROR: empty stack when trying to add '" + curr + "'");
					return;
				}
				if (stack.top() == '{') {
					stack.pop();
					System.out.print(curr);
				} else {
					System.out.print("\n\nERROR: cannot use '" + curr + "' to close '" + stack.top() + "'");
					return;
				}
			} 
			// if none of the above scenarios have occurred, then it is a normal character
			// and can be printed without any further debilitation
			else { 
				System.out.print(curr); 
			}
		}
		System.out.print("\n!!! Balanced !!!\n");
	}
	
	/**
	 * Identical to the logic of the previous method, except that in this one, rather than
	 * printing each character out to the console, it stores the result in a String, which
	 * is then returned at the end.
	 * 
	 * This method now, in addition to up above, removes code comments
	 * @param String
	 * @return String
	 * @throws IllegalArgumentException
	 */
	public String removeComments() throws IllegalArgumentException {
		if (text.length() == 0 || text == null)
			throw new IllegalArgumentException("String provided cannot be null or have a length of 0");
		
		String ret = "";
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		for (int i = 0; i < text.length(); i++) {
			char curr = text.charAt(i);
			
			// if curr is a slash, that might indicate the beginning of a code comment,
			// in which case we have to ignore that section
			if (curr == '/') {
				// 1. single line comment
				if (text.charAt(i+1) == '/') {
					// cycle through the file until a newline character (\n) is reached
					i++;
					while((text.charAt(i) != '\n') && i < text.length()) {
						i++;
					}
				}
				// 2. code comment block
				else if (text.charAt(i+1) == '*') {
					i++;
					while(!(text.charAt(i) == '*' && text.charAt(i+1) == '/') && i < text.length()) {
						i++;
					}
				}
			}
			
			// we also have to control for the case where we have single quotation marks
			// around the opening/closing characters
			else if (curr == '\'') {
				if (text.charAt(i+1) == '(' || text.charAt(i+1) == '[' || text.charAt(i+1) == '{' ||
					text.charAt(i+1) == ')' || text.charAt(i+1) == ']' || text.charAt(i+1) == '}') {
					if (text.charAt(i+2) == '\'') {
						// we still want to add these values to the return string, but
						// we DO NOT want to add the open/close chars to the stack
						ret += curr;
						ret += text.charAt(i+1);
						ret += text.charAt(i+2);
						i+=2;
					}
				}
			}
			
			// we need to handle string literals, as they could contain parentheses, brackets, and braces
			else if (curr == '"') {
				// cycle through the file until another closing string is found
				// TODO: maybe the double quotation marks could be added to the stack?
				i++;
				while((text.charAt(i) != '"') && i < text.length()) {
					i++;
				}
			}
			
			// if curr is an opening char, add to the stack
			else if (curr == '(' || curr == '[' || curr == '{') {
				stack.push(curr);
				ret += curr;
			} 
			// else if curr is a closing char, then we need to see if it closes the previous opening char
			// if it DOES NOT, then the string is NOT nested properly and we can return false
			// if it DOES, then we can pop the opening brace off the stack and continue
			else if (curr == ')') {
				if (stack.isEmpty()) {
					return ret += "\nERROR: empty stack when trying to add '" + curr + "'";
				}
				if (stack.top() == '(') {
					stack.pop();
					ret += curr;
				} else {
					return ret += "\n\nERROR: cannot use '" + curr + "' to close '" + stack.top() + "'";
				}
			} else if (curr == ']') {
				if (stack.isEmpty()) {
					return ret += "\nERROR: empty stack when trying to add '" + curr + "'";
				}
				if (stack.top() == '[') {
					stack.pop();
					ret += curr;
				} else {
					return ret += "\n\nERROR: cannot use '" + curr + "' to close '" + stack.top() + "'";
				}
			} else if (curr == '}') {
				if (stack.isEmpty()) {
					return ret += "\nERROR: empty stack when trying to add '" + curr + "'";
				}
				if (stack.top() == '{') {
					stack.pop();
					ret += curr;
				} else {
					return ret += "\n\nERROR: cannot use '" + curr + "' to close '" + stack.top() + "'";
				}
			} 
			// if none of the above scenarios have occurred, then it is a normal character
			// and can be printed without any further debilitation
			else { 
				ret += curr; 
			}
		}
		return ret += "\n!!! Balanced !!!\n";
	}
	
	/**
	 * Identical to the logic of the previous method, except that in this one, rather than
	 * printing each character out to the console, it stores the result in a String, which
	 * is then returned at the end.
	 * 
	 * This method now, in addition to up above, KEEPS code comments
	 * @param String
	 * @return String
	 * @throws IllegalArgumentException
	 */
	public String keepComments() throws IllegalArgumentException {
		if (text.length() == 0 || text == null)
			throw new IllegalArgumentException("String provided cannot be null or have a length of 0");
		
		String ret = "";
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		for (int i = 0; i < text.length(); i++) {
			char curr = text.charAt(i);
			
			// if curr is a slash, that might indicate the beginning of a code comment,
			// in which case we have to ignore that section
//			if (curr == '/') {
//				// 1. single line comment
//				if (text.charAt(i+1) == '/') {
//					// cycle through the file until a newline character (\n) is reached
//					i++;
//					while((text.charAt(i) != '\n') && i < text.length()) {
//						i++;
//					}
//				}
//				// 2. code comment block
//				else if (text.charAt(i+1) == '*') {
//					i++;
//					while(!(text.charAt(i) == '*' && text.charAt(i+1) == '/') && i < text.length()) {
//						i++;
//					}
//				}
//			}
			
			// we also have to control for the case where we have single quotation marks
			// around the opening/closing characters
			if (curr == '\'') {
				if (text.charAt(i+1) == '(' || text.charAt(i+1) == '[' || text.charAt(i+1) == '{' ||
					text.charAt(i+1) == ')' || text.charAt(i+1) == ']' || text.charAt(i+1) == '}') {
					if (text.charAt(i+2) == '\'') {
						// we still want to add these values to the return string, but
						// we DO NOT want to add the open/close chars to the stack
						ret += curr;
						ret += text.charAt(i+1);
						ret += text.charAt(i+2);
						i+=2;
					}
				}
			}
			
			// we need to handle string literals, as they could contain parentheses, brackets, and braces
			else if (curr == '"') {
				// cycle through the file until another closing string is found
				// TODO: maybe the double quotation marks could be added to the stack?
				i++;
				while((text.charAt(i) != '"') && i < text.length()) {
					i++;
				}
			}
			
			// if curr is an opening char, add to the stack
			else if (curr == '(' || curr == '[' || curr == '{') {
				stack.push(curr);
				ret += curr;
			} 
			// else if curr is a closing char, then we need to see if it closes the previous opening char
			// if it DOES NOT, then the string is NOT nested properly and we can return false
			// if it DOES, then we can pop the opening brace off the stack and continue
			else if (curr == ')') {
				if (stack.isEmpty()) {
					return ret += "\nERROR: empty stack when trying to add '" + curr + "'";
				}
				if (stack.top() == '(') {
					stack.pop();
					ret += curr;
				} else {
					return ret += "\n\nERROR: cannot use '" + curr + "' to close '" + stack.top() + "'";
				}
			} else if (curr == ']') {
				if (stack.isEmpty()) {
					return ret += "\nERROR: empty stack when trying to add '" + curr + "'";
				}
				if (stack.top() == '[') {
					stack.pop();
					ret += curr;
				} else {
					return ret += "\n\nERROR: cannot use '" + curr + "' to close '" + stack.top() + "'";
				}
			} else if (curr == '}') {
				if (stack.isEmpty()) {
					return ret += "\nERROR: empty stack when trying to add '" + curr + "'";
				}
				if (stack.top() == '{') {
					stack.pop();
					ret += curr;
				} else {
					return ret += "\n\nERROR: cannot use '" + curr + "' to close '" + stack.top() + "'";
				}
			} 
			// if none of the above scenarios have occurred, then it is a normal character
			// and can be printed without any further debilitation
			else { 
				ret += curr; 
			}
		}
		return ret += "\n!!! Balanced !!!\n";
	}

	public static void main(String[] args) {
//		System.out.println("Entering...");
//		BalancedSymbolChecker BSC = new BalancedSymbolChecker("src/balancedSymbols/BalancedSymbolChecker.java");
		BalancedSymbolChecker BSC = new BalancedSymbolChecker("ValidClass.java");
		System.out.println("==========================================================");
		System.out.println("Doing stuff below...");
		System.out.println("==========================================================");
		System.out.println(BSC.removeComments());
		System.out.println("==========================================================");
		System.out.println(BSC.keepComments());
		System.out.println("==========================================================");
		printEachCharUntilError(BSC.text);
		System.out.println("==========================================================");
		
	}
	
}