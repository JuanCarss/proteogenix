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
    public MessengerRNA transcribe(Strand strand, int promoter, int termination) {
        if ((termination - promoter) % 3 != 0) throw new RuntimeException("Gene length is not divisible by three");
        return new MessengerRNA(strand.bases().stream()
                .skip(promoter)
                .map(RNAPolymerase::complement)
                .limit(termination)
                .collect(Collectors.toList()));
    }

    private static NitrogenousBase complement(NitrogenousBase base) {
        return base == ADENINE ? URACIL : base.complement();
    }
}
