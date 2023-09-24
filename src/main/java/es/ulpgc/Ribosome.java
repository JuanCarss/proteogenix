package es.ulpgc;

import es.ulpgc.strands.MessengerRNA;

import java.util.Objects;
import java.util.stream.Collectors;

public class Ribosome {
    private final TransferRNA tRNA;

    public Ribosome(TransferRNA tRNA) {
        this.tRNA = tRNA;
    }

    public Protein translate(MessengerRNA mRNA) {
        return new Protein(mRNA.codons().stream().map(tRNA::toAminoAcid).takeWhile(Objects::nonNull).collect(Collectors.toList()));
    }
}
