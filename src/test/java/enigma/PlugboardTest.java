package test.java.enigma;

import main.java.enigma.Plugboard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlugboardTest {

    @Test
    void testEmptyPlugboard() {
        Plugboard plugboard = new Plugboard("");
        assertEquals('A', plugboard.swap('A'));
        assertEquals('Z', plugboard.swap('Z'));
        assertEquals('3', plugboard.swap('3')); // Non-letters pass through
    }

    @Test
    void testSinglePair() {
        Plugboard plugboard = new Plugboard("AB");
        // Letters in pair
        assertEquals('B', plugboard.swap('A'));
        assertEquals('A', plugboard.swap('B'));
        // Letters not in pair
        assertEquals('C', plugboard.swap('C'));
        assertEquals('X', plugboard.swap('X'));
    }

    @Test
    void testMultiplePairs() {
        Plugboard plugboard = new Plugboard("AB CD EF");
        // First pair
        assertEquals('B', plugboard.swap('A'));
        assertEquals('A', plugboard.swap('B'));
        // Second pair
        assertEquals('D', plugboard.swap('C'));
        assertEquals('C', plugboard.swap('D'));
        // Third pair
        assertEquals('F', plugboard.swap('E'));
        assertEquals('E', plugboard.swap('F'));
        // Unmapped letter
        assertEquals('G', plugboard.swap('G'));
    }

    @Test
    void testOverlappingPairs() {
        // Note: This is invalid per Enigma rules but allowed by the Plugboard class
        Plugboard plugboard = new Plugboard("AB BC");
        assertEquals('B', plugboard.swap('A')); // A → B
        assertEquals('C', plugboard.swap('B')); // B → C (last pair wins)
        assertEquals('B', plugboard.swap('C')); // C → B
    }

    @Test
    void testNonLetterPairs() {
        Plugboard plugboard = new Plugboard("12 @#");
        assertEquals('2', plugboard.swap('1'));
        assertEquals('1', plugboard.swap('2'));
        assertEquals('#', plugboard.swap('@'));
        assertEquals('@', plugboard.swap('#'));
    }

    @Test
    void testSelfMapping() {
        // Invalid in practice, but Plugboard allows it
        Plugboard plugboard = new Plugboard("AA");
        assertEquals('A', plugboard.swap('A'));
    }
}