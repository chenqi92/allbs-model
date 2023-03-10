package cn.allbs.utils.SFJK200.enums;

import lombok.Getter;

/**
 * 类 YesOrNoEnum
 * </p>
 * 数据位说明
 *
 * @author ChenQi
 * @since 2023/3/8 13:22
 */
@Getter
public enum YesOrNoEnum {

    /**
     * 是
     */
    YES("是", 1),

    /**
     * 否
     */
    NO("否", 0);

    private final String desc;

    private final Integer type;

    YesOrNoEnum(String desc, Integer type) {
        this.desc = desc;
        this.type = type;
    }
}
