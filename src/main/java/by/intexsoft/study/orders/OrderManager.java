package by.intexsoft.study.orders;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {

    private Map<Class<?>, IOrderTypesHelper> map = new HashMap<>();

    public OrderManager(){
        map.put(String.class, new StringOrderTypesHelper());
        map.put(Integer.class, new IntegerOrderTypesHelper());
    }

    public <T>IOrderTypesHelper<T> getOrderHelper(Class<T> clazz){
        return map.get(clazz);
    }


}
