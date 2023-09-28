package es.ulpgc;

import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.Sense;

import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DNA dna = (DNA) o;
        return Objects.equals(strand, dna.strand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strand);
    }
}
