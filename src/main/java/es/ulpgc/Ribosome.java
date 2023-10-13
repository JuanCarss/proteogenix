package es.ulpgc;

import es.ulpgc.strands.MessengerRNA;

import java.util.List;
import java.util.stream.Collectors;

import static es.ulpgc.NitrogenousBase.*;

public class Ribosome {
    private static final List<Codon> STOP_CODONS = List.of(
            new Codon(URACIL, ADENINE, ADENINE),
            new Codon(URACIL, ADENINE, GUANINE),
            new Codon(URACIL, GUANINE, ADENINE));
    private final TransferRNA tRNA;

    public Ribosome(TransferRNA tRNA) {
        this.tRNA = tRNA;
    }

    public Protein translate(MessengerRNA mRNA) {
        return new Protein(mRNA.codons().stream()
                .takeWhile(codon -> !isStop(codon))
                .map(tRNA::toAminoAcid)
                .collect(Collectors.toList()));
    }

    private boolean isStop(Codon codon) {
        return STOP_CODONS.contains(codon);
    }
}
