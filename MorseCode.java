import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class MorseCode
{
	public static String[] morseAlphabet = new String[37];
	public static String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890 ";
	public static String input = "";
	
	// read in file
	public static void readFile(String fileName) throws FileNotFoundException
	{
		// get the file and put the contents into an array
		File file = new File(fileName);
		Scanner scan = new Scanner(file);
	
		for(int i=0; i<morseAlphabet.length; i++) {
			if(scan.hasNextLine()) {
				morseAlphabet[i] = scan.nextLine();
			}
		}
		
		morseAlphabet[36] = " / ";   // word separator
	}
	
	// get some input from user
	public static void getInput()
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Type some text (a-z, 0-9 and spaces): "); // user must use a-z, 0-9, and space only
		input = scan.nextLine().toLowerCase();
		
	}
	
	// translate the text into Morse code (without encrytion)
	public static String convert()
	{
		String output = "";
		for(int i = 0; i < input.length(); i++) {
			// get the typed character and find its index in the alphabet
			// the index tells will be matched with the same Morse code index
			char charAtIndex = input.charAt(i);
			int indexInAlphabet = alphabet.indexOf(charAtIndex);
			output += morseAlphabet[indexInAlphabet] + " ";
		}
		return output;
	}

	// translate the text into Morse code (with encrytion)
	public static String convertEncrypt()
	{
		String output = "";
		for(int i=0; i<input.length(); i++) {
			// shift using Substitution
			Random rand = new Random();
			int shift = rand.nextInt(36);

			// get the typed character and find its index in the alphabet
			// the index tells will be matched with the same Morse code index
			char charAtIndex = input.charAt(i);
			int indexInAlphabet = alphabet.indexOf(charAtIndex);
			int shifted = indexInAlphabet + shift;

			// if the shift goes outside the alphabet range, bring it to the start
			if (shifted > 36) {
				shifted = shifted - 36;
			}
			output += morseAlphabet[shifted] + " ";
		}
		return output;
	}
}
