package de.scrum_master.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * TODO javadoc for PortUtilTest
 */
public class PortUtilTest {

	@Test
	public void testPortRetrievment() {
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
