package cn.allbs.utils.gb26875.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 判断是否储存至数据库（或者判断是否将状态至与显示内容中）
 *
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum SaveEnum {

    /**
     * 保存
     */
    SAVE(),

    /**
     * 不保存
     */
    NOT_SAVE();
}
