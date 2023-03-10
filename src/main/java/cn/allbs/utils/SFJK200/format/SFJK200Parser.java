package cn.allbs.utils.SFJK200.format;

import cn.allbs.core.Configurator;
import cn.allbs.core.Configured;
import cn.allbs.exception.SFJK200Exception;
import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.SFJK200.enums.OrderEnum;
import cn.allbs.utils.SFJK200.enums.PacketElement;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static cn.allbs.utils.SFJK200.enums.PacketElement.*;

/**
 * 类 SFJK200Parser
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/8 15:55
 */
public class SFJK200Parser implements Configured<SFJK200Parser>, Closeable {

    private int count;

    private int verifyFeature;

    protected ByteArrayInputStream dis;

    protected int register;

    protected List<Byte> dataByte;

    public SFJK200Parser(ByteArrayInputStream dis, int register) {
        this.dis = dis;
        this.register = register;
        this.dataByte = new LinkedList<>();
    }

    /**
     * 读取地址码
     */
    public int readAddress() throws IOException, SFJK200Exception {
        byte[] address = new byte[ADDRESS.getLen()];
        count = dis.read(address);
        return AsciiUtil.bytesToShort(address);
    }

    /**
     * 读取功能码
     */
    public int readFunction() throws IOException, SFJK200Exception {
        byte[] function = new byte[FUNCTION.getLen()];
        count = dis.read(function);
        return AsciiUtil.bytesToShort(function);
    }

    /**
     * 读取数据区长度
     *
     * @return
     * @throws IOException
     * @throws SFJK200Exception
     */
    public int readDataLen() throws IOException, SFJK200Exception {
        byte[] dataLen = new byte[DATA_LEN.getLen()];
        count = dis.read(dataLen);
        return AsciiUtil.bytesToShort(dataLen);
    }

    /**
     * 读取核心数据
     *
     * @param len 数据长度
     * @return
     * @throws SFJK200Exception
     * @throws IOException
     */
    public List<Map<String, Object>> readData(int len) throws SFJK200Exception, IOException {
        byte[] data = new byte[len];
        count = dis.read(data);
        // 查询使用哪个转换器
        OrderEnum orderEnum = OrderEnum.orderInfo(register);
        if (orderEnum == null) {
            throw new SFJK200Exception("超过寄存器范围!");
        }
        return orderEnum.getAbstractParser().readData(data);
    }

    /**
     * Crc校验
     */
    public byte[] readCheckCrc(int len) throws IOException, SFJK200Exception {
        byte[] crcByte = new byte[PacketElement.CRC_CHECK.getLen()];
        count = dis.read(crcByte);
        dis.reset();
        int needCheckLen = ADDRESS.getLen() + FUNCTION.getLen() + DATA_LEN.getLen() + len;
        byte[] needCheck = new byte[needCheckLen];
        dis.read(needCheck, 0, needCheckLen);
        int crcNum = AsciiUtil.bytesToCount(crcByte) & 0xFFFF;
        int crcCheckNum = AsciiUtil.crcModbusCheck(needCheck);
        if (crcCheckNum != -1 && crcNum == crcCheckNum) {
            return crcByte;
        }
        dis.reset();
        SFJK200Exception.crc_verification_failed(PacketElement.DATA, needCheck, crcNum);
        return null;
    }

    @Override
    public void configured(Configurator<SFJK200Parser> by) {
        by.config(this);
    }

    @Override
    public void close() throws IOException {
        try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
