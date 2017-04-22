package pl.com.bottega.cineman.application;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;

public class ShowingDto {

	private Long id;

	@JsonFormat(pattern = "hh:mm")
	private LocalTime time;

	public ShowingDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

}
