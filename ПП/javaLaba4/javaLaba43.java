import java.io.*;
import java.util.ArrayList;
//Гапоненко Ярослав Александрович 5 группа
/*Для выполнения заданий использовать регулярные выражения.
Каждое задание реализовать в отдельном методе.
Строку получать из текстового файла inputjavaLaba43.txt.
Результат работы методов записывать в выходной текстовый файл output.txt.

    1. Из заданной строки исключить символы, расположенные внутри круглых скобок. Сами скобки тоже должны быть исключены.

    2. Из заданной строки удалить из каждой группы идущих подряд цифр, в которой более двух цифр, все цифры, начиная с третьей.

    3. Из заданной строки удалить из каждой группы идущих подряд цифр все незначащие нули.
*/
public class javaLaba43 {
    private static ArrayList<String>arrayList=new ArrayList<>();
    private static String firstTask(String line){
        return line.replaceAll("\\(.*\\)","");
    }
    private static String secondTask(String line){
        return line.replaceAll("(\\d{2})\\d*","$1");
    }
    private static String thirdTask(String line) {
        return line.replaceAll("\\b0+(?!\\b)", "");
    }
    public static void main(String[] args) {
        try(BufferedReader reader=new BufferedReader(new FileReader("inputjavaLaba43.txt"));
            PrintStream stream=new PrintStream("output.txt")) {
            String line=null;
            stream.println("Первое задание:");
            while((line=reader.readLine())!=null){
                arrayList.add(line.trim());
                stream.println(firstTask(line.trim()));
            }
            stream.println();
            stream.println();
            stream.println("Второе задание");
            arrayList.forEach((s)->stream.println(secondTask(s.trim())));
            stream.println();
            stream.println();
            stream.println("Третье задание:");
            arrayList.forEach((s)->stream.println(thirdTask(s.trim())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}