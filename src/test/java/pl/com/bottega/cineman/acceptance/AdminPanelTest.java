package pl.com.bottega.cineman.acceptance;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.cineman.application.AdminPanel;
import pl.com.bottega.cineman.application.CinemaCatalog;
import pl.com.bottega.cineman.model.commands.CreateCinemaCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class AdminPanelTest {

	@Autowired
	private AdminPanel adminPanel;

	@Autowired
	private CinemaCatalog cinemaCatalog;

	@Test
	public void shouldCreateCinema() {
		CreateCinemaCommand cmd = new CreateCinemaCommand();
		cmd.setName("Felicity");
		cmd.setCity("Lublin");

		adminPanel.createCinema(cmd);

		Assertions.assertThat(cinemaCatalog.getCinemas().size()).isEqualTo(1);
	}

}
