import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//Гапоненко Ярослав Александрович 5 группа
/*Входной файл input1.html содержит текст, написанный на языке HTML.
В тесте находятся теги. В одной строке может быть несколько тегов. Теги находятся в угловых скобках, каждому открывающему тегу ставится в соответствие закрывающий тег.
Например, пара тегов<b></b>.
Между тегами находится текст, причем теги не разрывают текст. Например, при поиске слова hello комбинация h<b><i>el</i>l</b>o должна быть найдена.
Гарантируется, что страница HTML является корректной, т.е. все символы "<" и ">" используются только в тегах, все теги записаны корректно.
Входной файл input2.in содержит список фрагментов текста, которые нужно найти в первом файле, записанных через разделители (точка с запятой). Может быть несколько строк.

Примечание: Ваша программа должна игнорировать различие между строчными и прописными буквами и для тегов и для искомого контекста.

Выходные данные:
1. В выходной файл output1.out вывести список всех тегов в порядке возрастания количества символов тега.
2. В выходной файл output2.out вывести номера строк (нумерация с 0) первого файла, в которых был найден искомый контекст в первый раз или -1 , если не был найден.
3. В выходной файл output3.out - список фрагментов второго файла, которые НЕ были найдены в первом файле.
*/
public class javaLaba42 {
    private static List<String> stringsInput2 = new ArrayList<>();
    private static Set<String> tags = new HashSet<>();
    private static Pattern patternHtml = Pattern.compile("<[^>]+>");
    private static Map<String, Integer> foundLines = new HashMap<>();
    public static void main(String[] args) {
        try (BufferedReader readerInput1 = new BufferedReader(new FileReader("input1.html"));
             BufferedReader readerInput2 = new BufferedReader(new FileReader("input2.in"));
             PrintStream streamOutput1 = new PrintStream("output1.out");
             PrintStream streamOutput2 = new PrintStream("output2.out");
             PrintStream streamOutput3=new PrintStream("output3.out")) {
            while (readerInput2.ready()) {
                String[] strings = readerInput2.readLine().toLowerCase().split("\\s*;\\s*");
                Collections.addAll(stringsInput2, strings);
            }
            String line;
            int lineNumber = 0;

            while ((line = readerInput1.readLine()) != null) {
                line = line.toLowerCase();
                Matcher matcherHtml = patternHtml.matcher(line);

                while (matcherHtml.find()) {
                    tags.add(matcherHtml.group());
                }

                for (String fragment : stringsInput2) {
                    if (foundLines.containsKey(fragment))
                        continue;


                    if (line.contains(fragment)) {
                        foundLines.put(fragment, lineNumber);
                    }
                }

                lineNumber++;
            }

            List<String> sortedTags = new ArrayList<>(tags);
            Collections.sort(sortedTags, Comparator.comparingInt(String::length));
            sortedTags.forEach(streamOutput1::println);

            for (String fragment : stringsInput2) {
                if(foundLines.containsKey(fragment)){
                    streamOutput2.println(foundLines.get(fragment));
                }else{
                    streamOutput2.println(-1);
                    streamOutput3.println(fragment);
                }
            }
            System.out.println("Посмотрите файлы output1.out,output2.out,output3.out");
        } catch (FileNotFoundException e){
            System.out.println("Проверьте создали ли вы в папке файлы input1.html,input2.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}