package step2.domain;

public class Result {
    private final int index;
    private final String value;

    private Result(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static Result of(int index, String value) {
        return new Result(index, value);
    }

    public boolean isSameIndex(int targetIndex) {
        return index == targetIndex;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }
}
