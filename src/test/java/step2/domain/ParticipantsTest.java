package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import step2.domain.Participant;
import step2.domain.Participants;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ParticipantsTest {
    @DisplayName("참가자 수가 2명 미만인 경우 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"aaa", "bbb, "})
    void participantNameTest(String name) {
        assertThatThrownBy(() -> Participants.from(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자가 동명이인인 경우 서로 다른 객체로 생성하는지 테스트")
    @ParameterizedTest
    @MethodSource("participantObjectProvider")
    void participantDuplicateNameTest(Participants participants) {
        List<Participant> participantList = participants.stream().collect(toList());
        assertThat(participantList.get(0)).doesNotHaveSameHashCodeAs(participantList.get(1));
    }

    private static Stream<Arguments> participantObjectProvider() {
        return Stream.of(
                Arguments.of(Participants.from("aaa,aaa")),
                Arguments.of(Participants.from("bb,bb"))
        );
    }
}