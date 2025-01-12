package main.java.enigma;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Reflector {
    private final Map<Integer, Integer> map = new HashMap<>();
    private final char[] wiring;
    private static final String PAPER_ENIGMA = "PAPER ENIGMA";

    // Constructor that initializes the reflector with a given type and wiring.
    public Reflector(String type, String wiring) {
        this.wiring = wiring.toCharArray();
        if (Objects.equals(type, PAPER_ENIGMA)) {
            setPaperEnigmaMap();
        } else {
            setMap();
        }
    }

    // Factory method to create a Reflector based on its name.
    public static Reflector create(String name) {
        return switch (name) {
            case "A" -> new Reflector("B", "YRUHQSLDPXNGOKMIEBFZCWVJAT");
            case "B" -> new Reflector("C", "FVPJIAOYEDRZXWGCTKUQSBNMHL");
            case "B THIN" -> new Reflector("B Thin", "ENKQAUYWJICOPBLMDXZVFTHRGS");
            case PAPER_ENIGMA -> new Reflector(PAPER_ENIGMA, "ABCDEFGDIJKGMKMIEBFTCVVJAT");
            default -> throw new IllegalArgumentException("Invalid reflector type: " + name);
        };
    }

    // Sets the standard reflector map using the wiring.
    public void setMap() {
        for (int i = 0; i < this.getWiring().length; i++) {
            this.map.put(i, this.getWiring()[i] - 'A');
        }
    }

    // Sets the special Paper Enigma reflector map with duplicate mappings.
    public void setPaperEnigmaMap() {
        for (int i = 0; i < this.getWiring().length; i++) {
            for (int j = 0; j < this.getWiring().length; j++) {
                if (i != j && this.getWiring()[i] == this.getWiring()[j]) {
                    this.map.put(i, j);
                    break;
                }
            }
        }
    }

    // Returns the wiring configuration of the reflector.
    public char[] getWiring() {
        return this.wiring;
    }

    // Reflects the input character index using the reflector's map.
    public int reflect(int c) {
        return this.map.get(c);
    }
}
