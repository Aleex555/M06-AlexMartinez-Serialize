import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class PR131mainLlegeix {
    public static void main(String args[]) {
        String basePath = System.getProperty("user.dir") + "/data/";
        String filePath = basePath + "PR131HashMapData.ser";
        PR131hashmap pr131HashMap = new PR131hashmap();

        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            pr131HashMap = (PR131hashmap) ois.readObject();
            HashMap<String, Integer> hashMap = pr131HashMap.getHashMap();
            for (String nom : hashMap.keySet()) {
                System.out.println("Nom: " + nom + ", Edat: " + hashMap.get(nom));
            }

            ois.close();
            fis.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
