package m19.core;

public class FaultyBehavior extends Behavior {
    public static final FaultyBehavior _faultyBehavior = new FaultyBehavior();
    private static final String FAULTY = "FALTOSO";

    protected String getBehavior() {
        return FAULTY;
    }
}