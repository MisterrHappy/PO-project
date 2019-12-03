package m19.core;

public class ResponsibleBehavior extends Behavior {
    public static final ResponsibleBehavior _responsibleBehavior = new ResponsibleBehavior();
    private static final String RESPONSIBLE = "CUMPRIDOR";
    private static final int MAX_REQUESTS = 5;
    private static final int MAX_REQUEST_TERM_FOR_ONE_COPY = 8;
    private static final int MAX_REQUEST_TERM_FOR_FIVE_COPIES_OR_LESS = 15;
    private static final int MAX_REQUEST_TERM_FOR_MORE_THAN_FIVE_COPIES = 30;

    private ResponsibleBehavior() {}

    protected String getBehavior() {
        return RESPONSIBLE;
    }

    protected int getMaxRequests() {
        return MAX_REQUESTS;
    }

    protected boolean checkWorkPrice(Work work) {
        return true;
    }

    @Override
    protected int getRequestTerm(int workCopiesAvailable) {
        if (workCopiesAvailable == 1)
            return MAX_REQUEST_TERM_FOR_ONE_COPY;
            
        return workCopiesAvailable <= 5 ? MAX_REQUEST_TERM_FOR_FIVE_COPIES_OR_LESS : MAX_REQUEST_TERM_FOR_MORE_THAN_FIVE_COPIES;
    }
}