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

	public static void main(String[] args) {
		new BalancedSymbolChecker("ValidClass.java");
	}
	
}