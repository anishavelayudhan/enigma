package main.java.application;

import main.java.enigma.Enigma;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;



public class EnigmaMachineApp {
    public static boolean validRotors(String[] rotors) {
        if (rotors.length != 3) {
            return false;
        }

        Set<String> validRotors = new HashSet<>(Set.of("I", "II", "III", "IV", "V"));
        Set<String> rotorSet = new HashSet<>(Arrays.asList(rotors));

        return rotorSet.size() == 3 && validRotors.containsAll(rotorSet);
    }


    public static boolean validPositions(char[] positions) {
        return positions.length == 3 &&
                new String(positions).chars().allMatch(c -> c >= 'A' && c <= 'Z');
    }


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

        System.out.println("\nWelcome to the ENIGMA MACHINE\n");

        do {
            System.out.println("\nChoose your rotors in order separated by comma (I, II, III, IV, V): ");
            rotors = Arrays.stream(scanner.nextLine().toUpperCase().split(","))
                    .map(String::trim)
                    .toArray(String[]::new);
        } while (!validRotors(rotors));

        do {
            System.out.println("\nEnter initial rotor positions (3 letters, e.g., 'ABC'): ");
            positions = scanner.nextLine().toUpperCase().toCharArray();
        } while (!validPositions(positions));

        do {
            System.out.println("\nChoose your reflector (Paper Enigma, B, C, B Thin): ");
            reflector = scanner.nextLine().toUpperCase();
        } while (!validReflector(reflector));

        System.out.println("\nEnter a message to cipher: ");
        char[] message = scanner.nextLine().toUpperCase().toCharArray();

        String encodedMessage = new Enigma(rotors, positions, reflector).cipher(message);
        System.out.printf("%nHere's your encoded/decoded message:%n%s%n%n", encodedMessage);
    }

}
