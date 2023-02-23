package cn.allbs.utils.JBF293K.format.data;

import java.io.IOException;
import java.io.InputStream;

import static cn.allbs.utils.JBF293K.PacketElement.*;
import static cn.allbs.utils.JBF293K.enums.KeyWordEnums.*;

/**
 * 类 SimulationAlarmParser
 * </p>
 * 模拟报警解析器
 *
 * @author ChenQi
 * @since 2023/2/21 17:17
 */
public class SimulationAlarmParser extends AbstractParser {
    public SimulationAlarmParser(InputStream in) {
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
     * 读取回路 模拟报警回路为实际减1
     *
     * @throws IOException
     */
    protected void readLoop() throws IOException {
        short loop = handleByte(D3.getLen());
        this.dataMap.put(CIRCUIT.getName(), loop - 1);
    }

    /**
     * 读取部位
     *
     * @throws IOException
     */
    protected void readPart() throws IOException {
        short address = handleByte(D4.getLen());
        this.dataMap.put(ADDRESS.getName(), address);
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
