import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Labyrinth {
    public static void main(String[] args) {
        char[][] tab;
        Scanner scanner = new Scanner(System.in);

        if (args.length == 0 || args[0].equals("help")) options();
        else if (args.length == 3 && args[2].equals("simpleAlgo")) {
            tab = labGenerator(args);
            tab = littleAlgo(tab, args);
            labPrint(tab);
            System.out.print("\nQUIT or SAVE : ");
            String line = scanner.nextLine();
            if (line.equals("QUIT")) System.exit(0);
            if (line.equals("SAVE")) saveSystem(tab);
        }
        else System.out.print("Usage : java Labyrinth help, for more information.");
    }

    public static void saveSystem(char[][] tableau) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("map.txt"))) {
            for (int i = 0; i < tableau.length; i++) {
                writer.write(tableau[i]);
                writer.newLine();
            }
            System.out.println("Le tableau a été copié dans le fichier avec succès.");
        } catch (IOException e) {
            System.out.println("Une erreur s'est produite lors de la copie du tableau dans le fichier.");
            e.printStackTrace();
        }
    }

    public static void options() {
        System.out.print("Usage : java Labyrinth X Y [ALGORITHM]\nAlgorithm : simpleAlgo");
    }

    public static char[][] labGenerator(String[] arguments) {
        int x = Integer.parseInt(arguments[0]);
        int y = Integer.parseInt(arguments[1]);
        char[][] tableau2D = new char[x][y];

        for (int i = 0; i < tableau2D.length; i++) {
            for (int j = 0; j < tableau2D[i].length; j++) {
                if (i% 2 == 1) tableau2D[i][j] = 'o';
                else tableau2D[i][j] = 'x';
            }
        }
        return(tableau2D);
    }

    public static char[][] littleAlgo(char[][] tableau2D, String[] arguments) {

        Random random = new Random();
        int min = 1;
        int max = Integer.parseInt(arguments[1]) - 1;
        int randomInRange = 0;

        for (int i = 0; i < tableau2D.length; i++) {
            randomInRange = random.nextInt(max - min + 1) + min;
            for (int j = 0; j < tableau2D[i].length; j++) {
                if (i% 2 == 1) {
                    tableau2D[i][randomInRange] = 'x';
                }
            }
            randomInRange = 0;
        }
        return (tableau2D);
    }

    public static void labPrint(char[][] tableau2D ) {
        for (int i = 0; i < tableau2D.length; i++) {
            for (int j = 0; j < tableau2D[i].length; j++) {
                System.out.print(tableau2D[i][j] + " ");
            }
            System.out.println(); // Nouvelle ligne après chaque ligne du tableau
        }
    }
}