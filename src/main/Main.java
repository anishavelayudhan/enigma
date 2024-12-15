package main;

import enigma.Enigma;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static boolean validRotors(String[] rotors) {
        if (rotors.length != 3) {
            return false;
        }

        Set<String> validRotors = new HashSet<>(Set.of("I", "II", "III", "IV", "V"));
        Set<String> rotorSet = new HashSet<>(Set.of(rotors));

        return rotorSet.size() == 3 && validRotors.containsAll(rotorSet);
    }

    public static boolean validPositions(char[] positions) {
        return positions.length == 3 &&
                new String(positions).chars().allMatch(c -> c >= 'A' && c <= 'Z');
    }

    public static boolean validReflector(String reflector) {
        return reflector.equals("B") || reflector.equals("C") || reflector.equals("B Thin") || reflector.equals("Paper Enigma");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] rotors;
        String reflector;
        char[] positions;

        logger.log(Level.INFO, "\nWelcome to the ENIGMA MACHINE\n");

        do {
            logger.log(Level.INFO, "\nChoose your rotors in order separated by comma (I, II, III, IV, V): ");
            rotors = scanner.nextLine().split(", ");
        } while (!validRotors(rotors));

        do {
            logger.log(Level.INFO, "\nEnter initial rotor positions (3 letters, e.g., 'ABC'): ");
            positions = scanner.nextLine().toUpperCase().toCharArray();
        } while (!validPositions(positions));

        do {
            logger.log(Level.INFO, "Choose your reflector (Paper Enigma, B, C, B Thin): ");
            reflector = scanner.nextLine();
        } while (!validReflector(reflector));

        logger.log(Level.INFO, "Enter a message to cypher: ");
        char[] message = scanner.nextLine().toUpperCase().toCharArray();

        if (logger.isLoggable(Level.INFO)) {
            String encodedMessage = new Enigma(rotors, positions, reflector).cipher(message);
            logger.log(Level.INFO, String.format("%nHere's your encoded/decoded message:%n%s%n", encodedMessage));
        }
    }
}