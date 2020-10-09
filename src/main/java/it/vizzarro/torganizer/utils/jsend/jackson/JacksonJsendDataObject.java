package it.vizzarro.torganizer.utils.jsend.jackson;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.TypeToken;
import it.vizzarro.torganizer.utils.jsend.IndexableJSendDataObject;
import it.vizzarro.torganizer.utils.jsend.JSendConversionException;
import it.vizzarro.torganizer.utils.jsend.JSendDataObject;


public class JacksonJsendDataObject implements JSendDataObject {
	protected JsonNode jsonNode;
	protected ObjectCodec codec;
	
	private JacksonJsendDataObject( JsonNode node, ObjectCodec codec ) {
		this.jsonNode = node;
		this.codec = codec;
	}
	
	@Override
	public <T> T getAs( Class<T> type ) throws JSendConversionException {
		try {
			return codec.treeToValue( jsonNode, type );
		} catch ( JsonProcessingException ex ) {
			throw new JSendConversionException( ex );
		}
	}
	
	@Override
	public <T> T getAs( TypeToken<T> type ) throws JSendConversionException {
		try {
			JavaType jType = TypeFactory.defaultInstance().constructType( type.getType() );
			JsonParser jParser = codec.treeAsTokens( jsonNode );
			return codec.readValue( jParser, jType );
		} catch ( IOException ex ) {
			throw new JSendConversionException( ex );
		}
	}
	
	public static JacksonJsendDataObject wrap( JsonNode node, ObjectCodec codec ) {
		if ( node == null || node instanceof NullNode ) {
			return null;
		} if ( node instanceof ObjectNode ) {
			return new JacksonIndexableJsendDataObject( (ObjectNode) node, codec );
		} else {
			return new JacksonJsendDataObject( node, codec );
		}
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [" + this.jsonNode + "]";
	}
	
	/**
	 * Internal wrapper to expose json object fields as jsend data keys
	 * @author cristiano
	 *
	 */
	private static class JacksonIndexableJsendDataObject extends JacksonJsendDataObject implements IndexableJSendDataObject {
		
		public JacksonIndexableJsendDataObject( ObjectNode json, ObjectCodec codec ) {
			super( json, codec );
		}
		
		@Override
		public boolean hasDataFor( String key ) {
			return jsonNode.has( key );
		}

		@Override
		public JSendDataObject getDataFor( String key ) {
			JsonNode node = jsonNode.get( key );
			
			if ( node == null || node instanceof NullNode ) {
				return null;
			} else {
				return JacksonJsendDataObject.wrap( node, this.codec );
			}
		}

		@Override
		public Set<String> getDataKeys() {
			return ImmutableSet.copyOf( jsonNode.fieldNames() );
		}
	}

}
