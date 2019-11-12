package m19.core.exception;

public class NoWorkFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    
    private int _iD;

    public NoWorkFoundException(int iD) {
        _iD = iD;
    }

    public NoWorkFoundException(int iD, Exception cause) {
        super("Work not found.", cause);
        _iD = iD;
    }

    public int getID() {
        return _iD;
    }
}