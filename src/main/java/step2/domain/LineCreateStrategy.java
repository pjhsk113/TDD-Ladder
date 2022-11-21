package step2.domain;

@FunctionalInterface
public interface LineCreateStrategy {
    boolean createLine(boolean prev);
}
