package cn.allbs.utils.gb26875.format.data;

import cn.allbs.utils.gb26875.enums.ConstEnum;

import java.io.IOException;

/**
 * 用户信息传输装置软件版本
 *
 * @author ChenQi
 */
public class Type25Parser extends AbstractParser {
    public Type25Parser(byte[] bytes) {
        super(bytes);
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
