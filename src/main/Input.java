package src.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class Input {
    Random rand = new Random();

    public static String[] morseAlphabet = new String[37];
	public static int inputInstrument = 0;
	public static int encrypt = 0;


	// read file containing the morse code
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
        scan.close();
	}
	
	// ask for text to be converted to Morse code
	public static String getInputText()
	{
		Scanner scan = new Scanner(System.in);
		// ask for text to be converted
		System.out.println("Type some text (a-z, 0-9 and spaces): ");
		return scan.nextLine().toLowerCase();
	}

    // ask for instrument type
	public static int getInputInstrumentType()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Insert instrument number (1-128, for help see " 
		+ "wikipedia.org/wiki/General_MIDI). Type 0 for random instruments " 
		+ "(this disables the use of cryptography): ");
		inputInstrument = scan.nextInt();   // convert inputText to int
		return inputInstrument;
	}

    // ask if user wants to use encryption
	public static int getInputEncryption()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enable Encryption? (0-No, 1-Yes): ");
		encrypt = scan.nextInt();
        scan.close();
		return encrypt;
	}
}
