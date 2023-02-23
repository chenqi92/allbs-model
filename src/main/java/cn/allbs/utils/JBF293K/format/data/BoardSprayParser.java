package cn.allbs.utils.JBF293K.format.data;

import cn.allbs.utils.JBF293K.enums.SprayEnum;
import cn.allbs.utils.JBF293K.exception.JBF293KException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static cn.allbs.utils.JBF293K.PacketElement.D4;
import static cn.allbs.utils.JBF293K.PacketElement.D5;
import static cn.allbs.utils.JBF293K.enums.KeyWordEnums.*;

/**
 * 类 BoardSprayParser
 * </p>
 * 带板号的喷洒解析
 *
 * @author ChenQi
 * @since 2023/2/21 17:27
 */
public class BoardSprayParser extends AbstractParser {

    public BoardSprayParser(InputStream in) {
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
     * 读取信息类型
     *
     * @throws IOException
     */
    protected void readInfoType() throws IOException {
        short messageInfo = handleByte(MESSAGE_INFO.getLen());
        dataMap.put(MESSAGE_INFO.getPreName(), messageInfo);
        dataMap.put(MESSAGE_INFO.getName(), Objects.requireNonNull(SprayEnum.SPRAY_ENUM_TABLE.get(SprayEnum.GAS_LINE_FAILURE.getCode(), messageInfo)).getOrderName());
    }

    /**
     * 读取部位 -板号
     *
     * @throws IOException
     */
    protected void readPart() throws IOException, JBF293KException {
        short borderNumber = handleByte(D4.getLen());
        // 为板号
        this.dataMap.put(BOARD_NUMBER.getName(), borderNumber);
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
