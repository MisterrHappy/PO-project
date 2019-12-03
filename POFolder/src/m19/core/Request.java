package m19.core;

public class Request {
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