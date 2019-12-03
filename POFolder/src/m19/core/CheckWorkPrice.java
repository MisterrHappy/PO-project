package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckWorkPrice extends Rule {
    
    CheckWorkPrice(int iD) {
        super(iD);
    }

    private static final CheckWorkPrice CHECK_WORK_PRICE = new CheckWorkPrice(6) {
        @Override
        protected void checkRule(User user, Work work) throws RuleBrokenException {
            if (!user.getBehavior().checkWorkPrice(work))
                throw new RuleBrokenException(getId());
        }
    };

    public static Rule getCheckWorkPrice() {
        return CHECK_WORK_PRICE;
    }
}