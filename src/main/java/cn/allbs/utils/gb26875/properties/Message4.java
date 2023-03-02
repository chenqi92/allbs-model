package cn.allbs.utils.gb26875.properties;

import lombok.Data;

import java.util.List;

/**
 * 建筑消防设施操作信息
 *
 * @author ChenQi
 */
@Data
public class Message4 {
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
     * 操作标志
     */
    private List<String> operateDesc;

    /**
     * 操作员编号
     */
    private Short operateNo;
}
