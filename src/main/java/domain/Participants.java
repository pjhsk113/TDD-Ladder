package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Participants {
    private static final String COMMA = ",";
    private static final String PARTICIPANT_COUNT_EXCEPTION_MESSAGE = "참가자가 2명 이상 필요합니다.";

    private List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(String names) {
        List<Participant> participants = convert(names);
        validateParticipantCount(participants);

        return new Participants(participants);
    }

    private static List<Participant> convert(String names) {
        return Arrays.stream(names.split(COMMA))
                .map(Participant::from)
                .collect(Collectors.toList());
    }

    private static void validateParticipantCount(List<Participant> participants) {
        if (participants.size() < 2) {
            throw new IllegalArgumentException(PARTICIPANT_COUNT_EXCEPTION_MESSAGE);
        }
    }

    public String getNames() {
        return participants.stream()
                .map(participant -> String.format("%6s", participant.toString()))
                .collect(joining(""));
    }
}
