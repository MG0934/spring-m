package core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认资源加载器
 */
public class DefaultResourceLoader implements ResourceLoader {

    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    @Override
    public Resource getResource(String localtion) {

        if (localtion.startsWith(CLASSPATH_URL_PREFIX)) {
            //加载 classpath 下的资源
            return new ClassPathResource(localtion.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                //尝试加载url来处理
                URL url = new URL(localtion);
                return new UrlResource(url);
            } catch (MalformedURLException ex) {
                //当成文件系统下的资源处理
                return new FileSystemResource(localtion);
            }
        }
    }
}
