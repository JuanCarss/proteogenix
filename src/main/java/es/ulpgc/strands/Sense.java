package es.ulpgc.strands;

import es.ulpgc.NitrogenousBase;
import es.ulpgc.Strand;

import java.util.List;

import static es.ulpgc.NitrogenousBase.URACIL;

public record Sense(List<NitrogenousBase> bases) implements Strand {
    public Sense {
        if (containsUracil(bases)) throw new RuntimeException("ADN Strand can't contain uracil.");
    }

    private static boolean containsUracil(List<NitrogenousBase> bases) {
        return bases.stream().anyMatch(nitrogenousBase -> nitrogenousBase.equals(URACIL));
    }
}
