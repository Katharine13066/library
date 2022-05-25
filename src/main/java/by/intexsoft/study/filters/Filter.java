package by.intexsoft.study.filters;

public class Filter<T> {

    private String field;
    private T value;
    private Operator operator;

    public  Filter(){}


    public Filter(String field, T value, Operator operator) {
        this.field = field;
        this.value = value;
        this.operator = operator;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
