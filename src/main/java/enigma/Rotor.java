package main.java.enigma;

import java.util.HashMap;
import java.util.Map;

public class Rotor {
    private final char notch;
    private final char[] wiring;

    private final Map<Integer, Integer> forwardMap = new HashMap<>();
    private final Map<Integer, Integer> backwardMap = new HashMap<>();
    private final int startingPosition;
    private int position;

    // Constructor to initialize a rotor with a notch, wiring, and starting position.
    public Rotor(char notch, String wiring, char startingPosition) {
        this.notch = notch;
        this.wiring = wiring.toCharArray();
        this.startingPosition = startingPosition - 'A'; // Convert starting position to index.
        this.position = 0; // Initial position of the rotor.

        setForwardMap(); // Map the wiring for forward encoding.
        setBackwardMap(); // Map the wiring for backward encoding.
    }

    // Factory method to create a rotor based on its type and starting position.
    public static Rotor create(String name, char startingPosition) {
        return switch (name.toUpperCase()) {
            case "I" -> new Rotor('Q', "EKMFLGDQVZNTOWYHXUSPAIBRCJ", startingPosition);
            case "II" -> new Rotor('E', "AJDKSIRUXBLHWTMCQGZNPYFVOE", startingPosition);
            case "III" -> new Rotor('V', "BDFHJLCPRTXVZNYEIWGAKMUSQO", startingPosition);
            case "IV" -> new Rotor( 'J', "ESOVPZJAYQUIRHXLNFTGKDCMWB", startingPosition);
            case "V" -> new Rotor( 'Z', "VZBRGITYUPSDNHLXAWMJQOFECK", startingPosition);
            default -> throw new IllegalArgumentException("Invalid rotor type: " + name);
        };
    }

    public char getNotch() {
        return this.notch; // Return the notch position for rotor turnover.
    }

    public char[] getWiring() {
        return this.wiring; // Return the wiring configuration of the rotor.
    }

    // Calculate the current position of the rotor (with wrap-around).
    public int getCurrentPosition() {
        return (this.startingPosition + this.position) % 26;
    }

    // Set the forward map based on rotor wiring for encoding.
    private void setForwardMap() {
        for (int i = 0; i < this.getWiring().length; i++) {
            this.forwardMap.put(i, this.getWiring()[i] - 'A');
        }
    }

    // Set the backward map based on rotor wiring for decoding.
    private void setBackwardMap() {
        for (int i = 0; i < this.getWiring().length; i++) {
            this.backwardMap.put(this.getWiring()[i] - 'A', i);
        }
    }

    // Rotate the rotor and determine if it should trigger a turnover for the next rotor.
    public boolean rotate() {
        this.position = (this.position + 1) % 26; // Move rotor one step forward.
        return (this.getCurrentPosition() == 0 || this.getCurrentPosition() == this.getNotch() - 'A' + 1);
    }

    // Encode a character in the forward direction using the rotor's wiring.
    public int encodeForward(int c) {
        return (this.forwardMap.get((c + this.getCurrentPosition()) % 26) - this.getCurrentPosition() + 26) % 26;
    }

    // Decode a character in the backward direction using the rotor's wiring.
    public int encodeBackward(int c) {
        return (this.backwardMap.get((c + this.getCurrentPosition()) % 26) - this.getCurrentPosition() + 26) % 26;
    }
}
