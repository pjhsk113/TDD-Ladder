package domain;

@FunctionalInterface
public interface LineCreateStrategy {
    boolean createLine(boolean prev);
}
