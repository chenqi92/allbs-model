package cn.allbs.utils.SFJK200.format;

import cn.allbs.core.Configurator;
import cn.allbs.core.Configured;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

/**
 * 类 SFJK200Generator
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/8 16:24
 */
public class SFJK200Generator implements Configured<SFJK200Generator>, Closeable {

    protected Writer writer;

    public SFJK200Generator(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void configured(Configurator<SFJK200Generator> configurator) {
        configurator.config(this);
    }

    @Override
    public void close() throws IOException {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
