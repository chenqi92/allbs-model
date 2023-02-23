package cn.allbs.utils.JBF293K.enums;

import cn.allbs.utils.JBF293K.format.data.*;
import lombok.Getter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 AlarmEnum
 * </p>
 * 报警命令枚举
 *
 * @author ChenQi
 * @since 2023/2/21 13:29
 */
@Getter
public enum AlarmEnum {

    /**
     * 控制器心跳
     */
    CONTROLLER_HEART("控制器心跳", 0x00, 0x69) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new HeartParser(ins);
        }
    },

    /**
     * 控制器正常
     */
    CONTROLLER_NORMAL("控制器正常", 0x09) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new VersionParser(ins);
        }
    },

    /**
     * 控制器故障
     */
    CONTROLLER_BREAK("控制器故障", 0xEF) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new VersionParser(ins);
        }
    },

    /**
     * 控制器复位
     */
    CONTROLLER_RESET("控制器复位", 0x01) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new VersionParser(ins);
        }
    },

    /**
     * 控制器消音
     */
    CONTROLLER_MUFFLING("控制器消音", 0x0B) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new VersionParser(ins);
        }
    },

    /**
     * 火警
     */
    FIRE_ALARM("火警", 0x80, 0x0A) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 故障
     */
    FAULT("故障", 0x81) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 故障恢复
     */
    FAIL_BACK("故障恢复", 0x82) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 回路故障
     */
    CIRCUIT_FAILURE("回路故障", 0x87) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 回路故障恢复
     */
    LOOP_FAILURE_RECOVERY("回路故障恢复", 0x88) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 自动启动
     */
    START_AUTOMATICALLY("自动启动", 0x83) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 自动停止
     */
    AUTOMATIC_STOP("自动停止", 0x84) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 手动启动
     */
    START_MANUALLY("手动启动", 0x90) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 手动停止
     */
    STOP_MANUALLY("手动停止", 0x91) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 设备回答
     */
    THE_DEVICE_ANSWERS("设备回答", 0x85) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 回答撤销
     */
    ANSWER_REVOCATION("回答撤销", 0x86) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 部件隔离
     */
    PART_ISOLATION("部件隔离", 0x98) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 部件隔离撤销
     */
    PART_ISOLATION_IS_REVOKED("部件隔离撤销", 0x97) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },
    /**
     * 多线手动启动
     */
    MULTI_LINE_MANUAL_START("多线手动启动", (short) 0xf2, "-1,8", 0x51) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },

    /**
     * 多线手动停止
     */
    multi_line_manual_stop("多线手动停止", (short) 0xf2, "-1,8", 0x52) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },

    /**
     * 多线回答
     */
    MULTI_LINE_ANSWER("多线回答", (short) 0xf2, "-1,8", 0x53) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },

    /**
     * 多线停止回答
     */
    MULTI_LINE_STOP_ANSWERING("多线停止回答", (short) 0xf2, "-1,8", 0x54) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },
    /**
     * 多线自动启动
     */
    MULTI_LINE_AUTO_START("多线自动启动", (short) 0xf2, "-1,8", 0x55) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },
    /**
     * 多线自动停止
     */
    MULTI_LINE_AUTOMATIC_STOP("多线自动停止", (short) 0xf2, "-1,8", 0x56) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },
    /**
     * 多线自动启动延时
     */
    MULTI_LINE_AUTO_START_DELAY("多线自动启动延时", (short) 0xf2, "-1,8", 0x57) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },
    /**
     * 多线应答缺失
     */
    MULTI_LINE_RESPONSE_IS_MISSING("多线应答缺失", (short) 0xf2, "-1,8", 0x58) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },
    /**
     * 多线线路故障
     */
    MULTI_LINE_LINE_FAILURE("多线线路故障", (short) 0xf2, "-1,8", 0x59) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },
    /**
     * 多线线路故障恢复
     */
    MULTI_LINE_LINE_FAILURE_RECOVERY("多线线路故障恢复", (short) 0xf2, "-1,8", 0x5A) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MultiLineAlarmParser(ins);
        }
    },
    /**
     * 模拟报警
     */
    ANALOG_ALARM("模拟报警", (short) -1, 0x8b) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new SimulationAlarmParser(ins);
        }
    },
    /**
     * 监管报警
     */
    REGULATORY_ALARMS("监管报警", 0x8c) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    /**
     * 监管报警撤销
     */
    REGULATORY_ALARM_REVOCATION("监管报警撤销", 0x8d) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonAlarmParser(ins);
        }
    },

    ;

    public static final Map<Integer, AlarmEnum> ALARM_MAP = new HashMap<>();

    static {
        AlarmEnum[] alarmEnums = AlarmEnum.values();
        for (AlarmEnum alarm : alarmEnums) {
            for (int i : alarm.code) {
                ALARM_MAP.put(i, alarm);
            }
        }
    }

    /**
     * 描述
     */
    private final String desc;

    /**
     * 回路
     */
    private final short circuit;

    /**
     * 需要计算的盘号和专线号以逗号分割
     */
    private final String count;

    /**
     * 对应的命令编码
     */
    private final int[] code;

    public abstract AbstractParser execute(InputStream in);

    AlarmEnum(String desc, int... i) {
        this.circuit = 0;
        this.count = null;
        this.desc = desc;
        this.code = i;
    }

    AlarmEnum(String desc, short circuit, int... i) {
        this.desc = desc;
        this.circuit = circuit;
        this.count = null;
        this.code = i;
    }

    AlarmEnum(String desc, short circuit, String param, int... i) {
        this.desc = desc;
        this.circuit = circuit;
        this.count = param;
        this.code = i;
    }
}
