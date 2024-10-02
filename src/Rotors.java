public class Rotors {
    private static char[][] rotors = {
            "JGDQOXUSCAMIFRVTPLNEWKBZYH".toCharArray(),
            "NTZPSFBOKMWRCDJIVLAEYUXHGQ".toCharArray(),
            "JVIUBHTCDYAKEQZPOSGXNRMWFL".toCharArray()
    };

    private int[] positions = new int[3];

    public void rotate() {
        positions[0] = (positions[0] + 1) % 26;
        if (positions[0] == 0) {
            positions[1] = (positions[1] + 1) % 26;
            if (positions[1] == 0) {
                positions[2] = (positions[2] + 1) % 26;
            }
        }
    }

    public char encodeForward(char c) {
        char encodedLetter = c;
        int index = encodedLetter - 'A';

        for (int rotor = 0; rotor < rotors.length; rotor++) {
            index = (index + positions[rotor]) % 26;
            encodedLetter = rotors[rotor][index];
            index = encodedLetter - 'A';
        }
        return encodedLetter;
    }

    public char encodeBackward(char c) {
        char encodedLetter = c;
        int index = encodedLetter - 'A';

        // for (int rotor = rotors.length; rotor < rotors.length; rotor--) {
        //     index = (index + positions[rotor]) % 26;
        //     encodedLetter = rotors[rotor][index];
        //     index = encodedLetter - 'A';
        // }
        return encodedLetter;
    }

}
