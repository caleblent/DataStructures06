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
//		System.out.println(stack.toString());
		return stack;
	}
	
	/**
	 * Beginning with the logic of the previous method createStackFromSpecialChars(),
	 * this one seeks to do similarly but with logic checks to ensure nesting is proper
	 * @param String
	 * @return boolean
	 * @throws IllegalArgumentException
	 */
	public static boolean verifyIfProperlyNested(String str) throws IllegalArgumentException {
		if (str.length() == 0 || str == null)
			throw new IllegalArgumentException("String provided cannot be null or have a length of 0");
		
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		for (int i = 0; i < str.length(); i++) {
			char curr = str.charAt(i);
			
			// if curr is an opening char, add to the stack
			if (curr == '(' || curr == '[' || curr == '{') {
				stack.push(curr);
				System.out.println(stack.toStringReverse());
			} 
			// else if curr is a closing char, then we need to see if it closes the previous opening char
			// if it DOES NOT, then the string is NOT nested properly and we can return false
			// if it DOES, then we can pop the opening brace off the stack and continue
			else if (curr == ')') {
				if (stack.top() == '(') {
					stack.pop();
					System.out.println(stack.toStringReverse());
				} else {
					System.out.println("Top: " + stack.top());
					System.out.println("Attempted to add: " + curr);
					return false;
				}
			} else if (curr == ']') {
				if (stack.top() == '[') {
					stack.pop();
					System.out.println(stack.toStringReverse());
				} else {
					System.out.println("Top: " + stack.top());
					System.out.println("Attempted to add: " + curr);
					return false;
				}
			} else if (curr == '}') {
				if (stack.top() == '{') {
					stack.pop();
					System.out.println(stack.toStringReverse());
				} else {
					System.out.println("Top: " + stack.top());
					System.out.println("Attempted to add: " + curr);
					return false;
				}
			} // else { do nothing }
			
		}
		return true;
	}
	
	/**
	 * Beginning with the logic of the previous method verifyIfProperlyNested(),
	 * this one seeks to do similarly but by outputting each character individually until
	 * an incorrect nesting is spotted
	 * @param str
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static void printEachCharUntilError(String str) throws IllegalArgumentException {
		if (str.length() == 0 || str == null)
			throw new IllegalArgumentException("String provided cannot be null or have a length of 0");
		
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		for (int i = 0; i < str.length(); i++) {
			char curr = str.charAt(i);
			
			// if curr is an opening char, add to the stack
			if (curr == '(' || curr == '[' || curr == '{') {
				stack.push(curr);
				System.out.print(curr);
			} 
			// else if curr is a closing char, then we need to see if it closes the previous opening char
			// if it DOES NOT, then the string is NOT nested properly and we can return false
			// if it DOES, then we can pop the opening brace off the stack and continue
			else if (curr == ')') {
				if (stack.top() == '(') {
					stack.pop();
					System.out.print(curr);
				} else {
					System.out.print("\n\nERROR encountered with: '" + curr + "'");
					return;
				}
			} else if (curr == ']') {
				if (stack.top() == '[') {
					stack.pop();
					System.out.print(curr);
				} else {
					System.out.print("\n\nERROR encountered with: '" + curr + "'");
					return;
				}
			} else if (curr == '}') {
				if (stack.top() == '{') {
					stack.pop();
					System.out.print(curr);
				} else {
					System.out.print("\n\nERROR encountered with: '" + curr + "'");
					return;
				}
			} 
			// if none of the above scenarios have occurred, then it is a normal character
			// and can be printed without any further debilitation
			else { 
				System.out.print(curr); 
			}
		}
	}
	
	/**
	 * Identical to the logic of the previous method, except that in this one, rather than
	 * printing each character out to the console, it stores the result in a String, which
	 * is then returned at the end.
	 * @param String
	 * @return String
	 * @throws IllegalArgumentException
	 */
	public static String returnEachCharUntilError(String str) throws IllegalArgumentException {
		if (str.length() == 0 || str == null)
			throw new IllegalArgumentException("String provided cannot be null or have a length of 0");
		
		String ret = "";
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		for (int i = 0; i < str.length(); i++) {
			char curr = str.charAt(i);
			
			// if curr is an opening char, add to the stack
			if (curr == '(' || curr == '[' || curr == '{') {
				stack.push(curr);
				ret += curr;
			} 
			// else if curr is a closing char, then we need to see if it closes the previous opening char
			// if it DOES NOT, then the string is NOT nested properly and we can return false
			// if it DOES, then we can pop the opening brace off the stack and continue
			else if (curr == ')') {
				if (stack.top() == '(') {
					stack.pop();
					ret += curr;
				} else {
					ret += "\n\nERROR encountered with: '" + curr + "'";
					return ret;
				}
			} else if (curr == ']') {
				if (stack.top() == '[') {
					stack.pop();
					ret += curr;
				} else {
					ret += "\n\nERROR encountered with: '" + curr + "'";
					return ret;
				}
			} else if (curr == '}') {
				if (stack.top() == '{') {
					stack.pop();
					ret += curr;
				} else {
					ret += "\n\nERROR encountered with: '" + curr + "'";
					return ret;
				}
			} 
			// if none of the above scenarios have occurred, then it is a normal character
			// and can be printed without any further debilitation
			else { 
				ret += curr; 
			}
		}
		return ret;
	}

	public static void main(String[] args) {
//		System.out.println("Entering...");
		BalancedSymbolChecker BSC = new BalancedSymbolChecker("ValidClass.java");
//		BalancedSymbolChecker BSC = new BalancedSymbolChecker("BadNesting.java");
//		BalancedSymbolChecker BSC = new BalancedSymbolChecker("SmallFile.txt");
		
//		System.out.println("\n==========================================================");
//		System.out.println("Printing whole file...");
//		System.out.println("==========================================================\n");
//		System.out.println(BSC.text);
//		System.out.println("\n==========================================================");
//		System.out.println("Printing parentheses, brackets, and braces...");
//		System.out.println("==========================================================\n");
//		System.out.println(findCharsOfInterest(BSC.text));
		System.out.println("==========================================================");
//		System.out.println("Creating a stack object...");
		System.out.println("Printing the file until an error occurs...");
		System.out.println("==========================================================");
//		System.out.println(createStackFromSpecialChars(findCharsOfInterest(BSC.text)).toStringReverse());
//		System.out.println("Nested properly? " + verifyIfProperlyNested(BSC.text));
		printEachCharUntilError(BSC.text);
		System.out.println("\n==========================================================");
		System.out.println(returnEachCharUntilError(BSC.text));
		System.out.println("==========================================================");
//		System.out.println("END");
//		System.out.println("==========================================================\n");
		
	}
	
}