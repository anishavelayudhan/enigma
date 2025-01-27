package main.java.enigma;

import java.util.ArrayList;
import java.util.List;

public class Enigma {
    private final List<Rotor> rotors;
    private final Reflector reflector;


    public Enigma(String[] nameRotor, char[] startingPositions, String nameReflector) {
        this.rotors = new ArrayList<>();

        // Create each rotor and add to the list (reverse order for proper wiring).
        for (int i = nameRotor.length - 1; i >= 0; i--) {
            Rotor rotor = Rotor.create(nameRotor[i], startingPositions[i]);
            this.rotors.add(rotor);
        }

        this.reflector = Reflector.create(nameReflector);
    }

    // Cipher the message by processing each character through the rotors and reflector.
    public String cipher(char[] message) {
        char[] cypheredMessage = new char[message.length];

        for (int i = 0; i < message.length; i++) {
            if (!Character.isLetter(message[i])) {
                cypheredMessage[i] = message[i];
                continue;
            }
            int letter = message[i] - 'A';

            rotateRotor();

            for (Rotor rotor : this.rotors) {
                letter = rotor.encodeForward(letter);
            }

            letter = this.reflector.reflect(letter);

            for (int j = this.rotors.size() - 1; j >= 0; j--) {
                letter = this.rotors.get(j).encodeBackward(letter);
            }

            cypheredMessage[i] = (char) (letter + 'A');
        }

        return new String(cypheredMessage);
    }


    private void rotateRotor() {
        boolean shouldRotate = true;

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
