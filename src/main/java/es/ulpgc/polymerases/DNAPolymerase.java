package es.ulpgc.polymerases;

import es.ulpgc.DNA;
import es.ulpgc.NitrogenousBase;
import es.ulpgc.Polymerase;
import es.ulpgc.Strand;
import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.Sense;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DNAPolymerase implements Polymerase {
    @Override
    public Strand transcribe(Strand strand) {
        if (isSenseStrand(strand)) return transcribeFromOkazakiFragments(strand);
        return new Sense(strand.bases().stream()
                .map(NitrogenousBase::complement)
                .collect(Collectors.toList()));
    }

    private boolean isSenseStrand(Strand strand) {
        return strand instanceof Sense;
    }

    private Antisense transcribeFromOkazakiFragments(Strand strand) {
        return new Antisense(strand.bases().stream()
                .map(NitrogenousBase::complement)
                .collect(Collectors.toList()));
    }
    public List<NitrogenousBase> duplicateDNA(DNA dna){
        List<NitrogenousBase> duplicatedDNA = new ArrayList<>(dna.templateStrand().bases());
        duplicatedDNA.addAll(dna.templateStrand().bases());
        return duplicatedDNA;
    }
}
