package cn.allbs.utils.gb26875.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 报文传输方向
 *
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum DirectEnums {

    /**
     * 上行
     */
    UP(0, "上行"),

    DOWN(1, "下行");

    private final Integer type;

    private final String desc;

}
