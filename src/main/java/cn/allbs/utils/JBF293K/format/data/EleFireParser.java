package cn.allbs.utils.JBF293K.format.data;

import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.JBF293K.enums.EleFireStatusEnum;
import cn.allbs.utils.JBF293K.enums.EleFireTypeEnum;
import cn.allbs.exception.JBF293KException;
import cn.allbs.utils.JBF293K.utils.VerifyUtil;

import java.io.IOException;
import java.io.InputStream;

import static cn.allbs.utils.JBF293K.enums.PacketElement.*;
import static cn.allbs.utils.JBF293K.enums.KeyWordEnums.*;

/**
 * 类 EleFireParser
 * </p>
 * 电气火灾解析器
 *
 * @author ChenQi
 * @since 2023/2/21 17:31
 */
public class EleFireParser extends AbstractParser {

    public EleFireParser(InputStream in) {
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
        this.dataMap.put(CIRCUIT.getName(), loop);
    }

    /**
     * 读取部位
     *
     * @throws IOException
     */
    protected void readPart() throws IOException, JBF293KException {
        short address = handleByte(D4.getLen());
        this.dataMap.put(ADDRESS.getName(), address);
    }

    /**
     * 读取部件类型
     *
     * @throws IOException
     */
    protected void readPartType() throws IOException, JBF293KException {
        // 当前部件类型判断是否包含数据部分
        short partType = handleByte(D5.getLen());
        this.hasData = partType == 0xff;
        if (!this.hasData) {
            // 取低四位电气火灾探测器类型
            short type = (short) AsciiUtil.getLow4FromShort(partType);
            // 取高四位电气火灾探测器状态
            short status = (short) AsciiUtil.getHeight4FromShort(partType);
            try {
                this.dataMap.put(TYPE.getName(), EleFireTypeEnum.ELE_FIRE_TYPE_MAP.get(type).getDesc());
                this.dataMap.put(STATUS.getName(), EleFireStatusEnum.ELE_FIRE_STATUS_MAP.get(status));
            } catch (Exception e) {
                throw new JBF293KException("无法确定的部件状态!");
            }
            this.dataMap.put(TYPE.getPreName(), type);
            this.dataMap.put(STATUS.getPreName(), status);
        }
    }

    /**
     * 读取累加和
     *
     * @throws IOException
     */
    protected void readSummation() throws IOException, JBF293KException {
        if (!this.hasData) {
            return;
        }
        byte[] crc = new byte[ACCUMULATE_SUM.getLen()];
        for (int i = 0; i < ACCUMULATE_SUM.getLen(); i++) {
            crc[i] = dataOutputStream.readByte();
        }
        short crcNum = AsciiUtil.bytesToShort(crc, 0x30);
        VerifyUtil.verifyCheck(getSummation() != crcNum);
        addSummation(crcNum);
    }

    /**
     * 读取报警数据
     *
     * @throws IOException
     */
    protected void readAlarmData() throws IOException, JBF293KException {
        if (!this.hasData) {
            return;
        }
        // 读取部件类型
        short partType = handleByte(D13.getLen());
        // 取低四位电气火灾探测器类型
        short type = (short) AsciiUtil.getLow4FromShort(partType);
        // 取高四位电气火灾探测器状态
        short status = (short) AsciiUtil.getHeight4FromShort(partType);
        try {
            EleFireTypeEnum ele = EleFireTypeEnum.ELE_FIRE_TYPE_MAP.get(type);
            this.dataMap.put(TYPE.getName(), ele.getDesc());
            this.dataMap.put(STATUS.getName(), EleFireStatusEnum.ELE_FIRE_STATUS_MAP.get(status));
            this.dataMap.put(TYPE.getPreName(), type);
            this.dataMap.put(STATUS.getPreName(), status);
            // 读取传感器通道
            short controller = handleByte(D14.getLen());
            this.dataMap.put(CONTROLLER.getName(), controller);
            // 读取报警值
            short low = handleByte(D15.getLen());
            short high = handleByte(D16.getLen());
            // 实际数据
            double value = ((low & 0xff | (high & 0xff) << Byte.SIZE)) / Math.pow(10, ele.getFactor());
            // 判断高位是否为负数 也就是超过2的8次方的数据
            if (high >= 128 && ele.getMinus()) {
                value = 0 - value;
            }
            this.dataMap.put(ALARM_VALUE.getName(), value);
            this.dataMap.put(UNIT.getName(), ele.getUnit());
        } catch (Exception e) {
            throw new JBF293KException("无法确定的部件状态!");
        }
    }
}
