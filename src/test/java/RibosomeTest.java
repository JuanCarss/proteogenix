import es.ulpgc.Protein;
import es.ulpgc.Ribosome;
import es.ulpgc.TransferRNA;
import es.ulpgc.strands.MessengerRNA;
import org.junit.Test;

import java.util.List;

import static es.ulpgc.AminoAcid.*;
import static es.ulpgc.NitrogenousBase.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RibosomeTest {
    @Test
    public void should_create_protein() {
        assertThat(new Ribosome(new TransferRNA()).translate(new MessengerRNA(List.of(ADENINE, GUANINE, CYTOSINE, CYTOSINE, URACIL, ADENINE, CYTOSINE, CYTOSINE, CYTOSINE))))
                .isEqualTo(new Protein(List.of(SERINE, LEUCINE, PROLINE)));
    }

    @Test
    public void should_stop_creation_with_codon() {
        assertThat(new Ribosome(new TransferRNA()).translate(new MessengerRNA(List.of(ADENINE, GUANINE, CYTOSINE, URACIL, ADENINE, ADENINE, CYTOSINE, CYTOSINE, CYTOSINE))))
                .isEqualTo(new Protein(List.of(SERINE)));
    }
}
