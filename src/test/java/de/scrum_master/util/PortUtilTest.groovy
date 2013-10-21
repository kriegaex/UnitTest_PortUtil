package de.scrum_master.util

import spock.lang.*

/**
 * Unit-test {@link PortUtil} class
 */
class PortUtilTest extends Specification {
    static PortUtil portUtil = PortUtil.getInstance()

    def "get available port"() {
        when: def port = portUtil.getAvailablePort()
        then: port >= 1024 && port < 65536
    }

    def "cannot get unavailable port"() {
        when:
            def port = portUtil.getAvailablePort()
            ServerSocket socket = new ServerSocket(port)
            socket.setReuseAddress(true)
        then: !portUtil.isAvailable(port)
        cleanup: socket?.close()
    }

    def "running out of ports"() {
        when:
            def port = portUtil.getAvailablePort(PortUtil.MAX_PORT_OFFSET)
            ServerSocket socket = new ServerSocket(port)
            socket.setReuseAddress(true)
            port = portUtil.getAvailablePort(PortUtil.MAX_PORT_OFFSET)
        then:
            def exception = thrown(IOException)
            exception.message == "no port available"
        cleanup: socket?.close()
    }

    @Unroll("illegal port offset #offset")
    def "illegal port offsets"() {
        when: portUtil.getAvailablePort(offset)
        then: thrown(IllegalArgumentException)
        where: offset << [-1, PortUtil.MAX_PORT_OFFSET + 1]
    }

    @Unroll("illegal port no. #port")
    def "illegal port numbers"() {
        when: portUtil.isAvailable(port)
        then: thrown(IllegalArgumentException)
        where: port << [-1, PortUtil.MIN_PORT_NUMBER - 1, PortUtil.MAX_PORT_NUMBER + 1]
    }
}
