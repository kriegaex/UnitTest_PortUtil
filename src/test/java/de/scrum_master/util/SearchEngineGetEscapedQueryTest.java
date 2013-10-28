package de.scrum_master.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SearchEngineGetEscapedQueryTest {
	public final static String ESCAPE_EXCLAMATION = "wqrexycta";
	public final static String ESCAPE_FUZZY = "wqrexyctb";
	public final static String ESCAPE_OR_TRIMMED = "wqrexyctc";
	public final static String ESCAPE_WILDCARD1 = "wqrexyctd";
	public final static String ESCAPE_WILDCARD2 = "wqrexycte";

	@Test
	/**
	 *  Test if the method deletes the character "*"
	 *  if it´s on the start of the query
	 */
	public void testWildcardStart()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("*test");
		assertTrue(query.equals("test"));
	}

	@Test
	/**
	 *  Test if the method replaces the character "*"
	 *  if it´s in the middle of the string
	 */
	public void testWildcardMiddle()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("te*st");
		assertTrue(query.equals("te"+ESCAPE_WILDCARD1+"st"));
	}

	@Test
	/**
	 *  Test if the method replaces the character "*"
	 *  if it´s between two words
	 */
	public void testWildcardBetween()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test * set");
		assertTrue(query.equals("test "+ESCAPE_WILDCARD1+" set"));
	}

	@Test
	/**
	 *  Test if the method replaces the character "*"
	 *  if it´s on the end of the query
	 */
	public void testWildcardEnd()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test*");
		assertTrue(query.equals("test"+ESCAPE_WILDCARD1));
	}

	@Test
	/**
	 *  Test if the method deletes the question mark
	 *  if it´s on the start of the query
	 */
	public void testQuestionMarkStart()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("?test");
		assertTrue(query.equals("test"));
	}

	@Test
	/**
	 *  Test if the method replaces the question mark
	 *  if it´s in the middle of a string
	 */
	public void testQuestionMarkMiddle()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("te?st");
		assertTrue(query.equals("te"+ESCAPE_WILDCARD2+"st"));
	}

	@Test
	/**
	 *  Test if the method replaces the question mark
	 *  if it´s between two words
	 */
	public void testQuestionMarkBetween()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test ? set");
		assertTrue(query.equals("test "+ESCAPE_WILDCARD2+" set"));
	}

	@Test
	/**
	 *  Test if the method replaces the question mark
	 *  if it´s on the end of the query
	 */
	public void testQuestionMarkEnd()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test ?");
		assertTrue(query.equals("test "+ESCAPE_WILDCARD2));
	}

	@Test
	/**
	 *  Test if the method replaces the exclamation mark
	 *  at the end of the string
	 */
	public void testExclamationMarkStart()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("!test");
		assertTrue(query.equals(ESCAPE_EXCLAMATION+"test"));
	}

	@Test
	/**
	 *  Test if the method replaces the exclamation mark
	 *  at the middle of the string
	 */
	public void testExclamationMarkMiddle()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("te!st");
		assertTrue(query.equals("te"+ESCAPE_EXCLAMATION+"st"));
	}

	@Test
	/**
	 *  Test if the method replaces teh exclamation mark
	 *  if it´s between two words
	 */
	public void testExclamationMarkBetween()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test ! set");
		assertTrue(query.equals("test "+ESCAPE_EXCLAMATION+" set"));
	}

	@Test
	/**
	 *  Test if the method replaces the exclamation mark
	 *  at the start of the string
	 */
	public void testExclamationMarkEnd()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test!");
		assertTrue(query.equals("test"+ESCAPE_EXCLAMATION));
	}

	@Test
	/**
	 *  Test if the method replaces fuzzy at the start
	 *  of the string
	 */
	public void testFuzzyStart()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("+test");
		assertTrue(query.equals(ESCAPE_FUZZY+"test"));
	}

	@Test
	/**
	 *  Test if the method replaces fuzzy at the middle
	 *  of the string
	 */
	public void testFuzzyMiddle()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("te+st");
		assertTrue(query.equals("te"+ESCAPE_FUZZY+"st"));
	}

	@Test
	/**
	 *  Test if the method replaces fuzzy between
	 *  two words
	 */
	public void testFuzzyBetween()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test + set");
		assertTrue(query.equals("test "+ESCAPE_FUZZY+" set"));
	}

	@Test
	/**
	 *  Test if the method replaces fuzzy at the end
	 *  of the string
	 */
	public void testFuzzyEnd() throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test+");
		assertTrue(query.equals("test"+ESCAPE_FUZZY));
	}

	@Test
	/**
	 *  Test if the method don´t replaces "or"
	 *  if it´s on the start of the string
	 */
	public void testOrStartLowerCase() throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("or test");
		assertTrue(query.equals("or test"));
	}

	@Test
	/**
	 *  Test if the method don´t replaces "OR"
	 *  if it´s on the start of the string
	 */
	public void testOrStartUpperCase() throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("OR test");
		assertTrue(query.equals("OR test"));
	}

	@Test
	/**
	 *  Test if the method replaces "or" between
	 *  two words
	 */
	public void testOrBetweenLowerCase()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test or set");
		assertTrue(query.equals("test "+ESCAPE_OR_TRIMMED+" set"));
	}

	@Test
	/**
	 *  Test if the method replaces "OR" between
	 *  two words
	 */
	public void testOrBetweenUpperCase()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test OR set");
		assertTrue(query.equals("test "+ESCAPE_OR_TRIMMED+" set"));
	}

	@Test
	/**
	 *  Test if the method don´t replaces "or"
	 *  if it is on the end of the string
	 */
	public void testOrEndLowerCase()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test or");
		assertTrue(query.equals("test or"));
	}

	@Test
	/**
	 *  Test if the method don´t replaces "OR"
	 *  if it is on the end of the string
	 */
	public void testOrEndUpperCase()throws Exception{
		String query = SearchEngineImpl.getEscapedQuery("test OR");
		assertTrue(query.equals("test OR"));
	}
}
