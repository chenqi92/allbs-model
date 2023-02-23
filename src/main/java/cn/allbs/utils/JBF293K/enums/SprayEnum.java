package cn.allbs.utils.JBF293K.enums;

import cn.allbs.utils.JBF293K.format.data.AbstractParser;
import cn.allbs.utils.JBF293K.format.data.BoardSprayParser;
import cn.allbs.utils.JBF293K.format.data.CommonSprayParser;
import cn.allbs.utils.JBF293K.format.data.MessageSprayParser;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Getter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 SprayEnum
 * </p>
 * 喷洒启动枚举
 *
 * @author ChenQi
 * @since 2023/2/21 15:19
 */
@Getter
public enum SprayEnum {

    /**
     * 气体线路故障
     */
    GAS_LINE_FAILURE("气体线路故障", 0xfa, (short) 0x01, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 气体线路故障恢复
     */
    GAS_LINE_FAILURE_RECOVERY("气体线路故障恢复", 0xfa, (short) 0x02, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 气体喷洒应答
     */
    GAS_SPRAY_RESPONSE("气体喷洒应答", 0xfa, (short) 0x03, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 气体喷洒应答撤销
     */
    GAS_SPRAYING_RESPONSE_WITHDRAWS("气体喷洒应答撤销", 0xfa, (short) 0x04, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 声光启动
     */
    SOUND_AND_LIGHT_START("声光启动", 0xfa, (short) 0x05, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 声光停止
     */
    SOUND_AND_LIGHT_STOP("声光停止", 0xfa, (short) 0x06, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 相关设备动作
     */
    RELATED_DEVICE_ACTIONS("相关设备动作", 0xfa, (short) 0x07, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 相关设备动作停止
     */
    THE_RELATED_DEVICE_ACTION_STOPS("相关设备动作停止", 0xfa, (short) 0x08, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 防火区启动
     */
    FIRE_ZONE_ACTIVATION("防火区启动", 0xfa, (short) 0x09, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 防火区停止
     */
    THE_FIRE_ZONE_STOPS("防火区停止", 0xfa, (short) 0x0a, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 喷洒启动
     */
    SPRAY_START("喷洒启动", 0xfa, (short) 0x0b, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 延时启动
     */
    DELAYED_START("延时启动", 0xfa, (short) 0x0c, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },
    /**
     * 远程停止动作撤销
     */
    REMOTE_STOP_ACTION_UNDO("远程停止动作撤销", 0xfa, (short) 0x0e, "-1,4") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new MessageSprayParser(ins);
        }
    },

    /**
     * 板故障
     */
    BOARD_FAILURE("板故障", 0x70, (short) 0x80, "板号") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new BoardSprayParser(ins);
        }
    },

    /**
     * 板故障恢复
     */
    BOARD_FAILURE_RECOVERY("板故障恢复", 0x70, (short) 0x90, "板号") {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new BoardSprayParser(ins);
        }
    },

    /**
     * 主电故障
     */
    MAIN_POWER_FAILURE("主电故障", 0x71, (short) 0x80) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonSprayParser(ins);
        }
    },

    /**
     * 主电故障恢复
     */
    MAIN_POWER_FAILURE_RECOVERY("主电故障恢复", 0x71, (short) 0x90) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonSprayParser(ins);
        }
    },

    /**
     * 备电故障
     */
    POWER_BACKUP_FAILURE("备电故障", 0x72, (short) 0x80) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonSprayParser(ins);
        }
    },

    /**
     * 备电故障恢复
     */
    STANDBY_FAULT_RECOVERY("备电故障恢复", 0x72, (short) 0x90) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new CommonSprayParser(ins);
        }
    },

    ;

    public static final Table<Integer, Short, SprayEnum> SPRAY_ENUM_TABLE = HashBasedTable.create();

    public static final Map<Integer, SprayEnum> SPRAY_ENUM_MAP = new HashMap<>(4);

    static {
        SprayEnum[] sprayEnums = SprayEnum.values();
        for (SprayEnum sprayEnum : sprayEnums) {
            SPRAY_ENUM_TABLE.put(sprayEnum.code, sprayEnum.messageType, sprayEnum);
            if (!SPRAY_ENUM_MAP.containsKey(sprayEnum.code)) {
                SPRAY_ENUM_MAP.put(sprayEnum.code, sprayEnum);
            }
        }
    }

    private final String orderName;

    private final int code;

    private final Integer control;

    private final short messageType;

    private final String part;

    public abstract AbstractParser execute(InputStream in);

    SprayEnum(String orderName, int code, short messageType, String part) {
        this.orderName = orderName;
        this.code = code;
        this.control = null;
        this.messageType = messageType;
        this.part = part;
    }

    SprayEnum(String orderName, int code, short messageType) {
        this.orderName = orderName;
        this.code = code;
        this.control = null;
        this.messageType = messageType;
        this.part = null;
    }
}
