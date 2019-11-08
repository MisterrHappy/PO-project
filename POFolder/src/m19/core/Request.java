package m19.core;

import m19.core.User;
import m19.core.work.Work;

import java.io.Serializable;
import java.util.Comparator;

public class Request implements Serializable{
    private static final long serialVersionUID = -390459476484847035L;
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

    protected int getDeadline() {
        return _deadline;
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
        return other.getClass() == getClass() && _user.equals(((Request) other)._user) && _work.equals(((Request) other)._work);
    }
}

class RequestComparator implements Comparator<Request>, Serializable {
    private static final long serialVersionUID = 5529668764911233646L;

    @Override
    public int compare(Request a, Request b) {
        return a.getDeadline() - b.getDeadline();
    }
}