package de.scrum_master.util;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SearchEngineGetUnEscapedQueryTest {
	@Test
	/**
	 *  Test if the method returns null if there is no input
	 */
	public void testNoInput()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery(null);
		assertNull(query);
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexycta" to a exclamation mark
	 *  if it´s on the start of the string
	 */
	public void testExclamationMarkStart()throws Exception {
		String query = SearchEngineImpl.getUnEscapedQuery("wqrexyctatest");
		assertTrue(query.equals("!test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexycta" to a exclamation mark
	 *  if it´s in the middle of the string
	 */
	public void testExclamationMarkMiddle()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("testwqrexyctatest");
		assertTrue(query.equals("test!test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexycta" to a exclamation mark
	 *  if it´s between two words
	 */
	public void testExclamationMarkBetween()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("test wqrexycta test");
		assertTrue(query.equals("test ! test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexycta" to a exclamation mark
	 *  if it´s on the end of the string
	 */
	public void testExclamationMarkEnd()throws Exception {
		String query = SearchEngineImpl.getUnEscapedQuery("testwqrexycta");
		assertTrue(query.equals("test!"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexyctb" to a plus
	 *  if it´s on the start of the string
	 */
	public void testPlusStart()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("wqrexyctbtest");
		assertTrue(query.equals("+test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexyctb" to a plus
	 *  if it´s in the middle of the string
	 */
	public void testPlusMiddle()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("testwqrexyctbtest");
		assertTrue(query.equals("test+test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexyctb" to a plus
	 *  if it´s between two words
	 */
	public void testPlusBetween()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("test wqrexyctb test");
		assertTrue(query.equals("test + test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexyctb" to a plus
	 *  if it´s on the end of a string
	 */
	public void testPlusEnd()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("testwqrexyctb");
		assertTrue(query.equals("test+"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexyctd" to a star
	 *  if it´s on the start of a string
	 */
	public void testStarStart()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("wqrexyctdtest");
		assertTrue(query.equals("*test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexyctd" to a star
	 *  if it´s in the middle of a string
	 */
	public void testStarMiddle()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("testwqrexyctdtest");
		assertTrue(query.equals("test*test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexyctd" to a star
	 *  if it´s between two words
	 */
	public void testStarBetween()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("test wqrexyctd test");
		assertTrue(query.equals("test * test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexyctd" to a star
	 *  if it´s on the end of a string
	 */
	public void testStarEnd()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("testwqrexyctd");
		assertTrue(query.equals("test*"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexycte" to a question mark
	 *  if it´s on the start of a string
	 */
	public void testQuestionMarkStart()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("wqrexyctetest");
		assertTrue(query.equals("?test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexycte" to a question mark
	 *  if it´s in the middle of a string
	 */
	public void testQuestionMarkMiddle()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("testwqrexyctetest");
		assertTrue(query.equals("test?test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexycte" to a question mark
	 *  if it´s between two words
	 */
	public void testQuestionMarkBetween()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("test wqrexycte test");
		assertTrue(query.equals("test ? test"));
	}

	@Test
	/**
	 *  Test if the method replaces the string "wqrexycte" to a question mark
	 *  if it´s on the end of a string
	 */
	public void testQuestionMarkEnd()throws Exception{
		String query = SearchEngineImpl.getUnEscapedQuery("testwqrexycte");
		assertTrue(query.equals("test?"));
	}
}
