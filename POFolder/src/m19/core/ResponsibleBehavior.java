package m19.core;

public class ResponsibleBehavior extends Behavior {
    public static final ResponsibleBehavior _responsibleBehavior = new ResponsibleBehavior();
    private static final String RESPONSIBLE = "CUMPRIDOR";

    protected String getBehavior() {
        return RESPONSIBLE;
    }
}