# Instrumental and Cryptic Morse Code

## Description

Converts text to Morse Code and plays intstrumental audio of your choice for each Morse symbol. It is possible to use Substitution method for encryption of the converted text to code.

## Getting Started

### Dependencies

* Developed with Java in VS Code.

### Executing program

* Clone the repository.
* Open the terminal in the path of the downloaded files.
* Run the file 'Morse.java' from a compiler:
```
javac Morse.java
java Morse
```
* Alternatively, use an editor (e.g. VS Code) to compile the file 'Morse.java'.

### Usage

* Method 1: Play audio with random instruments and without encryption:
```
> Type some text (a-z, 0-9 and spaces):
Hello World
> Insert instrument number (1-128, for help see wikipedia.org/wiki/General_MIDI).
> Type 0 for random instruments (this disables the use of cryptography): 
0
> Morse:
> .... . .-.. .-.. ---  /  .-- --- .-. .-.. -.. 
> Playing sound...
> Sound finished.
```
* Method 2: Play audio with selected aduio (e.g. Acoustic Grand Piano) and without encryption:
```
> Type some text (a-z, 0-9 and spaces):
Hello World
> Insert instrument number (1-128, for help see wikipedia.org/wiki/General_MIDI).
> Type 0 for random instruments (this disables the use of cryptography): 
1
> Enable Encryption? (0-No, 1-Yes): 
0
> Morse:
> .... . .-.. .-.. ---  /  .-- --- .-. .-.. -.. 
> Playing sound...
> Sound finished.
```
* Method 3: Play audio with selected aduio (e.g. Acoustic Grand Piano) and with encryption:
```
> Type some text (a-z, 0-9 and spaces):
Hello World
> Insert instrument number (1-128, for help see wikipedia.org/wiki/General_MIDI).
> Type 0 for random instruments (this disables the use of cryptography): 
1
> Enable Encryption? (0-No, 1-Yes): 
1
> Morse Encrypted:
> ....- .-. .-. .. ....- .-. -- -.- -.... -. .----
> Playing sound...
> Sound finished.
```

### Changing the data
* Change the default instrument note in 'Morse.java':
```
int note = 60;
```
* Change the pause between letters from 1000ms to something else in 'Morse.java':
```			
try {
  Thread.sleep(1000);
} catch (InterruptedException e) {
  e.printStackTrace();
}
```
* Change the length of sounds for Morse dots and dashes in 'Morse.java':
```
int sleep = 500;   // for dots
if (output.charAt(i) == '-'){
  sleep = 1500;   // for dashes
}
```

## Help
* See list of MIDI instrument types and their codes here: https://en.wikipedia.org/wiki/General_MIDI#Parameter_interpretations
* See list of MIDI instrument notes and their codes here: https://en.scratch-wiki.info/wiki/MIDI_Notes

## Version History
* 0.1
    * Initial Release
