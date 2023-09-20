package es.ulpgc;

import java.util.stream.Stream;

public class MessengerRNA {
    private final Stream<RNAPolymerase.Nucleotides> nucleotides;

    public MessengerRNA(Stream<RNAPolymerase.Nucleotides> nucleotides) {
        this.nucleotides = nucleotides;
    }

    public Stream<RNAPolymerase.Nucleotides> nucleotides() {
        return nucleotides;
    }
}
