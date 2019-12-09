package m19.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Subject implements Serializable {

    private static final long serialVersionUID = -8561384535256156842L;
    private List<Observer> _observers = new ArrayList<>();

    void addObserver(Observer observer) {
        _observers.add(observer);
    }

    void removeObserver(Observer observer) {
        _observers.remove(observer);
    }

    protected void clearObservers() {
        _observers.clear();
    }

    protected abstract void notifyObserversAdvancedOptions();

    protected abstract void setMessage();

    final void notifyObservers(Subject subject) {
        for (Observer observer: _observers) 
            observer.notifyObserver(subject);
            
        notifyObserversAdvancedOptions();
    }
}