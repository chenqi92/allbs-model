package cn.allbs.utils.gb26875.enums;

/**
 * @author ChenQi
 */
public enum PacketElement {

    /**
     * 报文头 固定为@ @
     */
    HEADER(2),

    /**
     * 流水号
     */
    SERIAL_NUM(2),

    /**
     * 主版本号
     */
    MAIN_VERSION(1),

    /**
     * 用户版本号
     */
    USER_VERSION(1),

    /**
     * 时间标签
     */
    TIME(6),

    /**
     * 源地址 低字节在前
     */
    SOURCE_ADDRESS(6),

    /**
     * 目标地址 低字节在前
     */
    TARGET_ADDRESS(6),

    /**
     * 应用数据单元长度
     */
    APPLICATION_DATA_LEN(2),

    /**
     * 控制单元命令长度
     */
    CONTROL_ORDER_LEN(1),

    /**
     * 应用数据单元
     */
    APPLICATION_DATA_DATA(-0),

    /**
     * 校验位长度 控制单元中各字节数据(第3~27字节)及 应 用 数 据 单 元 的 算 术 校 验 和,舍 去 8 位以上的进位位后所形成的1字节二进制数
     */
    VERIFY(1),

    /**
     * 报文尾部 固定为# #
     */
    FOOTER(2);

    private int len;

    PacketElement(int len) {
        this.len = len;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
