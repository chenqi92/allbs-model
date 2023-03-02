package cn.allbs.utils.gb26875.properties;

import cn.allbs.utils.gb26875.enums.system.Type3AQEnum;
import lombok.Data;

/**
 * 建筑消防设施部件模拟量值
 *
 * @author ChenQi
 */
@Data
public class Message3 {

    /**
     * 系统类型标识
     */
    private Short sysType;

    /**
     * 系统类型标识含义
     */
    private String sysTypeTrans;

    /**
     * 系统地址
     */
    private Short sysAddress;

    /**
     * 部件类型标识
     */
    private Short partType;

    /**
     * 部件类型标识含义
     */
    private String partTypeTrans;

    /**
     * 模拟量类型
     */
    private Type3AQEnum aq;

    /**
     * 模拟量值
     */
    private Short aqNum;
}
