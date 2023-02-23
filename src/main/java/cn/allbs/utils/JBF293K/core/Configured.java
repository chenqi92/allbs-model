package cn.allbs.utils.JBF293K.core;

/**
 * 目标配置类
 *
 * @author ChenQi
 */
public interface Configured<Target> {

    /**
     * 配置器
     *
     * @param by
     */
    void configured(Configurator<Target> by);
}
