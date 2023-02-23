package cn.allbs.utils.JBF293K.format;

import cn.allbs.utils.JBF293K.core.Configurator;
import cn.allbs.utils.JBF293K.core.Configured;
import cn.allbs.utils.JBF293K.exception.JBF293KException;
import cn.allbs.utils.JBF293K.format.data.AbstractParser;
import cn.allbs.utils.JBF293K.format.der.JBF293KDeserializer;

import java.io.IOException;
import java.util.Map;

/**
 * 类 DataMapDeserializer
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 14:13
 */
public class DataMapDeserializer implements JBF293KDeserializer<Map<String, Object>>, Configured<DataMapDeserializer> {

    @Override
    public void configured(Configurator<DataMapDeserializer> by) {
        by.config(this);
    }

    /**
     * 数据解析
     *
     * @param jbf293KParser
     * @return
     * @throws IOException
     * @throws JBF293KException
     */
    @Override
    public Map<String, Object> deserialize(JBF293KParser jbf293KParser) throws IOException, JBF293KException {
        Map<String, Object> resMap;
        try {
            // 包头
            jbf293KParser.readHeader();
            // 读取两个字节的数据判断解析为哪种
            short orderType = jbf293KParser.readOrderType();
            // 读取主要数据内容,除Crc校验内容
            AbstractParser abstractParser = jbf293KParser.readData(orderType);
            resMap = abstractParser.parseMap();
            // 累加和校验
            if (abstractParser.isHasData()) {
                // 读取报警值数据
                // crc 校验
                jbf293KParser.readCheckCrc(abstractParser.getCrcNum());
            } else {
                jbf293KParser.readCheck(abstractParser.getSummation());
            }
            // 包尾
            jbf293KParser.readFooter();
        } catch (IOException e) {
            throw new JBF293KException("报文内容有误!");
        }
        return resMap;
    }
}
