package cn.allbs.utils.JBF293K.format;

import cn.allbs.exception.JBF293KException;
import cn.allbs.feature.Feature;
import cn.allbs.feature.VerifyFeature;
import cn.allbs.utils.JBF293K.format.der.JBF293KDeserializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 类 GBF293K
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 10:38
 */
public class JBF293KMapper {

    private static JBF293KFactory jbf293KFactory;

    private int verifyFeatures;

    static {
        try {
            jbf293KFactory = new JBF293KFactory();
            // 目前只做序列化Map的,其他后续再说
            jbf293KFactory.deserializerRegister(Map.class, DataMapDeserializer.class);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private JBF293KFactory factory;

    public JBF293KMapper() {
        this.factory = jbf293KFactory.copy();
    }

    public JBF293KMapper enableDefaultVerifyFeatures() {
        verifyFeatures = verifyFeatures | Feature.collectFeatureDefaults(VerifyFeature.class);
        return this;
    }

    public JBF293KMapper enable(VerifyFeature feature) {
        verifyFeatures = verifyFeatures | feature.getMask();
        return this;
    }

    public JBF293KMapper disable(VerifyFeature feature) {
        verifyFeatures = verifyFeatures & ~feature.getMask();
        return this;
    }

    public <T> T readValue(byte[] msg, Class<T> value) throws IOException, JBF293KException {
        // 校验
        if (msg.length == 0) {
            throw new JBF293KException("待解析报文内容为空!");
        }
        return _readValueAndClose(this.factory.parser(msg), value);
    }

    public <T> T readValue(InputStream in, Class<T> value) throws IOException, JBF293KException {
        // 校验
        if (in.available() == 0) {
            throw new JBF293KException("待解析报文内容为空!");
        }
        return _readValueAndClose(this.factory.parser(in), value);
    }


    private <T> T _readValueAndClose(JBF293KParser gb26875Parser, Class<T> value) throws IOException, JBF293KException {
        JBF293KDeserializer<T> deserializer = this.factory.deserializerFor(value);
        try (JBF293KParser g = gb26875Parser) {
            return deserializer.deserialize(g);
        } catch (RuntimeException e) {
            throw new JBF293KException("run time error", e.getLocalizedMessage());
        }
    }
}
