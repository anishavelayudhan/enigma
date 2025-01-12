package test.java.enigma;

import main.java.enigma.Rotor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RotorTest {

    @Test
    void testRotorInitialization() {
        // Test for Rotor I
        Rotor rotorI = Rotor.create("I", 'A');
        assertEquals('Q', rotorI.getNotch(), "Rotor I notch should be 'Q'");
        assertEquals(0, rotorI.getCurrentPosition(), "Rotor I should start at position 0 (A)");

        // Test for Rotor II
        Rotor rotorII = Rotor.create("II", 'B');
        assertEquals('E', rotorII.getNotch(), "Rotor II notch should be 'E'");
        assertEquals(1, rotorII.getCurrentPosition(), "Rotor II should start at position 1 (B)");

        // Test for Rotor III
        Rotor rotorIII = Rotor.create("III", 'C');
        assertEquals('V', rotorIII.getNotch(), "Rotor III notch should be 'V'");
        assertEquals(2, rotorIII.getCurrentPosition(), "Rotor III should start at position 2 (C)");
    }

    @Test
    void testRotorRotation() {
        // Test Rotor I
        Rotor rotorI = Rotor.create("I", 'A');
        rotorI.rotate();
        assertEquals(1, rotorI.getCurrentPosition(), "Rotor I should be at position 1 after one rotation");

        // Test Rotor II
        Rotor rotorII = Rotor.create("II", 'B');
        rotorII.rotate();
        assertEquals(2, rotorII.getCurrentPosition(), "Rotor II should be at position 2 after one rotation");

        // Test Rotor III
        Rotor rotorIII = Rotor.create("III", 'C');
        rotorIII.rotate();
        assertEquals(3, rotorIII.getCurrentPosition(), "Rotor III should be at position 3 after one rotation");
    }

    @Test
    void testRotorEncodingForward() {
        // Test Rotor I
        Rotor rotorI = Rotor.create("I", 'A');
        int encodedI = rotorI.encodeForward(0); // Encoding 'A' (0)
        assertEquals(4, encodedI, "Encoding 'A' should give the result 'E' for Rotor I at position 0 (A)");

        // Test Rotor II
        Rotor rotorII = Rotor.create("II", 'B');
        int encodedII = rotorII.encodeForward(0); // Encoding 'A' (0)
        assertEquals(8, encodedII, "Encoding 'A' should give the result 'I' for Rotor II at position 1 (B)");

        // Test Rotor III
        Rotor rotorIII = Rotor.create("III", 'C');
        int encodedIII = rotorIII.encodeForward(0); // Encoding 'A' (0)
        assertEquals(3, encodedIII, "Encoding 'A' should give the result 'D' for Rotor III at position 2 (C)");
    }

    @Test
    void testRotorEncodingBackward() {
        // Test Rotor I
        Rotor rotorI = Rotor.create("I", 'A');
        int decodedI = rotorI.encodeBackward(4); // Decoding 'E' (4)
        assertEquals(0, decodedI, "Decoding 'E' should give 'A' for Rotor I at position 0 (A)");

        // Test Rotor II
        Rotor rotorII = Rotor.create("II", 'B');
        int decodedII = rotorII.encodeBackward(9); // Decoding 'J' (9)
        assertEquals(2, decodedII, "Decoding 'J' should give 'C' for Rotor II at position 1 (B)");

        // Test Rotor III
        Rotor rotorIII = Rotor.create("III", 'C');
        int decodedIII = rotorIII.encodeBackward(1); // Decoding 'B' (1)
        assertEquals(25, decodedIII, "Decoding 'B' should give 'Z' for Rotor III at position 2 (C)");
    }

    // Test notch functionality for multiple rotors
    @Test
    void testRotorNotch() {
        Rotor rotorI = Rotor.create("I", 'A');

        for (int i = 0; i < 25; i++) {
            boolean result = rotorI.rotate();

            // Assert that it doesn't rotate the next rotor until the notch ('Q') is reached
            if (i < 16) {  // Position before notch (e.g., positions A to P)
                assertFalse(result, "Rotor I should not rotate the next rotor until the notch is reached");
            } else {
                // At notch position (Q), the rotor should rotate the next rotor
                assertTrue(result, "Rotor I should rotate the next rotor when the notch is reached");
                break;
            }
        }
    }
}
