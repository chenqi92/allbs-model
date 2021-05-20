package cn.allbs.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取class 包括父类
 *
 * @author ChenQi
 */
@UtilityClass
public class ClassUtil {

    public Field[] getClassField(Class<?> clazz) {
        List<Field> list = new ArrayList<>();
        Field[] fields;
        do {
            fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                list.add(fields[i]);
            }
        } while (clazz != Object.class && clazz != null);
        return list.toArray(fields);
    }
}
