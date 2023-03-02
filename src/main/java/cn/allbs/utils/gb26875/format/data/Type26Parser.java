package cn.allbs.utils.gb26875.format.data;

import cn.allbs.utils.AsciiUtil;
import java.io.IOException;

import static cn.allbs.utils.gb26875.enums.ConstEnum.TRANSMITTING_CONF_EXPLAIN;

/**
 * 用户信息传输装置配置情况
 *
 * @author ChenQi
 */
public class Type26Parser extends AbstractParser {
    public Type26Parser(byte[] bytes) {
        super(bytes);
    }

    /**
     * 用户信息传输装置配置情况
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readTransmittingInfo() throws IOException {
        // 读取一个字节的长度
        short len = (short) (this.dataOutputStream.readByte() & 0xff);
        // 读取指定长度的系统配置说明
        byte[] explain = new byte[len];
        for (int i = 0; i < len; i++) {
            explain[i] = this.dataOutputStream.readByte();
        }
        this.dataMap.put(TRANSMITTING_CONF_EXPLAIN.getConstDefined(), AsciiUtil.gb18030ToUtf8(explain));
    }
}
