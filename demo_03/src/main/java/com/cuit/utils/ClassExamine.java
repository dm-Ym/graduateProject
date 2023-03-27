package com.cuit.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class ClassExamine {
    /**
     * 对象字段互补。传入一个同类型被补充对象和完整对象，
     * 如果被补充对象中有字段为null或者字符串为空，就用完整对象对应的字段值补上去；
     * 如果被补充对象中某字段不为空则保留它自己的值。
     *
     * @param origin       被补充对象
     * @param intactObject 完整对象
     * @param <T>          传入对象类型
     */
    public static <T> void objectOverlap(T origin, T intactObject) throws Exception {
        // getDeclaredFields()  返回当前Class对象表示的类或接口的所有已说明的域对象数组。
        Field[] fields = origin.getClass().getDeclaredFields();
        for (Field field : fields) {
            // setAccessible(true) 使其可访问
            field.setAccessible(true);
            if (field.getType() == String.class) {
                // 判断前端传入字段是否为空，空则互补，非空则保持不变
                if (StringUtils.isEmpty((String) field.get(origin))) {
//                    System.out.println(field.get(intactObject));
                    // set() 给属性赋值
                    field.set(origin, field.get(intactObject));
                }
            } else {
                if (field.get(origin) == null) {
                    field.set(origin, field.get(intactObject));
                }
            }
        }
    }
}
