package cn.allbs.utils.SFJK200.format.data;

import cn.allbs.utils.SFJK200.enums.ExterPowerEnum;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static cn.allbs.utils.SFJK200.enums.KeyWordEnum.EXTRA_POWER;
import static cn.allbs.utils.SFJK200.enums.KeyWordEnum.EXTRA_POWER_PRE;

/**
 * 类 EcpsStatusParser
 * </p>
 * 外控电源状态
 *
 * @author ChenQi
 * @since 2023/3/9 11:31
 */
public class EcpsStatusParser extends AbstractParser {
    /**
     * 读取数据内容
     *
     * @throws IOException
     */
    public List<Map<String, Object>> readData(byte[] bytes) throws IOException {
        List<Map<String, Object>> list = new LinkedList<>();
        for (int i = 0; i < bytes.length; i++) {
            Map<String, Object> map = new HashMap<>();
            int status = bytes[i] & 0xff;
            map.put(EXTRA_POWER_PRE.getKey(), status);
            map.put(EXTRA_POWER.getKey(), ExterPowerEnum.EXTRA_POWER_MAP.get(status));
            list.add(map);
        }
        return list;
    }
}
