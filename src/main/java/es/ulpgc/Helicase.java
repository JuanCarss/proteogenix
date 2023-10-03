package es.ulpgc;

import java.util.List;

public class Helicase {
    public List<Strand> splitIntoStrands(DNA dna) {
        return List.of(dna.codingStrand(), dna.codingStrand());
    }
}
