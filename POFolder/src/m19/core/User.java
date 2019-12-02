package m19.core;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import m19.core.exception.EmptyUserNameOrEmailException;
import m19.core.exception.UserIsNotSuspendedException;

public class User implements Serializable {
    private static final long serialVersionUID = -5342790251379291184L;
    private int _iD;
    private boolean _isActive = true;
    private String _name;
    private String _email;
    private int _fine;
    private Behavior _behavior = new NormalBehavior();
    private Map<Integer, Request> _requests = new HashMap<>();

    User(int iD, String name, String email) throws EmptyUserNameOrEmailException {
        if (name.isEmpty() || email.isEmpty())
            throw new EmptyUserNameOrEmailException("User name " + name + " or email " + email + " are empty strings.");
        _iD = iD;
        _name = name;
        _email = email;
    }

    void payFine() throws UserIsNotSuspendedException {
        if (_isActive)
            throw new UserIsNotSuspendedException(_iD);
        _fine = 0;
        // _behavior = new NormalBehavior()  ???? falta perguntar ao stor cenas
    }

    Behavior getBehavior() {
        return _behavior;
    }

    boolean checkStatus() {
        return _isActive;
    }

    void addRequest(int key, Request r) {
        _requests.put(key, r);
    }

    void removeRequest(int key) {
        _requests.remove(key);
    }

    Map<Integer, Request> getUserRequests() {
        return Collections.unmodifiableMap(_requests);
    }

    public String getName() {
        return _name;
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
        String res = "" + _iD + " - " + _name + " - " + _email + " - " + _behavior.getBehavior() + " - ";
        return _isActive ? res + "ACTIVO" : res + "SUSPENSO - EUR " + _fine;
    }
    
}
