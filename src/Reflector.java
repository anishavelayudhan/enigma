public class Reflector {
    char[] reflectorWiring = "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray();
    char[] reflectorWiring2 = "ABCDEFGDIJKGMKMIEBFTCVVJAT".toCharArray();

    public char reflect(char c) {
        // System.out.println(reflectorWiring[(c - 'A')]);
        return reflectorWiring[(c - 'A')];
    }

    public char paperReflect(char c, int position) {
        int index = (c - 'A' - position + 26) % 26;
        char currentLetter = reflectorWiring2[index];
        // System.out.println("index " + index);
        
        for (int i = 0; i < reflectorWiring2.length; i++) {
            if (reflectorWiring2[i] == currentLetter && i != index) {
                // System.out.println(i);
                // System.out.println("reflect letter " + reflectorWiring2[i]);
                // System.out.println("reflect letter " + (char)((i + position + 26) % 26 + 'A'));
                // System.out.println("reflect letter " + (char)(i + 'A'));
                return (char)(i + 'A');        
            }
        }
        
        return c;
    }
}
