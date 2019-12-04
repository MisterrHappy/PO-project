package m19.core;

public class ResponsibleBehavior implements Behavior {
    static final ResponsibleBehavior _responsibleBehavior = new ResponsibleBehavior();
    private static final String RESPONSIBLE = "CUMPRIDOR";
    private static final int MAX_REQUESTS = 5;
    private static final int MAX_REQUEST_TERM_FOR_ONE_COPY = 8;
    private static final int MAX_REQUEST_TERM_FOR_FIVE_COPIES_OR_LESS = 15;
    private static final int MAX_REQUEST_TERM_FOR_MORE_THAN_FIVE_COPIES = 30;

    private ResponsibleBehavior() {}

    @Override
    public String getBehavior() {
        return RESPONSIBLE;
    }

    @Override
    public int getMaxRequests() {
        return MAX_REQUESTS;
    }

    @Override    
    public boolean checkWorkPrice(Work work) {
        return true;
    }

    @Override
    public int getRequestTerm(int workCopiesAvailable) {
        if (workCopiesAvailable == 1)
            return MAX_REQUEST_TERM_FOR_ONE_COPY;
            
        return workCopiesAvailable <= 5 ? MAX_REQUEST_TERM_FOR_FIVE_COPIES_OR_LESS : MAX_REQUEST_TERM_FOR_MORE_THAN_FIVE_COPIES;
    }

    @Override
    public void updateBehavior(User user) {
        if (user.getOnTimeStreak() < RESPONSIBLE_STREAK) {
            user.setOnTimeStreak(0);
            user.changeBehavior(NormalBehavior._normalBehavior);
        }
    }
}