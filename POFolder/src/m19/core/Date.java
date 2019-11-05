package m19.core;

public class Date {
    private int _currentDate;

    protected int getCurrentDate() {
        return _currentDate;
    }

    protected void advanceDay(int nDays) {
        _currentDate += nDays;
    }
}