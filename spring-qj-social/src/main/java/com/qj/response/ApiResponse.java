package com.qj.response;

public class ApiResponse {
	
	private String message;
	private boolean status;
    private Integer postId;
    private boolean saved;
	
	public ApiResponse() {
		
	}
	
    public ApiResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public ApiResponse(String message, boolean status, Integer postId, boolean saved) {
        this.message = message;
        this.status = status;
        this.postId = postId;
        this.saved = saved;
    }

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}
