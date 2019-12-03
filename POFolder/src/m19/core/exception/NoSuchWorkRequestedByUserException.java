package m19.core.exception;

public class NoSuchWorkRequestedByUserException extends Exception {

    private static final long serialVersionUID = 53493582469L;

    private int _workId;

    public NoSuchWorkRequestedByUserException(int workId) {
        _workId = workId;
    }

    public NoSuchWorkRequestedByUserException(int workId, Exception cause) {
        super("Work not requested by user", cause);
        _workId = workId;
    }

    public int getWorkId() {
        return _workId;
    }
    
}