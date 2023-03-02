package cn.allbs.utils.gb26875.format;

import cn.allbs.utils.gb26875.core.Configurator;
import cn.allbs.utils.gb26875.core.Configured;
import cn.allbs.utils.gb26875.enums.ControlUnitEnum;
import cn.allbs.utils.gb26875.exception.GB26875Exception;
import cn.allbs.utils.gb26875.format.der.GB26875Deserializer;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static cn.allbs.utils.gb26875.enums.ConstEnum.*;

/**
 * @author ChenQi
 */
public class DataMapDeserializer implements GB26875Deserializer<Map<String, Object>>, Configured<DataMapDeserializer> {
    @Override
    public void configured(Configurator<DataMapDeserializer> by) {
        by.config(this);
    }

    /**
     * 数据解析
     *
     * @param gb26875Parser
     * @return
     * @throws IOException
     * @throws GB26875Exception
     */
    @Override
    public Map<String, Object> deserialize(GB26875Parser gb26875Parser) throws IOException, GB26875Exception {
        Map<String, Object> resMap = new LinkedHashMap<>(17);
        try {
            // 包头
            gb26875Parser.readHeader();
            resMap.put(FLOW.getConstDefined(), gb26875Parser.readSerialNum());
            resMap.put(MAIN_VERSION.getConstDefined(), gb26875Parser.readMainVersion());
            resMap.put(MINOR_VERSION.getConstDefined(), gb26875Parser.readUserVersion());
            resMap.put(TIME.getConstDefined(), gb26875Parser.readTime());
            resMap.put(SOURCE_ADDRESS.getConstDefined(), gb26875Parser.readSourceAddress());
            resMap.put(TARGET_ADDRESS.getConstDefined(), gb26875Parser.readTargetAddress());
            short dataLen = gb26875Parser.readDataLen();
            resMap.put(CONTROL_ORDER.getConstDefined(), ControlUnitEnum.orderTrans(gb26875Parser.readControlOrder()));
            resMap.put(DATA.getConstDefined(), gb26875Parser.readData(dataLen));
            // 数据校验
            gb26875Parser.readCheck();
            // 包尾 作为尾分隔符已去除，否则无法解决粘包问题
//            gb26875Parser.readFooter();
        } catch (IOException e) {
            throw new GB26875Exception("报文内容有误!");
        }
        return resMap;
    }
}
