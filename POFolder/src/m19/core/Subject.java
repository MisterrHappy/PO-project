package m19.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> _observers = new ArrayList<>();

    protected void addObserver(Observer observer) {}
    protected void removeObserver(Observer observer) {}
    protected void notifyObservers() {}
}