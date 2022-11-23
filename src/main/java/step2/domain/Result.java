package step2.domain;

public class Result {
    private final String value;

    private Result(String value) {
        this.value = value;
    }

    public static Result from(String value) {
        return new Result(value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }
}
