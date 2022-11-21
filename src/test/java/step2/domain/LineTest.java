package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step1.domain.Line;
import step1.domain.LineCreateStrategy;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LineTest {

    @DisplayName("분기 라인 생성 테스트")
    @ParameterizedTest
    @MethodSource("createLineProvider")
    void createLineTest(Line line, long expected) {
        assertThat(
                line.stream()
                        .filter(point -> point.isDivergingPoint())
                        .count()
        ).isEqualTo(expected);
    }

    private static Stream<Arguments> createLineProvider() {
        LineCreateStrategy toggleStrategy = prev -> !prev;
        LineCreateStrategy alwaysTrueStrategy = prev -> true;
        LineCreateStrategy alwaysFalseStrategy = prev -> false;

        return Stream.of(
                Arguments.of(Line.of(2, toggleStrategy), 1),
                Arguments.of(Line.of(3, alwaysTrueStrategy), 2),
                Arguments.of(Line.of(4, alwaysTrueStrategy), 3),
                Arguments.of(Line.of(5, alwaysTrueStrategy), 4),
                Arguments.of(Line.of(2, alwaysFalseStrategy), 0),
                Arguments.of(Line.of(3, alwaysFalseStrategy), 0),
                Arguments.of(Line.of(4, alwaysFalseStrategy), 0)
        );
    }
}