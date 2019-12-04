package m19.core;

import java.io.Serializable;

import m19.core.exception.RuleBrokenException;

public abstract class Rule implements Serializable{
    private static final long serialVersionUID = -9085214926873894774L;
    private int _iD;
    
    protected Rule(int iD) {
        _iD = iD;
    }

    protected abstract void checkRule(User user, Work work) throws RuleBrokenException;

    protected int getId() {
        return _iD;
    }
}