package cn.allbs.utils.gb26875.format;


import cn.allbs.constant.StringPoolConstant;
import cn.allbs.core.Configurator;
import cn.allbs.core.Configured;
import cn.allbs.exception.GB26875Exception;
import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.gb26875.enums.PacketElement;
import cn.allbs.utils.gb26875.enums.TypeEnums;
import cn.allbs.utils.gb26875.format.data.AbstractParser;
import cn.allbs.utils.gb26875.utils.VerifyUtil;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import static cn.allbs.utils.gb26875.enums.ConstEnum.*;

/**
 * 主解析器 也就是非数据部分的解析器
 *
 * @author ChenQi
 */
public class GB26875Parser implements Configured<GB26875Parser>, Closeable {

    public static char[] HEADER = new char[]{'@', '@'};
    public static char[] FOOTER = new char[]{'#', '#'};

    protected DataInputStream dataOutputStream;

    protected byte[] msgBytes;

    private int parserFeature;

    /**
     * TEMP
     */
    private int count;

    public GB26875Parser(byte[] msgBytes) {
        this.msgBytes = msgBytes;
        ByteArrayInputStream bao = new ByteArrayInputStream(msgBytes);
        this.dataOutputStream = new DataInputStream(bao);
    }

    /**
     * 设置解析特性
     *
     * @param parserFeature 解析特性
     */
    public void setParserFeature(int parserFeature) {
        this.parserFeature = parserFeature;
    }


    /**
     * 读取 包头
     *
     * @return chars
     * @throws GB26875Exception 协议转换异常
     * @throws IOException      读写异常
     */
    public char[] readHeader() throws GB26875Exception, IOException {
        char[] header = new char[PacketElement.HEADER.getLen()];
        count = header.length;
        for (int i = 0; i < count; i++) {
            header[i] = (char) dataOutputStream.readByte();
        }
        VerifyUtil.verifyLen(count, 2, PacketElement.HEADER);
        VerifyUtil.verifyChar(header, HEADER, PacketElement.HEADER);
        return header;
    }

    /**
     * 读取流水号
     * <p>
     * 因为小端模式不能直接以short读出，只能以字节读出并小端模式转换
     *
     * @return 流水号
     * @throws GB26875Exception
     * @throws IOException
     */
    public short readSerialNum() throws GB26875Exception, IOException {
        byte[] serialNum = new byte[PacketElement.SERIAL_NUM.getLen()];
        count = serialNum.length;
        for (int i = 0; i < count; i++) {
            serialNum[i] = dataOutputStream.readByte();
        }
        return AsciiUtil.bytesToShort(serialNum);
    }

    /**
     * 读取主版本号
     *
     * @return 主版本号
     */
    public short readMainVersion() throws IOException {
        return (short) (dataOutputStream.readByte() & 0xff);
    }

    /**
     * 读取用户版本号
     *
     * @return 用户版本号
     */
    public short readUserVersion() throws IOException {
        return (short) (dataOutputStream.readByte() & 0xff);
    }

    /**
     * 读取报文传输时间
     *
     * @return 传输时间
     */
    public LocalDateTime readTime() throws IOException {
        short[] times = new short[PacketElement.TIME.getLen()];
        count = times.length;
        for (int i = 0; i < count; i++) {
            times[PacketElement.TIME.getLen() - 1 - i] = (short) (dataOutputStream.readByte() & 0xff);
        }
        return LocalDateTime.of(times[0] + 2000, times[1], times[2], times[3], times[4], times[5]);
    }

    /**
     * 读取源 mac地址
     *
     * @return
     * @throws IOException
     */
    public String readSourceAddress() throws IOException {
        // TODO 源mac地址获取方式待验证 并通过目的地址获取ip
        StringBuilder sb = new StringBuilder();
        count = PacketElement.SOURCE_ADDRESS.getLen();
//        short[] sourceAddress = new short[PacketElement.SOURCE_ADDRESS.getLen()];
        for (int i = 0; i < count; i++) {
//            sourceAddress[i] = (short) (dataOutputStream.readByte() & 0xff);
            sb.append((short) (dataOutputStream.readByte() & 0xff));
            if (i != count - 1) {
                sb.append(StringPoolConstant.COLON);
            }
        }
//        for (int i = 0; i < count; i++) {
//            sb.append(dataOutputStream.readByte() & 0xff);
//            if (i != count - 1) {
//                sb.append(StringPool.COLON);
//            }
//        }
        return sb.toString();
    }

