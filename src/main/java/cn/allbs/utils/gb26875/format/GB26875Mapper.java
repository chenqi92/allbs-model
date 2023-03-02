package cn.allbs.utils.gb26875.format;

import cn.allbs.utils.gb26875.exception.GB26875Exception;
import cn.allbs.utils.gb26875.format.der.GB26875Deserializer;

import java.io.IOException;
import java.util.Map;

/**
 * gb-26875 解析器调用
 *
 * @author ChenQi
 */
public class GB26875Mapper {

    private static GB26875Factory gb26875Factory;

    static {
        try {
            gb26875Factory = new GB26875Factory();
            // 目前只做序列化Map的,其他后续再说
            gb26875Factory.deserializerRegister(Map.class, DataMapDeserializer.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private GB26875Factory factory;

    public GB26875Mapper() {
        this.factory = gb26875Factory.copy();
    }

    public <T> T readValue(byte[] msg, Class<T> value) throws IOException, GB26875Exception {
        // 校验
        if (msg.length == 0) {
            throw new GB26875Exception("待解析报文为空!");
        }
        return _readValueAndClose(this.factory.parser(msg), value);
    }


    private <T> T _readValueAndClose(GB26875Parser gb26875Parser, Class<T> value) throws IOException, GB26875Exception {
        GB26875Deserializer<T> deserializer = this.factory.deserializerFor(value);
        try (GB26875Parser g = gb26875Parser) {
            return deserializer.deserialize(g);
        } catch (RuntimeException e) {
            throw new GB26875Exception("run time error", e.getLocalizedMessage());
        }
    }
}
