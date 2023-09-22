package es.ulpgc;

import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.Sense;

import java.util.List;
import java.util.stream.Collectors;

public class DNA {
    private final Strand strand;

    public DNA(Strand strand) {
        this.strand = strand;
    }

    public Strand templateStrand() {
        if (isTemplateStrand()) return this.strand;
        return new Antisense(getComplementaryStrand());
    }

    public Strand codingStrand() {
        if (isTemplateStrand()) return new Sense(getComplementaryStrand());
        return this.strand;
    }

    private boolean isTemplateStrand() {
        return this.strand instanceof Antisense;
    }

    private List<NitrogenousBase> getComplementaryStrand() {
        return this.strand.bases().stream().map(NitrogenousBase::complement).collect(Collectors.toList());
    }
}
