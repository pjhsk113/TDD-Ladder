package step2.domain;

public class Participant {
    private static final String OUT_OF_RANGE_NAME_LENGTH = "이름은 5글자를 초과할 수 없습니다.";
    private static final String NAME_IS_EMPTY = "이름에 빈 값이 입력되었습니다.";

    private int participationNumber;
    private String name;

    private Participant(int participationNumber, String name) {
        this.participationNumber = participationNumber;
        this.name = name;
    }

    public static Participant of(int participationNumber, String name) {
        validateNameLength(name);
        validateEmptyName(name);
        return new Participant(participationNumber, name);
    }

    private static void validateNameLength(String name) {
        if (name.length() >  5) {
            throw new IllegalArgumentException(OUT_OF_RANGE_NAME_LENGTH);
        }
    }

    private static void validateEmptyName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException(NAME_IS_EMPTY);
        }
    }

    public boolean isTarget(String target) {
        return name.equals(target);
    }

    public int getParticipationNumber() {
        return participationNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(name);
        return sb.toString();
    }
}
