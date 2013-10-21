package de.scrum_master.util;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.Assert.*;

/**
 * Checks the PortUtil for free use
 */
public class PortUtilTest {
	private static PortUtil portUtil = PortUtil.getInstance();

	@Test
	public void testGetAvailablePort() throws IOException {
		int port = portUtil.getAvailablePort();
		assertTrue(port >= 1024 && port < 65536);
	}

	@Test
	public void testCannotGetUnavailablePort() throws IOException {
		int port = portUtil.getAvailablePort();
		ServerSocket socket = new ServerSocket(port);
		socket.setReuseAddress(true);
		assertFalse(portUtil.isAvailable(port));
		socket.close();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOffsetTooSmall() throws IOException {
		portUtil.getAvailablePort(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOffsetTooBig() throws IOException {
		portUtil.getAvailablePort(PortUtil.MAX_PORT_OFFSET + 1);
	}

	@Test
	public void testIllegalPortNumber() {
		assertFalse(portUtil.isAvailable(-1));
		assertFalse(portUtil.isAvailable(PortUtil.MIN_PORT_NUMBER - 1));
		assertFalse(portUtil.isAvailable(PortUtil.MAX_PORT_NUMBER + 1));
	}
}
