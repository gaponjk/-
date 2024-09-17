import java.io.*;
import java.util.ArrayList;
//Гапоненко Ярослав Александрович 5 группа
/*1.	Строка состоит из слов. За один просмотр символов строки найти все самые длинные слова,
символы в которых идут в строгом возрастании кодов, и занести их в новую строку. Слова в исходной
строке разделяются некоторым множеством разделителей. Слова в новой строке должны разделяться ровно одним пробелом. */
public class MainLaba31 {
    private static String firstLine;//для считывания
    private static String[] firstWords;//массив разделенных чисел
    private static ArrayList<String> secondWords=new ArrayList<>();//результат
    private static int maxLength;
    public static void main(String[] args) {
        try(BufferedReader reader=new BufferedReader(new FileReader("inputLaba31.txt"))) {
             firstLine=reader.readLine();
             firstWords=firstLine.split("[\\p{P}\\s]+");
             for (String word:firstWords){
                 boolean isSequence=true;
                 char c=0;
                 char[]chars=word.toCharArray();
                 for (int i = 0; i < word.length(); i++) {
                     if(chars[i]<=c) {
                         isSequence=false;
                         break;
                     }
                     c=chars[i];
                 }
                 if (isSequence&&maxLength<=word.length()){
                     maxLength=word.length();
                     if(!secondWords.isEmpty()&&secondWords.get(secondWords.size()-1).length()<word.length())
                         secondWords.clear();
                     secondWords.add(word);
                 }

             }
            System.out.println("Самые длинные слова, символы в которых идут в строгом возрастании кодов:");
            secondWords.forEach((c)->System.out.printf(c+" "));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}