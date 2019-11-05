
package m19.core;

import java.util.Set;
import java.util.HashSet;
import m19.core.Request;

public class User {
    private int _iD;
    private boolean _isActive = true;
    private String _name;
    private String _email;
    private int _fine;
    private int _countFouls;
    //private Behaviour _behaviour;
    private Set<Request> _requests = new HashSet<>();

    public User(int iD, String name, String email) {
        _iD = iD;
        _name = name;
        _email = email;
    }

    protected boolean isActive() {
        return _isActive;
    }

    protected void changeStatus() {
        _isActive = !_isActive;
    }

    protected void increaseFouls() {
        _countFouls++;
    }

    protected void decreaseFouls() {
        _countFouls--;
    }

    public void makeRequest(Request request) {
        _requests.add(request);
    }

    public void returnWork(Request request) { 
        _requests.remove(request);
    }

    public void changeBehaviour() {

    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == getClass() && _iD == ((User) other)._iD;
    }

    public String getDescription() {
        String res = "" + _iD + " - " + _name + " - " + _email + " - ";
        if (_isActive) 
            return res += "ACTIVO";

        return res += "SUSPENSO" + " - EUR " + _fine; 
    }
}