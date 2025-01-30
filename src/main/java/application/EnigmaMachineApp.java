package main.java.application;

import main.java.enigma.Enigma;

import java.util.*;


public class EnigmaMachineApp {
    public static boolean validRotors(String[] rotors) {
        if (rotors.length != 3) {
            System.out.println("✖ Please enter exactly 3 rotors (you provided " + rotors.length + ").");
            return false;
        }

        Set<String> validRotors = Set.of("I", "II", "III", "IV", "V");
        Set<String> inputRotors = new HashSet<>(Arrays.asList(rotors));

        // Check for duplicates
        if (inputRotors.size() != 3) {
            System.out.println("✖ Duplicate rotors detected. Each rotor must be unique.");
            return false;
        }

        // Check for invalid rotor types
        if (!validRotors.containsAll(inputRotors)) {
            List<String> invalid = inputRotors.stream()
                    .filter(r -> !validRotors.contains(r))
                    .toList();
            System.out.printf("✖ Invalid rotor(s): %s. Valid options: I, II, III, IV, V.%n", invalid);
            return false;
        }

        return true;
    }


    public static boolean validPositions(char[] positions) {
        if (positions.length != 3) {
            System.out.println("✖ Please enter exactly 3 starting positions (e.g., 'ABC').");
            return false;
        }

        for (char c : positions) {
            if (c < 'A' || c > 'Z') {
                System.out.printf("✖ Invalid character '%c' in positions. Use uppercase A-Z only.%n", c);
                return false;
            }
        }

        return true;
    }


    public static boolean validReflector(String reflector) {
        Set<String> validReflectors = Set.of("B", "C", "B THIN", "PAPER ENIGMA");
        if (!validReflectors.contains(reflector)) {
            System.out.printf("✖ Invalid reflector '%s'. Choose from: B, C, B THIN, PAPER ENIGMA.%n", reflector);
            return false;
        }
        return true;
    }


    private static boolean validPlugboard(String input) {
        if (input == null || input.isEmpty()) return true;

        String[] pairs = input.split("\\s+");
        Set<Character> usedChars = new HashSet<>();

        for (String pair : pairs) {
            if (pair.length() != 2) {
                System.out.println("Invalid pair: " + pair + " (must be 2 letters)");
                return false;
            }
            char c1 = pair.charAt(0);
            char c2 = pair.charAt(1);

            if (c1 == c2) {
                System.out.println("Pair " + pair + ": Letter mapped to itself");
                return false;
            }
            if (usedChars.contains(c1) || usedChars.contains(c2)) {
                System.out.println("Pair " + pair + ": Duplicate letter");
                return false;
            }
            usedChars.add(c1);
            usedChars.add(c2);
        }

        if (usedChars.size() > 20) { // 10 pairs max (20 letters)
            System.out.println("Maximum 10 plugboard pairs allowed");
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] rotors;
        String reflector;
        char[] positions;
        String plugboardPairs;

        System.out.println("\nWelcome to the ENIGMA MACHINE\n");

        do {
            System.out.println("\nChoose your rotors in order separated by comma (I, II, III, IV, V): ");
            rotors = Arrays.stream(scanner.nextLine().toUpperCase().split(","))
                    .map(String::trim)
                    .toArray(String[]::new);
        } while (!validRotors(rotors));

        do {
            System.out.println("\nEnter the starting rotor positions (3 letters, e.g., 'ABC'): ");
            positions = scanner.nextLine().toUpperCase().toCharArray();
        } while (!validPositions(positions));

        do {
            System.out.println("\nChoose your reflector (Paper Enigma, B, C, B Thin): ");
            reflector = scanner.nextLine().toUpperCase();
        } while (!validReflector(reflector));

        do {
            System.out.println("\nEnter plugboard pairs if you want (up to 10 pairs, separated by spaces, e.g., 'AB CD'):");
            plugboardPairs = scanner.nextLine().toUpperCase().replaceAll("[^A-Z ]", "");
        } while (!validPlugboard(plugboardPairs));

        System.out.println("\nEnter a message to cipher: ");
        char[] message = scanner.nextLine().toUpperCase().toCharArray();

        String encodedMessage = new Enigma(rotors, positions, reflector, plugboardPairs).cipher(message);
        System.out.printf("%nHere's your encoded/decoded message:%n%s%n%n", encodedMessage);
    }

}
