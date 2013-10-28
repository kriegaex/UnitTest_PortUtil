package de.scrum_master.util

import org.junit.Rule
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule
import spock.lang.*

import static org.powermock.api.mockito.PowerMockito.*
import static org.mockito.Mockito.*

/**
 * Unit-test {@link PortUtil} class
 */
@PrepareForTest([PortUtil.class])
class PortUtilTest extends Specification {
	@Rule PowerMockRule rule = new PowerMockRule();

	def "private constructor"() {
		expect:
		new PortUtil()
	}

	def "get available port"() {
		setup:
		def serverSocket = mock(ServerSocket)
		def datagramSocket = mock(DatagramSocket)
		when:
		whenNew(ServerSocket.class).withArguments(anyInt()).thenReturn(serverSocket)
		whenNew(DatagramSocket.class).withArguments(anyInt()).thenReturn(datagramSocket)
		def port = PortUtil.getAvailablePort()
		then:
		port >= PortUtil.MIN_PORT_NUMBER && port < PortUtil.MAX_PORT_NUMBER
	}

	def "get available port (mockStatic)"() {
		// This rather pointless test does not create any code coverage. It is just a showcase of how 'mockStatic'
		// could be used when testing another class depending on PortUtil. TODO: remove test
		setup:
		mockStatic(PortUtil.class)
		when:
		when(PortUtil.getAvailablePort()).thenReturn(12345)
		then:
		PortUtil.getAvailablePort() == 12345
	}

	def "cannot get unavailable port"() {
		when:
		whenNew(ServerSocket.class).withArguments(anyInt()).thenThrow(IOException)
		then:
		!PortUtil.isAvailable(PortUtil.MAX_PORT_NUMBER)
	}

	def "running out of ports"() {
		setup:
		spy(PortUtil.class)
		when:
		doReturn(false).when(PortUtil.class, "isAvailable", anyInt())
		PortUtil.getAvailablePort(PortUtil.MAX_PORT_OFFSET - 3);
		then:
		def exception = thrown(IOException)
		exception.message == "no port available"
	}

	def "running out of ports (mockStatic)"() {
		// This rather pointless test does not create any code coverage. It is just a showcase of how 'mockStatic'
		// could be used when testing another class depending on PortUtil. TODO: remove test
		setup:
		mockStatic(PortUtil.class)
		when:
		when(PortUtil.getAvailablePort()).thenThrow(new IOException("no port available"))
		PortUtil.getAvailablePort()
		then:
		def exception = thrown(IOException)
		exception.message == "no port available"
	}

	@Unroll("illegal port offset #offset")
	def "illegal port offsets"() {
		when:
		PortUtil.getAvailablePort(offset)
		then:
		thrown(IllegalArgumentException)
		where:
		offset << [-1, PortUtil.MAX_PORT_OFFSET + 1]
	}

	@Unroll("illegal port no. #port")
	def "illegal port numbers"() {
		when:
		PortUtil.isAvailable(port)
		then:
		thrown(IllegalArgumentException)
		where:
		port << [-1, PortUtil.MIN_PORT_NUMBER - 1, PortUtil.MAX_PORT_NUMBER + 1]
	}
}
