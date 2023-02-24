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
 * 类 MessageSprayParser
 * </p>
 * 带信息类型和部位的解析器
 *
 * @author ChenQi
 * @since 2023/2/21 17:29
 */
public class MessageSprayParser extends AbstractParser {

    public MessageSprayParser(InputStream in) {
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
     * 读取部位
     *
     * @throws IOException
     */
    protected void readPart() throws IOException, JBF293KException {
        short part = handleByte(D4.getLen());
        // 划分为盘号和专线号
        short[] s = countDiskAndDistrict(part);
        this.dataMap.put(DISK.getName(), s[0]);
        this.dataMap.put(DISTRICT.getName(), s[1]);
        this.dataMap.put(PART.getName(), part);
    }

    /**
     * 根据部位计算可能的第一个实根
     *
     * @param total
     * @return
     * @throws JBF293KException
     */
    private short[] countDiskAndDistrict(short total) throws JBF293KException {
        // 通过计算（盘号-1）*4+区号 = total(最大256) 得出第一个正整数解
        for (short i = 1; i < 64; i++) {
            for (short j = 1; j < 5; j++) {
                if ((i - 1) * 4 + j == total) {
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
