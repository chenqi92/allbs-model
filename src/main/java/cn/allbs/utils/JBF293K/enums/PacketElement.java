package cn.allbs.utils.JBF293K.enums;

/**
 * 类 PacketElement
 * </p>
 * 报文包内容
 *
 * @author ChenQi
 * @since 2023/2/21 10:13
 */
public enum PacketElement {

    /**
     * 起始字节
     */
    START(1),

    D1(2),

    D2(2),

    D3(2),

    D4(2),

    D5(2),
    D6(2),

    D7(2),

    D8(2),

    D9(2),

    D10(2),

    D11(2),

    D12(2),

    D13(2),

    D14(2),

    D15(2),

    D16(2),

    CRC(2),

    /**
     * 结束字节
     */
    END(1);
    /**
     * 字节长度
     */
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
