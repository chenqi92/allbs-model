package cn.allbs.utils.JBF293K.format;

import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.Crc8MAXIM;
import cn.allbs.utils.JBF293K.enums.PacketElement;
import cn.allbs.core.Configurator;
import cn.allbs.core.Configured;
import cn.allbs.utils.JBF293K.enums.AlarmEnum;
import cn.allbs.utils.JBF293K.enums.FireAndEleEnum;
import cn.allbs.utils.JBF293K.enums.SprayEnum;
import cn.allbs.exception.JBF293KException;
import cn.allbs.feature.VerifyFeature;
import cn.allbs.utils.JBF293K.format.data.AbstractParser;
import cn.allbs.utils.JBF293K.utils.VerifyUtil;

import java.io.*;

import static cn.allbs.utils.JBF293K.enums.KeyWordEnums.*;

/**
 * 类 GB293KParser
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 10:41
 */
public class JBF293KParser implements Configured<JBF293KParser>, Closeable {

    public static int HEADER = 0x82;
    public static int FOOTER = 0x83;

    protected DataInputStream dataOutputStream;

    protected byte[] msgBytes;

    private int count;

    private int verifyFeature;

    public JBF293KParser(byte[] msgBytes) {
        this.msgBytes = msgBytes;
        ByteArrayInputStream bao = new ByteArrayInputStream(msgBytes);
        this.dataOutputStream = new DataInputStream(bao);
    }

    public JBF293KParser(InputStream in) {
        this.dataOutputStream = new DataInputStream(in);
    }

    /**
     * 读取 包头
     *
     * @return chars
     * @throws JBF293KException 协议转换异常
     * @throws IOException      读写异常
     */
    public byte[] readHeader() throws JBF293KException, IOException {
        byte[] header = new byte[PacketElement.START.getLen()];
        count = header.length;
        for (int i = 0; i < count; i++) {
            header[i] = dataOutputStream.readByte();
        }
        VerifyUtil.verifyLen(count, 1, PacketElement.START);
        VerifyUtil.verifyByte(header, HEADER, PacketElement.START);
        return header;
    }

    /**
     * 判断应该使用哪个转换器
     *
     * @return
     * @throws JBF293KException
     * @throws IOException
     */
    public short readOrderType() throws JBF293KException, IOException {
        byte[] order = new byte[ORDER.getLen()];
        count = order.length;
        for (int i = 0; i < count; i++) {
            order[i] = dataOutputStream.readByte();
        }
        // 取每个字节的低四位构成十进制整数
        return AsciiUtil.bytesToShort(order, 0x30);
    }

    /**
     * 读取核心数据
     *
     * @param orderType 命令类型
     * @return
     * @throws JBF293KException
     * @throws IOException
     */
    public AbstractParser readData(int orderType) throws JBF293KException, IOException {
        AbstractParser abstractParser = null;
        if (AlarmEnum.ALARM_MAP.containsKey(orderType)) {
            abstractParser = AlarmEnum.ALARM_MAP.get(orderType).execute(dataOutputStream);
            abstractParser.parseMap().put(ORDER.getName(), AlarmEnum.ALARM_MAP.get(orderType).getDesc());
        }
        if (SprayEnum.SPRAY_ENUM_MAP.containsKey(orderType)) {
            abstractParser = SprayEnum.SPRAY_ENUM_MAP.get(orderType).execute(dataOutputStream);
            abstractParser.parseMap().put(ORDER.getName(), SprayEnum.SPRAY_ENUM_MAP.get(orderType).getOrderName());
        }
        if (FireAndEleEnum.FIRE_AND_ELE_ENUM_MAP.containsKey(orderType)) {
            abstractParser = FireAndEleEnum.FIRE_AND_ELE_ENUM_MAP.get(orderType).execute(dataOutputStream);
            abstractParser.parseMap().put(ORDER.getName(), FireAndEleEnum.FIRE_AND_ELE_ENUM_MAP.get(orderType).getOrderName());
        }
        if (abstractParser == null) {
            throw new JBF293KException("未定义的命令类型!");
        }
        abstractParser.parseMap().put(ORDER.getPreName(), orderType);
        // 执行解析
        abstractParser.addSummation(orderType);
        abstractParser.create();
        return abstractParser;
    }

    /**
     * 校验
     */
    public void readCheck(short summation) throws IOException, JBF293KException {
        byte[] crc = new byte[ACCUMULATE_SUM.getLen()];
        count = crc.length;
        for (int i = 0; i < count; i++) {
            crc[i] = dataOutputStream.readByte();
        }
        short crcNum = AsciiUtil.bytesToShort(crc, 0x30);
        if (VerifyFeature.SUMMATION.enabledIn(verifyFeature)) {
            VerifyUtil.verifyCheck(summation != crcNum);
        }
    }

    /**
     * Crc校验
     */
    public void readCheckCrc(int... bytes) throws IOException, JBF293KException {
        byte[] crc = new byte[CRC.getLen()];
        count = crc.length;
        for (int i = 0; i < count; i++) {
            crc[i] = dataOutputStream.readByte();
        }
        short crcNum = AsciiUtil.bytesToShort(crc, 0x30);
        Crc8MAXIM checkCrc = Crc8MAXIM.getInstance();

        short checkCrcNum = (short) checkCrc.compute(bytes);
        if (VerifyFeature.DATA_CRC.enabledIn(verifyFeature)) {
            VerifyUtil.verifyCheck(crcNum != checkCrcNum);
        }
    }

    /**
     * 读取 报文尾
     *
     * @return chars
     * @throws JBF293KException 212协议转换异常
     * @throws IOException      读写异常
     */
    public byte[] readFooter() throws JBF293KException, IOException {
        byte[] footer = new byte[PacketElement.END.getLen()];
        count = footer.length;
        for (int i = 0; i < count; i++) {
            footer[i] = dataOutputStream.readByte();
        }
        VerifyUtil.verifyLen(count, 1, PacketElement.END);
        VerifyUtil.verifyByte(footer, FOOTER, PacketElement.END);
        return footer;
    }

    @Override
    public void configured(Configurator<JBF293KParser> by) {
        by.config(this);
    }

    @Override
    public void close() throws IOException {
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
