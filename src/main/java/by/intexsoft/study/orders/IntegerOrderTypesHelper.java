package by.intexsoft.study.orders;

import by.intexsoft.study.stringUtils.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;

public class IntegerOrderTypesHelper<T> implements IOrderTypesHelper<T>{

    @Override
    public Comparator<T> getAscComparator(Class<T> clazz, String field) throws NoSuchMethodException {
        Method getter = clazz.getDeclaredMethod("get"+ StringUtils.firstUpperCase(field));

        return (o1, o2) -> {
            try {
                return ((Integer) getter.invoke(o1)).compareTo((Integer) getter.invoke(o2));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        };
    }


    @Override
    public Comparator<T> getDescComparator(Class<T> clazz, String field) throws NoSuchMethodException {
        Method getter = clazz.getDeclaredMethod("get"+ StringUtils.firstUpperCase(field));

        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                try {
                    return ((Integer) getter.invoke(o1)).compareTo((Integer) getter.invoke(o2));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        return Collections.reverseOrder(comparator);
    }
}
