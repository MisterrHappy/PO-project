package m19.core;

import m19.core.Work;

import m19.core.Category;

public class Dvd extends Work {
    private static final long serialVersionUID = -9216843773806146841L;
    private static final String WORK_TYPE = "DVD";
    private String _director;
    private String _igac;

    Dvd(int iD, int price, String title, int numberOfCopies, Category category, String director, String igac) {
        super(iD, price, title, numberOfCopies, category);
        _director = director;
        _igac = igac;
    }

    @Override
    protected String getWorkType() {
        return WORK_TYPE;
    }

    @Override
    protected String addDescription() {
        return " - " + _director + " - " + _igac;
    }

    @Override
    protected Work checkSpecificField(String term) {
       String lowerCaseDirector = _director.toLowerCase();
       if (lowerCaseDirector.contains(term))
		return this;
       return null; 
    }
}