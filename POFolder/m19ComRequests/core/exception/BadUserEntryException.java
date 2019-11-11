package m19.core.exception;

public class BadUserEntryException extends Exception {

    private static final long serialVersionUID = 54338595437L;

    private String _entrySpecification;

    public BadUserEntryException(String entrySpecification) {
        _entrySpecification = entrySpecification;
    }

    public BadUserEntryException(String entrySpecification, Exception cause) {
        super(cause);
        _entrySpecification = entrySpecification;
    }

    public String getEntrySpecification() {
        return _entrySpecification;
    }
    
}
