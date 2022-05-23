package by.intexsoft.study.orders;

import by.intexsoft.study.model.Author;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class OrderTypesAuthorHelper {

    private static Map<OrderTypes, OrderTypesAuthorHandler<String>> map = new HashMap();

    static {
        map.put(OrderTypes.ASC, OrderTypesAuthorHelper::getAscOrder);
        map.put(OrderTypes.DESC, OrderTypesAuthorHelper::getDescOrder);
    }

    public static Comparator<Author> getAscOrder(Function<Author, String> function) {
        Comparator<Author> authorComparator = Comparator.comparing(function);
        return authorComparator;
    }

    public static Comparator<Author> getDescOrder(Function<Author, String> function) {
        Comparator<Author> authorComparator = Comparator.comparing(function).reversed();
        return authorComparator;
    }

    public static OrderTypesAuthorHandler<String> getOrder(OrderTypes orderTypes){
        return map.get(orderTypes);
    }
}
