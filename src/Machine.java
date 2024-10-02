import java.util.Scanner;

public class Machine {
    private static Rotors rotors;

    public static String cypher(String s) {
        char[] message = s.toUpperCase().toCharArray();
        char[] outputMessage = new char[message.length];

        for (int i = 0; i < message.length; i++) {
            outputMessage[i] = rotors.encodeForward(message[i]);
            // reflector
            // outputMessage[i] = rotors.encodeBackward(outputMessage[i]);
            rotors.rotate();

        }

        rotors.rotate();
        return new String(outputMessage);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rotors = new Rotors();

        System.out.println("\nWelcome to the ENIGMA MACHINE\n");
        System.out.println("Enter a message to cypher: ");
        String s = scanner.nextLine();

        System.out.println("\nHere's your encoded/decoded message:\n" + cypher(s) + "\n");
    }

}