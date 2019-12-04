package m19.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Work implements Serializable {
    private static final long serialVersionUID = -4003574528951482147L;
    private int _iD;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private int _numberOfCopiesAvailable;
    private Category _category;
    private List<Request> _requests = new ArrayList<>();
    private List<Observer> _observers = new ArrayList<>();

    protected Work(int iD, int price, String title, int numberOfCopies, Category category) {
        _iD = iD;
        _price = price;
        _title = title;
        _numberOfCopies = numberOfCopies;
        _numberOfCopiesAvailable = numberOfCopies;
        _category = category;
    }

    void addObserver(Observer o) {
        _observers.add(o);
    }

    void removeObserver(Observer o) {
        _observers.remove(o);
    }

    Category getCategory() {
        return _category;
    }

    int getPrice() {
        return _price;
    }

    int getNumberOfCopiesAvailable() {
        return _numberOfCopiesAvailable;
    }

    void addRequest(Request r) {
        _requests.add(r);
        _numberOfCopiesAvailable--;
    }

    void removeRequest(Request r) {
        _requests.remove(r);

        for (Observer o: _observers)
            o.notifyObserver(this);

        _observers.clear(); // perguntar ao stor
        _numberOfCopiesAvailable++;
    }

    @Override
    public int hashCode() {
        return _iD;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Work && _iD == ((Work) other)._iD;
    }

    protected abstract String getWorkType();

    protected abstract String addDescription();

    protected abstract Work checkSpecificField(String term);

    protected final Work getWorkByTerm(String term) {
        String lowerCaseTitle = _title.toLowerCase();
        if (lowerCaseTitle.contains(term))
            return this;
        return checkSpecificField(term);
    }
    
    public final String getDescription() {
        return "" + _iD + " - " + _numberOfCopiesAvailable + " de " + _numberOfCopies + " - " + getWorkType() + " - " 
                + _title + " - " + _price + " - " + _category.toString() + addDescription();
    }

    public static Comparator<Work> getComparatorById() {
        return COMPARE_BY_ID;
    }
    
    private static final Comparator<Work> COMPARE_BY_ID = new Comparator<Work>() {
        public int compare(Work w1, Work w2) {
            return ((int) w1._iD) - ((int) w2._iD);
        }
    };
}