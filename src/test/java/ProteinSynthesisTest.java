import es.ulpgc.*;
import es.ulpgc.cells.SomaticCell;
import es.ulpgc.deserializers.TsvDNADeserializer;
import es.ulpgc.polymerases.DNAPolymerase;
import es.ulpgc.polymerases.RNAPolymerase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static es.ulpgc.AminoAcid.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ProteinSynthesisTest {
    private DNADeserializer deserializer;
    private Helicase helicase;
    private DNAPolymerase dnaPolymerase;
    private RNAPolymerase rnaPolymerase;
    private Ribosome ribosome;

    @Before
    public void setUp() {
        deserializer = new TsvDNADeserializer();
        helicase = new Helicase();
        dnaPolymerase = new DNAPolymerase();
        rnaPolymerase = new RNAPolymerase();
        ribosome = new Ribosome(new TransferRNA());
    }

    @Test
    public void should_synthesize_protein_from_DNA_with_sense_strand() {
        DNA dna = deserializer.deserialize("ATATATGGGGCTCAGCGAC\tS");
        assertThat(new SomaticCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome).synthesizeProtein())
                .isEqualTo(new Protein(List.of(METHIONINE, GLYCINE, LEUCINE, SERINE, ASPARTIC_ACID)));
    }

    @Test
    public void should_synthesize_protein_from_DNA_with_antisense_strand() {
        DNA dna = deserializer.deserialize("TATATACGGCGTTAGACAAGTGCGTGAGTACACA\tA");
        assertThat(new SomaticCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome).synthesizeProtein())
                .isEqualTo(new Protein(List.of(METHIONINE, PROLINE, GLUTAMINE, SERINE, VALINE, HISTIDINE, ALANINE, LEUCINE, METHIONINE, CYSTEINE)));
    }


    @Test
    public void should_synthesize_protein_from_DNA_with_antisense_strand_with_stop() {
        DNA dna = deserializer.deserialize("TATATACGGCGTTAGAACTCAAGTGCGTGAGTACACA\tA");
        assertThat(new SomaticCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome).synthesizeProtein())
                .isEqualTo(new Protein(List.of(METHIONINE, PROLINE, GLUTAMINE, SERINE)));
    }
}
