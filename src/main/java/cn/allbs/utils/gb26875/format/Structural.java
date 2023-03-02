package cn.allbs.utils.gb26875.format;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 报文外层结构
 *
 * @author ChenQi
 */
@Data
public class Structural {

    /**
     * 流水号
     */
    private Integer serialNum;

    /**
     * 主版本号
     */
    private Integer mainVersion;

    /**
     * 用户版本号
     */
    private Integer userVersion;

    /**
     * 时间标签
     */
    private LocalDateTime time;

    /**
     * 源地址
     */
    private String sourceAddress;

    /**
     * 目的地址
     */
    private String targetAddress;

    /**
     * 应用单元
     */
    private Integer au;

    /**
     * 控制单元
     */
    private Integer cu;
}
