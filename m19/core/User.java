package m19.core;

public class User {
    private int _iD;
    private boolean _isActive = true;
    private String _name;
    private String _email;
    private int _fine;
    private int _countFouls;
    //private Behaviour _behaviour;
    //private TypeOfCollection<Request> _request = new TypeOfCollection<>();

    public User(int iD, String name, String email) {
        _iD = iD;
        _name = name;
        _email = email;
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

    public void makeRequest() {  // falta request a adicionar
        //add._request()
    }

    public void returnWork() {  // falta request a remover
        //remove._request()
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