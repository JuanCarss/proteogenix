package es.ulpgc;

public interface Polymerase {
    Strand transcribe(Strand strand, int promoter, int termination);
}
