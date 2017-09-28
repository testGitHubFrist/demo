package com.nilo.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/8/5 </p>
 * <p>Time: 16:27 </p>
 * <p>Version: 1.0 </p>
 */
public class ReflectUtils {

    /**
     * 编码GBK转换ISO-8859-1
     */
    public static String encodeGBKToISO(String name) {
        String result = name;
        try {
            result = new String(name.getBytes("GBK"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 编码ISO-8859-1转换UTF-8
     */
    public static String encode(String name){
        String result = name;
        try{
            result = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return result;
    }
    public static String capital(String[] strs){
        StringBuffer buffer = new StringBuffer();
        for(String str : strs){
            buffer.append(capital(str));
        }
        return buffer.toString();
    }
    // 首字母大写
    public static String capital(String str) {
        char[] array = str.toCharArray();
        if (String.valueOf(array[0]).matches("[A-Z0-9]")) {
            return str;
        }
        array[0] -= 32;
        return String.valueOf(array);
    }
    // 首字母小写
    public static String minuscules(String str){
        char[] array = str.toCharArray();
        if (String.valueOf(array[0]).matches("[a-z0-9]")) {
            return str;
        }
        array[0] += 32;
        return String.valueOf(array);
    }
    /**
     *
     * @param value
     * @return
     */
    public static String setMethodName(String value){
        return "set"+capital(value);
    }
    public static String getMethodName(String value){
        return "get"+capital(value);
    }
    public static Method getSetMethod(Class objectClass, String fieldName) throws Exception {
        Method method = null;
        Class[] parameterTypes = new Class[1];
        Field field = objectClass.getDeclaredField(fieldName);
        parameterTypes[0] = field.getType();
        method = objectClass.getMethod(setMethodName(fieldName), parameterTypes);
        return method;
    }
    public static void invokeSet(Object obj, String fieldName, Object value) throws Exception {
        getSetMethod(obj.getClass(), fieldName).invoke(obj, new Object[] { value });
    }
    public static Method getGetMethod(Class objectClass, String fieldName)throws Exception {
        return objectClass.getMethod(getMethodName(fieldName));
    }
    public static Object invokeGet(Object obj, String fieldName)throws Exception {
        Method method = getGetMethod(obj.getClass(), fieldName);
        return method.invoke(obj, new Object[0]);
    }

    public static Object invokeMethodByName(Object obj , String methodName, Object[] parameter)throws Exception {
        Class[] parameterTypes = null;
        if(null != parameter){
            parameterTypes = new Class[parameter.length];
            for(int i = 0,len = parameter.length; i< len;i++){
                parameterTypes[i] = parameter[i].getClass();
            }
        }
        Method method = obj.getClass().getDeclaredMethod(methodName,parameterTypes);
        return method.invoke(obj,parameter);
    }
}
