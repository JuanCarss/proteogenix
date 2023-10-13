import es.ulpgc.*;
import es.ulpgc.cells.GermCell;
import es.ulpgc.cells.SomaticCell;
import es.ulpgc.deserializers.TsvDNADeserializer;
import es.ulpgc.polymerases.DNAPolymerase;
import es.ulpgc.polymerases.RNAPolymerase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CellReplicationTest {
    private DNADeserializer deserializer;
    private Helicase helicase;
    private DNAPolymerase dnaPolymerase;
    private RNAPolymerase rnaPolymerase;
    private Ribosome ribosome;
    private MeioticSpindle meioticSpindle;

    @Before
    public void setUp() {
        deserializer = new TsvDNADeserializer();
        helicase = new Helicase();
        dnaPolymerase = new DNAPolymerase();
        rnaPolymerase = new RNAPolymerase();
        ribosome = new Ribosome(new TransferRNA());
        meioticSpindle = new MeioticSpindle();
    }

    @Test
    public void should_split_Cell_into_two() {
        DNA dna = deserializer.deserialize("ATGGGGCTCAGCGAC\tS");
        Cell cell = new SomaticCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome);
        assertThat(cell.performMitosis().get(0)).isEqualTo(new SomaticCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome));
        assertThat(cell.performMitosis().get(1)).isEqualTo(new SomaticCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome));
    }

    @Test
    public void should_split_Cell_into_four() {
        DNA dna = deserializer.deserialize("ATGGGGCTCAGCGA\tS");
        DNA dna1 = deserializer.deserialize("ATGGGGC\tS");
        DNA dna2 = deserializer.deserialize("TCAGCGA\tS");
        GermCell germcell = new GermCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome,meioticSpindle);
        assertThat(germcell.performMeiosis().get(0)).isEqualTo(new GermCell(new Nucleus(dna1), helicase, dnaPolymerase, rnaPolymerase, ribosome,meioticSpindle));
        assertThat(germcell.performMeiosis().get(1)).isEqualTo(new GermCell(new Nucleus(dna2), helicase, dnaPolymerase, rnaPolymerase, ribosome,meioticSpindle));
        assertThat(germcell.performMeiosis().get(2)).isEqualTo(new GermCell(new Nucleus(dna1), helicase, dnaPolymerase, rnaPolymerase, ribosome,meioticSpindle));
        assertThat(germcell.performMeiosis().get(3)).isEqualTo(new GermCell(new Nucleus(dna2), helicase, dnaPolymerase, rnaPolymerase, ribosome,meioticSpindle));
    }
}
