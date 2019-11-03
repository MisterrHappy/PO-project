package m19.core.work;

import m19.core.work.Work;

public class DVD extends Work {
    private String _director;
    private String _igac;

    public DVD(int iD, int price, String title, int numberOfCopies, Category category, String director, String igac) {
        super(iD, price, title, numberOfCopies, category);
        _director = director;
        _igac = igac;
    }

    @Override
    public String getDescription() {
        return commonDescription("DVD") + " - " + _director+ " - " + _igac;
    }
}