    /**
     * 读取目的 mac地址
     *
     * @return
     * @throws IOException
     */
    public String readTargetAddress() throws IOException {
        // TODO 目的mac地址获取方式待验证 并通过目的地址获取ip
        StringBuilder sb = new StringBuilder();
        count = PacketElement.TARGET_ADDRESS.getLen();
//        short[] targetAddress = new short[count];
        for (int i = 0; i < count; i++) {
//            sourceAddress[i] = (short) (dataOutputStream.readByte() & 0xff);
            sb.append((short) (dataOutputStream.readByte() & 0xff));
            if (i != count - 1) {
                sb.append(StringPoolConstant.COLON);
            }
        }
//        for (int i = 0; i < count; i++) {
//            sb.append(dataOutputStream.readByte() & 0xff);
//            if (i != count - 1) {
//                sb.append(StringPool.COLON);
//            }
//        }
        return sb.toString();
    }

    /**
     * 读取 数据段长度
     *
     * @return chars
     * @throws GB26875Exception 212协议转换异常
     * @throws IOException      读写异常
     */
    public short readDataLen() throws GB26875Exception, IOException {
        byte[] dataLen = new byte[PacketElement.APPLICATION_DATA_LEN.getLen()];
        count = dataLen.length;
        for (int i = 0; i < count; i++) {
            dataLen[i] = dataOutputStream.readByte();
        }
        return AsciiUtil.bytesToShort(dataLen);
    }

    /**
     * 控制单元命令字符
     *
     * @return 控制单元名字字符short
     * @throws IOException
     */
    public short readControlOrder() throws IOException {
        return (short) (dataOutputStream.readByte() & 0xff);
    }

    /**
     * 读取应用数据单元
     *
     * @param len 应用数据单元长度
     * @return
     * @throws IOException
     */
    public Map<String, Object> readData(short len) throws GB26875Exception, IOException {
        Map<String, Object> resMap = new LinkedHashMap<>();
        List<Map<String, Object>> resMapList = new ArrayList<>();
        byte[] data = new byte[len];
        for (int i = 0; i < len; i++) {
            data[i] = dataOutputStream.readByte();
        }
        // 首先读取数据单元标识符 类型标志 + 信息对象数目
        // 类型标志 1字节 判断需要使用哪种解析器
        short type = (short) (data[0] & 0xff);
        // 未定义
        if (!TypeEnums.TYPE_PARSER_MAP.containsKey(type)) {
            throw new GB26875Exception("该报文内容未定义解析方式");
        }
        resMap.put(MSG_DATA_TYPE.getConstDefined(), type);
        resMap.put(MSG_DATA_TYPE_TRANS.getConstDefined(), TypeEnums.TYPE_PARSER_MAP.get(type).getDesc());
        // 信息对象数目 1字节 判断解析数据一共有几个
        short num = (short) (data[1] & 0xff);
        resMap.put(MSG_DATA_NUM.getConstDefined(), num);
        int batch = (len - 2) / num;
        for (int i = 0; i < num; i++) {
            byte[] currentData = new byte[batch];
            System.arraycopy(data, 2, currentData, 0, batch * (i + 1));
            AbstractParser abstractParser = TypeEnums.parserType(type, currentData);
            abstractParser.create();
            // 解析数据
            resMapList.add(abstractParser.parseMap());
        }
        resMap.put(MSG_DATA.getConstDefined(), resMapList);
        return resMap;
    }

    /**
     * 校验
     *
     * @return 校验结果
     */
    public boolean readCheck() throws IOException, GB26875Exception {
        // 需要计算校验和的范围
        byte[] needCheckBytes = Arrays.copyOfRange(msgBytes, PacketElement.HEADER.getLen(), msgBytes.length - PacketElement.VERIFY.getLen() - PacketElement.FOOTER.getLen());
        // 待校验位长度
        byte needCheckB = dataOutputStream.readByte();
        byte b = AsciiUtil.sumCheck(needCheckBytes, PacketElement.VERIFY.getLen())[0];
        VerifyUtil.verifyCheck(b != needCheckB);
        return true;
    }

    /**
     * 读取 报文尾
     *
     * @return chars
     * @throws GB26875Exception 212协议转换异常
     * @throws IOException      读写异常
     */
    public char[] readFooter() throws GB26875Exception, IOException {
        char[] footer = new char[PacketElement.FOOTER.getLen()];
        count = footer.length;
        for (int i = 0; i < count; i++) {
            footer[i] = (char) dataOutputStream.readByte();
        }
        VerifyUtil.verifyLen(count, 2, PacketElement.FOOTER);
        VerifyUtil.verifyChar(footer, FOOTER, PacketElement.FOOTER);
        return footer;
    }


    @Override
    public void configured(Configurator<GB26875Parser> configurator) {
        configurator.config(this);
    }

    @Override
    public void close() {
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取 4字节Integer
     *
     * @param radix 进制
     * @return Integer
     * @throws IOException 读写异常
     */
    public int readInt32(int radix) throws IOException {
        char[] intChars = new char[4];
//        count = reader.read(intChars);
        if (count != 4) {
            return -1;
        }
        return Integer.parseInt(new String(intChars), radix);
    }
}
