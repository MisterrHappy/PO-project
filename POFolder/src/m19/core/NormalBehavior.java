package m19.core;

public class NormalBehavior extends Behavior {
    public static final NormalBehavior _normalBehavior = new NormalBehavior();
    private static final String NORMAL = "NORMAL";
    private static final int MAX_REQUESTS = 3;

    private NormalBehavior() {}

    protected String getBehavior() {
        return NORMAL;
    }

    protected int getMaxRequests() {
        return MAX_REQUESTS;
    }

    protected boolean checkWorkPrice(Work work) {
        return work.getPrice() > 25 ? false : true;
    }
}