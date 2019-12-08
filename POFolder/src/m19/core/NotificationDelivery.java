package m19.core;

public class NotificationDelivery extends Notification {
    private static final long serialVersionUID = 385831350987L;

    NotificationDelivery(Work work) {
        super(work);
    }

    public String getMessage() {
        return "ENTREGA: " + getWork().getDescription();
    }

    protected void notifyObserversAdvancedOptions() {
        clearObservers();
    }
}