package cn.allbs.utils.gb26875.format.data;

import cn.allbs.constant.DateConstant;
import cn.allbs.constant.StringPoolConstant;
import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.gb26875.enums.system.Type1STEnum;
import cn.allbs.utils.gb26875.enums.system.Type2SSEnum;
import cn.allbs.utils.gb26875.enums.system.Type2STEnum;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static cn.allbs.utils.gb26875.enums.ConstEnum.*;

/**
 * 建筑消防设施部件状态 转换器
 *
 * @author ChenQi
 */
public class Type2Parser extends AbstractParser {

    public Type2Parser(byte[] bytes) {
        super(bytes);
    }

    /**
     * 读取一个字节的系统类型标志
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readSysType() throws IOException {
        short type = (short) (this.dataOutputStream.readByte() & 0xff);
        this.dataMap.put(SYS_TYPE.getConstDefined(), (int) type);
        this.dataMap.put(SYS_TYPE_TRANS.getConstDefined(), Optional.of(Type1STEnum.TYPE_1_MAP).map(a -> a.get(type)).orElse("未定义"));
    }

    /**
     * 读取一个字节的系统地址
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readSysAddress() throws IOException {
        short address = (short) (this.dataOutputStream.readByte() & 0xff);
        this.dataMap.put(SYS_ADDRESS.getConstDefined(), address);
    }

    /**
     * 读取一个字节的部件类型
     */
    @Override
    protected void readPartType() throws IOException {
        short partType = (short) (this.dataOutputStream.readByte() & 0xff);
        this.dataMap.put(PART_TYPE.getConstDefined(), (int) partType);
        // 部件类型解释
        this.dataMap.put(PART_TYPE_TRANS.getConstDefined(), Optional.of(Type2STEnum.TYPE_2_MAP).map(a -> a.get(partType)).orElse("未定义"));
    }

    /**
     * 读取4个字节的部件地址
     *
     * @throws IOException IO异常
     */
    @Override
    protected void readPartAddress() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < PART_ADDRESS.getConstNum(); i++) {
            sb.append((short) (this.dataOutputStream.readByte() & 0xff));
            if (i != PART_ADDRESS.getConstNum() - 1) {
                sb.append(StringPoolConstant.DOT);
            }
        }
        this.dataMap.put(PART_ADDRESS.getConstDefined(), sb.toString());
    }

    /**
     * 读取2个字节的多部件状态
     *
     * @throws IOException IO异常
     */
    @Override
    protected void readPartDesc() throws IOException {
        byte[] partDesc = new byte[PART_DESC.getConstNum()];
        for (int i = 0; i < PART_DESC.getConstNum(); i++) {
            partDesc[i] = this.dataOutputStream.readByte();
        }
        List<String> list = Type2SSEnum.binaryTrans(partDesc);
        this.dataMap.put(PART_DESC.getConstDefined(), String.join(StringPoolConstant.COMMA, list));
    }

    /**
     * 读取31字节的部件说明
     * </p>
     * GB18030—2005 解析说明
     *
     * @throws IOException IO异常
     */
    @Override
    protected void readPartExplain() throws IOException {
        byte[] explain = new byte[PART_EXPLAIN.getConstNum()];
        for (int i = 0; i < PART_EXPLAIN.getConstNum(); i++) {
            explain[i] = this.dataOutputStream.readByte();
        }
        this.dataMap.put(PART_EXPLAIN.getConstDefined(), AsciiUtil.gb18030ToUtf8(explain));
    }

    /**
     * 读取报文传输时间
     *
     * @throws IOException io 流异常
     */
    @Override
    protected void readTime() throws IOException {
        short[] times = new short[HAPPEN_TIME.getConstNum()];
        for (int i = 0; i < HAPPEN_TIME.getConstNum(); i++) {
            times[5 - i] = (short) (dataOutputStream.readByte() & 0xff);
        }
        LocalDateTime time = LocalDateTime.of(times[0] + 2000, times[1], times[2], times[3], times[4], times[5]);
        this.dataMap.put(HAPPEN_TIME.getConstDefined(), time.format(DateTimeFormatter.ofPattern(DateConstant.NORM_DATETIME_PATTERN)));
    }
}
