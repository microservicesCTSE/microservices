package com.pos;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
class PosDiscoveryApplicationTests {



	@Test
	public void applicationContextLoaded() {

		Assert.assertNotNull(getClass());
		
	}

	@Test
	public void main() {

		
		Assert.assertNotNull(getClass());
	}

}

