package cn.allbs.utils.JBF293K.format.data;

import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.JBF293K.core.Configurator;
import cn.allbs.utils.JBF293K.core.Configured;
import cn.allbs.utils.JBF293K.enums.KeyWordEnums;
import cn.allbs.utils.JBF293K.exception.JBF293KException;
import cn.hutool.core.date.DatePattern;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.allbs.utils.JBF293K.PacketElement.*;

/**
 * 类 AbstractParser
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 10:31
 */
public abstract class AbstractParser implements IParser, Configured<AbstractParser>, Closeable {
    protected DataInputStream dataOutputStream;

    /**
     * 默认读取的数据长度为12个数据区 - 1号数据区的命令类型 - 最后一个数据区的crc校验
     */
    protected int readLen = 20;

    /**
     * 累加和
     */
    protected int summation = 0;

    protected List<Short> crcBList = new LinkedList<>();

    /**
     * 是否包含数据区
     */
    @Getter
    @Setter
    protected boolean hasData = false;

    protected final Map<String, Object> dataMap = new ConcurrentHashMap<>(19);

    public AbstractParser(byte[] bytes) {
        ByteArrayInputStream bao = new ByteArrayInputStream(bytes);
        this.dataOutputStream = new DataInputStream(bao);
    }

    public AbstractParser(InputStream in) {
        this.dataOutputStream = new DataInputStream(in);
    }

    public AbstractParser(int readLen) {
        this.readLen = readLen;
    }

    public AbstractParser(InputStream in, int readLen) {
        this.readLen = readLen;
        this.dataOutputStream = new DataInputStream(in);
    }

    /**
     * 报文流读取
     *
     * @throws IOException IO流异常
     */
    @Override
    public void create() throws IOException, JBF293KException {
        this.readCode();
        this.readVersion();
        this.readMachineNo();
        this.readLoop();
        this.readInfoType();
        this.readPart();
        this.readPartType();
        this.readNullInfo();
        this.readTime();
        // 校验和
        this.readSummation();
        // 特殊情况，包含报警数据
        this.readAlarmData();
        // 因为报文中为2个字节的数据所以要取2的8次方的余数
        this.dataMap.put(KeyWordEnums.ACCUMULATE_SUM.getName(), getSummation());
        this.close();
    }

    /**
     * 读取代码类型
     *
     * @throws IOException
     */
    protected void readCode() throws IOException {

    }

    /**
     * 读取机器号
     *
     * @throws Exception
     */
    protected void readMachineNo() throws IOException {

    }

    /**
     * 读取回路
     *
     * @throws IOException
     */
    protected void readLoop() throws IOException {

    }

    /**
     * 读取信息类型
     *
     * @throws IOException
     */
    protected void readInfoType() throws IOException {

    }

    /**
     * 读取部位
     *
     * @throws IOException
     */
    protected void readPart() throws IOException, JBF293KException {

    }

    /**
     * 读取部件类型
     *
     * @throws IOException
     */
    protected void readPartType() throws IOException, JBF293KException {

    }

    /**
     * 读取版本号
     *
     * @throws IOException
     */
    protected void readVersion() throws IOException {

    }

    /**
     * 读取crc 数据
     *
     * @throws IOException
     */
    protected void readCrc() throws IOException {

    }

    /**
     * 报文时间读取
     *
     * @throws IOException
     */
    protected void readTime() throws IOException {
        short year = handleByte(D6.getLen());
        short month = handleByte(D7.getLen());
        short day = handleByte(D8.getLen());
        short hour = handleByte(D9.getLen());
        short minute = handleByte(D10.getLen());
        short second = handleByte(D11.getLen());
        this.dataMap.put(KeyWordEnums.TIME.getName(), LocalDateTime.of(2000 + year, month, day, hour, minute, second).format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
    }

    /**
     * 读取累加和
     *
     * @throws IOException
     */
    protected void readSummation() throws IOException, JBF293KException {

    }

    /**
     * 读取不处理的信息
     *
     * @throws IOException
     */
    protected void readNullInfo() throws IOException {

    }

    /**
     * 读取报警数据
     *
     * @throws IOException
     */
    protected void readAlarmData() throws IOException, JBF293KException {

    }

    @Override
    public void configured(Configurator<AbstractParser> by) {
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

    @Override
    public Map<String, Object> parseMap() {
        return this.dataMap;
    }

    @Override
    public int currentReadLen() {
        return this.readLen;
    }

    public void addSummation(int num) {
        this.crcBList.add((short) (num & 255));
        this.summation += num;
    }

    /**
     * 获取当前累加和，两个字节最大256
     *
     * @return
     */
    public short getSummation() {
        return (short) (this.summation & 255);
    }

    /**
     * 获取当前储存的所有待校验的crc数组
     *
     * @return
     */
    public int[] getCrcNum() {
        int len = this.crcBList.size();
        int[] bytes = new int[len];
        for (int i = 0; i < len; i++) {
            bytes[i] = this.crcBList.get(i);
        }
        return bytes;
    }

    public short handleByte(int len) throws IOException {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[i] = dataOutputStream.readByte();
        }
        short castNum = AsciiUtil.bytesToByte(b);
        // 校验和增加
        addSummation(castNum);
        return castNum;
    }
}
