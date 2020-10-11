package it.vizzarro.torganizer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public abstract class CrudServiceImpl<S,F extends BaseFilter,M,ID>  implements CrudService<S,F,M,ID> {
	private static final Logger log = LoggerFactory.getLogger(CrudServiceImpl.class);

	public  void populate(M target, S source) throws ServiceException {
		try {
			validate(target,source);
			BeanInfo beanSourceInfo = Introspector.getBeanInfo(source.getClass());
			BeanInfo beanTargetInfo = Introspector.getBeanInfo(target.getClass());
			Map<String,PropertyDescriptor> sourceMapProps = new HashMap<>();
			Arrays.stream(beanSourceInfo.getPropertyDescriptors()).forEach(pd -> sourceMapProps.put(pd.getName(),pd));
			for (PropertyDescriptor pd : beanTargetInfo.getPropertyDescriptors()) {
				log.debug("----------");
				log.debug("Property Name: " + pd.getName());
				log.debug("Property Display Name:" + pd.getDisplayName());
				log.debug("Property Type: " + pd.getPropertyType());

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
							.invoke(target, sourceMapProps.get(pd.getName()).getReadMethod().invoke(source));

				}else{
					populateProperty(pd.getName(),target,source);
				}
				
			}
			
			log.debug("End Setting");
		} catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
			log.error("Error populate object", e);
		}

	}

	protected abstract void validate(M target, S source) throws ServiceException;

	protected abstract void populateProperty(String name, M target, S source);

	public abstract S toServiceObject(M source);

}
