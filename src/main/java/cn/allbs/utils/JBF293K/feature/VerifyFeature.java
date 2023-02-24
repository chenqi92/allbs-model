package cn.allbs.utils.JBF293K.feature;

/**
 * 枚举 VerifyFeature
 * </p>
 * 数据验证
 *
 * @author ChenQi
 * @since 2023/2/23 17:47
 */
public enum VerifyFeature implements Feature {

    /**
     * 数据区长度
     */
    DATA_LEN_RANGE(false),

    /**
     * 累加和
     */
    SUMMATION(true),

    /**
     * 数据区CRC
     */
    DATA_CRC(true),

    /**
     * 启动校验
     */
    USE_VERIFICATION(false),

    /**
     * 校验失败报错
     */
    THROW_ERROR_VERIFICATION_FAILED(true);

    private final boolean _defaultState;
    private final int _mask;

    VerifyFeature(boolean defaultState) {
        _defaultState = defaultState;
        _mask = (1 << ordinal());
    }

    @Override
    public boolean enabledByDefault() {
        return _defaultState;
    }

    @Override
    public int getMask() {
        return _mask;
    }

    @Override
    public boolean enabledIn(int flags) {
        return (flags & _mask) != 0;
    }

}
