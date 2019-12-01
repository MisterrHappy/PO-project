package m19.core;

public abstract class Behavior {

    protected abstract String getBehavior();
    protected abstract int getMaxRequests();
    protected abstract boolean checkWorkPrice(Work work);
}