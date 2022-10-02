import javax.sound.midi.*;


public class MIDIMessages
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
}