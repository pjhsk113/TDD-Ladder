package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step2.domain.Line;
import step2.domain.LineCreateStrategy;

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
                Arguments.of(Line.of(3, toggleStrategy), 1),
                Arguments.of(Line.of(4, toggleStrategy), 2),
                Arguments.of(Line.of(3, alwaysTrueStrategy), 2),
                Arguments.of(Line.of(4, alwaysTrueStrategy), 3),
                Arguments.of(Line.of(5, alwaysTrueStrategy), 4),
                Arguments.of(Line.of(2, alwaysFalseStrategy), 0),
                Arguments.of(Line.of(3, alwaysFalseStrategy), 0),
                Arguments.of(Line.of(4, alwaysFalseStrategy), 0)
        );
    }

    @DisplayName("사다리 라인 분기점 별 움직임 방향 테스트")
    @ParameterizedTest
    @MethodSource("lineMoveProvider")
    void lineMoveTest(Line line, int targetIndex, long expected) {
        assertThat(line.move(targetIndex)).isEqualTo(expected);
    }

    private static Stream<Arguments> lineMoveProvider() {
        LineCreateStrategy toggleStrategy = prev -> !prev;

        return Stream.of(
                Arguments.of(Line.of(2, toggleStrategy), 0, 1),
                Arguments.of(Line.of(2, toggleStrategy), 1, 0),
                Arguments.of(Line.of(3, toggleStrategy), 1, 0),
                Arguments.of(Line.of(3, toggleStrategy), 2, 2),
                Arguments.of(Line.of(4, toggleStrategy), 2, 3)
        );
    }
}