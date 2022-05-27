package by.intexsoft.study.stringUtils;

import by.intexsoft.study.orders.OrderTypes;

public class StringUtils {

    public static String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public static String getComparatorMethodName(OrderTypes orderTypes){
        String word = orderTypes.toString();
        if(word == null || word.isEmpty()) return "";
        return "get"+StringUtils.firstUpperCase(word.toLowerCase())+"Comparator";
    }
}
