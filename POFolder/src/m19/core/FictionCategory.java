package m19.core;

public class FictionCategory implements Category {
    private static final String FICTION = "Ficção";

    @Override
    public String getCategory() {
        return FICTION;
    }

    @Override
    public boolean isReference() {
        return false;
    }
}