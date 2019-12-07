package m19.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import m19.core.exception.EmptyUserNameOrEmailException;
import m19.core.exception.UserIsNotSuspendedException;

public class User implements Observer, Serializable {
    private static final long serialVersionUID = -5342790251379291184L;
    private int _iD;
    private boolean _isActive = true;
    private String _name;
    private String _email;
    private int _fine;
    private Behavior _behavior = NormalBehavior._normalBehavior;
    private int _lateStreak;
    private int _onTimeStreak;
    private List<Request> _requests = new ArrayList<>();
    private List<Notification> _notifications = new ArrayList<>();

    User(int iD, String name, String email) throws EmptyUserNameOrEmailException {
        if (name.isEmpty() || email.isEmpty())
        throw new EmptyUserNameOrEmailException("User name " + name + " or email " + email + " are empty strings.");
        _iD = iD;
        _name = name;
        _email = email;
    }
    
    int getLateStreak() {
        return _lateStreak;
    }
    
    int getOnTimeStreak() {
        return _onTimeStreak;
    }
    
    void setOnTimeStreak(int onTimeStreak) {
        _onTimeStreak = onTimeStreak;
    }
    
    void setLateStreak(int lateStreak) {
        _lateStreak = lateStreak;
    }
    
    void changeBehavior(Behavior behavior) {
        _behavior = behavior;
    }
    
    int getFine() {
        return _fine;
    }
    
    void fineUser(int fine) {
        _fine += fine;
    }
    
    Behavior getBehavior() {
        return _behavior;
    }

    boolean checkStatus() {
        return _isActive;
    }

    public void notifyObserver(Object o) {
        _notifications.add((NotificationDelivery) o);
    }

    List<Notification> getUserNotifications() {
        List<Notification> nots = new ArrayList<>(_notifications);

        _notifications.clear();
        return Collections.unmodifiableList(nots);
    }

    void payFine() throws UserIsNotSuspendedException {
        if (_isActive)
            throw new UserIsNotSuspendedException(_iD);
        _fine = 0;
    }

    void updateUser(int currentDate) {
        for (Request r: _requests) {
            if (r.getDeadline() < currentDate) {
                _isActive = false;
                return;
            }
        }
        if (_fine == 0)
            _isActive = true;
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

    public static Comparator<User> getComparatorByName() {
        return COMPARE_BY_NAME;
    }
    
    private static final Comparator<User> COMPARE_BY_NAME = new Comparator<User>() {
        @Override
        public int compare(User a, User b) {
          return a._name.compareTo(b._name) == 0 ? a.hashCode() - b.hashCode() : a._name.compareTo(b._name);
        }
    };
    
}
