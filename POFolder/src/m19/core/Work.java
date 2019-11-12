package m19.core;

import java.io.Serializable;

import m19.core.Category;

public abstract class Work implements Serializable {
    private static final long serialVersionUID = -4003574528951482147L;
    private int _iD;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private int _numberOfCopiesAvailable;
    private Category _category;

    public Work(int iD, int price, String title, int numberOfCopies, Category category) {
        _iD = iD;
        _price = price;
        _title = title;
        _numberOfCopies = numberOfCopies;
        _numberOfCopiesAvailable = numberOfCopies;
        _category = category;
    }

    @Override
    public int hashCode() {
        return _iD;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Work && _iD == ((Work) other)._iD;
    }

    protected void increaseCopiesAvailable() {
        _numberOfCopiesAvailable++;
    }

    protected void decreaseCopiesAvailable() {
        _numberOfCopiesAvailable--;
    }

    protected abstract String getWorkType();
    
    protected String useCommonDescription(String workType) {
        return "" + _iD + " - " + _numberOfCopiesAvailable + " de " + _numberOfCopies + " - " + workType + " - " 
                + _title + " - " + _price + " - " + _category.toString();
    }
    
    public abstract String getDescription();
}