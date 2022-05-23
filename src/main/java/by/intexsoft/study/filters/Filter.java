package by.intexsoft.study.filters;

public class Filter {

    private String field;
    private String value;
    private Operator operator;

    public  Filter(){}

    public Filter(String field, String value, Operator operator) {
        this.field = field;
        this.value = value;
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
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
