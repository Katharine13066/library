package by.intexsoft.study.orders;

import java.util.Comparator;

public interface IOrderTypesHelper<T> {

    Comparator<T> getAscComparator(Class<T> clazz, String field) throws NoSuchMethodException;
    Comparator<T> getDescComparator(Class<T> clazz, String field) throws NoSuchMethodException;
}
