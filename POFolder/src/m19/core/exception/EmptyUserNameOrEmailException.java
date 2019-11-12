package m19.core.exception;

public class EmptyUserNameOrEmailException extends Exception {

    private static final long serialVersionUID = 54338595437L;

    private String _entrySpecification;

    public EmptyUserNameOrEmailException(String entrySpecification) {
        _entrySpecification = entrySpecification;
    }

    public EmptyUserNameOrEmailException(String entrySpecification, Exception cause) {
        super("User name or email are empty.", cause);
        _entrySpecification = entrySpecification;
    }

    public String getEntrySpecification() {
        return _entrySpecification;
    }
    
}
