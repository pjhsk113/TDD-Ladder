package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ResultsTest {
    @DisplayName("참가자 수 보다 결괏값이 더 많이 입력된 경우 예외 발생")
    @ParameterizedTest
    @CsvSource(value = {"당첨, 꽝, 꽝, 당첨 : 3", "당첨, 꽝, 꽝 : 2"}, delimiter = ':')
    void inputExceededExceptionTest(String values, int limit) {
        assertThatThrownBy(() -> Results.of(values, limit))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("입력된 결과를 올바른 포맷으로 반환하는지 테스트")
    @ParameterizedTest
    @MethodSource("resultsProvider")
    void resultsFormattingTest(Results results, String expected) {
        assertThat(results.getResults()).isEqualTo(expected);
    }

    private static Stream<Arguments> resultsProvider() {
        return Stream.of(
                Arguments.of(Results.of("0,0,1000,0", 4), "     0     0  1000     0"),
                Arguments.of(Results.of("꽝,당첨,꽝,꽝", 4), "     꽝    당첨     꽝     꽝")
        );
    }

    @Nested
    @DisplayName("결과값 반환 테스트")
    class ReturnResultsTest {
        @DisplayName("올바른 매칭된 결과값을 반환")
        @ParameterizedTest
        @MethodSource("step2.domain.ResultsTest#matchingResultsProvider")
        void resultsMatchingTest(Results results, int resultIndex, Result expected) {
            assertThat(results.findResult(resultIndex).toString()).isEqualTo(expected.toString());
        }

        @DisplayName("매칭된 결과값이 없다면 예외 발생")
        @ParameterizedTest
        @MethodSource("step2.domain.ResultsTest#notMatchingResultsProvider")
        void resultsNotMatchingTest(Results results, int resultIndex) {
            assertThatThrownBy(() -> results.findResult(resultIndex))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    private static Stream<Arguments> matchingResultsProvider() {
        return Stream.of(
                Arguments.of(Results.of("0,0,1000,0", 4), 2, Result.of(2, "1000")),
                Arguments.of(Results.of("꽝,당첨,꽝,꽝", 4), 1, Result.of(1, "당첨"))
        );
    }

    private static Stream<Arguments> notMatchingResultsProvider() {
        return Stream.of(
                Arguments.of(Results.of("0,0,1000,0", 4), 5),
                Arguments.of(Results.of("꽝,당첨,꽝,꽝", 4), 5)
        );
    }
}