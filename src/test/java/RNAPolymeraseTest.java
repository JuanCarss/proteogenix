import es.ulpgc.polymerases.RNAPolymerase;
import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.MessengerRNA;
import org.junit.Test;

import java.util.List;

import static es.ulpgc.NitrogenousBase.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RNAPolymeraseTest {
    @Test
    public void should_create_mRNA_from_DNA_with_antisense_strand() {
        assertThat(new RNAPolymerase().transcribe(new Antisense(List.of(THYMINE, ADENINE, THYMINE, ADENINE, ADENINE, THYMINE, GUANINE, CYTOSINE, ADENINE, THYMINE, CYTOSINE, THYMINE, GUANINE))))
                .isEqualTo(new MessengerRNA(List.of(URACIL, ADENINE, CYTOSINE, GUANINE, URACIL, ADENINE, GUANINE, ADENINE, CYTOSINE)));
    }

    @Test(expected = RuntimeException.class)
    public void should_raise_error_when_DNA_has_no_promoter() {
        new RNAPolymerase().transcribe(new Antisense(List.of(ADENINE, THYMINE, ADENINE, ADENINE, THYMINE, GUANINE, CYTOSINE, ADENINE, THYMINE, CYTOSINE, THYMINE, GUANINE)));
    }
}
