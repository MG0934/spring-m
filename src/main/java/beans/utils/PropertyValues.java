package beans.utils;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue getPropertyValue(String propertyName) {

        for (PropertyValue propertyValue : propertyValueList) {

            if (propertyValue.getName().equalsIgnoreCase(propertyName)) {
                return propertyValue;
            }

        }
        return null;
    }

    public List<PropertyValue> getPropertyValues(){
        return propertyValueList;
    }
}
