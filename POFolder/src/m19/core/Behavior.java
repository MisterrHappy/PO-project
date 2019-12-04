package m19.core;

import java.io.Serializable;

interface Behavior extends Serializable {
    long serialVersionUID = 205824520580534L;
    int RESPONSIBLE_STREAK = 5;
    int FAULTY_STREAK = 3;

    String getBehavior();
    int getMaxRequests();
    boolean checkWorkPrice(Work work);
    int getRequestTerm(int workCopiesAvailable);
    void updateBehavior(User user);
}