package it.vizzarro.torganizer.utils.jsend;


import java.util.Objects;

import it.vizzarro.torganizer.utils.jsend.jackson.JacksonDataObjectDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.TypeToken;


/**
 * This class is a base response using the JSend standard (https://labs.omniti.com/labs/jsend). It
 * will allow to read base response informations (e.g. status and message), and to access the data
 * payload in an abstract way, converting it to the expected type
 * @author cristiano
 *
 */
@JsonInclude( Include.NON_NULL )
public class JSendResponse {
	private static final String READ_FAILURE_ERROR_TEMPLATE = "Failed attempt to read {} as {}";
	private static final String CONVERSION_EXCEPTION_MESSAGE_BASE = "Cannot convert to ";

	private static Logger _logger = LoggerFactory.getLogger( JSendResponse.class );
	
	private JSendResponseStatus status;
	
	@JsonDeserialize( using = JacksonDataObjectDeserializer.class )
	@JsonProperty
	private JSendDataObject data;
	
	// The following are only used for error messages
	private Long code;
	private String message;
	
	protected JSendResponse() { /* base constructor for deserialization */ }
	
	/**
	 * Protected constructor used by factory methods, will wrap the object in an appropriate
	 * basic jsend data object. This is the main method used to generate new responses from
	 * client code - e.g. when replying to a web service call
	 * @param status the response status, cannot be <code>null</code>
	 * @param data the data object to use, may be <code>null</code>
	 * @param code the error code, only needed when status is <code>error</code>
	 * @param message the error message, only needed when status is <code>error</code>
	 */
	protected JSendResponse( JSendResponseStatus status, Object data, Long code, String message ) {
		this( status, BasicJSendDataObject.wrap( data ), code, message );
	}
	
	/**
	 * This protected constructor allows using an arbitrary data object instance, and is mainly
	 * meant for testing purposes
	 * @param status the response status, cannot be <code>null</code>
	 * @param data the data object to use, or <code>null</code> to have an empty data object
	 * @param code the error code, only needed when status is <code>error</code>
	 * @param message the error message, only needed when status is <code>error</code>
	 */
	protected JSendResponse( JSendResponseStatus status, JSendDataObject data, Long code, String message ) {
		this.status = Objects.requireNonNull( status );
		this.data = data;
		this.code = code;
		this.message = message;
	}
	
	/**
	 * The outcome status of the response. This is usually (but not necessarily) sent back
	 * to the clients with an appropriate http code (e.g. a 4xx code for a <code>fail</code>
	 * response)
	 * @return
	 */
	public JSendResponseStatus getStatus() {
		return this.status;
	}
	
	/**
	 * This method will set the specific payload key to the given value, adding a payload
	 * if none was present previously. Note that even a null value will be explicitly set
	 * in the payload since it might have a different meaning to the response than not
	 * having the payload key at all
	 * @param key
	 * @param value
	 * @return this response for method chaining
	 * @throws UnsupportedOperationException if the payload of this response does not support setting data values
	 */
	public synchronized JSendResponse setData( String key, Object value ) {
		if ( this.data == null ) {
			this.data = BasicJSendDataObject.newIndexableDataObject();
		}
		
		if ( !( this.data instanceof WritableIndexableJsendDataObject ) ) {
			throw new UnsupportedOperationException( "The payload of this response does not support setting data keys" );
		}
		
		// note: add even a null here since it might be semantically different from not having the key at all
		( (WritableIndexableJsendDataObject) this.data ).setDataFor( key, new BasicJSendDataObject( value ) );
		
		return this;
	}
	
	/**
	 * Removes a payload entry. If the key was not present in the response the
	 * method simply ignores the request 
	 * @param key 
	 * @return this response for method chaining
	 * @throws UnsupportedOperationException if the payload of this response does not support setting data values
	 */
	public synchronized JSendResponse removeData( String key ) {
		if ( this.data != null ) {
			if ( !( this.data instanceof WritableIndexableJsendDataObject ) ) {
				throw new UnsupportedOperationException( "The payload of this response does not support setting data keys" );
			}
			
			( (WritableIndexableJsendDataObject) this.data ).removeDataFor( key );
		}
		return this;
	}
	
