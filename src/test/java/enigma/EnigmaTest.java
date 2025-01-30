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
        String plugboard = " ";

        Enigma enigma = new Enigma(rotors, positions, reflector, plugboard);

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
        String plugboard = " ";

        Enigma enigma = new Enigma(rotors, positions, reflector, plugboard);

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
        String plugboard = " ";

        Enigma enigma = new Enigma(rotors, positions, reflector, plugboard);

        char[] message = "".toCharArray();
        String encodedMessage = enigma.cipher(message);

        assertEquals("", encodedMessage, "An empty message should return an empty string.");
    }

    @Test
    void testPlugboardSwapsLetters() {
        String[] rotors = {"I", "II", "III"};
        char[] positions = {'A', 'A', 'A'}; // All rotors at position 'A'
        String reflector = "B";
        String plugboard = "AB"; // Swap A ↔ B

        Enigma enigma = new Enigma(rotors, positions, reflector, plugboard);

        // Encrypt 'A' (should swap to 'B' before rotors, process, then swap back if needed)
        char[] message = "A".toCharArray();
        String cyphered = enigma.cipher(message);

        // Expected result based on rotor wiring and plugboard swap
        assertEquals("R", cyphered);
    }

    @Test
    void testPlugboardRoundTrip() {
        String[] rotors = {"III", "II", "I"};
        char[] positions = {'M', 'E', 'S'};
        String reflector = "B THIN";
        String plugboard = "AE BF CM"; // Multiple pairs

        Enigma enigma = new Enigma(rotors, positions, reflector, plugboard);

        String original = "SECRETMESSAGE";
        char[] message = original.toCharArray();

        // Encrypt and decrypt (Enigma is symmetric)
        String encrypted = enigma.cipher(message);
        String decrypted = enigma.cipher(encrypted.toCharArray());

        assertEquals(original, decrypted);
    }

    @Test
    void testPlugboardChangesOutput() {
        String[] rotors = {"IV", "V", "II"};
        char[] positions = {'X', 'Y', 'Z'};
        String reflector = "C";

        // Without plugboard
        Enigma enigmaWithout = new Enigma(rotors, positions, reflector, "");
        // With plugboard
        Enigma enigmaWith = new Enigma(rotors, positions, reflector, "QW ER");

        String message = "ENIGMA";
        String cipherWithout = enigmaWithout.cipher(message.toCharArray());
        String cipherWith = enigmaWith.cipher(message.toCharArray());

        assertNotEquals(cipherWithout, cipherWith, "Plugboard should alter the ciphertext");
    }

    @Test
    void testPlugboardWithAllPairs() {
        String[] rotors = {"I", "II", "III"};
        char[] positions = {'B', 'E', 'T'};
        String reflector = "PAPER ENIGMA";
        String plugboard = "AB CD EF GH IJ KL MN OP QR ST"; // 10 pairs (max)

        Enigma enigma = new Enigma(rotors, positions, reflector, plugboard);

        char[] message = "HELLOWORLD".toCharArray();
        String encrypted = enigma.cipher(message);

        // Ensure no exceptions and non-empty result
        assertNotNull(encrypted);
        assertFalse(encrypted.isEmpty());
    }
}
