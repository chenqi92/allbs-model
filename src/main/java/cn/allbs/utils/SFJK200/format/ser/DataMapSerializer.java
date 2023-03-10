package cn.allbs.utils.SFJK200.format.ser;

import cn.allbs.core.Configurator;
import cn.allbs.core.Configured;
import cn.allbs.exception.SFJK200Exception;
import cn.allbs.utils.SFJK200.format.SFJK200Generator;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static cn.allbs.utils.SFJK200.enums.GeneratorEnum.*;

/**
 * 类 DataMapSerializer
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/10 10:54
 */
public class DataMapSerializer implements SFJK200Serializer<Map<String, Object>>, Configured<DataMapSerializer> {
    @Override
    public void configured(Configurator<DataMapSerializer> by) {
        by.config(this);
    }

    @Override
    public void serialize(SFJK200Generator generator, Map<String, Object> map) throws IOException, SFJK200Exception {
        generator.writeAddress(Optional.ofNullable(map).map(a -> Integer.valueOf(a.get(ADDRESS.getName()).toString())).orElseThrow(() -> new SFJK200Exception(ADDRESS.getDesc() + "不能为空!")));
        generator.writeFunction(Optional.ofNullable(map).map(a -> Integer.valueOf(a.get(FUNCTION.getName()).toString())).orElseThrow(() -> new SFJK200Exception(FUNCTION.getDesc() + "不能为空!")));
        generator.writeStartRegister(Optional.ofNullable(map).map(a -> Integer.valueOf(a.get(START_ADDRESS.getName()).toString())).orElseThrow(() -> new SFJK200Exception(START_ADDRESS.getDesc() + "不能为空!")));
        generator.writeReadRegister(Optional.ofNullable(map).map(a -> Integer.valueOf(a.get(READ_ADDRESS.getName()).toString())).orElseThrow(() -> new SFJK200Exception(READ_ADDRESS.getDesc() + "不能为空!")));
        generator.writeCrc();
    }
}
