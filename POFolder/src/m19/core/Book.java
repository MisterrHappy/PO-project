package m19.core;

import m19.core.Work;
import m19.core.Category;

public class Book extends Work {
    private static final long serialVersionUID = -7947582672361225393L;
    private String _author;
    private String _isbn;

    public Book(int iD, int price, String title, int numberOfCopies, Category category, String author, String isbn) {
        super(iD, price, title, numberOfCopies, category);
        _author = author;
        _isbn = isbn;
    }

    @Override
    protected String getWorkType() {
        return "Livro";
    }

    @Override
    public String getDescription() {
        return useCommonDescription(getWorkType())+ " - " + _author + " - " + _isbn;
    }
}