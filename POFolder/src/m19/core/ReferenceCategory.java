package m19.core;

public class ReferenceCategory implements Category {
    private static final String REFERENCE = "Referência";

    @Override
    public String getCategory() {
        return REFERENCE;
    }

    @Override
    public boolean isReference() {
        return true;
    }
}