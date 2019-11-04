package m19.core.work;

import java.util.HashSet;

import m19.core.Category;
import m19.core.Request;

public abstract class Work {
    private int _iD;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private int _numberOfCopiesAvailable;
    private Category _category;
    private HashSet<Request> _requests = new HashSet<>();

    public Work(int iD, int price, String title, int numberOfCopies, Category category) {
        _iD = iD;
        _price = price;
        _title = title;
        _numberOfCopies = numberOfCopies;
        _numberOfCopiesAvailable = numberOfCopies;
        _category = category;
    }
    
    protected int getID() {
        return _iD;
    }

    protected void increaseCopiesAvailable() {
        _numberOfCopiesAvailable++;
    }

    protected void decreaseCopiesAvailable() {
        _numberOfCopiesAvailable--;
    }

    public void addRequest(Request request) {
        _requests
    }

    abstract String getWorkType();

    protected String useCommonDescription(String workType) {
        return "" + _iD + " - " + _numberOfCopiesAvailable + " de " + _numberOfCopies + " - " + workType + " - " + _price + " - " + 
            _category.toString();
    }

    public abstract String getDescription();

}