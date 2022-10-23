package src.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

import src.main.Input;
import src.main.MorseCode;


public class MorseCodeTest {
    @Test
    public void testconvert() throws FileNotFoundException{
        MorseCode m = new MorseCode();
        Input.readFile("src/main/morseAlphabet.txt");

        String result = m.convert("hello world 09");
        String expectedResult = ".... . .-.. .-.. ---  /  .-- --- .-. .-.. -..  /  ----- ----. ";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testconvertEncrypt() throws FileNotFoundException{
        MorseCode m = new MorseCode();
        Input.readFile("C:/Vullnet/Side Projects/Morsify text/src/main/morseAlphabet.txt");

        String result = m.convertEncrypt(
            "hello world 09", false);
        String expectedResult = ".. ..-. -- -- .--. -... -..- .--. ... -- . -...  /  ----- ";
        assertEquals(expectedResult, result);
    }
}
