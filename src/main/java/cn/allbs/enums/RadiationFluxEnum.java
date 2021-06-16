package cn.allbs.enums;

import cn.allbs.constant.StringPoolConstant;
import cn.allbs.utils.IntervalUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 热辐射通量造成的损害
 *
 * @author ChenQi
 */
@Getter
@AllArgsConstructor
public enum RadiationFluxEnum {

    /**
     * 热辐射通量代表的等级
     */
    I(37.5, "[37.5,+∞)", "该范围内操作设备全部损坏", "该范围内人员10s内死亡率为1%,1min内死亡率为100%"),
    II(25.0, "[25,37.5)", "在无火焰、长时间辐射下，木材燃烧的最小能量", "该范围内人员10s内将被重大烧伤,1min内死亡率为100%"),
    III(12.5, "[12.5,25)", "有火焰时，木材燃烧，塑料熔化的最低能量", "该范围内人员10s内1度烧伤,1min内死亡率为1%"),
    IV(4.0, "[4,12.5)", "", "该范围内人员20s以上感觉疼痛，未必起泡"),
    V(1.6, "[1.6,4)", "", "该范围内人员长期辐射无不舒服感");

    private final Double num;

    /**
     * 热辐射通量影响范围
     */
    private final String numEffect;

    /**
     * 对设备的影响
     */
    private final String equipEffect;

    /**
     * 对人员的影响
     */
    private final String personEffect;

    /**
     * 查询热辐射通量造成的影响
     *
     * @param num 热辐射通量
     * @return 影响内容
     */
    public static String effect(double num) {
        RadiationFluxEnum[] radiationFluxEnums = RadiationFluxEnum.values();
        for (RadiationFluxEnum radiationFluxEnum : radiationFluxEnums) {
            if (IntervalUtil.isInTheInterval(String.valueOf(num), radiationFluxEnum.numEffect)) {
                return radiationFluxEnum.getPersonEffect() + StringPoolConstant.SEMICOLON + radiationFluxEnum.getEquipEffect();
            }
        }
        return "";
    }
}
