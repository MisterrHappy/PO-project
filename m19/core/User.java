package m19.core;

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
    private HashSet<Request> _requests = new HashSet<>();

    public User(int iD, String name, String email) {
        _iD = iD;
        _name = name;
        _email = email;
    }

    public int getID() {
        return _iD;
    }

    boolean isActive() {
        return _isActive;
    }

    public void changeStatus() {
        _isActive = !_isActive;
    }

    public void increaseFouls() {
        _countFouls++;
    }

    public void decreaseFouls() {
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

    public String getDescription() {
        String res = "" + _iD + " - " + _name + " - " + _email + " - ";
        if (_isActive) 
            return res += "ACTIVO";

        return res += "SUSPENSO" + " - EUR " + _fine; 
    }
}