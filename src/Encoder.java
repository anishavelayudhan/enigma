import java.util.HashMap;

public abstract class Encoder {
    protected final HashMap<Integer, Character> forwardMap = new HashMap<Integer, Character>();
    protected final HashMap<Character, Integer> backwardMap = new HashMap<Character, Integer>();
    protected final RotorConfig rotor;
    protected final int startingPosition;
    protected int position;

    public Encoder(RotorConfig rotor, char startingPosition) {
        this.rotor = rotor;
        this.startingPosition = startingPosition - 'A';
        this.position = 0;
    }

    public int getCurrentPosition() {
        return (this.startingPosition + this.position) % 26;
    }

    public abstract void setForwardMap();

    public abstract void setBackwardMap();

    public abstract int encodeForward(int c);

    public abstract int encodeBackward(int c);
}