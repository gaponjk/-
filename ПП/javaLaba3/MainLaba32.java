import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//Гапоненко Ярослав Александрович 5 группа
/*1.	Строки текстового файла inputLaba32.txt состоят из слов, разделенных одним или
несколькими пробелами. Перед первым, а также после последнего слова строки пробелы могут отсутствовать.
Требуется определить слово, которое чаще всего встречается в файле. Результат вывести на консоль в форме, удобной для чтения.*/
public class MainLaba32 {
    private static ArrayList<String> lines=new ArrayList<>();
    private static ArrayList<String> allWords=new ArrayList<>();
    private static int mostFrequentAmount=0;
    public static String mostFrequentString=null;
    public static void main(String[] args) {
        try(BufferedReader reader=new BufferedReader(new FileReader("inputLaba32.txt"))) {
            while(reader.ready()){
                lines.add(reader.readLine().trim());
            }
            for (String line:lines){
                String[]words=line.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    allWords.add(words[i].trim());
                }
            }
            Map<String, Integer> frequencyMap = new HashMap<>();
            for (String string : allWords) {
                frequencyMap.put(string, frequencyMap.getOrDefault(string, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                if (entry.getValue() > mostFrequentAmount) {
                    mostFrequentString = entry.getKey();
                    mostFrequentAmount = entry.getValue();
                }
            }
            System.out.println(String.format("Строка %s с количеством упоминаний %d",mostFrequentString, mostFrequentAmount));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}