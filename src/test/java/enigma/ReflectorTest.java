package test.java.enigma;

import main.java.enigma.Reflector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReflectorTest {

    private Reflector reflectorA;
    private Reflector reflectorB;
    private Reflector reflectorBThin;
    private Reflector reflectorPaperEnigma;

    @BeforeEach
    public void setup() {
        reflectorA = Reflector.create("A");
        reflectorB = Reflector.create("B");
        reflectorBThin = Reflector.create("B THIN");
        reflectorPaperEnigma = Reflector.create("PAPER ENIGMA");
    }

    @Test
    public void testReflectorACreation() {
        // Verify that the reflector A wiring matches the expected values.
        String expectedWiring = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
        assertEquals(expectedWiring, new String(reflectorA.getWiring()));
    }

    @Test
    public void testReflectorBCreation() {
        // Verify that the reflector B wiring matches the expected values.
        String expectedWiring = "FVPJIAOYEDRZXWGCTKUQSBNMHL";
        assertEquals(expectedWiring, new String(reflectorB.getWiring()));
    }

    @Test
    public void testReflectorBThinCreation() {
        // Verify that the reflector B Thin wiring matches the expected values.
        String expectedWiring = "ENKQAUYWJICOPBLMDXZVFTHRGS";
        assertEquals(expectedWiring, new String(reflectorBThin.getWiring()));
    }

    @Test
    public void testPaperEnigmaCreation() {
        // Verify that the PAPER ENIGMA reflector wiring matches the expected values.
        String expectedWiring = "ABCDEFGDIJKGMKMIEBFTCVVJAT";
        assertEquals(expectedWiring, new String(reflectorPaperEnigma.getWiring()));
    }

    @Test
    public void testReflectorAReflection() {
        // Test that reflector A correctly reflects a character (e.g., 'A' -> 'Y').
        int reflectedIndex = reflectorA.reflect(0); // 'A' is 0
        assertEquals(24, reflectedIndex); // 'Y' is at index 24
    }

    @Test
    public void testReflectorBReflection() {
        // Test that reflector B correctly reflects a character (e.g., 'A' -> 'F').
        int reflectedIndex = reflectorB.reflect(0); // 'A' is 0
        assertEquals(5, reflectedIndex); // 'F' is at index 5
    }

    @Test
    public void testReflectorBThinReflection() {
        // Test that reflector B Thin correctly reflects a character (e.g., 'A' -> 'E').
        int reflectedIndex = reflectorBThin.reflect(0); // 'A' is 0
        assertEquals(4, reflectedIndex); // 'E' is at index 4
    }

    @Test
    public void testPaperEnigmaReflection() {
        // Test that the Paper Enigma reflector correctly reflects a character (e.g., 'A' -> 'A').
        int reflectedIndex = reflectorPaperEnigma.reflect(0); // 'A' is 0
        assertEquals(24, reflectedIndex); // 'A' is at index 24 in the PAPER ENIGMA map
    }

    @Test
    public void testReflectorMultipleReflections() {
        assertEquals(24, reflectorA.reflect(0)); // 'A' -> 'Y'
        assertEquals(5, reflectorB.reflect(0)); // 'A' -> 'F'
        assertEquals(4, reflectorBThin.reflect(0)); // 'A' -> 'E'
        assertEquals(24, reflectorPaperEnigma.reflect(0)); // 'A' -> 'A'
    }
}
