//package m19.core;

public class UserIsInactive extends ActiveStatus {
    
    protected String getStatusDescription() {
        return "SUSPENSO" + " - EUR " + _fine;
    }
}