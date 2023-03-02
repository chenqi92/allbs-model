package cn.allbs.utils.gb26875.format.data;

import java.io.IOException;
import java.util.Map;

/**
 * 基础转化类
 *
 * @author ChenQi
 */
public interface IParser {

    /**
     * 初始化报文解析
     *
     * @throws IOException IO 异常
     */
    void create() throws IOException;

    /**
     * 获取转换结果
     *
     * @return 转换结果
     */
    Map<String, Object> parseMap();

}
