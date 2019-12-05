package m19.core;

import java.io.Serializable;

public interface Notification extends Serializable {
    long serialVersionUID = -6289670582830625922L;

    String getMessage();
}