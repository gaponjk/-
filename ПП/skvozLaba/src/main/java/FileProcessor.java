import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProcessor {
    private final String inputFilePath;
    private final String outputFilePath;

    private FileProcessor(Builder builder) {
        this.inputFilePath = builder.inputFilePath;
        this.outputFilePath = builder.outputFilePath;
    }
    private FileProcessor(String input,String output) {
        this.inputFilePath = input;
        this.outputFilePath = output;
    }

    public void execute() {
        try {
            String inputData = readFromFile(inputFilePath);

            String processedData = processArithmeticOperationsWithRegex(inputData);

            writeToFile(outputFilePath, processedData);

            ConsoleHelper.writeMessage("Обработка завершена. Данные записаны в " + outputFilePath);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Ошибка при работе с файлами: " + e.getMessage());
        }
    }

    private String readFromFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    private void writeToFile(String filePath, String data) throws IOException {
        Files.writeString(Path.of(filePath), data);
    }

    private String processArithmeticOperationsWithRegex(String data) {
        Pattern pattern = Pattern.compile("(\\d+\\s*[+\\-*/]\\s*\\d+)");
        Matcher matcher = pattern.matcher(data);

        StringBuilder result = new StringBuilder();
        DecimalFormat decimalFormat=new DecimalFormat("#.###");
        while (matcher.find()) {
            String expression = matcher.group();
            double evalResult = evaluate(expression);

            matcher.appendReplacement(result, String.valueOf(decimalFormat.format(evalResult)));
        }

        matcher.appendTail(result);
        return result.toString();
    }

    private double evaluate(String expression) {
        expression = expression.replaceAll("\\s+", "");
        char operator = ' ';

        if (expression.contains("+")) operator = '+';
        else if (expression.contains("-")) operator = '-';
        else if (expression.contains("*")) operator = '*';
        else if (expression.contains("/")) operator = '/';

        String[] operands = expression.split("[+\\-*/]");
        double num1 = Double.parseDouble(operands[0]);
        double num2 = Double.parseDouble(operands[1]);

        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }

    public static class Builder {
        private String inputFilePath;
        private String outputFilePath;

        public Builder setInputFilePath(String inputFilePath) {
            this.inputFilePath = inputFilePath;
            return this;
        }

        public Builder setOutputFilePath(String outputFilePath) {
            this.outputFilePath = outputFilePath;
            return this;
        }

        public FileProcessor build() {
            if (inputFilePath == null || outputFilePath == null) {
                throw new IllegalStateException("Не указаны пути для входного или выходного файла!");
            }
            return new FileProcessor(this);
        }
    }
}

