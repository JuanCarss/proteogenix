package es.ulpgc;

public enum NitrogenousBase {
    ADENINE, THYMINE, CYTOSINE, GUANINE, URACIL;

    static {
        ADENINE.complement = THYMINE;
        THYMINE.complement = ADENINE;
        CYTOSINE.complement = GUANINE;
        GUANINE.complement = CYTOSINE;
        URACIL.complement = ADENINE;
    }

    private NitrogenousBase complement;

    public NitrogenousBase complement() {
        return complement;
    }
}
