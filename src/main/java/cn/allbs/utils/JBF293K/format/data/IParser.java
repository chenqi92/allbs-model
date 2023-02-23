package cn.allbs.utils.JBF293K.format.data;

import cn.allbs.utils.JBF293K.exception.JBF293KException;

import java.io.IOException;
import java.util.Map;

/**
 * 类 IParser
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 10:30
 */
public interface IParser {

    /**
     * 初始化报文解析
     *
     * @throws IOException IO 异常
     */
    void create() throws IOException, JBF293KException;

    /**
     * 获取转换结果
     *
     * @return 转换结果
     */
    Map<String, Object> parseMap();

    /**
     * 获取指定读取的长度
     *
     * @return
     */
    int currentReadLen();
}
