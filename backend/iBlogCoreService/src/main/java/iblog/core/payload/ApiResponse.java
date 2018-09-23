package iblog.core.payload;


public class ApiResponse {
    private Boolean success;
    private String message;
    private Object object;
    public ApiResponse(Boolean success, String message, Object object) {
        this.success = success;
        this.message = message;
        this.object = object;
    }

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
    
    
}
