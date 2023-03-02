package cn.allbs.utils.gb26875.format.der;

import cn.allbs.utils.gb26875.exception.GB26875Exception;
import cn.allbs.utils.gb26875.format.GB26875Parser;

import java.io.IOException;

/**
 * 报文转换器
 *
 * @author ChenQi
 */
public interface GB26875Deserializer<Target> {

    /**
     * @param parser 解析器
     * @return 所需类型
     * @throws IOException      io异常
     * @throws GB26875Exception 自定义报文解析异常
     */
    Target deserialize(GB26875Parser parser) throws IOException, GB26875Exception;
}
