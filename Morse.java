import java.io.FileNotFoundException;
import javax.sound.midi.*;
import java.util.Random;


public class Morse 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		String fileName = "morseAlphabet.txt";

		// read the file and input from user
		Input.readFile(fileName);
		Input.getInputText();
		int instrumentType = Input.getInputInstrumentType();
		int encrypt = 0;
		if (instrumentType != 0){
			encrypt = Input.getInputEncryption();
		}
		int note = 60;  // set the default instrument note
		String output = MorseCode.convert();  // translate text to Morse

		// for encrytpion, use simple Substitution method
		if (encrypt == 1) {
			String outputEncrypt = MorseCode.convertEncrypt();
			System.out.println("Morse Encrypted:");
			System.out.println(outputEncrypt);
		} else {
			System.out.println("Morse:");
			System.out.println(output);
		}

		// play sound
		System.out.println("Playing sound...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		for (int i=0; i<output.length(); i++) {
			// sets a random instrument for each letter (with no encryption)
			if (Input.inputInstrument == 0 && 
				(output.charAt(i) == '/' || output.charAt(i) == ' '))
			{
				Random rand = new Random();
				instrumentType = rand.nextInt(128) + 1;
				note = rand.nextInt(128);
			}

			// pause between letters
			if ((output.charAt(i) == '/' || output.charAt(i) == ' '))
			{			
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
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
				receiver.send(MIDIMessages.createMIDINoteOnMessage(
					0, note, 127), 0);
				
				// play longer notes on dashes
				int sleep = 500;
				if (output.charAt(i) == '-'){
					sleep = 1500;
				}
				Thread.sleep(sleep); 

				// stop the note
				receiver.send(MIDIMessages.createMIDINoteOffMessage(
					0, note, 127), 0);

			} catch (MidiUnavailableException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Sound finished.");
	}
}
