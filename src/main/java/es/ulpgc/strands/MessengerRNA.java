package es.ulpgc.strands;

import es.ulpgc.NitrogenousBase;
import es.ulpgc.Strand;

import java.util.List;

import static es.ulpgc.NitrogenousBase.THYMINE;

public record MessengerRNA(List<NitrogenousBase> bases) implements Strand {
    public MessengerRNA {
        if (containsThymine(bases)) throw new RuntimeException("ARN Strand can't contain thymine.");
    }

    private boolean containsThymine(List<NitrogenousBase> bases) {
        return bases.stream().anyMatch(nitrogenousBase -> nitrogenousBase.equals(THYMINE));
    }
}
