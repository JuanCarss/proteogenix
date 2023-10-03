package es.ulpgc.polymerases;

import es.ulpgc.NitrogenousBase;
import es.ulpgc.Polymerase;
import es.ulpgc.Strand;
import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.Sense;

import java.util.stream.Collectors;

public class DNAPolymerase implements Polymerase {
    private static final int FRAGMENT_SIZE = 200;

    @Override
    public Strand transcribe(Strand strand) {
        if (isSenseStrand(strand)) return transcribeFromOkazakiFragments(strand);
        return new Antisense(strand.bases().stream()
                .map(NitrogenousBase::complement)
                .collect(Collectors.toList()));
    }

    private boolean isSenseStrand(Strand strand) {
        return strand instanceof Sense;
    }

    private Sense transcribeFromOkazakiFragments(Strand strand) {
        return null;
    }
}
