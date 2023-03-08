package cn.allbs.feature;

/**
 * 类 Feature
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/23 17:47
 */
public interface Feature {

    boolean enabledByDefault();

    int getMask();

    boolean enabledIn(int flags);


    static <F extends Enum<F> & Feature> int collectFeatureDefaults(Class<F> enumClass) {
        int flags = 0;
        for (F value : enumClass.getEnumConstants()) {
            if (value.enabledByDefault()) {
                flags |= value.getMask();
            }
        }
        return flags;
    }
}
