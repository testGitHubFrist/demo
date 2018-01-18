package com.nilo.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyAccessorFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/10 </p>
 * <p>Time: 12:08 </p>
 * <p>Version: 1.0 </p>
 */
public abstract class ObjectUtil {

    // 用序列化与反序列化实现深克隆
    public static Object deepClone(Object src) {
        Object o = null;
        try {
            if (src != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(src);
                oos.close();
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                o = ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }


    /**
     * GZIP 压缩
     * @param str
     * @return
     * @throws IOException
     */
    public static String compress(String str) throws IOException {
        if (isEmpty(str))
            return str;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }

    /**
     * 解压 GZIP
     * @param str
     * @return
     * @throws IOException
     */
    public static String uncompress(String str) throws IOException {
        if (isEmpty(str))
            return str;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        GZIPInputStream gzip = new GZIPInputStream(in);
        byte[] buffer = new byte[1024];
        int n;
        while ((n = gzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        return out.toString();
    }

    public static boolean isEmpty(String v) {
        return v == null || v.length() == 0;
    }

    /**
     * 拷贝全部属性，包含父类属性
     * @param source
     * @param target
     */
    public static void copyAllProperties(Object source, Object target){
        BeanWrapper sourceBw = PropertyAccessorFactory.forBeanPropertyAccess(source);
        BeanWrapper targetBw = PropertyAccessorFactory.forBeanPropertyAccess(target);
        //源对象全部属性
        List<String> sourceFieldNames = new ArrayList<String>();
        Class<?> sourceClass = source.getClass();
        do {
            Field[] sourceFields = sourceClass.getDeclaredFields();
            for(Field field : sourceFields){
                sourceFieldNames.add(field.getName());
            }
            sourceClass = sourceClass.getSuperclass();
        } while (sourceClass != null && sourceClass != Object.class);

        //目标对象全部属性
        Class<?> targetClass = target.getClass();
        do {
            Field[] targetFields = targetClass.getDeclaredFields();
            String name = null;
            for (Field targetField : targetFields){
                name = targetField.getName();
                if("serialVersionUID".equalsIgnoreCase(name)){
                    continue;
                }
                if(sourceFieldNames.contains(name)) {
                    try{
                        targetBw.setPropertyValue(name, sourceBw.getPropertyValue(name));
                    } catch (BeansException e) {
                        System.out.println(String.format("field %s is error : %s.", name, e.getMessage()));
                    }
                }
            }
            targetClass = targetClass.getSuperclass();
        } while (targetClass != null && targetClass != Object.class);
    }
    /**
     * 非序列化对象也有效
     * 将source 对象的属性 copy 到 target 对象
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target){
        BeanWrapper sourceBw = PropertyAccessorFactory.forBeanPropertyAccess(source);
        BeanWrapper targetBw = PropertyAccessorFactory.forBeanPropertyAccess(target);

        Field[] sourceFields = source.getClass().getDeclaredFields();
        List<String> sourceFieldNames = new ArrayList<String>();
        for(Field field : sourceFields){
            sourceFieldNames.add(field.getName());
        }
        Field[] targetFields = target.getClass().getDeclaredFields();
        String name = null;
        for (Field field : targetFields){
            name = field.getName();
            if("serialVersionUID".equalsIgnoreCase(name)){
                continue;
            }
            if(sourceFieldNames.contains(name)) {
                try{
                    targetBw.setPropertyValue(name, sourceBw.getPropertyValue(name));
                } catch (BeansException e) {
                    System.out.println(String.format("field %s is error : %s.", name, e.getMessage()));
                }
            }
        }
    }

    /**
     * 将source 对象有值的属性copy到target
     * @param source
     * @param target
     */
    public static void copyValueProperties(Object source, Object target){
        BeanWrapper sourceBw = PropertyAccessorFactory.forBeanPropertyAccess(source);
        BeanWrapper targetBw = PropertyAccessorFactory.forBeanPropertyAccess(target);

        Field[] sourceFields = source.getClass().getDeclaredFields();
        List<String> sourceFieldNames = new ArrayList<String>();
        for(Field field : sourceFields){
            sourceFieldNames.add(field.getName());
        }
        Field[] targetFields = target.getClass().getDeclaredFields();
        String name = null;
        for (Field field : targetFields){
            name = field.getName();
            if("serialVersionUID".equalsIgnoreCase(name)){
                continue;
            }
            if(sourceFieldNames.contains(name)) {
                try{
                    Object value = sourceBw.getPropertyValue(name);
                    if (null != value) {
                        targetBw.setPropertyValue(name, value);
                    }
                } catch (BeansException e) {
                    System.out.println(String.format("field %s is error : %s.", name, e.getMessage()));
                }
            }
        }
    }

    /**
     * 序列化的对象有效
     * @see java.io.Serializable
     * 类型转换，序列化json，在反序列化成对象
     * @param source
     * @param requiredType
     * @param <T>
     * @return
     */
    public static <T> T convertObject(Object source, Class<T> requiredType){
        return JacksonUtil.jsonConvertObject(JacksonUtil.write2JsonStr(source), requiredType);
    }
}
