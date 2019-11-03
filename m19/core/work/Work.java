package m19.core.work;

public abstract class Work {
    private int _iD;
    private int _price;
    private int _numberOfCopies;
    private String _title;
    private int _numberOfCopiesAvailable;
    private Category _category;
    //private TypeOfCollection<Request> _request = new TypeOfCollection<>();

    public Work(int iD, int price, String title, int numberOfCopies, Category category) {
        _iD = iD;
        _price = price;
        _title = title;
        _numberOfCopies = numberOfCopies;
        _numberOfCopiesAvailable = numberOfCopies;
        _category = category;
    }

    public void increaseCopiesAvailable() {
        _numberOfCopiesAvailable++;
    }

    public void decreaseCopiesAvailable() {
        _numberOfCopiesAvailable--;
    }

    abstract String getWorkType();

    String useCommonDescription(String workType) {
        return "" + _iD + " - " + _numberOfCopiesAvailable + " de " + _numberOfCopies + " - " + workType + " - " + _price + " - " + _category;
    }

    public abstract String getDescription();
}