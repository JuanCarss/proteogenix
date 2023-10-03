package es.ulpgc;

import es.ulpgc.polymerases.DNAPolymerase;
import es.ulpgc.strands.ComplimentaryStrand;

import java.util.ArrayList;
import java.util.List;

public class SomaticCell implements Cell{
    public static final DNAPolymerase polymerase = new DNAPolymerase();

    private final DNA dna;

    public SomaticCell(DNA dna){
        this.dna = dna;
    }

    public DNA DNA(){return this.dna;}

    public List<SomaticCell> mitosis() {
        List<Strand> Strands = helicase();
        List<SomaticCell> somaticCells = CellDivision(
                polymerase.okazakiFragmentReplication(Strands.get(0)),
                polymerase.okazakiFragmentReplication(Strands.get(1)));
        return somaticCells;
    }
    public List<Strand> helicase(){
        List<Strand> Strands = new ArrayList<>();
        Strands.add(this.dna.templateStrand());
        Strands.add(this.dna.codingStrand());
        return Strands;
    }

    public List<SomaticCell> CellDivision(ComplimentaryStrand templateComplimentary, ComplimentaryStrand codingComplimentary){
        SomaticCell Cell1 = new SomaticCell(new DNA(templateComplimentary));
        SomaticCell Cell2 = new SomaticCell(new DNA(codingComplimentary));
        List<SomaticCell> NewCells = new ArrayList<>();
        NewCells.add(Cell1);
        NewCells.add(Cell2);
        return NewCells;
    }


}
