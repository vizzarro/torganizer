package it.vizzarro.torganizer.utils.jsend;

import java.util.Set;

/**
 * This interface defines data objects that can be indexed by key to extract different values, working
 * like a readable map.
 * @author cristiano
 *
 */
public interface IndexableJSendDataObject extends JSendDataObject {
	/**
	 * This method will check if there is some data associated with the given key. Note
	 * that the data itself may be <code>null</code>, so even when this method returns
	 * <code>true</code>, {@link #getDataFor(String)} might still return <code>null</code>
	 * @param key the key to test
	 * @return <code>true</code> if some data is registered for said key, even if <code>null</code>
	 */
	boolean hasDataFor( String key );
	
	/**
	 * Calling this method will extract the value associated with the given key, wrapped as a 
	 * jsend data object. This method should return <code>null</code> either when no value is
	 * present for the given key, or if the value is <code>null</code>
	 * @param key the key to extract
	 * @return the value as a jsend data object, or <code>null</code> if no data is available 
	 */
	JSendDataObject getDataFor( String key );
	
	/**
	 * This method will return a list of the available data keys stored in this indexable data
	 * object. Note that some of these keys might still have <code>null</code> values
	 * @return a set of available keys, never <code>null</code> (but might be empty)
	 */
	Set<String> getDataKeys();
}
