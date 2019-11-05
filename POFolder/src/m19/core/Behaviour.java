package m19.core;

public enum Behaviour {
    NORMAL("NORMAL"),
    CUMPRIDOR("CUMPRIDOR"),
    FALTOSO("FALTOSO");

    private String _behaviour;

    private Behaviour(String behaviour) {
        _behaviour = behaviour;
    }

    @Override
    public String toString() {
        return _behaviour;
    }
}