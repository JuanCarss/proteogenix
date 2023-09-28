import es.ulpgc.DNA;
import es.ulpgc.RNAPolymerase;
import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.MessengerRNA;
import es.ulpgc.strands.Sense;
import org.junit.Test;

import java.util.List;

import static es.ulpgc.NitrogenousBase.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RNAPolymeraseTest {
    @Test
    public void should_create_mRNA_from_DNA_with_sense_strand() {
        assertThat(new RNAPolymerase().transcribe(new DNA(new Sense(List.of(ADENINE, THYMINE, GUANINE, CYTOSINE, THYMINE, GUANINE))), 0, 6))
                .isEqualTo(new MessengerRNA(List.of(ADENINE, URACIL, GUANINE, CYTOSINE, URACIL, GUANINE)));
    }

    @Test
    public void should_create_mRNA_from_DNA_with_antisense_strand() {
        assertThat(new RNAPolymerase().transcribe(new DNA(new Antisense(List.of(ADENINE, THYMINE, GUANINE, CYTOSINE, ADENINE, THYMINE, CYTOSINE, THYMINE, GUANINE))), 3, 9))
                .isEqualTo(new MessengerRNA(List.of(GUANINE, URACIL, ADENINE, GUANINE, ADENINE, CYTOSINE)));
    }

    @Test(expected = RuntimeException.class)
    public void should_raise_error_when_DNA_gene_is_not_divisible_by_three() {
        new RNAPolymerase().transcribe(new DNA(new Antisense(List.of(ADENINE, THYMINE, GUANINE, CYTOSINE))), 0, 4);
    }
}
