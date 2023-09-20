package es.ulpgc;

import java.util.Map;
import java.util.stream.Stream;

public class DNA {
    private final String templateStrand;

    public DNA(String strand) {
        this.templateStrand = strand;
    }

    public String templateStrand() {
        return templateStrand;
    }

    public Stream<Nucleotides> nucleotides() {
        Map<Character, Nucleotides> correspondence = Map.of('A', Nucleotides.Adenine,
                'T', Nucleotides.Thymine,
                'G', Nucleotides.Guanine,
                'C', Nucleotides.Cytosine);
        return templateStrand.chars()
                .mapToObj(i -> (char) i)
                .map(correspondence::get);
    }

    public enum Nucleotides {Adenine, Thymine, Guanine, Cytosine}
}
