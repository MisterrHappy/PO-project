package m19.core;

import java.io.Serializable;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import m19.core.exception.EmptyUserNameOrEmailException;
import m19.core.exception.UserIsNotSuspendedException;

public class User implements Serializable {
    private static final long serialVersionUID = -5342790251379291184L;
    private int _iD;
    private boolean _isActive = true;
    private String _name;
    private String _email;
    private int _fine;
    private Behavior _behavior = NormalBehavior._normalBehavior;
    private int _score;
    private List<Request> _requests = new ArrayList<>();

    User(int iD, String name, String email) throws EmptyUserNameOrEmailException {
        if (name.isEmpty() || email.isEmpty())
            throw new EmptyUserNameOrEmailException("User name " + name + " or email " + email + " are empty strings.");
        _iD = iD;
        _name = name;
        _email = email;
    }

    void fineUser(int fine) {
        _fine = fine;
    }

    void payFine() throws UserIsNotSuspendedException {
        if (_isActive)
            throw new UserIsNotSuspendedException(_iD);
        _fine = 0;
        // faltam cenas
        _isActive = true;
    }

    Behavior getBehavior() {
        return _behavior;
    }

    boolean checkStatus() {
        return _isActive;
    }

    void addRequest(Request r) {
        _requests.add(r);
    }

    void removeRequest(Request r) {
        _requests.remove(r);
    }

    Request checkUserRequest(int workId) {
        for (Request r: _requests) {
            if (r.getWork().hashCode() == workId)
                return r;
        }
        return null;
    }

    int getUserRequestsNumber() {
        return _requests.size();
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
