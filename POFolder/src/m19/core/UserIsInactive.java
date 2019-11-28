package m19.core;

public class UserIsInactive extends ActiveStatus {
    public static final ActiveStatus INACTIVE_STATUS = new UserIsInactive();
    
    public static ActiveStatus getStatus() {
        return INACTIVE_STATUS;
    }

    public String getStatusDescription(User user) {
        return "SUSPENSO" + " - EUR " + user.getFine();
    }
}