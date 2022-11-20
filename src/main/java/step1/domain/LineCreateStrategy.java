package step1.domain;

@FunctionalInterface
public interface LineCreateStrategy {
    boolean createLine(boolean prev);
}
