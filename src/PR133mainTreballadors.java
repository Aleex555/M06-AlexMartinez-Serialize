
import java.util.List;
import java.util.Scanner;

public class PR133mainTreballadors {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String basePath = System.getProperty("user.dir") + "/data/";
        String fileName = "PR133treballadors.csv";
        String filePath = basePath + fileName;

        System.out.println("");
        List<String> csv = UtilsCSV.read(filePath);
        try {

            System.out.print("Escribe el id: ");
            String id = sc.nextLine();
            int numLinia = UtilsCSV.getLineNumber(csv, "Id", id);

            System.out.print("Escribe la columna del dato que desea cambiar: ");
            String columna = sc.nextLine();
            if (columna != null && !columna.isEmpty()) {
                columna = columna.substring(0, 1).toUpperCase() + columna.substring(1);
            } else {
                System.out.println("La cadena está vacía o nula.");
            }

            System.out.print("Escribe el nuevo dato de la columna " + columna + " : ");
            String nuevoDato = sc.nextLine();

            UtilsCSV.update(csv, numLinia, columna, nuevoDato);
            UtilsCSV.write(filePath, csv);

            System.out.println("\nDades del CSV:");
            UtilsCSV.list(csv);
            sc.close();
        } catch (IndexOutOfBoundsException a) {
            System.out.println("El id no existe o la columna no existe");
        }

    }
}
