package m19.core;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

public abstract class Work implements Serializable {
    private static final long serialVersionUID = -4003574528951482147L;
    private int _iD;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private int _numberOfCopiesAvailable;
    private Category _category;
    private Map<Integer, Request> _requests = new HashMap<>();

    protected Work(int iD, int price, String title, int numberOfCopies, Category category) {
        _iD = iD;
        _price = price;
        _title = title;
        _numberOfCopies = numberOfCopies;
        _numberOfCopiesAvailable = numberOfCopies;
        _category = category;
    }

    int getPrice() {
        return _price;
    }

    int getNumberOfCopiesAvailable() {
        return _numberOfCopiesAvailable;
    }

    void addRequest(int key, Request r) {
        _requests.put(key, r);
    }

    void removeRequest(int key) {
        _requests.remove(key);
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
    
    public final String getDescription() {
        return "" + _iD + " - " + _numberOfCopiesAvailable + " de " + _numberOfCopies + " - " + getWorkType() + " - " 
                + _title + " - " + _price + " - " + _category.toString() + addDescription();
    }
}