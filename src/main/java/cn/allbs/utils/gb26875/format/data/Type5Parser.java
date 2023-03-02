package cn.allbs.utils.gb26875.format.data;


import cn.allbs.utils.gb26875.enums.ConstEnum;
import cn.allbs.utils.gb26875.enums.system.Type1STEnum;

import java.io.IOException;
import java.util.Optional;

import static cn.allbs.utils.gb26875.enums.ConstEnum.*;


/**
 * 建筑消防设施软件版本
 *
 * @author ChenQi
 */
public class Type5Parser extends AbstractParser {
    public Type5Parser(byte[] bytes) {
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
     * 读取主版本号
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readMainVersion() throws IOException {
        this.dataMap.put(ConstEnum.MAIN_VERSION.getConstDefined(), (short) (this.dataOutputStream.readByte() & 0xff));
    }

    /**
     * 读取主版本号
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readMinorVersion() throws IOException {
        this.dataMap.put(ConstEnum.MINOR_VERSION.getConstDefined(), (short) (this.dataOutputStream.readByte() & 0xff));
    }

}
