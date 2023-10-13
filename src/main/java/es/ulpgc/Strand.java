package es.ulpgc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Strand {
    List<NitrogenousBase> bases();
    default List<Codon> codons() {
        return IntStream.range(0, (bases().size() - 1) / Codon.SIZE + 1)
                .mapToObj(n -> bases().subList(n * Codon.SIZE, n == ((bases().size() - 1) / Codon.SIZE) ? bases().size() : (n + 1) * Codon.SIZE))
                .map(Codon::new)
                .collect(Collectors.toList());
    }

    default int size() {
        return bases().size();
    }
}
