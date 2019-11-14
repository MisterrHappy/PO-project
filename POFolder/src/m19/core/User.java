package m19.core;

import java.io.Serializable;
import m19.core.Behavior;
import m19.core.exception.EmptyUserNameOrEmailException;

public class User implements Serializable {
    private static final long serialVersionUID = -5342790251379291184L;
    private int _iD;
    private boolean _isActive = true;
    private String _name;
    private String _email;
    private int _fine;
    private Behavior _behavior = Behavior.NORMAL;

    protected User(int iD, String name, String email) throws EmptyUserNameOrEmailException {
        if (name.isEmpty() || email.isEmpty())
            throw new EmptyUserNameOrEmailException("User name " + name + " or email " + email + " are empty strings.");
        _iD = iD;
        _name = name;
        _email = email;
    }

    public String getName() {
        return _name;
    }

    protected boolean isActive() {
        return _isActive;
    }

    protected void changeStatus() {
        _isActive = !_isActive;
    }

    @Override
    public int hashCode() {
        return _iD;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof User && _iD == ((User) other)._iD;
    }

    public String getDescription() {
        String res = "" + _iD + " - " + _name + " - " + _email + " - " + _behavior.toString() + " - ";
        if (_isActive) 
            return res += "ACTIVO";

        return res += "SUSPENSO" + " - EUR " + _fine; 
    }
    
}
