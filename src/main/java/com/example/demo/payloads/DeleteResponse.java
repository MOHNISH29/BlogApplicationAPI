package com.example.demo.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteResponse {

	String deleteMessage;
	
	public DeleteResponse(String deleteMessage)
	{
		this.deleteMessage  = deleteMessage;
	}
}
