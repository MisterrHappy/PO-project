package m19.core;

public class FaultyBehavior extends Behavior {
    public static final FaultyBehavior _faultyBehavior = new FaultyBehavior();
    private static final String FAULTY = "FALTOSO";
    private static final int MAX_REQUESTS = 1;

    private FaultyBehavior() {}

    protected String getBehavior() {
        return FAULTY;
    }

    protected int getMaxRequests() {
        return MAX_REQUESTS;
    }

    protected boolean checkWorkPrice(Work work) {
        return work.getPrice() > 25 ? false : true;
    }
}