import java.util.HashMap;

public class Rotor extends Encoder {

    public Rotor(RotorConfig rotor, char startingPosition) {
        super(rotor, startingPosition);
    }

    @Override
    public void setForwardMap() {
        for (int i = 0; i < this.rotor.getWiring().length; i++) {
            this.forwardMap.put(i, this.rotor.getWiring()[i] - 'A');
        }
    }

    @Override
    public void setBackwardMap() {
        for (int i = 0; i < this.rotor.getWiring().length; i++) {
            this.backwardMap.put(this.rotor.getWiring()[i] - 'A', i);
        }
    }

    public boolean rotate() {
        this.position = (this.position + 1) % 26;
        return (this.getCurrentPosition() == 0 || this.getCurrentPosition() == this.rotor.getNotch() - 'A' + 1);
    }

    @Override
    public int encodeForward(int c) {
        return (this.forwardMap.get((c + this.getCurrentPosition()) % 26)  - this.getCurrentPosition() + 26) % 26;
    }

    @Override
    public int encodeBackward (int c) {
        return (this.backwardMap.get((c + this.getCurrentPosition()) % 26)  - this.getCurrentPosition() + 26) % 26;
    }
}