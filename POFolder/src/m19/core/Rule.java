package m19.core;

public abstract class Rule {
    private int _iD;
    
    protected Rule(int iD) {
        _iD = iD;
    }

    protected abstract boolean checkRule(User user, Work work);
}