package cn.allbs.utils.gb26875.format.data;


import cn.allbs.constant.StringPoolConstant;
import cn.allbs.utils.AsciiUtil;
import cn.allbs.utils.gb26875.enums.system.Type1STEnum;
import cn.allbs.utils.gb26875.enums.system.Type2STEnum;

import java.io.IOException;
import java.util.Optional;

import static cn.allbs.utils.gb26875.enums.ConstEnum.*;


/**
 * 建筑消防设施系统部件配置情况 解析器
 *
 * @author ChenQi
 */
public class Type7Parser extends AbstractParser {
    public Type7Parser(byte[] bytes) {
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
        this.dataMap.put(SYS_TYPE.getConstDefined(), type);
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
        this.dataMap.put(PART_TYPE.getConstDefined(), partType);
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
}
