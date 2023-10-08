package es.ulpgc;

import java.util.List;
import java.util.Objects;

public class Codon {
    public static final int SIZE = 3;
    private final NitrogenousBase firstBase;
    private final NitrogenousBase secondBase;
    private final NitrogenousBase thirdBase;

    public Codon(NitrogenousBase firstBase, NitrogenousBase secondBase, NitrogenousBase thirdBase) {
        this.firstBase = firstBase;
        this.secondBase = secondBase;
        this.thirdBase = thirdBase;
    }

    public Codon(List<NitrogenousBase> bases) {
        if (!isTriplet(bases)) throw new RuntimeException("Codon has to be of size three.");
        this.firstBase = bases.get(0);
        this.secondBase = bases.get(1);
        this.thirdBase = bases.get(2);
    }

    public List<NitrogenousBase> bases() {
        return List.of(this.firstBase, this.secondBase, this.thirdBase);
    }

    private boolean isTriplet(List<NitrogenousBase> bases) {
        return bases.size() == SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Codon codon = (Codon) o;
        return firstBase == codon.firstBase && secondBase == codon.secondBase && thirdBase == codon.thirdBase;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstBase, secondBase, thirdBase);
    }
}
