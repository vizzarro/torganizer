package it.vizzarro.torganizer.utils.jsend;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.reflect.TypeToken;

/**
 * Base implementation class of the {@link JSendDataObject} interface used
 * when building instances of the response in memory. It simply wraps the
 * given object
 * @author cristiano
 *
 */
public class BasicJSendDataObject implements JSendDataObject {
	private final Object wrapped;
	
	BasicJSendDataObject( Object wrapped ) {
		this.wrapped = wrapped;
	}
	
	@JsonValue
	public Object getWrapped() {
		return this.wrapped;
	}

	@Override
	public <T> T getAs( Class<T> type ) throws JSendConversionException {
		if ( this.wrapped == null ) {
			return null;
		} else if ( type.isInstance( this.wrapped ) ) {
			return type.cast( this.wrapped );
		} else {
			throw new JSendConversionException( "Can only retrieve value as " + this.wrapped.getClass() );
		}
	}
	
	@SuppressWarnings( "unchecked" )
	@Override
	public <T> T getAs( TypeToken<T> type ) throws JSendConversionException {
		/*
		 * The generic type of the wrapped object (if any) has already been lost by the time
		 * we get its reference, so we simply trust the caller and make an unsafe typecast.
		 * If some way to find out the actual type can be found in future, it can be implemented here
		 */
		return (T) getAs( type.getRawType() );
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [" + this.wrapped + "]";
	}
	
	/**
	 * This method will wrap the given object in a basic jsend object, the exact returned type may
	 * be different depending on the received object. A <code>null</code> object will always yield
	 * a <code>null</code> return value
	 * @param object the object to wrap
	 * @return a wrapped object, or <code>null</code>
	 */
	public static BasicJSendDataObject wrap( Object object ) {
		if ( object == null ) {
			return null;
		}
		
		BasicJSendDataObject wrapped = null;
		if ( object instanceof Map ) {
			// if all keys are string, copy and make it an indexable wrapper, otherwise we'll treat it as a generic object
			Map<?, ?> original = (Map<?, ?>) object;
			
			Map<String, Object> copy = new HashMap<>();
			for ( Entry<?, ?> item : original.entrySet() ) {
				Object key = item.getKey();
				if ( key instanceof String ) {
					copy.put( (String) key, item.getValue() );
				} else {
					// found a non-string key, skip operation
					copy = null;
					break;
				}
			}
			
			if ( copy != null ) {
				wrapped = new MapBasicJsendDataObject( copy );
			}
		} 
		
		if ( wrapped == null ) {
			wrapped = new BasicJSendDataObject( object );
		}
		return wrapped;
	}
	
	/**
	 * This factory method will generate a new indexable and writable data object, used to lazily initialize
	 * local response 
	 * @return
	 */
	public static JSendDataObject newIndexableDataObject() {
		return new MapBasicJsendDataObject( new HashMap<String, Object>() );
	}
	
	/**
	 * A specialized internal class returned by the {@link #wrap(Object)} method when
	 * a map is wrapper, allow indexing and modification
	 * @author cristiano
	 *
	 */
	private static class MapBasicJsendDataObject extends BasicJSendDataObject implements WritableIndexableJsendDataObject {
		private Map<String, Object> wrappedMap;

		MapBasicJsendDataObject( Map<String, Object> wrapped ) {
			super( wrapped );
			this.wrappedMap = wrapped;
		}
		
		@Override
		public Map<String, Object> getWrapped() {
			return this.wrappedMap;
		}

		@Override
		public boolean hasDataFor( String key ) {
			return this.wrappedMap.containsKey( key );
		}

		@Override
		public JSendDataObject getDataFor( String key ) {
			Object obj = this.wrappedMap.get( key );
			return obj != null ? BasicJSendDataObject.wrap( obj ) : null;
		}

		@Override
		public Set<String> getDataKeys() {
			return this.wrappedMap.keySet();
		}

		@Override
		public void setDataFor( String key, Object value ) {
			this.wrappedMap.put( key, value );
		}

		@Override
		public void removeDataFor( String key ) {
			this.wrappedMap.remove( key );
		}
		
	}
}