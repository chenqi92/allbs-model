package cn.allbs.utils.SFJK200.format.ser;

import cn.allbs.exception.SFJK200Exception;
import cn.allbs.utils.SFJK200.format.SFJK200Generator;

import java.io.IOException;

/**
 * 接口 SFJK200Serializer
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/10 10:53
 */
public interface SFJK200Serializer<Target> {

    void serialize(SFJK200Generator generator, Target target) throws IOException, SFJK200Exception;
}
