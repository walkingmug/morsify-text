package src.main;
import java.util.Random;


public class MorseCode
{
	// public static String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890 ";
	
	// convert text into Morse code (without encrytion)
	public static String convert(String inputText)
	{
		String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890 ";
		String output = "";
		for(int i = 0; i < inputText.length(); i++) {
			// get the typed character and find its index in the alphabet
			// the index tells will be matched with the same Morse code index
			char charAtIndex = inputText.charAt(i);
			int indexInAlphabet = alphabet.indexOf(charAtIndex);
			output += Input.morseAlphabet[indexInAlphabet] + " ";
		}
		return output;
	}

	// convert text into Morse code (with encrytion)
	public static String convertEncrypt(String inputText)
	{
		String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890 ";
		String output = "";
		for(int i=0; i<inputText.length(); i++) {
			// shift using Substitution encryption
			Random rand = new Random();
			int shift = rand.nextInt(36);

			// get the typed character and find its index in the alphabet
			// the index tells will be matched with the same Morse code index
			char charAtIndex = inputText.charAt(i);
			int indexInAlphabet = alphabet.indexOf(charAtIndex);
			int shifted = indexInAlphabet + shift;

			// if the shift goes outside the alphabet range, bring it to the start
			if (shifted > 36) {
				shifted = shifted - 36;
			}
			output += Input.morseAlphabet[shifted] + " ";
		}
		return output;
	}
}
