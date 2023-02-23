package cn.allbs.utils.JBF293K.format;

import cn.allbs.utils.JBF293K.format.der.JBF293KDeserializer;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * 类 GBF293KFactory
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 10:38
 */
public class JBF293KFactory {

    /**
     * 解析器
     */
    final protected HashMap<Type, JBF293KDeserializer<Object>> _rootDeserializers = new HashMap<>();

    public JBF293KFactory copy() {
        JBF293KFactory factory = new JBF293KFactory();
        factory._rootDeserializers.putAll(this._rootDeserializers);
        return factory;
    }

    public JBF293KFactory() {

    }

    /**
     * 目前只做基本解析,后续有时间完善框架
     *
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> JBF293KDeserializer<T> deserializerFor(Class<T> tClass) {
        JBF293KDeserializer<T> deserializer = (JBF293KDeserializer<T>) _rootDeserializers.get(tClass);
        return deserializer;
    }

    /**
     * 创建gb26875 报文解析器
     *
     * @param bytes
     * @return
     */
    public JBF293KParser parser(byte[] bytes) {
        return new JBF293KParser(bytes);
    }

    /**
     * 创建gb26875 报文解析器
     *
     * @param in
     * @return
     */
    public JBF293KParser parser(InputStream in) {
        return new JBF293KParser(in);
    }

    /**
     * 注册类型反序列化器
     *
     * @param type              类型
     * @param deserializerClass 反序列化器
     * @throws IllegalAccessException 越权访问异常
     * @throws InstantiationException 实例化异常
     */
    public void deserializerRegister(Type type, Class<? extends JBF293KDeserializer> deserializerClass) throws IllegalAccessException, InstantiationException {
        _rootDeserializers.put(type, deserializerClass.newInstance());
    }
}
