package cn.allbs.utils.gb26875.enums.system;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 建筑消防设施含义
 * 低字节传输在前
 *
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum Type1SSEnum {

    /**
     *
     */
    _0("测试状态", "正常运行状态"),

    _1("无火警", "火警"),

    _2("无故障", "故障"),

    _3("无屏蔽", "屏蔽"),

    _4("无监督", "监督"),

    _5("停止(关闭)", "启动(开启)"),

    _6("无反馈", "反馈"),

    _7("未延时", "延时"),

    _8("主电正常", "主电故障"),

    _9("备电正常", "备电故障"),

    _10("总线正常", "总线故障"),

    _11("自动状态", "手动状态"),

    _12("无配置改变", "配置改变"),

    _13("正常", "复位"),

    /**
     * 预留
     */
    _14("", ""),

    /**
     * 预留
     */
    _15("", "");

    private static final Table<Integer, String, String> DES_TABLE = HashBasedTable.create();

    static {
        Type1SSEnum[] enums = Type1SSEnum.values();
        for (Type1SSEnum e : enums) {
            e.binaryTransMap.forEach((k, v) -> {
                DES_TABLE.put(Convert.toInt(e.name().substring(1)), k, v);
            });
        }
    }

    /**
     * 二进制0位代表的意思
     */
    private final Map<String, String> binaryTransMap = new HashMap<>();

    Type1SSEnum(String bTrans0, String bTrans1) {
        this.binaryTransMap.put("0", bTrans0);
        this.binaryTransMap.put("1", bTrans1);
    }

    /**
     * @param bs
     * @return
     */
    public static List<String> binaryTrans(byte[] bs) {
        List<String> resList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            sb.append(String.format("%08d", Convert.toLong(Integer.toBinaryString(b))));
        }
        char[] binaryChars = sb.toString().toCharArray();
        int len = binaryChars.length;
        for (int i = 0; i < len; i++) {
            if (DES_TABLE.contains(len - 1 - i, Convert.toStr(binaryChars[i]))) {
                String trans = DES_TABLE.get(len - 1 - i, Convert.toStr(binaryChars[i]));
                if (StrUtil.isNotBlank(trans)) {
                    resList.add(trans);
                }
            }
        }
        return CollUtil.reverse(resList);
    }

}
