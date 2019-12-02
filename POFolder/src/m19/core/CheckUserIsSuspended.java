package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckUserIsSuspended extends Rule {
    
    CheckUserIsSuspended(int iD) {
        super(iD);
    }

    protected void checkRule(User user, Work work) throws RuleBrokenException {
        if (!user.checkStatus())
            throw new RuleBrokenException(getId());
    }
}