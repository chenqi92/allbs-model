package cn.allbs.utils.SFJK200.enums;

/**
 * 枚举 PacketElement
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/8 11:15
 */
public enum PacketElement {

    /**
     * 地址码
     */
    ADDRESS(1),

    /**
     * 功能码
     */
    FUNCTION(1),

    /**
     * 数据长度
     */
    DATA_LEN(1),

    /**
     * 数据码
     */
    DATA(-0),

    /**
     * crc校验码
     */
    CRC_CHECK(2);

    private final int len;

    PacketElement(int len) {
        this.len = len;
    }

    public int getLen() {
        return len;
    }
}
