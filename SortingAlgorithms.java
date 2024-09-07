import java.util.Arrays;

public class SortingAlgorithms {

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;

            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);
            mergeSort(right);

            merge(arr, left, right);
        }
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }


    // Método para medir o tempo de execução
    public static Pair<int[], Long> measureSortingTime(int[] arr, String algorithm) {
        int[] arrCopy = arr.clone();
        long startTime = System.nanoTime();

        switch (algorithm.toLowerCase()) {
            case "bubble":
                bubbleSort(arrCopy);
                break;
            case "insertion":
                insertionSort(arrCopy);
                break;
            case "merge":
                mergeSort(arrCopy);
                break;
            default:
                throw new IllegalArgumentException("Algoritmo de ordenação não reconhecido: " + algorithm);
        }

        long endTime = System.nanoTime();
        return new Pair<>(arrCopy, endTime - startTime);
    }

    // Método main para teste
    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Array original: " + Arrays.toString(arr));

        String[] algorithms = {"bubble", "insertion", "merge"};

        for (String algorithm : algorithms) {
            Pair<int[], Long> result = measureSortingTime(arr, algorithm);
            int[] sortedArr = result.getKey();
            long time = result.getValue();
            System.out.printf("%s Sort: %.6f segundos%n", algorithm, time / 1e9);
            System.out.println("Array ordenado: " + Arrays.toString(sortedArr));
        }
    }
}

