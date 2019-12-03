package m19.core;

public class ScitechCategory implements Category {
    private static final String SCITECH = "Técnica e Científica";

    @Override
    public String getCategory() {
        return SCITECH;
    }

    @Override
    public boolean isReference() {
        return false;
    }
}