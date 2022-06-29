package by.intexsoft.study.filters;

@FunctionalInterface
public interface OperatorHandler <T> {
   Boolean handle(T parameter1, T parameter2);
}
