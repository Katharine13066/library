package by.intexsoft.study.orders;

import by.intexsoft.study.model.Author;

import java.util.Comparator;
import java.util.function.Function;

@FunctionalInterface
public interface OrderTypesAuthorHandler<T> {
    Comparator<Author> handle(Function<Author, T> function);
}
