import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PR131mainEscriu {

    public static void main(String args[]) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "PR131HashMapData.ser";
        PR131hashmap pr131HashMap = new PR131hashmap();
        // Crear la carpeta 'data' si no existeix
        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'data'");
            }
        }

        System.out.println("");
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            pr131HashMap.getHashMap().put("Hola", 24);
            pr131HashMap.getHashMap().put("Fortinite", 45);
            pr131HashMap.getHashMap().put("COD", 12);
            pr131HashMap.getHashMap().put("Yuhenng", 1003);
            pr131HashMap.getHashMap().put("Adios", 2003);

            oos.writeObject(pr131HashMap);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
