package de.scrum_master.util

import spock.lang.Specification
import spock.lang.Unroll

class SearchEngineEscapeTest extends Specification {
	static final String ESC_BASE = "wqrexyct";

	static final String ESC_EXCLAMATION = ESC_BASE + "a";
	static final String ESC_PLUS        = ESC_BASE + "b";
	static final String ESC_OR          = ESC_BASE + "c";
	static final String ESC_ASTERISK    = ESC_BASE + "d";
	static final String ESC_QUESTION    = ESC_BASE + "e";

	@Unroll("Escape('#input') -> '#result'")
	def "Escaped queries"() {
		expect:
		result.equals(SearchEngineImpl.getEscapedQuery(input));

		where:
		input         | result
		null          | null
		"*test"       | "test"
		"te*st"       | "te" + ESC_ASTERISK + "st"
		"test * set"  | "test " + ESC_ASTERISK + " set"
		"test*"       | "test" + ESC_ASTERISK
		"?test"       | "test"
		"te?st"       | "te" + ESC_QUESTION + "st"
		"test ? set"  | "test " + ESC_QUESTION + " set"
		"test ?"      | "test " + ESC_QUESTION
		"!test"       | ESC_EXCLAMATION + "test"
		"te!st"       | "te" + ESC_EXCLAMATION + "st"
		"test ! set"  | "test " + ESC_EXCLAMATION + " set"
		"test!"       | "test" + ESC_EXCLAMATION
		"+test"       | ESC_PLUS + "test"
		"te+st"       | "te" + ESC_PLUS + "st"
		"test + set"  | "test " + ESC_PLUS + " set"
		"test+"       | "test" + ESC_PLUS
		"or test"     | "or test"
		"OR test"     | "OR test"
		"test or set" | "test " + ESC_OR + " set"
		"test OR set" | "test " + ESC_OR + " set"
		"test or"     | "test or"
		"test OR"     | "test OR"
	}

	@Unroll("Unescape('#input') -> '#result'")
	def "Unescaped queries"() {
		expect:
		result.equals(SearchEngineImpl.getUnEscapedQuery(input));

		where:
		input                               | result
		null                                | null
		ESC_EXCLAMATION + "test"            | "!test"
		"test" + ESC_EXCLAMATION + "test"   | "test!test"
		"test " + ESC_EXCLAMATION + " test" | "test ! test"
		"test" + ESC_EXCLAMATION            | "test!"
		ESC_PLUS + "test"                   | "+test"
		"test" + ESC_PLUS + "test"          | "test+test"
		"test " + ESC_PLUS + " test"        | "test + test"
		"test" + ESC_PLUS                   | "test+"
		ESC_ASTERISK + "test"               | "*test"
		"test" + ESC_ASTERISK + "test"      | "test*test"
		"test " + ESC_ASTERISK + " test"    | "test * test"
		"test" + ESC_ASTERISK               | "test*"
		ESC_QUESTION + "test"               | "?test"
		"test" + ESC_QUESTION + "test"      | "test?test"
		"test " + ESC_QUESTION + " test"    | "test ? test"
		"test" + ESC_QUESTION               | "test?"
	}
}
