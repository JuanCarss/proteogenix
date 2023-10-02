package es.ulpgc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static es.ulpgc.AminoAcid.*;
import static es.ulpgc.NitrogenousBase.*;

public class TransferRNA {
    private static final Map<List<NitrogenousBase>, AminoAcid> codonConversion = initMap();

    private static Map<List<NitrogenousBase>, AminoAcid> initMap() {
        Map<List<NitrogenousBase>, AminoAcid> map = new HashMap<>();
        map.put(List.of(URACIL,URACIL,URACIL), PHENYLALANINE);
        map.put(List.of(URACIL,URACIL,CYTOSINE), PHENYLALANINE);
        map.put(List.of(URACIL,URACIL,ADENINE), LEUCINE);
        map.put(List.of(URACIL,URACIL,GUANINE), LEUCINE);
        map.put(List.of(CYTOSINE,URACIL,URACIL), LEUCINE);
        map.put(List.of(CYTOSINE,URACIL,CYTOSINE), LEUCINE);
        map.put(List.of(CYTOSINE,URACIL,ADENINE), LEUCINE);
        map.put(List.of(CYTOSINE,URACIL,GUANINE), LEUCINE);
        map.put(List.of(ADENINE,URACIL,URACIL), ISOLEUCINE);
        map.put(List.of(ADENINE,URACIL,CYTOSINE), ISOLEUCINE);
        map.put(List.of(ADENINE,URACIL,ADENINE), ISOLEUCINE);
        map.put(List.of(ADENINE,URACIL,GUANINE), METHIONINE);
        map.put(List.of(GUANINE,URACIL,URACIL), VALINE);
        map.put(List.of(GUANINE,URACIL,CYTOSINE),VALINE);
        map.put(List.of(GUANINE,URACIL,ADENINE),VALINE);
        map.put(List.of(GUANINE,URACIL,GUANINE),VALINE);
        map.put(List.of(URACIL,CYTOSINE,URACIL),SERINE);
        map.put(List.of(URACIL,CYTOSINE,CYTOSINE),SERINE);
        map.put(List.of(URACIL,CYTOSINE,ADENINE),SERINE);
        map.put(List.of(URACIL,CYTOSINE,GUANINE),SERINE);
        map.put(List.of(CYTOSINE,CYTOSINE,URACIL),PROLINE);
        map.put(List.of(CYTOSINE,CYTOSINE,CYTOSINE),PROLINE);
        map.put(List.of(CYTOSINE,CYTOSINE,ADENINE),PROLINE);
        map.put(List.of(CYTOSINE,CYTOSINE,GUANINE),PROLINE);
        map.put(List.of(ADENINE,CYTOSINE,URACIL),THREONINE);
        map.put(List.of(ADENINE,CYTOSINE,CYTOSINE),THREONINE);
        map.put(List.of(ADENINE,CYTOSINE,ADENINE),THREONINE);
        map.put(List.of(ADENINE,CYTOSINE,GUANINE),THREONINE);
        map.put(List.of(GUANINE,CYTOSINE,URACIL), ALANINE);
        map.put(List.of(GUANINE,CYTOSINE,CYTOSINE), ALANINE);
        map.put(List.of(GUANINE,CYTOSINE,ADENINE), ALANINE);
        map.put(List.of(GUANINE,CYTOSINE,GUANINE), ALANINE);
        map.put(List.of(URACIL,ADENINE,URACIL),TYROSINE);
        map.put(List.of(URACIL,ADENINE,CYTOSINE),TYROSINE);
        map.put(List.of(URACIL,ADENINE,ADENINE), null);
        map.put(List.of(URACIL,ADENINE,GUANINE), null);
        map.put(List.of(CYTOSINE,ADENINE,URACIL),HISTIDINE); 
        map.put(List.of(CYTOSINE,ADENINE,CYTOSINE),HISTIDINE);
        map.put(List.of(CYTOSINE,ADENINE,ADENINE),GLUTAMINE);
        map.put(List.of(ADENINE,ADENINE,URACIL),GLUTAMINE);
        map.put(List.of(ADENINE,ADENINE,CYTOSINE), ASPARAGINE);
        map.put(List.of(ADENINE,ADENINE,ADENINE),LYSINE);
        map.put(List.of(ADENINE,ADENINE,GUANINE),LYSINE);
        map.put(List.of(GUANINE,ADENINE,URACIL),ASPARTIC_ACID);
        map.put(List.of(GUANINE,ADENINE,CYTOSINE),ASPARTIC_ACID);
        map.put(List.of(GUANINE,ADENINE,ADENINE),GLUTAMIC_ACID);
        map.put(List.of(GUANINE,ADENINE,GUANINE),GLUTAMIC_ACID);
        map.put(List.of(URACIL,GUANINE,URACIL), CYSTEINE);
        map.put(List.of(URACIL,GUANINE,CYTOSINE),CYSTEINE);
        map.put(List.of(URACIL,GUANINE,ADENINE), null);
        map.put(List.of(URACIL,GUANINE,GUANINE),TRYPTOPHAN); 
        map.put(List.of(CYTOSINE,GUANINE,URACIL),ARGININE);
        map.put(List.of(CYTOSINE,GUANINE,CYTOSINE),ARGININE);
        map.put(List.of(CYTOSINE,GUANINE,ADENINE),ARGININE);
        map.put(List.of(CYTOSINE,GUANINE,GUANINE),ARGININE);
        map.put(List.of(ADENINE,GUANINE,URACIL),SERINE);
        map.put(List.of(ADENINE,GUANINE,GUANINE),ARGININE);
        map.put(List.of(ADENINE,GUANINE,ADENINE),ARGININE);
        map.put(List.of(ADENINE,GUANINE,CYTOSINE),SERINE);
        map.put(List.of(GUANINE,GUANINE,URACIL),GLYCINE); 
        map.put(List.of(GUANINE,GUANINE,CYTOSINE),GLYCINE);
        map.put(List.of(GUANINE,GUANINE,ADENINE),GLYCINE);
        map.put(List.of(GUANINE,GUANINE,GUANINE),GLYCINE);
        return Collections.unmodifiableMap(map);
    }

    public AminoAcid toAminoAcid(Codon codon) {
        return codonConversion.get(codon.bases());
    }
}
