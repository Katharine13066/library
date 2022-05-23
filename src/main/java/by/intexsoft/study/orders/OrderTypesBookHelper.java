package by.intexsoft.study.orders;

import by.intexsoft.study.model.Book;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class OrderTypesBookHelper {

    private static Map<OrderTypes, OrderTypesBookHandler<String>> map = new HashMap();

    static {
        map.put(OrderTypes.ASC, OrderTypesBookHelper::getAscOrder);
        map.put(OrderTypes.DESC, OrderTypesBookHelper::getDescOrder);
    }

    public static Comparator<Book> getAscOrder(Function<Book, String> function) {
        Comparator<Book> bookComparator = Comparator.comparing(function);
        return bookComparator;
    }

    public static Comparator<Book> getDescOrder(Function<Book, String> function) {
        Comparator<Book> bookComparator = Comparator.comparing(function).reversed();
        return bookComparator;
    }

    public static OrderTypesBookHandler<String> getOrder(OrderTypes orderTypes){
        return map.get(orderTypes);
    }
}
