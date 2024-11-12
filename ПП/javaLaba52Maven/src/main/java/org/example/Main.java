package org.example;

import org.json.JSONArray;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Main {
    public static ArrayList<Company> companies = new ArrayList<>();
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static LinkedList<Company> resultArray = new LinkedList<>();
    private static StringBuilder input = new StringBuilder();

    public static Company findCompanyByShortName(String shortName) {
        String nameFind = shortName.toLowerCase().trim();
        for (Company company : companies) {
            if (company.getShortName().toLowerCase().equals(nameFind))
                return company;
        }
        return null;
    }

    public static ArrayList<Company> findCompanyByBranch(String branchName) {
        ArrayList<Company> result = new ArrayList<>();
        String nameFind = branchName.toLowerCase().trim();
        for (Company company : companies) {
            if (company.getBranch().toLowerCase().equals(nameFind)) {
                result.add(company);
            }
        }
        return result;
    }

    public static ArrayList<Company> findCompanyByActivity(String activityName) {
        ArrayList<Company> result = new ArrayList<>();
        String nameFind = activityName.toLowerCase().trim();
        for (Company company : companies) {
            if (company.getActivity().toLowerCase().equals(nameFind)) {
                result.add(company);
            }
        }
        return result;
    }

    public static ArrayList<Company> finCompanyByDateOfFoundation(LocalDate date1, LocalDate date2) {
        ArrayList<Company> result = new ArrayList<>();
        for (Company company : companies) {
            if (company.getDateFoundation().isAfter(date1) && company.getDateFoundation().isBefore(date2))
                result.add(company);
        }
        return result;
    }

    public static ArrayList<Company> findCompanyByNumberOfEmployees(Integer number1, Integer number2) {
        ArrayList<Company> result = new ArrayList<>();
        for (Company company : companies) {
            if (company.getCountEmployees() >= number1 && company.getCountEmployees() <= number2) {
                result.add(company);
            }
        }
        return result;
    }

    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
        String timeAtBeginning = getCurrentDateTime();
        try (BufferedReader reader = new BufferedReader(new FileReader("input.csv"));
             BufferedReader consoleRead = new BufferedReader(new InputStreamReader(System.in));
             FileWriter jsonWriter = new FileWriter("output.json");
             FileWriter fileWriter = new FileWriter("logfile.txt", true)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] elements = line.split(";");
                companies.add(new Company(
                        elements[0],
                        elements[1],
                        !elements[2].isEmpty() ? LocalDate.parse(elements[2], dateTimeFormatter) : LocalDate.now(),
                        elements[3],
                        !elements[5].isEmpty() ? (LocalDate.parse(elements[4], dateTimeFormatter)) : LocalDate.now(),
                        Integer.parseInt(!elements[5].isEmpty() ? elements[5] : "0"),
                        elements[6],
                        elements[7],
                        elements[8],
                        elements[9],
                        elements[10],
                        new URL(elements[11])
                ));
            }
            int a;
            boolean isntChoosen = false;
            do {
                System.out.println("Выберите:\n1. Найти компанию по краткому наименованию.\n" +
                        "2. Выбрать компании по отрасли.\n" +
                        "3. Выбрать компании по виду деятельности.\n" +
                        "4. Выбрать компании по дате основания в определенном промежутке (с и по). \n" +
                        "5. Выбрать компании по численности сотрудников в определенном промежутке (с и по).");
                a = Integer.parseInt(consoleRead.readLine().trim());
                input.append(String.valueOf(a)+" ");
                switch (a) {
                    case 1 -> {
                        System.out.println("Введите короткое наименование компании, которую вы ищите");
                        line=consoleRead.readLine();
                        resultArray.add(findCompanyByShortName(line));
                        input.append(line+' ');
                    }
                    case 2 -> {
                        System.out.println("Введите нужную вам отрасль");
                        line=consoleRead.readLine();
                        resultArray.addAll(findCompanyByBranch(line));
                        input.append(line+' ');
                    }
                    case 3 -> {
                        System.out.println("Введите нужный вам вид деятельности");
                        line=consoleRead.readLine();
                        resultArray.addAll(findCompanyByActivity(line));
                        input.append(line+' ');
                    }
                    case 4 -> {
                        while (true) {
                            System.out.println("Введите нужный вам промежуток в формате yyyy-MM-dd yyyy-MM-dd");
                            String read;
                            if (Pattern.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}-\\d{2}-\\d{2}", read = consoleRead.readLine().trim())) {
                                String[] dates = read.split(" ");
                                input.append(read);
                                resultArray.addAll(finCompanyByDateOfFoundation(LocalDate.parse(dates[0], dateTimeFormatter), LocalDate.parse(dates[1], dateTimeFormatter)));
                                break;
                            }
                            System.out.println("Попробуй ввести еще раз");
                        }
                    }
                    case 5 -> {
                        while (true) {
                            System.out.println("Введите нужный вам промежуток через пробел");
                            String read;
                            if (Pattern.matches("\\d+ \\d+", read = consoleRead.readLine())) {
                                String[] numbers = read.split(" ");
                                input.append(read);
                                resultArray.addAll(findCompanyByNumberOfEmployees(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
                                break;
                            }
                        }
                    }
                    default -> {
                        System.out.println("Попробуй ввести еще раз");
                        isntChoosen = true;
                    }
                }
            } while (isntChoosen);
            JSONArray jsonArray = new JSONArray();
            for (Company company : resultArray) {
                jsonArray.put(company.toJSON());
            }
            jsonWriter.write(jsonArray.toString(4));
            jsonWriter.flush();

            CompanyList companyList = new CompanyList(resultArray);
            JAXBContext context = JAXBContext.newInstance(CompanyList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(companyList, new File("output.xml"));
            fileWriter.write(timeAtBeginning + "\t");
            fileWriter.write(input.toString()+"\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            System.out.println("Ошибка связанная с ссылкой одной из компаний");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

}