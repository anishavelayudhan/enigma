import java.util.HashMap;
import java.util.Map;

public abstract class Encoder {
    protected final Map<Integer, Integer> forwardMap = new HashMap<>();
    protected final Map<Integer, Integer> backwardMap = new HashMap<>();
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

    public abstract boolean rotate();
}