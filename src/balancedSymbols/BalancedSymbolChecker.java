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
	 * @param str
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean verifyIfProperlyNested(String str) throws IllegalArgumentException {
		if (str.length() == 0 || str == null)
			throw new IllegalArgumentException("String provided cannot be null or have a length of 0");
		
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
//		stack.push(str.charAt(0));
//		System.out.println("Pushing : " + str.charAt(0));
		
		for (int i = 0; i < str.length(); i++) {
//			char prev = str.charAt(i-1);
			char curr = str.charAt(i);
			
			// if curr is an opening char, add to the stack
			if (curr == '(' || curr == '[' || curr == '{') {
				stack.push(curr);
				System.out.println(stack.toStringReverse());
//				System.out.println("Pushing : " + curr);
			} 
			// else if curr is a closing char, then we need to see if it closes the previous opening char
			// if it DOES NOT, then the string is NOT nested properly and we can return false
			// if it DOES, then we can pop the opening brace off the stack and continue
			else if (curr == ')') {
				if (stack.top() == '(') {
					stack.pop();
					System.out.println(stack.toStringReverse());
//					System.out.println("Popping : " + stack.pop()); // pops it off the stack, and also displays it
				} else {
					return false;
				}
			} else if (curr == ']') {
				if (stack.top() == '[') {
					stack.pop();
					System.out.println(stack.toStringReverse());
//					System.out.println("Popping : " + stack.pop()); // pops it off the stack, and also displays it
				} else {
					return false;
				}
			} else if (curr == '}') {
				if (stack.top() == '{') {
					stack.pop();
					System.out.println(stack.toStringReverse());
//					System.out.println("Popping : " + stack.pop()); // pops it off the stack, and also displays it
				} else {
					return false;
				}
			} // else { do nothing }
			
		}
		return true;
	}

	public static void main(String[] args) {
//		System.out.println("Entering...");
//		BalancedSymbolChecker BSC = new BalancedSymbolChecker("ValidClass.java");
//		BalancedSymbolChecker BSC = new BalancedSymbolChecker("BadNesting.java");
		BalancedSymbolChecker BSC = new BalancedSymbolChecker("SmallFile.txt");
		
//		System.out.println("\n==========================================================");
//		System.out.println("Printing whole file...");
//		System.out.println("==========================================================\n");
//		System.out.println(BSC.text);
		System.out.println("\n==========================================================");
		System.out.println("Printing parentheses, brackets, and braces...");
		System.out.println("==========================================================\n");
		System.out.println(findCharsOfInterest(BSC.text));
		System.out.println("\n==========================================================");
		System.out.println("Creating a stack object...");
		System.out.println("==========================================================\n");
		System.out.println(createStackFromSpecialChars(findCharsOfInterest(BSC.text)).toStringReverse());
		System.out.println("Nested properly? " + verifyIfProperlyNested(findCharsOfInterest(BSC.text)));
		System.out.println("\n==========================================================");
		System.out.println("END");
		System.out.println("==========================================================\n");
		
	}
	
}