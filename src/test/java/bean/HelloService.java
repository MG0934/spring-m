package bean;

import cn.hutool.core.util.StrUtil;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.context.annotation.Autowrite;
import org.springframework.context.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Driver;
import java.sql.Struct;

@Component
public class HelloService {
    @Value("${name}")
    private String value;

    @Autowrite
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void driver(){
        if(StrUtil.isNotEmpty(value)){
            System.out.println(value+" driver car");
        }else{
            System.out.println("default driver car");
        }
    }
}
