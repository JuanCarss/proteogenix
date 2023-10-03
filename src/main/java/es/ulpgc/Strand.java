package es.ulpgc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Strand {

    int CODON_SIZE = 3;

    List<NitrogenousBase> bases();
    default List<Codon> codons() {
        return IntStream.range(0, (bases().size() - 1) / CODON_SIZE + 1)
                .mapToObj(n -> bases().subList(n * CODON_SIZE, n == ((bases().size() - 1) / CODON_SIZE) ? bases().size() : (n + 1) * CODON_SIZE))
                .map(Codon::new)
                .collect(Collectors.toList());
    }

    default int size() {
        return bases().size();
    }
}
