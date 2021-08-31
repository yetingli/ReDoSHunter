// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/PythonRegex\JavaScriptRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.PythonRegex;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PythonRegexLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Quoted=1, BlockQuoted=2, BellChar=3, ControlChar=4, EscapeChar=5, FormFeed=6, 
		NewLine=7, CarriageReturn=8, Tab=9, Backslash=10, HexChar=11, Dot=12, 
		OneDataUnit=13, DecimalDigit=14, NotDecimalDigit=15, HorizontalWhiteSpace=16, 
		NotHorizontalWhiteSpace=17, NotNewLine=18, CharWithProperty=19, CharWithoutProperty=20, 
		NewLineSequence=21, WhiteSpace=22, NotWhiteSpace=23, VerticalWhiteSpace=24, 
		NotVerticalWhiteSpace=25, WordChar=26, NotWordChar=27, ExtendedUnicodeChar=28, 
		CharacterClassStart=29, CharacterClassEnd=30, Caret=31, Hyphen=32, QuestionMark=33, 
		Plus=34, Star=35, OpenBrace=36, CloseBrace=37, Comma=38, WordBoundary=39, 
		NonWordBoundary=40, StartOfSubject=41, EndOfSubjectOrLine=42, EndOfSubjectOrLineEndOfSubject=43, 
		EndOfSubject=44, PreviousMatchInSubject=45, ResetStartMatch=46, SubroutineOrNamedReferenceStartG=47, 
		NamedReferenceStartK=48, Pipe=49, OpenParen=50, CloseParen=51, LessThan=52, 
		GreaterThan=53, SingleQuote=54, Underscore=55, Colon=56, Hash=57, Equals=58, 
		Exclamation=59, Ampersand=60, ALC=61, BLC=62, CLC=63, DLC=64, ELC=65, 
		FLC=66, GLC=67, HLC=68, ILC=69, JLC=70, KLC=71, LLC=72, MLC=73, NLC=74, 
		OLC=75, PLC=76, QLC=77, RLC=78, SLC=79, TLC=80, ULC=81, VLC=82, WLC=83, 
		XLC=84, YLC=85, ZLC=86, AUC=87, BUC=88, CUC=89, DUC=90, EUC=91, FUC=92, 
		GUC=93, HUC=94, IUC=95, JUC=96, KUC=97, LUC=98, MUC=99, NUC=100, OUC=101, 
		PUC=102, QUC=103, RUC=104, SUC=105, TUC=106, UUC=107, VUC=108, WUC=109, 
		XUC=110, YUC=111, ZUC=112, D1=113, D2=114, D3=115, D4=116, D5=117, D6=118, 
		D7=119, D8=120, D9=121, D0=122, OtherChar=123;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"Quoted", "BlockQuoted", "BellChar", "ControlChar", "EscapeChar", "FormFeed", 
			"NewLine", "CarriageReturn", "Tab", "Backslash", "HexChar", "Dot", "OneDataUnit", 
			"DecimalDigit", "NotDecimalDigit", "HorizontalWhiteSpace", "NotHorizontalWhiteSpace", 
			"NotNewLine", "CharWithProperty", "CharWithoutProperty", "NewLineSequence", 
			"WhiteSpace", "NotWhiteSpace", "VerticalWhiteSpace", "NotVerticalWhiteSpace", 
			"WordChar", "NotWordChar", "ExtendedUnicodeChar", "CharacterClassStart", 
			"CharacterClassEnd", "Caret", "Hyphen", "QuestionMark", "Plus", "Star", 
			"OpenBrace", "CloseBrace", "Comma", "WordBoundary", "NonWordBoundary", 
			"StartOfSubject", "EndOfSubjectOrLine", "EndOfSubjectOrLineEndOfSubject", 
			"EndOfSubject", "PreviousMatchInSubject", "ResetStartMatch", "SubroutineOrNamedReferenceStartG", 
			"NamedReferenceStartK", "Pipe", "OpenParen", "CloseParen", "LessThan", 
			"GreaterThan", "SingleQuote", "Underscore", "Colon", "Hash", "Equals", 
			"Exclamation", "Ampersand", "ALC", "BLC", "CLC", "DLC", "ELC", "FLC", 
			"GLC", "HLC", "ILC", "JLC", "KLC", "LLC", "MLC", "NLC", "OLC", "PLC", 
			"QLC", "RLC", "SLC", "TLC", "ULC", "VLC", "WLC", "XLC", "YLC", "ZLC", 
			"AUC", "BUC", "CUC", "DUC", "EUC", "FUC", "GUC", "HUC", "IUC", "JUC", 
			"KUC", "LUC", "MUC", "NUC", "OUC", "PUC", "QUC", "RUC", "SUC", "TUC", 
			"UUC", "VUC", "WUC", "XUC", "YUC", "ZUC", "D1", "D2", "D3", "D4", "D5", 
			"D6", "D7", "D8", "D9", "D0", "OtherChar", "UnderscoreAlphaNumerics", 
			"AlphaNumerics", "AlphaNumeric", "NonAlphaNumeric", "HexDigit", "ASCII"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'\\a'", null, "'\\e'", "'\\f'", "'\\n'", "'\\r'", 
			"'\\t'", "'\\'", null, "'.'", "'\\C'", "'\\d'", "'\\D'", "'\\h'", "'\\H'", 
			"'\\N'", null, null, "'\\R'", "'\\s'", "'\\S'", "'\\v'", "'\\V'", "'\\w'", 
			"'\\W'", "'\\X'", "'['", "']'", "'^'", "'-'", "'?'", "'+'", "'*'", "'{'", 
			"'}'", "','", "'\\b'", "'\\B'", "'\\A'", "'$'", "'\\Z'", "'\\z'", "'\\G'", 
			"'\\K'", "'\\g'", "'\\k'", "'|'", "'('", "')'", "'<'", "'>'", "'''", 
			"'_'", "':'", "'#'", "'='", "'!'", "'&'", "'a'", "'b'", "'c'", "'d'", 
			"'e'", "'f'", "'g'", "'h'", "'i'", "'j'", "'k'", "'l'", "'m'", "'n'", 
			"'o'", "'p'", "'q'", "'r'", "'s'", "'t'", "'u'", "'v'", "'w'", "'x'", 
			"'y'", "'z'", "'A'", "'B'", "'C'", "'D'", "'E'", "'F'", "'G'", "'H'", 
			"'I'", "'J'", "'K'", "'L'", "'M'", "'N'", "'O'", "'P'", "'Q'", "'R'", 
			"'S'", "'T'", "'U'", "'V'", "'W'", "'X'", "'Y'", "'Z'", "'1'", "'2'", 
			"'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "'0'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Quoted", "BlockQuoted", "BellChar", "ControlChar", "EscapeChar", 
			"FormFeed", "NewLine", "CarriageReturn", "Tab", "Backslash", "HexChar", 
			"Dot", "OneDataUnit", "DecimalDigit", "NotDecimalDigit", "HorizontalWhiteSpace", 
			"NotHorizontalWhiteSpace", "NotNewLine", "CharWithProperty", "CharWithoutProperty", 
			"NewLineSequence", "WhiteSpace", "NotWhiteSpace", "VerticalWhiteSpace", 
			"NotVerticalWhiteSpace", "WordChar", "NotWordChar", "ExtendedUnicodeChar", 
			"CharacterClassStart", "CharacterClassEnd", "Caret", "Hyphen", "QuestionMark", 
			"Plus", "Star", "OpenBrace", "CloseBrace", "Comma", "WordBoundary", "NonWordBoundary", 
			"StartOfSubject", "EndOfSubjectOrLine", "EndOfSubjectOrLineEndOfSubject", 
			"EndOfSubject", "PreviousMatchInSubject", "ResetStartMatch", "SubroutineOrNamedReferenceStartG", 
			"NamedReferenceStartK", "Pipe", "OpenParen", "CloseParen", "LessThan", 
			"GreaterThan", "SingleQuote", "Underscore", "Colon", "Hash", "Equals", 
			"Exclamation", "Ampersand", "ALC", "BLC", "CLC", "DLC", "ELC", "FLC", 
			"GLC", "HLC", "ILC", "JLC", "KLC", "LLC", "MLC", "NLC", "OLC", "PLC", 
			"QLC", "RLC", "SLC", "TLC", "ULC", "VLC", "WLC", "XLC", "YLC", "ZLC", 
			"AUC", "BUC", "CUC", "DUC", "EUC", "FUC", "GUC", "HUC", "IUC", "JUC", 
			"KUC", "LUC", "MUC", "NUC", "OUC", "PUC", "QUC", "RUC", "SUC", "TUC", 
			"UUC", "VUC", "WUC", "XUC", "YUC", "ZUC", "D1", "D2", "D3", "D4", "D5", 
			"D6", "D7", "D8", "D9", "D0", "OtherChar"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public PythonRegexLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JavaScriptRegex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2}\u0253\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080"+
		"\4\u0081\t\u0081\4\u0082\t\u0082\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\u010d"+
		"\n\3\f\3\16\3\u0110\13\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u0138\n\f\r\f\16\f\u0139\3"+
		"\f\3\f\5\f\u013e\n\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3"+
		"\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3"+
		"#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3,\3,"+
		"\3,\3-\3-\3-\3.\3.\3.\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\62\3\62"+
		"\3\63\3\63\3\64\3\64\3\65\3\65\3\66\3\66\3\67\3\67\38\38\39\39\3:\3:\3"+
		";\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3"+
		"F\3G\3G\3H\3H\3I\3I\3J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3O\3O\3P\3P\3Q\3Q\3"+
		"R\3R\3S\3S\3T\3T\3U\3U\3V\3V\3W\3W\3X\3X\3Y\3Y\3Z\3Z\3[\3[\3\\\3\\\3]"+
		"\3]\3^\3^\3_\3_\3`\3`\3a\3a\3b\3b\3c\3c\3d\3d\3e\3e\3f\3f\3g\3g\3h\3h"+
		"\3i\3i\3j\3j\3k\3k\3l\3l\3m\3m\3n\3n\3o\3o\3p\3p\3q\3q\3r\3r\3s\3s\3t"+
		"\3t\3u\3u\3v\3v\3w\3w\3x\3x\3y\3y\3z\3z\3{\3{\3|\3|\3}\3}\6}\u0243\n}"+
		"\r}\16}\u0244\3~\6~\u0248\n~\r~\16~\u0249\3\177\3\177\3\u0080\3\u0080"+
		"\3\u0081\3\u0081\3\u0082\3\u0082\3\u010e\2\u0083\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O"+
		")Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177A\u0081"+
		"B\u0083C\u0085D\u0087E\u0089F\u008bG\u008dH\u008fI\u0091J\u0093K\u0095"+
		"L\u0097M\u0099N\u009bO\u009dP\u009fQ\u00a1R\u00a3S\u00a5T\u00a7U\u00a9"+
		"V\u00abW\u00adX\u00afY\u00b1Z\u00b3[\u00b5\\\u00b7]\u00b9^\u00bb_\u00bd"+
		"`\u00bfa\u00c1b\u00c3c\u00c5d\u00c7e\u00c9f\u00cbg\u00cdh\u00cfi\u00d1"+
		"j\u00d3k\u00d5l\u00d7m\u00d9n\u00dbo\u00ddp\u00dfq\u00e1r\u00e3s\u00e5"+
		"t\u00e7u\u00e9v\u00ebw\u00edx\u00efy\u00f1z\u00f3{\u00f5|\u00f7}\u00f9"+
		"\2\u00fb\2\u00fd\2\u00ff\2\u0101\2\u0103\2\3\2\5\5\2\62;C\\c|\5\2\62;"+
		"CHch\3\2\2\u0081\2\u0252\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3"+
		"\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2"+
		"\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2"+
		"Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3"+
		"\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2"+
		"\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2"+
		"w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2"+
		"\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u008b"+
		"\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091\3\2\2\2\2\u0093\3\2\2"+
		"\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d"+
		"\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2"+
		"\2\2\u00a7\3\2\2\2\2\u00a9\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af"+
		"\3\2\2\2\2\u00b1\3\2\2\2\2\u00b3\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2"+
		"\2\2\u00b9\3\2\2\2\2\u00bb\3\2\2\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1"+
		"\3\2\2\2\2\u00c3\3\2\2\2\2\u00c5\3\2\2\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2"+
		"\2\2\u00cb\3\2\2\2\2\u00cd\3\2\2\2\2\u00cf\3\2\2\2\2\u00d1\3\2\2\2\2\u00d3"+
		"\3\2\2\2\2\u00d5\3\2\2\2\2\u00d7\3\2\2\2\2\u00d9\3\2\2\2\2\u00db\3\2\2"+
		"\2\2\u00dd\3\2\2\2\2\u00df\3\2\2\2\2\u00e1\3\2\2\2\2\u00e3\3\2\2\2\2\u00e5"+
		"\3\2\2\2\2\u00e7\3\2\2\2\2\u00e9\3\2\2\2\2\u00eb\3\2\2\2\2\u00ed\3\2\2"+
		"\2\2\u00ef\3\2\2\2\2\u00f1\3\2\2\2\2\u00f3\3\2\2\2\2\u00f5\3\2\2\2\2\u00f7"+
		"\3\2\2\2\3\u0105\3\2\2\2\5\u0108\3\2\2\2\7\u0114\3\2\2\2\t\u0117\3\2\2"+
		"\2\13\u011c\3\2\2\2\r\u011f\3\2\2\2\17\u0122\3\2\2\2\21\u0125\3\2\2\2"+
		"\23\u0128\3\2\2\2\25\u012b\3\2\2\2\27\u012d\3\2\2\2\31\u013f\3\2\2\2\33"+
		"\u0141\3\2\2\2\35\u0144\3\2\2\2\37\u0147\3\2\2\2!\u014a\3\2\2\2#\u014d"+
		"\3\2\2\2%\u0150\3\2\2\2\'\u0153\3\2\2\2)\u015a\3\2\2\2+\u0161\3\2\2\2"+
		"-\u0164\3\2\2\2/\u0167\3\2\2\2\61\u016a\3\2\2\2\63\u016d\3\2\2\2\65\u0170"+
		"\3\2\2\2\67\u0173\3\2\2\29\u0176\3\2\2\2;\u0179\3\2\2\2=\u017b\3\2\2\2"+
		"?\u017d\3\2\2\2A\u017f\3\2\2\2C\u0181\3\2\2\2E\u0183\3\2\2\2G\u0185\3"+
		"\2\2\2I\u0187\3\2\2\2K\u0189\3\2\2\2M\u018b\3\2\2\2O\u018d\3\2\2\2Q\u0190"+
		"\3\2\2\2S\u0193\3\2\2\2U\u0196\3\2\2\2W\u0198\3\2\2\2Y\u019b\3\2\2\2["+
		"\u019e\3\2\2\2]\u01a1\3\2\2\2_\u01a4\3\2\2\2a\u01a7\3\2\2\2c\u01aa\3\2"+
		"\2\2e\u01ac\3\2\2\2g\u01ae\3\2\2\2i\u01b0\3\2\2\2k\u01b2\3\2\2\2m\u01b4"+
		"\3\2\2\2o\u01b6\3\2\2\2q\u01b8\3\2\2\2s\u01ba\3\2\2\2u\u01bc\3\2\2\2w"+
		"\u01be\3\2\2\2y\u01c0\3\2\2\2{\u01c2\3\2\2\2}\u01c4\3\2\2\2\177\u01c6"+
		"\3\2\2\2\u0081\u01c8\3\2\2\2\u0083\u01ca\3\2\2\2\u0085\u01cc\3\2\2\2\u0087"+
		"\u01ce\3\2\2\2\u0089\u01d0\3\2\2\2\u008b\u01d2\3\2\2\2\u008d\u01d4\3\2"+
		"\2\2\u008f\u01d6\3\2\2\2\u0091\u01d8\3\2\2\2\u0093\u01da\3\2\2\2\u0095"+
		"\u01dc\3\2\2\2\u0097\u01de\3\2\2\2\u0099\u01e0\3\2\2\2\u009b\u01e2\3\2"+
		"\2\2\u009d\u01e4\3\2\2\2\u009f\u01e6\3\2\2\2\u00a1\u01e8\3\2\2\2\u00a3"+
		"\u01ea\3\2\2\2\u00a5\u01ec\3\2\2\2\u00a7\u01ee\3\2\2\2\u00a9\u01f0\3\2"+
		"\2\2\u00ab\u01f2\3\2\2\2\u00ad\u01f4\3\2\2\2\u00af\u01f6\3\2\2\2\u00b1"+
		"\u01f8\3\2\2\2\u00b3\u01fa\3\2\2\2\u00b5\u01fc\3\2\2\2\u00b7\u01fe\3\2"+
		"\2\2\u00b9\u0200\3\2\2\2\u00bb\u0202\3\2\2\2\u00bd\u0204\3\2\2\2\u00bf"+
		"\u0206\3\2\2\2\u00c1\u0208\3\2\2\2\u00c3\u020a\3\2\2\2\u00c5\u020c\3\2"+
		"\2\2\u00c7\u020e\3\2\2\2\u00c9\u0210\3\2\2\2\u00cb\u0212\3\2\2\2\u00cd"+
		"\u0214\3\2\2\2\u00cf\u0216\3\2\2\2\u00d1\u0218\3\2\2\2\u00d3\u021a\3\2"+
		"\2\2\u00d5\u021c\3\2\2\2\u00d7\u021e\3\2\2\2\u00d9\u0220\3\2\2\2\u00db"+
		"\u0222\3\2\2\2\u00dd\u0224\3\2\2\2\u00df\u0226\3\2\2\2\u00e1\u0228\3\2"+
		"\2\2\u00e3\u022a\3\2\2\2\u00e5\u022c\3\2\2\2\u00e7\u022e\3\2\2\2\u00e9"+
		"\u0230\3\2\2\2\u00eb\u0232\3\2\2\2\u00ed\u0234\3\2\2\2\u00ef\u0236\3\2"+
		"\2\2\u00f1\u0238\3\2\2\2\u00f3\u023a\3\2\2\2\u00f5\u023c\3\2\2\2\u00f7"+
		"\u023e\3\2\2\2\u00f9\u0242\3\2\2\2\u00fb\u0247\3\2\2\2\u00fd\u024b\3\2"+
		"\2\2\u00ff\u024d\3\2\2\2\u0101\u024f\3\2\2\2\u0103\u0251\3\2\2\2\u0105"+
		"\u0106\7^\2\2\u0106\u0107\5\u00ff\u0080\2\u0107\4\3\2\2\2\u0108\u0109"+
		"\7^\2\2\u0109\u010a\7S\2\2\u010a\u010e\3\2\2\2\u010b\u010d\13\2\2\2\u010c"+
		"\u010b\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010f\3\2\2\2\u010e\u010c\3\2"+
		"\2\2\u010f\u0111\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0112\7^\2\2\u0112"+
		"\u0113\7G\2\2\u0113\6\3\2\2\2\u0114\u0115\7^\2\2\u0115\u0116\7c\2\2\u0116"+
		"\b\3\2\2\2\u0117\u0118\7^\2\2\u0118\u0119\7e\2\2\u0119\u011a\3\2\2\2\u011a"+
		"\u011b\5\u0103\u0082\2\u011b\n\3\2\2\2\u011c\u011d\7^\2\2\u011d\u011e"+
		"\7g\2\2\u011e\f\3\2\2\2\u011f\u0120\7^\2\2\u0120\u0121\7h\2\2\u0121\16"+
		"\3\2\2\2\u0122\u0123\7^\2\2\u0123\u0124\7p\2\2\u0124\20\3\2\2\2\u0125"+
		"\u0126\7^\2\2\u0126\u0127\7t\2\2\u0127\22\3\2\2\2\u0128\u0129\7^\2\2\u0129"+
		"\u012a\7v\2\2\u012a\24\3\2\2\2\u012b\u012c\7^\2\2\u012c\26\3\2\2\2\u012d"+
		"\u012e\7^\2\2\u012e\u012f\7z\2\2\u012f\u013d\3\2\2\2\u0130\u0131\5\u0101"+
		"\u0081\2\u0131\u0132\5\u0101\u0081\2\u0132\u013e\3\2\2\2\u0133\u0134\7"+
		"}\2\2\u0134\u0135\5\u0101\u0081\2\u0135\u0137\5\u0101\u0081\2\u0136\u0138"+
		"\5\u0101\u0081\2\u0137\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u0137\3"+
		"\2\2\2\u0139\u013a\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\7\177\2\2\u013c"+
		"\u013e\3\2\2\2\u013d\u0130\3\2\2\2\u013d\u0133\3\2\2\2\u013e\30\3\2\2"+
		"\2\u013f\u0140\7\60\2\2\u0140\32\3\2\2\2\u0141\u0142\7^\2\2\u0142\u0143"+
		"\7E\2\2\u0143\34\3\2\2\2\u0144\u0145\7^\2\2\u0145\u0146\7f\2\2\u0146\36"+
		"\3\2\2\2\u0147\u0148\7^\2\2\u0148\u0149\7F\2\2\u0149 \3\2\2\2\u014a\u014b"+
		"\7^\2\2\u014b\u014c\7j\2\2\u014c\"\3\2\2\2\u014d\u014e\7^\2\2\u014e\u014f"+
		"\7J\2\2\u014f$\3\2\2\2\u0150\u0151\7^\2\2\u0151\u0152\7P\2\2\u0152&\3"+
		"\2\2\2\u0153\u0154\7^\2\2\u0154\u0155\7r\2\2\u0155\u0156\7}\2\2\u0156"+
		"\u0157\3\2\2\2\u0157\u0158\5\u00f9}\2\u0158\u0159\7\177\2\2\u0159(\3\2"+
		"\2\2\u015a\u015b\7^\2\2\u015b\u015c\7R\2\2\u015c\u015d\7}\2\2\u015d\u015e"+
		"\3\2\2\2\u015e\u015f\5\u00f9}\2\u015f\u0160\7\177\2\2\u0160*\3\2\2\2\u0161"+
		"\u0162\7^\2\2\u0162\u0163\7T\2\2\u0163,\3\2\2\2\u0164\u0165\7^\2\2\u0165"+
		"\u0166\7u\2\2\u0166.\3\2\2\2\u0167\u0168\7^\2\2\u0168\u0169\7U\2\2\u0169"+
		"\60\3\2\2\2\u016a\u016b\7^\2\2\u016b\u016c\7x\2\2\u016c\62\3\2\2\2\u016d"+
		"\u016e\7^\2\2\u016e\u016f\7X\2\2\u016f\64\3\2\2\2\u0170\u0171\7^\2\2\u0171"+
		"\u0172\7y\2\2\u0172\66\3\2\2\2\u0173\u0174\7^\2\2\u0174\u0175\7Y\2\2\u0175"+
		"8\3\2\2\2\u0176\u0177\7^\2\2\u0177\u0178\7Z\2\2\u0178:\3\2\2\2\u0179\u017a"+
		"\7]\2\2\u017a<\3\2\2\2\u017b\u017c\7_\2\2\u017c>\3\2\2\2\u017d\u017e\7"+
		"`\2\2\u017e@\3\2\2\2\u017f\u0180\7/\2\2\u0180B\3\2\2\2\u0181\u0182\7A"+
		"\2\2\u0182D\3\2\2\2\u0183\u0184\7-\2\2\u0184F\3\2\2\2\u0185\u0186\7,\2"+
		"\2\u0186H\3\2\2\2\u0187\u0188\7}\2\2\u0188J\3\2\2\2\u0189\u018a\7\177"+
		"\2\2\u018aL\3\2\2\2\u018b\u018c\7.\2\2\u018cN\3\2\2\2\u018d\u018e\7^\2"+
		"\2\u018e\u018f\7d\2\2\u018fP\3\2\2\2\u0190\u0191\7^\2\2\u0191\u0192\7"+
		"D\2\2\u0192R\3\2\2\2\u0193\u0194\7^\2\2\u0194\u0195\7C\2\2\u0195T\3\2"+
		"\2\2\u0196\u0197\7&\2\2\u0197V\3\2\2\2\u0198\u0199\7^\2\2\u0199\u019a"+
		"\7\\\2\2\u019aX\3\2\2\2\u019b\u019c\7^\2\2\u019c\u019d\7|\2\2\u019dZ\3"+
		"\2\2\2\u019e\u019f\7^\2\2\u019f\u01a0\7I\2\2\u01a0\\\3\2\2\2\u01a1\u01a2"+
		"\7^\2\2\u01a2\u01a3\7M\2\2\u01a3^\3\2\2\2\u01a4\u01a5\7^\2\2\u01a5\u01a6"+
		"\7i\2\2\u01a6`\3\2\2\2\u01a7\u01a8\7^\2\2\u01a8\u01a9\7m\2\2\u01a9b\3"+
		"\2\2\2\u01aa\u01ab\7~\2\2\u01abd\3\2\2\2\u01ac\u01ad\7*\2\2\u01adf\3\2"+
		"\2\2\u01ae\u01af\7+\2\2\u01afh\3\2\2\2\u01b0\u01b1\7>\2\2\u01b1j\3\2\2"+
		"\2\u01b2\u01b3\7@\2\2\u01b3l\3\2\2\2\u01b4\u01b5\7)\2\2\u01b5n\3\2\2\2"+
		"\u01b6\u01b7\7a\2\2\u01b7p\3\2\2\2\u01b8\u01b9\7<\2\2\u01b9r\3\2\2\2\u01ba"+
		"\u01bb\7%\2\2\u01bbt\3\2\2\2\u01bc\u01bd\7?\2\2\u01bdv\3\2\2\2\u01be\u01bf"+
		"\7#\2\2\u01bfx\3\2\2\2\u01c0\u01c1\7(\2\2\u01c1z\3\2\2\2\u01c2\u01c3\7"+
		"c\2\2\u01c3|\3\2\2\2\u01c4\u01c5\7d\2\2\u01c5~\3\2\2\2\u01c6\u01c7\7e"+
		"\2\2\u01c7\u0080\3\2\2\2\u01c8\u01c9\7f\2\2\u01c9\u0082\3\2\2\2\u01ca"+
		"\u01cb\7g\2\2\u01cb\u0084\3\2\2\2\u01cc\u01cd\7h\2\2\u01cd\u0086\3\2\2"+
		"\2\u01ce\u01cf\7i\2\2\u01cf\u0088\3\2\2\2\u01d0\u01d1\7j\2\2\u01d1\u008a"+
		"\3\2\2\2\u01d2\u01d3\7k\2\2\u01d3\u008c\3\2\2\2\u01d4\u01d5\7l\2\2\u01d5"+
		"\u008e\3\2\2\2\u01d6\u01d7\7m\2\2\u01d7\u0090\3\2\2\2\u01d8\u01d9\7n\2"+
		"\2\u01d9\u0092\3\2\2\2\u01da\u01db\7o\2\2\u01db\u0094\3\2\2\2\u01dc\u01dd"+
		"\7p\2\2\u01dd\u0096\3\2\2\2\u01de\u01df\7q\2\2\u01df\u0098\3\2\2\2\u01e0"+
		"\u01e1\7r\2\2\u01e1\u009a\3\2\2\2\u01e2\u01e3\7s\2\2\u01e3\u009c\3\2\2"+
		"\2\u01e4\u01e5\7t\2\2\u01e5\u009e\3\2\2\2\u01e6\u01e7\7u\2\2\u01e7\u00a0"+
		"\3\2\2\2\u01e8\u01e9\7v\2\2\u01e9\u00a2\3\2\2\2\u01ea\u01eb\7w\2\2\u01eb"+
		"\u00a4\3\2\2\2\u01ec\u01ed\7x\2\2\u01ed\u00a6\3\2\2\2\u01ee\u01ef\7y\2"+
		"\2\u01ef\u00a8\3\2\2\2\u01f0\u01f1\7z\2\2\u01f1\u00aa\3\2\2\2\u01f2\u01f3"+
		"\7{\2\2\u01f3\u00ac\3\2\2\2\u01f4\u01f5\7|\2\2\u01f5\u00ae\3\2\2\2\u01f6"+
		"\u01f7\7C\2\2\u01f7\u00b0\3\2\2\2\u01f8\u01f9\7D\2\2\u01f9\u00b2\3\2\2"+
		"\2\u01fa\u01fb\7E\2\2\u01fb\u00b4\3\2\2\2\u01fc\u01fd\7F\2\2\u01fd\u00b6"+
		"\3\2\2\2\u01fe\u01ff\7G\2\2\u01ff\u00b8\3\2\2\2\u0200\u0201\7H\2\2\u0201"+
		"\u00ba\3\2\2\2\u0202\u0203\7I\2\2\u0203\u00bc\3\2\2\2\u0204\u0205\7J\2"+
		"\2\u0205\u00be\3\2\2\2\u0206\u0207\7K\2\2\u0207\u00c0\3\2\2\2\u0208\u0209"+
		"\7L\2\2\u0209\u00c2\3\2\2\2\u020a\u020b\7M\2\2\u020b\u00c4\3\2\2\2\u020c"+
		"\u020d\7N\2\2\u020d\u00c6\3\2\2\2\u020e\u020f\7O\2\2\u020f\u00c8\3\2\2"+
		"\2\u0210\u0211\7P\2\2\u0211\u00ca\3\2\2\2\u0212\u0213\7Q\2\2\u0213\u00cc"+
		"\3\2\2\2\u0214\u0215\7R\2\2\u0215\u00ce\3\2\2\2\u0216\u0217\7S\2\2\u0217"+
		"\u00d0\3\2\2\2\u0218\u0219\7T\2\2\u0219\u00d2\3\2\2\2\u021a\u021b\7U\2"+
		"\2\u021b\u00d4\3\2\2\2\u021c\u021d\7V\2\2\u021d\u00d6\3\2\2\2\u021e\u021f"+
		"\7W\2\2\u021f\u00d8\3\2\2\2\u0220\u0221\7X\2\2\u0221\u00da\3\2\2\2\u0222"+
		"\u0223\7Y\2\2\u0223\u00dc\3\2\2\2\u0224\u0225\7Z\2\2\u0225\u00de\3\2\2"+
		"\2\u0226\u0227\7[\2\2\u0227\u00e0\3\2\2\2\u0228\u0229\7\\\2\2\u0229\u00e2"+
		"\3\2\2\2\u022a\u022b\7\63\2\2\u022b\u00e4\3\2\2\2\u022c\u022d\7\64\2\2"+
		"\u022d\u00e6\3\2\2\2\u022e\u022f\7\65\2\2\u022f\u00e8\3\2\2\2\u0230\u0231"+
		"\7\66\2\2\u0231\u00ea\3\2\2\2\u0232\u0233\7\67\2\2\u0233\u00ec\3\2\2\2"+
		"\u0234\u0235\78\2\2\u0235\u00ee\3\2\2\2\u0236\u0237\79\2\2\u0237\u00f0"+
		"\3\2\2\2\u0238\u0239\7:\2\2\u0239\u00f2\3\2\2\2\u023a\u023b\7;\2\2\u023b"+
		"\u00f4\3\2\2\2\u023c\u023d\7\62\2\2\u023d\u00f6\3\2\2\2\u023e\u023f\13"+
		"\2\2\2\u023f\u00f8\3\2\2\2\u0240\u0243\7a\2\2\u0241\u0243\5\u00fd\177"+
		"\2\u0242\u0240\3\2\2\2\u0242\u0241\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0242"+
		"\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u00fa\3\2\2\2\u0246\u0248\5\u00fd\177"+
		"\2\u0247\u0246\3\2\2\2\u0248\u0249\3\2\2\2\u0249\u0247\3\2\2\2\u0249\u024a"+
		"\3\2\2\2\u024a\u00fc\3\2\2\2\u024b\u024c\t\2\2\2\u024c\u00fe\3\2\2\2\u024d"+
		"\u024e\n\2\2\2\u024e\u0100\3\2\2\2\u024f\u0250\t\3\2\2\u0250\u0102\3\2"+
		"\2\2\u0251\u0252\t\4\2\2\u0252\u0104\3\2\2\2\t\2\u010e\u0139\u013d\u0242"+
		"\u0244\u0249\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}