package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckNumberOfRequests extends Rule {
    
    CheckNumberOfRequests(int iD) {
        super(iD);
    }

    @Override
    protected void checkRule(User user, Work work) throws RuleBrokenException {
        if (user.getUserRequests().size() >= user.getBehavior().getMaxRequests())
            throw new RuleBrokenException(getId());
    }
}