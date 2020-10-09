package it.vizzarro.torganizer.utils.jsend.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import it.vizzarro.torganizer.utils.jsend.JSendDataObject;


public class JacksonDataObjectDeserializer extends StdDeserializer<JSendDataObject> {
	private static final long serialVersionUID = 1L;

	protected JacksonDataObjectDeserializer() {
		super( JSendDataObject.class );
	}

	@Override
	public JSendDataObject deserialize( JsonParser p, DeserializationContext ctxt )	throws IOException {
		return JacksonJsendDataObject.wrap( p.readValueAs( JsonNode.class ), p.getCodec() );
	}
}
