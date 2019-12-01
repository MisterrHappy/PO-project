package m19.core;

public class CheckNumberOfRequests extends Rule {
    
    CheckNumberOfRequests(int iD) {
        super(iD);
    }

    protected boolean checkRule(User user, Work work) {
        return user.getUserRequests().size() >= user.getBehavior().getMaxRequests() ? false : true;
    }
}