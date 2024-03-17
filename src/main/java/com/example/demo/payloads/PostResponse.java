package com.example.demo.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	private List<PostDto> PostContent;
	private int pageNum;
	private int pageSize;
	private long total_records;
	private int total_pages;
	private boolean isLastPage;
	public void setTotal_records(long totalElements) {
		this.total_records = totalElements;
		}
}
