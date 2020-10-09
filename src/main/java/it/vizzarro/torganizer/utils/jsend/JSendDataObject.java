package it.vizzarro.torganizer.utils.jsend;

import com.google.common.reflect.TypeToken;

/**
 * Generic interface that allows access to the data map returned in a {@link JSendResponse}
 * instance. Specific implementation will be library-dependent (e.g. jackson will have its
 * implementation, and gson another)
 * 
 * @author cristiano
 *
 */
public interface JSendDataObject {

	/**
	 * Return the data contained converted to the requested type, if possible. If the conversion
	 * cannot be performed, an exception is thrown
	 * 
	 * @param type
	 *            the desired object type
	 * @return an instance of the required type, if possible
	 * @throws JSendConversionException
	 */
	<T> T getAs( Class<T> type ) throws JSendConversionException;

	/**
	 * <p>
	 * Return the associated data converted to type described by the TypeToken instance, if
	 * possible. TypeToken is used by subclassing -e.g.
	 * <code>new TypeToken<List<String>>() {}</code> (for more information, see the TypeToken
	 * javadoc).
	 * </p>
	 * <p>
	 * If the conversion cannot be performed, an exception is thrown
	 * </p>
	 * 
	 * @param type
	 *            the TypeToken describing the desired object type
	 * @return an instance of the required type, if possible
	 * @throws JSendConversionException
	 */
	<T> T getAs( TypeToken<T> type ) throws JSendConversionException;
}
