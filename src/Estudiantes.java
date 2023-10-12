import java.io.RandomAccessFile;
import java.util.Scanner;
import java.io.IOException;

public class Estudiantes {
    private static final int ID_SIZE = 4; // bytes
    private static final int CHAR_SIZE = 2; // bytes por carácter en UTF-16
    private static final int NAME_SIZE = 50; // Longitud máxima en caracteres del nombre
    private static final int GRADE_SIZE = 4; // bytes para la nota (float)
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("./data/estudiantes.dat", "rw")) {
            boolean running = true;
            int id;
            float nota;

            while (running) {
                String menu = "Escull una opció:";
                menu = menu + "\n 1) AfegirEstudiant";
                menu = menu + "\n 2) ActualitzarNotaEstudiant";
                menu = menu + "\n 3) MostrarEstudiant";
                menu = menu + "\n 0) Sortir";
                System.out.println(menu);

                String opcio = sc.nextLine();
                try {
                    switch (opcio) {
                        case "1":
                            System.out.print("Introduce la Id del estudiante(Máximo 4 numeros): ");
                            id = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Introduce el Nombre del estudiante(Máximo 20 letras): ");
                            String nom = sc.nextLine();
                            System.out.print("Introduce la Nota del estudiante(Máximo 4 numeros): ");
                            nota = sc.nextFloat();
                            sc.nextLine();

                            afegirEstudiant(raf, nom, nota);
                            break;
                        case "2":
                            System.out.print("Introduce la Id del estudiante(Máximo 4 numeros): ");
                            id = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Introduce la Nota del estudiante(Máximo 4 numeros): ");
                            nota = sc.nextFloat();
                            sc.nextLine();
                            actualitzarNotaEstudiant(raf, id, nota);
                            break;
                        case "3":
                            System.out.print("Introduce la Id del estudiante(Máximo 4 numeros): ");
                            id = sc.nextInt();
                            sc.nextLine();
                            mostrarEstudiant(raf, id);
                            break;
                        case "0":
                            running = false;
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void afegirEstudiant(RandomAccessFile raf, String nom, float nota) throws IOException {
        // Obtén el último ID asignado y suma 1 para el nuevo estudiante
        int ultimoID = (int) (raf.length() / (ID_SIZE + NAME_SIZE * CHAR_SIZE + GRADE_SIZE)) + 1;
        raf.seek(raf.length());
        raf.writeInt(ultimoID);
        raf.writeChars(getPaddedName(nom));
        raf.writeFloat(nota);
    }

    public static void mostrarEstudiant(RandomAccessFile raf, int id) throws IOException {
        raf.seek(0); // Inicia la búsqueda desde el principio del archivo
        while (raf.getFilePointer() < raf.length()) {
            int readId = raf.readInt();
            if (readId == id) {
                String nom = readFixedString(raf, NAME_SIZE);
                float nota = raf.readFloat();
                System.out.println(readId + ": " + nom.trim() + " " + nota);
                return; // Se encontró el estudiante, no es necesario buscar más
            } else {
                // Avanza al siguiente estudiante en el archivo
                raf.skipBytes(NAME_SIZE * CHAR_SIZE + GRADE_SIZE);
            }
        }
        // Si llegamos aquí, el estudiante con el ID no se encontró
        System.out.println("Estudiante con ID " + id + " no encontrado.");
    }

    public static void actualitzarNotaEstudiant(RandomAccessFile raf, int id, float novaNota) throws IOException {
        raf.seek(0); // Inicia la búsqueda desde el principio del archivo
        while (raf.getFilePointer() < raf.length()) {
            int readId = raf.readInt();
            if (readId == id) {
                // Mueve el puntero a la posición de la nota y actualiza la nota
                raf.skipBytes(NAME_SIZE * CHAR_SIZE);
                raf.writeFloat(novaNota);
                System.out.println("Nota actualizada para el estudiante con ID " + id);
                return; // Se encontró el estudiante y se actualizó la nota
            } else {
                // Avanza al siguiente estudiante en el archivo
                raf.skipBytes(NAME_SIZE * CHAR_SIZE + GRADE_SIZE);
            }
        }
        // Si llegamos aquí, el estudiante con el ID no se encontró
        System.out.println("Estudiante con ID " + id + " no encontrado.");
    }

    private static String getPaddedName(String name) {
        if (name.length() > NAME_SIZE) {
            return name.substring(0, NAME_SIZE);
        }
        return String.format("%1$-" + NAME_SIZE + "s", name);
    }

    private static String readFixedString(RandomAccessFile raf, int size) throws IOException {
        StringBuilder sb = new StringBuilder(size);
        int i = 0;
        while (i < size) {
            char c = raf.readChar();
            sb.append(c);
            i++;
        }
        return sb.toString();
    }
}
