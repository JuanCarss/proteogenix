package es.ulpgc.strands;

import es.ulpgc.NitrogenousBase;
import es.ulpgc.Strand;

import java.util.List;
import static es.ulpgc.NitrogenousBase.URACIL;

public record ComplimentaryStrand(List<NitrogenousBase> bases) implements Strand {
    public ComplimentaryStrand {
        if (containsUracil(bases)) throw new RuntimeException("DNA Strand can't contain uracil.");
    }

    private static boolean containsUracil(List<NitrogenousBase> bases) {
        return bases.stream().anyMatch(nitrogenousBase -> nitrogenousBase.equals(URACIL));
    }
}