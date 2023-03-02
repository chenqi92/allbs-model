package cn.allbs.utils.gb26875.format.data;

import cn.allbs.constant.StringPoolConstant;
import cn.allbs.utils.gb26875.enums.ConstEnum;
import cn.allbs.utils.gb26875.enums.system.Type21SSEnum;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static cn.allbs.utils.gb26875.enums.ConstEnum.HAPPEN_TIME;


/**
 * 用户信息传输装置运行状态
 *
 * @author ChenQi
 */
public class Type21Parser extends AbstractParser {
    public Type21Parser(byte[] bytes) {
        super(bytes);
    }

    /**
     * 读取用户信息传输装置运行状态
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readRunningStatus() throws IOException {
        byte[] bytes = new byte[ConstEnum.RUNNING_STATUS.getConstNum()];
        for (int i = 0; i < ConstEnum.RUNNING_STATUS.getConstNum(); i++) {
            bytes[i] = dataOutputStream.readByte();
        }
        this.dataMap.put(ConstEnum.RUNNING_STATUS.getConstDefined(), CollUtil.join(Type21SSEnum.binaryTrans(bytes), StringPoolConstant.COMMA));
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
        this.dataMap.put(HAPPEN_TIME.getConstDefined(), time.format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
    }
}
