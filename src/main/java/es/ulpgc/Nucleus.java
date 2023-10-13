package es.ulpgc;

import java.util.Objects;

public record Nucleus(DNA dna) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nucleus nucleus = (Nucleus) o;
        return Objects.equals(dna, nucleus.dna);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dna);
    }
}
