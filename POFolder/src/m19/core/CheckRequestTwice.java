package m19.core;

import java.util.Map;

import m19.core.exception.RuleBrokenException;

public class CheckRequestTwice extends Rule {

    CheckRequestTwice(int iD) {
        super(iD);
    }

    protected void checkRule(User user, Work work) throws RuleBrokenException {
        Map<Integer, Request> requests = user.getUserRequests();
        int key = user.hashCode() * Request.getPrimeNumber() + work.hashCode();
        if (requests.get(key) != null)
            throw new RuleBrokenException(getId());
    }
    
}