import es.ulpgc.*;
import es.ulpgc.cells.SomaticCell;
import es.ulpgc.polymerases.RNAPolymerase;
import org.junit.Before;
import org.junit.Test;
import es.ulpgc.deserializers.TsvDNADeserializer;

public class CellReplicationTest {

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
    public void should_create_Cell_from_DNA() {
        DNA dna = deserializer.deserialize("ATGGGGCTCAGCGAC\tS");
        Cell cell = new SomaticCell(dna);
        cell.mitose();
    }

}
