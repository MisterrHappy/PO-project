package m19.core;

import m19.core.User;
import m19.core.work.Work;;

public class Request{
    private int _deadline;
    private User _user;
    private Work _work;

    public Request(int deadline, User user, Work work) {
        _deadline = deadline;
        _user = user;
        _work = work;
    }

    public User getUser() {
        return _user;
    }

    public Work getWork() {
        return _work;
    }

    @Override
    public int hashCode() {
        return _deadline;
    }

    @Override
    public boolean equals(Object other) {
        Request r = (Request) other;
        return _user.getID() == r.getUser().getID() && _work.getID() == r.getWork().getID();
    }

}