package cn.allbs.utils.JBF293K.format.data;

import cn.allbs.utils.JBF293K.enums.SprayEnum;
import cn.allbs.utils.JBF293K.exception.JBF293KException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static cn.allbs.utils.JBF293K.PacketElement.D4;
import static cn.allbs.utils.JBF293K.PacketElement.D5;
import static cn.allbs.utils.JBF293K.enums.KeyWordEnums.MACHINE_NO;
import static cn.allbs.utils.JBF293K.enums.KeyWordEnums.MESSAGE_INFO;

/**
 * 类 CommonSprayParser
 * </p>
 * 通用喷洒解析器
 *
 * @author ChenQi
 * @since 2023/2/21 17:18
 */
public class CommonSprayParser extends AbstractParser {
    public CommonSprayParser(InputStream in) {
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
        short part = handleByte(D4.getLen());
        // 展示不处理的部位号

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
