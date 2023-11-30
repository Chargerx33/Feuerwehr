public enum Dienstgrad {
    A_DIENST(3),
    B_DIENST(2),
    C_DIENST(1),
    D_DIENST(0);

    private Integer rang;

    Dienstgrad(int severity) {
        this.rang = severity;
    }

    public boolean istHoeherAls(Dienstgrad other) {
        return this.rang > other.rang;
    }
}
