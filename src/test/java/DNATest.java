import es.ulpgc.DNA;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DNATest {
    @Test
    public void should_create_empty_dna() {
        assertThat(new DNA("").templateStrand()).isEqualTo("");
    }

    @Test
    public void should_create_dna_from_sequence() {
        assertThat(new DNA("ATCGTACG").templateStrand()).isEqualTo("ATCGTACG");
    }

    @Test
    public void should_return_empty_list_from_dna_empty_sequence() {
        assertThat(new DNA("").nucleotides().collect(Collectors.toList())).isEqualTo(new ArrayList<>());
    }

    @Test
    public void should_return_nucleotide_from_dna_value() {
        assertThat(new DNA("A").nucleotides().collect(Collectors.toList()))
                .isEqualTo(List.of(DNA.Nucleotides.Adenine));
    }

    @Test
    public void should_return_nucleotides_from_dna_sequence() {
        assertThat(new DNA("ATGC").nucleotides().collect(Collectors.toList()))
                .isEqualTo(List.of(DNA.Nucleotides.Adenine, DNA.Nucleotides.Thymine, DNA.Nucleotides.Guanine, DNA.Nucleotides.Cytosine));
    }
}
