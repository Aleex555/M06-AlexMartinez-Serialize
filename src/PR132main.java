import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PR132main {
    public static void main(String args[]) throws ClassNotFoundException {
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "PR132people.dat";

        File dir = new File(basePath);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                System.out.println("Error en la creació de la carpeta 'data'");
            }
        }

        System.out.println("");

        PR132persona persona = new PR132persona("Cristian", "Alvarez", 12);
        PR132persona persona2 = new PR132persona("Alex", "Martinez", 20);
        PR132persona persona3 = new PR132persona("Yuheng", "Zhou", 25);

        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(persona);
            oos.writeObject(persona2);
            oos.writeObject(persona3);
            oos.close();

            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            persona = (PR132persona) ois.readObject();
            persona2 = (PR132persona) ois.readObject();
            persona3 = (PR132persona) ois.readObject();
            System.out.println(persona);
            System.out.println(persona2);
            System.out.println(persona3);

            ois.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
