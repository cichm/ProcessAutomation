package net.usermd.mcichon.body.dynamicproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BenchmarkTimingDynamicInvocationHandler implements InvocationHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(BenchmarkTimingDynamicInvocationHandler.class);
    private final Map<String, Method> stringMethodHashMap = new HashMap<>();
    private Object target;

    public BenchmarkTimingDynamicInvocationHandler(Object target) {
        this.target = target;

        for(Method method: target.getClass().getDeclaredMethods()) {
            this.stringMethodHashMap.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.nanoTime();
        Object result = stringMethodHashMap.get(method.getName()).invoke(target, args);
        long elapsed = System.nanoTime() - start;

        LOGGER.info("Executing {} finished in {} ns", method.getName(), elapsed);

        return result;
    }
}
