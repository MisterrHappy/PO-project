package m19.core;

import m19.core.Work;
import m19.core.Category;

public class Book extends Work {
    private static final long serialVersionUID = -7947582672361225393L;
    private static final String WORK_TYPE = "Livro";
    private String _author;
    private String _isbn;

    Book(int iD, int price, String title, int numberOfCopies, Category category, String author, String isbn) {
        super(iD, price, title, numberOfCopies, category);
        _author = author;
        _isbn = isbn;
    }

    @Override
    protected String getWorkType() {
        return WORK_TYPE;
    }

    @Override
    protected String addDescription() {
        return " - " + _author + " - " + _isbn;
    }

    @Override
    protected Work checkSpecificField(String term) {
        String lowerCaseAuthor = _author.toLowerCase();
        if (lowerCaseAuthor.contains(term))
            return this;
        return null; 
    }
}