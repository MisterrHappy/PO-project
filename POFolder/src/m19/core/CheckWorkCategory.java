package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckWorkCategory extends Rule {
    
    CheckWorkCategory(int iD) {
        super(iD);
    }

    private static final CheckWorkCategory CHECK_WORK_CATEGORY = new CheckWorkCategory(5) {
        @Override
        protected void checkRule(User user, Work work) throws RuleBrokenException {
            if (!work.getCategory().isReference())
                throw new RuleBrokenException(getId());
        }
    };

    public static Rule getCheckWorkCategory() {
        return CHECK_WORK_CATEGORY;
    }
}