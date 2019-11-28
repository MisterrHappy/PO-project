package m19.core;

public class UserIsActive extends ActiveStatus {
    public static final ActiveStatus ACTIVE_STATUS = new UserIsActive();
    
    public static ActiveStatus getStatus() {
        return ACTIVE_STATUS;
    }

    protected String getStatusDescription(User user) {
        return "ACTIVO";
    }
}