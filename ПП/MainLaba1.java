import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
//Гапоненко Ярослав Александрович 5 группа
//Лабараторная 1
public class Main {
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Number must be non-negative");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
    public static BigDecimal expCount(BigDecimal xo, BigDecimal eo){
        BigDecimal num= new BigDecimal(1);
        BigDecimal result= new BigDecimal(0);
        int i=0;

        while(num.compareTo(eo)>0){
            num = xo.pow(i).divide(new BigDecimal(factorial(i)), new MathContext(16, RoundingMode.HALF_UP));
            result = result.add(num,new MathContext(16, RoundingMode.HALF_UP));
            i++;
        }
        return result;
    }
    public static double expCount(double x, double e ){
        double num=1,result=0;
        int i=0;

        while(num>e){
            num=Math.pow(x,i)/factorial(i);
            result+=num;
            i++;
        }
        return result;
    }
    public static void main(String[] args) {
    try (BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))){
    String number=reader.readLine();
    String[] numbers=number.split(" ");
    int x=Integer.parseInt(numbers[0]);
    double e=Math.pow(10,Integer.parseInt(numbers[1])*-1);

    BigDecimal xo= BigDecimal.valueOf(x);
    BigDecimal eo= BigDecimal.valueOf(e);

    System.out.println("Встроенный метод: "+Math.exp(x));
    System.out.println("Через BigDecimal: "+expCount(xo,eo));//eo, xo -- BigDecimal
    System.out.println("Через обычный double: "+expCount(x,e));// e, x -- double
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    }
}