package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ParticipantsTest {
    @DisplayName("참가자 수가 2명 미만인 경우 IllegalArgumentException이 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"aaa", "bbb, "})
    void participantNameTest(String name) {
        assertThatThrownBy(() -> Participants.from(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}