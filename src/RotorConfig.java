public class RotorConfig {
    private final char notch;
    private final char[] wiring;
    private final String type;

    public RotorConfig(String type, char notch, String wiring) {
        this.type = type;
        this.notch = notch;
        this.wiring = wiring.toCharArray();
    }

    public String getType() {
        return this.type;
    }

    public char getNotch() {
        return this.notch;
    }

    public char[] getWiring() {
        return this.wiring;
    }
}
