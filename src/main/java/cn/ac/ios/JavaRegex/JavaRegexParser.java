// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/JavaRegex\JavaRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.JavaRegex;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JavaRegexParser extends Parser {
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
		RULE_character_class_intersection = 7, RULE_backreference = 8, RULE_backreference_or_octal = 9, 
		RULE_capture = 10, RULE_non_capture = 11, RULE_comment = 12, RULE_option = 13, 
		RULE_option_flags = 14, RULE_option_flag = 15, RULE_look_around = 16, 
		RULE_subroutine_reference = 17, RULE_conditional = 18, RULE_backtrack_control = 19, 
		RULE_newline_convention = 20, RULE_callout = 21, RULE_atom = 22, RULE_cc_atom = 23, 
		RULE_shared_atom = 24, RULE_literal = 25, RULE_cc_literal = 26, RULE_shared_literal = 27, 
		RULE_number = 28, RULE_octal_char = 29, RULE_octal_digit = 30, RULE_digits = 31, 
		RULE_digit = 32, RULE_name = 33, RULE_alpha_nums = 34, RULE_non_close_parens = 35, 
		RULE_non_close_paren = 36, RULE_letter = 37;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "alternation", "expr", "element", "quantifier", "quantifier_type", 
			"character_class", "character_class_intersection", "backreference", "backreference_or_octal", 
			"capture", "non_capture", "comment", "option", "option_flags", "option_flag", 
			"look_around", "subroutine_reference", "conditional", "backtrack_control", 
			"newline_convention", "callout", "atom", "cc_atom", "shared_atom", "literal", 
			"cc_literal", "shared_literal", "number", "octal_char", "octal_digit", 
			"digits", "digit", "name", "alpha_nums", "non_close_parens", "non_close_paren", 
			"letter"
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
	public String getGrammarFileName() { return "JavaRegex.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JavaRegexParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(JavaRegexParser.EOF, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			alternation();
			setState(77);
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
		public List<TerminalNode> Pipe() { return getTokens(JavaRegexParser.Pipe); }
		public TerminalNode Pipe(int i) {
			return getToken(JavaRegexParser.Pipe, i);
		}
		public AlternationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterAlternation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitAlternation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitAlternation(this);
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
			setState(79);
			expr();
			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(80);
					match(Pipe);
					setState(81);
					expr();
					}
					} 
				}
				setState(86);
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitExpr(this);
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
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << OneDataUnit) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << ExtendedUnicodeChar) | (1L << CharacterClassStart) | (1L << CharacterClassEnd) | (1L << Caret) | (1L << Hyphen) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << NonWordBoundary) | (1L << StartOfSubject) | (1L << EndOfSubjectOrLine) | (1L << EndOfSubjectOrLineEndOfSubject) | (1L << EndOfSubject) | (1L << PreviousMatchInSubject) | (1L << ResetStartMatch) | (1L << SubroutineOrNamedReferenceStartG) | (1L << NamedReferenceStartK) | (1L << OpenParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0)) {
				{
				{
				setState(87);
				element();
				}
				}
				setState(92);
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_element);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			atom();
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				setState(94);
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
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public Quantifier_typeContext quantifier_type() {
			return getRuleContext(Quantifier_typeContext.class,0);
		}
		public TerminalNode Plus() { return getToken(JavaRegexParser.Plus, 0); }
		public TerminalNode Star() { return getToken(JavaRegexParser.Star, 0); }
		public TerminalNode OpenBrace() { return getToken(JavaRegexParser.OpenBrace, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(JavaRegexParser.CloseBrace, 0); }
		public TerminalNode Comma() { return getToken(JavaRegexParser.Comma, 0); }
		public QuantifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterQuantifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitQuantifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitQuantifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuantifierContext quantifier() throws RecognitionException {
		QuantifierContext _localctx = new QuantifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_quantifier);
		try {
			setState(121);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(97);
				match(QuestionMark);
				setState(98);
				quantifier_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				match(Plus);
				setState(100);
				quantifier_type();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(101);
				match(Star);
				setState(102);
				quantifier_type();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(103);
				match(OpenBrace);
				setState(104);
				number();
				setState(105);
				match(CloseBrace);
				setState(106);
				quantifier_type();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(108);
				match(OpenBrace);
				setState(109);
				number();
				setState(110);
				match(Comma);
				setState(111);
				match(CloseBrace);
				setState(112);
				quantifier_type();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(114);
				match(OpenBrace);
				setState(115);
				number();
				setState(116);
				match(Comma);
				setState(117);
				number();
				setState(118);
				match(CloseBrace);
				setState(119);
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
		public TerminalNode Plus() { return getToken(JavaRegexParser.Plus, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public Quantifier_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterQuantifier_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitQuantifier_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitQuantifier_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quantifier_typeContext quantifier_type() throws RecognitionException {
		Quantifier_typeContext _localctx = new Quantifier_typeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_quantifier_type);
		try {
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Plus:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				match(Plus);
				}
				break;
			case QuestionMark:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
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
		public TerminalNode CharacterClassStart() { return getToken(JavaRegexParser.CharacterClassStart, 0); }
		public TerminalNode Caret() { return getToken(JavaRegexParser.Caret, 0); }
		public List<TerminalNode> CharacterClassEnd() { return getTokens(JavaRegexParser.CharacterClassEnd); }
		public TerminalNode CharacterClassEnd(int i) {
			return getToken(JavaRegexParser.CharacterClassEnd, i);
		}
		public TerminalNode Hyphen() { return getToken(JavaRegexParser.Hyphen, 0); }
		public List<Character_class_intersectionContext> character_class_intersection() {
			return getRuleContexts(Character_class_intersectionContext.class);
		}
		public Character_class_intersectionContext character_class_intersection(int i) {
			return getRuleContext(Character_class_intersectionContext.class,i);
		}
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterCharacter_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitCharacter_class(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitCharacter_class(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Character_classContext character_class() throws RecognitionException {
		Character_classContext _localctx = new Character_classContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_character_class);
		int _la;
		try {
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				match(CharacterClassStart);
				setState(129);
				match(Caret);
				setState(130);
				match(CharacterClassEnd);
				setState(131);
				match(Hyphen);
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(132);
					character_class_intersection();
					}
					}
					setState(135); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(137);
				match(CharacterClassEnd);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				match(CharacterClassStart);
				setState(140);
				match(Caret);
				setState(141);
				match(CharacterClassEnd);
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0)) {
					{
					{
					setState(142);
					character_class_intersection();
					}
					}
					setState(147);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(148);
				match(CharacterClassEnd);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(149);
				match(CharacterClassStart);
				setState(150);
				match(Caret);
				setState(152); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(151);
					character_class_intersection();
					}
					}
					setState(154); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(156);
				match(CharacterClassEnd);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(158);
				match(CharacterClassStart);
				setState(159);
				match(Caret);
				setState(161); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(160);
					cc_atom();
					}
					}
					setState(163); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(165);
				match(CharacterClassEnd);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(167);
				match(CharacterClassStart);
				setState(168);
				match(CharacterClassEnd);
				setState(169);
				match(Hyphen);
				setState(171); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(170);
					character_class_intersection();
					}
					}
					setState(173); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(175);
				match(CharacterClassEnd);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(177);
				match(CharacterClassStart);
				setState(178);
				match(CharacterClassEnd);
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0)) {
					{
					{
					setState(179);
					character_class_intersection();
					}
					}
					setState(184);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(185);
				match(CharacterClassEnd);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(186);
				match(CharacterClassStart);
				setState(188); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(187);
					character_class_intersection();
					}
					}
					setState(190); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(192);
				match(CharacterClassEnd);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(194);
				match(CharacterClassStart);
				setState(196); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(195);
					cc_atom();
					}
					}
					setState(198); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(200);
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

	public static class Character_class_intersectionContext extends ParserRuleContext {
		public List<Cc_atomContext> cc_atom() {
			return getRuleContexts(Cc_atomContext.class);
		}
		public Cc_atomContext cc_atom(int i) {
			return getRuleContext(Cc_atomContext.class,i);
		}
		public List<TerminalNode> Ampersand() { return getTokens(JavaRegexParser.Ampersand); }
		public TerminalNode Ampersand(int i) {
			return getToken(JavaRegexParser.Ampersand, i);
		}
		public Character_class_intersectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_character_class_intersection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterCharacter_class_intersection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitCharacter_class_intersection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitCharacter_class_intersection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Character_class_intersectionContext character_class_intersection() throws RecognitionException {
		Character_class_intersectionContext _localctx = new Character_class_intersectionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_character_class_intersection);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			cc_atom();
			setState(210);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(205);
					match(Ampersand);
					setState(206);
					match(Ampersand);
					setState(207);
					cc_atom();
					}
					} 
				}
				setState(212);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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

	public static class BackreferenceContext extends ParserRuleContext {
		public Backreference_or_octalContext backreference_or_octal() {
			return getRuleContext(Backreference_or_octalContext.class,0);
		}
		public TerminalNode SubroutineOrNamedReferenceStartG() { return getToken(JavaRegexParser.SubroutineOrNamedReferenceStartG, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(JavaRegexParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(JavaRegexParser.CloseBrace, 0); }
		public TerminalNode Hyphen() { return getToken(JavaRegexParser.Hyphen, 0); }
		public TerminalNode NamedReferenceStartK() { return getToken(JavaRegexParser.NamedReferenceStartK, 0); }
		public TerminalNode LessThan() { return getToken(JavaRegexParser.LessThan, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode GreaterThan() { return getToken(JavaRegexParser.GreaterThan, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(JavaRegexParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(JavaRegexParser.SingleQuote, i);
		}
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public TerminalNode PUC() { return getToken(JavaRegexParser.PUC, 0); }
		public TerminalNode Equals() { return getToken(JavaRegexParser.Equals, 0); }
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public BackreferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backreference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterBackreference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitBackreference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitBackreference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BackreferenceContext backreference() throws RecognitionException {
		BackreferenceContext _localctx = new BackreferenceContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_backreference);
		try {
			setState(254);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				backreference_or_octal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				match(SubroutineOrNamedReferenceStartG);
				setState(215);
				number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(216);
				match(SubroutineOrNamedReferenceStartG);
				setState(217);
				match(OpenBrace);
				setState(218);
				number();
				setState(219);
				match(CloseBrace);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(221);
				match(SubroutineOrNamedReferenceStartG);
				setState(222);
				match(OpenBrace);
				setState(223);
				match(Hyphen);
				setState(224);
				number();
				setState(225);
				match(CloseBrace);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(227);
				match(NamedReferenceStartK);
				setState(228);
				match(LessThan);
				setState(229);
				name();
				setState(230);
				match(GreaterThan);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(232);
				match(NamedReferenceStartK);
				setState(233);
				match(SingleQuote);
				setState(234);
				name();
				setState(235);
				match(SingleQuote);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(237);
				match(SubroutineOrNamedReferenceStartG);
				setState(238);
				match(OpenBrace);
				setState(239);
				name();
				setState(240);
				match(CloseBrace);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(242);
				match(NamedReferenceStartK);
				setState(243);
				match(OpenBrace);
				setState(244);
				name();
				setState(245);
				match(CloseBrace);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(247);
				match(OpenParen);
				setState(248);
				match(QuestionMark);
				setState(249);
				match(PUC);
				setState(250);
				match(Equals);
				setState(251);
				name();
				setState(252);
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
		public TerminalNode Backslash() { return getToken(JavaRegexParser.Backslash, 0); }
		public DigitContext digit() {
			return getRuleContext(DigitContext.class,0);
		}
		public Backreference_or_octalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backreference_or_octal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterBackreference_or_octal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitBackreference_or_octal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitBackreference_or_octal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Backreference_or_octalContext backreference_or_octal() throws RecognitionException {
		Backreference_or_octalContext _localctx = new Backreference_or_octalContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_backreference_or_octal);
		try {
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(256);
				octal_char();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				match(Backslash);
				setState(258);
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
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public TerminalNode LessThan() { return getToken(JavaRegexParser.LessThan, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode GreaterThan() { return getToken(JavaRegexParser.GreaterThan, 0); }
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(JavaRegexParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(JavaRegexParser.SingleQuote, i);
		}
		public TerminalNode PUC() { return getToken(JavaRegexParser.PUC, 0); }
		public CaptureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_capture; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterCapture(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitCapture(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitCapture(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaptureContext capture() throws RecognitionException {
		CaptureContext _localctx = new CaptureContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_capture);
		try {
			setState(290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(261);
				match(OpenParen);
				setState(262);
				match(QuestionMark);
				setState(263);
				match(LessThan);
				setState(264);
				name();
				setState(265);
				match(GreaterThan);
				setState(266);
				alternation();
				setState(267);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				match(OpenParen);
				setState(270);
				match(QuestionMark);
				setState(271);
				match(SingleQuote);
				setState(272);
				name();
				setState(273);
				match(SingleQuote);
				setState(274);
				alternation();
				setState(275);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(277);
				match(OpenParen);
				setState(278);
				match(QuestionMark);
				setState(279);
				match(PUC);
				setState(280);
				match(LessThan);
				setState(281);
				name();
				setState(282);
				match(GreaterThan);
				setState(283);
				alternation();
				setState(284);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(286);
				match(OpenParen);
				setState(287);
				alternation();
				setState(288);
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
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public TerminalNode Colon() { return getToken(JavaRegexParser.Colon, 0); }
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public TerminalNode Pipe() { return getToken(JavaRegexParser.Pipe, 0); }
		public TerminalNode GreaterThan() { return getToken(JavaRegexParser.GreaterThan, 0); }
		public Non_captureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_capture; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterNon_capture(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitNon_capture(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitNon_capture(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_captureContext non_capture() throws RecognitionException {
		Non_captureContext _localctx = new Non_captureContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_non_capture);
		try {
			setState(310);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(292);
				match(OpenParen);
				setState(293);
				match(QuestionMark);
				setState(294);
				match(Colon);
				setState(295);
				alternation();
				setState(296);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(298);
				match(OpenParen);
				setState(299);
				match(QuestionMark);
				setState(300);
				match(Pipe);
				setState(301);
				alternation();
				setState(302);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(304);
				match(OpenParen);
				setState(305);
				match(QuestionMark);
				setState(306);
				match(GreaterThan);
				setState(307);
				alternation();
				setState(308);
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
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public TerminalNode Hash() { return getToken(JavaRegexParser.Hash, 0); }
		public Non_close_parensContext non_close_parens() {
			return getRuleContext(Non_close_parensContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			match(OpenParen);
			setState(313);
			match(QuestionMark);
			setState(314);
			match(Hash);
			setState(315);
			non_close_parens();
			setState(316);
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
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public List<Option_flagsContext> option_flags() {
			return getRuleContexts(Option_flagsContext.class);
		}
		public Option_flagsContext option_flags(int i) {
			return getRuleContext(Option_flagsContext.class,i);
		}
		public TerminalNode Hyphen() { return getToken(JavaRegexParser.Hyphen, 0); }
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public TerminalNode Star() { return getToken(JavaRegexParser.Star, 0); }
		public TerminalNode NUC() { return getToken(JavaRegexParser.NUC, 0); }
		public List<TerminalNode> OUC() { return getTokens(JavaRegexParser.OUC); }
		public TerminalNode OUC(int i) {
			return getToken(JavaRegexParser.OUC, i);
		}
		public List<TerminalNode> Underscore() { return getTokens(JavaRegexParser.Underscore); }
		public TerminalNode Underscore(int i) {
			return getToken(JavaRegexParser.Underscore, i);
		}
		public TerminalNode SUC() { return getToken(JavaRegexParser.SUC, 0); }
		public List<TerminalNode> TUC() { return getTokens(JavaRegexParser.TUC); }
		public TerminalNode TUC(int i) {
			return getToken(JavaRegexParser.TUC, i);
		}
		public TerminalNode AUC() { return getToken(JavaRegexParser.AUC, 0); }
		public TerminalNode RUC() { return getToken(JavaRegexParser.RUC, 0); }
		public TerminalNode PUC() { return getToken(JavaRegexParser.PUC, 0); }
		public TerminalNode UUC() { return getToken(JavaRegexParser.UUC, 0); }
		public TerminalNode FUC() { return getToken(JavaRegexParser.FUC, 0); }
		public TerminalNode D8() { return getToken(JavaRegexParser.D8, 0); }
		public TerminalNode D1() { return getToken(JavaRegexParser.D1, 0); }
		public TerminalNode D6() { return getToken(JavaRegexParser.D6, 0); }
		public TerminalNode CUC() { return getToken(JavaRegexParser.CUC, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_option);
		try {
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(318);
				match(OpenParen);
				setState(319);
				match(QuestionMark);
				setState(320);
				option_flags();
				setState(321);
				match(Hyphen);
				setState(322);
				option_flags();
				setState(323);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(325);
				match(OpenParen);
				setState(326);
				match(QuestionMark);
				setState(327);
				option_flags();
				setState(328);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(330);
				match(OpenParen);
				setState(331);
				match(QuestionMark);
				setState(332);
				match(Hyphen);
				setState(333);
				option_flags();
				setState(334);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(336);
				match(OpenParen);
				setState(337);
				match(Star);
				setState(338);
				match(NUC);
				setState(339);
				match(OUC);
				setState(340);
				match(Underscore);
				setState(341);
				match(SUC);
				setState(342);
				match(TUC);
				setState(343);
				match(AUC);
				setState(344);
				match(RUC);
				setState(345);
				match(TUC);
				setState(346);
				match(Underscore);
				setState(347);
				match(OUC);
				setState(348);
				match(PUC);
				setState(349);
				match(TUC);
				setState(350);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(351);
				match(OpenParen);
				setState(352);
				match(Star);
				setState(353);
				match(UUC);
				setState(354);
				match(TUC);
				setState(355);
				match(FUC);
				setState(356);
				match(D8);
				setState(357);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(358);
				match(OpenParen);
				setState(359);
				match(Star);
				setState(360);
				match(UUC);
				setState(361);
				match(TUC);
				setState(362);
				match(FUC);
				setState(363);
				match(D1);
				setState(364);
				match(D6);
				setState(365);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(366);
				match(OpenParen);
				setState(367);
				match(Star);
				setState(368);
				match(UUC);
				setState(369);
				match(CUC);
				setState(370);
				match(PUC);
				setState(371);
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterOption_flags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitOption_flags(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitOption_flags(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_flagsContext option_flags() throws RecognitionException {
		Option_flagsContext _localctx = new Option_flagsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_option_flags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(374);
				option_flag();
				}
				}
				setState(377); 
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
		public TerminalNode ILC() { return getToken(JavaRegexParser.ILC, 0); }
		public TerminalNode JUC() { return getToken(JavaRegexParser.JUC, 0); }
		public TerminalNode MLC() { return getToken(JavaRegexParser.MLC, 0); }
		public TerminalNode SLC() { return getToken(JavaRegexParser.SLC, 0); }
		public TerminalNode UUC() { return getToken(JavaRegexParser.UUC, 0); }
		public TerminalNode XLC() { return getToken(JavaRegexParser.XLC, 0); }
		public Option_flagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterOption_flag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitOption_flag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitOption_flag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Option_flagContext option_flag() throws RecognitionException {
		Option_flagContext _localctx = new Option_flagContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_option_flag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
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
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public TerminalNode Equals() { return getToken(JavaRegexParser.Equals, 0); }
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public TerminalNode Exclamation() { return getToken(JavaRegexParser.Exclamation, 0); }
		public TerminalNode LessThan() { return getToken(JavaRegexParser.LessThan, 0); }
		public Look_aroundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_look_around; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterLook_around(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitLook_around(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitLook_around(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Look_aroundContext look_around() throws RecognitionException {
		Look_aroundContext _localctx = new Look_aroundContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_look_around);
		try {
			setState(407);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(381);
				match(OpenParen);
				setState(382);
				match(QuestionMark);
				setState(383);
				match(Equals);
				setState(384);
				alternation();
				setState(385);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				match(OpenParen);
				setState(388);
				match(QuestionMark);
				setState(389);
				match(Exclamation);
				setState(390);
				alternation();
				setState(391);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(393);
				match(OpenParen);
				setState(394);
				match(QuestionMark);
				setState(395);
				match(LessThan);
				setState(396);
				match(Equals);
				setState(397);
				alternation();
				setState(398);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(400);
				match(OpenParen);
				setState(401);
				match(QuestionMark);
				setState(402);
				match(LessThan);
				setState(403);
				match(Exclamation);
				setState(404);
				alternation();
				setState(405);
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
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public TerminalNode RUC() { return getToken(JavaRegexParser.RUC, 0); }
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode Plus() { return getToken(JavaRegexParser.Plus, 0); }
		public TerminalNode Hyphen() { return getToken(JavaRegexParser.Hyphen, 0); }
		public TerminalNode Ampersand() { return getToken(JavaRegexParser.Ampersand, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode PUC() { return getToken(JavaRegexParser.PUC, 0); }
		public TerminalNode GreaterThan() { return getToken(JavaRegexParser.GreaterThan, 0); }
		public TerminalNode SubroutineOrNamedReferenceStartG() { return getToken(JavaRegexParser.SubroutineOrNamedReferenceStartG, 0); }
		public TerminalNode LessThan() { return getToken(JavaRegexParser.LessThan, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(JavaRegexParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(JavaRegexParser.SingleQuote, i);
		}
		public Subroutine_referenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subroutine_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterSubroutine_reference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitSubroutine_reference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitSubroutine_reference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subroutine_referenceContext subroutine_reference() throws RecognitionException {
		Subroutine_referenceContext _localctx = new Subroutine_referenceContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_subroutine_reference);
		try {
			setState(487);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(409);
				match(OpenParen);
				setState(410);
				match(QuestionMark);
				setState(411);
				match(RUC);
				setState(412);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(413);
				match(OpenParen);
				setState(414);
				match(QuestionMark);
				setState(415);
				number();
				setState(416);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(418);
				match(OpenParen);
				setState(419);
				match(QuestionMark);
				setState(420);
				match(Plus);
				setState(421);
				number();
				setState(422);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(424);
				match(OpenParen);
				setState(425);
				match(QuestionMark);
				setState(426);
				match(Hyphen);
				setState(427);
				number();
				setState(428);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(430);
				match(OpenParen);
				setState(431);
				match(QuestionMark);
				setState(432);
				match(Ampersand);
				setState(433);
				name();
				setState(434);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(436);
				match(OpenParen);
				setState(437);
				match(QuestionMark);
				setState(438);
				match(PUC);
				setState(439);
				match(GreaterThan);
				setState(440);
				name();
				setState(441);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(443);
				match(SubroutineOrNamedReferenceStartG);
				setState(444);
				match(LessThan);
				setState(445);
				name();
				setState(446);
				match(GreaterThan);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(448);
				match(SubroutineOrNamedReferenceStartG);
				setState(449);
				match(SingleQuote);
				setState(450);
				name();
				setState(451);
				match(SingleQuote);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(453);
				match(SubroutineOrNamedReferenceStartG);
				setState(454);
				match(LessThan);
				setState(455);
				number();
				setState(456);
				match(GreaterThan);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(458);
				match(SubroutineOrNamedReferenceStartG);
				setState(459);
				match(SingleQuote);
				setState(460);
				number();
				setState(461);
				match(SingleQuote);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(463);
				match(SubroutineOrNamedReferenceStartG);
				setState(464);
				match(LessThan);
				setState(465);
				match(Plus);
				setState(466);
				number();
				setState(467);
				match(GreaterThan);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(469);
				match(SubroutineOrNamedReferenceStartG);
				setState(470);
				match(SingleQuote);
				setState(471);
				match(Plus);
				setState(472);
				number();
				setState(473);
				match(SingleQuote);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(475);
				match(SubroutineOrNamedReferenceStartG);
				setState(476);
				match(LessThan);
				setState(477);
				match(Hyphen);
				setState(478);
				number();
				setState(479);
				match(GreaterThan);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(481);
				match(SubroutineOrNamedReferenceStartG);
				setState(482);
				match(SingleQuote);
				setState(483);
				match(Hyphen);
				setState(484);
				number();
				setState(485);
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
		public List<TerminalNode> OpenParen() { return getTokens(JavaRegexParser.OpenParen); }
		public TerminalNode OpenParen(int i) {
			return getToken(JavaRegexParser.OpenParen, i);
		}
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<TerminalNode> CloseParen() { return getTokens(JavaRegexParser.CloseParen); }
		public TerminalNode CloseParen(int i) {
			return getToken(JavaRegexParser.CloseParen, i);
		}
		public List<AlternationContext> alternation() {
			return getRuleContexts(AlternationContext.class);
		}
		public AlternationContext alternation(int i) {
			return getRuleContext(AlternationContext.class,i);
		}
		public TerminalNode Pipe() { return getToken(JavaRegexParser.Pipe, 0); }
		public TerminalNode Plus() { return getToken(JavaRegexParser.Plus, 0); }
		public TerminalNode Hyphen() { return getToken(JavaRegexParser.Hyphen, 0); }
		public TerminalNode LessThan() { return getToken(JavaRegexParser.LessThan, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode GreaterThan() { return getToken(JavaRegexParser.GreaterThan, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(JavaRegexParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(JavaRegexParser.SingleQuote, i);
		}
		public TerminalNode RUC() { return getToken(JavaRegexParser.RUC, 0); }
		public TerminalNode Ampersand() { return getToken(JavaRegexParser.Ampersand, 0); }
		public TerminalNode DUC() { return getToken(JavaRegexParser.DUC, 0); }
		public List<TerminalNode> EUC() { return getTokens(JavaRegexParser.EUC); }
		public TerminalNode EUC(int i) {
			return getToken(JavaRegexParser.EUC, i);
		}
		public TerminalNode FUC() { return getToken(JavaRegexParser.FUC, 0); }
		public TerminalNode IUC() { return getToken(JavaRegexParser.IUC, 0); }
		public TerminalNode NUC() { return getToken(JavaRegexParser.NUC, 0); }
		public TerminalNode ALC() { return getToken(JavaRegexParser.ALC, 0); }
		public List<TerminalNode> SLC() { return getTokens(JavaRegexParser.SLC); }
		public TerminalNode SLC(int i) {
			return getToken(JavaRegexParser.SLC, i);
		}
		public TerminalNode ELC() { return getToken(JavaRegexParser.ELC, 0); }
		public TerminalNode RLC() { return getToken(JavaRegexParser.RLC, 0); }
		public TerminalNode TLC() { return getToken(JavaRegexParser.TLC, 0); }
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_conditional);
		int _la;
		try {
			setState(640);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(489);
				match(OpenParen);
				setState(490);
				match(QuestionMark);
				setState(491);
				match(OpenParen);
				setState(492);
				number();
				setState(493);
				match(CloseParen);
				setState(494);
				alternation();
				setState(497);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(495);
					match(Pipe);
					setState(496);
					alternation();
					}
				}

				setState(499);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(501);
				match(OpenParen);
				setState(502);
				match(QuestionMark);
				setState(503);
				match(OpenParen);
				setState(504);
				match(Plus);
				setState(505);
				number();
				setState(506);
				match(CloseParen);
				setState(507);
				alternation();
				setState(510);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(508);
					match(Pipe);
					setState(509);
					alternation();
					}
				}

				setState(512);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(514);
				match(OpenParen);
				setState(515);
				match(QuestionMark);
				setState(516);
				match(OpenParen);
				setState(517);
				match(Hyphen);
				setState(518);
				number();
				setState(519);
				match(CloseParen);
				setState(520);
				alternation();
				setState(523);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(521);
					match(Pipe);
					setState(522);
					alternation();
					}
				}

				setState(525);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(527);
				match(OpenParen);
				setState(528);
				match(QuestionMark);
				setState(529);
				match(OpenParen);
				setState(530);
				match(LessThan);
				setState(531);
				name();
				setState(532);
				match(GreaterThan);
				setState(533);
				match(CloseParen);
				setState(534);
				alternation();
				setState(537);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(535);
					match(Pipe);
					setState(536);
					alternation();
					}
				}

				setState(539);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(541);
				match(OpenParen);
				setState(542);
				match(QuestionMark);
				setState(543);
				match(OpenParen);
				setState(544);
				match(SingleQuote);
				setState(545);
				name();
				setState(546);
				match(SingleQuote);
				setState(547);
				match(CloseParen);
				setState(548);
				alternation();
				setState(551);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(549);
					match(Pipe);
					setState(550);
					alternation();
					}
				}

				setState(553);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(555);
				match(OpenParen);
				setState(556);
				match(QuestionMark);
				setState(557);
				match(OpenParen);
				setState(558);
				match(RUC);
				setState(559);
				number();
				setState(560);
				match(CloseParen);
				setState(561);
				alternation();
				setState(564);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(562);
					match(Pipe);
					setState(563);
					alternation();
					}
				}

				setState(566);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(568);
				match(OpenParen);
				setState(569);
				match(QuestionMark);
				setState(570);
				match(OpenParen);
				setState(571);
				match(RUC);
				setState(572);
				match(CloseParen);
				setState(573);
				alternation();
				setState(576);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(574);
					match(Pipe);
					setState(575);
					alternation();
					}
				}

				setState(578);
				match(CloseParen);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(580);
				match(OpenParen);
				setState(581);
				match(QuestionMark);
				setState(582);
				match(OpenParen);
				setState(583);
				match(RUC);
				setState(584);
				match(Ampersand);
				setState(585);
				name();
				setState(586);
				match(CloseParen);
				setState(587);
				alternation();
				setState(590);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(588);
					match(Pipe);
					setState(589);
					alternation();
					}
				}

				setState(592);
				match(CloseParen);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(594);
				match(OpenParen);
				setState(595);
				match(QuestionMark);
				setState(596);
				match(OpenParen);
				setState(597);
				match(DUC);
				setState(598);
				match(EUC);
				setState(599);
				match(FUC);
				setState(600);
				match(IUC);
				setState(601);
				match(NUC);
				setState(602);
				match(EUC);
				setState(603);
				match(CloseParen);
				setState(604);
				alternation();
				setState(607);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(605);
					match(Pipe);
					setState(606);
					alternation();
					}
				}

				setState(609);
				match(CloseParen);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(611);
				match(OpenParen);
				setState(612);
				match(QuestionMark);
				setState(613);
				match(OpenParen);
				setState(614);
				match(ALC);
				setState(615);
				match(SLC);
				setState(616);
				match(SLC);
				setState(617);
				match(ELC);
				setState(618);
				match(RLC);
				setState(619);
				match(TLC);
				setState(620);
				match(CloseParen);
				setState(621);
				alternation();
				setState(624);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(622);
					match(Pipe);
					setState(623);
					alternation();
					}
				}

				setState(626);
				match(CloseParen);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(628);
				match(OpenParen);
				setState(629);
				match(QuestionMark);
				setState(630);
				match(OpenParen);
				setState(631);
				name();
				setState(632);
				match(CloseParen);
				setState(633);
				alternation();
				setState(636);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(634);
					match(Pipe);
					setState(635);
					alternation();
					}
				}

				setState(638);
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
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode Star() { return getToken(JavaRegexParser.Star, 0); }
		public List<TerminalNode> AUC() { return getTokens(JavaRegexParser.AUC); }
		public TerminalNode AUC(int i) {
			return getToken(JavaRegexParser.AUC, i);
		}
		public List<TerminalNode> CUC() { return getTokens(JavaRegexParser.CUC); }
		public TerminalNode CUC(int i) {
			return getToken(JavaRegexParser.CUC, i);
		}
		public List<TerminalNode> EUC() { return getTokens(JavaRegexParser.EUC); }
		public TerminalNode EUC(int i) {
			return getToken(JavaRegexParser.EUC, i);
		}
		public TerminalNode PUC() { return getToken(JavaRegexParser.PUC, 0); }
		public TerminalNode TUC() { return getToken(JavaRegexParser.TUC, 0); }
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public TerminalNode FUC() { return getToken(JavaRegexParser.FUC, 0); }
		public TerminalNode IUC() { return getToken(JavaRegexParser.IUC, 0); }
		public TerminalNode LUC() { return getToken(JavaRegexParser.LUC, 0); }
		public TerminalNode Colon() { return getToken(JavaRegexParser.Colon, 0); }
		public List<TerminalNode> NUC() { return getTokens(JavaRegexParser.NUC); }
		public TerminalNode NUC(int i) {
			return getToken(JavaRegexParser.NUC, i);
		}
		public List<TerminalNode> MUC() { return getTokens(JavaRegexParser.MUC); }
		public TerminalNode MUC(int i) {
			return getToken(JavaRegexParser.MUC, i);
		}
		public TerminalNode RUC() { return getToken(JavaRegexParser.RUC, 0); }
		public TerminalNode KUC() { return getToken(JavaRegexParser.KUC, 0); }
		public TerminalNode OUC() { return getToken(JavaRegexParser.OUC, 0); }
		public TerminalNode UUC() { return getToken(JavaRegexParser.UUC, 0); }
		public TerminalNode SUC() { return getToken(JavaRegexParser.SUC, 0); }
		public TerminalNode HUC() { return getToken(JavaRegexParser.HUC, 0); }
		public Backtrack_controlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backtrack_control; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterBacktrack_control(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitBacktrack_control(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitBacktrack_control(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Backtrack_controlContext backtrack_control() throws RecognitionException {
		Backtrack_controlContext _localctx = new Backtrack_controlContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_backtrack_control);
		int _la;
		try {
			setState(742);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(642);
				match(OpenParen);
				setState(643);
				match(Star);
				setState(644);
				match(AUC);
				setState(645);
				match(CUC);
				setState(646);
				match(CUC);
				setState(647);
				match(EUC);
				setState(648);
				match(PUC);
				setState(649);
				match(TUC);
				setState(650);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(651);
				match(OpenParen);
				setState(652);
				match(Star);
				setState(653);
				match(FUC);
				setState(657);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AUC) {
					{
					setState(654);
					match(AUC);
					setState(655);
					match(IUC);
					setState(656);
					match(LUC);
					}
				}

				setState(659);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(660);
				match(OpenParen);
				setState(661);
				match(Star);
				setState(666);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MUC) {
					{
					setState(662);
					match(MUC);
					setState(663);
					match(AUC);
					setState(664);
					match(RUC);
					setState(665);
					match(KUC);
					}
				}

				setState(668);
				match(Colon);
				setState(669);
				match(NUC);
				setState(670);
				match(AUC);
				setState(671);
				match(MUC);
				setState(672);
				match(EUC);
				setState(673);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(674);
				match(OpenParen);
				setState(675);
				match(Star);
				setState(676);
				match(CUC);
				setState(677);
				match(OUC);
				setState(678);
				match(MUC);
				setState(679);
				match(MUC);
				setState(680);
				match(IUC);
				setState(681);
				match(TUC);
				setState(682);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(683);
				match(OpenParen);
				setState(684);
				match(Star);
				setState(685);
				match(PUC);
				setState(686);
				match(RUC);
				setState(687);
				match(UUC);
				setState(688);
				match(NUC);
				setState(689);
				match(EUC);
				setState(690);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(691);
				match(OpenParen);
				setState(692);
				match(Star);
				setState(693);
				match(PUC);
				setState(694);
				match(RUC);
				setState(695);
				match(UUC);
				setState(696);
				match(NUC);
				setState(697);
				match(EUC);
				setState(698);
				match(Colon);
				setState(699);
				match(NUC);
				setState(700);
				match(AUC);
				setState(701);
				match(MUC);
				setState(702);
				match(EUC);
				setState(703);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(704);
				match(OpenParen);
				setState(705);
				match(Star);
				setState(706);
				match(SUC);
				setState(707);
				match(KUC);
				setState(708);
				match(IUC);
				setState(709);
				match(PUC);
				setState(710);
				match(CloseParen);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(711);
				match(OpenParen);
				setState(712);
				match(Star);
				setState(713);
				match(SUC);
				setState(714);
				match(KUC);
				setState(715);
				match(IUC);
				setState(716);
				match(PUC);
				setState(717);
				match(Colon);
				setState(718);
				match(NUC);
				setState(719);
				match(AUC);
				setState(720);
				match(MUC);
				setState(721);
				match(EUC);
				setState(722);
				match(CloseParen);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(723);
				match(OpenParen);
				setState(724);
				match(Star);
				setState(725);
				match(TUC);
				setState(726);
				match(HUC);
				setState(727);
				match(EUC);
				setState(728);
				match(NUC);
				setState(729);
				match(CloseParen);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(730);
				match(OpenParen);
				setState(731);
				match(Star);
				setState(732);
				match(TUC);
				setState(733);
				match(HUC);
				setState(734);
				match(EUC);
				setState(735);
				match(NUC);
				setState(736);
				match(Colon);
				setState(737);
				match(NUC);
				setState(738);
				match(AUC);
				setState(739);
				match(MUC);
				setState(740);
				match(EUC);
				setState(741);
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
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode Star() { return getToken(JavaRegexParser.Star, 0); }
		public TerminalNode CUC() { return getToken(JavaRegexParser.CUC, 0); }
		public List<TerminalNode> RUC() { return getTokens(JavaRegexParser.RUC); }
		public TerminalNode RUC(int i) {
			return getToken(JavaRegexParser.RUC, i);
		}
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public TerminalNode LUC() { return getToken(JavaRegexParser.LUC, 0); }
		public TerminalNode FUC() { return getToken(JavaRegexParser.FUC, 0); }
		public TerminalNode AUC() { return getToken(JavaRegexParser.AUC, 0); }
		public TerminalNode NUC() { return getToken(JavaRegexParser.NUC, 0); }
		public TerminalNode YUC() { return getToken(JavaRegexParser.YUC, 0); }
		public TerminalNode BUC() { return getToken(JavaRegexParser.BUC, 0); }
		public TerminalNode SUC() { return getToken(JavaRegexParser.SUC, 0); }
		public TerminalNode Underscore() { return getToken(JavaRegexParser.Underscore, 0); }
		public TerminalNode UUC() { return getToken(JavaRegexParser.UUC, 0); }
		public TerminalNode IUC() { return getToken(JavaRegexParser.IUC, 0); }
		public TerminalNode OUC() { return getToken(JavaRegexParser.OUC, 0); }
		public TerminalNode DUC() { return getToken(JavaRegexParser.DUC, 0); }
		public TerminalNode EUC() { return getToken(JavaRegexParser.EUC, 0); }
		public Newline_conventionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newline_convention; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterNewline_convention(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitNewline_convention(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitNewline_convention(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Newline_conventionContext newline_convention() throws RecognitionException {
		Newline_conventionContext _localctx = new Newline_conventionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_newline_convention);
		try {
			setState(805);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(744);
				match(OpenParen);
				setState(745);
				match(Star);
				setState(746);
				match(CUC);
				setState(747);
				match(RUC);
				setState(748);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(749);
				match(OpenParen);
				setState(750);
				match(Star);
				setState(751);
				match(LUC);
				setState(752);
				match(FUC);
				setState(753);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(754);
				match(OpenParen);
				setState(755);
				match(Star);
				setState(756);
				match(CUC);
				setState(757);
				match(RUC);
				setState(758);
				match(LUC);
				setState(759);
				match(FUC);
				setState(760);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(761);
				match(OpenParen);
				setState(762);
				match(Star);
				setState(763);
				match(AUC);
				setState(764);
				match(NUC);
				setState(765);
				match(YUC);
				setState(766);
				match(CUC);
				setState(767);
				match(RUC);
				setState(768);
				match(LUC);
				setState(769);
				match(FUC);
				setState(770);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(771);
				match(OpenParen);
				setState(772);
				match(Star);
				setState(773);
				match(AUC);
				setState(774);
				match(NUC);
				setState(775);
				match(YUC);
				setState(776);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(777);
				match(OpenParen);
				setState(778);
				match(Star);
				setState(779);
				match(BUC);
				setState(780);
				match(SUC);
				setState(781);
				match(RUC);
				setState(782);
				match(Underscore);
				setState(783);
				match(AUC);
				setState(784);
				match(NUC);
				setState(785);
				match(YUC);
				setState(786);
				match(CUC);
				setState(787);
				match(RUC);
				setState(788);
				match(LUC);
				setState(789);
				match(FUC);
				setState(790);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(791);
				match(OpenParen);
				setState(792);
				match(Star);
				setState(793);
				match(BUC);
				setState(794);
				match(SUC);
				setState(795);
				match(RUC);
				setState(796);
				match(Underscore);
				setState(797);
				match(UUC);
				setState(798);
				match(NUC);
				setState(799);
				match(IUC);
				setState(800);
				match(CUC);
				setState(801);
				match(OUC);
				setState(802);
				match(DUC);
				setState(803);
				match(EUC);
				setState(804);
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
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public TerminalNode CUC() { return getToken(JavaRegexParser.CUC, 0); }
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public CalloutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callout; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterCallout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitCallout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitCallout(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalloutContext callout() throws RecognitionException {
		CalloutContext _localctx = new CalloutContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_callout);
		try {
			setState(817);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(807);
				match(OpenParen);
				setState(808);
				match(QuestionMark);
				setState(809);
				match(CUC);
				setState(810);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(811);
				match(OpenParen);
				setState(812);
				match(QuestionMark);
				setState(813);
				match(CUC);
				setState(814);
				number();
				setState(815);
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
		public TerminalNode Dot() { return getToken(JavaRegexParser.Dot, 0); }
		public TerminalNode Caret() { return getToken(JavaRegexParser.Caret, 0); }
		public TerminalNode StartOfSubject() { return getToken(JavaRegexParser.StartOfSubject, 0); }
		public TerminalNode WordBoundary() { return getToken(JavaRegexParser.WordBoundary, 0); }
		public TerminalNode NonWordBoundary() { return getToken(JavaRegexParser.NonWordBoundary, 0); }
		public TerminalNode EndOfSubjectOrLine() { return getToken(JavaRegexParser.EndOfSubjectOrLine, 0); }
		public TerminalNode EndOfSubjectOrLineEndOfSubject() { return getToken(JavaRegexParser.EndOfSubjectOrLineEndOfSubject, 0); }
		public TerminalNode EndOfSubject() { return getToken(JavaRegexParser.EndOfSubject, 0); }
		public TerminalNode PreviousMatchInSubject() { return getToken(JavaRegexParser.PreviousMatchInSubject, 0); }
		public TerminalNode ResetStartMatch() { return getToken(JavaRegexParser.ResetStartMatch, 0); }
		public TerminalNode OneDataUnit() { return getToken(JavaRegexParser.OneDataUnit, 0); }
		public TerminalNode ExtendedUnicodeChar() { return getToken(JavaRegexParser.ExtendedUnicodeChar, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_atom);
		try {
			setState(845);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(819);
				subroutine_reference();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(820);
				shared_atom();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(821);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(822);
				character_class();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(823);
				capture();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(824);
				non_capture();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(825);
				comment();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(826);
				option();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(827);
				look_around();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(828);
				backreference();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(829);
				conditional();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(830);
				backtrack_control();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(831);
				newline_convention();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(832);
				callout();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(833);
				match(Dot);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(834);
				match(Caret);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(835);
				match(StartOfSubject);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(836);
				match(WordBoundary);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(837);
				match(NonWordBoundary);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(838);
				match(EndOfSubjectOrLine);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(839);
				match(EndOfSubjectOrLineEndOfSubject);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(840);
				match(EndOfSubject);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(841);
				match(PreviousMatchInSubject);
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(842);
				match(ResetStartMatch);
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(843);
				match(OneDataUnit);
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(844);
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
		public Character_classContext character_class() {
			return getRuleContext(Character_classContext.class,0);
		}
		public List<Cc_literalContext> cc_literal() {
			return getRuleContexts(Cc_literalContext.class);
		}
		public Cc_literalContext cc_literal(int i) {
			return getRuleContext(Cc_literalContext.class,i);
		}
		public TerminalNode Hyphen() { return getToken(JavaRegexParser.Hyphen, 0); }
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterCc_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitCc_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitCc_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cc_atomContext cc_atom() throws RecognitionException {
		Cc_atomContext _localctx = new Cc_atomContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_cc_atom);
		try {
			setState(855);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(847);
				character_class();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(848);
				cc_literal();
				setState(849);
				match(Hyphen);
				setState(850);
				cc_literal();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(852);
				shared_atom();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(853);
				cc_literal();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(854);
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
		public TerminalNode ControlChar() { return getToken(JavaRegexParser.ControlChar, 0); }
		public TerminalNode DecimalDigit() { return getToken(JavaRegexParser.DecimalDigit, 0); }
		public TerminalNode NotDecimalDigit() { return getToken(JavaRegexParser.NotDecimalDigit, 0); }
		public TerminalNode HorizontalWhiteSpace() { return getToken(JavaRegexParser.HorizontalWhiteSpace, 0); }
		public TerminalNode NotHorizontalWhiteSpace() { return getToken(JavaRegexParser.NotHorizontalWhiteSpace, 0); }
		public TerminalNode NotNewLine() { return getToken(JavaRegexParser.NotNewLine, 0); }
		public TerminalNode CharWithProperty() { return getToken(JavaRegexParser.CharWithProperty, 0); }
		public TerminalNode CharWithoutProperty() { return getToken(JavaRegexParser.CharWithoutProperty, 0); }
		public TerminalNode NewLineSequence() { return getToken(JavaRegexParser.NewLineSequence, 0); }
		public TerminalNode WhiteSpace() { return getToken(JavaRegexParser.WhiteSpace, 0); }
		public TerminalNode NotWhiteSpace() { return getToken(JavaRegexParser.NotWhiteSpace, 0); }
		public TerminalNode VerticalWhiteSpace() { return getToken(JavaRegexParser.VerticalWhiteSpace, 0); }
		public TerminalNode NotVerticalWhiteSpace() { return getToken(JavaRegexParser.NotVerticalWhiteSpace, 0); }
		public TerminalNode WordChar() { return getToken(JavaRegexParser.WordChar, 0); }
		public TerminalNode NotWordChar() { return getToken(JavaRegexParser.NotWordChar, 0); }
		public Shared_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shared_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterShared_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitShared_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitShared_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shared_atomContext shared_atom() throws RecognitionException {
		Shared_atomContext _localctx = new Shared_atomContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_shared_atom);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(857);
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
		public TerminalNode CharacterClassEnd() { return getToken(JavaRegexParser.CharacterClassEnd, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_literal);
		try {
			setState(861);
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
				setState(859);
				shared_literal();
				}
				break;
			case CharacterClassEnd:
				enterOuterAlt(_localctx, 2);
				{
				setState(860);
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
		public TerminalNode Dot() { return getToken(JavaRegexParser.Dot, 0); }
		public TerminalNode CharacterClassStart() { return getToken(JavaRegexParser.CharacterClassStart, 0); }
		public TerminalNode Caret() { return getToken(JavaRegexParser.Caret, 0); }
		public TerminalNode QuestionMark() { return getToken(JavaRegexParser.QuestionMark, 0); }
		public TerminalNode Plus() { return getToken(JavaRegexParser.Plus, 0); }
		public TerminalNode Star() { return getToken(JavaRegexParser.Star, 0); }
		public TerminalNode WordBoundary() { return getToken(JavaRegexParser.WordBoundary, 0); }
		public TerminalNode EndOfSubjectOrLine() { return getToken(JavaRegexParser.EndOfSubjectOrLine, 0); }
		public TerminalNode Pipe() { return getToken(JavaRegexParser.Pipe, 0); }
		public TerminalNode OpenParen() { return getToken(JavaRegexParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public Cc_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cc_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterCc_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitCc_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitCc_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cc_literalContext cc_literal() throws RecognitionException {
		Cc_literalContext _localctx = new Cc_literalContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_cc_literal);
		try {
			setState(875);
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
				setState(863);
				shared_literal();
				}
				break;
			case Dot:
				enterOuterAlt(_localctx, 2);
				{
				setState(864);
				match(Dot);
				}
				break;
			case CharacterClassStart:
				enterOuterAlt(_localctx, 3);
				{
				setState(865);
				match(CharacterClassStart);
				}
				break;
			case Caret:
				enterOuterAlt(_localctx, 4);
				{
				setState(866);
				match(Caret);
				}
				break;
			case QuestionMark:
				enterOuterAlt(_localctx, 5);
				{
				setState(867);
				match(QuestionMark);
				}
				break;
			case Plus:
				enterOuterAlt(_localctx, 6);
				{
				setState(868);
				match(Plus);
				}
				break;
			case Star:
				enterOuterAlt(_localctx, 7);
				{
				setState(869);
				match(Star);
				}
				break;
			case WordBoundary:
				enterOuterAlt(_localctx, 8);
				{
				setState(870);
				match(WordBoundary);
				}
				break;
			case EndOfSubjectOrLine:
				enterOuterAlt(_localctx, 9);
				{
				setState(871);
				match(EndOfSubjectOrLine);
				}
				break;
			case Pipe:
				enterOuterAlt(_localctx, 10);
				{
				setState(872);
				match(Pipe);
				}
				break;
			case OpenParen:
				enterOuterAlt(_localctx, 11);
				{
				setState(873);
				match(OpenParen);
				}
				break;
			case CloseParen:
				enterOuterAlt(_localctx, 12);
				{
				setState(874);
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
		public TerminalNode BellChar() { return getToken(JavaRegexParser.BellChar, 0); }
		public TerminalNode EscapeChar() { return getToken(JavaRegexParser.EscapeChar, 0); }
		public TerminalNode FormFeed() { return getToken(JavaRegexParser.FormFeed, 0); }
		public TerminalNode NewLine() { return getToken(JavaRegexParser.NewLine, 0); }
		public TerminalNode CarriageReturn() { return getToken(JavaRegexParser.CarriageReturn, 0); }
		public TerminalNode Tab() { return getToken(JavaRegexParser.Tab, 0); }
		public TerminalNode HexChar() { return getToken(JavaRegexParser.HexChar, 0); }
		public TerminalNode Quoted() { return getToken(JavaRegexParser.Quoted, 0); }
		public TerminalNode BlockQuoted() { return getToken(JavaRegexParser.BlockQuoted, 0); }
		public TerminalNode OpenBrace() { return getToken(JavaRegexParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(JavaRegexParser.CloseBrace, 0); }
		public TerminalNode Comma() { return getToken(JavaRegexParser.Comma, 0); }
		public TerminalNode Hyphen() { return getToken(JavaRegexParser.Hyphen, 0); }
		public TerminalNode LessThan() { return getToken(JavaRegexParser.LessThan, 0); }
		public TerminalNode GreaterThan() { return getToken(JavaRegexParser.GreaterThan, 0); }
		public TerminalNode SingleQuote() { return getToken(JavaRegexParser.SingleQuote, 0); }
		public TerminalNode Underscore() { return getToken(JavaRegexParser.Underscore, 0); }
		public TerminalNode Colon() { return getToken(JavaRegexParser.Colon, 0); }
		public TerminalNode Hash() { return getToken(JavaRegexParser.Hash, 0); }
		public TerminalNode Equals() { return getToken(JavaRegexParser.Equals, 0); }
		public TerminalNode Exclamation() { return getToken(JavaRegexParser.Exclamation, 0); }
		public TerminalNode Ampersand() { return getToken(JavaRegexParser.Ampersand, 0); }
		public TerminalNode OtherChar() { return getToken(JavaRegexParser.OtherChar, 0); }
		public Shared_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shared_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterShared_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitShared_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitShared_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shared_literalContext shared_literal() throws RecognitionException {
		Shared_literalContext _localctx = new Shared_literalContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_shared_literal);
		try {
			setState(903);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Backslash:
				enterOuterAlt(_localctx, 1);
				{
				setState(877);
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
				setState(878);
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
				setState(879);
				digit();
				}
				break;
			case BellChar:
				enterOuterAlt(_localctx, 4);
				{
				setState(880);
				match(BellChar);
				}
				break;
			case EscapeChar:
				enterOuterAlt(_localctx, 5);
				{
				setState(881);
				match(EscapeChar);
				}
				break;
			case FormFeed:
				enterOuterAlt(_localctx, 6);
				{
				setState(882);
				match(FormFeed);
				}
				break;
			case NewLine:
				enterOuterAlt(_localctx, 7);
				{
				setState(883);
				match(NewLine);
				}
				break;
			case CarriageReturn:
				enterOuterAlt(_localctx, 8);
				{
				setState(884);
				match(CarriageReturn);
				}
				break;
			case Tab:
				enterOuterAlt(_localctx, 9);
				{
				setState(885);
				match(Tab);
				}
				break;
			case HexChar:
				enterOuterAlt(_localctx, 10);
				{
				setState(886);
				match(HexChar);
				}
				break;
			case Quoted:
				enterOuterAlt(_localctx, 11);
				{
				setState(887);
				match(Quoted);
				}
				break;
			case BlockQuoted:
				enterOuterAlt(_localctx, 12);
				{
				setState(888);
				match(BlockQuoted);
				}
				break;
			case OpenBrace:
				enterOuterAlt(_localctx, 13);
				{
				setState(889);
				match(OpenBrace);
				}
				break;
			case CloseBrace:
				enterOuterAlt(_localctx, 14);
				{
				setState(890);
				match(CloseBrace);
				}
				break;
			case Comma:
				enterOuterAlt(_localctx, 15);
				{
				setState(891);
				match(Comma);
				}
				break;
			case Hyphen:
				enterOuterAlt(_localctx, 16);
				{
				setState(892);
				match(Hyphen);
				}
				break;
			case LessThan:
				enterOuterAlt(_localctx, 17);
				{
				setState(893);
				match(LessThan);
				}
				break;
			case GreaterThan:
				enterOuterAlt(_localctx, 18);
				{
				setState(894);
				match(GreaterThan);
				}
				break;
			case SingleQuote:
				enterOuterAlt(_localctx, 19);
				{
				setState(895);
				match(SingleQuote);
				}
				break;
			case Underscore:
				enterOuterAlt(_localctx, 20);
				{
				setState(896);
				match(Underscore);
				}
				break;
			case Colon:
				enterOuterAlt(_localctx, 21);
				{
				setState(897);
				match(Colon);
				}
				break;
			case Hash:
				enterOuterAlt(_localctx, 22);
				{
				setState(898);
				match(Hash);
				}
				break;
			case Equals:
				enterOuterAlt(_localctx, 23);
				{
				setState(899);
				match(Equals);
				}
				break;
			case Exclamation:
				enterOuterAlt(_localctx, 24);
				{
				setState(900);
				match(Exclamation);
				}
				break;
			case Ampersand:
				enterOuterAlt(_localctx, 25);
				{
				setState(901);
				match(Ampersand);
				}
				break;
			case OtherChar:
				enterOuterAlt(_localctx, 26);
				{
				setState(902);
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(905);
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
		public TerminalNode Backslash() { return getToken(JavaRegexParser.Backslash, 0); }
		public List<Octal_digitContext> octal_digit() {
			return getRuleContexts(Octal_digitContext.class);
		}
		public Octal_digitContext octal_digit(int i) {
			return getRuleContext(Octal_digitContext.class,i);
		}
		public TerminalNode D0() { return getToken(JavaRegexParser.D0, 0); }
		public TerminalNode D1() { return getToken(JavaRegexParser.D1, 0); }
		public TerminalNode D2() { return getToken(JavaRegexParser.D2, 0); }
		public TerminalNode D3() { return getToken(JavaRegexParser.D3, 0); }
		public Octal_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octal_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterOctal_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitOctal_char(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitOctal_char(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Octal_charContext octal_char() throws RecognitionException {
		Octal_charContext _localctx = new Octal_charContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_octal_char);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(916);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(907);
				match(Backslash);
				setState(908);
				_la = _input.LA(1);
				if ( !(((((_la - 113)) & ~0x3f) == 0 && ((1L << (_la - 113)) & ((1L << (D1 - 113)) | (1L << (D2 - 113)) | (1L << (D3 - 113)) | (1L << (D0 - 113)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(909);
				octal_digit();
				setState(910);
				octal_digit();
				}
				break;
			case 2:
				{
				setState(912);
				match(Backslash);
				setState(913);
				octal_digit();
				setState(914);
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
		public TerminalNode D0() { return getToken(JavaRegexParser.D0, 0); }
		public TerminalNode D1() { return getToken(JavaRegexParser.D1, 0); }
		public TerminalNode D2() { return getToken(JavaRegexParser.D2, 0); }
		public TerminalNode D3() { return getToken(JavaRegexParser.D3, 0); }
		public TerminalNode D4() { return getToken(JavaRegexParser.D4, 0); }
		public TerminalNode D5() { return getToken(JavaRegexParser.D5, 0); }
		public TerminalNode D6() { return getToken(JavaRegexParser.D6, 0); }
		public TerminalNode D7() { return getToken(JavaRegexParser.D7, 0); }
		public Octal_digitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octal_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterOctal_digit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitOctal_digit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitOctal_digit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Octal_digitContext octal_digit() throws RecognitionException {
		Octal_digitContext _localctx = new Octal_digitContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_octal_digit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(918);
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterDigits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitDigits(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitDigits(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DigitsContext digits() throws RecognitionException {
		DigitsContext _localctx = new DigitsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_digits);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(921); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(920);
					digit();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(923); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
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
		public TerminalNode D0() { return getToken(JavaRegexParser.D0, 0); }
		public TerminalNode D1() { return getToken(JavaRegexParser.D1, 0); }
		public TerminalNode D2() { return getToken(JavaRegexParser.D2, 0); }
		public TerminalNode D3() { return getToken(JavaRegexParser.D3, 0); }
		public TerminalNode D4() { return getToken(JavaRegexParser.D4, 0); }
		public TerminalNode D5() { return getToken(JavaRegexParser.D5, 0); }
		public TerminalNode D6() { return getToken(JavaRegexParser.D6, 0); }
		public TerminalNode D7() { return getToken(JavaRegexParser.D7, 0); }
		public TerminalNode D8() { return getToken(JavaRegexParser.D8, 0); }
		public TerminalNode D9() { return getToken(JavaRegexParser.D9, 0); }
		public DigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitDigit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitDigit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DigitContext digit() throws RecognitionException {
		DigitContext _localctx = new DigitContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_digit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(925);
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(927);
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
		public List<TerminalNode> Underscore() { return getTokens(JavaRegexParser.Underscore); }
		public TerminalNode Underscore(int i) {
			return getToken(JavaRegexParser.Underscore, i);
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterAlpha_nums(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitAlpha_nums(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitAlpha_nums(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Alpha_numsContext alpha_nums() throws RecognitionException {
		Alpha_numsContext _localctx = new Alpha_numsContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_alpha_nums);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(931);
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
				setState(929);
				letter();
				}
				break;
			case Underscore:
				{
				setState(930);
				match(Underscore);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(938);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Underscore) | (1L << ALC) | (1L << BLC) | (1L << CLC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)))) != 0)) {
				{
				setState(936);
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
					setState(933);
					letter();
					}
					break;
				case Underscore:
					{
					setState(934);
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
					setState(935);
					digit();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(940);
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
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterNon_close_parens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitNon_close_parens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitNon_close_parens(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_close_parensContext non_close_parens() throws RecognitionException {
		Non_close_parensContext _localctx = new Non_close_parensContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_non_close_parens);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(942); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(941);
				non_close_paren();
				}
				}
				setState(944); 
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
		public TerminalNode CloseParen() { return getToken(JavaRegexParser.CloseParen, 0); }
		public Non_close_parenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_close_paren; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterNon_close_paren(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitNon_close_paren(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitNon_close_paren(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_close_parenContext non_close_paren() throws RecognitionException {
		Non_close_parenContext _localctx = new Non_close_parenContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_non_close_paren);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(946);
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
		public TerminalNode ALC() { return getToken(JavaRegexParser.ALC, 0); }
		public TerminalNode BLC() { return getToken(JavaRegexParser.BLC, 0); }
		public TerminalNode CLC() { return getToken(JavaRegexParser.CLC, 0); }
		public TerminalNode DLC() { return getToken(JavaRegexParser.DLC, 0); }
		public TerminalNode ELC() { return getToken(JavaRegexParser.ELC, 0); }
		public TerminalNode FLC() { return getToken(JavaRegexParser.FLC, 0); }
		public TerminalNode GLC() { return getToken(JavaRegexParser.GLC, 0); }
		public TerminalNode HLC() { return getToken(JavaRegexParser.HLC, 0); }
		public TerminalNode ILC() { return getToken(JavaRegexParser.ILC, 0); }
		public TerminalNode JLC() { return getToken(JavaRegexParser.JLC, 0); }
		public TerminalNode KLC() { return getToken(JavaRegexParser.KLC, 0); }
		public TerminalNode LLC() { return getToken(JavaRegexParser.LLC, 0); }
		public TerminalNode MLC() { return getToken(JavaRegexParser.MLC, 0); }
		public TerminalNode NLC() { return getToken(JavaRegexParser.NLC, 0); }
		public TerminalNode OLC() { return getToken(JavaRegexParser.OLC, 0); }
		public TerminalNode PLC() { return getToken(JavaRegexParser.PLC, 0); }
		public TerminalNode QLC() { return getToken(JavaRegexParser.QLC, 0); }
		public TerminalNode RLC() { return getToken(JavaRegexParser.RLC, 0); }
		public TerminalNode SLC() { return getToken(JavaRegexParser.SLC, 0); }
		public TerminalNode TLC() { return getToken(JavaRegexParser.TLC, 0); }
		public TerminalNode ULC() { return getToken(JavaRegexParser.ULC, 0); }
		public TerminalNode VLC() { return getToken(JavaRegexParser.VLC, 0); }
		public TerminalNode WLC() { return getToken(JavaRegexParser.WLC, 0); }
		public TerminalNode XLC() { return getToken(JavaRegexParser.XLC, 0); }
		public TerminalNode YLC() { return getToken(JavaRegexParser.YLC, 0); }
		public TerminalNode ZLC() { return getToken(JavaRegexParser.ZLC, 0); }
		public TerminalNode AUC() { return getToken(JavaRegexParser.AUC, 0); }
		public TerminalNode BUC() { return getToken(JavaRegexParser.BUC, 0); }
		public TerminalNode CUC() { return getToken(JavaRegexParser.CUC, 0); }
		public TerminalNode DUC() { return getToken(JavaRegexParser.DUC, 0); }
		public TerminalNode EUC() { return getToken(JavaRegexParser.EUC, 0); }
		public TerminalNode FUC() { return getToken(JavaRegexParser.FUC, 0); }
		public TerminalNode GUC() { return getToken(JavaRegexParser.GUC, 0); }
		public TerminalNode HUC() { return getToken(JavaRegexParser.HUC, 0); }
		public TerminalNode IUC() { return getToken(JavaRegexParser.IUC, 0); }
		public TerminalNode JUC() { return getToken(JavaRegexParser.JUC, 0); }
		public TerminalNode KUC() { return getToken(JavaRegexParser.KUC, 0); }
		public TerminalNode LUC() { return getToken(JavaRegexParser.LUC, 0); }
		public TerminalNode MUC() { return getToken(JavaRegexParser.MUC, 0); }
		public TerminalNode NUC() { return getToken(JavaRegexParser.NUC, 0); }
		public TerminalNode OUC() { return getToken(JavaRegexParser.OUC, 0); }
		public TerminalNode PUC() { return getToken(JavaRegexParser.PUC, 0); }
		public TerminalNode QUC() { return getToken(JavaRegexParser.QUC, 0); }
		public TerminalNode RUC() { return getToken(JavaRegexParser.RUC, 0); }
		public TerminalNode SUC() { return getToken(JavaRegexParser.SUC, 0); }
		public TerminalNode TUC() { return getToken(JavaRegexParser.TUC, 0); }
		public TerminalNode UUC() { return getToken(JavaRegexParser.UUC, 0); }
		public TerminalNode VUC() { return getToken(JavaRegexParser.VUC, 0); }
		public TerminalNode WUC() { return getToken(JavaRegexParser.WUC, 0); }
		public TerminalNode XUC() { return getToken(JavaRegexParser.XUC, 0); }
		public TerminalNode YUC() { return getToken(JavaRegexParser.YUC, 0); }
		public TerminalNode ZUC() { return getToken(JavaRegexParser.ZUC, 0); }
		public LetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).enterLetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaRegexListener ) ((JavaRegexListener)listener).exitLetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JavaRegexVisitor ) return ((JavaRegexVisitor<? extends T>)visitor).visitLetter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetterContext letter() throws RecognitionException {
		LetterContext _localctx = new LetterContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_letter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(948);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3}\u03b9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\2\3\3\3\3\3\3\7"+
		"\3U\n\3\f\3\16\3X\13\3\3\4\7\4[\n\4\f\4\16\4^\13\4\3\5\3\5\5\5b\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6|\n\6\3\7\3\7\3\7\5\7\u0081\n\7\3\b\3\b\3"+
		"\b\3\b\3\b\6\b\u0088\n\b\r\b\16\b\u0089\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0092"+
		"\n\b\f\b\16\b\u0095\13\b\3\b\3\b\3\b\3\b\6\b\u009b\n\b\r\b\16\b\u009c"+
		"\3\b\3\b\3\b\3\b\3\b\6\b\u00a4\n\b\r\b\16\b\u00a5\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\6\b\u00ae\n\b\r\b\16\b\u00af\3\b\3\b\3\b\3\b\3\b\7\b\u00b7\n\b\f"+
		"\b\16\b\u00ba\13\b\3\b\3\b\3\b\6\b\u00bf\n\b\r\b\16\b\u00c0\3\b\3\b\3"+
		"\b\3\b\6\b\u00c7\n\b\r\b\16\b\u00c8\3\b\3\b\5\b\u00cd\n\b\3\t\3\t\3\t"+
		"\3\t\7\t\u00d3\n\t\f\t\16\t\u00d6\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0101"+
		"\n\n\3\13\3\13\3\13\5\13\u0106\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\5\f\u0125\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0139\n\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0177"+
		"\n\17\3\20\6\20\u017a\n\20\r\20\16\20\u017b\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u019a\n\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u01ea\n\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u01f4\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u0201\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u020e\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\5\24\u021c\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\5\24\u022a\n\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\5\24\u0237\n\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\5\24\u0243\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\5\24\u0251\n\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0262\n\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24"+
		"\u0273\n\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u027f"+
		"\n\24\3\24\3\24\5\24\u0283\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u0294\n\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\5\25\u029d\n\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u02e9\n\25\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\5\26\u0328\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\5\27\u0334\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\5\30\u0350\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\5\31\u035a\n\31\3\32\3\32\3\33\3\33\5\33\u0360\n\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u036e\n\34\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u038a\n\35"+
		"\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37\u0397\n\37"+
		"\3 \3 \3!\6!\u039c\n!\r!\16!\u039d\3\"\3\"\3#\3#\3$\3$\5$\u03a6\n$\3$"+
		"\3$\3$\7$\u03ab\n$\f$\16$\u03ae\13$\3%\6%\u03b1\n%\r%\16%\u03b2\3&\3&"+
		"\3\'\3\'\3\'\2\2(\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\64\668:<>@BDFHJL\2\t\b\2GGKKQQVVbbmm\4\2\6\6\20\35\4\2su||\4\2sy||\3"+
		"\2s|\3\2\65\65\3\2?r\2\u0441\2N\3\2\2\2\4Q\3\2\2\2\6\\\3\2\2\2\b_\3\2"+
		"\2\2\n{\3\2\2\2\f\u0080\3\2\2\2\16\u00cc\3\2\2\2\20\u00ce\3\2\2\2\22\u0100"+
		"\3\2\2\2\24\u0105\3\2\2\2\26\u0124\3\2\2\2\30\u0138\3\2\2\2\32\u013a\3"+
		"\2\2\2\34\u0176\3\2\2\2\36\u0179\3\2\2\2 \u017d\3\2\2\2\"\u0199\3\2\2"+
		"\2$\u01e9\3\2\2\2&\u0282\3\2\2\2(\u02e8\3\2\2\2*\u0327\3\2\2\2,\u0333"+
		"\3\2\2\2.\u034f\3\2\2\2\60\u0359\3\2\2\2\62\u035b\3\2\2\2\64\u035f\3\2"+
		"\2\2\66\u036d\3\2\2\28\u0389\3\2\2\2:\u038b\3\2\2\2<\u0396\3\2\2\2>\u0398"+
		"\3\2\2\2@\u039b\3\2\2\2B\u039f\3\2\2\2D\u03a1\3\2\2\2F\u03a5\3\2\2\2H"+
		"\u03b0\3\2\2\2J\u03b4\3\2\2\2L\u03b6\3\2\2\2NO\5\4\3\2OP\7\2\2\3P\3\3"+
		"\2\2\2QV\5\6\4\2RS\7\63\2\2SU\5\6\4\2TR\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW"+
		"\3\2\2\2W\5\3\2\2\2XV\3\2\2\2Y[\5\b\5\2ZY\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2"+
		"\\]\3\2\2\2]\7\3\2\2\2^\\\3\2\2\2_a\5.\30\2`b\5\n\6\2a`\3\2\2\2ab\3\2"+
		"\2\2b\t\3\2\2\2cd\7#\2\2d|\5\f\7\2ef\7$\2\2f|\5\f\7\2gh\7%\2\2h|\5\f\7"+
		"\2ij\7&\2\2jk\5:\36\2kl\7\'\2\2lm\5\f\7\2m|\3\2\2\2no\7&\2\2op\5:\36\2"+
		"pq\7(\2\2qr\7\'\2\2rs\5\f\7\2s|\3\2\2\2tu\7&\2\2uv\5:\36\2vw\7(\2\2wx"+
		"\5:\36\2xy\7\'\2\2yz\5\f\7\2z|\3\2\2\2{c\3\2\2\2{e\3\2\2\2{g\3\2\2\2{"+
		"i\3\2\2\2{n\3\2\2\2{t\3\2\2\2|\13\3\2\2\2}\u0081\7$\2\2~\u0081\7#\2\2"+
		"\177\u0081\3\2\2\2\u0080}\3\2\2\2\u0080~\3\2\2\2\u0080\177\3\2\2\2\u0081"+
		"\r\3\2\2\2\u0082\u0083\7\37\2\2\u0083\u0084\7!\2\2\u0084\u0085\7 \2\2"+
		"\u0085\u0087\7\"\2\2\u0086\u0088\5\20\t\2\u0087\u0086\3\2\2\2\u0088\u0089"+
		"\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\7 \2\2\u008c\u00cd\3\2\2\2\u008d\u008e\7\37\2\2\u008e\u008f\7!"+
		"\2\2\u008f\u0093\7 \2\2\u0090\u0092\5\20\t\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0096\3\2"+
		"\2\2\u0095\u0093\3\2\2\2\u0096\u00cd\7 \2\2\u0097\u0098\7\37\2\2\u0098"+
		"\u009a\7!\2\2\u0099\u009b\5\20\t\2\u009a\u0099\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u009f\7 \2\2\u009f\u00cd\3\2\2\2\u00a0\u00a1\7\37\2\2\u00a1\u00a3\7!"+
		"\2\2\u00a2\u00a4\5\60\31\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\7 "+
		"\2\2\u00a8\u00cd\3\2\2\2\u00a9\u00aa\7\37\2\2\u00aa\u00ab\7 \2\2\u00ab"+
		"\u00ad\7\"\2\2\u00ac\u00ae\5\20\t\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3"+
		"\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1"+
		"\u00b2\7 \2\2\u00b2\u00cd\3\2\2\2\u00b3\u00b4\7\37\2\2\u00b4\u00b8\7 "+
		"\2\2\u00b5\u00b7\5\20\t\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba\u00b8\3\2"+
		"\2\2\u00bb\u00cd\7 \2\2\u00bc\u00be\7\37\2\2\u00bd\u00bf\5\20\t\2\u00be"+
		"\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2"+
		"\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\7 \2\2\u00c3\u00cd\3\2\2\2\u00c4"+
		"\u00c6\7\37\2\2\u00c5\u00c7\5\60\31\2\u00c6\u00c5\3\2\2\2\u00c7\u00c8"+
		"\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cb\7 \2\2\u00cb\u00cd\3\2\2\2\u00cc\u0082\3\2\2\2\u00cc\u008d\3\2"+
		"\2\2\u00cc\u0097\3\2\2\2\u00cc\u00a0\3\2\2\2\u00cc\u00a9\3\2\2\2\u00cc"+
		"\u00b3\3\2\2\2\u00cc\u00bc\3\2\2\2\u00cc\u00c4\3\2\2\2\u00cd\17\3\2\2"+
		"\2\u00ce\u00d4\5\60\31\2\u00cf\u00d0\7>\2\2\u00d0\u00d1\7>\2\2\u00d1\u00d3"+
		"\5\60\31\2\u00d2\u00cf\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2"+
		"\u00d4\u00d5\3\2\2\2\u00d5\21\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u0101"+
		"\5\24\13\2\u00d8\u00d9\7\61\2\2\u00d9\u0101\5:\36\2\u00da\u00db\7\61\2"+
		"\2\u00db\u00dc\7&\2\2\u00dc\u00dd\5:\36\2\u00dd\u00de\7\'\2\2\u00de\u0101"+
		"\3\2\2\2\u00df\u00e0\7\61\2\2\u00e0\u00e1\7&\2\2\u00e1\u00e2\7\"\2\2\u00e2"+
		"\u00e3\5:\36\2\u00e3\u00e4\7\'\2\2\u00e4\u0101\3\2\2\2\u00e5\u00e6\7\62"+
		"\2\2\u00e6\u00e7\7\66\2\2\u00e7\u00e8\5D#\2\u00e8\u00e9\7\67\2\2\u00e9"+
		"\u0101\3\2\2\2\u00ea\u00eb\7\62\2\2\u00eb\u00ec\78\2\2\u00ec\u00ed\5D"+
		"#\2\u00ed\u00ee\78\2\2\u00ee\u0101\3\2\2\2\u00ef\u00f0\7\61\2\2\u00f0"+
		"\u00f1\7&\2\2\u00f1\u00f2\5D#\2\u00f2\u00f3\7\'\2\2\u00f3\u0101\3\2\2"+
		"\2\u00f4\u00f5\7\62\2\2\u00f5\u00f6\7&\2\2\u00f6\u00f7\5D#\2\u00f7\u00f8"+
		"\7\'\2\2\u00f8\u0101\3\2\2\2\u00f9\u00fa\7\64\2\2\u00fa\u00fb\7#\2\2\u00fb"+
		"\u00fc\7h\2\2\u00fc\u00fd\7<\2\2\u00fd\u00fe\5D#\2\u00fe\u00ff\7\65\2"+
		"\2\u00ff\u0101\3\2\2\2\u0100\u00d7\3\2\2\2\u0100\u00d8\3\2\2\2\u0100\u00da"+
		"\3\2\2\2\u0100\u00df\3\2\2\2\u0100\u00e5\3\2\2\2\u0100\u00ea\3\2\2\2\u0100"+
		"\u00ef\3\2\2\2\u0100\u00f4\3\2\2\2\u0100\u00f9\3\2\2\2\u0101\23\3\2\2"+
		"\2\u0102\u0106\5<\37\2\u0103\u0104\7\f\2\2\u0104\u0106\5B\"\2\u0105\u0102"+
		"\3\2\2\2\u0105\u0103\3\2\2\2\u0106\25\3\2\2\2\u0107\u0108\7\64\2\2\u0108"+
		"\u0109\7#\2\2\u0109\u010a\7\66\2\2\u010a\u010b\5D#\2\u010b\u010c\7\67"+
		"\2\2\u010c\u010d\5\4\3\2\u010d\u010e\7\65\2\2\u010e\u0125\3\2\2\2\u010f"+
		"\u0110\7\64\2\2\u0110\u0111\7#\2\2\u0111\u0112\78\2\2\u0112\u0113\5D#"+
		"\2\u0113\u0114\78\2\2\u0114\u0115\5\4\3\2\u0115\u0116\7\65\2\2\u0116\u0125"+
		"\3\2\2\2\u0117\u0118\7\64\2\2\u0118\u0119\7#\2\2\u0119\u011a\7h\2\2\u011a"+
		"\u011b\7\66\2\2\u011b\u011c\5D#\2\u011c\u011d\7\67\2\2\u011d\u011e\5\4"+
		"\3\2\u011e\u011f\7\65\2\2\u011f\u0125\3\2\2\2\u0120\u0121\7\64\2\2\u0121"+
		"\u0122\5\4\3\2\u0122\u0123\7\65\2\2\u0123\u0125\3\2\2\2\u0124\u0107\3"+
		"\2\2\2\u0124\u010f\3\2\2\2\u0124\u0117\3\2\2\2\u0124\u0120\3\2\2\2\u0125"+
		"\27\3\2\2\2\u0126\u0127\7\64\2\2\u0127\u0128\7#\2\2\u0128\u0129\7:\2\2"+
		"\u0129\u012a\5\4\3\2\u012a\u012b\7\65\2\2\u012b\u0139\3\2\2\2\u012c\u012d"+
		"\7\64\2\2\u012d\u012e\7#\2\2\u012e\u012f\7\63\2\2\u012f\u0130\5\4\3\2"+
		"\u0130\u0131\7\65\2\2\u0131\u0139\3\2\2\2\u0132\u0133\7\64\2\2\u0133\u0134"+
		"\7#\2\2\u0134\u0135\7\67\2\2\u0135\u0136\5\4\3\2\u0136\u0137\7\65\2\2"+
		"\u0137\u0139\3\2\2\2\u0138\u0126\3\2\2\2\u0138\u012c\3\2\2\2\u0138\u0132"+
		"\3\2\2\2\u0139\31\3\2\2\2\u013a\u013b\7\64\2\2\u013b\u013c\7#\2\2\u013c"+
		"\u013d\7;\2\2\u013d\u013e\5H%\2\u013e\u013f\7\65\2\2\u013f\33\3\2\2\2"+
		"\u0140\u0141\7\64\2\2\u0141\u0142\7#\2\2\u0142\u0143\5\36\20\2\u0143\u0144"+
		"\7\"\2\2\u0144\u0145\5\36\20\2\u0145\u0146\7\65\2\2\u0146\u0177\3\2\2"+
		"\2\u0147\u0148\7\64\2\2\u0148\u0149\7#\2\2\u0149\u014a\5\36\20\2\u014a"+
		"\u014b\7\65\2\2\u014b\u0177\3\2\2\2\u014c\u014d\7\64\2\2\u014d\u014e\7"+
		"#\2\2\u014e\u014f\7\"\2\2\u014f\u0150\5\36\20\2\u0150\u0151\7\65\2\2\u0151"+
		"\u0177\3\2\2\2\u0152\u0153\7\64\2\2\u0153\u0154\7%\2\2\u0154\u0155\7f"+
		"\2\2\u0155\u0156\7g\2\2\u0156\u0157\79\2\2\u0157\u0158\7k\2\2\u0158\u0159"+
		"\7l\2\2\u0159\u015a\7Y\2\2\u015a\u015b\7j\2\2\u015b\u015c\7l\2\2\u015c"+
		"\u015d\79\2\2\u015d\u015e\7g\2\2\u015e\u015f\7h\2\2\u015f\u0160\7l\2\2"+
		"\u0160\u0177\7\65\2\2\u0161\u0162\7\64\2\2\u0162\u0163\7%\2\2\u0163\u0164"+
		"\7m\2\2\u0164\u0165\7l\2\2\u0165\u0166\7^\2\2\u0166\u0167\7z\2\2\u0167"+
		"\u0177\7\65\2\2\u0168\u0169\7\64\2\2\u0169\u016a\7%\2\2\u016a\u016b\7"+
		"m\2\2\u016b\u016c\7l\2\2\u016c\u016d\7^\2\2\u016d\u016e\7s\2\2\u016e\u016f"+
		"\7x\2\2\u016f\u0177\7\65\2\2\u0170\u0171\7\64\2\2\u0171\u0172\7%\2\2\u0172"+
		"\u0173\7m\2\2\u0173\u0174\7[\2\2\u0174\u0175\7h\2\2\u0175\u0177\7\65\2"+
		"\2\u0176\u0140\3\2\2\2\u0176\u0147\3\2\2\2\u0176\u014c\3\2\2\2\u0176\u0152"+
		"\3\2\2\2\u0176\u0161\3\2\2\2\u0176\u0168\3\2\2\2\u0176\u0170\3\2\2\2\u0177"+
		"\35\3\2\2\2\u0178\u017a\5 \21\2\u0179\u0178\3\2\2\2\u017a\u017b\3\2\2"+
		"\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c\37\3\2\2\2\u017d\u017e"+
		"\t\2\2\2\u017e!\3\2\2\2\u017f\u0180\7\64\2\2\u0180\u0181\7#\2\2\u0181"+
		"\u0182\7<\2\2\u0182\u0183\5\4\3\2\u0183\u0184\7\65\2\2\u0184\u019a\3\2"+
		"\2\2\u0185\u0186\7\64\2\2\u0186\u0187\7#\2\2\u0187\u0188\7=\2\2\u0188"+
		"\u0189\5\4\3\2\u0189\u018a\7\65\2\2\u018a\u019a\3\2\2\2\u018b\u018c\7"+
		"\64\2\2\u018c\u018d\7#\2\2\u018d\u018e\7\66\2\2\u018e\u018f\7<\2\2\u018f"+
		"\u0190\5\4\3\2\u0190\u0191\7\65\2\2\u0191\u019a\3\2\2\2\u0192\u0193\7"+
		"\64\2\2\u0193\u0194\7#\2\2\u0194\u0195\7\66\2\2\u0195\u0196\7=\2\2\u0196"+
		"\u0197\5\4\3\2\u0197\u0198\7\65\2\2\u0198\u019a\3\2\2\2\u0199\u017f\3"+
		"\2\2\2\u0199\u0185\3\2\2\2\u0199\u018b\3\2\2\2\u0199\u0192\3\2\2\2\u019a"+
		"#\3\2\2\2\u019b\u019c\7\64\2\2\u019c\u019d\7#\2\2\u019d\u019e\7j\2\2\u019e"+
		"\u01ea\7\65\2\2\u019f\u01a0\7\64\2\2\u01a0\u01a1\7#\2\2\u01a1\u01a2\5"+
		":\36\2\u01a2\u01a3\7\65\2\2\u01a3\u01ea\3\2\2\2\u01a4\u01a5\7\64\2\2\u01a5"+
		"\u01a6\7#\2\2\u01a6\u01a7\7$\2\2\u01a7\u01a8\5:\36\2\u01a8\u01a9\7\65"+
		"\2\2\u01a9\u01ea\3\2\2\2\u01aa\u01ab\7\64\2\2\u01ab\u01ac\7#\2\2\u01ac"+
		"\u01ad\7\"\2\2\u01ad\u01ae\5:\36\2\u01ae\u01af\7\65\2\2\u01af\u01ea\3"+
		"\2\2\2\u01b0\u01b1\7\64\2\2\u01b1\u01b2\7#\2\2\u01b2\u01b3\7>\2\2\u01b3"+
		"\u01b4\5D#\2\u01b4\u01b5\7\65\2\2\u01b5\u01ea\3\2\2\2\u01b6\u01b7\7\64"+
		"\2\2\u01b7\u01b8\7#\2\2\u01b8\u01b9\7h\2\2\u01b9\u01ba\7\67\2\2\u01ba"+
		"\u01bb\5D#\2\u01bb\u01bc\7\65\2\2\u01bc\u01ea\3\2\2\2\u01bd\u01be\7\61"+
		"\2\2\u01be\u01bf\7\66\2\2\u01bf\u01c0\5D#\2\u01c0\u01c1\7\67\2\2\u01c1"+
		"\u01ea\3\2\2\2\u01c2\u01c3\7\61\2\2\u01c3\u01c4\78\2\2\u01c4\u01c5\5D"+
		"#\2\u01c5\u01c6\78\2\2\u01c6\u01ea\3\2\2\2\u01c7\u01c8\7\61\2\2\u01c8"+
		"\u01c9\7\66\2\2\u01c9\u01ca\5:\36\2\u01ca\u01cb\7\67\2\2\u01cb\u01ea\3"+
		"\2\2\2\u01cc\u01cd\7\61\2\2\u01cd\u01ce\78\2\2\u01ce\u01cf\5:\36\2\u01cf"+
		"\u01d0\78\2\2\u01d0\u01ea\3\2\2\2\u01d1\u01d2\7\61\2\2\u01d2\u01d3\7\66"+
		"\2\2\u01d3\u01d4\7$\2\2\u01d4\u01d5\5:\36\2\u01d5\u01d6\7\67\2\2\u01d6"+
		"\u01ea\3\2\2\2\u01d7\u01d8\7\61\2\2\u01d8\u01d9\78\2\2\u01d9\u01da\7$"+
		"\2\2\u01da\u01db\5:\36\2\u01db\u01dc\78\2\2\u01dc\u01ea\3\2\2\2\u01dd"+
		"\u01de\7\61\2\2\u01de\u01df\7\66\2\2\u01df\u01e0\7\"\2\2\u01e0\u01e1\5"+
		":\36\2\u01e1\u01e2\7\67\2\2\u01e2\u01ea\3\2\2\2\u01e3\u01e4\7\61\2\2\u01e4"+
		"\u01e5\78\2\2\u01e5\u01e6\7\"\2\2\u01e6\u01e7\5:\36\2\u01e7\u01e8\78\2"+
		"\2\u01e8\u01ea\3\2\2\2\u01e9\u019b\3\2\2\2\u01e9\u019f\3\2\2\2\u01e9\u01a4"+
		"\3\2\2\2\u01e9\u01aa\3\2\2\2\u01e9\u01b0\3\2\2\2\u01e9\u01b6\3\2\2\2\u01e9"+
		"\u01bd\3\2\2\2\u01e9\u01c2\3\2\2\2\u01e9\u01c7\3\2\2\2\u01e9\u01cc\3\2"+
		"\2\2\u01e9\u01d1\3\2\2\2\u01e9\u01d7\3\2\2\2\u01e9\u01dd\3\2\2\2\u01e9"+
		"\u01e3\3\2\2\2\u01ea%\3\2\2\2\u01eb\u01ec\7\64\2\2\u01ec\u01ed\7#\2\2"+
		"\u01ed\u01ee\7\64\2\2\u01ee\u01ef\5:\36\2\u01ef\u01f0\7\65\2\2\u01f0\u01f3"+
		"\5\4\3\2\u01f1\u01f2\7\63\2\2\u01f2\u01f4\5\4\3\2\u01f3\u01f1\3\2\2\2"+
		"\u01f3\u01f4\3\2\2\2\u01f4\u01f5\3\2\2\2\u01f5\u01f6\7\65\2\2\u01f6\u0283"+
		"\3\2\2\2\u01f7\u01f8\7\64\2\2\u01f8\u01f9\7#\2\2\u01f9\u01fa\7\64\2\2"+
		"\u01fa\u01fb\7$\2\2\u01fb\u01fc\5:\36\2\u01fc\u01fd\7\65\2\2\u01fd\u0200"+
		"\5\4\3\2\u01fe\u01ff\7\63\2\2\u01ff\u0201\5\4\3\2\u0200\u01fe\3\2\2\2"+
		"\u0200\u0201\3\2\2\2\u0201\u0202\3\2\2\2\u0202\u0203\7\65\2\2\u0203\u0283"+
		"\3\2\2\2\u0204\u0205\7\64\2\2\u0205\u0206\7#\2\2\u0206\u0207\7\64\2\2"+
		"\u0207\u0208\7\"\2\2\u0208\u0209\5:\36\2\u0209\u020a\7\65\2\2\u020a\u020d"+
		"\5\4\3\2\u020b\u020c\7\63\2\2\u020c\u020e\5\4\3\2\u020d\u020b\3\2\2\2"+
		"\u020d\u020e\3\2\2\2\u020e\u020f\3\2\2\2\u020f\u0210\7\65\2\2\u0210\u0283"+
		"\3\2\2\2\u0211\u0212\7\64\2\2\u0212\u0213\7#\2\2\u0213\u0214\7\64\2\2"+
		"\u0214\u0215\7\66\2\2\u0215\u0216\5D#\2\u0216\u0217\7\67\2\2\u0217\u0218"+
		"\7\65\2\2\u0218\u021b\5\4\3\2\u0219\u021a\7\63\2\2\u021a\u021c\5\4\3\2"+
		"\u021b\u0219\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u021e"+
		"\7\65\2\2\u021e\u0283\3\2\2\2\u021f\u0220\7\64\2\2\u0220\u0221\7#\2\2"+
		"\u0221\u0222\7\64\2\2\u0222\u0223\78\2\2\u0223\u0224\5D#\2\u0224\u0225"+
		"\78\2\2\u0225\u0226\7\65\2\2\u0226\u0229\5\4\3\2\u0227\u0228\7\63\2\2"+
		"\u0228\u022a\5\4\3\2\u0229\u0227\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022b"+
		"\3\2\2\2\u022b\u022c\7\65\2\2\u022c\u0283\3\2\2\2\u022d\u022e\7\64\2\2"+
		"\u022e\u022f\7#\2\2\u022f\u0230\7\64\2\2\u0230\u0231\7j\2\2\u0231\u0232"+
		"\5:\36\2\u0232\u0233\7\65\2\2\u0233\u0236\5\4\3\2\u0234\u0235\7\63\2\2"+
		"\u0235\u0237\5\4\3\2\u0236\u0234\3\2\2\2\u0236\u0237\3\2\2\2\u0237\u0238"+
		"\3\2\2\2\u0238\u0239\7\65\2\2\u0239\u0283\3\2\2\2\u023a\u023b\7\64\2\2"+
		"\u023b\u023c\7#\2\2\u023c\u023d\7\64\2\2\u023d\u023e\7j\2\2\u023e\u023f"+
		"\7\65\2\2\u023f\u0242\5\4\3\2\u0240\u0241\7\63\2\2\u0241\u0243\5\4\3\2"+
		"\u0242\u0240\3\2\2\2\u0242\u0243\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0245"+
		"\7\65\2\2\u0245\u0283\3\2\2\2\u0246\u0247\7\64\2\2\u0247\u0248\7#\2\2"+
		"\u0248\u0249\7\64\2\2\u0249\u024a\7j\2\2\u024a\u024b\7>\2\2\u024b\u024c"+
		"\5D#\2\u024c\u024d\7\65\2\2\u024d\u0250\5\4\3\2\u024e\u024f\7\63\2\2\u024f"+
		"\u0251\5\4\3\2\u0250\u024e\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u0252\3\2"+
		"\2\2\u0252\u0253\7\65\2\2\u0253\u0283\3\2\2\2\u0254\u0255\7\64\2\2\u0255"+
		"\u0256\7#\2\2\u0256\u0257\7\64\2\2\u0257\u0258\7\\\2\2\u0258\u0259\7]"+
		"\2\2\u0259\u025a\7^\2\2\u025a\u025b\7a\2\2\u025b\u025c\7f\2\2\u025c\u025d"+
		"\7]\2\2\u025d\u025e\7\65\2\2\u025e\u0261\5\4\3\2\u025f\u0260\7\63\2\2"+
		"\u0260\u0262\5\4\3\2\u0261\u025f\3\2\2\2\u0261\u0262\3\2\2\2\u0262\u0263"+
		"\3\2\2\2\u0263\u0264\7\65\2\2\u0264\u0283\3\2\2\2\u0265\u0266\7\64\2\2"+
		"\u0266\u0267\7#\2\2\u0267\u0268\7\64\2\2\u0268\u0269\7?\2\2\u0269\u026a"+
		"\7Q\2\2\u026a\u026b\7Q\2\2\u026b\u026c\7C\2\2\u026c\u026d\7P\2\2\u026d"+
		"\u026e\7R\2\2\u026e\u026f\7\65\2\2\u026f\u0272\5\4\3\2\u0270\u0271\7\63"+
		"\2\2\u0271\u0273\5\4\3\2\u0272\u0270\3\2\2\2\u0272\u0273\3\2\2\2\u0273"+
		"\u0274\3\2\2\2\u0274\u0275\7\65\2\2\u0275\u0283\3\2\2\2\u0276\u0277\7"+
		"\64\2\2\u0277\u0278\7#\2\2\u0278\u0279\7\64\2\2\u0279\u027a\5D#\2\u027a"+
		"\u027b\7\65\2\2\u027b\u027e\5\4\3\2\u027c\u027d\7\63\2\2\u027d\u027f\5"+
		"\4\3\2\u027e\u027c\3\2\2\2\u027e\u027f\3\2\2\2\u027f\u0280\3\2\2\2\u0280"+
		"\u0281\7\65\2\2\u0281\u0283\3\2\2\2\u0282\u01eb\3\2\2\2\u0282\u01f7\3"+
		"\2\2\2\u0282\u0204\3\2\2\2\u0282\u0211\3\2\2\2\u0282\u021f\3\2\2\2\u0282"+
		"\u022d\3\2\2\2\u0282\u023a\3\2\2\2\u0282\u0246\3\2\2\2\u0282\u0254\3\2"+
		"\2\2\u0282\u0265\3\2\2\2\u0282\u0276\3\2\2\2\u0283\'\3\2\2\2\u0284\u0285"+
		"\7\64\2\2\u0285\u0286\7%\2\2\u0286\u0287\7Y\2\2\u0287\u0288\7[\2\2\u0288"+
		"\u0289\7[\2\2\u0289\u028a\7]\2\2\u028a\u028b\7h\2\2\u028b\u028c\7l\2\2"+
		"\u028c\u02e9\7\65\2\2\u028d\u028e\7\64\2\2\u028e\u028f\7%\2\2\u028f\u0293"+
		"\7^\2\2\u0290\u0291\7Y\2\2\u0291\u0292\7a\2\2\u0292\u0294\7d\2\2\u0293"+
		"\u0290\3\2\2\2\u0293\u0294\3\2\2\2\u0294\u0295\3\2\2\2\u0295\u02e9\7\65"+
		"\2\2\u0296\u0297\7\64\2\2\u0297\u029c\7%\2\2\u0298\u0299\7e\2\2\u0299"+
		"\u029a\7Y\2\2\u029a\u029b\7j\2\2\u029b\u029d\7c\2\2\u029c\u0298\3\2\2"+
		"\2\u029c\u029d\3\2\2\2\u029d\u029e\3\2\2\2\u029e\u029f\7:\2\2\u029f\u02a0"+
		"\7f\2\2\u02a0\u02a1\7Y\2\2\u02a1\u02a2\7e\2\2\u02a2\u02a3\7]\2\2\u02a3"+
		"\u02e9\7\65\2\2\u02a4\u02a5\7\64\2\2\u02a5\u02a6\7%\2\2\u02a6\u02a7\7"+
		"[\2\2\u02a7\u02a8\7g\2\2\u02a8\u02a9\7e\2\2\u02a9\u02aa\7e\2\2\u02aa\u02ab"+
		"\7a\2\2\u02ab\u02ac\7l\2\2\u02ac\u02e9\7\65\2\2\u02ad\u02ae\7\64\2\2\u02ae"+
		"\u02af\7%\2\2\u02af\u02b0\7h\2\2\u02b0\u02b1\7j\2\2\u02b1\u02b2\7m\2\2"+
		"\u02b2\u02b3\7f\2\2\u02b3\u02b4\7]\2\2\u02b4\u02e9\7\65\2\2\u02b5\u02b6"+
		"\7\64\2\2\u02b6\u02b7\7%\2\2\u02b7\u02b8\7h\2\2\u02b8\u02b9\7j\2\2\u02b9"+
		"\u02ba\7m\2\2\u02ba\u02bb\7f\2\2\u02bb\u02bc\7]\2\2\u02bc\u02bd\7:\2\2"+
		"\u02bd\u02be\7f\2\2\u02be\u02bf\7Y\2\2\u02bf\u02c0\7e\2\2\u02c0\u02c1"+
		"\7]\2\2\u02c1\u02e9\7\65\2\2\u02c2\u02c3\7\64\2\2\u02c3\u02c4\7%\2\2\u02c4"+
		"\u02c5\7k\2\2\u02c5\u02c6\7c\2\2\u02c6\u02c7\7a\2\2\u02c7\u02c8\7h\2\2"+
		"\u02c8\u02e9\7\65\2\2\u02c9\u02ca\7\64\2\2\u02ca\u02cb\7%\2\2\u02cb\u02cc"+
		"\7k\2\2\u02cc\u02cd\7c\2\2\u02cd\u02ce\7a\2\2\u02ce\u02cf\7h\2\2\u02cf"+
		"\u02d0\7:\2\2\u02d0\u02d1\7f\2\2\u02d1\u02d2\7Y\2\2\u02d2\u02d3\7e\2\2"+
		"\u02d3\u02d4\7]\2\2\u02d4\u02e9\7\65\2\2\u02d5\u02d6\7\64\2\2\u02d6\u02d7"+
		"\7%\2\2\u02d7\u02d8\7l\2\2\u02d8\u02d9\7`\2\2\u02d9\u02da\7]\2\2\u02da"+
		"\u02db\7f\2\2\u02db\u02e9\7\65\2\2\u02dc\u02dd\7\64\2\2\u02dd\u02de\7"+
		"%\2\2\u02de\u02df\7l\2\2\u02df\u02e0\7`\2\2\u02e0\u02e1\7]\2\2\u02e1\u02e2"+
		"\7f\2\2\u02e2\u02e3\7:\2\2\u02e3\u02e4\7f\2\2\u02e4\u02e5\7Y\2\2\u02e5"+
		"\u02e6\7e\2\2\u02e6\u02e7\7]\2\2\u02e7\u02e9\7\65\2\2\u02e8\u0284\3\2"+
		"\2\2\u02e8\u028d\3\2\2\2\u02e8\u0296\3\2\2\2\u02e8\u02a4\3\2\2\2\u02e8"+
		"\u02ad\3\2\2\2\u02e8\u02b5\3\2\2\2\u02e8\u02c2\3\2\2\2\u02e8\u02c9\3\2"+
		"\2\2\u02e8\u02d5\3\2\2\2\u02e8\u02dc\3\2\2\2\u02e9)\3\2\2\2\u02ea\u02eb"+
		"\7\64\2\2\u02eb\u02ec\7%\2\2\u02ec\u02ed\7[\2\2\u02ed\u02ee\7j\2\2\u02ee"+
		"\u0328\7\65\2\2\u02ef\u02f0\7\64\2\2\u02f0\u02f1\7%\2\2\u02f1\u02f2\7"+
		"d\2\2\u02f2\u02f3\7^\2\2\u02f3\u0328\7\65\2\2\u02f4\u02f5\7\64\2\2\u02f5"+
		"\u02f6\7%\2\2\u02f6\u02f7\7[\2\2\u02f7\u02f8\7j\2\2\u02f8\u02f9\7d\2\2"+
		"\u02f9\u02fa\7^\2\2\u02fa\u0328\7\65\2\2\u02fb\u02fc\7\64\2\2\u02fc\u02fd"+
		"\7%\2\2\u02fd\u02fe\7Y\2\2\u02fe\u02ff\7f\2\2\u02ff\u0300\7q\2\2\u0300"+
		"\u0301\7[\2\2\u0301\u0302\7j\2\2\u0302\u0303\7d\2\2\u0303\u0304\7^\2\2"+
		"\u0304\u0328\7\65\2\2\u0305\u0306\7\64\2\2\u0306\u0307\7%\2\2\u0307\u0308"+
		"\7Y\2\2\u0308\u0309\7f\2\2\u0309\u030a\7q\2\2\u030a\u0328\7\65\2\2\u030b"+
		"\u030c\7\64\2\2\u030c\u030d\7%\2\2\u030d\u030e\7Z\2\2\u030e\u030f\7k\2"+
		"\2\u030f\u0310\7j\2\2\u0310\u0311\79\2\2\u0311\u0312\7Y\2\2\u0312\u0313"+
		"\7f\2\2\u0313\u0314\7q\2\2\u0314\u0315\7[\2\2\u0315\u0316\7j\2\2\u0316"+
		"\u0317\7d\2\2\u0317\u0318\7^\2\2\u0318\u0328\7\65\2\2\u0319\u031a\7\64"+
		"\2\2\u031a\u031b\7%\2\2\u031b\u031c\7Z\2\2\u031c\u031d\7k\2\2\u031d\u031e"+
		"\7j\2\2\u031e\u031f\79\2\2\u031f\u0320\7m\2\2\u0320\u0321\7f\2\2\u0321"+
		"\u0322\7a\2\2\u0322\u0323\7[\2\2\u0323\u0324\7g\2\2\u0324\u0325\7\\\2"+
		"\2\u0325\u0326\7]\2\2\u0326\u0328\7\65\2\2\u0327\u02ea\3\2\2\2\u0327\u02ef"+
		"\3\2\2\2\u0327\u02f4\3\2\2\2\u0327\u02fb\3\2\2\2\u0327\u0305\3\2\2\2\u0327"+
		"\u030b\3\2\2\2\u0327\u0319\3\2\2\2\u0328+\3\2\2\2\u0329\u032a\7\64\2\2"+
		"\u032a\u032b\7#\2\2\u032b\u032c\7[\2\2\u032c\u0334\7\65\2\2\u032d\u032e"+
		"\7\64\2\2\u032e\u032f\7#\2\2\u032f\u0330\7[\2\2\u0330\u0331\5:\36\2\u0331"+
		"\u0332\7\65\2\2\u0332\u0334\3\2\2\2\u0333\u0329\3\2\2\2\u0333\u032d\3"+
		"\2\2\2\u0334-\3\2\2\2\u0335\u0350\5$\23\2\u0336\u0350\5\62\32\2\u0337"+
		"\u0350\5\64\33\2\u0338\u0350\5\16\b\2\u0339\u0350\5\26\f\2\u033a\u0350"+
		"\5\30\r\2\u033b\u0350\5\32\16\2\u033c\u0350\5\34\17\2\u033d\u0350\5\""+
		"\22\2\u033e\u0350\5\22\n\2\u033f\u0350\5&\24\2\u0340\u0350\5(\25\2\u0341"+
		"\u0350\5*\26\2\u0342\u0350\5,\27\2\u0343\u0350\7\16\2\2\u0344\u0350\7"+
		"!\2\2\u0345\u0350\7+\2\2\u0346\u0350\7)\2\2\u0347\u0350\7*\2\2\u0348\u0350"+
		"\7,\2\2\u0349\u0350\7-\2\2\u034a\u0350\7.\2\2\u034b\u0350\7/\2\2\u034c"+
		"\u0350\7\60\2\2\u034d\u0350\7\17\2\2\u034e\u0350\7\36\2\2\u034f\u0335"+
		"\3\2\2\2\u034f\u0336\3\2\2\2\u034f\u0337\3\2\2\2\u034f\u0338\3\2\2\2\u034f"+
		"\u0339\3\2\2\2\u034f\u033a\3\2\2\2\u034f\u033b\3\2\2\2\u034f\u033c\3\2"+
		"\2\2\u034f\u033d\3\2\2\2\u034f\u033e\3\2\2\2\u034f\u033f\3\2\2\2\u034f"+
		"\u0340\3\2\2\2\u034f\u0341\3\2\2\2\u034f\u0342\3\2\2\2\u034f\u0343\3\2"+
		"\2\2\u034f\u0344\3\2\2\2\u034f\u0345\3\2\2\2\u034f\u0346\3\2\2\2\u034f"+
		"\u0347\3\2\2\2\u034f\u0348\3\2\2\2\u034f\u0349\3\2\2\2\u034f\u034a\3\2"+
		"\2\2\u034f\u034b\3\2\2\2\u034f\u034c\3\2\2\2\u034f\u034d\3\2\2\2\u034f"+
		"\u034e\3\2\2\2\u0350/\3\2\2\2\u0351\u035a\5\16\b\2\u0352\u0353\5\66\34"+
		"\2\u0353\u0354\7\"\2\2\u0354\u0355\5\66\34\2\u0355\u035a\3\2\2\2\u0356"+
		"\u035a\5\62\32\2\u0357\u035a\5\66\34\2\u0358\u035a\5\24\13\2\u0359\u0351"+
		"\3\2\2\2\u0359\u0352\3\2\2\2\u0359\u0356\3\2\2\2\u0359\u0357\3\2\2\2\u0359"+
		"\u0358\3\2\2\2\u035a\61\3\2\2\2\u035b\u035c\t\3\2\2\u035c\63\3\2\2\2\u035d"+
		"\u0360\58\35\2\u035e\u0360\7 \2\2\u035f\u035d\3\2\2\2\u035f\u035e\3\2"+
		"\2\2\u0360\65\3\2\2\2\u0361\u036e\58\35\2\u0362\u036e\7\16\2\2\u0363\u036e"+
		"\7\37\2\2\u0364\u036e\7!\2\2\u0365\u036e\7#\2\2\u0366\u036e\7$\2\2\u0367"+
		"\u036e\7%\2\2\u0368\u036e\7)\2\2\u0369\u036e\7,\2\2\u036a\u036e\7\63\2"+
		"\2\u036b\u036e\7\64\2\2\u036c\u036e\7\65\2\2\u036d\u0361\3\2\2\2\u036d"+
		"\u0362\3\2\2\2\u036d\u0363\3\2\2\2\u036d\u0364\3\2\2\2\u036d\u0365\3\2"+
		"\2\2\u036d\u0366\3\2\2\2\u036d\u0367\3\2\2\2\u036d\u0368\3\2\2\2\u036d"+
		"\u0369\3\2\2\2\u036d\u036a\3\2\2\2\u036d\u036b\3\2\2\2\u036d\u036c\3\2"+
		"\2\2\u036e\67\3\2\2\2\u036f\u038a\5<\37\2\u0370\u038a\5L\'\2\u0371\u038a"+
		"\5B\"\2\u0372\u038a\7\5\2\2\u0373\u038a\7\7\2\2\u0374\u038a\7\b\2\2\u0375"+
		"\u038a\7\t\2\2\u0376\u038a\7\n\2\2\u0377\u038a\7\13\2\2\u0378\u038a\7"+
		"\r\2\2\u0379\u038a\7\3\2\2\u037a\u038a\7\4\2\2\u037b\u038a\7&\2\2\u037c"+
		"\u038a\7\'\2\2\u037d\u038a\7(\2\2\u037e\u038a\7\"\2\2\u037f\u038a\7\66"+
		"\2\2\u0380\u038a\7\67\2\2\u0381\u038a\78\2\2\u0382\u038a\79\2\2\u0383"+
		"\u038a\7:\2\2\u0384\u038a\7;\2\2\u0385\u038a\7<\2\2\u0386\u038a\7=\2\2"+
		"\u0387\u038a\7>\2\2\u0388\u038a\7}\2\2\u0389\u036f\3\2\2\2\u0389\u0370"+
		"\3\2\2\2\u0389\u0371\3\2\2\2\u0389\u0372\3\2\2\2\u0389\u0373\3\2\2\2\u0389"+
		"\u0374\3\2\2\2\u0389\u0375\3\2\2\2\u0389\u0376\3\2\2\2\u0389\u0377\3\2"+
		"\2\2\u0389\u0378\3\2\2\2\u0389\u0379\3\2\2\2\u0389\u037a\3\2\2\2\u0389"+
		"\u037b\3\2\2\2\u0389\u037c\3\2\2\2\u0389\u037d\3\2\2\2\u0389\u037e\3\2"+
		"\2\2\u0389\u037f\3\2\2\2\u0389\u0380\3\2\2\2\u0389\u0381\3\2\2\2\u0389"+
		"\u0382\3\2\2\2\u0389\u0383\3\2\2\2\u0389\u0384\3\2\2\2\u0389\u0385\3\2"+
		"\2\2\u0389\u0386\3\2\2\2\u0389\u0387\3\2\2\2\u0389\u0388\3\2\2\2\u038a"+
		"9\3\2\2\2\u038b\u038c\5@!\2\u038c;\3\2\2\2\u038d\u038e\7\f\2\2\u038e\u038f"+
		"\t\4\2\2\u038f\u0390\5> \2\u0390\u0391\5> \2\u0391\u0397\3\2\2\2\u0392"+
		"\u0393\7\f\2\2\u0393\u0394\5> \2\u0394\u0395\5> \2\u0395\u0397\3\2\2\2"+
		"\u0396\u038d\3\2\2\2\u0396\u0392\3\2\2\2\u0397=\3\2\2\2\u0398\u0399\t"+
		"\5\2\2\u0399?\3\2\2\2\u039a\u039c\5B\"\2\u039b\u039a\3\2\2\2\u039c\u039d"+
		"\3\2\2\2\u039d\u039b\3\2\2\2\u039d\u039e\3\2\2\2\u039eA\3\2\2\2\u039f"+
		"\u03a0\t\6\2\2\u03a0C\3\2\2\2\u03a1\u03a2\5F$\2\u03a2E\3\2\2\2\u03a3\u03a6"+
		"\5L\'\2\u03a4\u03a6\79\2\2\u03a5\u03a3\3\2\2\2\u03a5\u03a4\3\2\2\2\u03a6"+
		"\u03ac\3\2\2\2\u03a7\u03ab\5L\'\2\u03a8\u03ab\79\2\2\u03a9\u03ab\5B\""+
		"\2\u03aa\u03a7\3\2\2\2\u03aa\u03a8\3\2\2\2\u03aa\u03a9\3\2\2\2\u03ab\u03ae"+
		"\3\2\2\2\u03ac\u03aa\3\2\2\2\u03ac\u03ad\3\2\2\2\u03adG\3\2\2\2\u03ae"+
		"\u03ac\3\2\2\2\u03af\u03b1\5J&\2\u03b0\u03af\3\2\2\2\u03b1\u03b2\3\2\2"+
		"\2\u03b2\u03b0\3\2\2\2\u03b2\u03b3\3\2\2\2\u03b3I\3\2\2\2\u03b4\u03b5"+
		"\n\7\2\2\u03b5K\3\2\2\2\u03b6\u03b7\t\b\2\2\u03b7M\3\2\2\2\65V\\a{\u0080"+
		"\u0089\u0093\u009c\u00a5\u00af\u00b8\u00c0\u00c8\u00cc\u00d4\u0100\u0105"+
		"\u0124\u0138\u0176\u017b\u0199\u01e9\u01f3\u0200\u020d\u021b\u0229\u0236"+
		"\u0242\u0250\u0261\u0272\u027e\u0282\u0293\u029c\u02e8\u0327\u0333\u034f"+
		"\u0359\u035f\u036d\u0389\u0396\u039d\u03a5\u03aa\u03ac\u03b2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}