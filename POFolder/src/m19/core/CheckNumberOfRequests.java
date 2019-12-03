package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckNumberOfRequests extends Rule {
    
    CheckNumberOfRequests(int iD) {
        super(iD);
    }

    private static final CheckNumberOfRequests CHECK_NUMBER_OF_REQUESTS = new CheckNumberOfRequests(4){
        @Override
        protected void checkRule(User user, Work work) throws RuleBrokenException {
            if (user.getUserRequestsNumber() >= user.getBehavior().getMaxRequests())
                throw new RuleBrokenException(getId());
        }
    };

    public static Rule getCheckNumberOfRequests() {
        return CHECK_NUMBER_OF_REQUESTS;
    }
}