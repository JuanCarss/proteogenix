package es.ulpgc;

import java.util.List;


public interface Cell {
    DNA DNA();
    List<SomaticCell> mitosis();
    List<Strand> helicase();



}
