package m19.core.work;

import m19.core.work.Work;

public class Book extends Work {
    private String _author;
    private String _isbn;

    public Book(int iD, int price, String title, int numberOfCopies, Category category, String author, String isbn) {
        super(iD, price, title, numberOfCopies, category);
        _author = author;
        _isbn = isbn;
    }

    @Override
    String getWorkType() {
        return "Livro";
    }

    @Override
    public String getDescription() {
        return useCommonDescription(getWorkType())+ " - " + _author + " - " + _isbn;
    }
}