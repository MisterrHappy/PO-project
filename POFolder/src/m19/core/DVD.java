package m19.core;

import m19.core.Work;
import m19.core.Category;

public class DVD extends Work {
    private static final long serialVersionUID = -9216843773806146841L;
    private String _director;
    private String _igac;

    protected DVD(int iD, int price, String title, int numberOfCopies, Category category, String director, String igac) {
        super(iD, price, title, numberOfCopies, category);
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