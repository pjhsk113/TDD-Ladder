package step2.view;

import step2.domain.Participants;
import step2.domain.Results;

import java.util.Scanner;

public class InputView {
    private static final String INPUT_PARTICIPANTS_MESSAGE = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
    private static final String INPUT_RESULT_MESSAGE = "실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)";
    private static final String INPUT_HEIGHT_MESSAGE = "최대 사다리 높이는 몇 개인가요?";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {}

    public static Participants inputParticipant() {
        System.out.println(INPUT_PARTICIPANTS_MESSAGE);
        return Participants.from(SCANNER.nextLine());
    }

    public static Results inputResult(int limit) {
        System.out.println(INPUT_RESULT_MESSAGE);
        return Results.from(SCANNER.nextLine(), limit);
    }

    public static int inputHeight() {
        System.out.println(INPUT_HEIGHT_MESSAGE);
        return SCANNER.nextInt();
    }
}
