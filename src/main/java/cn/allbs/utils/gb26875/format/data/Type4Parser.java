package cn.allbs.utils.gb26875.format.data;

import cn.allbs.constant.DateConstant;
import cn.allbs.constant.StringPoolConstant;
import cn.allbs.utils.gb26875.enums.system.Type1STEnum;
import cn.allbs.utils.gb26875.enums.system.Type4SSEnum;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static cn.allbs.utils.gb26875.enums.ConstEnum.*;

/**
 * 建筑消防设施操作
 *
 * @author ChenQi
 */
public class Type4Parser extends AbstractParser {
    public Type4Parser(byte[] bytes) {
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
     * 读取操作标志 1字节
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readOperateType() throws IOException {
        byte[] bytes = new byte[OPERATE_TYPE.getConstNum()];
        for (int i = 0; i < OPERATE_TYPE.getConstNum(); i++) {
            bytes[i] = this.dataOutputStream.readByte();
        }
        this.dataMap.put(OPERATE_TYPE.getConstDefined(), String.join(StringPoolConstant.COMMA, Type4SSEnum.binaryTrans(bytes)));
    }

    /**
     * 读取操作员编号 1字节
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readOperatorNum() throws IOException {
        short code = (short) (this.dataOutputStream.readByte() & 0xff);
        this.dataMap.put(OPERATOR_NUM.getConstDefined(), code);
    }

    /**
     * 读取报文传输时间
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readTime() throws IOException {
        short[] times = new short[HAPPEN_TIME.getConstNum()];
        for (int i = 0; i < HAPPEN_TIME.getConstNum(); i++) {
            times[5 - i] = (short) (dataOutputStream.readByte() & 0xff);
        }
        LocalDateTime time = LocalDateTime.of(times[0] + 2000, times[1], times[2], times[3], times[4], times[5]);
        this.dataMap.put(HAPPEN_TIME.getConstDefined(), time.format(DateTimeFormatter.ofPattern(DateConstant.NORM_DATETIME_PATTERN)));
    }
}
