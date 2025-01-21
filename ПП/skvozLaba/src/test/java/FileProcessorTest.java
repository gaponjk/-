import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class FileProcessorTest {
    private static Method processArithmeticOperationsWithRegex;
    private static Method processSqrt;
    public static FileProcessor fileProcessor;

    @BeforeAll
    static void starter() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        processArithmeticOperationsWithRegex = FileProcessor.class.getDeclaredMethod("processArithmeticOperationsWithRegex", String.class);
        processArithmeticOperationsWithRegex.setAccessible(true);
        processSqrt = FileProcessor.class.getDeclaredMethod("processSqrt", String.class);
        processSqrt.setAccessible(true);
        Constructor<FileProcessor> constructor = FileProcessor.class.getDeclaredConstructor(String.class, String.class);
        constructor.setAccessible(true);
        fileProcessor = constructor.newInstance("input.txt", "output.txt");
    }

    @Test
//tests simple operation
    void testProcessArithmeticOperationsWithRegex1() throws InvocationTargetException, IllegalAccessException {
        String result = (String) processArithmeticOperationsWithRegex.invoke(fileProcessor, "5 + 3 и 7 * 2");
        assertEquals("8 и 14", result);
    }

    @Test
//tests large operations
    void testProcessArithmeticOperationsWithRegex2() throws InvocationTargetException, IllegalAccessException {
        String result = (String) processArithmeticOperationsWithRegex.invoke(fileProcessor, "5 + 3 *4");
        assertEquals("17", result);
    }

    @Test
//tests ()
    void testProcessArithmeticOperationsWithRegex3() throws InvocationTargetException, IllegalAccessException {
        String result = (String) processArithmeticOperationsWithRegex.invoke(fileProcessor, "(5 + 3)+2 *4 и ( 7+3)*2");
        assertEquals("16 и 20", result);
        result = (String) processArithmeticOperationsWithRegex.invoke(fileProcessor, "(5 + 3) *4");
        assertEquals("32", result);
    }

    @Test
//tests double
    void testProcessArithmeticOperationsWithRegex4() throws InvocationTargetException, IllegalAccessException {
        String result = (String) processArithmeticOperationsWithRegex.invoke(fileProcessor, "5.6 + 3.3 *4");
        assertEquals("18,8", result);
    }

    @Test
//tests sqrt
    void testProcessSqrt() throws InvocationTargetException, IllegalAccessException {

        String result = (String) processSqrt.invoke(fileProcessor, "sqrt(25) + 3.3 *4");
        result = (String) processArithmeticOperationsWithRegex.invoke(fileProcessor, result);
        assertEquals("18,2", result);
    }
}