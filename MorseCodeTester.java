import javax.sound.midi.*;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class MorseCodeTester 
{
	// creates a message for pressing a note
	protected static MidiMessage createMIDINoteOnMessage(
		int channel, int note, int velocity)
	{
		return createMIDINoteMessage(
			ShortMessage.NOTE_ON,channel,note,velocity);
	}
	// creates a message for releasing a note
	protected static MidiMessage createMIDINoteOffMessage(
		int channel, int note, int velocity)
	{
		return createMIDINoteMessage(
			ShortMessage.NOTE_OFF, channel, note, velocity);
	}
	// main message
	protected static MidiMessage createMIDINoteMessage(
		int command, int channel, int note, int velocity)
	{
		ShortMessage msg = new ShortMessage();
		try {
			msg.setMessage(command, channel, note, velocity);
		}catch(InvalidMidiDataException e){
			e.printStackTrace();
		}
		return msg;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		// read the input and translate it to Morse
		String fileName = "morseAlphabet.txt";
		MorseCode.readFile(fileName);
		MorseCode.getInput();
		String output = MorseCode.convert();

		// ask for instrument type
		System.out.println("Insert instrument number (1-128, for help see" 
			+ "wikipedia.org/wiki/General_MIDI). Type 0 for random instruments" 
			+ "(this disables the use of cryptography): ");
		Scanner scan = new Scanner(System.in);
		int instrumentType = scan.nextInt();   // convert input to int
		
		// set the default note for all instruments
		int note = 60;  

		// ask if user wants to use encryption
		System.out.println("Enable Encryption? (0-No, 1-Yes): ");
		Scanner scan2 = new Scanner(System.in);
		int encrypt = scan2.nextInt();
		scan2.close();

		// for no encryption, do simple translation
		if (encrypt == 0) {
			System.out.println("Morse:");
			System.out.println(output);
		}
		// for encrytpion, use simple Substitution method
		if (encrypt == 1) {
			String outputEncrypt = MorseCode.convertEncrypt();
			System.out.println("Morse Encrypted:");
			System.out.println(outputEncrypt);
		}

		// play sound
		System.out.println("Playing sound...");
		for (int i=0; i<output.length(); i++) {
			// set a random instrument for each letter (with no encryption)
			if (instrumentType == 0 && 
				(output.charAt(i) == '/' || output.charAt(i) == ' '))
			{
				Random rand = new Random();
				instrumentType = rand.nextInt(128) + 1;
				note = rand.nextInt(128);
			}

			// play the instrument for each morse code
			try {
				// initialize synthesizer to start generating sound
				Synthesizer synthesizer = MidiSystem.getSynthesizer();
				synthesizer.open();
				
				// store the set of instruments
				final Instrument[] instruments = 
					synthesizer.getDefaultSoundbank().getInstruments();
				
				// get the set of channels
				MidiChannel soundChannel = synthesizer.getChannels()[0];
				soundChannel.programChange(
					instruments[instrumentType].getPatch().getProgram());
				
				// get MIDI in-receiver
				Receiver receiver = synthesizer.getReceiver();

				// start the note
				receiver.send(createMIDINoteOnMessage(
					0, note, 127), 0);
				
				// play longer notes on dashes
				int sleep = 500;
				if (output.charAt(i) == '-'){
					sleep = 1500;
				}
				Thread.sleep(sleep); 

				// stop the note
				receiver.send(createMIDINoteOffMessage(
					0, note, 127), 0);

			} catch (MidiUnavailableException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Sound finished.");
	}
}
