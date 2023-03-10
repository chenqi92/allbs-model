package cn.allbs.utils.SFJK200.enums;

import lombok.Getter;

/**
 * 类 GeneratorEnum
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/10 11:10
 */
@Getter
public enum GeneratorEnum {

    ADDRESS("从站地址"),

    FUNCTION("功能码"),

    START_ADDRESS("起始寄存器地址"),

    READ_ADDRESS("读寄存器地址");

    private final String name;

    private final String desc;

    GeneratorEnum(String desc) {
        this.name = this.name().toLowerCase();
        this.desc = desc;
    }
}
