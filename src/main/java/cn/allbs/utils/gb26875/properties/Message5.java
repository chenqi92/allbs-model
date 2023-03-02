package cn.allbs.utils.gb26875.properties;

import lombok.Data;

/**
 * @author ChenQi
 */
@Data
public class Message5 {

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
     * 主版本号
     */
    private Short mainVersion;

    /**
     * 次版本号
     */
    private Short minorVersion;
}
