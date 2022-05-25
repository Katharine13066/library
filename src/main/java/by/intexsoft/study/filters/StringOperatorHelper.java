package by.intexsoft.study.filters;

import java.util.HashMap;
import java.util.Map;

public class StringOperatorHelper implements IOperatorHelper<String> {

    private Map<Operator, OperatorHandler<String>> stringOperatorMap = new HashMap();

    public StringOperatorHelper(){
        stringOperatorMap.put(Operator.STARTSWITH, StringOperatorHelper::startsWith);
        stringOperatorMap.put(Operator.ENDSWITH, StringOperatorHelper::endWith);
        stringOperatorMap.put(Operator.CONTAINS, StringOperatorHelper::contains);
    }

    public static Boolean startsWith(String source, String target) {
        return  source.startsWith(target);
    }

    public static Boolean endWith(String source, String target) { return  source.endsWith(target);}

    public static Boolean contains(String source, String target) { return source.contains(target);}

    @Override
    public OperatorHandler<String> getPredicate(Operator operator) {
        return stringOperatorMap.get(operator);
    }
}
