package cn.allbs.utils.JBF293K.format.data;

import cn.allbs.utils.JBF293K.enums.AlarmEnum;
import cn.allbs.utils.JBF293K.exception.JBF293KException;

import java.io.IOException;
import java.io.InputStream;

import static cn.allbs.utils.JBF293K.PacketElement.*;
import static cn.allbs.utils.JBF293K.enums.KeyWordEnums.*;

/**
 * 类 MultiLineAlarmParser
 * </p>
 * 多线相关的报警解析器
 *
 * @author ChenQi
 * @since 2023/2/21 17:15
 */
public class MultiLineAlarmParser extends AbstractParser {
    public MultiLineAlarmParser(InputStream in) {
        super(in);
    }

    /**
     * 读取机器号
     *
     * @throws Exception
     */
    protected void readMachineNo() throws IOException {
        short machineNo = handleByte(D2.getLen());
        this.dataMap.put(MACHINE_NO.getName(), machineNo);
    }

    /**
     * 读取回路
     *
     * @throws IOException
     */
    protected void readLoop() throws IOException {
        short loop = handleByte(D3.getLen());
        // 固定回路0xf2
        this.dataMap.put(CIRCUIT.getName(), AlarmEnum.MULTI_LINE_ANSWER.getCircuit());
    }

    /**
     * 读取部位
     *
     * @throws IOException
     */
    protected void readPart() throws IOException, JBF293KException {
        short part = handleByte(D4.getLen());
        // 划分为盘号和专线号
        short[] s = countDiskAndLine(part);
        this.dataMap.put(DISK.getName(), s[0]);
        this.dataMap.put(LINE.getName(), s[1]);
    }

    /**
     * 根据部位计算可能的第一个实根
     *
     * @param total
     * @return
     * @throws JBF293KException
     */
    private short[] countDiskAndLine(short total) throws JBF293KException {
        // 通过计算（盘号-1）*4+区号 = total(最大256) 得出所有正整数解
        for (short i = 1; i < 32; i++) {
            for (short j = 1; j < 9; j++) {
                if ((i - 1) * 8 + j == total) {
                    return new short[]{i, j};
                }
            }
        }
        throw new JBF293KException("无法计算的盘号和专线号!");
    }

    /**
     * 读取不处理的信息
     *
     * @throws IOException
     */
    protected void readNullInfo() throws IOException {
        handleByte(D5.getLen());
    }
}
