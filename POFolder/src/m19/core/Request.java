package m19.core;

import java.util.Comparator;

public class Request {
    private static final int PRIME_NUMBER = 233;
    private User _user;
    private Work _work;
    private int _deadline;
    
    Request(User user, Work work, int deadline) {
        _user = user;
        _work = work;
        _deadline = deadline;
    }

    static final int getPrimeNumber() {
        return PRIME_NUMBER;
    }

    @Override
    public int hashCode() {
        return _user.hashCode() * PRIME_NUMBER + _work.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Request && ((Request) other)._work == _work && ((Request) other)._user == _user;
    }

    public static Comparator<Request> getComparatorByDeadline() {
        return COMPARE_BY_DEADLINE;
    }

    private static final Comparator<Request> COMPARE_BY_DEADLINE = new Comparator<Request>() {
        @Override
        public int compare(Request a, Request b) {
            return a._deadline == b._deadline ? 1 : a._deadline - b._deadline;
        }
    };
}