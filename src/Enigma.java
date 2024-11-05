import java.util.Scanner;

public class Enigma {
    private static Rotor rotors;
    private static Reflector reflector;
    private static char[] positions = new char[0];
    private static char[] order = new char[0];

    public static String cypher(char[] message) {
        char[] outputMessage = new char[message.length];

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

    public static boolean validOrder(char[] order) {
        if (order.length != 3) {
            return false;
        }
        for (int i = 0; i < order.length; i++) {
            if (order[i] < 1 || order[i] > 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean validPositions (char[] s) {
        boolean isValid = false;
        if (s.length == 0) {
            isValid = true;
        }
        else if (s.length != 3) {
            System.out.println("\nInvalid input. Please enter exactly 3 letters.");
            isValid = false;
        } else {
            for (int i = 0; i < 3; i++) {
                if (s[i] < 'A' || s[i] > 'Z' ) {
                    System.out.printf("\nInvalid character at position %d: '%c'. Please use letters A-Z.\n", i + 1, s[i]);
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

        // do {
        //     System.out.println("Enter the order of rotors(ENTER for DEFAULT values): ");
        //     order = scanner.nextLine().toCharArray();
        // } while (!validOrder(order));

        do {
            System.out.println("Enter the three starting positions of the rotors (press ENTER for DEFAULT values): ");
            positions = scanner.nextLine().toUpperCase().toCharArray();
        } while (!validPositions(positions));
        

        System.out.println("\nEnter a message to cypher: ");
        char[] s = scanner.nextLine().toUpperCase().toCharArray();

        System.out.println("\nHere's your encoded/decoded message:\n" + cypher(s) + "\n");
    }

}