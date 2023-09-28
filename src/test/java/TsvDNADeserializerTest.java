import es.ulpgc.DNA;
import es.ulpgc.deserializers.TsvDNADeserializer;
import es.ulpgc.strands.Antisense;
import es.ulpgc.strands.Sense;
import org.junit.Test;

import java.util.List;

import static es.ulpgc.NitrogenousBase.*;
import static org.assertj.core.api.Assertions.assertThat;

public class TsvDNADeserializerTest {
    @Test
    public void should_deserialize_DNA_with_sense_strand() {
        assertThat(new TsvDNADeserializer().deserialize("AGTC\tS")).isEqualTo(new DNA(new Sense(List.of(ADENINE, GUANINE, THYMINE, CYTOSINE))));
    }

    @Test
    public void should_deserialize_DNA_with_antisense_strand() {
        assertThat(new TsvDNADeserializer().deserialize("AGTC\tA")).isEqualTo(new DNA(new Antisense(List.of(ADENINE, GUANINE, THYMINE, CYTOSINE))));
    }
}
