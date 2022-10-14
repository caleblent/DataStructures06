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
	
	public static void createStackFromSpecialChars(String str) {
		LinkedListStack<Character> stack = new LinkedListStack<Character>();
		
		int length = str.length();
		for (int i = 0; i < length; i++) {
			stack.push(str.charAt(i));
		}
		System.out.println(stack.toString());
	}

	public static void main(String[] args) {
//		System.out.println("Entering...");
//		BalancedSymbolChecker BSC = new BalancedSymbolChecker("ValidClass.java");
//		BalancedSymbolChecker BSC = new BalancedSymbolChecker("BadNesting.java");
		BalancedSymbolChecker BSC = new BalancedSymbolChecker("SmallFile.txt");
		
		System.out.println("=============================");
		System.out.println("Printing whole file...");
		System.out.println("=============================");
		System.out.println(BSC.text);
		System.out.println("=============================");
		System.out.println("Printing parentheses, brackets, and braces...");
		System.out.println("=============================");
		System.out.println(findCharsOfInterest(BSC.text));
		System.out.println("=============================");
		System.out.println("Creating a stack object...");
		System.out.println("=============================");
		createStackFromSpecialChars(findCharsOfInterest(BSC.text));
		
	}
	
}