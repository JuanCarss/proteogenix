package es.ulpgc;

import es.ulpgc.polymerases.DNAPolymerase;

import java.util.ArrayList;
import java.util.List;

public class SomaticCell implements Cell{

    private final DNA dna;

    public SomaticCell(DNA dna){
        this.dna = dna;
    }

    public DNA DNA(){return this.dna;}

    public void mitosis() {
        List<Strand> Strands = helicase();
        Strand TemplateStrand = Strands.get(0);
        Strand CodingStrand = Strands.get(1);
        new DNAPolymerase().okazakiSplit(this.dna);
    }
    public List<Strand> helicase(){
        List<Strand> Strands = new ArrayList<>();
        Strands.add(this.dna.templateStrand());
        Strands.add(this.dna.codingStrand());
        return Strands;
    }

//    public List<DNA> DNAPolymerase(){
//
//    }



}
