package cn.allbs.utils.gb26875.properties;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ChenQi
 */
@Data
public class Message2 {

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
     * 部件状态
     */
    private List<String> partDesc;

    /**
     * 部件说明
     */
    private String partExplain;

    /**
     * 消息体的时间
     */
    private LocalDateTime time;
}
