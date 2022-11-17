package domain;

public class Participant {
    private static final String OUT_OF_RANGE_NAME_LENGTH = "이름은 5글자를 초과할 수 없습니다.";

    private String name;

    private Participant(String name) {
        this.name = name;
    }

    public static Participant from(String name) {
        validateName(name);
        return new Participant(name);
    }

    private static void validateName(String name) {
        if (name.length() >  5) {
            throw new IllegalArgumentException(OUT_OF_RANGE_NAME_LENGTH);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(name);
        return sb.toString();
    }
}
