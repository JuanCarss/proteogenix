import es.ulpgc.DNA;
import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.Sense;
import org.junit.Test;

import java.util.List;

import static es.ulpgc.NitrogenousBase.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DNATest {
    @Test
    public void should_create_DNA_from_sense_strand() {
        assertThat(new DNA(new Sense(List.of(ADENINE, GUANINE, CYTOSINE, THYMINE))).codingStrand().bases())
                .isEqualTo(List.of(ADENINE, GUANINE, CYTOSINE, THYMINE));
    }

    @Test
    public void should_return_template_strand_from_DNA_with_sense_strand() {
        assertThat(new DNA(new Sense(List.of(ADENINE, GUANINE, CYTOSINE, THYMINE))).templateStrand().bases())
                .isEqualTo(List.of(THYMINE, CYTOSINE, GUANINE, ADENINE));
    }

    @Test
    public void should_create_DNA_from_antisense_strand() {
        assertThat(new DNA(new Antisense(List.of(ADENINE, GUANINE, CYTOSINE, THYMINE))).templateStrand().bases())
                .isEqualTo(List.of(ADENINE, GUANINE, CYTOSINE, THYMINE));
    }

    @Test
    public void should_return_coding_strand_from_DNA_with_antisense_strand() {
        assertThat(new DNA(new Antisense(List.of(ADENINE, GUANINE, CYTOSINE, THYMINE))).codingStrand().bases())
                .isEqualTo(List.of(THYMINE, CYTOSINE, GUANINE, ADENINE));
    }

    @Test(expected = RuntimeException.class)
    public void should_raise_error_when_creating_ADN_with_uracil_base() {
        new DNA(new Sense(List.of(URACIL, ADENINE, GUANINE, THYMINE)));
    }
}
