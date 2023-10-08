import es.ulpgc.*;
import es.ulpgc.cells.SomaticCell;
import es.ulpgc.deserializers.TsvDNADeserializer;
import es.ulpgc.polymerases.DNAPolymerase;
import es.ulpgc.polymerases.RNAPolymerase;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CellReplicationTest {
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
    public void should_split_Cell_into_two() {
        DNA dna = deserializer.deserialize("ATGGGGCTCAGCGAC\tS");
        Cell cell = new SomaticCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome);
        assertThat(cell.performMitosis().get(0)).isEqualTo(new SomaticCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome));
        assertThat(cell.performMitosis().get(1)).isEqualTo(new SomaticCell(new Nucleus(dna), helicase, dnaPolymerase, rnaPolymerase, ribosome));
    }
}
