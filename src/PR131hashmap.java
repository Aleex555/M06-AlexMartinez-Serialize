import java.io.Serializable;
import java.util.HashMap;

class PR131hashmap implements Serializable {

    private HashMap<String, Integer> hash;

    public PR131hashmap() {
        hash = new HashMap<>();
    }

    public HashMap<String, Integer> getHashMap() {
        return hash;
    }
}