	/**
	 * This method will return the set of keys available is the jsend data payload, as an
	 * immutable set. This method never returns <code>null</code> - even if no data is
	 * available at all, an empty set is returned.
	 * @return
	 * @throws UnsupportedOperationException if the payload of this response does not support reading data values
	 */
	@JsonIgnore
	public ImmutableSet<String> getDataKeys() {
		if ( !(data instanceof IndexableJSendDataObject) ) {
			throw new UnsupportedOperationException( "The payload of this response does not support reading data keys" );
		}
		
		return ImmutableSet.copyOf( ( (IndexableJSendDataObject) this.data ).getDataKeys() );
	}
	
	/**
	 * This method returns a {@link JSendDataObject} wrapper that allows impromptu
	 * conversion between the returned json and specific classes. In the common
	 * case in which the expected type of the returned response is already known,
	 * the <code>getData</code> methods should be preferred to this one.
	 * @param key
	 * @return
	 * @see #getData(String, Class)
	 * @see #getData(String, TypeToken)
	 * @throws UnsupportedOperationException if the payload of this response does not support reading data values
	 */
	public JSendDataObject getDataWrapper( String key ) {
		if ( !(data instanceof IndexableJSendDataObject) ) {
			throw new UnsupportedOperationException( "The payload of this response does not support reading data keys" );
		}
		
		return ( (IndexableJSendDataObject) this.data ).getDataFor( key ); 
	}
	
	/**
	 * <p>Retrieves the data contained at the given key as the required class, if
	 * possible. If there is no data for the given key, <code>null</code> is
	 * returned, while if the data cannot be converted to the required class,
	 * an exception is thrown.</p>
	 * <p>If more control is desired over the conversion process, try extracing
	 * the {@link JSendDataObject} wrapper and use it directly</p>
	 * @param key
	 * @param type
	 * @return
	 * @see #getDataWrapper(String)
	 * @See {@link #getData(String, TypeToken)}
	 * @throws UnsupportedOperationException if the payload of this response does not support reading data values
	 */
	public <T> T getData( String key, Class<T> type ) {
		JSendDataObject dobj = getDataWrapper( key );
		try {
			return dobj != null ? dobj.getAs( type ) : null;
		} catch ( JSendConversionException ex ) {
			_logger.error( READ_FAILURE_ERROR_TEMPLATE, dobj, type );
			throw new IllegalArgumentException( CONVERSION_EXCEPTION_MESSAGE_BASE + type.getName() );
		} 
	}
	
	/**
	 * <p>
	 * Retrieves the data contained at the given key converted to the class
	 * described by the given token. This method can be used to retrieve
	 * generic-based values like list of complex objects. A TypeToken is
	 * used by subclassing (e.g. <code>new TypeToken&lt;List&lt;Person&gt;&gt;() {}</code>)
	 * - for more informations, consult the javadoc of the TypeToken class. 
	 * </p>
	 * <p>All behavioral considerations explained about the {@link #getData(String, Class)}
	 * method apply to this method as well</p>
	 * 
	 * @param key
	 * @param type
	 * @return
	 * @throws UnsupportedOperationException if the payload of this response does not support reading data values
	 */
	public <T> T getData( String key, TypeToken<T> type ) {
		JSendDataObject dobj = getDataWrapper( key );
		try {
			return dobj != null ? dobj.getAs( type ) : null;
		} catch ( JSendConversionException ex ) {
			_logger.error( READ_FAILURE_ERROR_TEMPLATE, dobj, type, ex );
			throw new IllegalArgumentException( CONVERSION_EXCEPTION_MESSAGE_BASE + type.toString() );
		} 
	}
	
