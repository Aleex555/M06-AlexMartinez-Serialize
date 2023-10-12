import java.io.RandomAccessFile;

public class prueba {
    private static final int ID_SIZE = 4; // bytes
    private static final int NOTA = 4;
    private static final int CHAR_SIZE = 2; // bytes por carácter en UTF-16
    private static final int NAME_SIZE = 20; // Longitud máxima en caracteres del nombre

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("./data/estudiantes.dat", "rw")) {
            afegirEstudiante(raf, 1, "Estudiante1", 85.5f);
            afegirEstudiante(raf, 2, "Estudiante2", 92.0f);
            mostrarEstudiante(raf, 1);
            mostrarEstudiante(raf, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void afegirEstudiante(RandomAccessFile raf, int id, String nom, float nota) throws Exception {
        raf.seek(raf.length());
        raf.writeInt(id);
        raf.writeUTF(nom);
        raf.writeFloat(nota);
        raf.writeBytes("\r\n");
    }

    public static float consultarNotaEstudiante(RandomAccessFile raf, int id) throws Exception {
        raf.seek(getSeekPosition(id));
        raf.readInt(); // Saltar el ID
        raf.readUTF(); // Saltar el nombre
        float nota = raf.readFloat();
        return nota;
    }

    public static void mostrarEstudiante(RandomAccessFile raf, int id) throws Exception {
        long seekPosition = getSeekPosition(id);
        if (seekPosition >= 0 && seekPosition < raf.length()) {
            raf.seek(seekPosition);
            int idEstudiante = raf.readInt();
            String nombre = raf.readUTF();
            float nota = raf.readFloat();
            System.out.println("ID: " + idEstudiante + ", Nombre: " + nombre + ", Nota: " + nota);
        } else {
            System.out.println("Estudiante con ID " + id + " no encontrado.");
        }
    }
    

    private static long getSeekPosition(int id) {
        return (id - 1) * (ID_SIZE + NAME_SIZE * CHAR_SIZE + NOTA);
    }
}

