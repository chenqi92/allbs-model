package cn.allbs.utils.gb26875.properties;

import lombok.Data;

import java.util.List;

/**
 * 建筑消防设施系统消息体
 *
 * @author ChenQi
 */
@Data
public class Message1 {

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
     * 系统状态
     */
    private List<String> sysDesc;
}
