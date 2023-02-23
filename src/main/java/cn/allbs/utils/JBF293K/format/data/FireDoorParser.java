package cn.allbs.utils.JBF293K.format.data;

import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.JBF293K.enums.FireDoorsStatusEnum;
import cn.allbs.utils.JBF293K.enums.FireDoorsTypeEnum;
import cn.allbs.utils.JBF293K.exception.JBF293KException;

import java.io.IOException;
import java.io.InputStream;

import static cn.allbs.utils.JBF293K.PacketElement.D4;
import static cn.allbs.utils.JBF293K.PacketElement.D5;
import static cn.allbs.utils.JBF293K.enums.KeyWordEnums.*;

/**
 * 类 FireDoorParser
 * </p>
 * 防火门解析器
 *
 * @author ChenQi
 * @since 2023/2/21 17:30
 */
public class FireDoorParser extends AbstractParser {
    public FireDoorParser(InputStream in) {
        super(in);
    }

    /**
     * 读取机器号
     *
     * @throws Exception
     */
    protected void readMachineNo() throws IOException {
        short machineNo = handleByte(MACHINE_NO.getLen());
        dataMap.put(MACHINE_NO.getName(), machineNo);
    }

    /**
     * 读取回路
     *
     * @throws IOException
     */
    protected void readLoop() throws IOException {
        short loop = handleByte(CIRCUIT.getLen());
        this.dataMap.put(CIRCUIT.getName(), loop);
    }

    /**
     * 读取部位
     *
     * @throws IOException
     */
    protected void readPart() throws IOException, JBF293KException {
        short address = handleByte(ADDRESS.getLen());
        this.dataMap.put(ADDRESS.getName(), address);
    }

    /**
     * 读取部件类型-类型
     *
     * @throws IOException
     */
    protected void readPartType() throws IOException, JBF293KException {
        short partType = handleByte(D5.getLen());
        // 取低四位为探测器类型
        short type = (short) AsciiUtil.getLow4FromShort(partType);
        // 取高四位为探测器的状态
        short status = (short) AsciiUtil.getHeight4FromShort(partType);
        try {
            this.dataMap.put(TYPE.getName(), FireDoorsTypeEnum.DOOR_TYPE.get(type));
            this.dataMap.put(STATUS.getName(), FireDoorsStatusEnum.DOOR_STATUS.get(status));
        } catch (Exception e) {
            throw new JBF293KException("无法确定的部件状态!");
        }
        this.dataMap.put(TYPE.getPreName(), type);
        this.dataMap.put(STATUS.getPreName(), status);
    }
}
