//package m19.core

public abstract class ActiveStatus {
    protected abstract String getStatusDescription();

    protected final String buildUserDescription() {
        return "" + _iD + " - " + _name + " - " + _email + " - " + _behavior.toString() + " - " + getStatusDescription();
    }

}