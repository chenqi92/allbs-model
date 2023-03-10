package cn.allbs.utils.SFJK200.format.data;

import cn.allbs.exception.SFJK200Exception;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 接口 IParser
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/9 10:38
 */
public interface IParser {

    /**
     * 初始化报文解析
     *
     * @throws IOException IO 异常
     */
    List<Map<String, Object>> readData(byte[] bytes) throws IOException, SFJK200Exception;
}
