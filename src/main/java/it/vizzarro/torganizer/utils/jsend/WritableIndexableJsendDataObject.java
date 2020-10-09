package it.vizzarro.torganizer.utils.jsend;

public interface WritableIndexableJsendDataObject extends IndexableJSendDataObject {
	void setDataFor( String key, Object value );
	void removeDataFor( String key );
}
