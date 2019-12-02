package m19.core.exception;

public class UserIsNotSuspendedException extends Exception {

    private static final long serialVersionUID = 54735935458405L;
    
    private int _iD;

    public UserIsNotSuspendedException(int iD) {
        _iD = iD;
    }

    public UserIsNotSuspendedException(int iD, Exception cause) {
        super("User is active", cause);
        _iD = iD;
    }

    public int getID() {
        return _iD;
    }
}