package enigma;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Reflector {
    private final Map<Integer, Integer> map = new HashMap<>();
    private final char[] wiring;
    private static final String PAPER_ENIGMA = "Paper Enigma";

    public Reflector(String type, String wiring) {
        this.wiring = wiring.toCharArray();

        if (Objects.equals(type, PAPER_ENIGMA)) {
            setPaperEnigmaMap();
        } else {
            setMap();
        }
    }

    public static Reflector create(String name) {
        return switch (name) {
            case "A" -> new Reflector("B", "YRUHQSLDPXNGOKMIEBFZCWVJAT");
            case "B" -> new Reflector("C", "FVPJIAOYEDRZXWGCTKUQSBNMHL");
            case "B Thin" -> new Reflector("B Thin", "ENKQAUYWJICOPBLMDXZVFTHRGS");
            case PAPER_ENIGMA -> new Reflector(PAPER_ENIGMA, "ABCDEFGDIJKGMKMIEBFTCVVJAT");
            default -> throw new IllegalArgumentException("Invalid reflector type: " + name);
        };
    }

    public void setMap() {
        for (int i = 0; i < this.getWiring().length; i++) {
            this.map.put(i, this.getWiring()[i] - 'A');
        }
    }

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

    public char[] getWiring() {
        return this.wiring;
    }


    public int reflect(int c) {
        return this.map.get(c);
    }

}
