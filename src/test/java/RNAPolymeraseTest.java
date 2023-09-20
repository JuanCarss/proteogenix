import es.ulpgc.DNA;
import es.ulpgc.RNAPolymerase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RNAPolymeraseTest {
    @Test
    public void should_create_empty_mRNA_from_empty_dna() {
        assertThat(new RNAPolymerase().transcript(new DNA("")).nucleotides().collect(Collectors.toList()))
                .isEqualTo(new ArrayList<>());
    }

    @Test
    public void should_create_mRNA_from_sequence_dna() {
        assertThat(new RNAPolymerase().transcript(new DNA("TAC")).nucleotides().collect(Collectors.toList()))
                .isEqualTo(List.of(RNAPolymerase.Nucleotides.Adenine, RNAPolymerase.Nucleotides.Uracil, RNAPolymerase.Nucleotides.Guanine));
    }

}
