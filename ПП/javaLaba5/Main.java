import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static class NameComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact c1, Contact c2) {
            return c1.getName().compareTo(c2.getName());
        }
    }
    public static class MobileComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact c1, Contact c2) {
            return new BigInteger(c1.getMobilePhone()).compareTo(new BigInteger(c2.getMobilePhone()));
        }
    }
    public static class WorkComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact c1, Contact c2) {
            return new BigInteger(c1.getWorkPhone()).compareTo(new BigInteger(c2.getWorkPhone()));
        }
    }
    public static class HomeComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact c1, Contact c2) {
            return new BigInteger(c1.getHomePhone()).compareTo(new BigInteger(c2.getHomePhone()));
        }
    }
    public static class EmailComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact c1, Contact c2) {
            return c1.getEmail().compareTo(c2.getEmail());
        }
    }
    public static class WebSiteComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact c1, Contact c2) {
            return c1.getWebSite().compareTo(c2.getWebSite());
        }
    }
    public static class AddressComparator implements Comparator<Contact> {
        @Override
        public int compare(Contact c1, Contact c2) {
            return c1.getAddress().compareTo(c2.getAddress());
        }
    }
    private static ArrayList<Contact> contacts=new ArrayList<>();
    public static void main(String[] args) {
            try(BufferedReader fileReader=new BufferedReader(new FileReader("input.in"));
            BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))){
                String line=null;
                while((line=fileReader.readLine())!=null){
                    contacts.add(Contact.fromString(line));
                }
                System.out.println("Выбери как сортировать:\n- 1.Наименование (имя человека или организации)\n" +
                        "- 2.Номер телефона мобильного\n" +
                        "- 3.Номер телефона рабочего\n" +
                        "- 4.Номер телефона (домашнего)\n" +
                        "- 5.Адрес электронной почты\n" +
                        "- 6.Адрес веб-страницы\n" +
                        "- 7.Адрес\n");
                int a=Integer.parseInt(reader.readLine());
                Comparator<Contact> obj=null;
                switch (a){
                    case 1->{
                        obj=new NameComparator();
                    }
                    case 2->{
                        obj=new MobileComparator();
                    }
                    case 3->{
                        obj=new WorkComparator();
                    }
                    case 4->{
                        obj=new HomeComparator();
                    }
                    case 5->{
                        obj=new EmailComparator();
                    }
                    case 6->{
                        obj=new WebSiteComparator();
                    }
                    case 7->{
                        obj=new AddressComparator();
                    }
                }
                Collections.sort(contacts,obj);
                for(Contact contact:contacts){
                    System.out.println(contact);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}