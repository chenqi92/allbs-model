package cn.allbs.utils.JBF293K.enums;

import lombok.Getter;

/**
 * 类 KeyWordEnums
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 17:02
 */
@Getter
public enum KeyWordEnums {

    /**
     * 机器号
     */
    MACHINE_NO("机器号", "machineNo"),

    TIME("时间", "time", 12),

    /**
     * 版本号，包含高位地位总共4字节
     */
    VERSION("版本号", "version", 4),

    ORDER("命令", "order", "preOrder", 2),

    CODE("代码", "code"),

    CONTROLLER("控制器", "controller"),

    MESSAGE_INFO("信息类型", "message", "preMessage"),

    CIRCUIT("回路", "circuit"),

    PART("部位", "part"),

    DISK("盘号", "disk", 1),

    DISTRICT("区号", "district", 1),

    BOARD_NUMBER("板号", "boardNumber", 2),

    LINE("专线号", "line", 1),

    ADDRESS("地址", "address"),

    TYPE("部件类型", "type", "preType", 1),

    STATUS("状态", "status", "preStatus", 1),

    SENSOR_CHANNEL("传感器通道", "sensorChannel"),

    ALARM_VALUE("报警值", "alarmValue", 4),

    ACCUMULATE_SUM("累加和", "accumulateSum"),


    UNIT("单位", "unit"),

    CRC("CRC8校验", "accumulateSum"),
    ;

    private final String trans;

    private final String name;

    private final String preName;

    /**
     * 所占字节长度
     */
    private final Integer len;

    KeyWordEnums(String trans, String name) {
        this.trans = trans;
        this.name = name;
        this.preName = name;
        // 默认两个字节
        this.len = 2;
    }

    KeyWordEnums(String trans, String name, int len) {
        this.trans = trans;
        this.name = name;
        this.preName = name;
        // 默认两个字节
        this.len = len;
    }

    KeyWordEnums(String trans, String name, String preName) {
        this.trans = trans;
        this.name = name;
        this.preName = preName;
        // 默认两个字节
        this.len = 2;
    }

    KeyWordEnums(String trans, String name, String preName, int len) {
        this.trans = trans;
        this.name = name;
        this.preName = preName;
        // 默认两个字节
        this.len = len;
    }
}
