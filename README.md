# Simulador de Proceso de Creación de Proteínas

## Descripción

Este es un simulador de procesos biológicos que permite visualizar el proceso de creación de proteínas a partir de una cadena de ácido desoxirribonucleico (ADN). La aplicación utiliza algoritmos avanzados para simular las etapas clave de la síntesis de proteínas, incluyendo la transcripción y la traducción

La síntesis de proteínas es un proceso esencial en la célula, ya que las proteínas son responsables de una amplia variedad de funciones biológicas, desde el transporte de moléculas hasta la regulación de procesos celulares.

## DNA:

Esta clase representa una cadena de ADN y almacena una de dos posibles hebras: una hebra de sentido (Sense) o una hebra de antisentido (Antisense), que se especifica en su constructor.
Tiene métodos templateStrand y codingStrand para obtener la hebra de plantilla y la hebra codificante, respectivamente, según el tipo de hebra almacenada.
Estos métodos verifican si la hebra actual es la hebra de plantilla y, en función de eso, devuelven la hebra correcta.
El método getComplementaryStrand se utiliza para obtener la hebra complementaria de la actual. Esto es útil para obtener la hebra codificante si la actual es la hebra de plantilla y viceversa.
La clase también tiene métodos equals y hashCode para comparar objetos DNA en función de la hebra almacenada.

## Strand
La interfaz Strand define una serie de operaciones básicas que pueden realizarse en una secuencia de bases nitrogenadas, como obtener las bases individuales o dividir la secuencia en codones.
Cualquier clase que implemente esta interfaz deberá proporcionar una implementación para el método bases(), y opcionalmente, puede utilizar la implementación por defecto del método codons()
para obtener una lista de codones a partir de la secuencia de bases. Esta interfaz es útil para representar y manipular secuencias de ADN o ARN en aplicaciones relacionadas con la biología molecular.

 ## TsvDNADeserializer:

Esta clase implementa la interfaz DNADeserializer, que parece ser una interfaz diseñada para deserializar datos de ADN.
Tiene un método deserialize que toma una cadena de ADN como entrada y devuelve un objeto DNA.
En el método deserialize, verifica si la cadena de ADN representa una hebra de sentido (Sense) o una hebra de antisentido (Antisense) examinando un carácter específico en la cadena.
Si el carácter es 's', se considera una hebra de sentido; de lo contrario, se considera una hebra de antisentido.
Luego, llama al método buildStrand para crear una lista de NitrogenousBase que representa la cadena de bases nitrogenadas.
Utiliza esta lista de bases nitrogenadas para crear un objeto DNA apropiado (ya sea Sense o Antisense) y lo devuelve.

## RNAPolymerase

La clase RNAPolymerase que proporcionaste esta relacionada con la transcripción del ADN en ARN mensajero (ARNm) en el contexto de la biología molecular. Aquí se explica cómo funciona esta clase:

### transcribe:
Este método toma tres parámetros:

dna: Un objeto de la clase DNA, que representa una secuencia de ADN.

promoter: Un valor entero que representa la posición de inicio de la transcripción en la cadena de ADN.

termination: Un valor entero que representa la posición de finalización de la transcripción en la cadena de ADN.

El método verifica si la longitud del gen (determinada por la diferencia entre termination y promoter) es divisible por tres, ya que los codones en ARNm son de tres bases
y deben ser traducibles correctamente a aminoácidos. Si no es divisible por tres, se lanza una excepción.

Luego, el método realiza la transcripción de la secuencia de ADN en ARNm:

Utiliza templateStrand() de la clase DNA para obtener la hebra de plantilla del ADN.
Luego, obtiene las bases de la hebra de plantilla, omitiendo las bases antes de promoter, tomando las bases desde promoter hasta termination, y luego las complementa utilizando el método complement.
Finalmente, crea un objeto MessengerRNA con las bases complementadas y lo devuelve. MessengerRNA parece ser una clase que representa el ARNm.
complement: Este método es privado y se utiliza para obtener la base complementaria de una base dada. Si la base es ADENINE, devuelve URACIL (debido a las reglas de apareamiento de bases en
la transcripción de ADN a ARNm), de lo contrario, llama al método complement de la base para obtener su complemento.

En resumen, la clase RNAPolymerase se utiliza para realizar la transcripción de una porción específica de una cadena de ADN en un ARNm correspondiente,
asegurándose de que la longitud del gen sea divisible por tres. Esta transcripción se basa en el concepto de complementariedad de bases. Esta clase es fundamental en la síntesis de proteínas,
ya que el ARNm generado contiene la información necesaria para la posterior traducción en aminoácidos.

## Codon

La clase Codon representa un codón en el contexto de la genética y la biología molecular. Un codón es una secuencia de tres bases nitrogenadas en una cadena de ARN mensajero (ARNm)
y es fundamental en la traducción del código genético en un aminoácido específico. A continuación, se presenta una explicación resumida de esta clase:

