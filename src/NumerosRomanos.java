import java.io.FileWriter;    //Trillas Ortiz Jose Luis
import java.io.IOException;   //Garcia Gonzales Diego Andres
import java.util.HashMap;     //Ruiz Grijalva Valeria
import java.util.Map;

public class NumerosRomanos {

    private static final Map<Character, Integer> mapaRomano = new HashMap<>();
    static {
        mapaRomano.put('I', 1);
        mapaRomano.put('V', 5);
        mapaRomano.put('X', 10);
        mapaRomano.put('L', 50);
        mapaRomano.put('C', 100);
        mapaRomano.put('D', 500);
        mapaRomano.put('M', 1000);
    }

    public static int romanoADecimal(String roman) {
        int decimal = 0;
        int prevalor = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int valor = mapaRomano.get(roman.charAt(i));

            if (valor < prevalor) {
                decimal -= valor;
            } else {
                decimal += valor;
            }
            prevalor = valor;
        }

        return decimal;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Recuerda ingresar una lista de numeros romanos como argumentos, como por ejemplo: I V VII X XI");
            return;
        }

        try (FileWriter csvWriter = new FileWriter("romanos.csv")) {
            csvWriter.append("Numero Romano,Equivalente Decimal\n");

            for (String roman : args) {
                int decimal = romanoADecimal(roman);
                System.out.println("El equivalente decimal de " + roman + " es: " + decimal);
                csvWriter.append(roman).append(",").append(String.valueOf(decimal)).append("\n");
            }

            System.out.println("El archivo romanos.csv ha sido generado exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrio un error al escribir el archivo CSV.");
            e.printStackTrace();
        }
    }
}
