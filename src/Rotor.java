import java.util.HashMap;

public class Rotor {
    private HashMap<Integer, Character> forwardMap = new HashMap<Integer, Character>();
    private HashMap<Character, Integer> backwardMap = new HashMap<Character, Integer>();

    private final RotorConfig rotor;
    private final int startingPosition;
    private int position;

    public Rotor(int order, RotorConfig rotor, char startingPosition) {
        this.rotor = rotor;
        this.startingPosition = startingPosition - 'A';
    }

    public int getCurrentPosition() {
        return (this.startingPosition + this.position) % 26;
    }

    public void setForwardMap() {
        for (int i = 0; i < this.rotor.getWiring().length; i++) {
            this.forwardMap.put(i, this.rotor.getWiring()[i]);
        }
    }

    public void setBackwardMap() {
        for (int i = 0; i < this.rotor.getWiring().length; i++) {
            this.backwardMap.put(this.rotor.getWiring()[i], i);
        }
    }

    public boolean rotate() {
        this.position = (this.position + 1) % 26;
        return (this.position == 0 || this.position == this.rotor.getNotch() -'A');
    }

    public int encodeForward (char c)  {
        return (this.forwardMap.get((c + this.getCurrentPosition() - 'A') % 26) - this.getCurrentPosition());
    }

    public int encodeBackward (int c) {
        return ((this.backwardMap.get((c + this.getCurrentPosition()) % 26 + 'A') - this.getCurrentPosition()));
    }
}