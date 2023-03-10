package cn.allbs.utils.SFJK200.format.data;

import cn.allbs.utils.SFJK200.enums.DetectorEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static cn.allbs.utils.SFJK200.enums.KeyWordEnum.DETECTOR_STATUS;
import static cn.allbs.utils.SFJK200.enums.KeyWordEnum.DETECTOR_STATUS_PRE;


/**
 * 类 ProbeStatusParser
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/9 11:29
 */
public class ProbeStatusParser extends AbstractParser {
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
            // 高字节 预留
            int higher = bytes[2 * i] & 0xff;
            // 低字节
            int lower = bytes[2 * i + 1] & 0xff;
            List<String> detectorList = DetectorEnum.binaryTrans(bytes[2 * i + 1]);
            map.put(DETECTOR_STATUS.getKey(), detectorList);
            map.put(DETECTOR_STATUS_PRE.getKey(), lower);
            list.add(map);
        }
        return list;
    }
}
