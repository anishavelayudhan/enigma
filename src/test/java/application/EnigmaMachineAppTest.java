package test.java.application;

import main.java.application.EnigmaMachineApp;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EnigmaMachineAppTest {

    // Test valid rotors with uppercase input
    @Test
    public void testValidRotors() {
        String[] validRotors = {"I", "II", "III"};
        assertTrue(EnigmaMachineApp.validRotors(validRotors), "Valid rotors with uppercase should pass");
    }

    // Test invalid rotors (not 3 rotors)
    @Test
    public void testInvalidRotors() {
        String[] invalidRotors = {"I", "II"};
        assertFalse(EnigmaMachineApp.validRotors(invalidRotors), "Should fail for invalid number of rotors");
    }

    // Test invalid rotors (containing invalid rotor name)
    @Test
    public void testInvalidRotorsName() {
        String[] invalidRotors = {"I", "II", "VI"};
        assertFalse(EnigmaMachineApp.validRotors(invalidRotors), "Should fail for invalid rotor name");
    }

    // Test valid positions
    @Test
    public void testValidPositions() {
        char[] positions = {'A', 'B', 'C'};
        assertTrue(EnigmaMachineApp.validPositions(positions), "Valid positions with uppercase should pass");
    }


    // Test invalid positions (not 3 characters)
    @Test
    public void testInvalidPositionsLength() {
        char[] positions = {'A', 'B'};
        assertFalse(EnigmaMachineApp.validPositions(positions), "Should fail for invalid number of positions");
    }

    // Test valid reflector with uppercase
    @Test
    public void testValidReflectorUpperCase() {
        String reflector = "B";
        assertTrue(EnigmaMachineApp.validReflector(reflector), "Valid reflector with uppercase should pass");
    }

    // Test valid reflector with lowercase
    @Test
    public void testValidReflectorLowerCase() {
        String reflector = "b";
        assertTrue(EnigmaMachineApp.validReflector(reflector.toUpperCase()), "Valid reflector with lowercase should pass");
    }

    // Test invalid reflector
    @Test
    public void testInvalidReflector() {
        String reflector = "D";
        assertFalse(EnigmaMachineApp.validReflector(reflector), "Should fail for invalid reflector");
    }

}
