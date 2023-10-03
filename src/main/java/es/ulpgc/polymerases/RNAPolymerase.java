package es.ulpgc.polymerases;

import es.ulpgc.NitrogenousBase;
import es.ulpgc.Polymerase;
import es.ulpgc.Strand;
import es.ulpgc.strands.MessengerRNA;

import java.util.stream.Collectors;

import static es.ulpgc.NitrogenousBase.ADENINE;
import static es.ulpgc.NitrogenousBase.URACIL;

public class RNAPolymerase implements Polymerase {
    @Override
    public MessengerRNA transcribe(Strand strand) {
        // TODO add gene detection
        return new MessengerRNA(strand.bases().stream()
                .map(RNAPolymerase::complement)
                .collect(Collectors.toList()));
    }

    private static NitrogenousBase complement(NitrogenousBase base) {
        return base == ADENINE ? URACIL : base.complement();
    }
}
