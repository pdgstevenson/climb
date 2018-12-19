package uk.co.tatari.climb.web.utils;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;


public class SimpleLookupsMapEditor<T> extends PropertyEditorSupport {

	private Map<String, T> map = new HashMap<String, T>(0);
    
	public SimpleLookupsMapEditor(Map<String, T> map){

    		this.map = map;
    }
    
	@Override
	public void setAsText(String type) throws IllegalArgumentException {
		
		T t = null;
		if (type != null && !type.isEmpty()) {
	
			t = map.get(type);			
		} else {
			throw new IllegalArgumentException("Input string must not be null or empty.");
		}
		setValue(t);
	}
}
