//Alunos: Jhénifer Matos de Mendonça Pereira e Sidney Kenzo Goya Miyassato

import java.io.FileWriter;
import java.io.IOException;

public class ExperimentRuner {

    private static final String[] ALGORITHMS = {"bubble", "insertion", "merge", "heap", "quick", "count"};

    public static void runExperiments(int inc, int fim, int stp, int rpt) {
        System.out.println("[[RANDOM]]");
        printHeader();
        for (int size = inc; size <= fim; size += stp) {
            runRandomExperiment(size, rpt);
        }

        System.out.println("\n[[REVERSE]]");
        printHeader();
        for (int size = inc; size <= fim; size += stp) {
            runSingleExperiment(size, "reverse");
        }

        System.out.println("\n[[SORTED]]");
        printHeader();
        for (int size = inc; size <= fim; size += stp) {
            runSingleExperiment(size, "sorted");
        }

        System.out.println("\n[[NEARLY SORTED]]");
        printHeader();
        for (int size = inc; size <= fim; size += stp) {
            runSingleExperiment(size, "nearlySorted");
        }
    }

    private static void printHeader() {
        System.out.printf("%-10s", "n");
        for (String algorithm : ALGORITHMS) {
            System.out.printf("%-15s", algorithm);
        }
        System.out.println();
        System.out.println("-".repeat(123));
    }

    private static void runRandomExperiment(int size, int rpt) {
        System.out.printf("%-10d", size);
        for (String algorithm : ALGORITHMS) {
            long totalTime = 0;
            for (int i = 0; i < rpt; i++) {
                int[] arr = ArrayGenerators.generateRandomArray(size);
                Pair<int[], Long> result = SortingAlgorithms.measureSortingTime(arr, algorithm);
                totalTime += result.getValue();

            }
            double averageTime = totalTime / (double) rpt / 1e9; // Converte para segundos
            System.out.printf("%-15.6f", averageTime);
            
            try { //cria um arquivo .dat para criar um gráfico no gnuplot
                // o primeiro número de cada linha é o eixo X
                //representando o tamanho da entrada
                // o segundo número é o eixo Y, representando o tempo
                FileWriter writer = new FileWriter(algorithm+"-random.dat", true); 
                String newLine = System.lineSeparator(); //separar os diferentes dados
                writer.write(size + " ");
                writer.write(Double.toString(averageTime) + newLine);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
                
        }
        System.out.println();
    }

    private static void runSingleExperiment(int size, String arrayType) {
        System.out.printf("%-10d", size);
        int[] arr;
        switch (arrayType) {
            case "reverse":
                arr = ArrayGenerators.generateReverseArray(size);
                break;
            case "sorted":
                arr = ArrayGenerators.generateSortedArray(size);
                break;
            case "nearlySorted":
                arr = ArrayGenerators.generateNearlySortedArray(size);
                break;
            default:
                throw new IllegalArgumentException("Tipo de array inválido");
        }

        for (String algorithm : ALGORITHMS) {
            Pair<int[], Long> result = SortingAlgorithms.measureSortingTime(arr, algorithm);
            long time = result.getValue();
            double averageTime = time / 1e9;
            System.out.printf("%-15.6f", time / 1e9); // Converte para segundos
        
            try { //cria um arquivo .dat para criar um gráfico no gnuplot
                // o primeiro número de cada linha é o eixo X
                //representando o tamanho da entrada
                // o segundo número é o eixo Y, representando o tempo
                FileWriter writer = new FileWriter(algorithm+"-"+arrayType+".dat", true); 
                String newLine = System.lineSeparator(); //separar os diferentes dados
                writer.write(size + " ");
                writer.write(Double.toString(averageTime) + newLine);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        runExperiments(1000, 20000, 1000, 10);
    }
}
