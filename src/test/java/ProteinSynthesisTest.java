import es.ulpgc.*;
import es.ulpgc.deserializers.TsvDNADeserializer;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static es.ulpgc.AminoAcid.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProteinSynthesisTest {
    private DNADeserializer deserializer;
    private RNAPolymerase polymerase;
    private Ribosome ribosome;

    @Before
    public void setUp() {
        deserializer = new TsvDNADeserializer();
        polymerase = new RNAPolymerase();
        ribosome = new Ribosome(new TransferRNA());
    }

    @Test
    public void should_synthesize_protein_from_DNA_with_sense_strand() {
        DNA dna = deserializer.deserialize("ATGGGGCTCAGCGAC\tS");
        assertThat(ribosome.translate(polymerase.transcribe(dna, 0, 15)))
                .isEqualTo(new Protein(List.of(METHIONINE, GLYCINE, LEUCINE, SERINE, ASPARTIC_ACID)));
    }
}
