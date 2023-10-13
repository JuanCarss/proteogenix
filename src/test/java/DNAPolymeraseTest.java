import es.ulpgc.polymerases.DNAPolymerase;
import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.Sense;
import org.junit.Test;

import java.util.List;

import static es.ulpgc.NitrogenousBase.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DNAPolymeraseTest {
    @Test
    public void should_create_sense_strand_from_antisense_strand() {
        assertThat(new DNAPolymerase().transcribe(new Antisense(List.of(ADENINE, THYMINE, GUANINE, CYTOSINE, ADENINE, THYMINE, CYTOSINE, THYMINE, GUANINE))))
                .isEqualTo(new Sense(List.of(THYMINE, ADENINE, CYTOSINE, GUANINE, THYMINE, ADENINE, GUANINE, ADENINE, CYTOSINE)));
    }

    @Test
    public void should_create_antisense_strand_from_sense_strand() {
        assertThat(new DNAPolymerase().transcribe(new Sense(List.of(ADENINE, THYMINE, GUANINE, CYTOSINE, ADENINE, THYMINE, CYTOSINE, THYMINE, GUANINE))))
                .isEqualTo(new Antisense(List.of(THYMINE, ADENINE, CYTOSINE, GUANINE, THYMINE, ADENINE, GUANINE, ADENINE, CYTOSINE)));
    }
}
