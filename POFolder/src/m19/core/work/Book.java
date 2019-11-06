package m19.core.work;

import m19.core.work.Work;

public class Book extends Work {
    private String _author;
    private String _isbn;

    public Book(int iD, int price, String title, int numberOfCopies, String categoryValue, String author, String isbn) {
        super(iD, price, title, numberOfCopies, categoryValue);
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