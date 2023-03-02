package cn.allbs.utils.gb26875.core;

/**
 * @author ChenQi
 */
public interface Configurator<Target> {

    /**
     * 配置
     *
     * @param target 配置目标
     */
    void config(Target target);
}
