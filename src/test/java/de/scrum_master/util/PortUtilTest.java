package de.scrum_master.util;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Checks the PortUtil for free use
 */
public class PortUtilTest {

	@Test
	public void testPortRetrieval() throws IOException {
		int port = PortUtil.getAvailablePort();
		assertTrue(port >= 1024 && port < 65536);
	}

	@Test
	public void testUnexpectedPort() {
		assertFalse(PortUtil.isAvailable(-232));
		assertFalse(PortUtil.isAvailable(1023));
		assertFalse(PortUtil.isAvailable(65536));
	}

}
