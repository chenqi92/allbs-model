package cn.allbs.utils.SFJK200.format.der;

import cn.allbs.core.Configurator;
import cn.allbs.core.Configured;
import cn.allbs.exception.GB26875Exception;
import cn.allbs.exception.SFJK200Exception;
import cn.allbs.utils.SFJK200.enums.KeyWordEnum;
import cn.allbs.utils.SFJK200.format.SFJK200Parser;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 类 DataMapDeserializer
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/9 9:16
 */
public class DataMapDeserializer implements SFJK200Deserializer<Map<String, Object>>, Configured<DataMapDeserializer> {
    @Override
    public void configured(Configurator<DataMapDeserializer> by) {
        by.config(this);
    }

    /**
     * 数据解析
     *
     * @param sfjk200Parser
     * @return
     * @throws IOException
     * @throws GB26875Exception
     */
    @Override
    public Map<String, Object> deserialize(SFJK200Parser sfjk200Parser) throws IOException, SFJK200Exception {
        Map<String, Object> resMap = new LinkedHashMap<>(17);
        try {
            int address = sfjk200Parser.readAddress();
            resMap.put(KeyWordEnum.ADDRESS.getKey(), address);
            int function = sfjk200Parser.readFunction();
            resMap.put(KeyWordEnum.FUNCTION.getKey(), function);
            // 读取数据长度
            int len = sfjk200Parser.readDataLen();
            // 读取主要数据内容
            List<Map<String, Object>> resList = sfjk200Parser.readData(len);
            // CRC校验
            sfjk200Parser.readCheckCrc(len);
            // 包头
            resMap.put(KeyWordEnum.DATA.getKey(), resList);
        } catch (IOException | SFJK200Exception e) {
            throw new SFJK200Exception(e.getLocalizedMessage());
        }
        return resMap;
    }
}
