package test.java.application;

import main.java.application.EnigmaMachineApp;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnigmaMachineAppTest {

    @Test
    void testValidRotorsDuplicateRotors() {
        // Case: Duplicate rotor should be rejected
        String[] duplicateRotors = {"I", "II", "II"};
        assertFalse(EnigmaMachineApp.validRotors(duplicateRotors), "Duplicate rotors should return false.");
    }

    @Test
    void testValidRotorsNoDuplicate() {
        // Case: No duplicate rotors should pass
        String[] uniqueRotors = {"I", "II", "III"};
        assertTrue(EnigmaMachineApp.validRotors(uniqueRotors), "Unique rotors should return true.");
    }


    @Test
    void testValidPositionsValid() {
        // Case: Valid positions should be accepted
        char[] validPositions = {'A', 'B', 'C'};
        assertTrue(EnigmaMachineApp.validPositions(validPositions), "Valid positions should return true.");
    }

    @Test
    void testValidPositionsInvalidLength() {
        // Case: Invalid length for positions (not 3 characters)
        char[] invalidPositions = {'A', 'B'};
        assertFalse(EnigmaMachineApp.validPositions(invalidPositions), "Invalid position length should return false.");
    }

    @Test
    void testValidPositionsInvalidCharacters() {
        // Case: Invalid characters in positions (non-alphabetic)
        char[] invalidPositions = {'A', 'B', '1'};
        assertFalse(EnigmaMachineApp.validPositions(invalidPositions), "Positions with non-alphabetic characters should return false.");
    }

    @Test
    void testValidReflectorValid() {
        // Case: Valid reflectors should be accepted
        assertTrue(EnigmaMachineApp.validReflector("B"), "Valid reflector 'B' should return true.");
        assertTrue(EnigmaMachineApp.validReflector("C"), "Valid reflector 'C' should return true.");
        assertTrue(EnigmaMachineApp.validReflector("B THIN"), "Valid reflector 'B THIN' should return true.");
        assertTrue(EnigmaMachineApp.validReflector("PAPER ENIGMA"), "Valid reflector 'PAPER ENIGMA' should return true.");
    }

    @Test
    void testValidReflectorInvalid() {
        // Case: Invalid reflectors should be rejected
        assertFalse(EnigmaMachineApp.validReflector("A"), "Invalid reflector 'A' should return false.");
        assertFalse(EnigmaMachineApp.validReflector("X"), "Invalid reflector 'X' should return false.");
        assertFalse(EnigmaMachineApp.validReflector("UNKNOWN"), "Invalid reflector 'UNKNOWN' should return false.");
    }

    // Additional tests for edge cases:

    @Test
    void testValidRotorsEmptyInput() {
        // Case: Empty rotor input should return false
        String[] emptyRotors = {};
        assertFalse(EnigmaMachineApp.validRotors(emptyRotors), "Empty rotor input should return false.");
    }

    @Test
    void testValidRotorsSingleRotorInput() {
        // Case: Single rotor input should return false
        String[] singleRotor = {"I"};
        assertFalse(EnigmaMachineApp.validRotors(singleRotor), "Single rotor input should return false.");
    }

    @Test
    void testValidPositionsEmptyArray() {
        // Case: Empty positions should return false
        char[] emptyPositions = {};
        assertFalse(EnigmaMachineApp.validPositions(emptyPositions), "Empty position array should return false.");
    }

    @Test
    void testValidReflectorEmptyInput() {
        // Case: Empty reflector input should return false
        assertFalse(EnigmaMachineApp.validReflector(""), "Empty reflector input should return false.");
    }
}
