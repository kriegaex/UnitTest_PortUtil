package de.scrum_master.util;

public class SearchEngineImpl {
	public static final String ESCAPE_EXCLAMATION = "wqrexycta";
	public static final String ESCAPE_FUZZY = "wqrexyctb";
	public static final String ESCAPE_OR_TRIMMED = "wqrexyctc";
	public static final String ESCAPE_OR = " " + ESCAPE_OR_TRIMMED + " ";
	public static final String ESCAPE_WILDCARD1 = "wqrexyctd";
	public static final String ESCAPE_WILDCARD2 = "wqrexycte";

	public static String getEscapedQuery(String query) {
		if (query == null) {
			return query;
		}
		if (query.startsWith("*") || query.startsWith("?")) {
			query = query.substring(1);
		}
		query = query.replace("!", ESCAPE_EXCLAMATION);
		query = query.replace("+", ESCAPE_FUZZY);
		query = query.replace("*", ESCAPE_WILDCARD1);
		query = query.replace("?", ESCAPE_WILDCARD2);
		query = query.replace(" OR ", ESCAPE_OR);
		query = query.replace(" or ", ESCAPE_OR);
		return query;
	}

	public static String getUnEscapedQuery(String query) {
		if (query == null) {
			return query;
		}
		query = query.replace(ESCAPE_EXCLAMATION, "!");
		query = query.replace(ESCAPE_FUZZY, "+");
		query = query.replace(ESCAPE_WILDCARD1, "*");
		query = query.replace(ESCAPE_WILDCARD2, "?");
		return query;
	}
}
