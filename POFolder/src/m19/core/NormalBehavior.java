package m19.core;

public class NormalBehavior implements Behavior {
    private static final long serialVersionUID = -3353775589244593141L;
    static final NormalBehavior _normalBehavior = new NormalBehavior();
    private static final String NORMAL = "NORMAL";
    private static final int MAX_REQUESTS = 3;
    private static final int MAX_REQUEST_TERM_FOR_ONE_COPY = 3;
    private static final int MAX_REQUEST_TERM_FOR_FIVE_COPIES_OR_LESS = 8;
    private static final int MAX_REQUEST_TERM_FOR_MORE_THAN_FIVE_COPIES = 15;

    private NormalBehavior() {}

    @Override
    public String getBehavior() {
        return NORMAL;
    }

    @Override
    public int getMaxRequests() {
        return MAX_REQUESTS;
    }

    @Override
    public boolean checkWorkPrice(Work work) {
        return work.getPrice() > 25 ? false : true;
    }

    @Override
    public int getRequestTerm(int workCopies) {
        if (workCopies == 1)
            return MAX_REQUEST_TERM_FOR_ONE_COPY;

        return workCopies <= 5 ? MAX_REQUEST_TERM_FOR_FIVE_COPIES_OR_LESS : MAX_REQUEST_TERM_FOR_MORE_THAN_FIVE_COPIES;
    }

    @Override
    public void updateBehavior(User user) {
        int lateStreak = user.getLateStreak();
        int onTimeStreak = user.getOnTimeStreak();

        if (onTimeStreak == RESPONSIBLE_STREAK)
            user.changeBehavior(ResponsibleBehavior._responsibleBehavior);

        else if (lateStreak == FAULTY_STREAK)
            user.changeBehavior(FaultyBehavior._faultyBehavior);
    }
}