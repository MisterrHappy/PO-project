package m19.core;

import java.util.Map;

public class CheckRequestTwice extends Rule {

    protected CheckRequestTwice(int iD) {
        super(iD);
    }

    protected boolean checkRule(User user, Work work) {
        Map<Integer, Request> requests = user.getUserRequests();
        int key = user.hashCode() * Request.getPrimeNumber() + work.hashCode();
        return requests.get(key) == null ? false : true;
    }
    
}