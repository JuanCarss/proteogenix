package es.ulpgc.polymerases;

import es.ulpgc.NitrogenousBase;
import es.ulpgc.Polymerase;
import es.ulpgc.Strand;
import es.ulpgc.strands.Gene;
import es.ulpgc.strands.MessengerRNA;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static es.ulpgc.NitrogenousBase.*;

public class RNAPolymerase implements Polymerase {
    private static final List<NitrogenousBase> TATA_BOX = List.of(THYMINE, ADENINE, THYMINE, ADENINE);

    @Override
    public MessengerRNA transcribe(Strand strand) {
        return new MessengerRNA(getGene(strand).bases().stream()
                .map(RNAPolymerase::complement)
                .collect(Collectors.toList()));
    }

    private Gene getGene(Strand strand) {
        return new Gene(strand.bases().subList(findPromoter(strand), strand.size()));
    }

    private int findPromoter(Strand strand) {
        if (Collections.indexOfSubList(strand.bases(), TATA_BOX) == -1) throw new RuntimeException("DNA Strand has no Gene to create Protein from.");
        return Collections.indexOfSubList(strand.bases(), TATA_BOX) + TATA_BOX.size();
    }

    private static NitrogenousBase complement(NitrogenousBase base) {
        return base == ADENINE ? URACIL : base.complement();
    }
}
