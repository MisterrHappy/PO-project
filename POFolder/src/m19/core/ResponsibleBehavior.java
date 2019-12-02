package m19.core;

public class ResponsibleBehavior extends Behavior {
    public static final ResponsibleBehavior _responsibleBehavior = new ResponsibleBehavior();
    private static final String RESPONSIBLE = "CUMPRIDOR";
    private static final int MAX_REQUESTS = 5;

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
}