package m19.core;

public enum Category {
    REFERENCE("Referência"),
    SCITECH("Técnica e Científica"),
    FICTION("Ficção");

    private String _category;

    private Category(String category) {
        _category = category;
    }

    @Override
    public String toString() {
        return _category;
    }
}