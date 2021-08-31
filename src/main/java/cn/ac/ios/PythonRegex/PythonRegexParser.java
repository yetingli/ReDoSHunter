// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/PythonRegex\JavaScriptRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.PythonRegex;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PythonRegexParser extends Parser {
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
	public static final int
		RULE_parse = 0, RULE_alternation = 1, RULE_expr = 2, RULE_element = 3, 
		RULE_quantifier = 4, RULE_quantifier_type = 5, RULE_character_class = 6, 
		RULE_backreference = 7, RULE_backreference_or_octal = 8, RULE_capture = 9, 
		RULE_non_capture = 10, RULE_comment = 11, RULE_option = 12, RULE_option_flags = 13, 
		RULE_option_flag = 14, RULE_look_around = 15, RULE_subroutine_reference = 16, 
		RULE_conditional = 17, RULE_backtrack_control = 18, RULE_newline_convention = 19, 
		RULE_callout = 20, RULE_atom = 21, RULE_cc_atom = 22, RULE_shared_atom = 23, 
		RULE_literal = 24, RULE_cc_literal = 25, RULE_shared_literal = 26, RULE_number = 27, 
		RULE_octal_char = 28, RULE_octal_digit = 29, RULE_digits = 30, RULE_digit = 31, 
		RULE_name = 32, RULE_alpha_nums = 33, RULE_non_close_parens = 34, RULE_non_close_paren = 35, 
		RULE_letter = 36;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "alternation", "expr", "element", "quantifier", "quantifier_type", 
			"character_class", "backreference", "backreference_or_octal", "capture", 
			"non_capture", "comment", "option", "option_flags", "option_flag", "look_around", 
			"subroutine_reference", "conditional", "backtrack_control", "newline_convention", 
			"callout", "atom", "cc_atom", "shared_atom", "literal", "cc_literal", 
			"shared_literal", "number", "octal_char", "octal_digit", "digits", "digit", 
			"name", "alpha_nums", "non_close_parens", "non_close_paren", "letter"
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

	@Override
	public String getGrammarFileName() { return "JavaScriptRegex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PythonRegexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PythonRegexParser.EOF, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			alternation();
			setState(75);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlternationContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> Pipe() { return getTokens(PythonRegexParser.Pipe); }
		public TerminalNode Pipe(int i) {
			return getToken(PythonRegexParser.Pipe, i);
		}
		public AlternationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterAlternation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitAlternation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitAlternation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlternationContext alternation() throws RecognitionException {
		AlternationContext _localctx = new AlternationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_alternation);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			expr();
			setState(82);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(78);
					match(Pipe);
					setState(79);
					expr();
					}
					} 
				}
				setState(84);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << OneDataUnit) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << ExtendedUnicodeChar) | (1L << CharacterClassStart) | (1L << CharacterClassEnd) | (1L << Caret) | (1L << Hyphen) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << NonWordBoundary) | (1L << StartOfSubject) | (1L << EndOfSubjectOrLine) | (1L << EndOfSubjectOrLineEndOfSubject) | (1L << EndOfSubject) | (1L << PreviousMatchInSubject) | (1L << ResetStartMatch) | (1L << SubroutineOrNamedReferenceStartG) | (1L << NamedReferenceStartK) | (1L << OpenParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0)) {
				{
				{
				setState(85);
				element();
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public QuantifierContext quantifier() {
			return getRuleContext(QuantifierContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			atom();
			setState(93);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(92);
				quantifier();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuantifierContext extends ParserRuleContext {
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public Quantifier_typeContext quantifier_type() {
			return getRuleContext(Quantifier_typeContext.class,0);
		}
		public TerminalNode Plus() { return getToken(PythonRegexParser.Plus, 0); }
		public TerminalNode Star() { return getToken(PythonRegexParser.Star, 0); }
		public TerminalNode OpenBrace() { return getToken(PythonRegexParser.OpenBrace, 0); }
		public TerminalNode Comma() { return getToken(PythonRegexParser.Comma, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(PythonRegexParser.CloseBrace, 0); }
		public QuantifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterQuantifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitQuantifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitQuantifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuantifierContext quantifier() throws RecognitionException {
		QuantifierContext _localctx = new QuantifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_quantifier);
		try {
			setState(125);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				match(QuestionMark);
				setState(96);
				quantifier_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(97);
				match(Plus);
				setState(98);
				quantifier_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(99);
				match(Star);
				setState(100);
				quantifier_type();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(101);
				match(OpenBrace);
				setState(102);
				match(Comma);
				setState(103);
				number();
				setState(104);
				match(CloseBrace);
				setState(105);
				quantifier_type();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(107);
				match(OpenBrace);
				setState(108);
				number();
				setState(109);
				match(CloseBrace);
				setState(110);
				quantifier_type();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(112);
				match(OpenBrace);
				setState(113);
				number();
				setState(114);
				match(Comma);
				setState(115);
				match(CloseBrace);
				setState(116);
				quantifier_type();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(118);
				match(OpenBrace);
				setState(119);
				number();
				setState(120);
				match(Comma);
				setState(121);
				number();
				setState(122);
				match(CloseBrace);
				setState(123);
				quantifier_type();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Quantifier_typeContext extends ParserRuleContext {
		public TerminalNode Plus() { return getToken(PythonRegexParser.Plus, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public Quantifier_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterQuantifier_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitQuantifier_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitQuantifier_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quantifier_typeContext quantifier_type() throws RecognitionException {
		Quantifier_typeContext _localctx = new Quantifier_typeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_quantifier_type);
		try {
			setState(130);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Plus:
				enterOuterAlt(_localctx, 1);
				{
				setState(127);
				match(Plus);
				}
				break;
			case QuestionMark:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				match(QuestionMark);
				}
				break;
			case EOF:
			case Quoted:
			case BlockQuoted:
			case BellChar:
			case ControlChar:
			case EscapeChar:
			case FormFeed:
			case NewLine:
			case CarriageReturn:
			case Tab:
			case Backslash:
			case HexChar:
			case Dot:
			case OneDataUnit:
			case DecimalDigit:
			case NotDecimalDigit:
			case HorizontalWhiteSpace:
			case NotHorizontalWhiteSpace:
			case NotNewLine:
			case CharWithProperty:
			case CharWithoutProperty:
			case NewLineSequence:
			case WhiteSpace:
			case NotWhiteSpace:
			case VerticalWhiteSpace:
			case NotVerticalWhiteSpace:
			case WordChar:
			case NotWordChar:
			case ExtendedUnicodeChar:
			case CharacterClassStart:
			case CharacterClassEnd:
			case Caret:
			case Hyphen:
			case OpenBrace:
			case CloseBrace:
			case Comma:
			case WordBoundary:
			case NonWordBoundary:
			case StartOfSubject:
			case EndOfSubjectOrLine:
			case EndOfSubjectOrLineEndOfSubject:
			case EndOfSubject:
			case PreviousMatchInSubject:
			case ResetStartMatch:
			case SubroutineOrNamedReferenceStartG:
			case NamedReferenceStartK:
			case Pipe:
			case OpenParen:
			case CloseParen:
			case LessThan:
			case GreaterThan:
			case SingleQuote:
			case Underscore:
			case Colon:
			case Hash:
			case Equals:
			case Exclamation:
			case Ampersand:
			case ALC:
			case BLC:
			case CLC:
			case DLC:
			case ELC:
			case FLC:
			case GLC:
			case HLC:
			case ILC:
			case JLC:
			case KLC:
			case LLC:
			case MLC:
			case NLC:
			case OLC:
			case PLC:
			case QLC:
			case RLC:
			case SLC:
			case TLC:
			case ULC:
			case VLC:
			case WLC:
			case XLC:
			case YLC:
			case ZLC:
			case AUC:
			case BUC:
			case CUC:
			case DUC:
			case EUC:
			case FUC:
			case GUC:
			case HUC:
			case IUC:
			case JUC:
			case KUC:
			case LUC:
			case MUC:
			case NUC:
			case OUC:
			case PUC:
			case QUC:
			case RUC:
			case SUC:
			case TUC:
			case UUC:
			case VUC:
			case WUC:
			case XUC:
			case YUC:
			case ZUC:
			case D1:
			case D2:
			case D3:
			case D4:
			case D5:
			case D6:
			case D7:
			case D8:
			case D9:
			case D0:
			case OtherChar:
				enterOuterAlt(_localctx, 3);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Character_classContext extends ParserRuleContext {
		public TerminalNode CharacterClassStart() { return getToken(PythonRegexParser.CharacterClassStart, 0); }
		public TerminalNode Caret() { return getToken(PythonRegexParser.Caret, 0); }
		public List<TerminalNode> CharacterClassEnd() { return getTokens(PythonRegexParser.CharacterClassEnd); }
		public TerminalNode CharacterClassEnd(int i) {
			return getToken(PythonRegexParser.CharacterClassEnd, i);
		}
		public TerminalNode Hyphen() { return getToken(PythonRegexParser.Hyphen, 0); }
		public List<Cc_atomContext> cc_atom() {
			return getRuleContexts(Cc_atomContext.class);
		}
		public Cc_atomContext cc_atom(int i) {
			return getRuleContext(Cc_atomContext.class,i);
		}
		public Character_classContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterCharacter_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitCharacter_class(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitCharacter_class(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Character_classContext character_class() throws RecognitionException {
		Character_classContext _localctx = new Character_classContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_character_class);
		int _la;
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				match(CharacterClassStart);
				setState(133);
				match(Caret);
				setState(134);
				match(CharacterClassEnd);
				setState(135);
				match(Hyphen);
				setState(137); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(136);
					cc_atom();
					}
					}
					setState(139); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(141);
				match(CharacterClassEnd);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				match(CharacterClassStart);
				setState(144);
				match(Caret);
				setState(145);
				match(CharacterClassEnd);
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0)) {
					{
					{
					setState(146);
					cc_atom();
					}
					}
					setState(151);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(152);
				match(CharacterClassEnd);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				match(CharacterClassStart);
				setState(154);
				match(Caret);
				setState(156); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(155);
					cc_atom();
					}
					}
					setState(158); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(160);
				match(CharacterClassEnd);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(162);
				match(CharacterClassStart);
				setState(163);
				match(CharacterClassEnd);
				setState(164);
				match(Hyphen);
				setState(166); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(165);
					cc_atom();
					}
					}
					setState(168); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(170);
				match(CharacterClassEnd);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(172);
				match(CharacterClassStart);
				setState(173);
				match(CharacterClassEnd);
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0)) {
					{
					{
					setState(174);
					cc_atom();
					}
					}
					setState(179);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(180);
				match(CharacterClassEnd);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(181);
				match(CharacterClassStart);
				setState(183); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(182);
					cc_atom();
					}
					}
					setState(185); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(187);
				match(CharacterClassEnd);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BackreferenceContext extends ParserRuleContext {
		public Backreference_or_octalContext backreference_or_octal() {
			return getRuleContext(Backreference_or_octalContext.class,0);
		}
		public TerminalNode SubroutineOrNamedReferenceStartG() { return getToken(PythonRegexParser.SubroutineOrNamedReferenceStartG, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(PythonRegexParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(PythonRegexParser.CloseBrace, 0); }
		public TerminalNode Hyphen() { return getToken(PythonRegexParser.Hyphen, 0); }
		public TerminalNode NamedReferenceStartK() { return getToken(PythonRegexParser.NamedReferenceStartK, 0); }
		public TerminalNode LessThan() { return getToken(PythonRegexParser.LessThan, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode GreaterThan() { return getToken(PythonRegexParser.GreaterThan, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(PythonRegexParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(PythonRegexParser.SingleQuote, i);
		}
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public TerminalNode PUC() { return getToken(PythonRegexParser.PUC, 0); }
		public TerminalNode Equals() { return getToken(PythonRegexParser.Equals, 0); }
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public BackreferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backreference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterBackreference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitBackreference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitBackreference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BackreferenceContext backreference() throws RecognitionException {
		BackreferenceContext _localctx = new BackreferenceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_backreference);
		try {
			setState(232);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				backreference_or_octal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(192);
				match(SubroutineOrNamedReferenceStartG);
				setState(193);
				number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				match(SubroutineOrNamedReferenceStartG);
				setState(195);
				match(OpenBrace);
				setState(196);
				number();
				setState(197);
				match(CloseBrace);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(199);
				match(SubroutineOrNamedReferenceStartG);
				setState(200);
				match(OpenBrace);
				setState(201);
				match(Hyphen);
				setState(202);
				number();
				setState(203);
				match(CloseBrace);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(205);
				match(NamedReferenceStartK);
				setState(206);
				match(LessThan);
				setState(207);
				name();
				setState(208);
				match(GreaterThan);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(210);
				match(NamedReferenceStartK);
				setState(211);
				match(SingleQuote);
				setState(212);
				name();
				setState(213);
				match(SingleQuote);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(215);
				match(SubroutineOrNamedReferenceStartG);
				setState(216);
				match(OpenBrace);
				setState(217);
				name();
				setState(218);
				match(CloseBrace);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(220);
				match(NamedReferenceStartK);
				setState(221);
				match(OpenBrace);
				setState(222);
				name();
				setState(223);
				match(CloseBrace);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(225);
				match(OpenParen);
				setState(226);
				match(QuestionMark);
				setState(227);
				match(PUC);
				setState(228);
				match(Equals);
				setState(229);
				name();
				setState(230);
				match(CloseParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Backreference_or_octalContext extends ParserRuleContext {
		public Octal_charContext octal_char() {
			return getRuleContext(Octal_charContext.class,0);
		}
		public TerminalNode Backslash() { return getToken(PythonRegexParser.Backslash, 0); }
		public DigitContext digit() {
			return getRuleContext(DigitContext.class,0);
		}
		public Backreference_or_octalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backreference_or_octal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterBackreference_or_octal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitBackreference_or_octal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitBackreference_or_octal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Backreference_or_octalContext backreference_or_octal() throws RecognitionException {
		Backreference_or_octalContext _localctx = new Backreference_or_octalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_backreference_or_octal);
		try {
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				octal_char();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(235);
				match(Backslash);
				setState(236);
				digit();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaptureContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public TerminalNode LessThan() { return getToken(PythonRegexParser.LessThan, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode GreaterThan() { return getToken(PythonRegexParser.GreaterThan, 0); }
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(PythonRegexParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(PythonRegexParser.SingleQuote, i);
		}
		public TerminalNode PUC() { return getToken(PythonRegexParser.PUC, 0); }
		public CaptureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_capture; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterCapture(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitCapture(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitCapture(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaptureContext capture() throws RecognitionException {
		CaptureContext _localctx = new CaptureContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_capture);
		try {
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(OpenParen);
				setState(240);
				match(QuestionMark);
				setState(241);
				match(LessThan);
				setState(242);
				name();
				setState(243);
				match(GreaterThan);
				setState(244);
				alternation();
				setState(245);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				match(OpenParen);
				setState(248);
				match(QuestionMark);
				setState(249);
				match(SingleQuote);
				setState(250);
				name();
				setState(251);
				match(SingleQuote);
				setState(252);
				alternation();
				setState(253);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(255);
				match(OpenParen);
				setState(256);
				match(QuestionMark);
				setState(257);
				match(PUC);
				setState(258);
				match(LessThan);
				setState(259);
				name();
				setState(260);
				match(GreaterThan);
				setState(261);
				alternation();
				setState(262);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(264);
				match(OpenParen);
				setState(265);
				alternation();
				setState(266);
				match(CloseParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Non_captureContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public TerminalNode Colon() { return getToken(PythonRegexParser.Colon, 0); }
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public TerminalNode Pipe() { return getToken(PythonRegexParser.Pipe, 0); }
		public TerminalNode GreaterThan() { return getToken(PythonRegexParser.GreaterThan, 0); }
		public Non_captureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_capture; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterNon_capture(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitNon_capture(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitNon_capture(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_captureContext non_capture() throws RecognitionException {
		Non_captureContext _localctx = new Non_captureContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_non_capture);
		try {
			setState(288);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				match(OpenParen);
				setState(271);
				match(QuestionMark);
				setState(272);
				match(Colon);
				setState(273);
				alternation();
				setState(274);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				match(OpenParen);
				setState(277);
				match(QuestionMark);
				setState(278);
				match(Pipe);
				setState(279);
				alternation();
				setState(280);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(282);
				match(OpenParen);
				setState(283);
				match(QuestionMark);
				setState(284);
				match(GreaterThan);
				setState(285);
				alternation();
				setState(286);
				match(CloseParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public TerminalNode Hash() { return getToken(PythonRegexParser.Hash, 0); }
		public Non_close_parensContext non_close_parens() {
			return getRuleContext(Non_close_parensContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(OpenParen);
			setState(291);
			match(QuestionMark);
			setState(292);
			match(Hash);
			setState(293);
			non_close_parens();
			setState(294);
			match(CloseParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public List<Option_flagsContext> option_flags() {
			return getRuleContexts(Option_flagsContext.class);
		}
		public Option_flagsContext option_flags(int i) {
			return getRuleContext(Option_flagsContext.class,i);
		}
		public TerminalNode Hyphen() { return getToken(PythonRegexParser.Hyphen, 0); }
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public TerminalNode Star() { return getToken(PythonRegexParser.Star, 0); }
		public TerminalNode NUC() { return getToken(PythonRegexParser.NUC, 0); }
		public List<TerminalNode> OUC() { return getTokens(PythonRegexParser.OUC); }
		public TerminalNode OUC(int i) {
			return getToken(PythonRegexParser.OUC, i);
		}
		public List<TerminalNode> Underscore() { return getTokens(PythonRegexParser.Underscore); }
		public TerminalNode Underscore(int i) {
			return getToken(PythonRegexParser.Underscore, i);
		}
		public TerminalNode SUC() { return getToken(PythonRegexParser.SUC, 0); }
		public List<TerminalNode> TUC() { return getTokens(PythonRegexParser.TUC); }
		public TerminalNode TUC(int i) {
			return getToken(PythonRegexParser.TUC, i);
		}
		public TerminalNode AUC() { return getToken(PythonRegexParser.AUC, 0); }
		public TerminalNode RUC() { return getToken(PythonRegexParser.RUC, 0); }
		public TerminalNode PUC() { return getToken(PythonRegexParser.PUC, 0); }
		public TerminalNode UUC() { return getToken(PythonRegexParser.UUC, 0); }
		public TerminalNode FUC() { return getToken(PythonRegexParser.FUC, 0); }
		public TerminalNode D8() { return getToken(PythonRegexParser.D8, 0); }
		public TerminalNode D1() { return getToken(PythonRegexParser.D1, 0); }
		public TerminalNode D6() { return getToken(PythonRegexParser.D6, 0); }
		public TerminalNode CUC() { return getToken(PythonRegexParser.CUC, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_option);
		try {
			setState(350);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(296);
				match(OpenParen);
				setState(297);
				match(QuestionMark);
				setState(298);
				option_flags();
				setState(299);
				match(Hyphen);
				setState(300);
				option_flags();
				setState(301);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(303);
				match(OpenParen);
				setState(304);
				match(QuestionMark);
				setState(305);
				option_flags();
				setState(306);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(308);
				match(OpenParen);
				setState(309);
				match(QuestionMark);
				setState(310);
				match(Hyphen);
				setState(311);
				option_flags();
				setState(312);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(314);
				match(OpenParen);
				setState(315);
				match(Star);
				setState(316);
				match(NUC);
				setState(317);
				match(OUC);
				setState(318);
				match(Underscore);
				setState(319);
				match(SUC);
				setState(320);
				match(TUC);
				setState(321);
				match(AUC);
				setState(322);
				match(RUC);
				setState(323);
				match(TUC);
				setState(324);
				match(Underscore);
				setState(325);
				match(OUC);
				setState(326);
				match(PUC);
				setState(327);
				match(TUC);
				setState(328);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(329);
				match(OpenParen);
				setState(330);
				match(Star);
				setState(331);
				match(UUC);
				setState(332);
				match(TUC);
				setState(333);
				match(FUC);
				setState(334);
				match(D8);
				setState(335);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(336);
				match(OpenParen);
				setState(337);
				match(Star);
				setState(338);
				match(UUC);
				setState(339);
				match(TUC);
				setState(340);
				match(FUC);
				setState(341);
				match(D1);
				setState(342);
				match(D6);
				setState(343);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(344);
				match(OpenParen);
				setState(345);
				match(Star);
				setState(346);
				match(UUC);
				setState(347);
				match(CUC);
				setState(348);
				match(PUC);
				setState(349);
				match(CloseParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Option_flagsContext extends ParserRuleContext {
		public List<Option_flagContext> option_flag() {
			return getRuleContexts(Option_flagContext.class);
		}
		public Option_flagContext option_flag(int i) {
			return getRuleContext(Option_flagContext.class,i);
		}
		public Option_flagsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_flags; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterOption_flags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitOption_flags(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitOption_flags(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_flagsContext option_flags() throws RecognitionException {
		Option_flagsContext _localctx = new Option_flagsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_option_flags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(353); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(352);
				option_flag();
				}
				}
				setState(355); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (ILC - 69)) | (1L << (MLC - 69)) | (1L << (SLC - 69)) | (1L << (XLC - 69)) | (1L << (JUC - 69)) | (1L << (UUC - 69)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Option_flagContext extends ParserRuleContext {
		public TerminalNode ILC() { return getToken(PythonRegexParser.ILC, 0); }
		public TerminalNode JUC() { return getToken(PythonRegexParser.JUC, 0); }
		public TerminalNode MLC() { return getToken(PythonRegexParser.MLC, 0); }
		public TerminalNode SLC() { return getToken(PythonRegexParser.SLC, 0); }
		public TerminalNode UUC() { return getToken(PythonRegexParser.UUC, 0); }
		public TerminalNode XLC() { return getToken(PythonRegexParser.XLC, 0); }
		public Option_flagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterOption_flag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitOption_flag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitOption_flag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_flagContext option_flag() throws RecognitionException {
		Option_flagContext _localctx = new Option_flagContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_option_flag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			_la = _input.LA(1);
			if ( !(((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (ILC - 69)) | (1L << (MLC - 69)) | (1L << (SLC - 69)) | (1L << (XLC - 69)) | (1L << (JUC - 69)) | (1L << (UUC - 69)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Look_aroundContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public TerminalNode Equals() { return getToken(PythonRegexParser.Equals, 0); }
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public TerminalNode Exclamation() { return getToken(PythonRegexParser.Exclamation, 0); }
		public TerminalNode LessThan() { return getToken(PythonRegexParser.LessThan, 0); }
		public Look_aroundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_look_around; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterLook_around(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitLook_around(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitLook_around(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Look_aroundContext look_around() throws RecognitionException {
		Look_aroundContext _localctx = new Look_aroundContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_look_around);
		try {
			setState(385);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(359);
				match(OpenParen);
				setState(360);
				match(QuestionMark);
				setState(361);
				match(Equals);
				setState(362);
				alternation();
				setState(363);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(365);
				match(OpenParen);
				setState(366);
				match(QuestionMark);
				setState(367);
				match(Exclamation);
				setState(368);
				alternation();
				setState(369);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(371);
				match(OpenParen);
				setState(372);
				match(QuestionMark);
				setState(373);
				match(LessThan);
				setState(374);
				match(Equals);
				setState(375);
				alternation();
				setState(376);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(378);
				match(OpenParen);
				setState(379);
				match(QuestionMark);
				setState(380);
				match(LessThan);
				setState(381);
				match(Exclamation);
				setState(382);
				alternation();
				setState(383);
				match(CloseParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Subroutine_referenceContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public TerminalNode RUC() { return getToken(PythonRegexParser.RUC, 0); }
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode Plus() { return getToken(PythonRegexParser.Plus, 0); }
		public TerminalNode Hyphen() { return getToken(PythonRegexParser.Hyphen, 0); }
		public TerminalNode Ampersand() { return getToken(PythonRegexParser.Ampersand, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode PUC() { return getToken(PythonRegexParser.PUC, 0); }
		public TerminalNode GreaterThan() { return getToken(PythonRegexParser.GreaterThan, 0); }
		public TerminalNode SubroutineOrNamedReferenceStartG() { return getToken(PythonRegexParser.SubroutineOrNamedReferenceStartG, 0); }
		public TerminalNode LessThan() { return getToken(PythonRegexParser.LessThan, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(PythonRegexParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(PythonRegexParser.SingleQuote, i);
		}
		public Subroutine_referenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subroutine_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterSubroutine_reference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitSubroutine_reference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitSubroutine_reference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subroutine_referenceContext subroutine_reference() throws RecognitionException {
		Subroutine_referenceContext _localctx = new Subroutine_referenceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_subroutine_reference);
		try {
			setState(465);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				match(OpenParen);
				setState(388);
				match(QuestionMark);
				setState(389);
				match(RUC);
				setState(390);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(391);
				match(OpenParen);
				setState(392);
				match(QuestionMark);
				setState(393);
				number();
				setState(394);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(396);
				match(OpenParen);
				setState(397);
				match(QuestionMark);
				setState(398);
				match(Plus);
				setState(399);
				number();
				setState(400);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(402);
				match(OpenParen);
				setState(403);
				match(QuestionMark);
				setState(404);
				match(Hyphen);
				setState(405);
				number();
				setState(406);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(408);
				match(OpenParen);
				setState(409);
				match(QuestionMark);
				setState(410);
				match(Ampersand);
				setState(411);
				name();
				setState(412);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(414);
				match(OpenParen);
				setState(415);
				match(QuestionMark);
				setState(416);
				match(PUC);
				setState(417);
				match(GreaterThan);
				setState(418);
				name();
				setState(419);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(421);
				match(SubroutineOrNamedReferenceStartG);
				setState(422);
				match(LessThan);
				setState(423);
				name();
				setState(424);
				match(GreaterThan);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(426);
				match(SubroutineOrNamedReferenceStartG);
				setState(427);
				match(SingleQuote);
				setState(428);
				name();
				setState(429);
				match(SingleQuote);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(431);
				match(SubroutineOrNamedReferenceStartG);
				setState(432);
				match(LessThan);
				setState(433);
				number();
				setState(434);
				match(GreaterThan);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(436);
				match(SubroutineOrNamedReferenceStartG);
				setState(437);
				match(SingleQuote);
				setState(438);
				number();
				setState(439);
				match(SingleQuote);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(441);
				match(SubroutineOrNamedReferenceStartG);
				setState(442);
				match(LessThan);
				setState(443);
				match(Plus);
				setState(444);
				number();
				setState(445);
				match(GreaterThan);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(447);
				match(SubroutineOrNamedReferenceStartG);
				setState(448);
				match(SingleQuote);
				setState(449);
				match(Plus);
				setState(450);
				number();
				setState(451);
				match(SingleQuote);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(453);
				match(SubroutineOrNamedReferenceStartG);
				setState(454);
				match(LessThan);
				setState(455);
				match(Hyphen);
				setState(456);
				number();
				setState(457);
				match(GreaterThan);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(459);
				match(SubroutineOrNamedReferenceStartG);
				setState(460);
				match(SingleQuote);
				setState(461);
				match(Hyphen);
				setState(462);
				number();
				setState(463);
				match(SingleQuote);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalContext extends ParserRuleContext {
		public List<TerminalNode> OpenParen() { return getTokens(PythonRegexParser.OpenParen); }
		public TerminalNode OpenParen(int i) {
			return getToken(PythonRegexParser.OpenParen, i);
		}
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<TerminalNode> CloseParen() { return getTokens(PythonRegexParser.CloseParen); }
		public TerminalNode CloseParen(int i) {
			return getToken(PythonRegexParser.CloseParen, i);
		}
		public List<AlternationContext> alternation() {
			return getRuleContexts(AlternationContext.class);
		}
		public AlternationContext alternation(int i) {
			return getRuleContext(AlternationContext.class,i);
		}
		public TerminalNode Pipe() { return getToken(PythonRegexParser.Pipe, 0); }
		public TerminalNode Plus() { return getToken(PythonRegexParser.Plus, 0); }
		public TerminalNode Hyphen() { return getToken(PythonRegexParser.Hyphen, 0); }
		public TerminalNode LessThan() { return getToken(PythonRegexParser.LessThan, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode GreaterThan() { return getToken(PythonRegexParser.GreaterThan, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(PythonRegexParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(PythonRegexParser.SingleQuote, i);
		}
		public TerminalNode RUC() { return getToken(PythonRegexParser.RUC, 0); }
		public TerminalNode Ampersand() { return getToken(PythonRegexParser.Ampersand, 0); }
		public TerminalNode DUC() { return getToken(PythonRegexParser.DUC, 0); }
		public List<TerminalNode> EUC() { return getTokens(PythonRegexParser.EUC); }
		public TerminalNode EUC(int i) {
			return getToken(PythonRegexParser.EUC, i);
		}
		public TerminalNode FUC() { return getToken(PythonRegexParser.FUC, 0); }
		public TerminalNode IUC() { return getToken(PythonRegexParser.IUC, 0); }
		public TerminalNode NUC() { return getToken(PythonRegexParser.NUC, 0); }
		public TerminalNode ALC() { return getToken(PythonRegexParser.ALC, 0); }
		public List<TerminalNode> SLC() { return getTokens(PythonRegexParser.SLC); }
		public TerminalNode SLC(int i) {
			return getToken(PythonRegexParser.SLC, i);
		}
		public TerminalNode ELC() { return getToken(PythonRegexParser.ELC, 0); }
		public TerminalNode RLC() { return getToken(PythonRegexParser.RLC, 0); }
		public TerminalNode TLC() { return getToken(PythonRegexParser.TLC, 0); }
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_conditional);
		int _la;
		try {
			setState(618);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(467);
				match(OpenParen);
				setState(468);
				match(QuestionMark);
				setState(469);
				match(OpenParen);
				setState(470);
				number();
				setState(471);
				match(CloseParen);
				setState(472);
				alternation();
				setState(475);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(473);
					match(Pipe);
					setState(474);
					alternation();
					}
				}

				setState(477);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(479);
				match(OpenParen);
				setState(480);
				match(QuestionMark);
				setState(481);
				match(OpenParen);
				setState(482);
				match(Plus);
				setState(483);
				number();
				setState(484);
				match(CloseParen);
				setState(485);
				alternation();
				setState(488);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(486);
					match(Pipe);
					setState(487);
					alternation();
					}
				}

				setState(490);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(492);
				match(OpenParen);
				setState(493);
				match(QuestionMark);
				setState(494);
				match(OpenParen);
				setState(495);
				match(Hyphen);
				setState(496);
				number();
				setState(497);
				match(CloseParen);
				setState(498);
				alternation();
				setState(501);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(499);
					match(Pipe);
					setState(500);
					alternation();
					}
				}

				setState(503);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(505);
				match(OpenParen);
				setState(506);
				match(QuestionMark);
				setState(507);
				match(OpenParen);
				setState(508);
				match(LessThan);
				setState(509);
				name();
				setState(510);
				match(GreaterThan);
				setState(511);
				match(CloseParen);
				setState(512);
				alternation();
				setState(515);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(513);
					match(Pipe);
					setState(514);
					alternation();
					}
				}

				setState(517);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(519);
				match(OpenParen);
				setState(520);
				match(QuestionMark);
				setState(521);
				match(OpenParen);
				setState(522);
				match(SingleQuote);
				setState(523);
				name();
				setState(524);
				match(SingleQuote);
				setState(525);
				match(CloseParen);
				setState(526);
				alternation();
				setState(529);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(527);
					match(Pipe);
					setState(528);
					alternation();
					}
				}

				setState(531);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(533);
				match(OpenParen);
				setState(534);
				match(QuestionMark);
				setState(535);
				match(OpenParen);
				setState(536);
				match(RUC);
				setState(537);
				number();
				setState(538);
				match(CloseParen);
				setState(539);
				alternation();
				setState(542);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(540);
					match(Pipe);
					setState(541);
					alternation();
					}
				}

				setState(544);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(546);
				match(OpenParen);
				setState(547);
				match(QuestionMark);
				setState(548);
				match(OpenParen);
				setState(549);
				match(RUC);
				setState(550);
				match(CloseParen);
				setState(551);
				alternation();
				setState(554);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(552);
					match(Pipe);
					setState(553);
					alternation();
					}
				}

				setState(556);
				match(CloseParen);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(558);
				match(OpenParen);
				setState(559);
				match(QuestionMark);
				setState(560);
				match(OpenParen);
				setState(561);
				match(RUC);
				setState(562);
				match(Ampersand);
				setState(563);
				name();
				setState(564);
				match(CloseParen);
				setState(565);
				alternation();
				setState(568);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(566);
					match(Pipe);
					setState(567);
					alternation();
					}
				}

				setState(570);
				match(CloseParen);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(572);
				match(OpenParen);
				setState(573);
				match(QuestionMark);
				setState(574);
				match(OpenParen);
				setState(575);
				match(DUC);
				setState(576);
				match(EUC);
				setState(577);
				match(FUC);
				setState(578);
				match(IUC);
				setState(579);
				match(NUC);
				setState(580);
				match(EUC);
				setState(581);
				match(CloseParen);
				setState(582);
				alternation();
				setState(585);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(583);
					match(Pipe);
					setState(584);
					alternation();
					}
				}

				setState(587);
				match(CloseParen);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(589);
				match(OpenParen);
				setState(590);
				match(QuestionMark);
				setState(591);
				match(OpenParen);
				setState(592);
				match(ALC);
				setState(593);
				match(SLC);
				setState(594);
				match(SLC);
				setState(595);
				match(ELC);
				setState(596);
				match(RLC);
				setState(597);
				match(TLC);
				setState(598);
				match(CloseParen);
				setState(599);
				alternation();
				setState(602);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(600);
					match(Pipe);
					setState(601);
					alternation();
					}
				}

				setState(604);
				match(CloseParen);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(606);
				match(OpenParen);
				setState(607);
				match(QuestionMark);
				setState(608);
				match(OpenParen);
				setState(609);
				name();
				setState(610);
				match(CloseParen);
				setState(611);
				alternation();
				setState(614);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(612);
					match(Pipe);
					setState(613);
					alternation();
					}
				}

				setState(616);
				match(CloseParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Backtrack_controlContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode Star() { return getToken(PythonRegexParser.Star, 0); }
		public List<TerminalNode> AUC() { return getTokens(PythonRegexParser.AUC); }
		public TerminalNode AUC(int i) {
			return getToken(PythonRegexParser.AUC, i);
		}
		public List<TerminalNode> CUC() { return getTokens(PythonRegexParser.CUC); }
		public TerminalNode CUC(int i) {
			return getToken(PythonRegexParser.CUC, i);
		}
		public List<TerminalNode> EUC() { return getTokens(PythonRegexParser.EUC); }
		public TerminalNode EUC(int i) {
			return getToken(PythonRegexParser.EUC, i);
		}
		public TerminalNode PUC() { return getToken(PythonRegexParser.PUC, 0); }
		public TerminalNode TUC() { return getToken(PythonRegexParser.TUC, 0); }
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public TerminalNode FUC() { return getToken(PythonRegexParser.FUC, 0); }
		public TerminalNode IUC() { return getToken(PythonRegexParser.IUC, 0); }
		public TerminalNode LUC() { return getToken(PythonRegexParser.LUC, 0); }
		public TerminalNode Colon() { return getToken(PythonRegexParser.Colon, 0); }
		public List<TerminalNode> NUC() { return getTokens(PythonRegexParser.NUC); }
		public TerminalNode NUC(int i) {
			return getToken(PythonRegexParser.NUC, i);
		}
		public List<TerminalNode> MUC() { return getTokens(PythonRegexParser.MUC); }
		public TerminalNode MUC(int i) {
			return getToken(PythonRegexParser.MUC, i);
		}
		public TerminalNode RUC() { return getToken(PythonRegexParser.RUC, 0); }
		public TerminalNode KUC() { return getToken(PythonRegexParser.KUC, 0); }
		public TerminalNode OUC() { return getToken(PythonRegexParser.OUC, 0); }
		public TerminalNode UUC() { return getToken(PythonRegexParser.UUC, 0); }
		public TerminalNode SUC() { return getToken(PythonRegexParser.SUC, 0); }
		public TerminalNode HUC() { return getToken(PythonRegexParser.HUC, 0); }
		public Backtrack_controlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backtrack_control; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterBacktrack_control(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitBacktrack_control(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitBacktrack_control(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Backtrack_controlContext backtrack_control() throws RecognitionException {
		Backtrack_controlContext _localctx = new Backtrack_controlContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_backtrack_control);
		int _la;
		try {
			setState(720);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(620);
				match(OpenParen);
				setState(621);
				match(Star);
				setState(622);
				match(AUC);
				setState(623);
				match(CUC);
				setState(624);
				match(CUC);
				setState(625);
				match(EUC);
				setState(626);
				match(PUC);
				setState(627);
				match(TUC);
				setState(628);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(629);
				match(OpenParen);
				setState(630);
				match(Star);
				setState(631);
				match(FUC);
				setState(635);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AUC) {
					{
					setState(632);
					match(AUC);
					setState(633);
					match(IUC);
					setState(634);
					match(LUC);
					}
				}

				setState(637);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(638);
				match(OpenParen);
				setState(639);
				match(Star);
				setState(644);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MUC) {
					{
					setState(640);
					match(MUC);
					setState(641);
					match(AUC);
					setState(642);
					match(RUC);
					setState(643);
					match(KUC);
					}
				}

				setState(646);
				match(Colon);
				setState(647);
				match(NUC);
				setState(648);
				match(AUC);
				setState(649);
				match(MUC);
				setState(650);
				match(EUC);
				setState(651);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(652);
				match(OpenParen);
				setState(653);
				match(Star);
				setState(654);
				match(CUC);
				setState(655);
				match(OUC);
				setState(656);
				match(MUC);
				setState(657);
				match(MUC);
				setState(658);
				match(IUC);
				setState(659);
				match(TUC);
				setState(660);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(661);
				match(OpenParen);
				setState(662);
				match(Star);
				setState(663);
				match(PUC);
				setState(664);
				match(RUC);
				setState(665);
				match(UUC);
				setState(666);
				match(NUC);
				setState(667);
				match(EUC);
				setState(668);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(669);
				match(OpenParen);
				setState(670);
				match(Star);
				setState(671);
				match(PUC);
				setState(672);
				match(RUC);
				setState(673);
				match(UUC);
				setState(674);
				match(NUC);
				setState(675);
				match(EUC);
				setState(676);
				match(Colon);
				setState(677);
				match(NUC);
				setState(678);
				match(AUC);
				setState(679);
				match(MUC);
				setState(680);
				match(EUC);
				setState(681);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(682);
				match(OpenParen);
				setState(683);
				match(Star);
				setState(684);
				match(SUC);
				setState(685);
				match(KUC);
				setState(686);
				match(IUC);
				setState(687);
				match(PUC);
				setState(688);
				match(CloseParen);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(689);
				match(OpenParen);
				setState(690);
				match(Star);
				setState(691);
				match(SUC);
				setState(692);
				match(KUC);
				setState(693);
				match(IUC);
				setState(694);
				match(PUC);
				setState(695);
				match(Colon);
				setState(696);
				match(NUC);
				setState(697);
				match(AUC);
				setState(698);
				match(MUC);
				setState(699);
				match(EUC);
				setState(700);
				match(CloseParen);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(701);
				match(OpenParen);
				setState(702);
				match(Star);
				setState(703);
				match(TUC);
				setState(704);
				match(HUC);
				setState(705);
				match(EUC);
				setState(706);
				match(NUC);
				setState(707);
				match(CloseParen);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(708);
				match(OpenParen);
				setState(709);
				match(Star);
				setState(710);
				match(TUC);
				setState(711);
				match(HUC);
				setState(712);
				match(EUC);
				setState(713);
				match(NUC);
				setState(714);
				match(Colon);
				setState(715);
				match(NUC);
				setState(716);
				match(AUC);
				setState(717);
				match(MUC);
				setState(718);
				match(EUC);
				setState(719);
				match(CloseParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Newline_conventionContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode Star() { return getToken(PythonRegexParser.Star, 0); }
		public TerminalNode CUC() { return getToken(PythonRegexParser.CUC, 0); }
		public List<TerminalNode> RUC() { return getTokens(PythonRegexParser.RUC); }
		public TerminalNode RUC(int i) {
			return getToken(PythonRegexParser.RUC, i);
		}
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public TerminalNode LUC() { return getToken(PythonRegexParser.LUC, 0); }
		public TerminalNode FUC() { return getToken(PythonRegexParser.FUC, 0); }
		public TerminalNode AUC() { return getToken(PythonRegexParser.AUC, 0); }
		public TerminalNode NUC() { return getToken(PythonRegexParser.NUC, 0); }
		public TerminalNode YUC() { return getToken(PythonRegexParser.YUC, 0); }
		public TerminalNode BUC() { return getToken(PythonRegexParser.BUC, 0); }
		public TerminalNode SUC() { return getToken(PythonRegexParser.SUC, 0); }
		public TerminalNode Underscore() { return getToken(PythonRegexParser.Underscore, 0); }
		public TerminalNode UUC() { return getToken(PythonRegexParser.UUC, 0); }
		public TerminalNode IUC() { return getToken(PythonRegexParser.IUC, 0); }
		public TerminalNode OUC() { return getToken(PythonRegexParser.OUC, 0); }
		public TerminalNode DUC() { return getToken(PythonRegexParser.DUC, 0); }
		public TerminalNode EUC() { return getToken(PythonRegexParser.EUC, 0); }
		public Newline_conventionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newline_convention; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterNewline_convention(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitNewline_convention(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitNewline_convention(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Newline_conventionContext newline_convention() throws RecognitionException {
		Newline_conventionContext _localctx = new Newline_conventionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_newline_convention);
		try {
			setState(783);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(722);
				match(OpenParen);
				setState(723);
				match(Star);
				setState(724);
				match(CUC);
				setState(725);
				match(RUC);
				setState(726);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(727);
				match(OpenParen);
				setState(728);
				match(Star);
				setState(729);
				match(LUC);
				setState(730);
				match(FUC);
				setState(731);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(732);
				match(OpenParen);
				setState(733);
				match(Star);
				setState(734);
				match(CUC);
				setState(735);
				match(RUC);
				setState(736);
				match(LUC);
				setState(737);
				match(FUC);
				setState(738);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(739);
				match(OpenParen);
				setState(740);
				match(Star);
				setState(741);
				match(AUC);
				setState(742);
				match(NUC);
				setState(743);
				match(YUC);
				setState(744);
				match(CUC);
				setState(745);
				match(RUC);
				setState(746);
				match(LUC);
				setState(747);
				match(FUC);
				setState(748);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(749);
				match(OpenParen);
				setState(750);
				match(Star);
				setState(751);
				match(AUC);
				setState(752);
				match(NUC);
				setState(753);
				match(YUC);
				setState(754);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(755);
				match(OpenParen);
				setState(756);
				match(Star);
				setState(757);
				match(BUC);
				setState(758);
				match(SUC);
				setState(759);
				match(RUC);
				setState(760);
				match(Underscore);
				setState(761);
				match(AUC);
				setState(762);
				match(NUC);
				setState(763);
				match(YUC);
				setState(764);
				match(CUC);
				setState(765);
				match(RUC);
				setState(766);
				match(LUC);
				setState(767);
				match(FUC);
				setState(768);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(769);
				match(OpenParen);
				setState(770);
				match(Star);
				setState(771);
				match(BUC);
				setState(772);
				match(SUC);
				setState(773);
				match(RUC);
				setState(774);
				match(Underscore);
				setState(775);
				match(UUC);
				setState(776);
				match(NUC);
				setState(777);
				match(IUC);
				setState(778);
				match(CUC);
				setState(779);
				match(OUC);
				setState(780);
				match(DUC);
				setState(781);
				match(EUC);
				setState(782);
				match(CloseParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CalloutContext extends ParserRuleContext {
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public TerminalNode CUC() { return getToken(PythonRegexParser.CUC, 0); }
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public CalloutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callout; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterCallout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitCallout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitCallout(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalloutContext callout() throws RecognitionException {
		CalloutContext _localctx = new CalloutContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_callout);
		try {
			setState(795);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(785);
				match(OpenParen);
				setState(786);
				match(QuestionMark);
				setState(787);
				match(CUC);
				setState(788);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(789);
				match(OpenParen);
				setState(790);
				match(QuestionMark);
				setState(791);
				match(CUC);
				setState(792);
				number();
				setState(793);
				match(CloseParen);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public Subroutine_referenceContext subroutine_reference() {
			return getRuleContext(Subroutine_referenceContext.class,0);
		}
		public Shared_atomContext shared_atom() {
			return getRuleContext(Shared_atomContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Character_classContext character_class() {
			return getRuleContext(Character_classContext.class,0);
		}
		public CaptureContext capture() {
			return getRuleContext(CaptureContext.class,0);
		}
		public Non_captureContext non_capture() {
			return getRuleContext(Non_captureContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public OptionContext option() {
			return getRuleContext(OptionContext.class,0);
		}
		public Look_aroundContext look_around() {
			return getRuleContext(Look_aroundContext.class,0);
		}
		public BackreferenceContext backreference() {
			return getRuleContext(BackreferenceContext.class,0);
		}
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public Backtrack_controlContext backtrack_control() {
			return getRuleContext(Backtrack_controlContext.class,0);
		}
		public Newline_conventionContext newline_convention() {
			return getRuleContext(Newline_conventionContext.class,0);
		}
		public CalloutContext callout() {
			return getRuleContext(CalloutContext.class,0);
		}
		public TerminalNode Dot() { return getToken(PythonRegexParser.Dot, 0); }
		public TerminalNode Caret() { return getToken(PythonRegexParser.Caret, 0); }
		public TerminalNode StartOfSubject() { return getToken(PythonRegexParser.StartOfSubject, 0); }
		public TerminalNode WordBoundary() { return getToken(PythonRegexParser.WordBoundary, 0); }
		public TerminalNode NonWordBoundary() { return getToken(PythonRegexParser.NonWordBoundary, 0); }
		public TerminalNode EndOfSubjectOrLine() { return getToken(PythonRegexParser.EndOfSubjectOrLine, 0); }
		public TerminalNode EndOfSubjectOrLineEndOfSubject() { return getToken(PythonRegexParser.EndOfSubjectOrLineEndOfSubject, 0); }
		public TerminalNode EndOfSubject() { return getToken(PythonRegexParser.EndOfSubject, 0); }
		public TerminalNode PreviousMatchInSubject() { return getToken(PythonRegexParser.PreviousMatchInSubject, 0); }
		public TerminalNode ResetStartMatch() { return getToken(PythonRegexParser.ResetStartMatch, 0); }
		public TerminalNode OneDataUnit() { return getToken(PythonRegexParser.OneDataUnit, 0); }
		public TerminalNode ExtendedUnicodeChar() { return getToken(PythonRegexParser.ExtendedUnicodeChar, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_atom);
		try {
			setState(823);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(797);
				subroutine_reference();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(798);
				shared_atom();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(799);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(800);
				character_class();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(801);
				capture();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(802);
				non_capture();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(803);
				comment();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(804);
				option();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(805);
				look_around();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(806);
				backreference();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(807);
				conditional();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(808);
				backtrack_control();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(809);
				newline_convention();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(810);
				callout();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(811);
				match(Dot);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(812);
				match(Caret);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(813);
				match(StartOfSubject);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(814);
				match(WordBoundary);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(815);
				match(NonWordBoundary);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(816);
				match(EndOfSubjectOrLine);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(817);
				match(EndOfSubjectOrLineEndOfSubject);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(818);
				match(EndOfSubject);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(819);
				match(PreviousMatchInSubject);
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(820);
				match(ResetStartMatch);
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(821);
				match(OneDataUnit);
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(822);
				match(ExtendedUnicodeChar);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cc_atomContext extends ParserRuleContext {
		public List<Cc_literalContext> cc_literal() {
			return getRuleContexts(Cc_literalContext.class);
		}
		public Cc_literalContext cc_literal(int i) {
			return getRuleContext(Cc_literalContext.class,i);
		}
		public TerminalNode Hyphen() { return getToken(PythonRegexParser.Hyphen, 0); }
		public Shared_atomContext shared_atom() {
			return getRuleContext(Shared_atomContext.class,0);
		}
		public Backreference_or_octalContext backreference_or_octal() {
			return getRuleContext(Backreference_or_octalContext.class,0);
		}
		public Cc_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cc_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterCc_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitCc_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitCc_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cc_atomContext cc_atom() throws RecognitionException {
		Cc_atomContext _localctx = new Cc_atomContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_cc_atom);
		try {
			setState(832);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(825);
				cc_literal();
				setState(826);
				match(Hyphen);
				setState(827);
				cc_literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(829);
				shared_atom();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(830);
				cc_literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(831);
				backreference_or_octal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shared_atomContext extends ParserRuleContext {
		public TerminalNode ControlChar() { return getToken(PythonRegexParser.ControlChar, 0); }
		public TerminalNode DecimalDigit() { return getToken(PythonRegexParser.DecimalDigit, 0); }
		public TerminalNode NotDecimalDigit() { return getToken(PythonRegexParser.NotDecimalDigit, 0); }
		public TerminalNode HorizontalWhiteSpace() { return getToken(PythonRegexParser.HorizontalWhiteSpace, 0); }
		public TerminalNode NotHorizontalWhiteSpace() { return getToken(PythonRegexParser.NotHorizontalWhiteSpace, 0); }
		public TerminalNode NotNewLine() { return getToken(PythonRegexParser.NotNewLine, 0); }
		public TerminalNode CharWithProperty() { return getToken(PythonRegexParser.CharWithProperty, 0); }
		public TerminalNode CharWithoutProperty() { return getToken(PythonRegexParser.CharWithoutProperty, 0); }
		public TerminalNode NewLineSequence() { return getToken(PythonRegexParser.NewLineSequence, 0); }
		public TerminalNode WhiteSpace() { return getToken(PythonRegexParser.WhiteSpace, 0); }
		public TerminalNode NotWhiteSpace() { return getToken(PythonRegexParser.NotWhiteSpace, 0); }
		public TerminalNode VerticalWhiteSpace() { return getToken(PythonRegexParser.VerticalWhiteSpace, 0); }
		public TerminalNode NotVerticalWhiteSpace() { return getToken(PythonRegexParser.NotVerticalWhiteSpace, 0); }
		public TerminalNode WordChar() { return getToken(PythonRegexParser.WordChar, 0); }
		public TerminalNode NotWordChar() { return getToken(PythonRegexParser.NotWordChar, 0); }
		public Shared_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shared_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterShared_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitShared_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitShared_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shared_atomContext shared_atom() throws RecognitionException {
		Shared_atomContext _localctx = new Shared_atomContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_shared_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(834);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ControlChar) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public Shared_literalContext shared_literal() {
			return getRuleContext(Shared_literalContext.class,0);
		}
		public TerminalNode CharacterClassEnd() { return getToken(PythonRegexParser.CharacterClassEnd, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_literal);
		try {
			setState(838);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Quoted:
			case BlockQuoted:
			case BellChar:
			case EscapeChar:
			case FormFeed:
			case NewLine:
			case CarriageReturn:
			case Tab:
			case Backslash:
			case HexChar:
			case Hyphen:
			case OpenBrace:
			case CloseBrace:
			case Comma:
			case LessThan:
			case GreaterThan:
			case SingleQuote:
			case Underscore:
			case Colon:
			case Hash:
			case Equals:
			case Exclamation:
			case Ampersand:
			case ALC:
			case BLC:
			case CLC:
			case DLC:
			case ELC:
			case FLC:
			case GLC:
			case HLC:
			case ILC:
			case JLC:
			case KLC:
			case LLC:
			case MLC:
			case NLC:
			case OLC:
			case PLC:
			case QLC:
			case RLC:
			case SLC:
			case TLC:
			case ULC:
			case VLC:
			case WLC:
			case XLC:
			case YLC:
			case ZLC:
			case AUC:
			case BUC:
			case CUC:
			case DUC:
			case EUC:
			case FUC:
			case GUC:
			case HUC:
			case IUC:
			case JUC:
			case KUC:
			case LUC:
			case MUC:
			case NUC:
			case OUC:
			case PUC:
			case QUC:
			case RUC:
			case SUC:
			case TUC:
			case UUC:
			case VUC:
			case WUC:
			case XUC:
			case YUC:
			case ZUC:
			case D1:
			case D2:
			case D3:
			case D4:
			case D5:
			case D6:
			case D7:
			case D8:
			case D9:
			case D0:
			case OtherChar:
				enterOuterAlt(_localctx, 1);
				{
				setState(836);
				shared_literal();
				}
				break;
			case CharacterClassEnd:
				enterOuterAlt(_localctx, 2);
				{
				setState(837);
				match(CharacterClassEnd);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cc_literalContext extends ParserRuleContext {
		public Shared_literalContext shared_literal() {
			return getRuleContext(Shared_literalContext.class,0);
		}
		public TerminalNode Dot() { return getToken(PythonRegexParser.Dot, 0); }
		public TerminalNode CharacterClassStart() { return getToken(PythonRegexParser.CharacterClassStart, 0); }
		public TerminalNode Caret() { return getToken(PythonRegexParser.Caret, 0); }
		public TerminalNode QuestionMark() { return getToken(PythonRegexParser.QuestionMark, 0); }
		public TerminalNode Plus() { return getToken(PythonRegexParser.Plus, 0); }
		public TerminalNode Star() { return getToken(PythonRegexParser.Star, 0); }
		public TerminalNode WordBoundary() { return getToken(PythonRegexParser.WordBoundary, 0); }
		public TerminalNode EndOfSubjectOrLine() { return getToken(PythonRegexParser.EndOfSubjectOrLine, 0); }
		public TerminalNode Pipe() { return getToken(PythonRegexParser.Pipe, 0); }
		public TerminalNode OpenParen() { return getToken(PythonRegexParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public Cc_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cc_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterCc_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitCc_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitCc_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cc_literalContext cc_literal() throws RecognitionException {
		Cc_literalContext _localctx = new Cc_literalContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_cc_literal);
		try {
			setState(852);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Quoted:
			case BlockQuoted:
			case BellChar:
			case EscapeChar:
			case FormFeed:
			case NewLine:
			case CarriageReturn:
			case Tab:
			case Backslash:
			case HexChar:
			case Hyphen:
			case OpenBrace:
			case CloseBrace:
			case Comma:
			case LessThan:
			case GreaterThan:
			case SingleQuote:
			case Underscore:
			case Colon:
			case Hash:
			case Equals:
			case Exclamation:
			case Ampersand:
			case ALC:
			case BLC:
			case CLC:
			case DLC:
			case ELC:
			case FLC:
			case GLC:
			case HLC:
			case ILC:
			case JLC:
			case KLC:
			case LLC:
			case MLC:
			case NLC:
			case OLC:
			case PLC:
			case QLC:
			case RLC:
			case SLC:
			case TLC:
			case ULC:
			case VLC:
			case WLC:
			case XLC:
			case YLC:
			case ZLC:
			case AUC:
			case BUC:
			case CUC:
			case DUC:
			case EUC:
			case FUC:
			case GUC:
			case HUC:
			case IUC:
			case JUC:
			case KUC:
			case LUC:
			case MUC:
			case NUC:
			case OUC:
			case PUC:
			case QUC:
			case RUC:
			case SUC:
			case TUC:
			case UUC:
			case VUC:
			case WUC:
			case XUC:
			case YUC:
			case ZUC:
			case D1:
			case D2:
			case D3:
			case D4:
			case D5:
			case D6:
			case D7:
			case D8:
			case D9:
			case D0:
			case OtherChar:
				enterOuterAlt(_localctx, 1);
				{
				setState(840);
				shared_literal();
				}
				break;
			case Dot:
				enterOuterAlt(_localctx, 2);
				{
				setState(841);
				match(Dot);
				}
				break;
			case CharacterClassStart:
				enterOuterAlt(_localctx, 3);
				{
				setState(842);
				match(CharacterClassStart);
				}
				break;
			case Caret:
				enterOuterAlt(_localctx, 4);
				{
				setState(843);
				match(Caret);
				}
				break;
			case QuestionMark:
				enterOuterAlt(_localctx, 5);
				{
				setState(844);
				match(QuestionMark);
				}
				break;
			case Plus:
				enterOuterAlt(_localctx, 6);
				{
				setState(845);
				match(Plus);
				}
				break;
			case Star:
				enterOuterAlt(_localctx, 7);
				{
				setState(846);
				match(Star);
				}
				break;
			case WordBoundary:
				enterOuterAlt(_localctx, 8);
				{
				setState(847);
				match(WordBoundary);
				}
				break;
			case EndOfSubjectOrLine:
				enterOuterAlt(_localctx, 9);
				{
				setState(848);
				match(EndOfSubjectOrLine);
				}
				break;
			case Pipe:
				enterOuterAlt(_localctx, 10);
				{
				setState(849);
				match(Pipe);
				}
				break;
			case OpenParen:
				enterOuterAlt(_localctx, 11);
				{
				setState(850);
				match(OpenParen);
				}
				break;
			case CloseParen:
				enterOuterAlt(_localctx, 12);
				{
				setState(851);
				match(CloseParen);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shared_literalContext extends ParserRuleContext {
		public Octal_charContext octal_char() {
			return getRuleContext(Octal_charContext.class,0);
		}
		public LetterContext letter() {
			return getRuleContext(LetterContext.class,0);
		}
		public DigitContext digit() {
			return getRuleContext(DigitContext.class,0);
		}
		public TerminalNode BellChar() { return getToken(PythonRegexParser.BellChar, 0); }
		public TerminalNode EscapeChar() { return getToken(PythonRegexParser.EscapeChar, 0); }
		public TerminalNode FormFeed() { return getToken(PythonRegexParser.FormFeed, 0); }
		public TerminalNode NewLine() { return getToken(PythonRegexParser.NewLine, 0); }
		public TerminalNode CarriageReturn() { return getToken(PythonRegexParser.CarriageReturn, 0); }
		public TerminalNode Tab() { return getToken(PythonRegexParser.Tab, 0); }
		public TerminalNode HexChar() { return getToken(PythonRegexParser.HexChar, 0); }
		public TerminalNode Quoted() { return getToken(PythonRegexParser.Quoted, 0); }
		public TerminalNode BlockQuoted() { return getToken(PythonRegexParser.BlockQuoted, 0); }
		public TerminalNode OpenBrace() { return getToken(PythonRegexParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(PythonRegexParser.CloseBrace, 0); }
		public TerminalNode Comma() { return getToken(PythonRegexParser.Comma, 0); }
		public TerminalNode Hyphen() { return getToken(PythonRegexParser.Hyphen, 0); }
		public TerminalNode LessThan() { return getToken(PythonRegexParser.LessThan, 0); }
		public TerminalNode GreaterThan() { return getToken(PythonRegexParser.GreaterThan, 0); }
		public TerminalNode SingleQuote() { return getToken(PythonRegexParser.SingleQuote, 0); }
		public TerminalNode Underscore() { return getToken(PythonRegexParser.Underscore, 0); }
		public TerminalNode Colon() { return getToken(PythonRegexParser.Colon, 0); }
		public TerminalNode Hash() { return getToken(PythonRegexParser.Hash, 0); }
		public TerminalNode Equals() { return getToken(PythonRegexParser.Equals, 0); }
		public TerminalNode Exclamation() { return getToken(PythonRegexParser.Exclamation, 0); }
		public TerminalNode Ampersand() { return getToken(PythonRegexParser.Ampersand, 0); }
		public TerminalNode OtherChar() { return getToken(PythonRegexParser.OtherChar, 0); }
		public Shared_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shared_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterShared_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitShared_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitShared_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shared_literalContext shared_literal() throws RecognitionException {
		Shared_literalContext _localctx = new Shared_literalContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_shared_literal);
		try {
			setState(880);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Backslash:
				enterOuterAlt(_localctx, 1);
				{
				setState(854);
				octal_char();
				}
				break;
			case ALC:
			case BLC:
			case CLC:
			case DLC:
			case ELC:
			case FLC:
			case GLC:
			case HLC:
			case ILC:
			case JLC:
			case KLC:
			case LLC:
			case MLC:
			case NLC:
			case OLC:
			case PLC:
			case QLC:
			case RLC:
			case SLC:
			case TLC:
			case ULC:
			case VLC:
			case WLC:
			case XLC:
			case YLC:
			case ZLC:
			case AUC:
			case BUC:
			case CUC:
			case DUC:
			case EUC:
			case FUC:
			case GUC:
			case HUC:
			case IUC:
			case JUC:
			case KUC:
			case LUC:
			case MUC:
			case NUC:
			case OUC:
			case PUC:
			case QUC:
			case RUC:
			case SUC:
			case TUC:
			case UUC:
			case VUC:
			case WUC:
			case XUC:
			case YUC:
			case ZUC:
				enterOuterAlt(_localctx, 2);
				{
				setState(855);
				letter();
				}
				break;
			case D1:
			case D2:
			case D3:
			case D4:
			case D5:
			case D6:
			case D7:
			case D8:
			case D9:
			case D0:
				enterOuterAlt(_localctx, 3);
				{
				setState(856);
				digit();
				}
				break;
			case BellChar:
				enterOuterAlt(_localctx, 4);
				{
				setState(857);
				match(BellChar);
				}
				break;
			case EscapeChar:
				enterOuterAlt(_localctx, 5);
				{
				setState(858);
				match(EscapeChar);
				}
				break;
			case FormFeed:
				enterOuterAlt(_localctx, 6);
				{
				setState(859);
				match(FormFeed);
				}
				break;
			case NewLine:
				enterOuterAlt(_localctx, 7);
				{
				setState(860);
				match(NewLine);
				}
				break;
			case CarriageReturn:
				enterOuterAlt(_localctx, 8);
				{
				setState(861);
				match(CarriageReturn);
				}
				break;
			case Tab:
				enterOuterAlt(_localctx, 9);
				{
				setState(862);
				match(Tab);
				}
				break;
			case HexChar:
				enterOuterAlt(_localctx, 10);
				{
				setState(863);
				match(HexChar);
				}
				break;
			case Quoted:
				enterOuterAlt(_localctx, 11);
				{
				setState(864);
				match(Quoted);
				}
				break;
			case BlockQuoted:
				enterOuterAlt(_localctx, 12);
				{
				setState(865);
				match(BlockQuoted);
				}
				break;
			case OpenBrace:
				enterOuterAlt(_localctx, 13);
				{
				setState(866);
				match(OpenBrace);
				}
				break;
			case CloseBrace:
				enterOuterAlt(_localctx, 14);
				{
				setState(867);
				match(CloseBrace);
				}
				break;
			case Comma:
				enterOuterAlt(_localctx, 15);
				{
				setState(868);
				match(Comma);
				}
				break;
			case Hyphen:
				enterOuterAlt(_localctx, 16);
				{
				setState(869);
				match(Hyphen);
				}
				break;
			case LessThan:
				enterOuterAlt(_localctx, 17);
				{
				setState(870);
				match(LessThan);
				}
				break;
			case GreaterThan:
				enterOuterAlt(_localctx, 18);
				{
				setState(871);
				match(GreaterThan);
				}
				break;
			case SingleQuote:
				enterOuterAlt(_localctx, 19);
				{
				setState(872);
				match(SingleQuote);
				}
				break;
			case Underscore:
				enterOuterAlt(_localctx, 20);
				{
				setState(873);
				match(Underscore);
				}
				break;
			case Colon:
				enterOuterAlt(_localctx, 21);
				{
				setState(874);
				match(Colon);
				}
				break;
			case Hash:
				enterOuterAlt(_localctx, 22);
				{
				setState(875);
				match(Hash);
				}
				break;
			case Equals:
				enterOuterAlt(_localctx, 23);
				{
				setState(876);
				match(Equals);
				}
				break;
			case Exclamation:
				enterOuterAlt(_localctx, 24);
				{
				setState(877);
				match(Exclamation);
				}
				break;
			case Ampersand:
				enterOuterAlt(_localctx, 25);
				{
				setState(878);
				match(Ampersand);
				}
				break;
			case OtherChar:
				enterOuterAlt(_localctx, 26);
				{
				setState(879);
				match(OtherChar);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public DigitsContext digits() {
			return getRuleContext(DigitsContext.class,0);
		}
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(882);
			digits();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Octal_charContext extends ParserRuleContext {
		public TerminalNode Backslash() { return getToken(PythonRegexParser.Backslash, 0); }
		public List<Octal_digitContext> octal_digit() {
			return getRuleContexts(Octal_digitContext.class);
		}
		public Octal_digitContext octal_digit(int i) {
			return getRuleContext(Octal_digitContext.class,i);
		}
		public TerminalNode D0() { return getToken(PythonRegexParser.D0, 0); }
		public TerminalNode D1() { return getToken(PythonRegexParser.D1, 0); }
		public TerminalNode D2() { return getToken(PythonRegexParser.D2, 0); }
		public TerminalNode D3() { return getToken(PythonRegexParser.D3, 0); }
		public Octal_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octal_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterOctal_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitOctal_char(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitOctal_char(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Octal_charContext octal_char() throws RecognitionException {
		Octal_charContext _localctx = new Octal_charContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_octal_char);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(893);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(884);
				match(Backslash);
				setState(885);
				_la = _input.LA(1);
				if ( !(((((_la - 113)) & ~0x3f) == 0 && ((1L << (_la - 113)) & ((1L << (D1 - 113)) | (1L << (D2 - 113)) | (1L << (D3 - 113)) | (1L << (D0 - 113)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(886);
				octal_digit();
				setState(887);
				octal_digit();
				}
				break;
			case 2:
				{
				setState(889);
				match(Backslash);
				setState(890);
				octal_digit();
				setState(891);
				octal_digit();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Octal_digitContext extends ParserRuleContext {
		public TerminalNode D0() { return getToken(PythonRegexParser.D0, 0); }
		public TerminalNode D1() { return getToken(PythonRegexParser.D1, 0); }
		public TerminalNode D2() { return getToken(PythonRegexParser.D2, 0); }
		public TerminalNode D3() { return getToken(PythonRegexParser.D3, 0); }
		public TerminalNode D4() { return getToken(PythonRegexParser.D4, 0); }
		public TerminalNode D5() { return getToken(PythonRegexParser.D5, 0); }
		public TerminalNode D6() { return getToken(PythonRegexParser.D6, 0); }
		public TerminalNode D7() { return getToken(PythonRegexParser.D7, 0); }
		public Octal_digitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octal_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterOctal_digit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitOctal_digit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitOctal_digit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Octal_digitContext octal_digit() throws RecognitionException {
		Octal_digitContext _localctx = new Octal_digitContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_octal_digit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(895);
			_la = _input.LA(1);
			if ( !(((((_la - 113)) & ~0x3f) == 0 && ((1L << (_la - 113)) & ((1L << (D1 - 113)) | (1L << (D2 - 113)) | (1L << (D3 - 113)) | (1L << (D4 - 113)) | (1L << (D5 - 113)) | (1L << (D6 - 113)) | (1L << (D7 - 113)) | (1L << (D0 - 113)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DigitsContext extends ParserRuleContext {
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public DigitsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digits; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterDigits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitDigits(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitDigits(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DigitsContext digits() throws RecognitionException {
		DigitsContext _localctx = new DigitsContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_digits);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(898); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(897);
					digit();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(900); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DigitContext extends ParserRuleContext {
		public TerminalNode D0() { return getToken(PythonRegexParser.D0, 0); }
		public TerminalNode D1() { return getToken(PythonRegexParser.D1, 0); }
		public TerminalNode D2() { return getToken(PythonRegexParser.D2, 0); }
		public TerminalNode D3() { return getToken(PythonRegexParser.D3, 0); }
		public TerminalNode D4() { return getToken(PythonRegexParser.D4, 0); }
		public TerminalNode D5() { return getToken(PythonRegexParser.D5, 0); }
		public TerminalNode D6() { return getToken(PythonRegexParser.D6, 0); }
		public TerminalNode D7() { return getToken(PythonRegexParser.D7, 0); }
		public TerminalNode D8() { return getToken(PythonRegexParser.D8, 0); }
		public TerminalNode D9() { return getToken(PythonRegexParser.D9, 0); }
		public DigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitDigit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitDigit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DigitContext digit() throws RecognitionException {
		DigitContext _localctx = new DigitContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_digit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(902);
			_la = _input.LA(1);
			if ( !(((((_la - 113)) & ~0x3f) == 0 && ((1L << (_la - 113)) & ((1L << (D1 - 113)) | (1L << (D2 - 113)) | (1L << (D3 - 113)) | (1L << (D4 - 113)) | (1L << (D5 - 113)) | (1L << (D6 - 113)) | (1L << (D7 - 113)) | (1L << (D8 - 113)) | (1L << (D9 - 113)) | (1L << (D0 - 113)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public Alpha_numsContext alpha_nums() {
			return getRuleContext(Alpha_numsContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(904);
			alpha_nums();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Alpha_numsContext extends ParserRuleContext {
		public List<LetterContext> letter() {
			return getRuleContexts(LetterContext.class);
		}
		public LetterContext letter(int i) {
			return getRuleContext(LetterContext.class,i);
		}
		public List<TerminalNode> Underscore() { return getTokens(PythonRegexParser.Underscore); }
		public TerminalNode Underscore(int i) {
			return getToken(PythonRegexParser.Underscore, i);
		}
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public Alpha_numsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alpha_nums; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterAlpha_nums(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitAlpha_nums(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitAlpha_nums(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Alpha_numsContext alpha_nums() throws RecognitionException {
		Alpha_numsContext _localctx = new Alpha_numsContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_alpha_nums);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(908);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ALC:
			case BLC:
			case CLC:
			case DLC:
			case ELC:
			case FLC:
			case GLC:
			case HLC:
			case ILC:
			case JLC:
			case KLC:
			case LLC:
			case MLC:
			case NLC:
			case OLC:
			case PLC:
			case QLC:
			case RLC:
			case SLC:
			case TLC:
			case ULC:
			case VLC:
			case WLC:
			case XLC:
			case YLC:
			case ZLC:
			case AUC:
			case BUC:
			case CUC:
			case DUC:
			case EUC:
			case FUC:
			case GUC:
			case HUC:
			case IUC:
			case JUC:
			case KUC:
			case LUC:
			case MUC:
			case NUC:
			case OUC:
			case PUC:
			case QUC:
			case RUC:
			case SUC:
			case TUC:
			case UUC:
			case VUC:
			case WUC:
			case XUC:
			case YUC:
			case ZUC:
				{
				setState(906);
				letter();
				}
				break;
			case Underscore:
				{
				setState(907);
				match(Underscore);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(915);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Underscore) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)))) != 0)) {
				{
				setState(913);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ALC:
				case BLC:
				case CLC:
				case DLC:
				case ELC:
				case FLC:
				case GLC:
				case HLC:
				case ILC:
				case JLC:
				case KLC:
				case LLC:
				case MLC:
				case NLC:
				case OLC:
				case PLC:
				case QLC:
				case RLC:
				case SLC:
				case TLC:
				case ULC:
				case VLC:
				case WLC:
				case XLC:
				case YLC:
				case ZLC:
				case AUC:
				case BUC:
				case CUC:
				case DUC:
				case EUC:
				case FUC:
				case GUC:
				case HUC:
				case IUC:
				case JUC:
				case KUC:
				case LUC:
				case MUC:
				case NUC:
				case OUC:
				case PUC:
				case QUC:
				case RUC:
				case SUC:
				case TUC:
				case UUC:
				case VUC:
				case WUC:
				case XUC:
				case YUC:
				case ZUC:
					{
					setState(910);
					letter();
					}
					break;
				case Underscore:
					{
					setState(911);
					match(Underscore);
					}
					break;
				case D1:
				case D2:
				case D3:
				case D4:
				case D5:
				case D6:
				case D7:
				case D8:
				case D9:
				case D0:
					{
					setState(912);
					digit();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(917);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Non_close_parensContext extends ParserRuleContext {
		public List<Non_close_parenContext> non_close_paren() {
			return getRuleContexts(Non_close_parenContext.class);
		}
		public Non_close_parenContext non_close_paren(int i) {
			return getRuleContext(Non_close_parenContext.class,i);
		}
		public Non_close_parensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_close_parens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterNon_close_parens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitNon_close_parens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitNon_close_parens(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_close_parensContext non_close_parens() throws RecognitionException {
		Non_close_parensContext _localctx = new Non_close_parensContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_non_close_parens);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(919); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(918);
				non_close_paren();
				}
				}
				setState(921); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << OneDataUnit) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << ExtendedUnicodeChar) | (1L << CharacterClassStart) | (1L << CharacterClassEnd) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << NonWordBoundary) | (1L << StartOfSubject) | (1L << EndOfSubjectOrLine) | (1L << EndOfSubjectOrLineEndOfSubject) | (1L << EndOfSubject) | (1L << PreviousMatchInSubject) | (1L << ResetStartMatch) | (1L << SubroutineOrNamedReferenceStartG) | (1L << NamedReferenceStartK) | (1L << Pipe) | (1L << OpenParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Non_close_parenContext extends ParserRuleContext {
		public TerminalNode CloseParen() { return getToken(PythonRegexParser.CloseParen, 0); }
		public Non_close_parenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_close_paren; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterNon_close_paren(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitNon_close_paren(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitNon_close_paren(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_close_parenContext non_close_paren() throws RecognitionException {
		Non_close_parenContext _localctx = new Non_close_parenContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_non_close_paren);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(923);
			_la = _input.LA(1);
			if ( _la <= 0 || (_la==CloseParen) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetterContext extends ParserRuleContext {
		public TerminalNode ALC() { return getToken(PythonRegexParser.ALC, 0); }
		public TerminalNode BLC() { return getToken(PythonRegexParser.BLC, 0); }
		public TerminalNode CLC() { return getToken(PythonRegexParser.CLC, 0); }
		public TerminalNode DLC() { return getToken(PythonRegexParser.DLC, 0); }
		public TerminalNode ELC() { return getToken(PythonRegexParser.ELC, 0); }
		public TerminalNode FLC() { return getToken(PythonRegexParser.FLC, 0); }
		public TerminalNode GLC() { return getToken(PythonRegexParser.GLC, 0); }
		public TerminalNode HLC() { return getToken(PythonRegexParser.HLC, 0); }
		public TerminalNode ILC() { return getToken(PythonRegexParser.ILC, 0); }
		public TerminalNode JLC() { return getToken(PythonRegexParser.JLC, 0); }
		public TerminalNode KLC() { return getToken(PythonRegexParser.KLC, 0); }
		public TerminalNode LLC() { return getToken(PythonRegexParser.LLC, 0); }
		public TerminalNode MLC() { return getToken(PythonRegexParser.MLC, 0); }
		public TerminalNode NLC() { return getToken(PythonRegexParser.NLC, 0); }
		public TerminalNode OLC() { return getToken(PythonRegexParser.OLC, 0); }
		public TerminalNode PLC() { return getToken(PythonRegexParser.PLC, 0); }
		public TerminalNode QLC() { return getToken(PythonRegexParser.QLC, 0); }
		public TerminalNode RLC() { return getToken(PythonRegexParser.RLC, 0); }
		public TerminalNode SLC() { return getToken(PythonRegexParser.SLC, 0); }
		public TerminalNode TLC() { return getToken(PythonRegexParser.TLC, 0); }
		public TerminalNode ULC() { return getToken(PythonRegexParser.ULC, 0); }
		public TerminalNode VLC() { return getToken(PythonRegexParser.VLC, 0); }
		public TerminalNode WLC() { return getToken(PythonRegexParser.WLC, 0); }
		public TerminalNode XLC() { return getToken(PythonRegexParser.XLC, 0); }
		public TerminalNode YLC() { return getToken(PythonRegexParser.YLC, 0); }
		public TerminalNode ZLC() { return getToken(PythonRegexParser.ZLC, 0); }
		public TerminalNode AUC() { return getToken(PythonRegexParser.AUC, 0); }
		public TerminalNode BUC() { return getToken(PythonRegexParser.BUC, 0); }
		public TerminalNode CUC() { return getToken(PythonRegexParser.CUC, 0); }
		public TerminalNode DUC() { return getToken(PythonRegexParser.DUC, 0); }
		public TerminalNode EUC() { return getToken(PythonRegexParser.EUC, 0); }
		public TerminalNode FUC() { return getToken(PythonRegexParser.FUC, 0); }
		public TerminalNode GUC() { return getToken(PythonRegexParser.GUC, 0); }
		public TerminalNode HUC() { return getToken(PythonRegexParser.HUC, 0); }
		public TerminalNode IUC() { return getToken(PythonRegexParser.IUC, 0); }
		public TerminalNode JUC() { return getToken(PythonRegexParser.JUC, 0); }
		public TerminalNode KUC() { return getToken(PythonRegexParser.KUC, 0); }
		public TerminalNode LUC() { return getToken(PythonRegexParser.LUC, 0); }
		public TerminalNode MUC() { return getToken(PythonRegexParser.MUC, 0); }
		public TerminalNode NUC() { return getToken(PythonRegexParser.NUC, 0); }
		public TerminalNode OUC() { return getToken(PythonRegexParser.OUC, 0); }
		public TerminalNode PUC() { return getToken(PythonRegexParser.PUC, 0); }
		public TerminalNode QUC() { return getToken(PythonRegexParser.QUC, 0); }
		public TerminalNode RUC() { return getToken(PythonRegexParser.RUC, 0); }
		public TerminalNode SUC() { return getToken(PythonRegexParser.SUC, 0); }
		public TerminalNode TUC() { return getToken(PythonRegexParser.TUC, 0); }
		public TerminalNode UUC() { return getToken(PythonRegexParser.UUC, 0); }
		public TerminalNode VUC() { return getToken(PythonRegexParser.VUC, 0); }
		public TerminalNode WUC() { return getToken(PythonRegexParser.WUC, 0); }
		public TerminalNode XUC() { return getToken(PythonRegexParser.XUC, 0); }
		public TerminalNode YUC() { return getToken(PythonRegexParser.YUC, 0); }
		public TerminalNode ZUC() { return getToken(PythonRegexParser.ZUC, 0); }
		public LetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).enterLetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PythonRegexListener ) ((PythonRegexListener)listener).exitLetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PythonRegexVisitor ) return ((PythonRegexVisitor<? extends T>)visitor).visitLetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetterContext letter() throws RecognitionException {
		LetterContext _localctx = new LetterContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_letter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(925);
			_la = _input.LA(1);
			if ( !(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & ((1L << (ALC - 61)) | (1L << (BLC - 61)) | (1L << (CLC - 61)) | (1L << (DLC - 61)) | (1L << (ELC - 61)) | (1L << (FLC - 61)) | (1L << (GLC - 61)) | (1L << (HLC - 61)) | (1L << (ILC - 61)) | (1L << (JLC - 61)) | (1L << (KLC - 61)) | (1L << (LLC - 61)) | (1L << (MLC - 61)) | (1L << (NLC - 61)) | (1L << (OLC - 61)) | (1L << (PLC - 61)) | (1L << (QLC - 61)) | (1L << (RLC - 61)) | (1L << (SLC - 61)) | (1L << (TLC - 61)) | (1L << (ULC - 61)) | (1L << (VLC - 61)) | (1L << (WLC - 61)) | (1L << (XLC - 61)) | (1L << (YLC - 61)) | (1L << (ZLC - 61)) | (1L << (AUC - 61)) | (1L << (BUC - 61)) | (1L << (CUC - 61)) | (1L << (DUC - 61)) | (1L << (EUC - 61)) | (1L << (FUC - 61)) | (1L << (GUC - 61)) | (1L << (HUC - 61)) | (1L << (IUC - 61)) | (1L << (JUC - 61)) | (1L << (KUC - 61)) | (1L << (LUC - 61)) | (1L << (MUC - 61)) | (1L << (NUC - 61)) | (1L << (OUC - 61)) | (1L << (PUC - 61)) | (1L << (QUC - 61)) | (1L << (RUC - 61)) | (1L << (SUC - 61)) | (1L << (TUC - 61)) | (1L << (UUC - 61)) | (1L << (VUC - 61)) | (1L << (WUC - 61)) | (1L << (XUC - 61)) | (1L << (YUC - 61)) | (1L << (ZUC - 61)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3}\u03a2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\3\3\3\3\3\7\3S\n\3\f"+
		"\3\16\3V\13\3\3\4\7\4Y\n\4\f\4\16\4\\\13\4\3\5\3\5\5\5`\n\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0080\n\6\3\7\3\7\3\7\5\7"+
		"\u0085\n\7\3\b\3\b\3\b\3\b\3\b\6\b\u008c\n\b\r\b\16\b\u008d\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\7\b\u0096\n\b\f\b\16\b\u0099\13\b\3\b\3\b\3\b\3\b\6\b\u009f"+
		"\n\b\r\b\16\b\u00a0\3\b\3\b\3\b\3\b\3\b\3\b\6\b\u00a9\n\b\r\b\16\b\u00aa"+
		"\3\b\3\b\3\b\3\b\3\b\7\b\u00b2\n\b\f\b\16\b\u00b5\13\b\3\b\3\b\3\b\6\b"+
		"\u00ba\n\b\r\b\16\b\u00bb\3\b\3\b\5\b\u00c0\n\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\5\t\u00eb\n\t\3\n\3\n\3\n\5\n\u00f0\n\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u010f\n\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f"+
		"\u0123\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\5\16\u0161\n\16\3\17\6\17\u0164\n\17\r\17\16"+
		"\17\u0165\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u0184\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22"+
		"\u01d4\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u01de\n\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u01eb\n\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u01f8\n\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0206"+
		"\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u0214\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u0221\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u022d"+
		"\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u023b\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\5\23\u024c\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u025d\n\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0269\n\23\3\23\3\23\5\23\u026d\n"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\5\24\u027e\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0287"+
		"\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\5\24\u02d3\n\24\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25"+
		"\u0312\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u031e"+
		"\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27"+
		"\u033a\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\5\30\u0343\n\30\3\31\3"+
		"\31\3\32\3\32\5\32\u0349\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\5\33\u0357\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\5\34\u0373\n\34\3\35\3\35\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\5\36\u0380\n\36\3\37\3\37\3 \6 \u0385\n \r "+
		"\16 \u0386\3!\3!\3\"\3\"\3#\3#\5#\u038f\n#\3#\3#\3#\7#\u0394\n#\f#\16"+
		"#\u0397\13#\3$\6$\u039a\n$\r$\16$\u039b\3%\3%\3&\3&\3&\2\2\'\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJ\2\t\b\2G"+
		"GKKQQVVbbmm\4\2\6\6\20\35\4\2su||\4\2sy||\3\2s|\3\2\65\65\3\2?r\2\u0426"+
		"\2L\3\2\2\2\4O\3\2\2\2\6Z\3\2\2\2\b]\3\2\2\2\n\177\3\2\2\2\f\u0084\3\2"+
		"\2\2\16\u00bf\3\2\2\2\20\u00ea\3\2\2\2\22\u00ef\3\2\2\2\24\u010e\3\2\2"+
		"\2\26\u0122\3\2\2\2\30\u0124\3\2\2\2\32\u0160\3\2\2\2\34\u0163\3\2\2\2"+
		"\36\u0167\3\2\2\2 \u0183\3\2\2\2\"\u01d3\3\2\2\2$\u026c\3\2\2\2&\u02d2"+
		"\3\2\2\2(\u0311\3\2\2\2*\u031d\3\2\2\2,\u0339\3\2\2\2.\u0342\3\2\2\2\60"+
		"\u0344\3\2\2\2\62\u0348\3\2\2\2\64\u0356\3\2\2\2\66\u0372\3\2\2\28\u0374"+
		"\3\2\2\2:\u037f\3\2\2\2<\u0381\3\2\2\2>\u0384\3\2\2\2@\u0388\3\2\2\2B"+
		"\u038a\3\2\2\2D\u038e\3\2\2\2F\u0399\3\2\2\2H\u039d\3\2\2\2J\u039f\3\2"+
		"\2\2LM\5\4\3\2MN\7\2\2\3N\3\3\2\2\2OT\5\6\4\2PQ\7\63\2\2QS\5\6\4\2RP\3"+
		"\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\5\3\2\2\2VT\3\2\2\2WY\5\b\5\2XW"+
		"\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\7\3\2\2\2\\Z\3\2\2\2]_\5,\27"+
		"\2^`\5\n\6\2_^\3\2\2\2_`\3\2\2\2`\t\3\2\2\2ab\7#\2\2b\u0080\5\f\7\2cd"+
		"\7$\2\2d\u0080\5\f\7\2ef\7%\2\2f\u0080\5\f\7\2gh\7&\2\2hi\7(\2\2ij\58"+
		"\35\2jk\7\'\2\2kl\5\f\7\2l\u0080\3\2\2\2mn\7&\2\2no\58\35\2op\7\'\2\2"+
		"pq\5\f\7\2q\u0080\3\2\2\2rs\7&\2\2st\58\35\2tu\7(\2\2uv\7\'\2\2vw\5\f"+
		"\7\2w\u0080\3\2\2\2xy\7&\2\2yz\58\35\2z{\7(\2\2{|\58\35\2|}\7\'\2\2}~"+
		"\5\f\7\2~\u0080\3\2\2\2\177a\3\2\2\2\177c\3\2\2\2\177e\3\2\2\2\177g\3"+
		"\2\2\2\177m\3\2\2\2\177r\3\2\2\2\177x\3\2\2\2\u0080\13\3\2\2\2\u0081\u0085"+
		"\7$\2\2\u0082\u0085\7#\2\2\u0083\u0085\3\2\2\2\u0084\u0081\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0083\3\2\2\2\u0085\r\3\2\2\2\u0086\u0087\7\37\2"+
		"\2\u0087\u0088\7!\2\2\u0088\u0089\7 \2\2\u0089\u008b\7\"\2\2\u008a\u008c"+
		"\5.\30\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2\2\2\u008d"+
		"\u008e\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\7 \2\2\u0090\u00c0\3\2"+
		"\2\2\u0091\u0092\7\37\2\2\u0092\u0093\7!\2\2\u0093\u0097\7 \2\2\u0094"+
		"\u0096\5.\30\2\u0095\u0094\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2"+
		"\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3\2\2\2\u0099\u0097\3\2\2\2\u009a"+
		"\u00c0\7 \2\2\u009b\u009c\7\37\2\2\u009c\u009e\7!\2\2\u009d\u009f\5.\30"+
		"\2\u009e\u009d\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1"+
		"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\7 \2\2\u00a3\u00c0\3\2\2\2\u00a4"+
		"\u00a5\7\37\2\2\u00a5\u00a6\7 \2\2\u00a6\u00a8\7\"\2\2\u00a7\u00a9\5."+
		"\30\2\u00a8\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa"+
		"\u00ab\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\7 \2\2\u00ad\u00c0\3\2"+
		"\2\2\u00ae\u00af\7\37\2\2\u00af\u00b3\7 \2\2\u00b0\u00b2\5.\30\2\u00b1"+
		"\u00b0\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2"+
		"\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00c0\7 \2\2\u00b7"+
		"\u00b9\7\37\2\2\u00b8\u00ba\5.\30\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3"+
		"\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		"\u00be\7 \2\2\u00be\u00c0\3\2\2\2\u00bf\u0086\3\2\2\2\u00bf\u0091\3\2"+
		"\2\2\u00bf\u009b\3\2\2\2\u00bf\u00a4\3\2\2\2\u00bf\u00ae\3\2\2\2\u00bf"+
		"\u00b7\3\2\2\2\u00c0\17\3\2\2\2\u00c1\u00eb\5\22\n\2\u00c2\u00c3\7\61"+
		"\2\2\u00c3\u00eb\58\35\2\u00c4\u00c5\7\61\2\2\u00c5\u00c6\7&\2\2\u00c6"+
		"\u00c7\58\35\2\u00c7\u00c8\7\'\2\2\u00c8\u00eb\3\2\2\2\u00c9\u00ca\7\61"+
		"\2\2\u00ca\u00cb\7&\2\2\u00cb\u00cc\7\"\2\2\u00cc\u00cd\58\35\2\u00cd"+
		"\u00ce\7\'\2\2\u00ce\u00eb\3\2\2\2\u00cf\u00d0\7\62\2\2\u00d0\u00d1\7"+
		"\66\2\2\u00d1\u00d2\5B\"\2\u00d2\u00d3\7\67\2\2\u00d3\u00eb\3\2\2\2\u00d4"+
		"\u00d5\7\62\2\2\u00d5\u00d6\78\2\2\u00d6\u00d7\5B\"\2\u00d7\u00d8\78\2"+
		"\2\u00d8\u00eb\3\2\2\2\u00d9\u00da\7\61\2\2\u00da\u00db\7&\2\2\u00db\u00dc"+
		"\5B\"\2\u00dc\u00dd\7\'\2\2\u00dd\u00eb\3\2\2\2\u00de\u00df\7\62\2\2\u00df"+
		"\u00e0\7&\2\2\u00e0\u00e1\5B\"\2\u00e1\u00e2\7\'\2\2\u00e2\u00eb\3\2\2"+
		"\2\u00e3\u00e4\7\64\2\2\u00e4\u00e5\7#\2\2\u00e5\u00e6\7h\2\2\u00e6\u00e7"+
		"\7<\2\2\u00e7\u00e8\5B\"\2\u00e8\u00e9\7\65\2\2\u00e9\u00eb\3\2\2\2\u00ea"+
		"\u00c1\3\2\2\2\u00ea\u00c2\3\2\2\2\u00ea\u00c4\3\2\2\2\u00ea\u00c9\3\2"+
		"\2\2\u00ea\u00cf\3\2\2\2\u00ea\u00d4\3\2\2\2\u00ea\u00d9\3\2\2\2\u00ea"+
		"\u00de\3\2\2\2\u00ea\u00e3\3\2\2\2\u00eb\21\3\2\2\2\u00ec\u00f0\5:\36"+
		"\2\u00ed\u00ee\7\f\2\2\u00ee\u00f0\5@!\2\u00ef\u00ec\3\2\2\2\u00ef\u00ed"+
		"\3\2\2\2\u00f0\23\3\2\2\2\u00f1\u00f2\7\64\2\2\u00f2\u00f3\7#\2\2\u00f3"+
		"\u00f4\7\66\2\2\u00f4\u00f5\5B\"\2\u00f5\u00f6\7\67\2\2\u00f6\u00f7\5"+
		"\4\3\2\u00f7\u00f8\7\65\2\2\u00f8\u010f\3\2\2\2\u00f9\u00fa\7\64\2\2\u00fa"+
		"\u00fb\7#\2\2\u00fb\u00fc\78\2\2\u00fc\u00fd\5B\"\2\u00fd\u00fe\78\2\2"+
		"\u00fe\u00ff\5\4\3\2\u00ff\u0100\7\65\2\2\u0100\u010f\3\2\2\2\u0101\u0102"+
		"\7\64\2\2\u0102\u0103\7#\2\2\u0103\u0104\7h\2\2\u0104\u0105\7\66\2\2\u0105"+
		"\u0106\5B\"\2\u0106\u0107\7\67\2\2\u0107\u0108\5\4\3\2\u0108\u0109\7\65"+
		"\2\2\u0109\u010f\3\2\2\2\u010a\u010b\7\64\2\2\u010b\u010c\5\4\3\2\u010c"+
		"\u010d\7\65\2\2\u010d\u010f\3\2\2\2\u010e\u00f1\3\2\2\2\u010e\u00f9\3"+
		"\2\2\2\u010e\u0101\3\2\2\2\u010e\u010a\3\2\2\2\u010f\25\3\2\2\2\u0110"+
		"\u0111\7\64\2\2\u0111\u0112\7#\2\2\u0112\u0113\7:\2\2\u0113\u0114\5\4"+
		"\3\2\u0114\u0115\7\65\2\2\u0115\u0123\3\2\2\2\u0116\u0117\7\64\2\2\u0117"+
		"\u0118\7#\2\2\u0118\u0119\7\63\2\2\u0119\u011a\5\4\3\2\u011a\u011b\7\65"+
		"\2\2\u011b\u0123\3\2\2\2\u011c\u011d\7\64\2\2\u011d\u011e\7#\2\2\u011e"+
		"\u011f\7\67\2\2\u011f\u0120\5\4\3\2\u0120\u0121\7\65\2\2\u0121\u0123\3"+
		"\2\2\2\u0122\u0110\3\2\2\2\u0122\u0116\3\2\2\2\u0122\u011c\3\2\2\2\u0123"+
		"\27\3\2\2\2\u0124\u0125\7\64\2\2\u0125\u0126\7#\2\2\u0126\u0127\7;\2\2"+
		"\u0127\u0128\5F$\2\u0128\u0129\7\65\2\2\u0129\31\3\2\2\2\u012a\u012b\7"+
		"\64\2\2\u012b\u012c\7#\2\2\u012c\u012d\5\34\17\2\u012d\u012e\7\"\2\2\u012e"+
		"\u012f\5\34\17\2\u012f\u0130\7\65\2\2\u0130\u0161\3\2\2\2\u0131\u0132"+
		"\7\64\2\2\u0132\u0133\7#\2\2\u0133\u0134\5\34\17\2\u0134\u0135\7\65\2"+
		"\2\u0135\u0161\3\2\2\2\u0136\u0137\7\64\2\2\u0137\u0138\7#\2\2\u0138\u0139"+
		"\7\"\2\2\u0139\u013a\5\34\17\2\u013a\u013b\7\65\2\2\u013b\u0161\3\2\2"+
		"\2\u013c\u013d\7\64\2\2\u013d\u013e\7%\2\2\u013e\u013f\7f\2\2\u013f\u0140"+
		"\7g\2\2\u0140\u0141\79\2\2\u0141\u0142\7k\2\2\u0142\u0143\7l\2\2\u0143"+
		"\u0144\7Y\2\2\u0144\u0145\7j\2\2\u0145\u0146\7l\2\2\u0146\u0147\79\2\2"+
		"\u0147\u0148\7g\2\2\u0148\u0149\7h\2\2\u0149\u014a\7l\2\2\u014a\u0161"+
		"\7\65\2\2\u014b\u014c\7\64\2\2\u014c\u014d\7%\2\2\u014d\u014e\7m\2\2\u014e"+
		"\u014f\7l\2\2\u014f\u0150\7^\2\2\u0150\u0151\7z\2\2\u0151\u0161\7\65\2"+
		"\2\u0152\u0153\7\64\2\2\u0153\u0154\7%\2\2\u0154\u0155\7m\2\2\u0155\u0156"+
		"\7l\2\2\u0156\u0157\7^\2\2\u0157\u0158\7s\2\2\u0158\u0159\7x\2\2\u0159"+
		"\u0161\7\65\2\2\u015a\u015b\7\64\2\2\u015b\u015c\7%\2\2\u015c\u015d\7"+
		"m\2\2\u015d\u015e\7[\2\2\u015e\u015f\7h\2\2\u015f\u0161\7\65\2\2\u0160"+
		"\u012a\3\2\2\2\u0160\u0131\3\2\2\2\u0160\u0136\3\2\2\2\u0160\u013c\3\2"+
		"\2\2\u0160\u014b\3\2\2\2\u0160\u0152\3\2\2\2\u0160\u015a\3\2\2\2\u0161"+
		"\33\3\2\2\2\u0162\u0164\5\36\20\2\u0163\u0162\3\2\2\2\u0164\u0165\3\2"+
		"\2\2\u0165\u0163\3\2\2\2\u0165\u0166\3\2\2\2\u0166\35\3\2\2\2\u0167\u0168"+
		"\t\2\2\2\u0168\37\3\2\2\2\u0169\u016a\7\64\2\2\u016a\u016b\7#\2\2\u016b"+
		"\u016c\7<\2\2\u016c\u016d\5\4\3\2\u016d\u016e\7\65\2\2\u016e\u0184\3\2"+
		"\2\2\u016f\u0170\7\64\2\2\u0170\u0171\7#\2\2\u0171\u0172\7=\2\2\u0172"+
		"\u0173\5\4\3\2\u0173\u0174\7\65\2\2\u0174\u0184\3\2\2\2\u0175\u0176\7"+
		"\64\2\2\u0176\u0177\7#\2\2\u0177\u0178\7\66\2\2\u0178\u0179\7<\2\2\u0179"+
		"\u017a\5\4\3\2\u017a\u017b\7\65\2\2\u017b\u0184\3\2\2\2\u017c\u017d\7"+
		"\64\2\2\u017d\u017e\7#\2\2\u017e\u017f\7\66\2\2\u017f\u0180\7=\2\2\u0180"+
		"\u0181\5\4\3\2\u0181\u0182\7\65\2\2\u0182\u0184\3\2\2\2\u0183\u0169\3"+
		"\2\2\2\u0183\u016f\3\2\2\2\u0183\u0175\3\2\2\2\u0183\u017c\3\2\2\2\u0184"+
		"!\3\2\2\2\u0185\u0186\7\64\2\2\u0186\u0187\7#\2\2\u0187\u0188\7j\2\2\u0188"+
		"\u01d4\7\65\2\2\u0189\u018a\7\64\2\2\u018a\u018b\7#\2\2\u018b\u018c\5"+
		"8\35\2\u018c\u018d\7\65\2\2\u018d\u01d4\3\2\2\2\u018e\u018f\7\64\2\2\u018f"+
		"\u0190\7#\2\2\u0190\u0191\7$\2\2\u0191\u0192\58\35\2\u0192\u0193\7\65"+
		"\2\2\u0193\u01d4\3\2\2\2\u0194\u0195\7\64\2\2\u0195\u0196\7#\2\2\u0196"+
		"\u0197\7\"\2\2\u0197\u0198\58\35\2\u0198\u0199\7\65\2\2\u0199\u01d4\3"+
		"\2\2\2\u019a\u019b\7\64\2\2\u019b\u019c\7#\2\2\u019c\u019d\7>\2\2\u019d"+
		"\u019e\5B\"\2\u019e\u019f\7\65\2\2\u019f\u01d4\3\2\2\2\u01a0\u01a1\7\64"+
		"\2\2\u01a1\u01a2\7#\2\2\u01a2\u01a3\7h\2\2\u01a3\u01a4\7\67\2\2\u01a4"+
		"\u01a5\5B\"\2\u01a5\u01a6\7\65\2\2\u01a6\u01d4\3\2\2\2\u01a7\u01a8\7\61"+
		"\2\2\u01a8\u01a9\7\66\2\2\u01a9\u01aa\5B\"\2\u01aa\u01ab\7\67\2\2\u01ab"+
		"\u01d4\3\2\2\2\u01ac\u01ad\7\61\2\2\u01ad\u01ae\78\2\2\u01ae\u01af\5B"+
		"\"\2\u01af\u01b0\78\2\2\u01b0\u01d4\3\2\2\2\u01b1\u01b2\7\61\2\2\u01b2"+
		"\u01b3\7\66\2\2\u01b3\u01b4\58\35\2\u01b4\u01b5\7\67\2\2\u01b5\u01d4\3"+
		"\2\2\2\u01b6\u01b7\7\61\2\2\u01b7\u01b8\78\2\2\u01b8\u01b9\58\35\2\u01b9"+
		"\u01ba\78\2\2\u01ba\u01d4\3\2\2\2\u01bb\u01bc\7\61\2\2\u01bc\u01bd\7\66"+
		"\2\2\u01bd\u01be\7$\2\2\u01be\u01bf\58\35\2\u01bf\u01c0\7\67\2\2\u01c0"+
		"\u01d4\3\2\2\2\u01c1\u01c2\7\61\2\2\u01c2\u01c3\78\2\2\u01c3\u01c4\7$"+
		"\2\2\u01c4\u01c5\58\35\2\u01c5\u01c6\78\2\2\u01c6\u01d4\3\2\2\2\u01c7"+
		"\u01c8\7\61\2\2\u01c8\u01c9\7\66\2\2\u01c9\u01ca\7\"\2\2\u01ca\u01cb\5"+
		"8\35\2\u01cb\u01cc\7\67\2\2\u01cc\u01d4\3\2\2\2\u01cd\u01ce\7\61\2\2\u01ce"+
		"\u01cf\78\2\2\u01cf\u01d0\7\"\2\2\u01d0\u01d1\58\35\2\u01d1\u01d2\78\2"+
		"\2\u01d2\u01d4\3\2\2\2\u01d3\u0185\3\2\2\2\u01d3\u0189\3\2\2\2\u01d3\u018e"+
		"\3\2\2\2\u01d3\u0194\3\2\2\2\u01d3\u019a\3\2\2\2\u01d3\u01a0\3\2\2\2\u01d3"+
		"\u01a7\3\2\2\2\u01d3\u01ac\3\2\2\2\u01d3\u01b1\3\2\2\2\u01d3\u01b6\3\2"+
		"\2\2\u01d3\u01bb\3\2\2\2\u01d3\u01c1\3\2\2\2\u01d3\u01c7\3\2\2\2\u01d3"+
		"\u01cd\3\2\2\2\u01d4#\3\2\2\2\u01d5\u01d6\7\64\2\2\u01d6\u01d7\7#\2\2"+
		"\u01d7\u01d8\7\64\2\2\u01d8\u01d9\58\35\2\u01d9\u01da\7\65\2\2\u01da\u01dd"+
		"\5\4\3\2\u01db\u01dc\7\63\2\2\u01dc\u01de\5\4\3\2\u01dd\u01db\3\2\2\2"+
		"\u01dd\u01de\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e0\7\65\2\2\u01e0\u026d"+
		"\3\2\2\2\u01e1\u01e2\7\64\2\2\u01e2\u01e3\7#\2\2\u01e3\u01e4\7\64\2\2"+
		"\u01e4\u01e5\7$\2\2\u01e5\u01e6\58\35\2\u01e6\u01e7\7\65\2\2\u01e7\u01ea"+
		"\5\4\3\2\u01e8\u01e9\7\63\2\2\u01e9\u01eb\5\4\3\2\u01ea\u01e8\3\2\2\2"+
		"\u01ea\u01eb\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ed\7\65\2\2\u01ed\u026d"+
		"\3\2\2\2\u01ee\u01ef\7\64\2\2\u01ef\u01f0\7#\2\2\u01f0\u01f1\7\64\2\2"+
		"\u01f1\u01f2\7\"\2\2\u01f2\u01f3\58\35\2\u01f3\u01f4\7\65\2\2\u01f4\u01f7"+
		"\5\4\3\2\u01f5\u01f6\7\63\2\2\u01f6\u01f8\5\4\3\2\u01f7\u01f5\3\2\2\2"+
		"\u01f7\u01f8\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01fa\7\65\2\2\u01fa\u026d"+
		"\3\2\2\2\u01fb\u01fc\7\64\2\2\u01fc\u01fd\7#\2\2\u01fd\u01fe\7\64\2\2"+
		"\u01fe\u01ff\7\66\2\2\u01ff\u0200\5B\"\2\u0200\u0201\7\67\2\2\u0201\u0202"+
		"\7\65\2\2\u0202\u0205\5\4\3\2\u0203\u0204\7\63\2\2\u0204\u0206\5\4\3\2"+
		"\u0205\u0203\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0207\3\2\2\2\u0207\u0208"+
		"\7\65\2\2\u0208\u026d\3\2\2\2\u0209\u020a\7\64\2\2\u020a\u020b\7#\2\2"+
		"\u020b\u020c\7\64\2\2\u020c\u020d\78\2\2\u020d\u020e\5B\"\2\u020e\u020f"+
		"\78\2\2\u020f\u0210\7\65\2\2\u0210\u0213\5\4\3\2\u0211\u0212\7\63\2\2"+
		"\u0212\u0214\5\4\3\2\u0213\u0211\3\2\2\2\u0213\u0214\3\2\2\2\u0214\u0215"+
		"\3\2\2\2\u0215\u0216\7\65\2\2\u0216\u026d\3\2\2\2\u0217\u0218\7\64\2\2"+
		"\u0218\u0219\7#\2\2\u0219\u021a\7\64\2\2\u021a\u021b\7j\2\2\u021b\u021c"+
		"\58\35\2\u021c\u021d\7\65\2\2\u021d\u0220\5\4\3\2\u021e\u021f\7\63\2\2"+
		"\u021f\u0221\5\4\3\2\u0220\u021e\3\2\2\2\u0220\u0221\3\2\2\2\u0221\u0222"+
		"\3\2\2\2\u0222\u0223\7\65\2\2\u0223\u026d\3\2\2\2\u0224\u0225\7\64\2\2"+
		"\u0225\u0226\7#\2\2\u0226\u0227\7\64\2\2\u0227\u0228\7j\2\2\u0228\u0229"+
		"\7\65\2\2\u0229\u022c\5\4\3\2\u022a\u022b\7\63\2\2\u022b\u022d\5\4\3\2"+
		"\u022c\u022a\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u022e\3\2\2\2\u022e\u022f"+
		"\7\65\2\2\u022f\u026d\3\2\2\2\u0230\u0231\7\64\2\2\u0231\u0232\7#\2\2"+
		"\u0232\u0233\7\64\2\2\u0233\u0234\7j\2\2\u0234\u0235\7>\2\2\u0235\u0236"+
		"\5B\"\2\u0236\u0237\7\65\2\2\u0237\u023a\5\4\3\2\u0238\u0239\7\63\2\2"+
		"\u0239\u023b\5\4\3\2\u023a\u0238\3\2\2\2\u023a\u023b\3\2\2\2\u023b\u023c"+
		"\3\2\2\2\u023c\u023d\7\65\2\2\u023d\u026d\3\2\2\2\u023e\u023f\7\64\2\2"+
		"\u023f\u0240\7#\2\2\u0240\u0241\7\64\2\2\u0241\u0242\7\\\2\2\u0242\u0243"+
		"\7]\2\2\u0243\u0244\7^\2\2\u0244\u0245\7a\2\2\u0245\u0246\7f\2\2\u0246"+
		"\u0247\7]\2\2\u0247\u0248\7\65\2\2\u0248\u024b\5\4\3\2\u0249\u024a\7\63"+
		"\2\2\u024a\u024c\5\4\3\2\u024b\u0249\3\2\2\2\u024b\u024c\3\2\2\2\u024c"+
		"\u024d\3\2\2\2\u024d\u024e\7\65\2\2\u024e\u026d\3\2\2\2\u024f\u0250\7"+
		"\64\2\2\u0250\u0251\7#\2\2\u0251\u0252\7\64\2\2\u0252\u0253\7?\2\2\u0253"+
		"\u0254\7Q\2\2\u0254\u0255\7Q\2\2\u0255\u0256\7C\2\2\u0256\u0257\7P\2\2"+
		"\u0257\u0258\7R\2\2\u0258\u0259\7\65\2\2\u0259\u025c\5\4\3\2\u025a\u025b"+
		"\7\63\2\2\u025b\u025d\5\4\3\2\u025c\u025a\3\2\2\2\u025c\u025d\3\2\2\2"+
		"\u025d\u025e\3\2\2\2\u025e\u025f\7\65\2\2\u025f\u026d\3\2\2\2\u0260\u0261"+
		"\7\64\2\2\u0261\u0262\7#\2\2\u0262\u0263\7\64\2\2\u0263\u0264\5B\"\2\u0264"+
		"\u0265\7\65\2\2\u0265\u0268\5\4\3\2\u0266\u0267\7\63\2\2\u0267\u0269\5"+
		"\4\3\2\u0268\u0266\3\2\2\2\u0268\u0269\3\2\2\2\u0269\u026a\3\2\2\2\u026a"+
		"\u026b\7\65\2\2\u026b\u026d\3\2\2\2\u026c\u01d5\3\2\2\2\u026c\u01e1\3"+
		"\2\2\2\u026c\u01ee\3\2\2\2\u026c\u01fb\3\2\2\2\u026c\u0209\3\2\2\2\u026c"+
		"\u0217\3\2\2\2\u026c\u0224\3\2\2\2\u026c\u0230\3\2\2\2\u026c\u023e\3\2"+
		"\2\2\u026c\u024f\3\2\2\2\u026c\u0260\3\2\2\2\u026d%\3\2\2\2\u026e\u026f"+
		"\7\64\2\2\u026f\u0270\7%\2\2\u0270\u0271\7Y\2\2\u0271\u0272\7[\2\2\u0272"+
		"\u0273\7[\2\2\u0273\u0274\7]\2\2\u0274\u0275\7h\2\2\u0275\u0276\7l\2\2"+
		"\u0276\u02d3\7\65\2\2\u0277\u0278\7\64\2\2\u0278\u0279\7%\2\2\u0279\u027d"+
		"\7^\2\2\u027a\u027b\7Y\2\2\u027b\u027c\7a\2\2\u027c\u027e\7d\2\2\u027d"+
		"\u027a\3\2\2\2\u027d\u027e\3\2\2\2\u027e\u027f\3\2\2\2\u027f\u02d3\7\65"+
		"\2\2\u0280\u0281\7\64\2\2\u0281\u0286\7%\2\2\u0282\u0283\7e\2\2\u0283"+
		"\u0284\7Y\2\2\u0284\u0285\7j\2\2\u0285\u0287\7c\2\2\u0286\u0282\3\2\2"+
		"\2\u0286\u0287\3\2\2\2\u0287\u0288\3\2\2\2\u0288\u0289\7:\2\2\u0289\u028a"+
		"\7f\2\2\u028a\u028b\7Y\2\2\u028b\u028c\7e\2\2\u028c\u028d\7]\2\2\u028d"+
		"\u02d3\7\65\2\2\u028e\u028f\7\64\2\2\u028f\u0290\7%\2\2\u0290\u0291\7"+
		"[\2\2\u0291\u0292\7g\2\2\u0292\u0293\7e\2\2\u0293\u0294\7e\2\2\u0294\u0295"+
		"\7a\2\2\u0295\u0296\7l\2\2\u0296\u02d3\7\65\2\2\u0297\u0298\7\64\2\2\u0298"+
		"\u0299\7%\2\2\u0299\u029a\7h\2\2\u029a\u029b\7j\2\2\u029b\u029c\7m\2\2"+
		"\u029c\u029d\7f\2\2\u029d\u029e\7]\2\2\u029e\u02d3\7\65\2\2\u029f\u02a0"+
		"\7\64\2\2\u02a0\u02a1\7%\2\2\u02a1\u02a2\7h\2\2\u02a2\u02a3\7j\2\2\u02a3"+
		"\u02a4\7m\2\2\u02a4\u02a5\7f\2\2\u02a5\u02a6\7]\2\2\u02a6\u02a7\7:\2\2"+
		"\u02a7\u02a8\7f\2\2\u02a8\u02a9\7Y\2\2\u02a9\u02aa\7e\2\2\u02aa\u02ab"+
		"\7]\2\2\u02ab\u02d3\7\65\2\2\u02ac\u02ad\7\64\2\2\u02ad\u02ae\7%\2\2\u02ae"+
		"\u02af\7k\2\2\u02af\u02b0\7c\2\2\u02b0\u02b1\7a\2\2\u02b1\u02b2\7h\2\2"+
		"\u02b2\u02d3\7\65\2\2\u02b3\u02b4\7\64\2\2\u02b4\u02b5\7%\2\2\u02b5\u02b6"+
		"\7k\2\2\u02b6\u02b7\7c\2\2\u02b7\u02b8\7a\2\2\u02b8\u02b9\7h\2\2\u02b9"+
		"\u02ba\7:\2\2\u02ba\u02bb\7f\2\2\u02bb\u02bc\7Y\2\2\u02bc\u02bd\7e\2\2"+
		"\u02bd\u02be\7]\2\2\u02be\u02d3\7\65\2\2\u02bf\u02c0\7\64\2\2\u02c0\u02c1"+
		"\7%\2\2\u02c1\u02c2\7l\2\2\u02c2\u02c3\7`\2\2\u02c3\u02c4\7]\2\2\u02c4"+
		"\u02c5\7f\2\2\u02c5\u02d3\7\65\2\2\u02c6\u02c7\7\64\2\2\u02c7\u02c8\7"+
		"%\2\2\u02c8\u02c9\7l\2\2\u02c9\u02ca\7`\2\2\u02ca\u02cb\7]\2\2\u02cb\u02cc"+
		"\7f\2\2\u02cc\u02cd\7:\2\2\u02cd\u02ce\7f\2\2\u02ce\u02cf\7Y\2\2\u02cf"+
		"\u02d0\7e\2\2\u02d0\u02d1\7]\2\2\u02d1\u02d3\7\65\2\2\u02d2\u026e\3\2"+
		"\2\2\u02d2\u0277\3\2\2\2\u02d2\u0280\3\2\2\2\u02d2\u028e\3\2\2\2\u02d2"+
		"\u0297\3\2\2\2\u02d2\u029f\3\2\2\2\u02d2\u02ac\3\2\2\2\u02d2\u02b3\3\2"+
		"\2\2\u02d2\u02bf\3\2\2\2\u02d2\u02c6\3\2\2\2\u02d3\'\3\2\2\2\u02d4\u02d5"+
		"\7\64\2\2\u02d5\u02d6\7%\2\2\u02d6\u02d7\7[\2\2\u02d7\u02d8\7j\2\2\u02d8"+
		"\u0312\7\65\2\2\u02d9\u02da\7\64\2\2\u02da\u02db\7%\2\2\u02db\u02dc\7"+
		"d\2\2\u02dc\u02dd\7^\2\2\u02dd\u0312\7\65\2\2\u02de\u02df\7\64\2\2\u02df"+
		"\u02e0\7%\2\2\u02e0\u02e1\7[\2\2\u02e1\u02e2\7j\2\2\u02e2\u02e3\7d\2\2"+
		"\u02e3\u02e4\7^\2\2\u02e4\u0312\7\65\2\2\u02e5\u02e6\7\64\2\2\u02e6\u02e7"+
		"\7%\2\2\u02e7\u02e8\7Y\2\2\u02e8\u02e9\7f\2\2\u02e9\u02ea\7q\2\2\u02ea"+
		"\u02eb\7[\2\2\u02eb\u02ec\7j\2\2\u02ec\u02ed\7d\2\2\u02ed\u02ee\7^\2\2"+
		"\u02ee\u0312\7\65\2\2\u02ef\u02f0\7\64\2\2\u02f0\u02f1\7%\2\2\u02f1\u02f2"+
		"\7Y\2\2\u02f2\u02f3\7f\2\2\u02f3\u02f4\7q\2\2\u02f4\u0312\7\65\2\2\u02f5"+
		"\u02f6\7\64\2\2\u02f6\u02f7\7%\2\2\u02f7\u02f8\7Z\2\2\u02f8\u02f9\7k\2"+
		"\2\u02f9\u02fa\7j\2\2\u02fa\u02fb\79\2\2\u02fb\u02fc\7Y\2\2\u02fc\u02fd"+
		"\7f\2\2\u02fd\u02fe\7q\2\2\u02fe\u02ff\7[\2\2\u02ff\u0300\7j\2\2\u0300"+
		"\u0301\7d\2\2\u0301\u0302\7^\2\2\u0302\u0312\7\65\2\2\u0303\u0304\7\64"+
		"\2\2\u0304\u0305\7%\2\2\u0305\u0306\7Z\2\2\u0306\u0307\7k\2\2\u0307\u0308"+
		"\7j\2\2\u0308\u0309\79\2\2\u0309\u030a\7m\2\2\u030a\u030b\7f\2\2\u030b"+
		"\u030c\7a\2\2\u030c\u030d\7[\2\2\u030d\u030e\7g\2\2\u030e\u030f\7\\\2"+
		"\2\u030f\u0310\7]\2\2\u0310\u0312\7\65\2\2\u0311\u02d4\3\2\2\2\u0311\u02d9"+
		"\3\2\2\2\u0311\u02de\3\2\2\2\u0311\u02e5\3\2\2\2\u0311\u02ef\3\2\2\2\u0311"+
		"\u02f5\3\2\2\2\u0311\u0303\3\2\2\2\u0312)\3\2\2\2\u0313\u0314\7\64\2\2"+
		"\u0314\u0315\7#\2\2\u0315\u0316\7[\2\2\u0316\u031e\7\65\2\2\u0317\u0318"+
		"\7\64\2\2\u0318\u0319\7#\2\2\u0319\u031a\7[\2\2\u031a\u031b\58\35\2\u031b"+
		"\u031c\7\65\2\2\u031c\u031e\3\2\2\2\u031d\u0313\3\2\2\2\u031d\u0317\3"+
		"\2\2\2\u031e+\3\2\2\2\u031f\u033a\5\"\22\2\u0320\u033a\5\60\31\2\u0321"+
		"\u033a\5\62\32\2\u0322\u033a\5\16\b\2\u0323\u033a\5\24\13\2\u0324\u033a"+
		"\5\26\f\2\u0325\u033a\5\30\r\2\u0326\u033a\5\32\16\2\u0327\u033a\5 \21"+
		"\2\u0328\u033a\5\20\t\2\u0329\u033a\5$\23\2\u032a\u033a\5&\24\2\u032b"+
		"\u033a\5(\25\2\u032c\u033a\5*\26\2\u032d\u033a\7\16\2\2\u032e\u033a\7"+
		"!\2\2\u032f\u033a\7+\2\2\u0330\u033a\7)\2\2\u0331\u033a\7*\2\2\u0332\u033a"+
		"\7,\2\2\u0333\u033a\7-\2\2\u0334\u033a\7.\2\2\u0335\u033a\7/\2\2\u0336"+
		"\u033a\7\60\2\2\u0337\u033a\7\17\2\2\u0338\u033a\7\36\2\2\u0339\u031f"+
		"\3\2\2\2\u0339\u0320\3\2\2\2\u0339\u0321\3\2\2\2\u0339\u0322\3\2\2\2\u0339"+
		"\u0323\3\2\2\2\u0339\u0324\3\2\2\2\u0339\u0325\3\2\2\2\u0339\u0326\3\2"+
		"\2\2\u0339\u0327\3\2\2\2\u0339\u0328\3\2\2\2\u0339\u0329\3\2\2\2\u0339"+
		"\u032a\3\2\2\2\u0339\u032b\3\2\2\2\u0339\u032c\3\2\2\2\u0339\u032d\3\2"+
		"\2\2\u0339\u032e\3\2\2\2\u0339\u032f\3\2\2\2\u0339\u0330\3\2\2\2\u0339"+
		"\u0331\3\2\2\2\u0339\u0332\3\2\2\2\u0339\u0333\3\2\2\2\u0339\u0334\3\2"+
		"\2\2\u0339\u0335\3\2\2\2\u0339\u0336\3\2\2\2\u0339\u0337\3\2\2\2\u0339"+
		"\u0338\3\2\2\2\u033a-\3\2\2\2\u033b\u033c\5\64\33\2\u033c\u033d\7\"\2"+
		"\2\u033d\u033e\5\64\33\2\u033e\u0343\3\2\2\2\u033f\u0343\5\60\31\2\u0340"+
		"\u0343\5\64\33\2\u0341\u0343\5\22\n\2\u0342\u033b\3\2\2\2\u0342\u033f"+
		"\3\2\2\2\u0342\u0340\3\2\2\2\u0342\u0341\3\2\2\2\u0343/\3\2\2\2\u0344"+
		"\u0345\t\3\2\2\u0345\61\3\2\2\2\u0346\u0349\5\66\34\2\u0347\u0349\7 \2"+
		"\2\u0348\u0346\3\2\2\2\u0348\u0347\3\2\2\2\u0349\63\3\2\2\2\u034a\u0357"+
		"\5\66\34\2\u034b\u0357\7\16\2\2\u034c\u0357\7\37\2\2\u034d\u0357\7!\2"+
		"\2\u034e\u0357\7#\2\2\u034f\u0357\7$\2\2\u0350\u0357\7%\2\2\u0351\u0357"+
		"\7)\2\2\u0352\u0357\7,\2\2\u0353\u0357\7\63\2\2\u0354\u0357\7\64\2\2\u0355"+
		"\u0357\7\65\2\2\u0356\u034a\3\2\2\2\u0356\u034b\3\2\2\2\u0356\u034c\3"+
		"\2\2\2\u0356\u034d\3\2\2\2\u0356\u034e\3\2\2\2\u0356\u034f\3\2\2\2\u0356"+
		"\u0350\3\2\2\2\u0356\u0351\3\2\2\2\u0356\u0352\3\2\2\2\u0356\u0353\3\2"+
		"\2\2\u0356\u0354\3\2\2\2\u0356\u0355\3\2\2\2\u0357\65\3\2\2\2\u0358\u0373"+
		"\5:\36\2\u0359\u0373\5J&\2\u035a\u0373\5@!\2\u035b\u0373\7\5\2\2\u035c"+
		"\u0373\7\7\2\2\u035d\u0373\7\b\2\2\u035e\u0373\7\t\2\2\u035f\u0373\7\n"+
		"\2\2\u0360\u0373\7\13\2\2\u0361\u0373\7\r\2\2\u0362\u0373\7\3\2\2\u0363"+
		"\u0373\7\4\2\2\u0364\u0373\7&\2\2\u0365\u0373\7\'\2\2\u0366\u0373\7(\2"+
		"\2\u0367\u0373\7\"\2\2\u0368\u0373\7\66\2\2\u0369\u0373\7\67\2\2\u036a"+
		"\u0373\78\2\2\u036b\u0373\79\2\2\u036c\u0373\7:\2\2\u036d\u0373\7;\2\2"+
		"\u036e\u0373\7<\2\2\u036f\u0373\7=\2\2\u0370\u0373\7>\2\2\u0371\u0373"+
		"\7}\2\2\u0372\u0358\3\2\2\2\u0372\u0359\3\2\2\2\u0372\u035a\3\2\2\2\u0372"+
		"\u035b\3\2\2\2\u0372\u035c\3\2\2\2\u0372\u035d\3\2\2\2\u0372\u035e\3\2"+
		"\2\2\u0372\u035f\3\2\2\2\u0372\u0360\3\2\2\2\u0372\u0361\3\2\2\2\u0372"+
		"\u0362\3\2\2\2\u0372\u0363\3\2\2\2\u0372\u0364\3\2\2\2\u0372\u0365\3\2"+
		"\2\2\u0372\u0366\3\2\2\2\u0372\u0367\3\2\2\2\u0372\u0368\3\2\2\2\u0372"+
		"\u0369\3\2\2\2\u0372\u036a\3\2\2\2\u0372\u036b\3\2\2\2\u0372\u036c\3\2"+
		"\2\2\u0372\u036d\3\2\2\2\u0372\u036e\3\2\2\2\u0372\u036f\3\2\2\2\u0372"+
		"\u0370\3\2\2\2\u0372\u0371\3\2\2\2\u0373\67\3\2\2\2\u0374\u0375\5> \2"+
		"\u03759\3\2\2\2\u0376\u0377\7\f\2\2\u0377\u0378\t\4\2\2\u0378\u0379\5"+
		"<\37\2\u0379\u037a\5<\37\2\u037a\u0380\3\2\2\2\u037b\u037c\7\f\2\2\u037c"+
		"\u037d\5<\37\2\u037d\u037e\5<\37\2\u037e\u0380\3\2\2\2\u037f\u0376\3\2"+
		"\2\2\u037f\u037b\3\2\2\2\u0380;\3\2\2\2\u0381\u0382\t\5\2\2\u0382=\3\2"+
		"\2\2\u0383\u0385\5@!\2\u0384\u0383\3\2\2\2\u0385\u0386\3\2\2\2\u0386\u0384"+
		"\3\2\2\2\u0386\u0387\3\2\2\2\u0387?\3\2\2\2\u0388\u0389\t\6\2\2\u0389"+
		"A\3\2\2\2\u038a\u038b\5D#\2\u038bC\3\2\2\2\u038c\u038f\5J&\2\u038d\u038f"+
		"\79\2\2\u038e\u038c\3\2\2\2\u038e\u038d\3\2\2\2\u038f\u0395\3\2\2\2\u0390"+
		"\u0394\5J&\2\u0391\u0394\79\2\2\u0392\u0394\5@!\2\u0393\u0390\3\2\2\2"+
		"\u0393\u0391\3\2\2\2\u0393\u0392\3\2\2\2\u0394\u0397\3\2\2\2\u0395\u0393"+
		"\3\2\2\2\u0395\u0396\3\2\2\2\u0396E\3\2\2\2\u0397\u0395\3\2\2\2\u0398"+
		"\u039a\5H%\2\u0399\u0398\3\2\2\2\u039a\u039b\3\2\2\2\u039b\u0399\3\2\2"+
		"\2\u039b\u039c\3\2\2\2\u039cG\3\2\2\2\u039d\u039e\n\7\2\2\u039eI\3\2\2"+
		"\2\u039f\u03a0\t\b\2\2\u03a0K\3\2\2\2\62TZ_\177\u0084\u008d\u0097\u00a0"+
		"\u00aa\u00b3\u00bb\u00bf\u00ea\u00ef\u010e\u0122\u0160\u0165\u0183\u01d3"+
		"\u01dd\u01ea\u01f7\u0205\u0213\u0220\u022c\u023a\u024b\u025c\u0268\u026c"+
		"\u027d\u0286\u02d2\u0311\u031d\u0339\u0342\u0348\u0356\u0372\u037f\u0386"+
		"\u038e\u0393\u0395\u039b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}