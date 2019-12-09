package m19.core;

import java.io.Serializable;

public abstract class Notification extends Subject implements Serializable {
    private static final long serialVersionUID = -6289670582830625922L;

    private Work _work;
    private String _message;

    protected Notification(Work work) {
        _work = work;
    }

    public String getMessage() {
        return _message;
    }

    protected Work getWork() {
        return _work;
    }

    protected void setMessage() {
        _message = buildMessage();
    }

    protected abstract String buildMessage();
}