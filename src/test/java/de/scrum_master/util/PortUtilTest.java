package de.scrum_master.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Checks the PortUtil for free use
 */
public class PortUtilTest {

	@Test
	public void testPortRetrieval() {
		int port = PortUtil.getAvailablePort();
		assertTrue(port > 1024 && port < 65536);
	}

	@Test
	public void testUnexpectedPort() {
		assertFalse(PortUtil.available(-232));
		assertFalse(PortUtil.available(1023));
		assertFalse(PortUtil.available(65536));
	}

}
