package es.ulpgc.cells;

import es.ulpgc.*;
import es.ulpgc.polymerases.DNAPolymerase;
import es.ulpgc.polymerases.RNAPolymerase;

import java.util.List;
import java.util.stream.Collectors;

public class SomaticCell implements Cell {
    private static final Helicase Helicase = new Helicase();
    private static final DNAPolymerase DNAPolymerase = new DNAPolymerase();
    private static final RNAPolymerase RNAPOLYMERASE = new RNAPolymerase();
    private static final Ribosome Ribosome = new Ribosome(new TransferRNA());
    private final Nucleus nucleus;

    public SomaticCell(DNA dna){
        this.nucleus = new Nucleus(dna);
    }

    public List<Cell> mitose() {
        return Helicase.splitIntoStrands(this.nucleus.dna()).stream()
                .map(DNAPolymerase::transcribe)
                .map(strand -> new SomaticCell(new DNA(strand)))
                .collect(Collectors.toList());
    }
}
