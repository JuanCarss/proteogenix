package es.ulpgc.cells;

import es.ulpgc.*;
import es.ulpgc.polymerases.DNAPolymerase;
import es.ulpgc.polymerases.RNAPolymerase;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SomaticCell implements Cell {
    private final Nucleus nucleus;
    private final Helicase helicase;
    private final DNAPolymerase dnaPolymerase;
    private final RNAPolymerase rnaPolymerase;
    private final Ribosome ribosome;

    public SomaticCell(Nucleus nucleus, Helicase helicase, DNAPolymerase dnaPolymerase, RNAPolymerase rnaPolymerase, Ribosome ribosome) {
        this.nucleus = nucleus;
        this.helicase = helicase;
        this.dnaPolymerase = dnaPolymerase;
        this.rnaPolymerase = rnaPolymerase;
        this.ribosome = ribosome;
    }

    public List<Cell> performMitosis() {
        return this.helicase.splitIntoStrands(this.nucleus.dna()).stream()
                .map(this.dnaPolymerase::transcribe)
                .map(strand -> new SomaticCell(new Nucleus(new DNA(strand)), this.helicase, this.dnaPolymerase, this.rnaPolymerase, this.ribosome))
                .collect(Collectors.toList());
    }

    @Override
    public Protein synthesizeProtein() {
        return this.ribosome.translate(this.rnaPolymerase.transcribe(this.nucleus.dna().templateStrand()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SomaticCell cell = (SomaticCell) o;
        return Objects.equals(nucleus.dna().codingStrand(), cell.nucleus.dna().codingStrand()) && Objects.equals(helicase, cell.helicase) && Objects.equals(dnaPolymerase, cell.dnaPolymerase) && Objects.equals(rnaPolymerase, cell.rnaPolymerase) && Objects.equals(ribosome, cell.ribosome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nucleus, helicase, dnaPolymerase, rnaPolymerase, ribosome);
    }
}
