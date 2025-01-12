package main.java.enigma;

import java.util.ArrayList;
import java.util.List;

public class Enigma {
    private final List<Rotor> rotors;
    private final Reflector reflector;

    // Constructor initializes the rotors and reflector based on the given configuration.
    public Enigma(String[] nameRotor, char[] startingPositions, String nameReflector) {
        this.rotors = new ArrayList<>();

        // Create each rotor and add to the list (reverse order for proper wiring).
        for (int i = nameRotor.length - 1; i >= 0; i--) {
            Rotor rotor = Rotor.create(nameRotor[i], startingPositions[i]);
            this.rotors.add(rotor);
        }

        // Create the reflector
        this.reflector = Reflector.create(nameReflector);
    }

    // Cipher the message by processing each character through the rotors and reflector.
    public String cipher(char[] message) {
        char[] cypheredMessage = new char[message.length];

        for (int i = 0; i < message.length; i++) {
            if (!Character.isLetter(message[i])) {
                cypheredMessage[i] = message[i]; // Non-letter characters remain unchanged.
                continue;
            }
            int letter = message[i] - 'A'; // Convert character to index (0-25).

            rotateRotor(); // Rotate rotors before encoding each character.

            // Encode the letter through each rotor (forward direction).
            for (Rotor rotor : this.rotors) {
                letter = rotor.encodeForward(letter);
            }

            // Reflect the letter using the configured reflector.
            letter = this.reflector.reflect(letter);

            // Encode the letter through each rotor (reverse direction).
            for (int j = this.rotors.size() - 1; j >= 0; j--) {
                letter = this.rotors.get(j).encodeBackward(letter);
            }

            // Convert the letter index back to character and store it.
            cypheredMessage[i] = (char) (letter + 'A');
        }

        return new String(cypheredMessage);
    }

    // Rotate the rotors, handling the rotor turnover logic.
    private void rotateRotor() {
        boolean shouldRotate = true;

        // Rotate rotors one by one, stop if any rotor doesn't rotate.
        for (Rotor rotor : this.rotors) {
            // Rotate further rotors if a rotor can rotate.
            if (shouldRotate) {
                shouldRotate = rotor.rotate();
            } else {
                break;
            }
        }
    }
}
