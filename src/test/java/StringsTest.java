import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import string.Strings;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void isBlank_test1(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    //@EmptySource
    @NullAndEmptySource // 4 cas de tests
    @ValueSource(strings = {"", " "})
    void isBlank_test2(String input) {
        assertTrue(Strings.isBlank(input));
    }

    @ParameterizedTest
    @CsvSource({"test,TEST", "TEsT,TEST", "Java,JAVA"})
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValue(String inputt, String expected) {
        String actualValue = inputt.toUpperCase();
        assertEquals(expected, actualValue);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/file.csv", numLinesToSkip = 1)
    void toUpperCase_ShouldGenerateTheExpectedUppercaseValueWithCSVFile(String input, String expected) {
        String actualValue = input.toUpperCase();
        assertEquals(expected, actualValue);
    }

    private static Stream<Arguments> providesStringsForIsBlank() {
        return Stream.of(
                // lowl tani huwa expected
                Arguments.of(null, true),
                Arguments.of("", true),
                Arguments.of(" ", true),
                Arguments.of("not blank", false)
        );
    }

    @ParameterizedTest
    @MethodSource("providesStringsForIsBlank")
    void isBlank_ShouldReturnTrueForNullBlankStrings(
            String input, boolean expected) {
        assertEquals(expected,Strings.isBlank(input));
    }

    
}
