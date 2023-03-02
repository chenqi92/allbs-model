package cn.allbs.utils.gb26875.format.data;

import cn.allbs.utils.gb26875.core.Configurator;
import cn.allbs.utils.gb26875.core.Configured;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 抽象转换类
 *
 * @author ChenQi
 */
@Slf4j
public abstract class AbstractParser implements IParser, Configured<AbstractParser>, Closeable {

    protected DataInputStream dataOutputStream;

    protected final Map<String, Object> dataMap = new LinkedHashMap<>(9);

    public AbstractParser(byte[] bytes) {
        ByteArrayInputStream bao = new ByteArrayInputStream(bytes);
        this.dataOutputStream = new DataInputStream(bao);
    }

    /**
     * 报文流读取
     *
     * @throws IOException IO流异常
     */
    @Override
    public void create() throws IOException {
        this.readSysType();
        this.readSysAddress();
        this.readSysDesc();
        this.readPartType();
        this.readPartAddress();
        this.readAnalogQuantityType();
        this.readAnalogQuantityValue();
        this.readPartDesc();
        this.readPartExplain();
        this.readOperateType();
        this.readOperatorNum();
        this.readMainVersion();
        this.readMinorVersion();
        this.readSysConfig();
        this.readRunningStatus();
        this.readTransmittingInfo();

        // 读取时间
        this.readTime();
        this.close();
    }

    /**
     * 读取一个字节的系统类型标志
     *
     * @throws IOException io 流异常
     */
    protected void readSysType() throws IOException {
    }

    /**
     * 读取一个字节的系统地址
     *
     * @throws IOException io 流异常
     */
    protected void readSysAddress() throws IOException {
    }

    /**
     * 读取2个字节的系统状态
     *
     * @throws IOException io 流异常
     */
    protected void readSysDesc() throws IOException {

    }

    /**
     * 读取一个字节的部件类型
     *
     * @throws IOException io 流异常
     */
    protected void readPartType() throws IOException {

    }

    /**
     * 读取4个字节的部件地址
     *
     * @throws IOException io 流异常
     */
    protected void readPartAddress() throws IOException {

    }

    /**
     * 读取模拟量类型
     *
     * @throws IOException io 流异常
     */
    protected void readAnalogQuantityType() throws IOException {

    }

    /**
     * 读取模拟量值
     *
     * @throws IOException io 流异常
     */
    protected void readAnalogQuantityValue() throws IOException {

    }

    /**
     * 读取2个字节的多部件状态
     *
     * @throws IOException io 流异常
     */
    protected void readPartDesc() throws IOException {

    }

    /**
     * 读取31字节的部件说明
     * </p>
     * GB18030—2005 解析说明
     *
     * @throws IOException io 流异常
     */
    protected void readPartExplain() throws IOException {

    }

    /**
     * 读取报文传输时间
     *
     * @throws IOException io 流异常
     */
    protected void readTime() throws IOException {
    }

    /**
     * 读取操作标志
     *
     * @throws IOException io 流异常
     */
    protected void readOperateType() throws IOException {

    }

    /**
     * 读取操作员编号
     *
     * @throws IOException io 流异常
     */
    protected void readOperatorNum() throws IOException {

    }

    /**
     * 读取主版本号
     *
     * @throws IOException io 流异常
     */
    protected void readMainVersion() throws IOException {

    }

    /**
     * 读取主版本号
     *
     * @throws IOException io 流异常
     */
    protected void readMinorVersion() throws IOException {

    }

    /**
     * 系统配置
     *
     * @throws IOException io 流异常
     */
    protected void readSysConfig() throws IOException {

    }

    /**
     * 读取用户信息传输装置运行状态
     *
     * @throws IOException io 流异常
     */
    protected void readRunningStatus() throws IOException {

    }

    /**
     * 用户信息传输装置配置情况
     *
     * @throws IOException io 流异常
     */
    protected void readTransmittingInfo() throws IOException {

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
}
