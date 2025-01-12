package main.java.application;

import main.java.enigma.Enigma;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.*;

public class EnigmaMachineApp {
    private static final Logger logger = Logger.getLogger(EnigmaMachineApp.class.getName());

    // Static block to configure logger for the application.
    static {
        // Remove any default handlers for the logger
        for (java.util.logging.Handler handler : logger.getHandlers()) {
            logger.removeHandler(handler);
        }

        // Create and set a new console handler with a custom formatter.
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord recordLog) {
                return recordLog.getMessage() + "\n"; // Format the log message to only show the message
            }
        });

        // Add the new handler to the logger and disable default handlers
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false); // Prevent logging from parent handlers
    }

    // Validates the rotor selection: it must have 3 unique rotors from the allowed options.
    public static boolean validRotors(String[] rotors) {
        if (rotors.length != 3) {
            return false; // Must have exactly 3 rotors
        }

        // Set of valid rotor types
        Set<String> validRotors = new HashSet<>(Set.of("I", "II", "III", "IV", "V"));
        Set<String> rotorSet = new HashSet<>(Set.of(rotors)); // Convert input array to a set

        return rotorSet.size() == 3 && validRotors.containsAll(rotorSet);
    }

    // Validates the rotor positions: it must have 3 uppercase letters.
    public static boolean validPositions(char[] positions) {
        return positions.length == 3 &&
                new String(positions).chars().allMatch(c -> c >= 'A' && c <= 'Z');
    }

    // Validates the reflector type. It must match one of the allowed options.
    public static boolean validReflector(String reflector) {
        return reflector.equals("B") ||
                reflector.equals("C") ||
                reflector.equals("B THIN") ||
                reflector.equals("PAPER ENIGMA");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] rotors;
        String reflector;
        char[] positions;

        logger.info("\nWelcome to the ENIGMA MACHINE\n");

        // Prompt user to select rotors until the input is valid.
        do {
            logger.info("\nChoose your rotors in order separated by comma (I, II, III, IV, V): ");
            rotors = scanner.nextLine().toUpperCase().split(", ");
        } while (!validRotors(rotors)); // Ensure rotors are valid

        // Prompt user to enter initial rotor positions until the input is valid.
        do {
            logger.info("\nEnter initial rotor positions (3 letters, e.g., 'ABC'): ");
            positions = scanner.nextLine().toUpperCase().toCharArray();
        } while (!validPositions(positions)); // Ensure positions are valid

        // Prompt user to select a reflector until the input is valid.
        do {
            logger.info("\nChoose your reflector (Paper Enigma, B, C, B Thin): ");
            reflector = scanner.nextLine().toUpperCase();
        } while (!validReflector(reflector)); // Ensure reflector is valid

        // Prompt user to enter the message to be ciphered/deciphered.
        logger.info("\nEnter a message to cypher: ");
        char[] message = scanner.nextLine().toUpperCase().toCharArray();

        // Encrypt/decrypt the message.
        if (logger.isLoggable(Level.INFO)) {
            String encodedMessage = new Enigma(rotors, positions, reflector).cipher(message);
            logger.info(String.format("%nHere's your encoded/decoded message:%n%s%n", encodedMessage));
        }
    }
}
