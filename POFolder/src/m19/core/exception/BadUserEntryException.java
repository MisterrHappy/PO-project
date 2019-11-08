package m19.core.exception;

public class BadUserEntryException extends Exception {

    private static final long serialVersionUID = 1L;

    private String _userEntry;

    public BadUserEntryException(String userEntry) {
        _userEntry = userEntry;
    }

    public BadUserEntryException(String userEntry, Exception cause) {
        super(cause);
        _userEntry = userEntry;
    }

    public String getUserEntry() {
        return _userEntry;
    }

}