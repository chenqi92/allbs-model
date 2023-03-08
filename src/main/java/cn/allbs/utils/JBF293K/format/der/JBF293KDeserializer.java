package cn.allbs.utils.JBF293K.format.der;

import cn.allbs.exception.JBF293KException;
import cn.allbs.utils.JBF293K.format.JBF293KParser;

import java.io.IOException;

/**
 * 类 Gb293KDeserializer
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 10:39
 */
public interface JBF293KDeserializer<Target> {

    /**
     * @param parser 解析器
     * @return 所需类型
     * @throws IOException      io异常
     * @throws JBF293KException 自定义报文解析异常
     */
    Target deserialize(JBF293KParser parser) throws IOException, JBF293KException;
}
