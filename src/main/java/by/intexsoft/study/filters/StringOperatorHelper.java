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

    private static Boolean startsWith(String source, String target) {
        return  source.startsWith(target);
    }

    private static Boolean endWith(String source, String target) { return  source.endsWith(target);}

    private static Boolean contains(String source, String target) { return source.contains(target);}

    @Override
    public OperatorHandler<String> getPredicate(Operator operator) {
        return stringOperatorMap.get(operator);
    }
}
