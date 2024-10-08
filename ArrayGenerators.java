//Alunos: Jhénifer Matos de Mendonça Pereira e Sidney Kenzo Goya Miyassato

import java.util.Arrays;
import java.util.Random;

public class ArrayGenerators {

    //aleatorio
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        int upperBound = size * size;
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(upperBound + 1);
        }
        return arr;
    }

    //reverso
    public static int[] generateReverseArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    //ordenado
    public static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    //quase ordenado
    public static int[] generateNearlySortedArray(int size) {
        int[] arr = generateSortedArray(size);
        Random rand = new Random();
        int swaps = size / 10;
        for (int i = 0; i < swaps; i++) {
            int pos1 = rand.nextInt(size);
            int pos2 = rand.nextInt(size);
            int temp = arr[pos1];
            arr[pos1] = arr[pos2];
            arr[pos2] = temp;
        }
        return arr;
    }

    //Exemplos de saida
    public static void main(String[] args) {
        int[] randomArray = generateRandomArray(50);
        System.out.println("Aleatório: " + Arrays.toString(randomArray));

        int[] reverseArray = generateReverseArray(50);
        System.out.println("Reverso: " + Arrays.toString(reverseArray));

        int[] sortedArray = generateSortedArray(50);
        System.out.println("Ordenado: " + Arrays.toString(sortedArray));

        int[] nearlySortedArray = generateNearlySortedArray(50);
        System.out.println("Quase Ordenado: " + Arrays.toString(nearlySortedArray));
    }
}
