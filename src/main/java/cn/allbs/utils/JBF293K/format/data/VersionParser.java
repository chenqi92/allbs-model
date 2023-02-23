package cn.allbs.utils.JBF293K.format.data;

import cn.allbs.constant.StringPoolConstant;
import cn.allbs.utils.JBF293K.enums.KeyWordEnums;

import java.io.IOException;
import java.io.InputStream;

import static cn.allbs.utils.JBF293K.PacketElement.*;

/**
 * 类 VersionParser
 * </p>
 * 版本号解析器
 *
 * @author ChenQi
 * @since 2023/2/21 17:12
 */
public class VersionParser extends AbstractParser {

    public VersionParser(InputStream in) {
        super(in);
    }

    /**
     * 读取版本号
     *
     * @throws IOException
     */
    protected void readVersion() throws IOException {
        // 先读版本高位
        short versionH = handleByte(D2.getLen());

        // 再读版本低位
        short versionL = handleByte(D3.getLen());

        this.dataMap.put(KeyWordEnums.VERSION.getName(), versionH + StringPoolConstant.DOT + versionL);
    }

    /**
     * 读取不解析区的数据
     *
     * @throws IOException
     */
    protected void readNullInfo() throws IOException {
        // 只加如校验和不处理具体内容
        handleByte(D4.getLen());
        handleByte(D5.getLen());
        handleByte(D6.getLen());
    }

    protected void readTime() throws IOException {
        // 没有时间可以读取
        handleByte(D7.getLen());
        handleByte(D8.getLen());
        handleByte(D9.getLen());
        handleByte(D10.getLen());
        handleByte(D11.getLen());
    }
}
