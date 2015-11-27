package com.read60.rest.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	public final static ObjectMapper mapper = new ObjectMapper();
	
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	public enum ErrorCode {
		Success (0, "Success"), 
		InvalidUsername (1, "Invalid Username"),
		InvalidPassword (2, "Invalid Password"), 
		InvalidArgument (3, "Invalid Argument"), 
		InvalidSession (4, "Invalid Session"), 
		DuplicateRecord (5, "Duplicate Record"), 
		DatabaseError (6, "Database Error"), 
		NoRecord (7, "No Record"), 
		Inactive (8, "Inactive");
		
		private int mErrorCode;
		private String mErrorMessage;       
		
	    private ErrorCode(int c, String s) {
	    	mErrorCode = c;
	    	mErrorMessage = s;
	    }

	    public boolean equalsMessage(String otherName) {
	        return (otherName == null) ? false : mErrorMessage.equals(otherName);
	    }
	    
	    public boolean equalsCode(int otherCode) {
	    	return mErrorCode == otherCode ? true : false ;
	    }
	    
	    public String toJson() {
	    	String response = null;;
			try {
				response = mapper.writeValueAsString(this);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return response;
	    }

		public int getmErrorCode() {
			return mErrorCode;
		}

		public void setmErrorCode(int mErrorCode) {
			this.mErrorCode = mErrorCode;
		}

		public String getmErrorMessage() {
			return mErrorMessage;
		}

		public void setmErrorMessage(String mErrorMessage) {
			this.mErrorMessage = mErrorMessage;
		}
	}
	
}
