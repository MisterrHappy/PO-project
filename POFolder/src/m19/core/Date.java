package m19.core;

import java.io.Serializable;

public class Date implements Serializable {
    private int _currentDate;

    protected int getCurrentDate() {
        return _currentDate;
    }

    protected void advanceDay(int nDays) {
        if (nDays > 0)
            _currentDate += nDays;
    }
}