package es.ulpgc.deserializers;

import es.ulpgc.DNA;
import es.ulpgc.DNADeserializer;
import es.ulpgc.NitrogenousBase;
import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.Sense;

import java.util.List;
import java.util.stream.Collectors;

import static es.ulpgc.NitrogenousBase.*;

public class TsvDNADeserializer implements DNADeserializer {
    @Override
    public DNA deserialize(String dna) {
        if (isSenseStrand(dna)) return new DNA(new Sense(buildStrand(dna)));
        return new DNA(new Antisense(buildStrand(dna)));
    }

    private static boolean isSenseStrand(String dna) {
        return Character.toLowerCase(dna.charAt(dna.indexOf('\t') + 1)) == 's';
    }

    private List<NitrogenousBase> buildStrand(String dna) {
        return dna.chars()
                .mapToObj(i -> (char) i)
                .map(Character::toLowerCase)
                .takeWhile(character -> !character.equals('\t'))
                .map(this::toNitrogenousBase)
                .collect(Collectors.toList());
    }

    private NitrogenousBase toNitrogenousBase(Character character) {
        if (character.equals('a')) return ADENINE;
        if (character.equals('c')) return CYTOSINE;
        if (character.equals('g')) return GUANINE;
        return THYMINE;
    }
}
