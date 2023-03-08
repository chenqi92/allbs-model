package cn.allbs.utils.JBF293K.format.data;

import java.io.IOException;
import java.io.InputStream;

import static cn.allbs.utils.JBF293K.enums.PacketElement.*;
import static cn.allbs.utils.JBF293K.enums.KeyWordEnums.MACHINE_NO;

/**
 * 类 HeartParser
 * </p>
 * 心跳包解析器
 *
 * @author ChenQi
 * @since 2023/2/21 17:12
 */
public class HeartParser extends AbstractParser {

    public HeartParser(InputStream in) {
        super(in);
    }

    /**
     * 读取机器号
     *
     * @throws Exception
     */
    protected void readMachineNo() throws IOException {
        short machineNo = handleByte(MACHINE_NO.getLen());
        this.dataMap.put(MACHINE_NO.getName(), machineNo);
    }

    /**
     * 读取不解析区的数据
     *
     * @throws IOException
     */
    protected void readNullInfo() throws IOException {
        // 只加如校验和不处理具体内容
        handleByte(D3.getLen());
        handleByte(D4.getLen());
        handleByte(D5.getLen());
    }
}
