package m19.core;

import java.io.Serializable;

public abstract class Notification extends Subject implements Serializable {
    private static final long serialVersionUID = -6289670582830625922L;

    private Work _work;

    protected Notification(Work work) {
        _work = work;
    }

    protected Work getWork() {
        return _work;
    }

    public abstract String getMessage();
}