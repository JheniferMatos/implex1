//Alunos: Jhénifer Matos de Mendonça Pereira e Sidney Kenzo Goya Miyassato

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

    //Counting Sort
    private static void countSort(int[] arr) {
        int n = arr.length;
        int auxiliar = 0;
        int[] arrayauxiliar2 = new int[n];
        for (int i = 0; i < n; i++) {
            auxiliar = Math.max(auxiliar, arr[i]);
        }
        int[] arrayauxiliar = new int[auxiliar + 1];
        for (int i = 0; i < n; i++) {
            arrayauxiliar[arr[i]]++;
        }
        for (int i = 1; i <= auxiliar; i++) { //loop para fazer a soma cumulativa
            arrayauxiliar[i] += arrayauxiliar[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            arrayauxiliar2[arrayauxiliar[arr[i]] - 1] = arr[i];
            arrayauxiliar[arr[i]]--;
        }
        arr = arrayauxiliar2.clone();
    }

    //Heap Sort
    private static void heapSort(int arr[]) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int auxiliar = arr[0];
            arr[0] = arr[i];
            arr[i] = auxiliar;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int arr[], int n, int i) {
        int raiz = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        if (esq < n && arr[esq] > arr[raiz]) {
            raiz = esq;
        }
        if (dir < n && arr[dir] > arr[raiz]) {
            raiz = dir;
        }
        if (raiz != i) {
            int auxiliar = arr[i];
            arr[i] = arr[raiz];
            arr[raiz] = auxiliar;
            heapify(arr, n, raiz); //chamada recursiva para garantir a propriedade da max-heap
        }
    }
    

    //QuickSort
    /* 
    private static void quickSort(int arr[]) {
        divideQuickSort(arr, 0, arr.length - 1);
    }
    
    private static void divideQuickSort(int[] arr, int low, int high){
        
        if (low < high) {
            int pi = conquistaQuickSort(arr, low, high);
            divideQuickSort(arr, low, pi - 1);
            divideQuickSort(arr, pi + 1, high);
        }
        
    }

    private static int conquistaQuickSort(int[] arr, int esq, int dir) {
        int pivot = arr[dir];
        int i = (esq - 1);
        for (int j = esq; j <= dir - 1; j++) {
            if (arr[j] < pivot) {
                i++;
                troca(arr, i, j);
            }
        }
        troca(arr, i + 1, dir);
        return (i + 1);
    }

    private static void troca(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    */

    private static void quickSort(int[] arr){
        divideQuickSort (arr, 0, arr.length-1);
    }

    private static void divideQuickSort(int[] arr, int inicioVet, int fimVet){
        int i, j, pivot, auxiliar;
        i = inicioVet;
        j = fimVet;
        pivot = arr[(inicioVet + fimVet) / 2];
        while(i <= j){
            while(arr[i] < pivot){
                i++;
            }
            while(arr[j] > pivot){
                j--;
            }
            if(i <= j){
                auxiliar = arr[i];
                arr[i] = arr[j];
                arr[j] = auxiliar;
                i++;
                j--;
            }
        }
        if(inicioVet < j){
            divideQuickSort(arr, inicioVet, j);
        }
        if(fimVet > i){
            divideQuickSort(arr, i, fimVet);
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
            case "count":
                countSort(arrCopy);
                break;
            case "heap":
                heapSort(arrCopy);
                break;
            case "quick":
                quickSort(arrCopy);
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

        String[] algorithms = {"bubble", "insertion", "merge", "count", "heap", "quick"};

        for (String algorithm : algorithms) {
            Pair<int[], Long> result = measureSortingTime(arr, algorithm);
            int[] sortedArr = result.getKey();
            long time = result.getValue();
            System.out.printf("%s Sort: %.6f segundos%n", algorithm, time / 1e9);
            System.out.println("Array ordenado: " + Arrays.toString(sortedArr));
        }
    }
}
