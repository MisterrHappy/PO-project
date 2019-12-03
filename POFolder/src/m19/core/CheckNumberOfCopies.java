package m19.core;

import m19.core.exception.RuleBrokenException;

public class CheckNumberOfCopies extends Rule {

    CheckNumberOfCopies(int iD) {
        super(iD);
    }

    private static final Rule CHECK_NUMBER_OF_COPIES = new Rule(3){
        @Override
        protected void checkRule(User user, Work work) throws RuleBrokenException {
            if (work.getNumberOfCopiesAvailable() == 0)
                throw new RuleBrokenException(getId());
        }
    };

    public static Rule getCheckNumberOfCopies() {
        return CHECK_NUMBER_OF_COPIES;
    }
    
}