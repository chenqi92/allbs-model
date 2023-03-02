package cn.allbs.utils.gb26875.format;

import cn.allbs.utils.gb26875.format.der.GB26875Deserializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * 目前只定架构，只做最简单的解析方式，后续有机会完善整个报文解析结构
 * 只做数据解析，构建后续有需要再配置
 *
 * @author ChenQi
 */
public class GB26875Factory {

    /**
     * 解析器
     */
    final protected HashMap<Type, GB26875Deserializer<Object>> _rootDeserializers = new HashMap<>();

    public GB26875Factory copy() {
        GB26875Factory factory = new GB26875Factory();
        factory._rootDeserializers.putAll(this._rootDeserializers);
        return factory;
    }

    public GB26875Factory() {

    }

    /**
     * 目前只做基本解析,后续有时间完善框架
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> GB26875Deserializer<T> deserializerFor(Class<T> tClass) {
        GB26875Deserializer<T> deserializer = (GB26875Deserializer<T>) _rootDeserializers.get(tClass);
        // 后续有时间完善
//        if (deserializer instanceof Configured) {
//            Configured configured = (Configured) deserializer;
//            configured.configured(configurator);
//        }
        return deserializer;
    }

    /**
     * 创建gb26875 报文解析器
     *
     * @param bytes
     * @return
     */
    public GB26875Parser parser(byte[] bytes) {
        return new GB26875Parser(bytes);
    }

    /**
     * 注册类型反序列化器
     *
     * @param deserializerClass 反序列化器
     * @throws InstantiationException 实例化异常
     * @throws IllegalAccessException 越权访问异常
     */
    public void deserializerRegister(Class<? extends GB26875Deserializer> deserializerClass) throws InstantiationException, IllegalAccessException {
        Type type = Stream.of(deserializerClass.getGenericInterfaces())
                .filter(t -> t instanceof ParameterizedType)
                .map(t -> (ParameterizedType) t)
                .filter(pt -> pt.getRawType().equals(GB26875Deserializer.class))
                .map(pt -> pt.getActualTypeArguments()[0])
                .findFirst()
                .orElse(Object.class);

        deserializerRegister(type, deserializerClass);
    }

    /**
     * 注册类型反序列化器
     *
     * @param type              类型
     * @param deserializerClass 反序列化器
     * @throws IllegalAccessException 越权访问异常
     * @throws InstantiationException 实例化异常
     */
    public void deserializerRegister(Type type, Class<? extends GB26875Deserializer> deserializerClass) throws IllegalAccessException, InstantiationException {
        _rootDeserializers.put(type, deserializerClass.newInstance());
    }

}
