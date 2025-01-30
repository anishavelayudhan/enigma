package main.java.enigma;

import java.util.HashMap;
import java.util.Map;

public class Plugboard {
    private final Map<Character, Character> wiring = new HashMap<>();

    public Plugboard(String pairs) {
        if (pairs == null || pairs.isEmpty()) return;

        String[] pairArray = pairs.split("\\s+");
        for (String pair : pairArray) {
            char c1 = pair.charAt(0);
            char c2 = pair.charAt(1);
            wiring.put(c1, c2);
            wiring.put(c2, c1);
        }
    }

    public char swap(char c) {
        return wiring.getOrDefault(c, c);
    }
}