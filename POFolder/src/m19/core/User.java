package m19.core;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import m19.core.Request;
import m19.core.Behaviour;

public class User {
    private int _iD;
    private boolean _isActive = true;
    private String _name;
    private String _email;
    private int _fine;
    private int _countFouls;
    private Behaviour _behaviour;
    private Set<Request> _requests = new TreeSet<>(new RequestComparator());

    public User(int iD, String name, String email) {
        _iD = iD;
        _name = name;
        _email = email;
        _behaviour = Behaviour.NORMAL;
    }

    protected String getName() {
        return _name;
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

    protected void changeBehaviour() {

    }

    @Override
    public int hashCode() {
        return _iD;
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == getClass() && _iD == ((User) other)._iD;
    }

    public String getDescription() {
        String res = "" + _iD + " - " + _name + " - " + _email + " - " + _behaviour.toString() + " - ";
        if (_isActive) 
            return res += "ACTIVO";

        return res += "SUSPENSO" + " - EUR " + _fine; 
    }
}

class UserComparator implements Comparator<User> {
    @Override
    public int compare(User a, User b) {
        if (a.getName().compareTo(b.getName()) == 0)
            return a.hashCode() - b.hashCode();
        return a.getName().compareTo(b.getName());
    }
}