package m19.core;

public class CheckUserIsSuspended extends Rule {
    
    CheckUserIsSuspended(int iD) {
        super(iD);
    }

    protected boolean checkRule(User user, Work work) {
        return user.checkStatus();
    }
}