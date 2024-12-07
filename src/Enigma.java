import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Enigma {
    private static final RotorConfig ROTOR_I =
            new RotorConfig("I", 'Q', "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
    private static final RotorConfig ROTOR_II =
            new RotorConfig("II", 'E', "AJDKSIRUXBLHWTMCQGZNPYFVOE");
    private static final RotorConfig ROTOR_III =
            new RotorConfig("III", 'V', "BDFHJLCPRTXVZNYEIWGAKMUSQO");
    private static final RotorConfig REFLECTOR =
            new RotorConfig("REFLECTOR", 'A', "ABCDEFGDIJKGMKMIEBFTCVVJAT");

    private static List<Rotor> rotors;
    private static Reflector reflector;

    public Enigma(char[] order, char[] positions) {
        rotors = new ArrayList<>();

        // list starts from last rotor in order
        for (int i = order.length - 1; i >= 0; i--) {
            Rotor rotor = createRotor(order[i], positions[i]);
            rotor.setForwardMap();
            rotor.setBackwardMap();

            rotors.add(rotor);
        }

        reflector = new Reflector(REFLECTOR);
        reflector.setForwardMap();
    }

    private Rotor createRotor(char rotorNumber, char position) {
        return switch (rotorNumber) {
            case '1' -> new Rotor(ROTOR_I, position);
            case '2' -> new Rotor(ROTOR_II, position);
            case '3' -> new Rotor(ROTOR_III, position);
            default -> throw new IllegalArgumentException("Invalid rotor number: " + rotorNumber);
        };
    }

    public static char[] cypher(char[] message) {
        char[] cypheredMessage = new char[message.length];
        int prevRotorOffset = 0;

        for (int i = 0; i < message.length; i++) {
            if (!Character.isLetter(message[i])) {
                cypheredMessage[i] = message[i];
                continue;
            }
            int letter = message[i] -'A';

            rotateRotor();

            for (Encoder rotor : rotors) {
                letter = rotor.encodeForward(letter);
            }

            letter = reflector.encodeForward(letter);

            for (int j = rotors.size() - 1; j >= 0; j--) {
                letter = rotors.get(j).encodeBackward(letter);
            }

            cypheredMessage[i] = (char) (letter + 'A');
        }

        return cypheredMessage;
    }

    public static void rotateRotor() {
        boolean shouldRotate = true;

        for (int i = 0; i < rotors.size(); i++) {
            if (shouldRotate) {
                shouldRotate = rotors.get(i).rotate();
            } else {
                break;
            }
        }
    }

    // TODO look into hashSet
    public static boolean validOrder(char[] order) {
        return order.length == 3 &&
                new String(order).chars().allMatch(c -> c >= '1' && c <= '3') &&
                order[0] != order[1] && order[1] != order[2] && order[0] != order[2];
    }

    public static boolean validPositions(char[] positions) {
        return positions.length == 3 &&
                new String(positions).chars().allMatch(c -> c >= 'A' && c <= 'Z');
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] order;
        char[] positions;

        System.out.println("\nWelcome to the ENIGMA MACHINE\n");

        do {
            System.out.print("Enter rotor order (3 digits, e.g., '123'): ");
            order = scanner.nextLine().toCharArray();
        } while (!validOrder(order));

        do {
            System.out.print("Enter initial rotor positions (3 letters, e.g., 'ABC'): ");
            positions = scanner.nextLine().toUpperCase().toCharArray();
        } while (!validPositions(positions));

        Enigma enigma = new Enigma(order, positions);

        System.out.println("\nEnter a message to cypher: ");
        char[] s = scanner.nextLine().toUpperCase().toCharArray();

        System.out.println("\nHere's your encoded/decoded message:\n" + new String(cypher(s)) + "\n");
    }

}