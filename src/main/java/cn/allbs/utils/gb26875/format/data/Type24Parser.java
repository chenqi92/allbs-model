package cn.allbs.utils.gb26875.format.data;

import cn.allbs.constant.DateConstant;
import cn.allbs.constant.StringPoolConstant;
import cn.allbs.utils.gb26875.enums.system.Type24SSEnum;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static cn.allbs.utils.gb26875.enums.ConstEnum.*;


/**
 * 用户信息传输装置操作信息
 *
 * @author ChenQi
 */
public class Type24Parser extends AbstractParser {
    public Type24Parser(byte[] bytes) {
        super(bytes);
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
        this.dataMap.put(OPERATE_TYPE.getConstDefined(), String.join(StringPoolConstant.COMMA, Type24SSEnum.binaryTrans(bytes)));
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
