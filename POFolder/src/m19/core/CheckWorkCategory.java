package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckWorkCategory extends Rule {
    
    CheckWorkCategory(int iD) {
        super(iD);
    }

    @Override
    protected void checkRule(User user, Work work) throws RuleBrokenException {
        if (!work.getCategory().isReference())
            throw new RuleBrokenException(getId());
    }
}