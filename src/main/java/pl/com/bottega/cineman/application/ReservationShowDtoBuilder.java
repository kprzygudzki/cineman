package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.ShowingExporter;

import java.time.LocalDateTime;

import static pl.com.bottega.cineman.application.ReservationDto.Show;

public class ReservationShowDtoBuilder implements ShowingExporter {

	private Show dto = new Show();

	public Show build() {
		Show result = dto;
		dto = new Show();
		return result;
	}

	@Override
	public void addId(Long id) {
		dto.setId(id);
	}

	@Override
	public void addBeginsAt(LocalDateTime beginsAt) {
		dto.setTime(beginsAt);
	}

}
