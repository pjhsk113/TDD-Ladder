package step2.domain;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class Participants {
    private static final String COMMA = ",";
    private static final String PARTICIPANT_COUNT_EXCEPTION_MESSAGE = "참가자가 2명 이상 필요합니다.";
    private static final String NOT_EXIST_PARTICIPANT_EXCEPTION_MESSAGE = "입력하신 참가자가 존재하지 않습니다";

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
        AtomicInteger index = new AtomicInteger();
        return Arrays.stream(names.split(COMMA))
                .map(name -> Participant.of(index.getAndIncrement(), name))
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

    public int getSize() {
        return participants.size();
    }

    public Stream<Participant> stream() {
        return participants.stream();
    }

    public Participant findParticipant(String name) {
        return participants.stream()
                .filter(participant -> participant.isTarget(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PARTICIPANT_EXCEPTION_MESSAGE));
    }

    public Participant get(int index) {
        return participants.get(index);
    }
}
