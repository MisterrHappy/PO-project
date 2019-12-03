package m19.core;

public class FaultyBehavior extends Behavior {
    public static final FaultyBehavior _faultyBehavior = new FaultyBehavior();
    private static final String FAULTY = "FALTOSO";
    private static final int MAX_REQUESTS = 1;
    private static final int FAULTY_REQUEST_TERM = 2;

    private FaultyBehavior() {}

    @Override
    protected String getBehavior() {
        return FAULTY;
    }

    @Override
    protected int getMaxRequests() {
        return MAX_REQUESTS;
    }

    @Override
    protected boolean checkWorkPrice(Work work) {
        return work.getPrice() > 25 ? false : true;
    }

    @Override
    protected int getRequestTerm(int workCopiesAvailable) {
        return FAULTY_REQUEST_TERM;
    }
}