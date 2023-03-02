package cn.allbs.utils.gb26875.properties;

import lombok.Data;

/**
 * @author ChenQi
 */
@Data
public class Message6 {

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
     * 系统说明长度
     */
    private Short sysDataLen;

    /**
     * 系统说明
     */
    private String sysData;
}
