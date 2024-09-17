import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Гапоненко Ярослав Александрович 5 группа
//Я задавал ячейкам массива рандомные значения от 0 до 10000
/*1.	Поменять местами строку, содержащую элемент с наибольшим значением в матрице со строкой,
содержащей элемент с наименьшим значением. Вывести на экран получен¬ную матрицу. Для каждой строки
с нулевым элементом на главной диагонали вывести ее  номер  и значение наибольшего из элементов этой строки.
15.	Для заданной целочисленной матрицы найти максимум среди сумм элементов диагоналей, параллельных главной диагонали матрицы.
29.	Упорядочить ее строки по неубыванию их наибольших элементов. */
public class MainLaba2 {
    private static int width;
    private static int length;
    private static int[][]arr;
    private static ArrayList<ArrayList<Integer>> arrayList;
    private static Random random=new Random();

    public static void printMatrix(int[][] arr){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                System.out.printf(String.format("%d ",arr[i][j]));
            }
            System.out.println();
        }
    }

    public static void task1(){
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        int iMax=0;
        int iMin=0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (max<arr[i][j]) {
                    max = arr[i][j];
                    iMax=i;
                }
                if(min>arr[i][j]){
                    min=arr[i][j];
                    iMin=i;
                }
            }
        }
        for (int j = 0; j < length; j++) {
            int temp=arr[iMin][j];
            arr[iMin][j]=arr[iMax][j];
            arr[iMax][j]=temp;
        }
    }

    public static int task15() {
        int maxSum = Integer.MIN_VALUE;
        HashMap<Integer, Integer> diagonalSums = new HashMap<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                int diagonalKey = i - j;
                diagonalSums.put(diagonalKey, diagonalSums.getOrDefault(diagonalKey, 0) + arrayList.get(i).get(j));
            }
        }
        for (int sum : diagonalSums.values()) {
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }

    public static int[][] task29() {
        Integer[] indices = new Integer[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(findMax(arrayList.get(a)), findMax(arrayList.get(b)));
            }
        });

        int[][] sortedMatrix = new int[arrayList.size()][];
        for (int i = 0; i < indices.length; i++) {
            List<Integer> row = arrayList.get(indices[i]);
            sortedMatrix[i] = row.stream().mapToInt(Integer::intValue).toArray();
        }
        return sortedMatrix;
    }
    private static Integer findMax(ArrayList<Integer> row) {
        int max = Integer.MIN_VALUE;
        for (int value : row) {
            max = Math.max(max, value);
        }
        return max;
    }

    public static void main(String[] args) {
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите ширину матрицы: ");
            width=Integer.parseInt(reader.readLine().trim());
            System.out.println("Введите длину матрицы: ");
            length= Integer.parseInt(reader.readLine().trim());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            arr=new int[width][length];
            arrayList=new ArrayList<>(width);
            System.out.println();
            System.out.println();
            for (int i = 0; i < width; i++) {
                ArrayList<Integer>arrayTemp=new ArrayList<>();
                for (int j = 0; j < length; j++) {
                    int rand=random.nextInt(0,100);
                    arr[i][j]= rand;
                    arrayTemp.add(rand);
                }
                arrayList.add(arrayTemp);
            }
           // arr[1][1]=0; arr[2][2]=0; ИСПОЛЬЗОВАТЬ ДЛЯ ПРОВЕРКИ ОДНОГО ИЗ УСЛОВИЙ ПЕРВОГО ЗАДАНИЯ КОГДА РАЗМЕР БОЛЬШЕ ИЛИ РАВЕН 3
            System.out.println("Изначальная матрица:");
            System.out.println();
            printMatrix(arr);
            System.out.println();
            System.out.println();

            System.out.println("Задание 1: ");
            System.out.println();
            task1();
            System.out.println("После того, как строка с наименьшей ячейкой поменялась с наибольшой ячейкой:");
            System.out.println();
            printMatrix(arr);
            System.out.println();
            System.out.println();
            int i=0;
            while(i<width&&i<length){
               if (arr[i][i]==0) {
                   int max=Integer.MIN_VALUE;
                   for (int j = 0; j < arr[i].length; j++) {
                    if(max<arr[i][j])
                        max=arr[i][j];
                   }
                   System.out.printf(String.format("Строка с нулевой ячейкой после изменений матрицы под номером %d с максималным элементом равным %d ", i+1, max));
                   System.out.println();
               }
               ++i;
            }

            System.out.println("Задание 15:");
            System.out.println();
            System.out.println("Максимум среди сумм элементов диагоналей, " +
                "параллельных главной диагонали матрицы: "+task15());
            System.out.println();

        System.out.println("Задание 29:");
        System.out.println();
        System.out.println("Упорядоченные строки по неубыванию их наибольших элементов: ");
        System.out.println();
        printMatrix(task29());
    }
}