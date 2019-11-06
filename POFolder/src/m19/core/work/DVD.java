package m19.core.work;

import m19.core.work.Work;

public class DVD extends Work {
    private String _director;
    private String _igac;

    public DVD(int iD, int price, String title, int numberOfCopies, String categoryValue, String director, String igac) {
        super(iD, price, title, numberOfCopies, categoryValue);
        _director = director;
        _igac = igac;
    }

    @Override
    protected String getWorkType() {
        return "DVD";
    }

    @Override
    public String getDescription() {
        return useCommonDescription(getWorkType())+ " - " + _director+ " - " + _igac;
    }
}