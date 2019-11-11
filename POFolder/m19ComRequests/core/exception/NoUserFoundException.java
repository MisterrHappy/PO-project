package m19.core.exception;

public class NoUserFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private int _iD;

    public NoUserFoundException(int iD) {
        _iD = iD;
    }

    public NoUserFoundException(int iD, Exception cause) {
        super(cause);
        _iD = iD;
    }

    public int getID() {
        return _iD;
    }
}