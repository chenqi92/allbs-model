package cn.allbs.utils.SFJK200.format.data;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static cn.allbs.utils.SFJK200.enums.KeyWordEnum.DETECTOR_NUM;

/**
 * 类 DetectorParser
 * </p>
 * 探测器浓度解析器
 *
 * @author ChenQi
 * @since 2023/3/9 11:30
 */
public class DetectorParser extends AbstractParser {

    /**
     * 读取数据内容
     *
     * @throws IOException
     */
    public List<Map<String, Object>> readData(byte[] bytes) throws IOException {
        List<Map<String, Object>> list = new LinkedList<>();
        int group = bytes.length / 2;
        for (int i = 0; i < group; i++) {
            Map<String, Object> map = new HashMap<>();
            // 高字节
            int higher = bytes[2 * i] & 0xff;
            int lower = bytes[2 * i + 1] & 0xff;
            // 根据后续的指令区分
            map.put(DETECTOR_NUM.getKey(), BigDecimal.valueOf((higher * 256 + lower)).divide(BigDecimal.valueOf(10), 1, RoundingMode.HALF_UP));
            list.add(map);
        }
        return list;
    }
}
