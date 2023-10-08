package es.ulpgc.cells;

import es.ulpgc.*;
import es.ulpgc.polymerases.DNAPolymerase;
import es.ulpgc.polymerases.RNAPolymerase;

import java.util.List;
import java.util.stream.Collectors;

public class GermCell implements Cell {
    private final Nucleus nucleus;
    private final Helicase helicase;
    private final DNAPolymerase dnaPolymerase;
    private final RNAPolymerase rnaPolymerase;
    private final Ribosome ribosome;

    public GermCell(Nucleus nucleus, Helicase helicase, DNAPolymerase dnaPolymerase, RNAPolymerase rnaPolymerase, Ribosome ribosome) {
        this.nucleus = nucleus;
        this.helicase = helicase;
        this.dnaPolymerase = dnaPolymerase;
        this.rnaPolymerase = rnaPolymerase;
        this.ribosome = ribosome;
    }

    @Override
    public List<Cell> performMitosis() {
        return this.helicase.splitIntoStrands(this.nucleus.dna()).stream()
                .map(this.dnaPolymerase::transcribe)
                .map(strand -> new SomaticCell(new Nucleus(new DNA(strand)), new Helicase(), new DNAPolymerase(), new RNAPolymerase(), new Ribosome(new TransferRNA())))
                .collect(Collectors.toList());
    }

    @Override
    public Protein synthesizeProtein() {
        return this.ribosome.translate(this.rnaPolymerase.transcribe(this.nucleus.dna().templateStrand()));
    }
}
