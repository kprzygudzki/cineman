package pl.com.bottega.cineman.application;

import pl.com.bottega.cineman.model.ShowingExporter;

import java.time.LocalDateTime;

public class ShowingDtoBuilder implements ShowingExporter {

	private ShowingDto dto = new ShowingDto();

	public ShowingDto build() {
		ShowingDto result = dto;
		dto = new ShowingDto();
		return result;
	}

	@Override
	public void addId(Long id) {
		dto.setId(id);
	}

	@Override
	public void addBeginsAt(LocalDateTime beginsAt) {
		dto.setTime(beginsAt.toLocalTime());
	}

}
