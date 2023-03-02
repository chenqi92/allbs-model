package cn.allbs.utils.gb26875.enums;

import cn.allbs.utils.gb26875.utils.PacketElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum ConstEnum {

    /**
     * 系统类型标志
     */
    SYS_TYPE("sysType", 1),

    /**
     * 系统类型标志含义
     */
    SYS_TYPE_TRANS("sysTypeTrans", 1),

    /**
     * 系统地址
     */
    SYS_ADDRESS("sysAddress", 1),

    /**
     * 系统状态
     */
    SYS_DESC("sysDesc", 2),

    /**
     * 部件类型
     */
    PART_TYPE("partType", 1),

    /**
     * 部件类型翻译
     */
    PART_TYPE_TRANS("partTypeTrans", 1),

    /**
     * 部件地址
     */
    PART_ADDRESS("partAddress", 4),

    /**
     * 部件状态
     */
    PART_DESC("partDesc", 2),

    /**
     * 部件说明
     */
    PART_EXPLAIN("partExplain", 31),

    /**
     * 时间
     */
    TIME("time", 6),

    /**
     * 模拟量类型
     */
    AQ("aq", 1),

    /**
     * 模拟量值
     */
    AQ_NUM("aqNum", 2),

    /**
     * 操作员标识
     */
    OPERATE_TYPE("operateType", 1),

    /**
     * 操作员编号
     */
    OPERATOR_NUM("operatorNum", 2),

    /**
     * 主版本号
     */
    MAIN_VERSION("mainVersion", 1),

    /**
     * 次版本号
     */
    MINOR_VERSION("minorVersion", 1),

    /**
     * 系统配置说明长度
     */
    SYS_CONF_LEN("sysConfLen", 1),

    /**
     * 系统配置说明 内容
     */
    SYS_CONF_EXPLAIN("sysConfExplain", -0),

    /**
     * 运行状态
     */
    RUNNING_STATUS("runningStatus", 1),

    /**
     * 系统配置说明长度
     */
    TRANSMITTING_CONF_LEN("transmittingConfLen", 1),

    /**
     * 系统配置说明 内容
     */
    TRANSMITTING_CONF_EXPLAIN("transmittingConfExplain", -0),

    /**
     * 信息对象
     */
    MSG_DATA("msgData", -0),

    /**
     * 数据单元标识符-类型标志
     */
    MSG_DATA_TYPE("msgDataType", 1),

    /**
     * 数据单元标识符-类型标志
     */
    MSG_DATA_TYPE_TRANS("msgDataTypeTrans", 1),

    /**
     * 数据单元标识符-信息对象数目
     */
    MSG_DATA_NUM("msgDataNum", 1),

    /**
     * 流水号
     */
    FLOW("flow", PacketElement.SERIAL_NUM.getLen()),

    /**
     * 源地址
     */
    SOURCE_ADDRESS("sourceAddress", PacketElement.SOURCE_ADDRESS.getLen()),

    /**
     * 目的地址
     */
    TARGET_ADDRESS("targetAddress", PacketElement.TARGET_ADDRESS.getLen()),

    /**
     * 控制命令
     */
    CONTROL_ORDER("controlOrder", PacketElement.CONTROL_ORDER_LEN.getLen()),

    /**
     * 应用数据单元
     */
    DATA("data", PacketElement.APPLICATION_DATA_DATA.getLen()),

    /**
     * 时间
     */
    HAPPEN_TIME("happenTime", 6),
    ;

    /**
     * 信息体定义
     */
    private final String constDefined;

    /**
     * 所占字节数
     */
    private final Integer constNum;
}
