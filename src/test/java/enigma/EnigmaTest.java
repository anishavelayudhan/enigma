package test.java.enigma;

import main.java.enigma.Enigma;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnigmaTest {

    @Test
    public void testCipher() {
        String[] rotors = {"I", "II", "III"};
        char[] positions = {'D', 'O', 'G'};
        String reflector = "PAPER ENIGMA";

        Enigma enigma = new Enigma(rotors, positions, reflector);

        // Input message
        char[] message = "ENIGMA".toCharArray();

        String cypheredMessage = enigma.cipher(message);

        String expected = "GSPHDX";

        assertEquals(expected, cypheredMessage);
    }

    @Test
    public void testNonLetterCharacters() {
        String[] rotors = {"I", "II", "III"};
        char[] positions = {'M', 'C', 'K'};
        String reflector = "PAPER ENIGMA";

        Enigma enigma = new Enigma(rotors, positions, reflector);

        // Input message with non-letter characters
        char[] message = "ENIGMA REVEALED!".toCharArray();

        String cypheredMessage = enigma.cipher(message);

        String expected = "QMJIDO MZWZJFJR!";

        assertEquals(expected, cypheredMessage);
    }

    @Test
    void testEmptyMessage() {
        String[] rotors = {"I", "II", "III"};
        char[] positions = {'A', 'B', 'C'};
        String reflector = "B";

        Enigma enigma = new Enigma(rotors, positions, reflector);

        char[] message = "".toCharArray();
        String encodedMessage = enigma.cipher(message);

        assertEquals("", encodedMessage, "An empty message should return an empty string.");
    }
}
