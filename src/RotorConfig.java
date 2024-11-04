public class RotorConfig {
    private final char notch;
    private final char[] wiring;
    private final char[] type;

    public RotorConfig(String type, char notch, String wiring) {
        this.type = type.toCharArray();
        this.notch = notch;
        this.wiring = wiring.toCharArray();
    }

    public char[] getType() {
        return this.type;
    }

    public char getNotch() {
        return this.notch;
    }

    public char[] getWiring() {
        return this.wiring;
    }
}
