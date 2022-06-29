package by.intexsoft.study.filters;

import java.util.HashMap;
import java.util.Map;

public class IntegerOperatorHelper implements IOperatorHelper<Integer>{

    private Map<Operator, OperatorHandler<Integer>> integerOperatorMap = new HashMap<>();

    public IntegerOperatorHelper(){
        integerOperatorMap.put(Operator.BIGGERTHAN, IntegerOperatorHelper::biggerThan);
        integerOperatorMap.put(Operator.SMALLERTHAN, IntegerOperatorHelper::smallerThan);
        integerOperatorMap.put(Operator.EQUALS, IntegerOperatorHelper::equalInteger);
    }

    private static Boolean equalInteger(Integer i1, Integer i2){
        return i1.equals(i2);
    }

    private static Boolean smallerThan(Integer i1, Integer i2) {
        return i1 <= i2;
    }

    private static Boolean biggerThan(Integer i1, Integer i2) {
        return i1 >= i2;
    }
    @Override
    public OperatorHandler<Integer> getPredicate(Operator operator) {
        return integerOperatorMap.get(operator);
    }
}
