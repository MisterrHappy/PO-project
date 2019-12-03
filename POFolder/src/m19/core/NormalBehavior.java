package m19.core;

public class NormalBehavior extends Behavior {
    public static final NormalBehavior _normalBehavior = new NormalBehavior();
    private static final String NORMAL = "NORMAL";
    private static final int MAX_REQUESTS = 3;
    private static final int MAX_REQUEST_TERM_FOR_ONE_COPY = 3;
    private static final int MAX_REQUEST_TERM_FOR_FIVE_COPIES_OR_LESS = 8;
    private static final int MAX_REQUEST_TERM_FOR_MORE_THAN_FIVE_COPIES = 15;

    private NormalBehavior() {}

    @Override
    protected String getBehavior() {
        return NORMAL;
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
        if (workCopiesAvailable == 1)
            return MAX_REQUEST_TERM_FOR_ONE_COPY;

        return workCopiesAvailable <= 5 ? MAX_REQUEST_TERM_FOR_FIVE_COPIES_OR_LESS : MAX_REQUEST_TERM_FOR_MORE_THAN_FIVE_COPIES;
    }
}