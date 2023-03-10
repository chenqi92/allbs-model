package cn.allbs.utils.SFJK200.format.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类 AbstractParser
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/9 10:56
 */
public abstract class AbstractParser implements IParser {

    /**
     * 读取数据内容
     *
     * @throws IOException
     */
    @Override
    public List<Map<String, Object>> readData(byte[] bytes) throws IOException {
        return new ArrayList<>();
    }
}
