package m19.core.work;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import m19.core.Category;
import m19.core.Request;

public abstract class Work implements Serializable {
    private static final long serialVersionUID = -4003574528951482147L;
    private int _iD;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private int _numberOfCopiesAvailable;
    private Category _category;
    private Set<Request> _requests = new HashSet<>();

    public Work(int iD, int price, String title, int numberOfCopies, Category category) {
        _iD = iD;
        _price = price;
        _title = title;
        _numberOfCopies = numberOfCopies;
        _numberOfCopiesAvailable = numberOfCopies;
        _category = category;
    }

    public int hashCode() {
        return _iD;
    }

    protected void increaseCopiesAvailable() {
        _numberOfCopiesAvailable++;
    }

    protected void decreaseCopiesAvailable() {
        _numberOfCopiesAvailable--;
    }

    public void addRequest(Request request) {
        _requests.add(request);
    }

    public void removeRequest(Request request) {
        _requests.remove(request);
    }

    protected abstract String getWorkType();
    
    protected String useCommonDescription(String workType) {
        return "" + _iD + " - " + _numberOfCopiesAvailable + " de " + _numberOfCopies + " - " + workType + " - " 
        + _title + " - " + _price + " - " + _category.toString();
    }
    
    public abstract String getDescription();
    
    @Override
    public boolean equals(Object other) {
        return other.getClass() == getClass() && _iD == ((Work) other)._iD;
    }
}