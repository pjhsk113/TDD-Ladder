package step2.domain;

public class Participant {
    private static final String OUT_OF_RANGE_NAME_LENGTH = "이름은 5글자를 초과할 수 없습니다.";
    private static final String NAME_IS_EMPTY = "이름에 빈 값이 입력되었습니다.";

    private String name;

    private Participant(String name) {
        this.name = name;
    }

    public static Participant from(String name) {
        validateNameLength(name);
        validateEmptyName(name);
        return new Participant(name);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(name);
        return sb.toString();
    }
}