	@JsonIgnore
	public JSendDataObject getWrappedPayload() {
		// TODO: we should make a copy if possible (i.e. add an optional copying method to the data object interface(s)) 
		return this.data;
	}
	
	public <T> T getPayload( Class<T> targetClass ) {
		try {
			return this.data != null ? this.data.getAs( targetClass ) : null;
		} catch ( JSendConversionException ex ) {
			_logger.error( READ_FAILURE_ERROR_TEMPLATE, this.data, targetClass, ex );
			throw new IllegalArgumentException( CONVERSION_EXCEPTION_MESSAGE_BASE + targetClass.toString() );
		}
	}
	
	public <T> T getPayload( TypeToken<T> targetType ) {
		try {
			return this.data != null ? this.data.getAs( targetType ) : null;
		} catch ( JSendConversionException ex ) {
			_logger.error( READ_FAILURE_ERROR_TEMPLATE, this.data, targetType, ex );
			throw new IllegalArgumentException( CONVERSION_EXCEPTION_MESSAGE_BASE + targetType.toString() );
		}
	}
	
	/**
	 * The error code of the response. This can be present when the status is
	 * <code>error</code>, but is not required (and thus should not be relied
	 * upon unless listed in the specific implementation).
	 * @return
	 */
	public Long getCode() {
		return this.code;
	}
	
	/**
	 * The error message returned by the response. This should always be present when the
	 * status is <code>error</code>, but not for any other state 
	 * @return
	 */
	public String getMessage() {
		return this.message;
	}
	
	// builders
	/**
	 * This factory method will create a successful response with no payload. Payload
	 * keys can be added subsequently.
	 * @return
	 * @see #setData(String, Object)
	 */
	public static JSendResponse success() {
		return new JSendResponse( JSendResponseStatus.success, null, null, null );
	}
	
	/**
	 * This factory method will create a successful response with an arbitrary payload.
	 * This method is mainly useful for retrocompatibility, prefer using the variant
	 * with key and value fields
	 * @param payload
	 * @return
	 * 
	 * @see #success(String, Object)
	 */
	public static JSendResponse success( Object payload ) {
		return new JSendResponse( JSendResponseStatus.success, payload, null, null );
	}
	
	/**
	 * This factory method will create a successful response with a single payload
	 * element. More payload entries may be added later to the response
	 * @param dataKey the key to use for the payload entry
	 * @param dataValue the value of the payload entry
	 * @return
	 * @see #setData(String, Object)
	 */
	public static JSendResponse success( String dataKey, Object dataValue ) {
		JSendResponse response = success();
		response.setData( dataKey, dataValue );
		return response;
	}
	
	/**
	 * This factory method creates a failed response with no payload. Payload
	 * keys can be added later
	 * @return
	 * @see #setData(String, Object)
	 */
	public static JSendResponse fail() {
		return new JSendResponse( JSendResponseStatus.fail, null, null, null );
	}
	
	/**
	 * This factory method creates an error response with no message or error code
	 * @return
	 */
	public static JSendResponse error() {
		return new JSendResponse( JSendResponseStatus.error, null, null, null );
	}
	
	/**
	 * This factory method creates an error response with the given message and
	 * no error code
	 * @return
	 */
	public static JSendResponse error( String message ) {
		return new JSendResponse( JSendResponseStatus.error, null, null, message );
	}
	
	/**
	 * This factory method creates an error response with the given message and
	 * error code
	 * @return
	 */
	public static JSendResponse error( Long code, String message ) {
		return new JSendResponse( JSendResponseStatus.error, null, code, message );
	}

	@Override
	public String toString() {
		return "JSendResponse ["
				+ "status=" + this.status 
				+ ", data=" + this.data 
				+ ", code=" + this.code 
				+ ", message=" + this.message 
		+ "]";
	}
}
