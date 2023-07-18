import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;

public class mySolver {

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {
        if (args.length > 0) {
            String filePath = args[0];
            File file = new File(filePath);
            char[][] test;
            if (file.isFile()) {
                try {
                    test = readFileToCharArray(file);
                    solveLab(test);
                    labPrint(test);
                } catch (IOException excep) {
                    excep.printStackTrace();
                }
            } else {
                System.out.println("L'argument n'est pas un fichier valide.");
            }
        } else {
            System.out.println("Aucun argument spécifié.");
        }
    }

    public static char[][] readFileToCharArray(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder stringBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(System.lineSeparator()); // Ajouter un séparateur de ligne
        }

        reader.close();

        String content = stringBuilder.toString();
        String[] lines = content.split(System.lineSeparator());

        char[][] charArray = new char[lines.length][];

        for (int i = 0; i < lines.length; i++) {
            charArray[i] = lines[i].toCharArray();
        }
        return charArray;
    }

    public static void labPrint(char[][] tableau2D) {
        for (int i = 0; i < tableau2D.length; i++) {
            for (int j = 0; j < tableau2D[i].length; j++) {
                System.out.print(tableau2D[i][j] + " ");
            }
            System.out.println(); // Nouvelle ligne après chaque ligne du tableau
        }
    }

    public static void solveLab(char[][] tableau2D) {
        boolean test;
        int z = 0;

        for (int x = 0; x < tableau2D.length; x++) {
            test = true;
            for (int y = 0; y < tableau2D[x].length; y++) {
                if (x < tableau2D.length - 1 && tableau2D[x][y] == 'x' && tableau2D[x + 1][y] != 'x' && test) {
                    tableau2D[x][y] = '1';
                    z = y;
                } else {
                    tableau2D[x][z + 1] = '1';
                    test = false;
                }
                if (y < tableau2D[x].length - 1 && tableau2D[x][y] == 'o' && tableau2D[x][y + 1] == 'x') tableau2D[x][y + 1] = '1';
            }
        }
    }
}

