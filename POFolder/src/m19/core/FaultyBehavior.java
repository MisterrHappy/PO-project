package m19.core;

public class FaultyBehavior implements Behavior {
    private static final long serialVersionUID = -4479798849466650700L;
    static final FaultyBehavior _faultyBehavior = new FaultyBehavior();
    private static final String FAULTY = "FALTOSO";
    private static final int MAX_REQUESTS = 1;
    private static final int FAULTY_REQUEST_TERM = 2;
    private static final int REACH_NORMAL_BEHAVIOR = 3;

    private FaultyBehavior() {}

    @Override
    public String getBehavior() {
        return FAULTY;
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
        return FAULTY_REQUEST_TERM;
    }

    @Override
    public void updateBehavior(User user) {
        int onTimeStreak = user.getOnTimeStreak();
        int lateStreak = user.getLateStreak();

        if (onTimeStreak == REACH_NORMAL_BEHAVIOR) 
            user.changeBehavior(NormalBehavior._normalBehavior);
    }
}