package cn.allbs.utils.SFJK200.format.der;

import cn.allbs.exception.JBF293KException;
import cn.allbs.exception.SFJK200Exception;
import cn.allbs.utils.SFJK200.format.SFJK200Parser;

import java.io.IOException;

/**
 * 类 SFJK200Deserializer
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/8 15:54
 */
public interface SFJK200Deserializer<Target> {

    /**
     * @param parser 解析器
     * @return 所需类型
     * @throws IOException      io异常
     * @throws JBF293KException 自定义报文解析异常
     */
    Target deserialize(SFJK200Parser parser) throws IOException, SFJK200Exception;
}
