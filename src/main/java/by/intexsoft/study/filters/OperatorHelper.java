package by.intexsoft.study.filters;

import java.util.HashMap;
import java.util.Map;

public class OperatorHelper {

    private static Map<Operator, OperatorHandler<String>> map = new HashMap();

    static {
        map.put(Operator.STARTSWITH, OperatorHelper::startsWith);
        map.put(Operator.ENDSWITH, OperatorHelper::endWith);
        map.put(Operator.CONTAINS, OperatorHelper::contains);
    }

    public static Boolean startsWith(String source, String target) {
        return  source.startsWith(target);
    }

    public static Boolean endWith(String source, String target) { return  source.endsWith(target);}

    public static Boolean contains(String source, String target) { return source.contains(target);}

    public static OperatorHandler<String> getPredicate(Operator operator){
        return map.get(operator);
    }
}
