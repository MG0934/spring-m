package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源抽象和访问就接口
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