### Constructores:

La clase Codon tiene dos constructores:

El primer constructor toma tres objetos NitrogenousBase como parámetros para crear un codón con bases específicas.

El segundo constructor toma una lista de objetos NitrogenousBase y verifica si la lista tiene exactamente tres elementos. Si no cumple esta condición, se lanza una excepción.
Luego, utiliza los tres elementos de la lista para crear un codón.

### Método bases:

Este método devuelve una lista que contiene las tres bases nitrogenadas que componen el codón.

### Método isTriplet:

Este método privado verifica si una lista de bases tiene exactamente tres elementos. Se utiliza en el segundo constructor para garantizar que se cree un codón válido.
Métodos equals y hashCode:

Estos métodos se utilizan para comparar objetos Codon. El método equals compara las bases de dos codones para determinar si son iguales, y el método hashCode genera un valor hash basado en las bases del codón.

## TransferRNA

La clase TransferRNA es una representación de una molécula de ARN de transferencia (ARNt) y su función principal es traducir un codón en una secuencia de bases nitrogenadas en
el ARNm a un aminoácido específico. Aquí está una explicación resumida de esta clase:

### codonConversion: 
Esta es una constante estática que representa una tabla de conversión entre codones (secuencias de tres bases) y aminoácidos. Utiliza un Map para asociar cada posible secuencia
de tres bases nitrogenadas con un aminoácido correspondiente. Esto refleja el código genético en el que ciertos codones codifican para aminoácidos específicos.

### initMap: 
Este método estático es utilizado para inicializar la tabla de conversión codonConversion. Asocia cada posible secuencia de tres bases con un aminoácido correspondiente. Las secuencias
de bases nitrogenadas se representan como listas de NitrogenousBase, y los aminoácidos se representan como valores de la enumeración AminoAcid.

### toAminoAcid: 
Este método toma un objeto Codon como entrada y devuelve el aminoácido correspondiente según la tabla de conversión codonConversion. El Codon proporciona las bases nitrogenadas que
se deben buscar en la tabla para determinar el aminoácido.

## Ribosome

La clase Ribosome representa un ribosoma, una organela celular esencial en la síntesis de proteínas. Su función principal es tomar una secuencia de ARN mensajero (ARNm) y traducirla en una proteína,
utilizando ARNt (ARN de transferencia) como intermediario. Aquí tienes una explicación resumida de esta clase:

### Constructor:

El constructor de la clase Ribosome toma un objeto TransferRNA (tRNA) como parámetro. El tRNA desempeña un papel crucial en la traducción del código genético.
Método translate:

Este método toma un objeto MessengerRNA (mRNA) como entrada, que representa una secuencia de ARNm que contiene codones.
Utiliza la secuencia de codones en el ARNm para mapear cada codón a su aminoácido correspondiente utilizando el tRNA (utilizando el método toAminoAcid del tRNA).
El método takeWhile(Objects::nonNull) se utiliza para detener la traducción cuando se encuentre un codón nulo. En la tabla de conversión de codones , algunos codones no codifican para
ningún aminoácido sino que representan codones de terminación, los cuales estan en el código representados como nulos.
Luego, los aminoácidos se recopilan en una lista y se utilizan para crear un objeto Protein, que representa la proteína resultante de la traducción.

## Ejemplo de uso

```
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
```
Este caso de prueba se centra en probar la síntesis de proteínas a partir de una secuencia de ADN utilizando las clases TsvDNADeserializer, RNAPolymerase y Ribosome. Aquí está la explicación del ejemplo:

Preparación (setUp):

En el método setUp, se inicializan tres objetos:

deserializer: Un objeto de la clase TsvDNADeserializer, que se utiliza para deserializar una cadena de ADN.

polymerase: Un objeto de la clase RNAPolymerase, que se utiliza para realizar la transcripción del ADN en ARNm.

ribosome: Un objeto de la clase Ribosome, que se utiliza para traducir el ARNm en una proteína.

Caso de prueba (should_synthesize_protein_from_DNA_with_sense_strand):

Este método de prueba verifica el proceso de síntesis de proteínas a partir de una cadena de ADN que tiene una hebra de sentido ("sense strand").
Se crea una instancia de la clase DNA mediante la deserialización de la cadena "ATGGGGCTCAGCGAC\tS" utilizando el deserializer. Esta cadena representa una secuencia de ADN con una hebra de sentido.
Luego, se utiliza el polymerase para transcribir la secuencia de ADN en un ARNm. Se toma un segmento de la secuencia desde la posición 0 hasta la 15.
Finalmente, se utiliza el ribosome para traducir el ARNm en una proteína.
Se utiliza assertThat del framework AssertJ para afirmar que la proteína resultante es igual a una lista de aminoácidos esperados (METHIONINE, GLYCINE, LEUCINE, SERINE, ASPARTIC_ACID).

