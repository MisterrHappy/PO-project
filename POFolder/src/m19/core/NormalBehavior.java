package m19.core;

public class NormalBehavior extends Behavior {
    public static final NormalBehavior _normalBehavior = new NormalBehavior();
    private static final String NORMAL = "NORMAL";

    protected String getBehavior() {
        return NORMAL;
    }
}