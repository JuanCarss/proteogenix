package es.ulpgc;

import java.util.Map;

public class RNAPolymerase {
    public MessengerRNA transcript(DNA dna) {
        Map<DNA.Nucleotides, Nucleotides> correspondence = Map.of(
                DNA.Nucleotides.Adenine, Nucleotides.Uracil,
                DNA.Nucleotides.Thymine, Nucleotides.Adenine,
                DNA.Nucleotides.Guanine, Nucleotides.Cytosine,
                DNA.Nucleotides.Cytosine, Nucleotides.Guanine);
        return new MessengerRNA(dna.nucleotides().map(correspondence::get));
    }

    public enum Nucleotides {Adenine, Uracil, Guanine, Cytosine}
}
