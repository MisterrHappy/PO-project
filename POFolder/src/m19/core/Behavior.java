package m19.core;

interface Behavior {
    int RESPONSIBLE_STREAK = 5;
    int FAULTY_STREAK = 3;

    String getBehavior();
    int getMaxRequests();
    boolean checkWorkPrice(Work work);
    int getRequestTerm(int workCopiesAvailable);
    void updateBehavior(User user);
}