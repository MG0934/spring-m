package org.springframework.beans.factory;

import org.springframework.utils.StringValueResolver;

import java.util.Properties;

public class PlaceholderResolvingStringValueResolver implements StringValueResolver {

    private final Properties properties;

    public PlaceholderResolvingStringValueResolver(Properties properties) {

        this.properties = properties;
    }

    @Override
    public String resolverStringValue(String strVal) {
        return new PropertyPlaceholderConfigurer().resolvePlaceholder(strVal,properties);
    }
}
