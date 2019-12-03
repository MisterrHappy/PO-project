package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckRequestTwice extends Rule {

    CheckRequestTwice(int iD) {
        super(iD);
    }
    
    private static final Rule CHECK_REQUEST_TWICE = new Rule(1){
        @Override
        protected void checkRule(User user, Work work) throws RuleBrokenException {
            if (user.checkUserRequest(work.hashCode()))
                throw new RuleBrokenException(getId());
            
        }
    };

    public static Rule getCheckRequestTwice() {
        return CHECK_REQUEST_TWICE;
    }
}