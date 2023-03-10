package cn.allbs.utils.SFJK200.format;

import cn.allbs.exception.SFJK200Exception;
import cn.allbs.utils.SFJK200.format.der.DataMapDeserializer;
import cn.allbs.utils.SFJK200.format.der.SFJK200Deserializer;
import cn.allbs.utils.SFJK200.format.ser.DataMapSerializer;
import cn.allbs.utils.SFJK200.format.ser.SFJK200Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * 类 SFK200Mapper
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/8 15:53
 */
public class SFJK200Mapper {

    private static SFJK200Factory sfjk200Factory;

    static {
        try {
            sfjk200Factory = new SFJK200Factory();
            // 目前只做序列化Map的,其他后续再说
            sfjk200Factory.deserializerRegister(Map.class, DataMapDeserializer.class);
            sfjk200Factory.serializerRegister(Map.class, DataMapSerializer.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private SFJK200Factory factory;

    public SFJK200Mapper() {
        this.factory = sfjk200Factory.copy();
    }

    public <T> T readValue(byte[] msg, int register, Class<T> value) throws IOException, SFJK200Exception {
        // 校验
        if (msg.length == 0) {
            throw new SFJK200Exception("待解析报文为空!");
        }
        return _readValueAndClose(this.factory.parser(msg, register), value);
    }


    private <T> T _readValueAndClose(SFJK200Parser sfjk200Parser, Class<T> value) throws IOException, SFJK200Exception {
        SFJK200Deserializer<T> deserializer = this.factory.deserializerFor(value);
        try (SFJK200Parser g = sfjk200Parser) {
            return deserializer.deserialize(g);
        } catch (RuntimeException e) {
            throw new SFJK200Exception("run time error", e.getLocalizedMessage());
        }
    }

    public <T> void writeValueAsStream(T value, Class<T> type, ByteArrayOutputStream outputStream) throws IOException, SFJK200Exception {
        _writeValueAndClose(factory.generator(outputStream), value, type);
    }

    private <T> void _writeValueAndClose(SFJK200Generator generator, T value, Class<T> type) throws IOException, SFJK200Exception {
        SFJK200Serializer<T> serializer = factory.serializerFor(type);
        try (SFJK200Generator g = generator) {
            serializer.serialize(g, value);
        } catch (RuntimeException e) {
            throw new SFJK200Exception("Runtime error", e);
        }
    }

    public String writeDataAsString(Map<String, Object> data) throws IOException, SFJK200Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        writeValueAsStream(data, Map.class, os);
        return new String(os.toByteArray(), StandardCharsets.UTF_8);
    }

    public byte[] writeDataAsByteArray(Map<String, Object> data) throws IOException, SFJK200Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        writeValueAsStream(data, Map.class, os);
        return os.toByteArray();
    }
}
