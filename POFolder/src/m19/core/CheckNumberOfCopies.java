package m19.core;

public class CheckNumberOfCopies extends Rule {

    CheckNumberOfCopies(int iD) {
        super(iD);
    }

    protected boolean checkRule(User user, Work work) {
        return work.getNumberOfCopiesAvailable() == 0 ? false : true;
    }
    
}