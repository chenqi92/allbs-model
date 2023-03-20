package cn.allbs.utils.gb26875.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum ControlUnitEnum {

    /**
     * 预留
     */
    _0(0, "预留"),

    _1(1, "控制命令"),

    _2(2, "发送数据"),

    _3(3, "确认"),

    _4(4, "请求"),

    _5(5, "应答"),

    _6(6, "否认"),

    /**
     * 7~127 为预留
     */
    _127(127, "预留"),

    /**
     * 128~255为用户自定义
     */
    _255(255, "用户自定义");

    private final Integer num;

    private final String dsc;

    public static String orderTrans(short n) {
        ControlUnitEnum[] enums = ControlUnitEnum.values();
        for (ControlUnitEnum cu : enums) {
            if (cu.num.equals((int) n)) {
                return cu.dsc;
            }
        }
        return "未定义的命令!与传输方确定!";
    }
}
