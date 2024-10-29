import java.text.SimpleDateFormat;
import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaActual = sdf.format(todayDate);

        System.out.println("Hola mundo");
        System.out.println("====================");

        System.out.println("Mi nombre es Ramón Pablo Botana Piñeiro.");
        System.out.println("Hoy es " + fechaActual);

    }
}