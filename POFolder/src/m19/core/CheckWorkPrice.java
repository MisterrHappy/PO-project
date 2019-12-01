package m19.core;

public class CheckWorkPrice extends Rule {
    
    CheckWorkPrice(int iD) {
        super(iD);
    }

    protected boolean checkRule(User user, Work work) {
        return user.getBehavior().checkWorkPrice(work);
    }
}