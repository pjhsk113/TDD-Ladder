package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ParticipantTest {

    @DisplayName("참여자 이름이 5글자를 초과하면 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"aaaaaaaa", "bbbbbbb", "cccccc"})
    void participantNameLengthTest(String name) {
        assertThatThrownBy(() -> Participant.of(0, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 5글자를 초과할 수 없습니다.");
    }

    @DisplayName("참여자 이름에 공백이 입력된 경우 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void participantEmptyNameTest(String name) {
        assertThatThrownBy(() -> Participant.of(0, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름에 빈 값이 입력되었습니다.");
    }
}