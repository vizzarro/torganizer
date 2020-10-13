package it.vizzarro.torganizer.service;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class BaseFilter {
    private static final Logger log = LoggerFactory.getLogger(BaseFilter.class);

    public BaseFilter(String q) throws ServiceException{
        if (!StringUtils.isEmpty(q)) {
            populate(q);
        }
    }

    public  void populate(String q) throws ServiceException {
        try {
            BeanInfo beanSourceInfo = Introspector.getBeanInfo(this.getClass());

            Map<String, PropertyDescriptor> sourceMapProps = new HashMap<>();
            Arrays.stream(beanSourceInfo.getPropertyDescriptors()).forEach(pd -> sourceMapProps.put(pd.getName(),pd));
           Map<String,Object> mapParam = new HashMap<>();
            Iterator it = Splitter.on(';').split(q).iterator();

            while(it.hasNext()) {
                Iterator paramIt  = Splitter.on('=').split((String)it.next()).iterator();
                String key = (String)paramIt.next();
                String param = (String)paramIt.next();
                mapParam.put(key, param);
            }

            for (PropertyDescriptor pd : beanSourceInfo.getPropertyDescriptors()) {

                if (pd.getPropertyType()
                        .isAssignableFrom(java.lang.String.class) ||
                        pd.getPropertyType()
                                .isAssignableFrom(java.lang.Long.class) ||
                        pd.getPropertyType()
                                .isAssignableFrom(java.lang.Boolean.class) ||
                        pd.getPropertyType()
                                .isAssignableFrom(java.lang.Integer.class) ||
                        pd.getPropertyType()
                                .isAssignableFrom(java.lang.Short.class) ||
                        pd.getPropertyType()
                                .isAssignableFrom(java.util.Date.class) ) {

                    pd.getWriteMethod()
                            .invoke(this, mapParam.get(pd.getName()));

                }



            }

        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            log.error("Error populate object", e);
        }

    }

}
