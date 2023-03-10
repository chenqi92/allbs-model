package cn.allbs.utils.SFJK200.format.data;

import cn.allbs.utils.SFJK200.enums.GasEnum;
import cn.allbs.utils.SFJK200.enums.UnitEnum;

import java.io.IOException;
import java.util.*;

import static cn.allbs.utils.SFJK200.enums.KeyWordEnum.*;

/**
 * 类 AttributeUnitParser
 * </p>
 * 点位的属性单位解析器
 *
 * @author ChenQi
 * @since 2023/3/9 11:22
 */
public class AttributeUnitParser extends AbstractParser {

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
            // 单位
            int unit = bytes[2 * i] & 0xff;
            map.put(GAS_UNIT_PRE.getKey(), unit);
            // 气体类型
            int type = bytes[2 * i + 1] & 0xff;
            map.put(GAS_TYPE_PRE.getKey(), type);
            UnitEnum unitEnum = Optional.of(UnitEnum.UNIT_ENUM_MAP).map(a -> a.get(unit)).orElse(GasEnum.GAS_ENUM_MAP.get(type).getUnit());
            map.put(GAS_UNIT.getKey(), unitEnum.getUnit());
            // 气体类型
            map.put(GAS_TYPE.getKey(), GasEnum.GAS_ENUM_MAP.get(type).getDesc());
            list.add(map);
        }
        return list;
    }
}
