package m19.core;

import java.io.Serializable;

public class Request implements Serializable {

    private static final long serialVersionUID = 924724959569305842L;
    private User _user;
    private Work _work;
    private int _deadline;
    
    Request(User user, Work work, int deadline) {
        _user = user;
        _work = work;
        _deadline = deadline;
    }

    Work getWork() {
        return _work;
    }

    int getDeadline() {
        return _deadline;
    }
}