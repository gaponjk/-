import java.io.*;
import java.util.*;

//Гапоненко Ярослав Александрович 5 группа
/*Расстановка станков

Предприятие покупает станки. Известна стоимость S, мощность M каждого  и общее количество К

Нужно расстановить станки по мощности (как палиндром), количество станков дб четно. Но предприятие
экономит и покупает наименьшее количество станков, но необходимо иметь мощность всех = MN.
Площадь ограничено и она может вместить КL станков.

Определить количество  станков , их  характеристики и расстановку, общую стоимость покупки*/
//В файле input.in хранится информация о машинах
//Остальную информацию вводит пользователь
public class Main {
    private static int MN;
    private static int KL;
    private static int totalCost;
    private static ArrayList<Machine> machines=new ArrayList<>();
    private static ArrayList<Machine> selectedMachines=new ArrayList<>();
    public static void main(String[] args) {
        try (BufferedReader fileReader=new BufferedReader(new FileReader("input.in"));
             PrintStream printStream=new PrintStream("output.out");
             BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))){
                System.out.println("Enter the maximum Power required");
                MN=Integer.parseInt(reader.readLine().trim());
                System.out.println("Enter the maximum amount required");
                KL=Integer.parseInt(reader.readLine().trim());
                String line=null;
                while((line=fileReader.readLine())!=null){
                    String[]two=line.trim().split(" ");
                    machines.add(new Machine(Integer.parseInt(two[0]),Integer.parseInt(two[1])));
                }
                selectedMachines=getSelectedMachines(machines);
            if (selectedMachines != null) {
                totalCost = selectedMachines.stream().mapToInt(m -> m.getCost()).sum();
                printStream.println("Total machines: " + selectedMachines.size());
                printStream.println("Total cost: " + totalCost);
                printStream.println("Machine arrangement: ");
                selectedMachines.forEach(printStream::println);
            } else {
                System.out.println("No valid arrangement found.");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static ArrayList<Machine> getSelectedMachines(ArrayList<Machine>arrayList){
        arrayList.sort(Comparator.comparing(m->m.getCost()));
        for (int i = 2; i <=KL && i<=arrayList.size(); i+=2) {
            int totalPower=0;
            ArrayList<Machine> selected=new ArrayList<>();
            for (Machine machine:arrayList){
                if(selected.size()>=i)
                    break;
                selected.add(machine);
                totalPower+=machine.getPower();
            }
            selected=formPalindrome(selected);

            if(totalPower>=MN&&selected!=null)
                    return selected;
        }
        return null;
    }

    public static ArrayList<Machine> formPalindrome(ArrayList<Machine> array) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Machine> powerToMachine = new HashMap<>();
        ArrayList<Machine> part = new ArrayList<>();

        for (Machine machine : array) {
            int power = machine.getPower();
            countMap.put(power, countMap.getOrDefault(power, 0) + 1);
            powerToMachine.putIfAbsent(power, machine);
        }
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int power = entry.getKey();
            int count = entry.getValue();

            if (count % 2 == 1)
                    return null;

            Machine machine = powerToMachine.get(power);
            for (int i = 0; i < count / 2; i++) {
                part.add(machine);
            }
        }
        ArrayList<Machine> reversePart = new ArrayList<>(part);
        Collections.reverse(reversePart);

        part.addAll(reversePart);
        return part;
    }
}