package com.reszy.searchflix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppController.class)
@WebAppConfiguration
public class MiniWrapperTests {

	@Test
	public void apiWrapperConnection() {
		MiniNetflixRouletteWrapper wrapper = new MiniNetflixRouletteWrapper();
		try {
			wrapper.getActorData("Nicolas Cage");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Can't connect to netflixroulette api, check conection");
		}
	}
}
