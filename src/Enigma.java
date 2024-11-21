import java.util.Scanner;

public class Enigma {
    private static final RotorConfig ROTOR_I = new RotorConfig("I", 'Q', "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
    private static final RotorConfig ROTOR_II = new RotorConfig("II", 'E', "AJDKSIRUXBLHWTMCQGZNPYFVOE");
    private static final RotorConfig ROTOR_III = new RotorConfig("III", 'V', "BDFHJLCPRTXVZNYEIWGAKMUSQO");
    private static final RotorConfig REFLECTOR = new RotorConfig("REFLECTOR", 'A', "ABCDEFGDIJKGMKMIEBFTCVVJAT");

    private static char[] positions = new char[0];
    private static char[] order = new char[0];


    public static String cypher(char[] message) {

    }

    public static boolean validOrder(char[] order) {

        return true;
    }

    public static boolean validPositions (char[] s) {
        boolean isValid = false;

        return isValid;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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