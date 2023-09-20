import es.ulpgc.MessengerRNA;
import es.ulpgc.RNAPolymerase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RibosomeTest {
    @Test
    public void should_create_empty_protein_from_empty_mRNA() {
        assertThat(new Ribosome().translate(new MessengerRNA(Stream.empty())).aminoacids()).isEqualTo(new ArrayList<>());
    }

    @Test
    public void should_create_protein_from_unicodon_mRNA() {
        assertThat(new Ribosome()
                .translate(new MessengerRNA(Stream.of(RNAPolymerase.Nucleotides.Guanine, RNAPolymerase.Nucleotides.Uracil, RNAPolymerase.Nucleotides.Uracil)))
                .aminoacids.collect(Collectors.toList()))
                .isEqualTo(List.of(Aminoacids.Valine));
    }

    private class Ribosome {
        public Protein translate(MessengerRNA mRNA) {
            return null;
        }
    }

    private class Codon {
        public Codon(Stream<Aminoacids> aminoacids) {

        }
    }

    private class Protein {
        private final Stream<Aminoacids> aminoacids;

        public Protein(Stream<Aminoacids> aminoacids) {
            this.aminoacids = aminoacids;
        }

        public Stream<Aminoacids> aminoacids() {
            return Stream.empty();
        }
    }

    private enum Aminoacids { Alanine, Arginine, Asparagine, AsparticAcid, Cysteine, Glutamine, GlutamicAcid, Glycine,
        Histidine, Isoleucine, Leucine, Lysine, Methionine, Phenylalanine, Proline, Serine, Threonine, Tryptophan, Tyrosine, Valine }
}
