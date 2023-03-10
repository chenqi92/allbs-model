package cn.allbs.utils.SFJK200.format;

import cn.allbs.utils.SFJK200.format.der.SFJK200Deserializer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * 类 SFK200Factory
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/8 15:53
 */
public class SFJK200Factory {

    /**
     * 解析器
     */
    final protected HashMap<Type, SFJK200Deserializer<Object>> _rootDeserializers = new HashMap<>();

    public SFJK200Factory copy() {
        SFJK200Factory factory = new SFJK200Factory();
        factory._rootDeserializers.putAll(this._rootDeserializers);
        return factory;
    }

    public SFJK200Factory() {

    }

    /**
     * 目前只做基本解析,后续有时间完善框架
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> SFJK200Deserializer<T> deserializerFor(Class<T> tClass) {
        SFJK200Deserializer<T> deserializer = (SFJK200Deserializer<T>) _rootDeserializers.get(tClass);
        return deserializer;
    }

    /**
     * 创建gb26875 报文解析器
     *
     * @param bytes
     * @return
     */
    public SFJK200Parser parser(byte[] bytes, int register) {
        return parser(new ByteArrayInputStream(bytes), register);
    }

    /**
     * 创建gb26875 报文解析器
     *
     * @param is
     * @return
     */
    public SFJK200Parser parser(InputStream is, int register) {
        SFJK200Parser parser = new SFJK200Parser((ByteArrayInputStream) is, register);
        return parser;
    }

    /**
     * 注册类型反序列化器
     *
     * @param type              类型
     * @param deserializerClass 反序列化器
     * @throws IllegalAccessException 越权访问异常
     * @throws InstantiationException 实例化异常
     */
    public void deserializerRegister(Type type, Class<? extends SFJK200Deserializer> deserializerClass) throws IllegalAccessException, InstantiationException {
        _rootDeserializers.put(type, deserializerClass.newInstance());
    }
}
