package by.intexsoft.study.orders;

public class Order {
    private String field;
    private OrderTypes orderTypes;

    public Order(){};

    public Order(String field, OrderTypes orderTypes) {
        this.field = field;
        this.orderTypes = orderTypes;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public OrderTypes getOrderTypes() {
        return orderTypes;
    }

    public void setOrderTypes(OrderTypes orderTypes) {
        this.orderTypes = orderTypes;
    }
}
