package m19.core;

interface ObserverSubject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}