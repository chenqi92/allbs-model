package cn.allbs.utils.SFJK200.format.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static cn.allbs.utils.SFJK200.enums.KeyWordEnum.CIRCUIT;
import static cn.allbs.utils.SFJK200.enums.KeyWordEnum.POINT;

/**
 * 类 PlacementParser
 * </p>
 * 配置点数解析
 *
 * @author ChenQi
 * @since 2023/3/9 11:18
 */
public class PlacementParser extends AbstractParser {

    /**
     * 读取数据内容
     *
     * @throws IOException
     */
    public List<Map<String, Object>> readData(byte[] bytes) throws IOException {
        List<Map<String, Object>> list = new LinkedList<>();
        for (int i = 0; i < bytes.length; i++) {
            Map<String, Object> map = new HashMap<>(2);
            map.put(CIRCUIT.getKey(), i + 1);
            map.put(POINT.getKey(), bytes[i] & 0xff);
            list.add(map);
        }
        return list;
    }

}
