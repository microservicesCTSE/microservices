package com.pos.testInventory;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pos.inventory.PosInventoryApplication;


/**
 * @author Thilina Madhusanka
 *
 */
@ActiveProfiles("")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=PosInventoryApplication.class)
//@ComponentScan(basePackages = "com.bankingcore.service")
public abstract class AbstractTest {

	protected Logger logger=LoggerFactory.getLogger(this.getClass());
	
}
