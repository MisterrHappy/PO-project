package m19.core;

import java.io.Serializable;

public class Date implements Serializable {
    private static final long serialVersionUID = 383907972717283888L;
    private int _currentDate;

    int getCurrentDate() {
        return _currentDate;
    }

    void advanceDay(int nDays) {
        if (nDays > 0)
            _currentDate += nDays;
    }
    
}