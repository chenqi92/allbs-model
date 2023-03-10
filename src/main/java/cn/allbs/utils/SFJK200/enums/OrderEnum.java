package cn.allbs.utils.SFJK200.enums;

import cn.allbs.utils.SFJK200.format.data.*;
import lombok.Getter;

/**
 * 枚举 OrderEnum
 * </p>
 * 命令说明
 *
 * @author ChenQi
 * @since 2023/3/8 14:24
 */
@Getter
public enum OrderEnum {

    /**
     * 读 1、2 回路配置点数
     */
    NUMBER_OF_PLACEMENT_1_2("读 1、2 回路配置点数", 0x00, 0x01, new PlacementParser()),

    /**
     * 读 3、4 回路配置点数
     */
    NUMBER_OF_PLACEMENT_3_4("读 3、4 回路配置点数", 0x01, 0x01, new PlacementParser()),

    /**
     * 1 回路 1-248 点位的属性单位
     */
    ATTRIBUTE_UNITS_1("1 回路 1-248 点位的属性单位", 0x0002, 0x00f9, 0x01, 0x64, new AttributeUnitParser()),

    /**
     * 2 回路 1-248 点位的属性单位
     */
    ATTRIBUTE_UNITS_2("2 回路 1-248 点位的属性单位", 0x00fa, 0x01f1, 0x01, 0x64, new AttributeUnitParser()),

    /**
     * 3 回路 1-248 点位的属性单位
     */
    ATTRIBUTE_UNITS_3("3 回路 1-248 点位的属性单位", 0x01f2, -0x02e9, 0x01, 0x64, new AttributeUnitParser()),

    /**
     * 4 回路 1-248 点位的属性单位
     */
    ATTRIBUTE_UNITS_4("4 回路 1-248 点位的属性单位", 0x02ea, 0x03e1, 0x01, 0x64, new AttributeUnitParser()),

    /**
     * 1 回路 1-248 点位的低限报警值
     */
    LOW_ALARM_VALUE_1("1 回路 1-248 点位的低限报警值", 0x03e2, 0x04d9, 0x01, 0x64, new LowAlarmParser()),

    /**
     * 2 回路 1-248 点位的低限报警值
     */
    LOW_ALARM_VALUE_2("2 回路 1-248 点位的低限报警值", 0x04da, 0x05d1, 0x01, 0x64, new LowAlarmParser()),

    /**
     * 3 回路 1-248 点位的低限报警值
     */
    LOW_ALARM_VALUE_3("3 回路 1-248 点位的低限报警值", 0x05d2, 0x06c9, 0x01, 0x64, new LowAlarmParser()),

    /**
     * 4 回路 1-248 点位的低限报警值
     */
    LOW_ALARM_VALUE_4("4 回路 1-248 点位的低限报警值", 0x06ca, 0x07c1, 0x01, 0x64, new LowAlarmParser()),

    /**
     * 1 回路 1-248 点位的探测器状态
     */
    PROBE_STATUS_1("1 回路 1-248 点位的探测器状态", 0x07c2, 0x08b9, 0x01, 0x64, new ProbeStatusParser()),

    /**
     * 2 回路 1-248 点位的探测器状态
     */
    PROBE_STATUS_2("2 回路 1-248 点位的探测器状态", 0x08ba, 0x09b1, 0x01, 0x64, new ProbeStatusParser()),

    /**
     * 3 回路 1-248 点位的探测器状态
     */
    PROBE_STATUS_3("3 回路 1-248 点位的探测器状态", 0x09b2, 0x0aa9, 0x01, 0x64, new ProbeStatusParser()),

    /**
     * 4 回路 1-248 点位的探测器状态
     */
    PROBE_STATUS_4("4 回路 1-248 点位的探测器状态", 0x0aaa, 0x0ba1, 0x01, 0x64, new ProbeStatusParser()),

    /**
     * 1 回路 1-248 点位的探测器浓度
     */
    DETECTOR_CONCENTRATION_1("1 回路 1-248 点位的探测器浓度", 0x0ba2, 0x0c99, 0x01, 0x64, new DetectorParser()),

    /**
     * 2 回路 1-248 点位的探测器浓度
     */
    DETECTOR_CONCENTRATION_2("2 回路 1-248 点位的探测器浓度", 0x0c9a, 0x0d91, 0x01, 0x64, new DetectorParser()),

    /**
     * 3 回路 1-248 点位的探测器浓度
     */
    DETECTOR_CONCENTRATION_3("3 回路 1-248 点位的探测器浓度", 0x0d92, 0x0e89, 0x01, 0x64, new DetectorParser()),

    /**
     * 4 回路 1-248 点位的探测器浓度
     */
    DETECTOR_CONCENTRATION_4("4 回路 1-248 点位的探测器浓度", 0x0e8a, 0x0f81, 0x01, 0x64, new DetectorParser()),

    /**
     * 1 回路外控电源状态
     */
    ECPS_STATUS_1("1 回路外控电源状态", 0x0f82, 0x0f83, 0x02, new EcpsStatusParser()),

    /**
     * 2 回路外控电源状态
     */
    ECPS_STATUS_2("2 回路外控电源状态", 0x0f84, 0x0f85, 0x02, new EcpsStatusParser()),

    /**
     * 3 回路外控电源状态
     */
    ECPS_STATUS_3("3 回路外控电源状态", 0x0f86, 0x0f87, 0x02, new EcpsStatusParser()),

    /**
     * 4 回路外控电源状态
     */
    ECPS_STATUS_4("4 回路外控电源状态", 0x0f88, 0x0f89, 0x02, new EcpsStatusParser()),

    /**
     * 控制状态
     */
    CONTROL_STATUS("控制状态", 0x0f8a, 0x01, new ControlStatusParser());

    /**
     * 属性说明
     */
    private final String desc;

    private final int startAddress;

    private final Integer endAdress;

    private final int startRegister;

    private final Integer endRegister;

    private final AbstractParser abstractParser;

    OrderEnum(String desc, int startAddress, int startRegister, AbstractParser abstractParser) {
        this.desc = desc;
        this.startAddress = startAddress;
        this.endAdress = null;
        this.startRegister = startRegister;
        this.endRegister = null;
        this.abstractParser = abstractParser;
    }

    OrderEnum(String desc, int startAddress, Integer endAddress, int startRegister, AbstractParser abstractParser) {
        this.desc = desc;
        this.startAddress = startAddress;
        this.endAdress = endAddress;
        this.startRegister = startRegister;
        this.endRegister = null;
        this.abstractParser = abstractParser;
    }

    OrderEnum(String desc, int startAddress, Integer endAddress, int startRegister, int endRegister, AbstractParser abstractParser) {
        this.desc = desc;
        this.startAddress = startAddress;
        this.endAdress = endAddress;
        this.startRegister = startRegister;
        this.endRegister = endRegister;
        this.abstractParser = abstractParser;
    }

    /**
     * 根据寄存器地址查询命令类型
     *
     * @param register 寄存器地址
     * @return 命令类型
     */
    public static OrderEnum orderInfo(int register) {
        OrderEnum[] orderEnums = OrderEnum.values();
        for (OrderEnum order : orderEnums) {
            if (order.getStartAddress() == register || (order.getEndAdress() != null && order.getStartAddress() <= register && order.getEndAdress() >= register)) {
                return order;
            }
        }
        return null;
    }
}
