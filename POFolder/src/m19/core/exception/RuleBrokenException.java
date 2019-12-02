package m19.core.exception;

public class RuleBrokenException extends Exception {
    private static final long serialVersionUID = 3857349024085L;

    private int _ruleIndex;

    public RuleBrokenException(int ruleIndex) {
        _ruleIndex = ruleIndex;
    }

    public RuleBrokenException(int ruleIndex, Exception cause) {
        super("Rule number " + ruleIndex + " broken", cause);
        _ruleIndex = ruleIndex;
    }
}