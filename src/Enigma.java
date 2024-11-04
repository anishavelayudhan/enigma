import java.util.Scanner;

public class Enigma {
    private static Rotor rotors;
    private static Reflector reflector;
    private static char[] positions = {' '};

    public static String cypher(char[] message) {
        char[] outputMessage = new char[message.length];
        rotors.setPositions(positions);

        for (int i = 0; i < message.length; i++) {
            if (message[i] < 'A' || message[i] > 'Z') {
                outputMessage[i] = message[i];
                continue;
            }

            rotors.rotate();
            char encodedForwardChar = rotors.encode(message[i]);
            // char reflectedChar = reflector.reflect(encodedForwardChar);
            char reflectedChar = reflector.paperReflect(encodedForwardChar, rotors.getPositionLastRotor());
            char encodedBackwardChar = rotors.encodeBackward(reflectedChar);

            outputMessage[i] = encodedBackwardChar;
        }
        return new String(outputMessage);
    }

    public static boolean validPositions (char[] s) {
        boolean isValid = false;

        if (s.length != 3) {
            isValid = false;
        } else {
            for (int i = 0; i < 3; i++) {
                if (s[i] < 'A' || s[i] > 'Z' ) {
                    isValid = false;
                    break;
                } else {
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        rotors = new Rotor();
        reflector = new Reflector();

        System.out.println("\nWelcome to the ENIGMA MACHINE\n");
        while (!validPositions(positions)) {
            System.out.println("Enter the starting position of rotors: ");
            positions = scanner.nextLine().toUpperCase().toCharArray();
        }
        System.out.println("\nEnter a message to cypher: ");
        char[] s = scanner.nextLine().toUpperCase().toCharArray();

        System.out.println("\nHere's your encoded/decoded message:\n" + cypher(s) + "\n");
    }

}