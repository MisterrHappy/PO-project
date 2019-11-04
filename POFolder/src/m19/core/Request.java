package m19.core;

import m19.core.User;
import m19.core.work.Work;;

public class Request{
    private int _deadline;
    private User _user;
    private Work _work;
    private int _iD;

    public Request(int deadline, User user, Work work, int iD) {
        _deadline = deadline;
        _user = user;
        _work = work;
        _iD = iD;
    }

    protected User getUser() {
        return _user;
    }

    protected Work getWork() {
        return _work;
    }

    @Override
    public int hashCode() {
        return _iD;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Request))
            return false;
        Request newRequest = (Request) other;
        return _user.getID() == newRequest.getUser().getID() && _work.getID() == newRequest.getWork().getID();
    }
}