import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class PR130mainPersonesHashmap {
    public static void main(String[] args) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "PR130persones.dat";
        HashMap<String, Integer> hash = new HashMap<>();
        hash.put("Cristina", 28);
        hash.put("Yuheng", 45);
        hash.put("Alex", 20);
        hash.put("Marc", 55);
        hash.put("Borja", 12);
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
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeInt(hash.size());
            for (String nom : hash.keySet()) {
                dos.writeUTF(nom);
                dos.writeInt(hash.get(nom));
            }
            dos.flush();
            dos.close();
            fos.close();

            FileInputStream fis = new FileInputStream(filePath);
            DataInputStream dis = new DataInputStream(fis);

            System.out.println(dis.readInt());
            for (String hash1 : hash.keySet()) {
                System.out.println(dis.readUTF() + " " + dis.readInt());
            }
            dis.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
