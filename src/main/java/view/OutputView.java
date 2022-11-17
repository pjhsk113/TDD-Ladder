package view;

import domain.Participants;

import java.util.StringJoiner;

public class OutputView {
    private OutputView() { }

    public static void printParticipant(Participants participants) {
        System.out.println(participants.getNames());
    }
}
