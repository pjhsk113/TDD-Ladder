package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import step2.domain.Ladder;
import step2.domain.LineCreateStrategy;
import step2.domain.Participants;
import step2.domain.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class LadderTest {

    @DisplayName("사다리가 높이에 맞게 생성되었는지 테스트")
    @ParameterizedTest
    @MethodSource("createLadderProvider")
    void ladderHeightTest(Ladder ladder, long expectedHeight) {
        assertThat(ladder.stream().count()).isEqualTo(expectedHeight);
    }

    private static Stream<Arguments> createLadderProvider() {
        LineCreateStrategy alwaysTrueStrategy = prev -> true;
        return Stream.of(
                Arguments.of(Ladder.of(Participants.from("aa,bb,cc"), 4, alwaysTrueStrategy), 4),
                Arguments.of(Ladder.of(Participants.from("aa,bb"), 2, alwaysTrueStrategy), 2),
                Arguments.of(Ladder.of(Participants.from("aa,bb,cc,dd"), 3, alwaysTrueStrategy), 3),
                Arguments.of(Ladder.of(Participants.from("aa,bb,cc"), 5, alwaysTrueStrategy), 5)
        );
    }

    @DisplayName("사다리 생성 규칙 테스트")
    @ParameterizedTest
    @MethodSource("createLadderRulesProvider")
    void ladderRuleTest(Ladder ladder, List<List<Boolean>> expected) {
        assertThat(
                ladder.stream()
                        .map(line -> line.stream()
                                .map(Point::isDivergingPoint)
                                .collect(toList()))
                        .collect(toList())
        ).isEqualTo(expected);
    }

    private static Stream<Arguments> createLadderRulesProvider() {
        LineCreateStrategy toggleStrategy = prev -> !prev;
        LineCreateStrategy alwaysTrueStrategy = prev -> true;
        LineCreateStrategy alwaysFalseStrategy = prev -> false;

        return Stream.of(
                Arguments.of(
                        Ladder.of(Participants.from("aa,bb,cc"), 3, toggleStrategy),
                        Arrays.asList(
                                Arrays.asList(false, true, false),
                                Arrays.asList(false, true, false),
                                Arrays.asList(false, true, false)
                        )
                ),
                Arguments.of(
                        Ladder.of(Participants.from("aa,bb"), 3, alwaysTrueStrategy),
                        Arrays.asList(
                                Arrays.asList(false, true),
                                Arrays.asList(false, true),
                                Arrays.asList(false, true)
                        )
                ),
                Arguments.of(
                        Ladder.of(Participants.from("aa,bb,cc,dd"), 3, alwaysFalseStrategy),
                        Arrays.asList(
                                Arrays.asList(false, false, false, false),
                                Arrays.asList(false, false, false, false),
                                Arrays.asList(false, false, false, false)
                        )
                )
        );
    }

    @Nested
    @DisplayName("사다리 게임 실행 테스트")
    class LadderExecutorTest {
        @DisplayName("사다리 게임 단일 실행 테스트")
        @ParameterizedTest
        @MethodSource("step2.domain.LadderTest#ladderExecuteSingleResultProvider")
        void ladderSingleExecuteTest(Ladder ladder, Participant participant, int expectedIndex) {
            assertThat(ladder.execute(participant)).isEqualTo(expectedIndex);
        }

        @DisplayName("사다리 게임 전체 실행 테스트")
        @ParameterizedTest
        @MethodSource("step2.domain.LadderTest#ladderExecuteAllResultProvider")
        void ladderAllExecuteTest(Ladder ladder, Participants participants, List<Integer> expectedIndexes) {
            assertThat(ladder.executeAll(participants)).isEqualTo(expectedIndexes);
        }
    }

    private static Stream<Arguments> ladderExecuteSingleResultProvider() {
        LineCreateStrategy toggleStrategy = prev -> !prev;

        return Stream.of(
                Arguments.of(
                        Ladder.of(Participants.from("aa,bb,cc"), 3, toggleStrategy),
                        Participant.of(1, "bb"),
                        0
                ),
                Arguments.of(
                        Ladder.of(Participants.from("aa,bb"), 4, toggleStrategy),
                        Participant.of(0, "aa"),
                        0
                ),
                Arguments.of(
                        Ladder.of(Participants.from("aa,bb,cc,dd"), 5, toggleStrategy),
                        Participant.of(2, "cc"),
                        3
                )
        );
    }

    private static Stream<Arguments> ladderExecuteAllResultProvider() {
        LineCreateStrategy toggleStrategy = prev -> !prev;

        return Stream.of(
                Arguments.of(
                        Ladder.of(Participants.from("aa,bb,cc"), 3, toggleStrategy),
                        Participants.from("aa,bb,cc"),
                        Arrays.asList(1,0,2)
                ),
                Arguments.of(
                        Ladder.of(Participants.from("aa,bb"), 4, toggleStrategy),
                        Participants.from("aa,bb"),
                        Arrays.asList(0,1)
                ),
                Arguments.of(
                        Ladder.of(Participants.from("aa,bb,cc,dd"), 5, toggleStrategy),
                        Participants.from("aa,bb,cc,dd"),
                        Arrays.asList(1,0,3,2)
                )
        );
    }
}