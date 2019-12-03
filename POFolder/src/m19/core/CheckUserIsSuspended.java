package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckUserIsSuspended extends Rule {
    
    CheckUserIsSuspended(int iD) {
        super(iD);
    }

    private static final Rule CHECK_USER_IS_SUSPENDED = new Rule(2) {
        @Override
        protected void checkRule(User user, Work work) throws RuleBrokenException {
            if (!user.checkStatus())
                throw new RuleBrokenException(getId());
        }
    };

    public static Rule getCheckUserIsSuspended() {
        return CHECK_USER_IS_SUSPENDED;
    }
}