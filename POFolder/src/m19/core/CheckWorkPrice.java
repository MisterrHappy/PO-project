package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckWorkPrice extends Rule {
    
    CheckWorkPrice(int iD) {
        super(iD);
    }

    @Override
    protected void checkRule(User user, Work work) throws RuleBrokenException {
        if (!user.getBehavior().checkWorkPrice(work))
            throw new RuleBrokenException(getId());
    }
}