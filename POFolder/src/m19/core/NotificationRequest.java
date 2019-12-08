package m19.core;

public class NotificationRequest extends Notification {
    private static final long serialVersionUID = 5496474578234L;

    NotificationRequest(Work work) {
        super(work);
    }

    public String getMessage() {
        return "REQUISIÇÃO: " + getWork().getDescription();
    }

    protected void notifyObserversAdvancedOptions() {}
}