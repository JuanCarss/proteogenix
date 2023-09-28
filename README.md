# Simulador de Proceso de Creación de Proteínas

## Descripción

Este es un simulador de procesos biológicos que permite visualizar el proceso de creación de proteínas a partir de una cadena de ácido desoxirribonucleico (ADN). La aplicación utiliza algoritmos avanzados para simular las etapas clave de la síntesis de proteínas, incluyendo la transcripción y la traducción

La síntesis de proteínas es un proceso esencial en la célula, ya que las proteínas son responsables de una amplia variedad de funciones biológicas, desde el transporte de moléculas hasta la regulación de procesos celulares.

## DNA:

Esta clase representa una cadena de ADN y almacena una de dos posibles hebras: una hebra de sentido (Sense) o una hebra de antisentido (Antisense), que se especifica en su constructor.
Tiene métodos templateStrand y codingStrand para obtener la hebra de plantilla y la hebra codificante, respectivamente, según el tipo de hebra almacenada.
Estos métodos verifican si la hebra actual es la hebra de plantilla y, en función de eso, devuelven la hebra correcta.
El método getComplementaryStrand se utiliza para obtener la hebra complementaria de la actual.
## Strand
La interfaz Strand define una serie de operaciones básicas que pueden realizarse en una secuencia de bases nitrogenadas, como obtener las bases individuales o dividir la secuencia en codones.
Cualquier clase que implemente esta interfaz deberá proporcionar una implementación para el método bases(), y opcionalmente, puede utilizar la implementación por defecto del método codons()
para obtener una lista de codones a partir de la secuencia de bases. Esta interfaz es útil para representar y manipular secuencias de ADN (Sense y Antisense) o ARNm.
 ## TsvDNADeserializer:

Esta clase implementa la interfaz DNADeserializer, que es una interfaz diseñada para deserializar datos de ADN.
Tiene un método deserialize que toma una cadena de ADN como entrada y devuelve un objeto DNA.
En el método deserialize, verifica si la cadena de ADN representa una hebra de sentido (Sense) o una hebra de antisentido (Antisense) examinando el caracter separado de la cadena por un tabulador ya que se lee de un archivo tsv

Si el carácter es 's', se considera una hebra de sentido; de lo contrario, se considera una hebra de antisentido.
Luego, llama al método buildStrand para crear una lista de NitrogenousBase que representa la cadena de bases nitrogenadas.
Utiliza esta lista de bases nitrogenadas para crear un objeto DNA apropiado (ya sea Sense o Antisense) y lo devuelve.

## RNAPolymerase

La clase RNAPolymerase está relacionada con la transcripción del ADN en ARN mensajero (ARNm) en el contexto de la biología molecular. Aquí se explica cómo funciona esta clase:

### transcribe:
Este método toma tres parámetros:

#### dna: 
Un objeto de la clase DNA, que representa una secuencia de ADN.

#### promoter:
Un valor entero que representa la posición de inicio de la transcripción en la cadena de ADN.

#### termination:
Un valor entero que representa la posición de finalización de la transcripción en la cadena de ADN.

El método verifica si la longitud del gen (determinada por la diferencia entre termination y promoter) es divisible por tres, ya que los codones en ARNm son de tres bases
y deben ser traducibles correctamente a aminoácidos. Si no es divisible por tres, se lanza una excepción.

Luego, el método realiza la transcripción de la secuencia de ADN en ARNm:

Utiliza templateStrand() de la clase DNA para obtener la hebra de plantilla del ADN.
Luego, obtiene las bases de la hebra de plantilla, omitiendo las bases antes de promoter, tomando las bases desde promoter hasta termination, y luego las complementa utilizando el método complement.
Finalmente, crea un objeto MessengerRNA con las bases complementadas y lo devuelve. MessengerRNA es una clase que representa el ARNm.

### complement:
Este método se utiliza para obtener la base complementaria de una base dada. Si la base es ADENINE, devuelve URACIL (debido a las reglas de apareamiento de bases en
la transcripción de ADN a ARNm), de lo contrario, llama al método complement de la base para obtener su complemento.

En resumen, la clase RNAPolymerase se utiliza para realizar la transcripción de un gen en un ARNm correspondiente,
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

## TransferRNA

La clase TransferRNA es una representación de todas las moléculas de ARN de transferencia (ARNt) y su función principal es traducir un codón a un aminoácido específico. Aquí está una explicación resumida de esta clase:

## Ribosome

La clase Ribosome representa un ribosoma, una organela celular esencial en la síntesis de proteínas. Su función principal es tomar una secuencia de ARN mensajero (ARNm) y crear un peptido que sirve de base para crear la proteína,
utilizando ARNt (ARN de transferencia) como intermediario. Aquí tienes una explicación resumida de esta clase:

### Método translate:

Este método toma un objeto MessengerRNA (mRNA) como entrada, que representa una secuencia de codones.
Utiliza la secuencia de codones en el ARNm para mapear cada codón a su aminoácido correspondiente utilizando el tRNA (utilizando el método toAminoAcid del tRNA).
El método takeWhile(Objects::nonNull) se utiliza para detener la traducción cuando se encuentre un codón de terminación. En la tabla de conversión de codones, algunos codones no codifican para ningún aminoácido pues son codones de terminación y se representan como nulos.
Luego, los aminoácidos se recopilan en una lista y se utilizan para crear un objeto Protein, que representa la proteína resultante de la traducción.

## Ejemplo de uso

```
package es.ulpgc;

import es.ulpgc.deserializers.TsvDNADeserializer;
import es.ulpgc.strands.MessengerRNA;

public class Main {
    public static void main(String[] args) {
        DNA dna = new TsvDNADeserializer().deserialize("ATGGGGCTCAGCGAC\tS");
        MessengerRNA messengerRNA = new RNAPolymerase().transcribe(dna, 0, 15);
        Protein protein = new Ribosome(new TransferRNA()).translate(messengerRNA);
    }
}
```
Este código representa una simulación simplificada de la síntesis de proteínas. Comienza con una cadena de ADN, la transcribe en ARNm y luego traduce ese ARNm en una proteína. Cada paso se realiza utilizando clases específicas que simulan las etapas del proceso biológico real. Este tipo de simulación puede ser útil para comprender mejor la biología molecular y genética o para fines educativos y de investigación.

## Contact

(c) 2023 Juan Carlos Santana Santana
(c) 2023 Liam Manuel Mahmud Morera
