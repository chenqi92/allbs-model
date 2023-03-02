package cn.allbs.utils.gb26875.format.data;


import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.gb26875.enums.system.Type1STEnum;

import java.io.IOException;
import java.util.Optional;

import static cn.allbs.utils.gb26875.enums.ConstEnum.*;


/**
 * 建筑消防设施系统配置
 *
 * @author ChenQi
 */
public class Type6Parser extends AbstractParser {
    public Type6Parser(byte[] bytes) {
        super(bytes);
    }

    /**
     * 读取一个字节的系统类型标志
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readSysType() throws IOException {
        short type = (short) (this.dataOutputStream.readByte() & 0xff);
        this.dataMap.put(SYS_TYPE.getConstDefined(), type);
        this.dataMap.put(SYS_TYPE_TRANS.getConstDefined(), Optional.of(Type1STEnum.TYPE_1_MAP).map(a -> a.get(type)).orElse("未定义"));
    }

    /**
     * 读取一个字节的系统地址
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readSysAddress() throws IOException {
        short address = (short) (this.dataOutputStream.readByte() & 0xff);
        this.dataMap.put(SYS_ADDRESS.getConstDefined(), address);
    }

    /**
     * 系统配置
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readSysConfig() throws IOException {
        // 读取一个字节的长度
        short len = (short) (this.dataOutputStream.readByte() & 0xff);
        // 读取指定长度的系统配置说明
        byte[] explain = new byte[len];
        for (int i = 0; i < len; i++) {
            explain[i] = this.dataOutputStream.readByte();
        }
        this.dataMap.put(SYS_CONF_EXPLAIN.getConstDefined(), AsciiUtil.gb18030ToUtf8(explain));
    }
}
