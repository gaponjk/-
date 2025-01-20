import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {

    @Test
    public void testAdd() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<FileProcessor> constructor = FileProcessor.class.getDeclaredConstructor(String.class, String.class);
        constructor.setAccessible(true);

        FileProcessor fileProcessor = constructor.newInstance("input.txt", "output.txt");
        Method method=FileProcessor.class.getDeclaredMethod("processArithmeticOperationsWithRegex",String.class);
        method.setAccessible(true);
        String input = "5 + 3 и 7 * 2";
        String result = (String) method.invoke(fileProcessor, input);

        assertEquals("8 и 14", result);
    }
}