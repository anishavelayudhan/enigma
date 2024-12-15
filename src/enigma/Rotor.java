package enigma;

import java.util.HashMap;
import java.util.Map;

public class Rotor {
    private final char notch;
    private final char[] wiring;

    private final Map<Integer, Integer> forwardMap = new HashMap<>();
    private final Map<Integer, Integer> backwardMap = new HashMap<>();
    private final int startingPosition;
    private int position;

    public Rotor(char notch, String wiring, char startingPosition) {
        this.notch = notch;
        this.wiring = wiring.toCharArray();
        this.startingPosition = startingPosition - 'A';
        this.position = 0;

        setForwardMap();
        setBackwardMap();
    }

    public static Rotor create(String name, char startingPosition) {
        return switch (name) {
            case "I" -> new Rotor('Q', "EKMFLGDQVZNTOWYHXUSPAIBRCJ", startingPosition);
            case "II" -> new Rotor('E', "AJDKSIRUXBLHWTMCQGZNPYFVOE", startingPosition);
            case "III" -> new Rotor('V', "BDFHJLCPRTXVZNYEIWGAKMUSQO", startingPosition);
            case "IV" -> new Rotor( 'J', "ESOVPZJAYQUIRHXLNFTGKDCMWB", startingPosition);
            case "V" -> new Rotor( 'Z', "VZBRGITYUPSDNHLXAWMJQOFECK", startingPosition);
            default -> throw new IllegalArgumentException("Invalid rotor type: " + name);
        };
    }

    public char getNotch() {
        return this.notch;
    }

    public char[] getWiring() {
        return this.wiring;
    }

    public int getCurrentPosition() {
        return (this.startingPosition + this.position) % 26;
    }


    private void setForwardMap() {
        for (int i = 0; i < this.getWiring().length; i++) {
            this.forwardMap.put(i, this.getWiring()[i] - 'A');
        }
    }


    private void setBackwardMap() {
        for (int i = 0; i < this.getWiring().length; i++) {
            this.backwardMap.put(this.getWiring()[i] - 'A', i);
        }
    }

    public boolean rotate() {
        this.position = (this.position + 1) % 26;
        return (this.getCurrentPosition() == 0 || this.getCurrentPosition() == this.getNotch() - 'A' + 1);
    }


    public int encodeForward(int c) {
        return (this.forwardMap.get((c + this.getCurrentPosition()) % 26)  - this.getCurrentPosition() + 26) % 26;
    }


    public int encodeBackward (int c) {
        return (this.backwardMap.get((c + this.getCurrentPosition()) % 26)  - this.getCurrentPosition() + 26) % 26;
    }
}