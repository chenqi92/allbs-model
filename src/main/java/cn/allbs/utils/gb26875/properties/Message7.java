package cn.allbs.utils.gb26875.properties;

import lombok.Data;

/**
 * 建筑消防设施系统部件配置情况
 * @author ChenQi
 */
@Data
public class Message7 {

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
     * 部件地址
     */
    private String partAddress;

    /**
     * 部件说明
     */
    private String partExplain;
}
