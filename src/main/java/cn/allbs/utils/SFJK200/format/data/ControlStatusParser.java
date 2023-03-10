package cn.allbs.utils.SFJK200.format.data;

import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.SFJK200.enums.KeyWordEnum;
import cn.allbs.utils.SFJK200.enums.MonitorEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 类 ControlStatusParser
 * </p>
 * 控制器状态
 *
 * @author ChenQi
 * @since 2023/3/9 11:50
 */
public class ControlStatusParser extends AbstractParser {

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
            byte[] data = new byte[2];
            data[0] = bytes[i * 2];
            data[1] = bytes[i * 2 + 1];
            List<String> dataList = MonitorEnum.binaryTrans(data);
            map.put(KeyWordEnum.CONTROLLER_STATUS.getKey(), dataList);
            map.put(KeyWordEnum.CONTROLLER_STATUS_PRE.getKey(), AsciiUtil.bytesToShort(data));
            list.add(map);
        }
        return list;
    }
}
