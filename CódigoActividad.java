package operacionFicheros;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
public class MenuFichero {
    public static void main(String[] args){
        Scanner entrada = new Scanner(System.in);
        int option;
        boolean exit = false;
        File fichero;
        String ruta;
        try {
            while (!exit) {
                System.out.println("Operaciones con ficheros");
                System.out.println("=========================");
                System.out.println("Que operación desea hacer?");
                System.out.println("1. Listar archivos de forma simple.");
                System.out.println("1. Listar archivos de forma detallada.");
                System.out.println("3. Crear carpeta.");
                System.out.println("4. Copiar archivo.");
                System.out.println("5. Mover archivo.");
                System.out.println("6. Leer archivo.");
                System.out.println("7. Escribir archivo");
                System.out.println("8. Eliminar archivo.");
                System.out.println("9. Salir.");
                option = entrada.nextInt();
                entrada.nextLine();
                switch (option) {
                    case 1:
                        System.out.println(informacion(obtenerArchivo(entrada),true));
                        break;
                    case 2:
                        System.out.println(informacion(obtenerArchivo(entrada),false));
                        break;
                    case 3:
                        createFolder(obtenerArchivo(entrada));
                        break;
                    case 4:
                        copyFile(obtenerArchivo(entrada),obtenerRutaDestino(entrada));
                        break;
                    case 5:
                        moveFile(obtenerArchivo(entrada),obtenerRutaDestino(entrada));
                        break;
                    case 6:
                        //readFile(obtenerArchivo(entrada));
                        break;
                    case 7:
                        //writeFile(obtenerArchivo(entrada));
                        break;
                    case 8:
                        //deleteFile(obtenerArchivo(entrada));
                        break;
                    case 9:
                        exit = true;
                        break;
                    default:
                        System.out.println("Esa opcion no se encuentra en el menu.");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada no válida. Por favor, introduzca un número.");
            entrada.nextLine();  // Limpiar la entrada incorrecta
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Saliendo.");
    }

    //Para obtener mediante teclado la ruta de destino
    public static File obtenerRutaDestino(Scanner entrada){
        System.out.println("Introduce la ruta de destino");
        String ruta=entrada.nextLine();
        File fichero=new File(ruta);
        return fichero;
    }
    //Obtener la ruta del archivo mediante teclado.
    public static File obtenerArchivo(Scanner entrada){
        System.out.println("Introduce la ruta del fichero");
        String ruta=entrada.nextLine();
        File fichero=new File(ruta);
        return fichero;
    }
    /*public static void writeFile(File file){
        String cadena;
        try {
            FileWriter fileWriter=new FileWriter(file,true);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            System.out.println("Introduce el contenido.");
            Scanner entrada=new Scanner(System.in);
            cadena="\n"+entrada.nextLine();
            bufferedWriter.write(cadena);
            bufferedWriter.close();
            System.out.println("Texto escrito correctamente");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }*/
    /*public static void readFile(File file){
        try {
            String cadena;
            FileReader fileReader= new FileReader(file);
            BufferedReader bufferedReader= new BufferedReader(fileReader);
            while ((cadena = bufferedReader.readLine())!=null){
                System.out.println(cadena);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }*/
    //Copia el archivo a un directorio existente
    public static void copyFile(File file, File destino){
        try {
            if(file.exists()){
                FileUtils.copyFileToDirectory(file,destino);
                System.out.println("Fichero copiado correctamente.");
            }else {
                System.out.println("El fichero no existe.");
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /*public static void deleteFile(File file){
        try {
            if(file.exists()){
                FileUtils.delete(file);
                System.out.println("Fichero eliminado correctamente.");
            }else{
                System.out.println("El archivo no existe.");
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }*/
    //Mueve el archivo a un directorio existente.
    public static void moveFile(File file, File destino){
        try {
            if(file.exists()) {
                FileUtils.moveFileToDirectory(file, destino, false);
                System.out.println("Fichero movido correctamente.");
            }else {
                System.out.println("El fichero no existe.");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Obtiene información de un archivo identificando si es una carpeta o un fichero y dependiendo como quieras la información con el boolean llama al metodo obtenerInfo y le pasa el valor true o false.
    public static String informacion(File archivo, boolean simple) {
        StringBuilder resultado = new StringBuilder();
        if (archivo.exists()) {
            if (archivo.isDirectory()){
                resultado.append(archivo.getName()).append(" es un directorio. Contenidos:\n");
                File[] lista = archivo.listFiles();

                if (lista != null) {
                    for (File file : lista) {
                        resultado.append(file.isDirectory() ? "(/)" : "(_)");
                        resultado.append(" ");
                        resultado.append(obtenerInfo(file, simple)).append("\n");
                    }
                }
            }else{
                resultado.append("Es un fichero: ").append(obtenerInfo(archivo, simple)).append("\n");
            }
        }else{
            resultado.append("El fichero no existe");
        }
        return resultado.toString();
    }
    //Segun el boolean devuelve el nombre del archivo o llama al metodo infoDetallada para obetener toda la información según sea un directorio o un archivo.
    public static String obtenerInfo(File archivo, boolean simple) {
        if (simple) {
            return archivo.getName();
        } else {
            return archivo.isDirectory() ? infoDetallada(archivo, true) : infoDetallada(archivo, false);
        }
    }
    //Devuelve la información detallada del fichero según sea un directorio o no.
    public static String infoDetallada(File archivo, boolean esDirectorio) {
        long ultimaModificacion = archivo.lastModified();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = sdf.format(ultimaModificacion);
        StringBuilder resultado = new StringBuilder();

        if (esDirectorio) {
            resultado.append(archivo.getName()).append(" [");
        } else {
            resultado.append(archivo.getName()).append(" [").append(archivo.length()).append(" bytes ");
        }

        resultado.append(archivo.canRead() ? "r" : "-");
        resultado.append(archivo.canWrite() ? "w" : "-");
        resultado.append(archivo.canExecute() ? "x " : "- ");
        resultado.append(fechaFormateada).append("]");

        return resultado.toString();
    }
    //Permite crear carpetas.
    public static void createFolder(File carpeta){
        if (carpeta.mkdir()){
            System.out.println("La carpeta creada correctamente.");
        }else{
            System.out.println("La carpeta no se pudo crear.");
        }
    }
}
