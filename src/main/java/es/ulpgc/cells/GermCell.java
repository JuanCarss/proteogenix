package es.ulpgc.cells;

import es.ulpgc.*;
import es.ulpgc.polymerases.DNAPolymerase;
import es.ulpgc.polymerases.RNAPolymerase;
import es.ulpgc.strands.Antisense;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GermCell implements Cell {
    public Nucleus nucleus;
    private final Helicase helicase;
    private final DNAPolymerase dnaPolymerase;
    private final RNAPolymerase rnaPolymerase;
    private final Ribosome ribosome;
    private final MeioticSpindle meioticSpindle;

    public GermCell(Nucleus nucleus, Helicase helicase, DNAPolymerase dnaPolymerase, RNAPolymerase rnaPolymerase, Ribosome ribosome, MeioticSpindle meioticSpindle) {
        this.nucleus = nucleus;
        this.helicase = helicase;
        this.dnaPolymerase = dnaPolymerase;
        this.rnaPolymerase = rnaPolymerase;
        this.ribosome = ribosome;
        this.meioticSpindle = meioticSpindle;
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
    public List<GermCell> performMeiosis() {
        DNA duplicatedDNA = new DNA(new Antisense(dnaPolymerase.duplicateDNA(nucleus.dna())));
        List<GermCell> meiosis1 = splitCell(meioticSpindle.splitDNA(duplicatedDNA));
        return groupCells(meiosis1);
    }

    public List<GermCell> splitCell(List<List<NitrogenousBase>> splittedDNA){
        List<GermCell> splittedCells = new ArrayList<>();
        splittedCells.add(new GermCell(new Nucleus(new DNA(new Antisense(splittedDNA.get(0)))), helicase, dnaPolymerase, rnaPolymerase, ribosome,meioticSpindle));
        splittedCells.add(new GermCell(new Nucleus(new DNA(new Antisense(splittedDNA.get(1)))), helicase, dnaPolymerase, rnaPolymerase, ribosome,meioticSpindle));
        return splittedCells;
    }
    public List<GermCell> groupCells(List<GermCell> meiosis1){
        List<GermCell> meiosis2 = new ArrayList<>();
        for (GermCell cell : meiosis1) {
            meiosis2.add(splitCell(cell.meioticSpindle.splitDNA(cell.nucleus.dna())).get(0));
            meiosis2.add(splitCell(cell.meioticSpindle.splitDNA(cell.nucleus.dna())).get(1));
        }
        return meiosis2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GermCell germCell = (GermCell) o;
        return Objects.equals(nucleus.dna().templateStrand(), germCell.nucleus.dna().templateStrand()) && Objects.equals(helicase, germCell.helicase) && Objects.equals(dnaPolymerase, germCell.dnaPolymerase) && Objects.equals(rnaPolymerase, germCell.rnaPolymerase) && Objects.equals(ribosome, germCell.ribosome) && Objects.equals(meioticSpindle, germCell.meioticSpindle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nucleus, helicase, dnaPolymerase, rnaPolymerase, ribosome, meioticSpindle);
    }
}
