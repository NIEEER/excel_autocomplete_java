package com.excel.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EntityReflectionUtils {
    public static Object getFieldValue(Object object, String fieldName) {
        try {
            Class<?> clazz = object.getClass();
            String methodName = "get" + capitalize(fieldName);
            Method getMethod = clazz.getMethod(methodName);
            return getMethod.invoke(object);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            // 处理异常
            return null;
        }
    }

    public static void setFieldValue(Object object, String fieldName, String value) {
        try {
            Class<?> clazz = object.getClass();
            String methodName = "set" + capitalize(fieldName);
            Method setMethod = clazz.getDeclaredMethod(methodName, value.getClass());
            setMethod.invoke(object, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // 将驼峰法命名字段转化为下划线命名字段。
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
