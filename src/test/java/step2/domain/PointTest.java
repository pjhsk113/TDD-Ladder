package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @DisplayName("진행 방향에 대한 인덱스를 올바르게 반환하는지 테스트")
    @ParameterizedTest
    @MethodSource("directionProvider")
    void findPointDirectionTest(Point currentPoint, Point nextPoint, int expectedIndex) {
        assertThat(currentPoint.findDirectionIndex(nextPoint)).isEqualTo(expectedIndex);
    }

    private static Stream<Arguments> directionProvider() {
        return Stream.of(
                Arguments.of(Point.of(1, false), Point.of(2, true), 2),
                Arguments.of(Point.of(1, false), Point.of(2, false), 1),
                Arguments.of(Point.of(1, true), Point.of(2, false), 0)
        );
    }

    @DisplayName("첫 번째 포인트의 방향 결정 테스트")
    @ParameterizedTest
    @MethodSource("firstPointProvider")
    void firstPointDirectionTest(Point currentPoint, Point nextPoint, int expectedIndex) {
        assertThat(currentPoint.isRightDiverge(nextPoint)).isEqualTo(expectedIndex);
    }

    private static Stream<Arguments> firstPointProvider() {
        return Stream.of(
                Arguments.of(Point.of(0, false), Point.of(1, true), 1),
                Arguments.of(Point.of(0, false), Point.of(1, false), 0)
        );
    }

    @DisplayName("마지막 포인트의 방향 결정 테스트")
    @ParameterizedTest
    @MethodSource("lastPointProvider")
    void lastPointDirectionTest(Point currentPoint, int expectedIndex) {
        assertThat(currentPoint.isLeftDiverge()).isEqualTo(expectedIndex);
    }

    private static Stream<Arguments> lastPointProvider() {
        return Stream.of(
                Arguments.of(Point.of(5, true), 4),
                Arguments.of(Point.of(5, false), 5)
        );
    }

}