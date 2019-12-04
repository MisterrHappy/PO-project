package m19.core;

import java.io.Serializable;

public abstract class Notification implements Serializable {
    private static final long serialVersionUID = -6289670582830625922L;

    public abstract String getMessage();
}