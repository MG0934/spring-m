import cn.hutool.core.io.IoUtil;
import core.io.DefaultResourceLoader;
import core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceAndResourceLoaderTest {

    @Test
    public  void resourceLoaderTest() throws IOException {

        //加载classpath文件
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        Resource resource = defaultResourceLoader.getResource("classpath:a.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);

        //加载本地文件
        DefaultResourceLoader defaultResourceLoader1 = new DefaultResourceLoader();
        Resource resource1 = defaultResourceLoader1.getResource("C:\\Users\\mu\\Desktop\\b.txt");
        InputStream inputStream1 = resource1.getInputStream();
        String content1 = IoUtil.readUtf8(inputStream1);
        System.out.println(content1);

        //加载网络文件
        DefaultResourceLoader defaultResourceLoader2 = new DefaultResourceLoader();
        Resource resource2 = defaultResourceLoader2.getResource("https://github.com/DerekYRC/mini-spring/blob/main/README.md");
        InputStream inputStream2 = resource2.getInputStream();
        String content2 = IoUtil.readUtf8(inputStream2);
        System.out.println(content2);
    }

}
