package es.ulpgc.polymerases;

import es.ulpgc.DNA;
import es.ulpgc.NitrogenousBase;
import es.ulpgc.Polymerase;
import es.ulpgc.Strand;
import es.ulpgc.strands.ComplimentaryStrand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DNAPolymerase implements Polymerase {
    @Override
    public Strand transcribe(Strand strand, int promoter, int termination) {
        return new ComplimentaryStrand(strand.bases().stream()
                .skip(promoter)
                .map(NitrogenousBase::complement)
                .limit(termination)
                .collect(Collectors.toList()));
    }

    public void okazakiSplit(DNA dna){
        int size = dna.templateStrand().bases().size();
        List<Strand> templateStrand = new ArrayList<>();
        List<Strand> codingStrand = new ArrayList<>();
        int longitudFragmento = (int) Math.ceil(size * 0.2);
        for (int i = 0; i < size; i += longitudFragmento) {
            int fin = Math.min(i + longitudFragmento, size);
//            transcribe(dna.templateStrand(), i, fin);
            templateStrand.add(transcribe(dna.codingStrand(), i, longitudFragmento));
        }
        System.out.println(templateStrand);
    }
}
