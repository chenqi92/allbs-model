package cn.allbs.utils.SFJK200.format;

import cn.allbs.core.Configurator;
import cn.allbs.core.Configured;
import cn.allbs.utils.AsciiUtil;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;

/**
 * 类 SFJK200Generator
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/8 16:24
 */
public class SFJK200Generator implements Configured<SFJK200Generator>, Closeable {

    protected ByteArrayOutputStream bao;

    protected byte[] dataByte = new byte[6];

    public SFJK200Generator(ByteArrayOutputStream bao) {
        this.bao = bao;
    }

    @Override
    public void configured(Configurator<SFJK200Generator> configurator) {
        configurator.config(this);
    }

    /**
     * 写入从站地址
     *
     * @param address
     */
    public void writeAddress(int address) {
        this.dataByte[0] = (byte) (address & 0xFF);
        bao.write(address);
    }

    /**
     * 写入功能码
     *
     * @param function
     */
    public void writeFunction(int function) {
        this.dataByte[1] = (byte) (function & 0xFF);
        bao.write(function);
    }

    /**
     * 写入起始寄存器地址
     */
    public void writeStartRegister(int startAddress) throws IOException {
        // 转为两个字节的byte数组
        byte[] bytes = new byte[2];
        bytes[1] = (byte) (startAddress & 0xFF);
        bytes[0] = (byte) ((startAddress >>> 8) & 0xFF);
        this.dataByte[2] = bytes[0];
        this.dataByte[3] = bytes[1];
        bao.write(bytes);
    }

    /**
     * 吸入读寄存器地址
     */
    public void writeReadRegister(int readAddress) throws IOException {
        // 转为两个字节的byte数组
        byte[] bytes = new byte[2];
        bytes[1] = (byte) (readAddress & 0xFF);
        bytes[0] = (byte) ((readAddress >>> 8) & 0xFF);
        this.dataByte[4] = bytes[0];
        this.dataByte[5] = bytes[1];
        bao.write(bytes);
    }

    /**
     * 写入crc校验
     */
    public void writeCrc() throws IOException {
        // 计算crc校验值
        int crc = AsciiUtil.crcModbusCheck(this.dataByte);
        byte[] bytes = new byte[2];
        bytes[0] = (byte) (crc & 0xFF);
        bytes[1] = (byte) ((crc >>> 8) & 0xFF);
        bao.write(bytes);
    }


    @Override
    public void close() throws IOException {
        try {
            bao.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
