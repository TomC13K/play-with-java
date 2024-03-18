import spock.lang.Specification

class DummyGroovyTest extends  Specification {
    def "first test"() {
        System.out.println("This is a test")
        expect:
        1 + 1 == 2
    }
}
