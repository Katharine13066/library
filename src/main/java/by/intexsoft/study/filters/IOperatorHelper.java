package by.intexsoft.study.filters;

public interface IOperatorHelper<T> {
    public OperatorHandler<T> getPredicate(Operator operator);
}
