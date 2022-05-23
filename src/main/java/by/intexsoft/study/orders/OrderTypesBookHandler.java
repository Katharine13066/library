package by.intexsoft.study.orders;

import by.intexsoft.study.model.Book;

import java.util.Comparator;
import java.util.function.Function;

@FunctionalInterface
public interface OrderTypesBookHandler<T> {
    Comparator<Book> handle(Function<Book, String> function);
}
