package es.ulpgc.polymerases;

import es.ulpgc.NitrogenousBase;
import es.ulpgc.Polymerase;
import es.ulpgc.Strand;
import es.ulpgc.strands.ComplimentaryStrand;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DNAPolymerase implements Polymerase {

    private static double fragmentSizeProportion = 0.2;

    @Override
    public Strand transcribe(Strand strand, int promoter, int termination) {
        return new ComplimentaryStrand(strand.bases().stream()
                .skip(promoter)
                .map(NitrogenousBase::complement)
                .limit(termination)
                .collect(Collectors.toList()));
    }

    public ComplimentaryStrand okazakiFragmentReplication(Strand strand){
        List<NitrogenousBase> complimentaryStrand = new ArrayList<>();
        for (int startIndex = 0; startIndex < getStrandSize(strand); startIndex += fragmentLength(strand))
            complimentaryStrand.addAll(transcribe(strand, startIndex, fragmentLength(strand)).bases());
        return new ComplimentaryStrand(complimentaryStrand);
    }

    private static int fragmentLength(Strand strand) {
        return (int) Math.ceil(getStrandSize(strand) * fragmentSizeProportion);
    }

    private static int getStrandSize(Strand TemplateStrand) {
        return TemplateStrand.bases().size();
    }
}
