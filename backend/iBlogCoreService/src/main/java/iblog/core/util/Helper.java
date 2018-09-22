package iblog.core.util;

import java.util.HashMap;
import java.util.Map;

import iblog.core.model.Status;

public class Helper {
	private static final Map<Integer, Status> intToStatusMap = new HashMap<Integer, Status>();
	static {
	    for (Status type : Status.values()) {
	    	intToStatusMap.put(type.ordinal(), type);
	    }
	}

	public static Status fromIntStatus(int i) {
		Status type = intToStatusMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return Status.UNKNOWN;
	    return type;
	}
}
