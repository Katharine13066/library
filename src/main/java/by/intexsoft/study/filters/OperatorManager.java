package by.intexsoft.study.filters;

import java.util.HashMap;
import java.util.Map;

public class OperatorManager{

     private Map<Class<?>,IOperatorHelper> map = new HashMap<>();

     public OperatorManager(){
         map.put(String.class, new StringOperatorHelper());
         map.put(Integer.class, new IntegerOperatorHelper());
     }

     public <T>IOperatorHelper<T> getOperatorHelper(Class<T> clazz){
         return map.get(clazz);
     }
}
