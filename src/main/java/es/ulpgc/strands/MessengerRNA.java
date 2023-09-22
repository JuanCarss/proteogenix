package es.ulpgc.strands;

import es.ulpgc.NitrogenousBase;
import es.ulpgc.Strand;

import java.util.List;

public record MessengerRNA(List<NitrogenousBase> bases) implements Strand {
}
