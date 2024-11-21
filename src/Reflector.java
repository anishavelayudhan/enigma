public class Reflector extends Encoder {
    public Reflector(int order, RotorConfig rotor) {
        super(rotor, 'A');
    }

    @Override
    public void setForwardMap() {
        for (int i = 0; i < this.rotor.getWiring().length; i++) {
            for (int j = i + 1; j < this.rotor.getWiring().length; j++) {
                if (this.rotor.getWiring()[i] == this.rotor.getWiring()[j]) {
                    this.forwardMap.put(i, this.rotor.getWiring()[j]);
                    this.forwardMap.put(j, this.rotor.getWiring()[i]);
                    break;
                }
            }
        }
    }

    @Override
    public int encodeForward(int c) {
        return this.forwardMap.get(c) -'A';
    }

    @Override
    public void setBackwardMap() {
        throw new UnsupportedOperationException("Reflector does not support backward mapping");
    }

    @Override
    public int encodeBackward(int c) {
        throw new UnsupportedOperationException("Reflector does not support backward encoding");
    }
}
