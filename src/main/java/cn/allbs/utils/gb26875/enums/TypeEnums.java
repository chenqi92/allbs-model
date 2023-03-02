package cn.allbs.utils.gb26875.enums;

import cn.allbs.utils.gb26875.format.data.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenQi
 */
@Getter
public enum TypeEnums {

    /**
     * 控制命令
     */
    _0(0, "预留", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _1(1, "上传建筑消防设施系统状态", DirectEnums.UP, AlarmEnum.ALARM) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type1Parser(bytes);
        }
    }, _2(2, "上传建筑消防设施部件运行状态", DirectEnums.UP, AlarmEnum.ALARM) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type2Parser(bytes);
        }
    }, _3(3, "上传建筑消防设施部件模拟量值", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type3Parser(bytes);
        }
    }, _4(4, "上传建筑消防设施操作信息", DirectEnums.UP, AlarmEnum.ALARM) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type4Parser(bytes);
        }
    }, _5(5, "上传建筑消防设施软件版本", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type5Parser(bytes);
        }
    }, _6(6, "上传建筑消防设施系统配置情况", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type6Parser(bytes);
        }
    }, _7(7, "上传建筑消防设施部件配置情况", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type7Parser(bytes);
        }
    }, _8(8, "上传建筑消防设施系统时间", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type8Parser(bytes);
        }
    }, _9(9, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _10(10, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _11(11, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _12(12, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _13(13, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _14(14, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _15(15, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _16(16, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _17(17, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _18(18, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _19(19, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _20(20, "预留(建筑消防设施信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _21(21, "上传用户信息传输装置运行状态", DirectEnums.UP, AlarmEnum.ALARM) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type21Parser(bytes);
        }
    }, _22(22, "预留", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _23(23, "预留", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _24(24, "上传用户信息传输装置操作信息", DirectEnums.UP, AlarmEnum.ALARM) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type24Parser(bytes);
        }
    }, _25(25, "上传用户信息传输装置软件版本", DirectEnums.UP, SaveEnum.NOT_SAVE) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type25Parser(bytes);
        }
    }, _26(26, "上传用户信息传输装置配置情况", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type26Parser(bytes);
        }
    }, _27(27, "预留", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _28(28, "上传用户信息传输装置系统时间", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new Type28Parser(bytes);
        }
    }, _29(29, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _30(30, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _31(31, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _32(32, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _33(33, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _34(34, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _35(35, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _36(36, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _37(37, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _38(38, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _39(39, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _40(40, "预留(用户信息传输装置信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _41(41, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _42(42, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _43(43, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _44(44, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _45(45, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _46(46, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _47(47, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _48(48, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _49(49, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _50(50, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _51(51, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _52(52, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _53(53, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _54(54, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _55(55, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _56(56, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _57(57, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _58(58, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _59(59, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _60(60, "预留(控制信息)", DirectEnums.UP) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _61(61, "读建筑消防设施系统状态", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _62(62, "读建筑消防设施部件运行状态", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _63(63, "读建筑消防设施部件模拟量值", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _64(64, "读建筑消防设施操作信息", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _65(65, "读建筑消防设施软件版本", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _66(66, "读建筑消防设施系统配置情况", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _67(67, "读建筑消防设施部件配置情况", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _68(68, "读建筑消防设施系统时间", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _69(69, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _70(70, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _71(71, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _72(72, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _73(73, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _74(74, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _75(75, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _76(76, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _77(77, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _78(78, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _79(79, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _80(80, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _81(81, "读用户信息传输装置运行状态", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _82(82, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _83(83, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _84(84, "读用户信息传输装置操作信息记录", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _85(85, "读用户信息传输装置软件版本", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _86(86, "读用户信息传输装置配置情况", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _87(87, "预留", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _88(88, "读用户信息传输装置系统时间", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _89(89, "初始化用户信息传输装置", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _90(90, "同步用户信息传输装置时钟", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    }, _91(91, "查岗命令", DirectEnums.DOWN) {
        @Override
        public AbstractParser execute(byte[] bytes) {
            return new DefaultParser(bytes);
        }
    };


    private final int type;

    private final String desc;

    private final DirectEnums directEnums;

    private final SaveEnum save;

    private final AlarmEnum alarm;

    public abstract AbstractParser execute(byte[] bytes);

    public static final Map<Short, TypeEnums> TYPE_PARSER_MAP = new HashMap<>();

    TypeEnums(int type, String desc, DirectEnums directEnums) {
        this.type = type;
        this.desc = desc;
        this.directEnums = directEnums;
        this.save = SaveEnum.SAVE;
        // 默认不报警
        this.alarm = AlarmEnum.NOT_ALARM;
    }

    TypeEnums(int type, String desc, DirectEnums directEnums, SaveEnum saveEnum) {
        this.type = type;
        this.desc = desc;
        this.directEnums = directEnums;
        this.save = saveEnum;
        // 默认不报警
        this.alarm = AlarmEnum.NOT_ALARM;
    }

    TypeEnums(int type, String desc, DirectEnums directEnums, SaveEnum saveEnum, AlarmEnum alarm) {
        this.type = type;
        this.desc = desc;
        this.directEnums = directEnums;
        this.save = saveEnum;
        this.alarm = alarm;
    }

    TypeEnums(int type, String desc, DirectEnums directEnums, AlarmEnum alarm) {
        this.type = type;
        this.desc = desc;
        this.directEnums = directEnums;
        this.save = SaveEnum.SAVE;
        this.alarm = alarm;
    }

    static {
        TypeEnums[] items = TypeEnums.values();
        for (TypeEnums item : items) {
            TYPE_PARSER_MAP.put((short) item.getType(), item);
        }
    }

    /**
     * @param type  1字节的 类型标志
     * @param bytes 单个信息体
     * @return
     */
    public static AbstractParser parserType(short type, byte[] bytes) {
        if (TYPE_PARSER_MAP.containsKey(type)) {
            return TYPE_PARSER_MAP.get(type).execute(bytes);
        }
        return new DefaultParser(bytes);
    }

}
