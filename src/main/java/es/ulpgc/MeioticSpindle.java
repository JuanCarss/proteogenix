package es.ulpgc;

import java.util.ArrayList;
import java.util.List;

public class MeioticSpindle {

    public List<List<NitrogenousBase>> splitDNA(DNA dna){
        List<List<NitrogenousBase>> splittedDNA = new ArrayList<>();
        splittedDNA.add(new ArrayList<>(getHalf(dna, 0, dna.templateStrand().bases().size()/2)));
        splittedDNA.add(new ArrayList<>(getHalf(dna, dna.templateStrand().bases().size()/2, dna.templateStrand().bases().size())));
        return splittedDNA;
    }

    private static List<NitrogenousBase> getHalf(DNA dna, int from, int to) {
        List<NitrogenousBase> Half = dna.templateStrand().bases().subList(from, to);
        if (dna.templateStrand().bases().size() % 2 != 0) {
            Half.remove(Half.size() - 1);
        }
        return Half;
    }
}
