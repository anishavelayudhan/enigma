public class Rotor {
    private static final int ROTOR_SLOTS = 3;
    // private static final RotorConfig ROTOR_I = new RotorConfig("I", 'V',
    // "JGDQOXUSCAMIFRVTPNEWKBLZYH");
    // private static final RotorConfig ROTOR_II = new RotorConfig("II", 'M',
    // "NTZPSFBOKMWRCJDIVLAEYUXHGQ");
    // private static final RotorConfig ROTOR_III = new RotorConfig("III", 'G',
    // "JVIUBHTCDYAKEQZPOSGXNRMWFL");
    private static final RotorConfig ROTOR_I = new RotorConfig("I", 'Q', "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
    private static final RotorConfig ROTOR_II = new RotorConfig("II", 'E', "AJDKSIRUXBLHWTMCQGZNPYFVOE");
    private static final RotorConfig ROTOR_III = new RotorConfig("III", 'V', "BDFHJLCPRTXVZNYEIWGAKMUSQO");

    private int[] positions = new int[ROTOR_SLOTS];
    private RotorConfig[] rotors = { ROTOR_III, ROTOR_II, ROTOR_I };

    
    public void setOrder(int[] chosenOrder) {
        for (int i = 0; i < ROTOR_SLOTS; i++) {
            switch (chosenOrder[ROTOR_SLOTS - 1 - i]) {
                case 1:
                    rotors[i] = ROTOR_I;
                    break;
                case 2:
                    rotors[i] = ROTOR_II;
                    break;
                case 3:
                    rotors[i] = ROTOR_III;
                    break;
            }
        }
    }


    public void setPositions(char[] startPositions) {
        for (int i = 0; i < ROTOR_SLOTS; i++) {
            positions[i] = startPositions[ROTOR_SLOTS - 1 - i] - 'A';
        }
    }


    public int getPositionLastRotor() {
        return positions[2];
    }


    public void rotate() {
        positions[0] = (positions[0] + 1) % 26;

        if (positions[0] == (rotors[0].getNotch() - 'A' + 1) || positions[0] == 0) {
            positions[1] = (positions[1] + 1) % 26;

            if (positions[1] == (rotors[1].getNotch() - 'A' + 1) || positions[1] == 0) {
                positions[2] = (positions[2] + 1) % 26;
            }
        }
    }


    public char encode(char c) {
        char encodedLetter = c;
        int index;

        for (int rotor = 0; rotor < ROTOR_SLOTS; rotor++) {
            if (rotor > 0) {
                index = ((encodedLetter - 'A' + positions[rotor] - positions[rotor - 1] + 26) % 26);
            } else {
                index = ((encodedLetter - 'A' + positions[rotor]) % 26);
            }
            // System.out.println("\npos index " + positions[rotor]);
            // System.out.println("pos letter " + (char)(positions[rotor] + 'A'));
            // System.out.println("encode index " + index);          
            encodedLetter = rotors[rotor].getWiring()[index];
            // System.out.println("encode letter " + encodedLetter); 
        }

        return encodedLetter;
    }


    public char encodeBackward(char c) {
        char encodedLetter = c;
        int wiringIndex = 0;
    
        for (int rotor = ROTOR_SLOTS - 1; rotor >= 0; rotor--) {
            int index = ((encodedLetter - 'A') + positions[rotor] + 26) % 26;
            // System.out.println(encodedLetter);
            // System.out.println(encodedLetter - 'A');
            
            for (int i = 0; i < 26; i++) {
                if (rotors[rotor].getWiring()[i] == (char)(index + 'A')) {
                    wiringIndex = (i - positions[rotor]) % 26;
                    if (wiringIndex < 0) {
                        wiringIndex += 26;
                    }
                    // System.out.println((wiringIndex));
                    break;
                }
            }

            encodedLetter = (char)(wiringIndex + 'A');
            // System.out.println("Rotor " + (rotor + 1) + " backward encoding: " + encodedLetter);
        }
    
        return encodedLetter;
    }
}