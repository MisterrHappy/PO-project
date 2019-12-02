package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckNumberOfCopies extends Rule {

    CheckNumberOfCopies(int iD) {
        super(iD);
    }

    protected void checkRule(User user, Work work) throws RuleBrokenException {
        if (work.getNumberOfCopiesAvailable() == 0)
            throw new RuleBrokenException(getId());
    }
    
}