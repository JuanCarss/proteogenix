package es.ulpgc;

import es.ulpgc.strands.MessengerRNA;

import java.util.stream.Collectors;

import static es.ulpgc.NitrogenousBase.*;

public class RNAPolymerase {
    public MessengerRNA transcribe(DNA dna, int promoter, int termination) {
        if ((termination - promoter) % 3 != 0) throw new RuntimeException("Gene length is not divisible by three");
        return new MessengerRNA(dna.templateStrand().bases().stream()
                .skip(promoter)
                .map(RNAPolymerase::complement)
                .limit(termination)
                .collect(Collectors.toList()));
    }

    private static NitrogenousBase complement(NitrogenousBase base) {
        return base == ADENINE ? URACIL : base.complement();
    }
}
