package es.ulpgc;

import java.util.List;


public interface Cell {
    List<Cell> performMitosis();
    Protein synthesizeProtein();
}
