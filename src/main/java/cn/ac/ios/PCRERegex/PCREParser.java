// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/PCRE\JavaScriptRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.PCRERegex;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PCREParser extends Parser {
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
		CharacterClassStart=29, CharacterClassEnd=30, Caret=31, Hyphen=32, POSIXNamedSet=33, 
		POSIXNegatedNamedSet=34, QuestionMark=35, Plus=36, Star=37, OpenBrace=38, 
		CloseBrace=39, Comma=40, WordBoundary=41, NonWordBoundary=42, StartOfSubject=43, 
		EndOfSubjectOrLine=44, EndOfSubjectOrLineEndOfSubject=45, EndOfSubject=46, 
		PreviousMatchInSubject=47, ResetStartMatch=48, SubroutineOrNamedReferenceStartG=49, 
		NamedReferenceStartK=50, Pipe=51, OpenParen=52, CloseParen=53, LessThan=54, 
		GreaterThan=55, SingleQuote=56, Underscore=57, Colon=58, Hash=59, Equals=60, 
		Exclamation=61, Ampersand=62, ALC=63, BLC=64, CLC=65, DLC=66, ELC=67, 
		FLC=68, GLC=69, HLC=70, ILC=71, JLC=72, KLC=73, LLC=74, MLC=75, NLC=76, 
		OLC=77, PLC=78, QLC=79, RLC=80, SLC=81, TLC=82, ULC=83, VLC=84, WLC=85, 
		XLC=86, YLC=87, ZLC=88, AUC=89, BUC=90, CUC=91, DUC=92, EUC=93, FUC=94, 
		GUC=95, HUC=96, IUC=97, JUC=98, KUC=99, LUC=100, MUC=101, NUC=102, OUC=103, 
		PUC=104, QUC=105, RUC=106, SUC=107, TUC=108, UUC=109, VUC=110, WUC=111, 
		XUC=112, YUC=113, ZUC=114, D1=115, D2=116, D3=117, D4=118, D5=119, D6=120, 
		D7=121, D8=122, D9=123, D0=124, OtherChar=125;
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
			"'\\W'", "'\\X'", "'['", "']'", "'^'", "'-'", null, null, "'?'", "'+'", 
			"'*'", "'{'", "'}'", "','", "'\\b'", "'\\B'", "'\\A'", "'$'", "'\\Z'", 
			"'\\z'", "'\\G'", "'\\K'", "'\\g'", "'\\k'", "'|'", "'('", "')'", "'<'", 
			"'>'", "'''", "'_'", "':'", "'#'", "'='", "'!'", "'&'", "'a'", "'b'", 
			"'c'", "'d'", "'e'", "'f'", "'g'", "'h'", "'i'", "'j'", "'k'", "'l'", 
			"'m'", "'n'", "'o'", "'p'", "'q'", "'r'", "'s'", "'t'", "'u'", "'v'", 
			"'w'", "'x'", "'y'", "'z'", "'A'", "'B'", "'C'", "'D'", "'E'", "'F'", 
			"'G'", "'H'", "'I'", "'J'", "'K'", "'L'", "'M'", "'N'", "'O'", "'P'", 
			"'Q'", "'R'", "'S'", "'T'", "'U'", "'V'", "'W'", "'X'", "'Y'", "'Z'", 
			"'1'", "'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "'0'"
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
			"CharacterClassStart", "CharacterClassEnd", "Caret", "Hyphen", "POSIXNamedSet", 
			"POSIXNegatedNamedSet", "QuestionMark", "Plus", "Star", "OpenBrace", 
			"CloseBrace", "Comma", "WordBoundary", "NonWordBoundary", "StartOfSubject", 
			"EndOfSubjectOrLine", "EndOfSubjectOrLineEndOfSubject", "EndOfSubject", 
			"PreviousMatchInSubject", "ResetStartMatch", "SubroutineOrNamedReferenceStartG", 
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

	public PCREParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PCREParser.EOF, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitParse(this);
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
		public List<TerminalNode> Pipe() { return getTokens(PCREParser.Pipe); }
		public TerminalNode Pipe(int i) {
			return getToken(PCREParser.Pipe, i);
		}
		public AlternationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alternation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterAlternation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitAlternation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitAlternation(this);
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitExpr(this);
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << OneDataUnit) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << ExtendedUnicodeChar) | (1L << CharacterClassStart) | (1L << CharacterClassEnd) | (1L << Caret) | (1L << Hyphen) | (1L << POSIXNamedSet) | (1L << POSIXNegatedNamedSet) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << NonWordBoundary) | (1L << StartOfSubject) | (1L << EndOfSubjectOrLine) | (1L << EndOfSubjectOrLineEndOfSubject) | (1L << EndOfSubject) | (1L << PreviousMatchInSubject) | (1L << ResetStartMatch) | (1L << SubroutineOrNamedReferenceStartG) | (1L << NamedReferenceStartK) | (1L << OpenParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (BLC - 64)) | (1L << (CLC - 64)) | (1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0)) {
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitElement(this);
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
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public Quantifier_typeContext quantifier_type() {
			return getRuleContext(Quantifier_typeContext.class,0);
		}
		public TerminalNode Plus() { return getToken(PCREParser.Plus, 0); }
		public TerminalNode Star() { return getToken(PCREParser.Star, 0); }
		public TerminalNode OpenBrace() { return getToken(PCREParser.OpenBrace, 0); }
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public TerminalNode CloseBrace() { return getToken(PCREParser.CloseBrace, 0); }
		public TerminalNode Comma() { return getToken(PCREParser.Comma, 0); }
		public QuantifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterQuantifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitQuantifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitQuantifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuantifierContext quantifier() throws RecognitionException {
		QuantifierContext _localctx = new QuantifierContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_quantifier);
		try {
			setState(119);
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
				number();
				setState(103);
				match(CloseBrace);
				setState(104);
				quantifier_type();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(106);
				match(OpenBrace);
				setState(107);
				number();
				setState(108);
				match(Comma);
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
				number();
				setState(116);
				match(CloseBrace);
				setState(117);
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
		public TerminalNode Plus() { return getToken(PCREParser.Plus, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public Quantifier_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterQuantifier_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitQuantifier_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitQuantifier_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Quantifier_typeContext quantifier_type() throws RecognitionException {
		Quantifier_typeContext _localctx = new Quantifier_typeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_quantifier_type);
		try {
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Plus:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				match(Plus);
				}
				break;
			case QuestionMark:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
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
			case POSIXNamedSet:
			case POSIXNegatedNamedSet:
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
		public TerminalNode CharacterClassStart() { return getToken(PCREParser.CharacterClassStart, 0); }
		public TerminalNode Caret() { return getToken(PCREParser.Caret, 0); }
		public List<TerminalNode> CharacterClassEnd() { return getTokens(PCREParser.CharacterClassEnd); }
		public TerminalNode CharacterClassEnd(int i) {
			return getToken(PCREParser.CharacterClassEnd, i);
		}
		public TerminalNode Hyphen() { return getToken(PCREParser.Hyphen, 0); }
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterCharacter_class(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitCharacter_class(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitCharacter_class(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Character_classContext character_class() throws RecognitionException {
		Character_classContext _localctx = new Character_classContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_character_class);
		int _la;
		try {
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(CharacterClassStart);
				setState(127);
				match(Caret);
				setState(128);
				match(CharacterClassEnd);
				setState(129);
				match(Hyphen);
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(130);
					cc_atom();
					}
					}
					setState(133); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << POSIXNamedSet) | (1L << POSIXNegatedNamedSet) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (BLC - 64)) | (1L << (CLC - 64)) | (1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(135);
				match(CharacterClassEnd);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				match(CharacterClassStart);
				setState(138);
				match(Caret);
				setState(139);
				match(CharacterClassEnd);
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << POSIXNamedSet) | (1L << POSIXNegatedNamedSet) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (BLC - 64)) | (1L << (CLC - 64)) | (1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0)) {
					{
					{
					setState(140);
					cc_atom();
					}
					}
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(146);
				match(CharacterClassEnd);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(147);
				match(CharacterClassStart);
				setState(148);
				match(Caret);
				setState(150); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(149);
					cc_atom();
					}
					}
					setState(152); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << POSIXNamedSet) | (1L << POSIXNegatedNamedSet) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (BLC - 64)) | (1L << (CLC - 64)) | (1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(154);
				match(CharacterClassEnd);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(156);
				match(CharacterClassStart);
				setState(157);
				match(CharacterClassEnd);
				setState(158);
				match(Hyphen);
				setState(160); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(159);
					cc_atom();
					}
					}
					setState(162); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << POSIXNamedSet) | (1L << POSIXNegatedNamedSet) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (BLC - 64)) | (1L << (CLC - 64)) | (1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(164);
				match(CharacterClassEnd);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(166);
				match(CharacterClassStart);
				setState(167);
				match(CharacterClassEnd);
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << POSIXNamedSet) | (1L << POSIXNegatedNamedSet) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (BLC - 64)) | (1L << (CLC - 64)) | (1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0)) {
					{
					{
					setState(168);
					cc_atom();
					}
					}
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(174);
				match(CharacterClassEnd);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(175);
				match(CharacterClassStart);
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(176);
					cc_atom();
					}
					}
					setState(179); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << CharacterClassStart) | (1L << Caret) | (1L << Hyphen) | (1L << POSIXNamedSet) | (1L << POSIXNegatedNamedSet) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << EndOfSubjectOrLine) | (1L << Pipe) | (1L << OpenParen) | (1L << CloseParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (BLC - 64)) | (1L << (CLC - 64)) | (1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
				setState(181);
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
		public TerminalNode SubroutineOrNamedReferenceStartG() { return getToken(PCREParser.SubroutineOrNamedReferenceStartG, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode OpenBrace() { return getToken(PCREParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(PCREParser.CloseBrace, 0); }
		public TerminalNode Hyphen() { return getToken(PCREParser.Hyphen, 0); }
		public TerminalNode NamedReferenceStartK() { return getToken(PCREParser.NamedReferenceStartK, 0); }
		public TerminalNode LessThan() { return getToken(PCREParser.LessThan, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode GreaterThan() { return getToken(PCREParser.GreaterThan, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(PCREParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(PCREParser.SingleQuote, i);
		}
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public TerminalNode PUC() { return getToken(PCREParser.PUC, 0); }
		public TerminalNode Equals() { return getToken(PCREParser.Equals, 0); }
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public BackreferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backreference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterBackreference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitBackreference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitBackreference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BackreferenceContext backreference() throws RecognitionException {
		BackreferenceContext _localctx = new BackreferenceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_backreference);
		try {
			setState(226);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				backreference_or_octal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				match(SubroutineOrNamedReferenceStartG);
				setState(187);
				number();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(188);
				match(SubroutineOrNamedReferenceStartG);
				setState(189);
				match(OpenBrace);
				setState(190);
				number();
				setState(191);
				match(CloseBrace);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(193);
				match(SubroutineOrNamedReferenceStartG);
				setState(194);
				match(OpenBrace);
				setState(195);
				match(Hyphen);
				setState(196);
				number();
				setState(197);
				match(CloseBrace);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(199);
				match(NamedReferenceStartK);
				setState(200);
				match(LessThan);
				setState(201);
				name();
				setState(202);
				match(GreaterThan);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(204);
				match(NamedReferenceStartK);
				setState(205);
				match(SingleQuote);
				setState(206);
				name();
				setState(207);
				match(SingleQuote);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(209);
				match(SubroutineOrNamedReferenceStartG);
				setState(210);
				match(OpenBrace);
				setState(211);
				name();
				setState(212);
				match(CloseBrace);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(214);
				match(NamedReferenceStartK);
				setState(215);
				match(OpenBrace);
				setState(216);
				name();
				setState(217);
				match(CloseBrace);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(219);
				match(OpenParen);
				setState(220);
				match(QuestionMark);
				setState(221);
				match(PUC);
				setState(222);
				match(Equals);
				setState(223);
				name();
				setState(224);
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
		public TerminalNode Backslash() { return getToken(PCREParser.Backslash, 0); }
		public DigitContext digit() {
			return getRuleContext(DigitContext.class,0);
		}
		public Backreference_or_octalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backreference_or_octal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterBackreference_or_octal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitBackreference_or_octal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitBackreference_or_octal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Backreference_or_octalContext backreference_or_octal() throws RecognitionException {
		Backreference_or_octalContext _localctx = new Backreference_or_octalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_backreference_or_octal);
		try {
			setState(231);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(228);
				octal_char();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(229);
				match(Backslash);
				setState(230);
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
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public TerminalNode LessThan() { return getToken(PCREParser.LessThan, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode GreaterThan() { return getToken(PCREParser.GreaterThan, 0); }
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(PCREParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(PCREParser.SingleQuote, i);
		}
		public TerminalNode PUC() { return getToken(PCREParser.PUC, 0); }
		public CaptureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_capture; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterCapture(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitCapture(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitCapture(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaptureContext capture() throws RecognitionException {
		CaptureContext _localctx = new CaptureContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_capture);
		try {
			setState(262);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(233);
				match(OpenParen);
				setState(234);
				match(QuestionMark);
				setState(235);
				match(LessThan);
				setState(236);
				name();
				setState(237);
				match(GreaterThan);
				setState(238);
				alternation();
				setState(239);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				match(OpenParen);
				setState(242);
				match(QuestionMark);
				setState(243);
				match(SingleQuote);
				setState(244);
				name();
				setState(245);
				match(SingleQuote);
				setState(246);
				alternation();
				setState(247);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(249);
				match(OpenParen);
				setState(250);
				match(QuestionMark);
				setState(251);
				match(PUC);
				setState(252);
				match(LessThan);
				setState(253);
				name();
				setState(254);
				match(GreaterThan);
				setState(255);
				alternation();
				setState(256);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(258);
				match(OpenParen);
				setState(259);
				alternation();
				setState(260);
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
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public TerminalNode Colon() { return getToken(PCREParser.Colon, 0); }
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public TerminalNode Pipe() { return getToken(PCREParser.Pipe, 0); }
		public TerminalNode GreaterThan() { return getToken(PCREParser.GreaterThan, 0); }
		public Non_captureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_capture; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterNon_capture(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitNon_capture(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitNon_capture(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Non_captureContext non_capture() throws RecognitionException {
		Non_captureContext _localctx = new Non_captureContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_non_capture);
		try {
			setState(282);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				match(OpenParen);
				setState(265);
				match(QuestionMark);
				setState(266);
				match(Colon);
				setState(267);
				alternation();
				setState(268);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(270);
				match(OpenParen);
				setState(271);
				match(QuestionMark);
				setState(272);
				match(Pipe);
				setState(273);
				alternation();
				setState(274);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(276);
				match(OpenParen);
				setState(277);
				match(QuestionMark);
				setState(278);
				match(GreaterThan);
				setState(279);
				alternation();
				setState(280);
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
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public TerminalNode Hash() { return getToken(PCREParser.Hash, 0); }
		public Non_close_parensContext non_close_parens() {
			return getRuleContext(Non_close_parensContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitComment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(OpenParen);
			setState(285);
			match(QuestionMark);
			setState(286);
			match(Hash);
			setState(287);
			non_close_parens();
			setState(288);
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
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public List<Option_flagsContext> option_flags() {
			return getRuleContexts(Option_flagsContext.class);
		}
		public Option_flagsContext option_flags(int i) {
			return getRuleContext(Option_flagsContext.class,i);
		}
		public TerminalNode Hyphen() { return getToken(PCREParser.Hyphen, 0); }
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public TerminalNode Star() { return getToken(PCREParser.Star, 0); }
		public TerminalNode NUC() { return getToken(PCREParser.NUC, 0); }
		public List<TerminalNode> OUC() { return getTokens(PCREParser.OUC); }
		public TerminalNode OUC(int i) {
			return getToken(PCREParser.OUC, i);
		}
		public List<TerminalNode> Underscore() { return getTokens(PCREParser.Underscore); }
		public TerminalNode Underscore(int i) {
			return getToken(PCREParser.Underscore, i);
		}
		public TerminalNode SUC() { return getToken(PCREParser.SUC, 0); }
		public List<TerminalNode> TUC() { return getTokens(PCREParser.TUC); }
		public TerminalNode TUC(int i) {
			return getToken(PCREParser.TUC, i);
		}
		public TerminalNode AUC() { return getToken(PCREParser.AUC, 0); }
		public TerminalNode RUC() { return getToken(PCREParser.RUC, 0); }
		public TerminalNode PUC() { return getToken(PCREParser.PUC, 0); }
		public TerminalNode UUC() { return getToken(PCREParser.UUC, 0); }
		public TerminalNode FUC() { return getToken(PCREParser.FUC, 0); }
		public TerminalNode D8() { return getToken(PCREParser.D8, 0); }
		public TerminalNode D1() { return getToken(PCREParser.D1, 0); }
		public TerminalNode D6() { return getToken(PCREParser.D6, 0); }
		public TerminalNode CUC() { return getToken(PCREParser.CUC, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_option);
		try {
			setState(344);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(290);
				match(OpenParen);
				setState(291);
				match(QuestionMark);
				setState(292);
				option_flags();
				setState(293);
				match(Hyphen);
				setState(294);
				option_flags();
				setState(295);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(297);
				match(OpenParen);
				setState(298);
				match(QuestionMark);
				setState(299);
				option_flags();
				setState(300);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(302);
				match(OpenParen);
				setState(303);
				match(QuestionMark);
				setState(304);
				match(Hyphen);
				setState(305);
				option_flags();
				setState(306);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(308);
				match(OpenParen);
				setState(309);
				match(Star);
				setState(310);
				match(NUC);
				setState(311);
				match(OUC);
				setState(312);
				match(Underscore);
				setState(313);
				match(SUC);
				setState(314);
				match(TUC);
				setState(315);
				match(AUC);
				setState(316);
				match(RUC);
				setState(317);
				match(TUC);
				setState(318);
				match(Underscore);
				setState(319);
				match(OUC);
				setState(320);
				match(PUC);
				setState(321);
				match(TUC);
				setState(322);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(323);
				match(OpenParen);
				setState(324);
				match(Star);
				setState(325);
				match(UUC);
				setState(326);
				match(TUC);
				setState(327);
				match(FUC);
				setState(328);
				match(D8);
				setState(329);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(330);
				match(OpenParen);
				setState(331);
				match(Star);
				setState(332);
				match(UUC);
				setState(333);
				match(TUC);
				setState(334);
				match(FUC);
				setState(335);
				match(D1);
				setState(336);
				match(D6);
				setState(337);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(338);
				match(OpenParen);
				setState(339);
				match(Star);
				setState(340);
				match(UUC);
				setState(341);
				match(CUC);
				setState(342);
				match(PUC);
				setState(343);
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterOption_flags(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitOption_flags(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitOption_flags(this);
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
			setState(347); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(346);
				option_flag();
				}
				}
				setState(349); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (ILC - 71)) | (1L << (MLC - 71)) | (1L << (SLC - 71)) | (1L << (XLC - 71)) | (1L << (JUC - 71)) | (1L << (UUC - 71)))) != 0) );
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
		public TerminalNode ILC() { return getToken(PCREParser.ILC, 0); }
		public TerminalNode JUC() { return getToken(PCREParser.JUC, 0); }
		public TerminalNode MLC() { return getToken(PCREParser.MLC, 0); }
		public TerminalNode SLC() { return getToken(PCREParser.SLC, 0); }
		public TerminalNode UUC() { return getToken(PCREParser.UUC, 0); }
		public TerminalNode XLC() { return getToken(PCREParser.XLC, 0); }
		public Option_flagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option_flag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterOption_flag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitOption_flag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitOption_flag(this);
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
			setState(351);
			_la = _input.LA(1);
			if ( !(((((_la - 71)) & ~0x3f) == 0 && ((1L << (_la - 71)) & ((1L << (ILC - 71)) | (1L << (MLC - 71)) | (1L << (SLC - 71)) | (1L << (XLC - 71)) | (1L << (JUC - 71)) | (1L << (UUC - 71)))) != 0)) ) {
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
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public TerminalNode Equals() { return getToken(PCREParser.Equals, 0); }
		public AlternationContext alternation() {
			return getRuleContext(AlternationContext.class,0);
		}
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public TerminalNode Exclamation() { return getToken(PCREParser.Exclamation, 0); }
		public TerminalNode LessThan() { return getToken(PCREParser.LessThan, 0); }
		public Look_aroundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_look_around; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterLook_around(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitLook_around(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitLook_around(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Look_aroundContext look_around() throws RecognitionException {
		Look_aroundContext _localctx = new Look_aroundContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_look_around);
		try {
			setState(379);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(353);
				match(OpenParen);
				setState(354);
				match(QuestionMark);
				setState(355);
				match(Equals);
				setState(356);
				alternation();
				setState(357);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(359);
				match(OpenParen);
				setState(360);
				match(QuestionMark);
				setState(361);
				match(Exclamation);
				setState(362);
				alternation();
				setState(363);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(365);
				match(OpenParen);
				setState(366);
				match(QuestionMark);
				setState(367);
				match(LessThan);
				setState(368);
				match(Equals);
				setState(369);
				alternation();
				setState(370);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(372);
				match(OpenParen);
				setState(373);
				match(QuestionMark);
				setState(374);
				match(LessThan);
				setState(375);
				match(Exclamation);
				setState(376);
				alternation();
				setState(377);
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
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public TerminalNode RUC() { return getToken(PCREParser.RUC, 0); }
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode Plus() { return getToken(PCREParser.Plus, 0); }
		public TerminalNode Hyphen() { return getToken(PCREParser.Hyphen, 0); }
		public TerminalNode Ampersand() { return getToken(PCREParser.Ampersand, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode PUC() { return getToken(PCREParser.PUC, 0); }
		public TerminalNode GreaterThan() { return getToken(PCREParser.GreaterThan, 0); }
		public TerminalNode SubroutineOrNamedReferenceStartG() { return getToken(PCREParser.SubroutineOrNamedReferenceStartG, 0); }
		public TerminalNode LessThan() { return getToken(PCREParser.LessThan, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(PCREParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(PCREParser.SingleQuote, i);
		}
		public Subroutine_referenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subroutine_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterSubroutine_reference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitSubroutine_reference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitSubroutine_reference(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Subroutine_referenceContext subroutine_reference() throws RecognitionException {
		Subroutine_referenceContext _localctx = new Subroutine_referenceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_subroutine_reference);
		try {
			setState(459);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(381);
				match(OpenParen);
				setState(382);
				match(QuestionMark);
				setState(383);
				match(RUC);
				setState(384);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(385);
				match(OpenParen);
				setState(386);
				match(QuestionMark);
				setState(387);
				number();
				setState(388);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(390);
				match(OpenParen);
				setState(391);
				match(QuestionMark);
				setState(392);
				match(Plus);
				setState(393);
				number();
				setState(394);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(396);
				match(OpenParen);
				setState(397);
				match(QuestionMark);
				setState(398);
				match(Hyphen);
				setState(399);
				number();
				setState(400);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(402);
				match(OpenParen);
				setState(403);
				match(QuestionMark);
				setState(404);
				match(Ampersand);
				setState(405);
				name();
				setState(406);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(408);
				match(OpenParen);
				setState(409);
				match(QuestionMark);
				setState(410);
				match(PUC);
				setState(411);
				match(GreaterThan);
				setState(412);
				name();
				setState(413);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(415);
				match(SubroutineOrNamedReferenceStartG);
				setState(416);
				match(LessThan);
				setState(417);
				name();
				setState(418);
				match(GreaterThan);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(420);
				match(SubroutineOrNamedReferenceStartG);
				setState(421);
				match(SingleQuote);
				setState(422);
				name();
				setState(423);
				match(SingleQuote);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(425);
				match(SubroutineOrNamedReferenceStartG);
				setState(426);
				match(LessThan);
				setState(427);
				number();
				setState(428);
				match(GreaterThan);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(430);
				match(SubroutineOrNamedReferenceStartG);
				setState(431);
				match(SingleQuote);
				setState(432);
				number();
				setState(433);
				match(SingleQuote);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(435);
				match(SubroutineOrNamedReferenceStartG);
				setState(436);
				match(LessThan);
				setState(437);
				match(Plus);
				setState(438);
				number();
				setState(439);
				match(GreaterThan);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(441);
				match(SubroutineOrNamedReferenceStartG);
				setState(442);
				match(SingleQuote);
				setState(443);
				match(Plus);
				setState(444);
				number();
				setState(445);
				match(SingleQuote);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(447);
				match(SubroutineOrNamedReferenceStartG);
				setState(448);
				match(LessThan);
				setState(449);
				match(Hyphen);
				setState(450);
				number();
				setState(451);
				match(GreaterThan);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(453);
				match(SubroutineOrNamedReferenceStartG);
				setState(454);
				match(SingleQuote);
				setState(455);
				match(Hyphen);
				setState(456);
				number();
				setState(457);
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
		public List<TerminalNode> OpenParen() { return getTokens(PCREParser.OpenParen); }
		public TerminalNode OpenParen(int i) {
			return getToken(PCREParser.OpenParen, i);
		}
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public List<TerminalNode> CloseParen() { return getTokens(PCREParser.CloseParen); }
		public TerminalNode CloseParen(int i) {
			return getToken(PCREParser.CloseParen, i);
		}
		public List<AlternationContext> alternation() {
			return getRuleContexts(AlternationContext.class);
		}
		public AlternationContext alternation(int i) {
			return getRuleContext(AlternationContext.class,i);
		}
		public TerminalNode Pipe() { return getToken(PCREParser.Pipe, 0); }
		public TerminalNode Plus() { return getToken(PCREParser.Plus, 0); }
		public TerminalNode Hyphen() { return getToken(PCREParser.Hyphen, 0); }
		public TerminalNode LessThan() { return getToken(PCREParser.LessThan, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode GreaterThan() { return getToken(PCREParser.GreaterThan, 0); }
		public List<TerminalNode> SingleQuote() { return getTokens(PCREParser.SingleQuote); }
		public TerminalNode SingleQuote(int i) {
			return getToken(PCREParser.SingleQuote, i);
		}
		public TerminalNode RUC() { return getToken(PCREParser.RUC, 0); }
		public TerminalNode Ampersand() { return getToken(PCREParser.Ampersand, 0); }
		public TerminalNode DUC() { return getToken(PCREParser.DUC, 0); }
		public List<TerminalNode> EUC() { return getTokens(PCREParser.EUC); }
		public TerminalNode EUC(int i) {
			return getToken(PCREParser.EUC, i);
		}
		public TerminalNode FUC() { return getToken(PCREParser.FUC, 0); }
		public TerminalNode IUC() { return getToken(PCREParser.IUC, 0); }
		public TerminalNode NUC() { return getToken(PCREParser.NUC, 0); }
		public TerminalNode ALC() { return getToken(PCREParser.ALC, 0); }
		public List<TerminalNode> SLC() { return getTokens(PCREParser.SLC); }
		public TerminalNode SLC(int i) {
			return getToken(PCREParser.SLC, i);
		}
		public TerminalNode ELC() { return getToken(PCREParser.ELC, 0); }
		public TerminalNode RLC() { return getToken(PCREParser.RLC, 0); }
		public TerminalNode TLC() { return getToken(PCREParser.TLC, 0); }
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_conditional);
		int _la;
		try {
			setState(612);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(461);
				match(OpenParen);
				setState(462);
				match(QuestionMark);
				setState(463);
				match(OpenParen);
				setState(464);
				number();
				setState(465);
				match(CloseParen);
				setState(466);
				alternation();
				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(467);
					match(Pipe);
					setState(468);
					alternation();
					}
				}

				setState(471);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(473);
				match(OpenParen);
				setState(474);
				match(QuestionMark);
				setState(475);
				match(OpenParen);
				setState(476);
				match(Plus);
				setState(477);
				number();
				setState(478);
				match(CloseParen);
				setState(479);
				alternation();
				setState(482);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(480);
					match(Pipe);
					setState(481);
					alternation();
					}
				}

				setState(484);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(486);
				match(OpenParen);
				setState(487);
				match(QuestionMark);
				setState(488);
				match(OpenParen);
				setState(489);
				match(Hyphen);
				setState(490);
				number();
				setState(491);
				match(CloseParen);
				setState(492);
				alternation();
				setState(495);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(493);
					match(Pipe);
					setState(494);
					alternation();
					}
				}

				setState(497);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(499);
				match(OpenParen);
				setState(500);
				match(QuestionMark);
				setState(501);
				match(OpenParen);
				setState(502);
				match(LessThan);
				setState(503);
				name();
				setState(504);
				match(GreaterThan);
				setState(505);
				match(CloseParen);
				setState(506);
				alternation();
				setState(509);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(507);
					match(Pipe);
					setState(508);
					alternation();
					}
				}

				setState(511);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(513);
				match(OpenParen);
				setState(514);
				match(QuestionMark);
				setState(515);
				match(OpenParen);
				setState(516);
				match(SingleQuote);
				setState(517);
				name();
				setState(518);
				match(SingleQuote);
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
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(527);
				match(OpenParen);
				setState(528);
				match(QuestionMark);
				setState(529);
				match(OpenParen);
				setState(530);
				match(RUC);
				setState(531);
				number();
				setState(532);
				match(CloseParen);
				setState(533);
				alternation();
				setState(536);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(534);
					match(Pipe);
					setState(535);
					alternation();
					}
				}

				setState(538);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(540);
				match(OpenParen);
				setState(541);
				match(QuestionMark);
				setState(542);
				match(OpenParen);
				setState(543);
				match(RUC);
				setState(544);
				match(CloseParen);
				setState(545);
				alternation();
				setState(548);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(546);
					match(Pipe);
					setState(547);
					alternation();
					}
				}

				setState(550);
				match(CloseParen);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(552);
				match(OpenParen);
				setState(553);
				match(QuestionMark);
				setState(554);
				match(OpenParen);
				setState(555);
				match(RUC);
				setState(556);
				match(Ampersand);
				setState(557);
				name();
				setState(558);
				match(CloseParen);
				setState(559);
				alternation();
				setState(562);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(560);
					match(Pipe);
					setState(561);
					alternation();
					}
				}

				setState(564);
				match(CloseParen);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(566);
				match(OpenParen);
				setState(567);
				match(QuestionMark);
				setState(568);
				match(OpenParen);
				setState(569);
				match(DUC);
				setState(570);
				match(EUC);
				setState(571);
				match(FUC);
				setState(572);
				match(IUC);
				setState(573);
				match(NUC);
				setState(574);
				match(EUC);
				setState(575);
				match(CloseParen);
				setState(576);
				alternation();
				setState(579);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(577);
					match(Pipe);
					setState(578);
					alternation();
					}
				}

				setState(581);
				match(CloseParen);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(583);
				match(OpenParen);
				setState(584);
				match(QuestionMark);
				setState(585);
				match(OpenParen);
				setState(586);
				match(ALC);
				setState(587);
				match(SLC);
				setState(588);
				match(SLC);
				setState(589);
				match(ELC);
				setState(590);
				match(RLC);
				setState(591);
				match(TLC);
				setState(592);
				match(CloseParen);
				setState(593);
				alternation();
				setState(596);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(594);
					match(Pipe);
					setState(595);
					alternation();
					}
				}

				setState(598);
				match(CloseParen);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(600);
				match(OpenParen);
				setState(601);
				match(QuestionMark);
				setState(602);
				match(OpenParen);
				setState(603);
				name();
				setState(604);
				match(CloseParen);
				setState(605);
				alternation();
				setState(608);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Pipe) {
					{
					setState(606);
					match(Pipe);
					setState(607);
					alternation();
					}
				}

				setState(610);
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
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode Star() { return getToken(PCREParser.Star, 0); }
		public List<TerminalNode> AUC() { return getTokens(PCREParser.AUC); }
		public TerminalNode AUC(int i) {
			return getToken(PCREParser.AUC, i);
		}
		public List<TerminalNode> CUC() { return getTokens(PCREParser.CUC); }
		public TerminalNode CUC(int i) {
			return getToken(PCREParser.CUC, i);
		}
		public List<TerminalNode> EUC() { return getTokens(PCREParser.EUC); }
		public TerminalNode EUC(int i) {
			return getToken(PCREParser.EUC, i);
		}
		public TerminalNode PUC() { return getToken(PCREParser.PUC, 0); }
		public TerminalNode TUC() { return getToken(PCREParser.TUC, 0); }
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public TerminalNode FUC() { return getToken(PCREParser.FUC, 0); }
		public TerminalNode IUC() { return getToken(PCREParser.IUC, 0); }
		public TerminalNode LUC() { return getToken(PCREParser.LUC, 0); }
		public TerminalNode Colon() { return getToken(PCREParser.Colon, 0); }
		public List<TerminalNode> NUC() { return getTokens(PCREParser.NUC); }
		public TerminalNode NUC(int i) {
			return getToken(PCREParser.NUC, i);
		}
		public List<TerminalNode> MUC() { return getTokens(PCREParser.MUC); }
		public TerminalNode MUC(int i) {
			return getToken(PCREParser.MUC, i);
		}
		public TerminalNode RUC() { return getToken(PCREParser.RUC, 0); }
		public TerminalNode KUC() { return getToken(PCREParser.KUC, 0); }
		public TerminalNode OUC() { return getToken(PCREParser.OUC, 0); }
		public TerminalNode UUC() { return getToken(PCREParser.UUC, 0); }
		public TerminalNode SUC() { return getToken(PCREParser.SUC, 0); }
		public TerminalNode HUC() { return getToken(PCREParser.HUC, 0); }
		public Backtrack_controlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_backtrack_control; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterBacktrack_control(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitBacktrack_control(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitBacktrack_control(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Backtrack_controlContext backtrack_control() throws RecognitionException {
		Backtrack_controlContext _localctx = new Backtrack_controlContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_backtrack_control);
		int _la;
		try {
			setState(714);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(614);
				match(OpenParen);
				setState(615);
				match(Star);
				setState(616);
				match(AUC);
				setState(617);
				match(CUC);
				setState(618);
				match(CUC);
				setState(619);
				match(EUC);
				setState(620);
				match(PUC);
				setState(621);
				match(TUC);
				setState(622);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(623);
				match(OpenParen);
				setState(624);
				match(Star);
				setState(625);
				match(FUC);
				setState(629);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AUC) {
					{
					setState(626);
					match(AUC);
					setState(627);
					match(IUC);
					setState(628);
					match(LUC);
					}
				}

				setState(631);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(632);
				match(OpenParen);
				setState(633);
				match(Star);
				setState(638);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MUC) {
					{
					setState(634);
					match(MUC);
					setState(635);
					match(AUC);
					setState(636);
					match(RUC);
					setState(637);
					match(KUC);
					}
				}

				setState(640);
				match(Colon);
				setState(641);
				match(NUC);
				setState(642);
				match(AUC);
				setState(643);
				match(MUC);
				setState(644);
				match(EUC);
				setState(645);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(646);
				match(OpenParen);
				setState(647);
				match(Star);
				setState(648);
				match(CUC);
				setState(649);
				match(OUC);
				setState(650);
				match(MUC);
				setState(651);
				match(MUC);
				setState(652);
				match(IUC);
				setState(653);
				match(TUC);
				setState(654);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(655);
				match(OpenParen);
				setState(656);
				match(Star);
				setState(657);
				match(PUC);
				setState(658);
				match(RUC);
				setState(659);
				match(UUC);
				setState(660);
				match(NUC);
				setState(661);
				match(EUC);
				setState(662);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(663);
				match(OpenParen);
				setState(664);
				match(Star);
				setState(665);
				match(PUC);
				setState(666);
				match(RUC);
				setState(667);
				match(UUC);
				setState(668);
				match(NUC);
				setState(669);
				match(EUC);
				setState(670);
				match(Colon);
				setState(671);
				match(NUC);
				setState(672);
				match(AUC);
				setState(673);
				match(MUC);
				setState(674);
				match(EUC);
				setState(675);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(676);
				match(OpenParen);
				setState(677);
				match(Star);
				setState(678);
				match(SUC);
				setState(679);
				match(KUC);
				setState(680);
				match(IUC);
				setState(681);
				match(PUC);
				setState(682);
				match(CloseParen);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(683);
				match(OpenParen);
				setState(684);
				match(Star);
				setState(685);
				match(SUC);
				setState(686);
				match(KUC);
				setState(687);
				match(IUC);
				setState(688);
				match(PUC);
				setState(689);
				match(Colon);
				setState(690);
				match(NUC);
				setState(691);
				match(AUC);
				setState(692);
				match(MUC);
				setState(693);
				match(EUC);
				setState(694);
				match(CloseParen);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(695);
				match(OpenParen);
				setState(696);
				match(Star);
				setState(697);
				match(TUC);
				setState(698);
				match(HUC);
				setState(699);
				match(EUC);
				setState(700);
				match(NUC);
				setState(701);
				match(CloseParen);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(702);
				match(OpenParen);
				setState(703);
				match(Star);
				setState(704);
				match(TUC);
				setState(705);
				match(HUC);
				setState(706);
				match(EUC);
				setState(707);
				match(NUC);
				setState(708);
				match(Colon);
				setState(709);
				match(NUC);
				setState(710);
				match(AUC);
				setState(711);
				match(MUC);
				setState(712);
				match(EUC);
				setState(713);
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
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode Star() { return getToken(PCREParser.Star, 0); }
		public TerminalNode CUC() { return getToken(PCREParser.CUC, 0); }
		public List<TerminalNode> RUC() { return getTokens(PCREParser.RUC); }
		public TerminalNode RUC(int i) {
			return getToken(PCREParser.RUC, i);
		}
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public TerminalNode LUC() { return getToken(PCREParser.LUC, 0); }
		public TerminalNode FUC() { return getToken(PCREParser.FUC, 0); }
		public TerminalNode AUC() { return getToken(PCREParser.AUC, 0); }
		public TerminalNode NUC() { return getToken(PCREParser.NUC, 0); }
		public TerminalNode YUC() { return getToken(PCREParser.YUC, 0); }
		public TerminalNode BUC() { return getToken(PCREParser.BUC, 0); }
		public TerminalNode SUC() { return getToken(PCREParser.SUC, 0); }
		public TerminalNode Underscore() { return getToken(PCREParser.Underscore, 0); }
		public TerminalNode UUC() { return getToken(PCREParser.UUC, 0); }
		public TerminalNode IUC() { return getToken(PCREParser.IUC, 0); }
		public TerminalNode OUC() { return getToken(PCREParser.OUC, 0); }
		public TerminalNode DUC() { return getToken(PCREParser.DUC, 0); }
		public TerminalNode EUC() { return getToken(PCREParser.EUC, 0); }
		public Newline_conventionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newline_convention; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterNewline_convention(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitNewline_convention(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitNewline_convention(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Newline_conventionContext newline_convention() throws RecognitionException {
		Newline_conventionContext _localctx = new Newline_conventionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_newline_convention);
		try {
			setState(777);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(716);
				match(OpenParen);
				setState(717);
				match(Star);
				setState(718);
				match(CUC);
				setState(719);
				match(RUC);
				setState(720);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(721);
				match(OpenParen);
				setState(722);
				match(Star);
				setState(723);
				match(LUC);
				setState(724);
				match(FUC);
				setState(725);
				match(CloseParen);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(726);
				match(OpenParen);
				setState(727);
				match(Star);
				setState(728);
				match(CUC);
				setState(729);
				match(RUC);
				setState(730);
				match(LUC);
				setState(731);
				match(FUC);
				setState(732);
				match(CloseParen);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(733);
				match(OpenParen);
				setState(734);
				match(Star);
				setState(735);
				match(AUC);
				setState(736);
				match(NUC);
				setState(737);
				match(YUC);
				setState(738);
				match(CUC);
				setState(739);
				match(RUC);
				setState(740);
				match(LUC);
				setState(741);
				match(FUC);
				setState(742);
				match(CloseParen);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(743);
				match(OpenParen);
				setState(744);
				match(Star);
				setState(745);
				match(AUC);
				setState(746);
				match(NUC);
				setState(747);
				match(YUC);
				setState(748);
				match(CloseParen);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(749);
				match(OpenParen);
				setState(750);
				match(Star);
				setState(751);
				match(BUC);
				setState(752);
				match(SUC);
				setState(753);
				match(RUC);
				setState(754);
				match(Underscore);
				setState(755);
				match(AUC);
				setState(756);
				match(NUC);
				setState(757);
				match(YUC);
				setState(758);
				match(CUC);
				setState(759);
				match(RUC);
				setState(760);
				match(LUC);
				setState(761);
				match(FUC);
				setState(762);
				match(CloseParen);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(763);
				match(OpenParen);
				setState(764);
				match(Star);
				setState(765);
				match(BUC);
				setState(766);
				match(SUC);
				setState(767);
				match(RUC);
				setState(768);
				match(Underscore);
				setState(769);
				match(UUC);
				setState(770);
				match(NUC);
				setState(771);
				match(IUC);
				setState(772);
				match(CUC);
				setState(773);
				match(OUC);
				setState(774);
				match(DUC);
				setState(775);
				match(EUC);
				setState(776);
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
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public TerminalNode CUC() { return getToken(PCREParser.CUC, 0); }
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public CalloutContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callout; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterCallout(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitCallout(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitCallout(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CalloutContext callout() throws RecognitionException {
		CalloutContext _localctx = new CalloutContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_callout);
		try {
			setState(789);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(779);
				match(OpenParen);
				setState(780);
				match(QuestionMark);
				setState(781);
				match(CUC);
				setState(782);
				match(CloseParen);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(783);
				match(OpenParen);
				setState(784);
				match(QuestionMark);
				setState(785);
				match(CUC);
				setState(786);
				number();
				setState(787);
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
		public TerminalNode Dot() { return getToken(PCREParser.Dot, 0); }
		public TerminalNode Caret() { return getToken(PCREParser.Caret, 0); }
		public TerminalNode StartOfSubject() { return getToken(PCREParser.StartOfSubject, 0); }
		public TerminalNode WordBoundary() { return getToken(PCREParser.WordBoundary, 0); }
		public TerminalNode NonWordBoundary() { return getToken(PCREParser.NonWordBoundary, 0); }
		public TerminalNode EndOfSubjectOrLine() { return getToken(PCREParser.EndOfSubjectOrLine, 0); }
		public TerminalNode EndOfSubjectOrLineEndOfSubject() { return getToken(PCREParser.EndOfSubjectOrLineEndOfSubject, 0); }
		public TerminalNode EndOfSubject() { return getToken(PCREParser.EndOfSubject, 0); }
		public TerminalNode PreviousMatchInSubject() { return getToken(PCREParser.PreviousMatchInSubject, 0); }
		public TerminalNode ResetStartMatch() { return getToken(PCREParser.ResetStartMatch, 0); }
		public TerminalNode OneDataUnit() { return getToken(PCREParser.OneDataUnit, 0); }
		public TerminalNode ExtendedUnicodeChar() { return getToken(PCREParser.ExtendedUnicodeChar, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_atom);
		try {
			setState(817);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(791);
				subroutine_reference();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(792);
				shared_atom();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(793);
				literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(794);
				character_class();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(795);
				capture();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(796);
				non_capture();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(797);
				comment();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(798);
				option();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(799);
				look_around();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(800);
				backreference();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(801);
				conditional();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(802);
				backtrack_control();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(803);
				newline_convention();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(804);
				callout();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(805);
				match(Dot);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(806);
				match(Caret);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(807);
				match(StartOfSubject);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(808);
				match(WordBoundary);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(809);
				match(NonWordBoundary);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(810);
				match(EndOfSubjectOrLine);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(811);
				match(EndOfSubjectOrLineEndOfSubject);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(812);
				match(EndOfSubject);
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(813);
				match(PreviousMatchInSubject);
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(814);
				match(ResetStartMatch);
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(815);
				match(OneDataUnit);
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(816);
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
		public TerminalNode Hyphen() { return getToken(PCREParser.Hyphen, 0); }
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterCc_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitCc_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitCc_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cc_atomContext cc_atom() throws RecognitionException {
		Cc_atomContext _localctx = new Cc_atomContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_cc_atom);
		try {
			setState(826);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(819);
				cc_literal();
				setState(820);
				match(Hyphen);
				setState(821);
				cc_literal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(823);
				shared_atom();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(824);
				cc_literal();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(825);
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
		public TerminalNode POSIXNamedSet() { return getToken(PCREParser.POSIXNamedSet, 0); }
		public TerminalNode POSIXNegatedNamedSet() { return getToken(PCREParser.POSIXNegatedNamedSet, 0); }
		public TerminalNode ControlChar() { return getToken(PCREParser.ControlChar, 0); }
		public TerminalNode DecimalDigit() { return getToken(PCREParser.DecimalDigit, 0); }
		public TerminalNode NotDecimalDigit() { return getToken(PCREParser.NotDecimalDigit, 0); }
		public TerminalNode HorizontalWhiteSpace() { return getToken(PCREParser.HorizontalWhiteSpace, 0); }
		public TerminalNode NotHorizontalWhiteSpace() { return getToken(PCREParser.NotHorizontalWhiteSpace, 0); }
		public TerminalNode NotNewLine() { return getToken(PCREParser.NotNewLine, 0); }
		public TerminalNode CharWithProperty() { return getToken(PCREParser.CharWithProperty, 0); }
		public TerminalNode CharWithoutProperty() { return getToken(PCREParser.CharWithoutProperty, 0); }
		public TerminalNode NewLineSequence() { return getToken(PCREParser.NewLineSequence, 0); }
		public TerminalNode WhiteSpace() { return getToken(PCREParser.WhiteSpace, 0); }
		public TerminalNode NotWhiteSpace() { return getToken(PCREParser.NotWhiteSpace, 0); }
		public TerminalNode VerticalWhiteSpace() { return getToken(PCREParser.VerticalWhiteSpace, 0); }
		public TerminalNode NotVerticalWhiteSpace() { return getToken(PCREParser.NotVerticalWhiteSpace, 0); }
		public TerminalNode WordChar() { return getToken(PCREParser.WordChar, 0); }
		public TerminalNode NotWordChar() { return getToken(PCREParser.NotWordChar, 0); }
		public Shared_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shared_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterShared_atom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitShared_atom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitShared_atom(this);
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
			setState(828);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ControlChar) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << POSIXNamedSet) | (1L << POSIXNegatedNamedSet))) != 0)) ) {
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
		public TerminalNode CharacterClassEnd() { return getToken(PCREParser.CharacterClassEnd, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_literal);
		try {
			setState(832);
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
				setState(830);
				shared_literal();
				}
				break;
			case CharacterClassEnd:
				enterOuterAlt(_localctx, 2);
				{
				setState(831);
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
		public TerminalNode Dot() { return getToken(PCREParser.Dot, 0); }
		public TerminalNode CharacterClassStart() { return getToken(PCREParser.CharacterClassStart, 0); }
		public TerminalNode Caret() { return getToken(PCREParser.Caret, 0); }
		public TerminalNode QuestionMark() { return getToken(PCREParser.QuestionMark, 0); }
		public TerminalNode Plus() { return getToken(PCREParser.Plus, 0); }
		public TerminalNode Star() { return getToken(PCREParser.Star, 0); }
		public TerminalNode WordBoundary() { return getToken(PCREParser.WordBoundary, 0); }
		public TerminalNode EndOfSubjectOrLine() { return getToken(PCREParser.EndOfSubjectOrLine, 0); }
		public TerminalNode Pipe() { return getToken(PCREParser.Pipe, 0); }
		public TerminalNode OpenParen() { return getToken(PCREParser.OpenParen, 0); }
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public Cc_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cc_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterCc_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitCc_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitCc_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Cc_literalContext cc_literal() throws RecognitionException {
		Cc_literalContext _localctx = new Cc_literalContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_cc_literal);
		try {
			setState(846);
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
				setState(834);
				shared_literal();
				}
				break;
			case Dot:
				enterOuterAlt(_localctx, 2);
				{
				setState(835);
				match(Dot);
				}
				break;
			case CharacterClassStart:
				enterOuterAlt(_localctx, 3);
				{
				setState(836);
				match(CharacterClassStart);
				}
				break;
			case Caret:
				enterOuterAlt(_localctx, 4);
				{
				setState(837);
				match(Caret);
				}
				break;
			case QuestionMark:
				enterOuterAlt(_localctx, 5);
				{
				setState(838);
				match(QuestionMark);
				}
				break;
			case Plus:
				enterOuterAlt(_localctx, 6);
				{
				setState(839);
				match(Plus);
				}
				break;
			case Star:
				enterOuterAlt(_localctx, 7);
				{
				setState(840);
				match(Star);
				}
				break;
			case WordBoundary:
				enterOuterAlt(_localctx, 8);
				{
				setState(841);
				match(WordBoundary);
				}
				break;
			case EndOfSubjectOrLine:
				enterOuterAlt(_localctx, 9);
				{
				setState(842);
				match(EndOfSubjectOrLine);
				}
				break;
			case Pipe:
				enterOuterAlt(_localctx, 10);
				{
				setState(843);
				match(Pipe);
				}
				break;
			case OpenParen:
				enterOuterAlt(_localctx, 11);
				{
				setState(844);
				match(OpenParen);
				}
				break;
			case CloseParen:
				enterOuterAlt(_localctx, 12);
				{
				setState(845);
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
		public TerminalNode BellChar() { return getToken(PCREParser.BellChar, 0); }
		public TerminalNode EscapeChar() { return getToken(PCREParser.EscapeChar, 0); }
		public TerminalNode FormFeed() { return getToken(PCREParser.FormFeed, 0); }
		public TerminalNode NewLine() { return getToken(PCREParser.NewLine, 0); }
		public TerminalNode CarriageReturn() { return getToken(PCREParser.CarriageReturn, 0); }
		public TerminalNode Tab() { return getToken(PCREParser.Tab, 0); }
		public TerminalNode HexChar() { return getToken(PCREParser.HexChar, 0); }
		public TerminalNode Quoted() { return getToken(PCREParser.Quoted, 0); }
		public TerminalNode BlockQuoted() { return getToken(PCREParser.BlockQuoted, 0); }
		public TerminalNode OpenBrace() { return getToken(PCREParser.OpenBrace, 0); }
		public TerminalNode CloseBrace() { return getToken(PCREParser.CloseBrace, 0); }
		public TerminalNode Comma() { return getToken(PCREParser.Comma, 0); }
		public TerminalNode Hyphen() { return getToken(PCREParser.Hyphen, 0); }
		public TerminalNode LessThan() { return getToken(PCREParser.LessThan, 0); }
		public TerminalNode GreaterThan() { return getToken(PCREParser.GreaterThan, 0); }
		public TerminalNode SingleQuote() { return getToken(PCREParser.SingleQuote, 0); }
		public TerminalNode Underscore() { return getToken(PCREParser.Underscore, 0); }
		public TerminalNode Colon() { return getToken(PCREParser.Colon, 0); }
		public TerminalNode Hash() { return getToken(PCREParser.Hash, 0); }
		public TerminalNode Equals() { return getToken(PCREParser.Equals, 0); }
		public TerminalNode Exclamation() { return getToken(PCREParser.Exclamation, 0); }
		public TerminalNode Ampersand() { return getToken(PCREParser.Ampersand, 0); }
		public TerminalNode OtherChar() { return getToken(PCREParser.OtherChar, 0); }
		public Shared_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shared_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterShared_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitShared_literal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitShared_literal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shared_literalContext shared_literal() throws RecognitionException {
		Shared_literalContext _localctx = new Shared_literalContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_shared_literal);
		try {
			setState(874);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Backslash:
				enterOuterAlt(_localctx, 1);
				{
				setState(848);
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
				setState(849);
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
				setState(850);
				digit();
				}
				break;
			case BellChar:
				enterOuterAlt(_localctx, 4);
				{
				setState(851);
				match(BellChar);
				}
				break;
			case EscapeChar:
				enterOuterAlt(_localctx, 5);
				{
				setState(852);
				match(EscapeChar);
				}
				break;
			case FormFeed:
				enterOuterAlt(_localctx, 6);
				{
				setState(853);
				match(FormFeed);
				}
				break;
			case NewLine:
				enterOuterAlt(_localctx, 7);
				{
				setState(854);
				match(NewLine);
				}
				break;
			case CarriageReturn:
				enterOuterAlt(_localctx, 8);
				{
				setState(855);
				match(CarriageReturn);
				}
				break;
			case Tab:
				enterOuterAlt(_localctx, 9);
				{
				setState(856);
				match(Tab);
				}
				break;
			case HexChar:
				enterOuterAlt(_localctx, 10);
				{
				setState(857);
				match(HexChar);
				}
				break;
			case Quoted:
				enterOuterAlt(_localctx, 11);
				{
				setState(858);
				match(Quoted);
				}
				break;
			case BlockQuoted:
				enterOuterAlt(_localctx, 12);
				{
				setState(859);
				match(BlockQuoted);
				}
				break;
			case OpenBrace:
				enterOuterAlt(_localctx, 13);
				{
				setState(860);
				match(OpenBrace);
				}
				break;
			case CloseBrace:
				enterOuterAlt(_localctx, 14);
				{
				setState(861);
				match(CloseBrace);
				}
				break;
			case Comma:
				enterOuterAlt(_localctx, 15);
				{
				setState(862);
				match(Comma);
				}
				break;
			case Hyphen:
				enterOuterAlt(_localctx, 16);
				{
				setState(863);
				match(Hyphen);
				}
				break;
			case LessThan:
				enterOuterAlt(_localctx, 17);
				{
				setState(864);
				match(LessThan);
				}
				break;
			case GreaterThan:
				enterOuterAlt(_localctx, 18);
				{
				setState(865);
				match(GreaterThan);
				}
				break;
			case SingleQuote:
				enterOuterAlt(_localctx, 19);
				{
				setState(866);
				match(SingleQuote);
				}
				break;
			case Underscore:
				enterOuterAlt(_localctx, 20);
				{
				setState(867);
				match(Underscore);
				}
				break;
			case Colon:
				enterOuterAlt(_localctx, 21);
				{
				setState(868);
				match(Colon);
				}
				break;
			case Hash:
				enterOuterAlt(_localctx, 22);
				{
				setState(869);
				match(Hash);
				}
				break;
			case Equals:
				enterOuterAlt(_localctx, 23);
				{
				setState(870);
				match(Equals);
				}
				break;
			case Exclamation:
				enterOuterAlt(_localctx, 24);
				{
				setState(871);
				match(Exclamation);
				}
				break;
			case Ampersand:
				enterOuterAlt(_localctx, 25);
				{
				setState(872);
				match(Ampersand);
				}
				break;
			case OtherChar:
				enterOuterAlt(_localctx, 26);
				{
				setState(873);
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(876);
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
		public TerminalNode Backslash() { return getToken(PCREParser.Backslash, 0); }
		public List<Octal_digitContext> octal_digit() {
			return getRuleContexts(Octal_digitContext.class);
		}
		public Octal_digitContext octal_digit(int i) {
			return getRuleContext(Octal_digitContext.class,i);
		}
		public TerminalNode D0() { return getToken(PCREParser.D0, 0); }
		public TerminalNode D1() { return getToken(PCREParser.D1, 0); }
		public TerminalNode D2() { return getToken(PCREParser.D2, 0); }
		public TerminalNode D3() { return getToken(PCREParser.D3, 0); }
		public Octal_charContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octal_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterOctal_char(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitOctal_char(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitOctal_char(this);
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
			setState(887);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(878);
				match(Backslash);
				setState(879);
				_la = _input.LA(1);
				if ( !(((((_la - 115)) & ~0x3f) == 0 && ((1L << (_la - 115)) & ((1L << (D1 - 115)) | (1L << (D2 - 115)) | (1L << (D3 - 115)) | (1L << (D0 - 115)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(880);
				octal_digit();
				setState(881);
				octal_digit();
				}
				break;
			case 2:
				{
				setState(883);
				match(Backslash);
				setState(884);
				octal_digit();
				setState(885);
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
		public TerminalNode D0() { return getToken(PCREParser.D0, 0); }
		public TerminalNode D1() { return getToken(PCREParser.D1, 0); }
		public TerminalNode D2() { return getToken(PCREParser.D2, 0); }
		public TerminalNode D3() { return getToken(PCREParser.D3, 0); }
		public TerminalNode D4() { return getToken(PCREParser.D4, 0); }
		public TerminalNode D5() { return getToken(PCREParser.D5, 0); }
		public TerminalNode D6() { return getToken(PCREParser.D6, 0); }
		public TerminalNode D7() { return getToken(PCREParser.D7, 0); }
		public Octal_digitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octal_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterOctal_digit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitOctal_digit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitOctal_digit(this);
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
			setState(889);
			_la = _input.LA(1);
			if ( !(((((_la - 115)) & ~0x3f) == 0 && ((1L << (_la - 115)) & ((1L << (D1 - 115)) | (1L << (D2 - 115)) | (1L << (D3 - 115)) | (1L << (D4 - 115)) | (1L << (D5 - 115)) | (1L << (D6 - 115)) | (1L << (D7 - 115)) | (1L << (D0 - 115)))) != 0)) ) {
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterDigits(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitDigits(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitDigits(this);
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
			setState(892); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(891);
					digit();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(894); 
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
		public TerminalNode D0() { return getToken(PCREParser.D0, 0); }
		public TerminalNode D1() { return getToken(PCREParser.D1, 0); }
		public TerminalNode D2() { return getToken(PCREParser.D2, 0); }
		public TerminalNode D3() { return getToken(PCREParser.D3, 0); }
		public TerminalNode D4() { return getToken(PCREParser.D4, 0); }
		public TerminalNode D5() { return getToken(PCREParser.D5, 0); }
		public TerminalNode D6() { return getToken(PCREParser.D6, 0); }
		public TerminalNode D7() { return getToken(PCREParser.D7, 0); }
		public TerminalNode D8() { return getToken(PCREParser.D8, 0); }
		public TerminalNode D9() { return getToken(PCREParser.D9, 0); }
		public DigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitDigit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitDigit(this);
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
			setState(896);
			_la = _input.LA(1);
			if ( !(((((_la - 115)) & ~0x3f) == 0 && ((1L << (_la - 115)) & ((1L << (D1 - 115)) | (1L << (D2 - 115)) | (1L << (D3 - 115)) | (1L << (D4 - 115)) | (1L << (D5 - 115)) | (1L << (D6 - 115)) | (1L << (D7 - 115)) | (1L << (D8 - 115)) | (1L << (D9 - 115)) | (1L << (D0 - 115)))) != 0)) ) {
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(898);
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
		public List<TerminalNode> Underscore() { return getTokens(PCREParser.Underscore); }
		public TerminalNode Underscore(int i) {
			return getToken(PCREParser.Underscore, i);
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterAlpha_nums(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitAlpha_nums(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitAlpha_nums(this);
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
			setState(902);
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
				setState(900);
				letter();
				}
				break;
			case Underscore:
				{
				setState(901);
				match(Underscore);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(909);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Underscore || _la==ALC || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (BLC - 64)) | (1L << (CLC - 64)) | (1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)))) != 0)) {
				{
				setState(907);
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
					setState(904);
					letter();
					}
					break;
				case Underscore:
					{
					setState(905);
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
					setState(906);
					digit();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(911);
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
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterNon_close_parens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitNon_close_parens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitNon_close_parens(this);
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
			setState(913); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(912);
				non_close_paren();
				}
				}
				setState(915); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Quoted) | (1L << BlockQuoted) | (1L << BellChar) | (1L << ControlChar) | (1L << EscapeChar) | (1L << FormFeed) | (1L << NewLine) | (1L << CarriageReturn) | (1L << Tab) | (1L << Backslash) | (1L << HexChar) | (1L << Dot) | (1L << OneDataUnit) | (1L << DecimalDigit) | (1L << NotDecimalDigit) | (1L << HorizontalWhiteSpace) | (1L << NotHorizontalWhiteSpace) | (1L << NotNewLine) | (1L << CharWithProperty) | (1L << CharWithoutProperty) | (1L << NewLineSequence) | (1L << WhiteSpace) | (1L << NotWhiteSpace) | (1L << VerticalWhiteSpace) | (1L << NotVerticalWhiteSpace) | (1L << WordChar) | (1L << NotWordChar) | (1L << ExtendedUnicodeChar) | (1L << CharacterClassStart) | (1L << CharacterClassEnd) | (1L << Caret) | (1L << Hyphen) | (1L << POSIXNamedSet) | (1L << POSIXNegatedNamedSet) | (1L << QuestionMark) | (1L << Plus) | (1L << Star) | (1L << OpenBrace) | (1L << CloseBrace) | (1L << Comma) | (1L << WordBoundary) | (1L << NonWordBoundary) | (1L << StartOfSubject) | (1L << EndOfSubjectOrLine) | (1L << EndOfSubjectOrLineEndOfSubject) | (1L << EndOfSubject) | (1L << PreviousMatchInSubject) | (1L << ResetStartMatch) | (1L << SubroutineOrNamedReferenceStartG) | (1L << NamedReferenceStartK) | (1L << Pipe) | (1L << OpenParen) | (1L << LessThan) | (1L << GreaterThan) | (1L << SingleQuote) | (1L << Underscore) | (1L << Colon) | (1L << Hash) | (1L << Equals) | (1L << Exclamation) | (1L << Ampersand) | (1L << ALC))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (BLC - 64)) | (1L << (CLC - 64)) | (1L << (DLC - 64)) | (1L << (ELC - 64)) | (1L << (FLC - 64)) | (1L << (GLC - 64)) | (1L << (HLC - 64)) | (1L << (ILC - 64)) | (1L << (JLC - 64)) | (1L << (KLC - 64)) | (1L << (LLC - 64)) | (1L << (MLC - 64)) | (1L << (NLC - 64)) | (1L << (OLC - 64)) | (1L << (PLC - 64)) | (1L << (QLC - 64)) | (1L << (RLC - 64)) | (1L << (SLC - 64)) | (1L << (TLC - 64)) | (1L << (ULC - 64)) | (1L << (VLC - 64)) | (1L << (WLC - 64)) | (1L << (XLC - 64)) | (1L << (YLC - 64)) | (1L << (ZLC - 64)) | (1L << (AUC - 64)) | (1L << (BUC - 64)) | (1L << (CUC - 64)) | (1L << (DUC - 64)) | (1L << (EUC - 64)) | (1L << (FUC - 64)) | (1L << (GUC - 64)) | (1L << (HUC - 64)) | (1L << (IUC - 64)) | (1L << (JUC - 64)) | (1L << (KUC - 64)) | (1L << (LUC - 64)) | (1L << (MUC - 64)) | (1L << (NUC - 64)) | (1L << (OUC - 64)) | (1L << (PUC - 64)) | (1L << (QUC - 64)) | (1L << (RUC - 64)) | (1L << (SUC - 64)) | (1L << (TUC - 64)) | (1L << (UUC - 64)) | (1L << (VUC - 64)) | (1L << (WUC - 64)) | (1L << (XUC - 64)) | (1L << (YUC - 64)) | (1L << (ZUC - 64)) | (1L << (D1 - 64)) | (1L << (D2 - 64)) | (1L << (D3 - 64)) | (1L << (D4 - 64)) | (1L << (D5 - 64)) | (1L << (D6 - 64)) | (1L << (D7 - 64)) | (1L << (D8 - 64)) | (1L << (D9 - 64)) | (1L << (D0 - 64)) | (1L << (OtherChar - 64)))) != 0) );
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
		public TerminalNode CloseParen() { return getToken(PCREParser.CloseParen, 0); }
		public Non_close_parenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_close_paren; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterNon_close_paren(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitNon_close_paren(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitNon_close_paren(this);
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
			setState(917);
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
		public TerminalNode ALC() { return getToken(PCREParser.ALC, 0); }
		public TerminalNode BLC() { return getToken(PCREParser.BLC, 0); }
		public TerminalNode CLC() { return getToken(PCREParser.CLC, 0); }
		public TerminalNode DLC() { return getToken(PCREParser.DLC, 0); }
		public TerminalNode ELC() { return getToken(PCREParser.ELC, 0); }
		public TerminalNode FLC() { return getToken(PCREParser.FLC, 0); }
		public TerminalNode GLC() { return getToken(PCREParser.GLC, 0); }
		public TerminalNode HLC() { return getToken(PCREParser.HLC, 0); }
		public TerminalNode ILC() { return getToken(PCREParser.ILC, 0); }
		public TerminalNode JLC() { return getToken(PCREParser.JLC, 0); }
		public TerminalNode KLC() { return getToken(PCREParser.KLC, 0); }
		public TerminalNode LLC() { return getToken(PCREParser.LLC, 0); }
		public TerminalNode MLC() { return getToken(PCREParser.MLC, 0); }
		public TerminalNode NLC() { return getToken(PCREParser.NLC, 0); }
		public TerminalNode OLC() { return getToken(PCREParser.OLC, 0); }
		public TerminalNode PLC() { return getToken(PCREParser.PLC, 0); }
		public TerminalNode QLC() { return getToken(PCREParser.QLC, 0); }
		public TerminalNode RLC() { return getToken(PCREParser.RLC, 0); }
		public TerminalNode SLC() { return getToken(PCREParser.SLC, 0); }
		public TerminalNode TLC() { return getToken(PCREParser.TLC, 0); }
		public TerminalNode ULC() { return getToken(PCREParser.ULC, 0); }
		public TerminalNode VLC() { return getToken(PCREParser.VLC, 0); }
		public TerminalNode WLC() { return getToken(PCREParser.WLC, 0); }
		public TerminalNode XLC() { return getToken(PCREParser.XLC, 0); }
		public TerminalNode YLC() { return getToken(PCREParser.YLC, 0); }
		public TerminalNode ZLC() { return getToken(PCREParser.ZLC, 0); }
		public TerminalNode AUC() { return getToken(PCREParser.AUC, 0); }
		public TerminalNode BUC() { return getToken(PCREParser.BUC, 0); }
		public TerminalNode CUC() { return getToken(PCREParser.CUC, 0); }
		public TerminalNode DUC() { return getToken(PCREParser.DUC, 0); }
		public TerminalNode EUC() { return getToken(PCREParser.EUC, 0); }
		public TerminalNode FUC() { return getToken(PCREParser.FUC, 0); }
		public TerminalNode GUC() { return getToken(PCREParser.GUC, 0); }
		public TerminalNode HUC() { return getToken(PCREParser.HUC, 0); }
		public TerminalNode IUC() { return getToken(PCREParser.IUC, 0); }
		public TerminalNode JUC() { return getToken(PCREParser.JUC, 0); }
		public TerminalNode KUC() { return getToken(PCREParser.KUC, 0); }
		public TerminalNode LUC() { return getToken(PCREParser.LUC, 0); }
		public TerminalNode MUC() { return getToken(PCREParser.MUC, 0); }
		public TerminalNode NUC() { return getToken(PCREParser.NUC, 0); }
		public TerminalNode OUC() { return getToken(PCREParser.OUC, 0); }
		public TerminalNode PUC() { return getToken(PCREParser.PUC, 0); }
		public TerminalNode QUC() { return getToken(PCREParser.QUC, 0); }
		public TerminalNode RUC() { return getToken(PCREParser.RUC, 0); }
		public TerminalNode SUC() { return getToken(PCREParser.SUC, 0); }
		public TerminalNode TUC() { return getToken(PCREParser.TUC, 0); }
		public TerminalNode UUC() { return getToken(PCREParser.UUC, 0); }
		public TerminalNode VUC() { return getToken(PCREParser.VUC, 0); }
		public TerminalNode WUC() { return getToken(PCREParser.WUC, 0); }
		public TerminalNode XUC() { return getToken(PCREParser.XUC, 0); }
		public TerminalNode YUC() { return getToken(PCREParser.YUC, 0); }
		public TerminalNode ZUC() { return getToken(PCREParser.ZUC, 0); }
		public LetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).enterLetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PCREListener ) ((PCREListener)listener).exitLetter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PCREVisitor ) return ((PCREVisitor<? extends T>)visitor).visitLetter(this);
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
			setState(919);
			_la = _input.LA(1);
			if ( !(((((_la - 63)) & ~0x3f) == 0 && ((1L << (_la - 63)) & ((1L << (ALC - 63)) | (1L << (BLC - 63)) | (1L << (CLC - 63)) | (1L << (DLC - 63)) | (1L << (ELC - 63)) | (1L << (FLC - 63)) | (1L << (GLC - 63)) | (1L << (HLC - 63)) | (1L << (ILC - 63)) | (1L << (JLC - 63)) | (1L << (KLC - 63)) | (1L << (LLC - 63)) | (1L << (MLC - 63)) | (1L << (NLC - 63)) | (1L << (OLC - 63)) | (1L << (PLC - 63)) | (1L << (QLC - 63)) | (1L << (RLC - 63)) | (1L << (SLC - 63)) | (1L << (TLC - 63)) | (1L << (ULC - 63)) | (1L << (VLC - 63)) | (1L << (WLC - 63)) | (1L << (XLC - 63)) | (1L << (YLC - 63)) | (1L << (ZLC - 63)) | (1L << (AUC - 63)) | (1L << (BUC - 63)) | (1L << (CUC - 63)) | (1L << (DUC - 63)) | (1L << (EUC - 63)) | (1L << (FUC - 63)) | (1L << (GUC - 63)) | (1L << (HUC - 63)) | (1L << (IUC - 63)) | (1L << (JUC - 63)) | (1L << (KUC - 63)) | (1L << (LUC - 63)) | (1L << (MUC - 63)) | (1L << (NUC - 63)) | (1L << (OUC - 63)) | (1L << (PUC - 63)) | (1L << (QUC - 63)) | (1L << (RUC - 63)) | (1L << (SUC - 63)) | (1L << (TUC - 63)) | (1L << (UUC - 63)) | (1L << (VUC - 63)) | (1L << (WUC - 63)) | (1L << (XUC - 63)) | (1L << (YUC - 63)) | (1L << (ZUC - 63)))) != 0)) ) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\177\u039c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\3\3\3\3\3\7\3S\n\3\f"+
		"\3\16\3V\13\3\3\4\7\4Y\n\4\f\4\16\4\\\13\4\3\5\3\5\5\5`\n\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\5\6z\n\6\3\7\3\7\3\7\5\7\177\n\7\3\b\3\b\3\b\3\b\3\b"+
		"\6\b\u0086\n\b\r\b\16\b\u0087\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0090\n\b\f"+
		"\b\16\b\u0093\13\b\3\b\3\b\3\b\3\b\6\b\u0099\n\b\r\b\16\b\u009a\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\6\b\u00a3\n\b\r\b\16\b\u00a4\3\b\3\b\3\b\3\b\3\b\7"+
		"\b\u00ac\n\b\f\b\16\b\u00af\13\b\3\b\3\b\3\b\6\b\u00b4\n\b\r\b\16\b\u00b5"+
		"\3\b\3\b\5\b\u00ba\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00e5\n\t\3\n\3\n\3"+
		"\n\5\n\u00ea\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13\u0109\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u011d\n\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u015b\n\16\3\17\6\17\u015e\n\17\r\17\16\17\u015f\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u017e\n\21\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u01ce\n\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u01d8\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u01e5\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\5\23\u01f2\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\5\23\u0200\n\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u020e\n\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u021b\n\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\5\23\u0227\n\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0235\n\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0246\n\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\5\23\u0257\n\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23"+
		"\u0263\n\23\3\23\3\23\5\23\u0267\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0278\n\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\5\24\u0281\n\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u02cd"+
		"\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u030c\n\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\5\26\u0318\n\26\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u0334\n\27\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\5\30\u033d\n\30\3\31\3\31\3\32\3\32\5\32\u0343\n\32\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0351\n\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u036d"+
		"\n\34\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u037a"+
		"\n\36\3\37\3\37\3 \6 \u037f\n \r \16 \u0380\3!\3!\3\"\3\"\3#\3#\5#\u0389"+
		"\n#\3#\3#\3#\7#\u038e\n#\f#\16#\u0391\13#\3$\6$\u0394\n$\r$\16$\u0395"+
		"\3%\3%\3&\3&\3&\2\2\'\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,."+
		"\60\62\64\668:<>@BDFHJ\2\t\b\2IIMMSSXXddoo\5\2\6\6\20\35#$\4\2uw~~\4\2"+
		"u{~~\3\2u~\3\2\67\67\3\2At\2\u041f\2L\3\2\2\2\4O\3\2\2\2\6Z\3\2\2\2\b"+
		"]\3\2\2\2\ny\3\2\2\2\f~\3\2\2\2\16\u00b9\3\2\2\2\20\u00e4\3\2\2\2\22\u00e9"+
		"\3\2\2\2\24\u0108\3\2\2\2\26\u011c\3\2\2\2\30\u011e\3\2\2\2\32\u015a\3"+
		"\2\2\2\34\u015d\3\2\2\2\36\u0161\3\2\2\2 \u017d\3\2\2\2\"\u01cd\3\2\2"+
		"\2$\u0266\3\2\2\2&\u02cc\3\2\2\2(\u030b\3\2\2\2*\u0317\3\2\2\2,\u0333"+
		"\3\2\2\2.\u033c\3\2\2\2\60\u033e\3\2\2\2\62\u0342\3\2\2\2\64\u0350\3\2"+
		"\2\2\66\u036c\3\2\2\28\u036e\3\2\2\2:\u0379\3\2\2\2<\u037b\3\2\2\2>\u037e"+
		"\3\2\2\2@\u0382\3\2\2\2B\u0384\3\2\2\2D\u0388\3\2\2\2F\u0393\3\2\2\2H"+
		"\u0397\3\2\2\2J\u0399\3\2\2\2LM\5\4\3\2MN\7\2\2\3N\3\3\2\2\2OT\5\6\4\2"+
		"PQ\7\65\2\2QS\5\6\4\2RP\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\5\3\2\2"+
		"\2VT\3\2\2\2WY\5\b\5\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\7\3\2"+
		"\2\2\\Z\3\2\2\2]_\5,\27\2^`\5\n\6\2_^\3\2\2\2_`\3\2\2\2`\t\3\2\2\2ab\7"+
		"%\2\2bz\5\f\7\2cd\7&\2\2dz\5\f\7\2ef\7\'\2\2fz\5\f\7\2gh\7(\2\2hi\58\35"+
		"\2ij\7)\2\2jk\5\f\7\2kz\3\2\2\2lm\7(\2\2mn\58\35\2no\7*\2\2op\7)\2\2p"+
		"q\5\f\7\2qz\3\2\2\2rs\7(\2\2st\58\35\2tu\7*\2\2uv\58\35\2vw\7)\2\2wx\5"+
		"\f\7\2xz\3\2\2\2ya\3\2\2\2yc\3\2\2\2ye\3\2\2\2yg\3\2\2\2yl\3\2\2\2yr\3"+
		"\2\2\2z\13\3\2\2\2{\177\7&\2\2|\177\7%\2\2}\177\3\2\2\2~{\3\2\2\2~|\3"+
		"\2\2\2~}\3\2\2\2\177\r\3\2\2\2\u0080\u0081\7\37\2\2\u0081\u0082\7!\2\2"+
		"\u0082\u0083\7 \2\2\u0083\u0085\7\"\2\2\u0084\u0086\5.\30\2\u0085\u0084"+
		"\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u008a\7 \2\2\u008a\u00ba\3\2\2\2\u008b\u008c\7\37"+
		"\2\2\u008c\u008d\7!\2\2\u008d\u0091\7 \2\2\u008e\u0090\5.\30\2\u008f\u008e"+
		"\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u00ba\7 \2\2\u0095\u0096\7\37"+
		"\2\2\u0096\u0098\7!\2\2\u0097\u0099\5.\30\2\u0098\u0097\3\2\2\2\u0099"+
		"\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u009d\7 \2\2\u009d\u00ba\3\2\2\2\u009e\u009f\7\37\2\2\u009f"+
		"\u00a0\7 \2\2\u00a0\u00a2\7\"\2\2\u00a1\u00a3\5.\30\2\u00a2\u00a1\3\2"+
		"\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a7\7 \2\2\u00a7\u00ba\3\2\2\2\u00a8\u00a9\7\37"+
		"\2\2\u00a9\u00ad\7 \2\2\u00aa\u00ac\5.\30\2\u00ab\u00aa\3\2\2\2\u00ac"+
		"\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0\3\2"+
		"\2\2\u00af\u00ad\3\2\2\2\u00b0\u00ba\7 \2\2\u00b1\u00b3\7\37\2\2\u00b2"+
		"\u00b4\5.\30\2\u00b3\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b3\3\2"+
		"\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\7 \2\2\u00b8"+
		"\u00ba\3\2\2\2\u00b9\u0080\3\2\2\2\u00b9\u008b\3\2\2\2\u00b9\u0095\3\2"+
		"\2\2\u00b9\u009e\3\2\2\2\u00b9\u00a8\3\2\2\2\u00b9\u00b1\3\2\2\2\u00ba"+
		"\17\3\2\2\2\u00bb\u00e5\5\22\n\2\u00bc\u00bd\7\63\2\2\u00bd\u00e5\58\35"+
		"\2\u00be\u00bf\7\63\2\2\u00bf\u00c0\7(\2\2\u00c0\u00c1\58\35\2\u00c1\u00c2"+
		"\7)\2\2\u00c2\u00e5\3\2\2\2\u00c3\u00c4\7\63\2\2\u00c4\u00c5\7(\2\2\u00c5"+
		"\u00c6\7\"\2\2\u00c6\u00c7\58\35\2\u00c7\u00c8\7)\2\2\u00c8\u00e5\3\2"+
		"\2\2\u00c9\u00ca\7\64\2\2\u00ca\u00cb\78\2\2\u00cb\u00cc\5B\"\2\u00cc"+
		"\u00cd\79\2\2\u00cd\u00e5\3\2\2\2\u00ce\u00cf\7\64\2\2\u00cf\u00d0\7:"+
		"\2\2\u00d0\u00d1\5B\"\2\u00d1\u00d2\7:\2\2\u00d2\u00e5\3\2\2\2\u00d3\u00d4"+
		"\7\63\2\2\u00d4\u00d5\7(\2\2\u00d5\u00d6\5B\"\2\u00d6\u00d7\7)\2\2\u00d7"+
		"\u00e5\3\2\2\2\u00d8\u00d9\7\64\2\2\u00d9\u00da\7(\2\2\u00da\u00db\5B"+
		"\"\2\u00db\u00dc\7)\2\2\u00dc\u00e5\3\2\2\2\u00dd\u00de\7\66\2\2\u00de"+
		"\u00df\7%\2\2\u00df\u00e0\7j\2\2\u00e0\u00e1\7>\2\2\u00e1\u00e2\5B\"\2"+
		"\u00e2\u00e3\7\67\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00bb\3\2\2\2\u00e4\u00bc"+
		"\3\2\2\2\u00e4\u00be\3\2\2\2\u00e4\u00c3\3\2\2\2\u00e4\u00c9\3\2\2\2\u00e4"+
		"\u00ce\3\2\2\2\u00e4\u00d3\3\2\2\2\u00e4\u00d8\3\2\2\2\u00e4\u00dd\3\2"+
		"\2\2\u00e5\21\3\2\2\2\u00e6\u00ea\5:\36\2\u00e7\u00e8\7\f\2\2\u00e8\u00ea"+
		"\5@!\2\u00e9\u00e6\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\23\3\2\2\2\u00eb"+
		"\u00ec\7\66\2\2\u00ec\u00ed\7%\2\2\u00ed\u00ee\78\2\2\u00ee\u00ef\5B\""+
		"\2\u00ef\u00f0\79\2\2\u00f0\u00f1\5\4\3\2\u00f1\u00f2\7\67\2\2\u00f2\u0109"+
		"\3\2\2\2\u00f3\u00f4\7\66\2\2\u00f4\u00f5\7%\2\2\u00f5\u00f6\7:\2\2\u00f6"+
		"\u00f7\5B\"\2\u00f7\u00f8\7:\2\2\u00f8\u00f9\5\4\3\2\u00f9\u00fa\7\67"+
		"\2\2\u00fa\u0109\3\2\2\2\u00fb\u00fc\7\66\2\2\u00fc\u00fd\7%\2\2\u00fd"+
		"\u00fe\7j\2\2\u00fe\u00ff\78\2\2\u00ff\u0100\5B\"\2\u0100\u0101\79\2\2"+
		"\u0101\u0102\5\4\3\2\u0102\u0103\7\67\2\2\u0103\u0109\3\2\2\2\u0104\u0105"+
		"\7\66\2\2\u0105\u0106\5\4\3\2\u0106\u0107\7\67\2\2\u0107\u0109\3\2\2\2"+
		"\u0108\u00eb\3\2\2\2\u0108\u00f3\3\2\2\2\u0108\u00fb\3\2\2\2\u0108\u0104"+
		"\3\2\2\2\u0109\25\3\2\2\2\u010a\u010b\7\66\2\2\u010b\u010c\7%\2\2\u010c"+
		"\u010d\7<\2\2\u010d\u010e\5\4\3\2\u010e\u010f\7\67\2\2\u010f\u011d\3\2"+
		"\2\2\u0110\u0111\7\66\2\2\u0111\u0112\7%\2\2\u0112\u0113\7\65\2\2\u0113"+
		"\u0114\5\4\3\2\u0114\u0115\7\67\2\2\u0115\u011d\3\2\2\2\u0116\u0117\7"+
		"\66\2\2\u0117\u0118\7%\2\2\u0118\u0119\79\2\2\u0119\u011a\5\4\3\2\u011a"+
		"\u011b\7\67\2\2\u011b\u011d\3\2\2\2\u011c\u010a\3\2\2\2\u011c\u0110\3"+
		"\2\2\2\u011c\u0116\3\2\2\2\u011d\27\3\2\2\2\u011e\u011f\7\66\2\2\u011f"+
		"\u0120\7%\2\2\u0120\u0121\7=\2\2\u0121\u0122\5F$\2\u0122\u0123\7\67\2"+
		"\2\u0123\31\3\2\2\2\u0124\u0125\7\66\2\2\u0125\u0126\7%\2\2\u0126\u0127"+
		"\5\34\17\2\u0127\u0128\7\"\2\2\u0128\u0129\5\34\17\2\u0129\u012a\7\67"+
		"\2\2\u012a\u015b\3\2\2\2\u012b\u012c\7\66\2\2\u012c\u012d\7%\2\2\u012d"+
		"\u012e\5\34\17\2\u012e\u012f\7\67\2\2\u012f\u015b\3\2\2\2\u0130\u0131"+
		"\7\66\2\2\u0131\u0132\7%\2\2\u0132\u0133\7\"\2\2\u0133\u0134\5\34\17\2"+
		"\u0134\u0135\7\67\2\2\u0135\u015b\3\2\2\2\u0136\u0137\7\66\2\2\u0137\u0138"+
		"\7\'\2\2\u0138\u0139\7h\2\2\u0139\u013a\7i\2\2\u013a\u013b\7;\2\2\u013b"+
		"\u013c\7m\2\2\u013c\u013d\7n\2\2\u013d\u013e\7[\2\2\u013e\u013f\7l\2\2"+
		"\u013f\u0140\7n\2\2\u0140\u0141\7;\2\2\u0141\u0142\7i\2\2\u0142\u0143"+
		"\7j\2\2\u0143\u0144\7n\2\2\u0144\u015b\7\67\2\2\u0145\u0146\7\66\2\2\u0146"+
		"\u0147\7\'\2\2\u0147\u0148\7o\2\2\u0148\u0149\7n\2\2\u0149\u014a\7`\2"+
		"\2\u014a\u014b\7|\2\2\u014b\u015b\7\67\2\2\u014c\u014d\7\66\2\2\u014d"+
		"\u014e\7\'\2\2\u014e\u014f\7o\2\2\u014f\u0150\7n\2\2\u0150\u0151\7`\2"+
		"\2\u0151\u0152\7u\2\2\u0152\u0153\7z\2\2\u0153\u015b\7\67\2\2\u0154\u0155"+
		"\7\66\2\2\u0155\u0156\7\'\2\2\u0156\u0157\7o\2\2\u0157\u0158\7]\2\2\u0158"+
		"\u0159\7j\2\2\u0159\u015b\7\67\2\2\u015a\u0124\3\2\2\2\u015a\u012b\3\2"+
		"\2\2\u015a\u0130\3\2\2\2\u015a\u0136\3\2\2\2\u015a\u0145\3\2\2\2\u015a"+
		"\u014c\3\2\2\2\u015a\u0154\3\2\2\2\u015b\33\3\2\2\2\u015c\u015e\5\36\20"+
		"\2\u015d\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u015d\3\2\2\2\u015f\u0160"+
		"\3\2\2\2\u0160\35\3\2\2\2\u0161\u0162\t\2\2\2\u0162\37\3\2\2\2\u0163\u0164"+
		"\7\66\2\2\u0164\u0165\7%\2\2\u0165\u0166\7>\2\2\u0166\u0167\5\4\3\2\u0167"+
		"\u0168\7\67\2\2\u0168\u017e\3\2\2\2\u0169\u016a\7\66\2\2\u016a\u016b\7"+
		"%\2\2\u016b\u016c\7?\2\2\u016c\u016d\5\4\3\2\u016d\u016e\7\67\2\2\u016e"+
		"\u017e\3\2\2\2\u016f\u0170\7\66\2\2\u0170\u0171\7%\2\2\u0171\u0172\78"+
		"\2\2\u0172\u0173\7>\2\2\u0173\u0174\5\4\3\2\u0174\u0175\7\67\2\2\u0175"+
		"\u017e\3\2\2\2\u0176\u0177\7\66\2\2\u0177\u0178\7%\2\2\u0178\u0179\78"+
		"\2\2\u0179\u017a\7?\2\2\u017a\u017b\5\4\3\2\u017b\u017c\7\67\2\2\u017c"+
		"\u017e\3\2\2\2\u017d\u0163\3\2\2\2\u017d\u0169\3\2\2\2\u017d\u016f\3\2"+
		"\2\2\u017d\u0176\3\2\2\2\u017e!\3\2\2\2\u017f\u0180\7\66\2\2\u0180\u0181"+
		"\7%\2\2\u0181\u0182\7l\2\2\u0182\u01ce\7\67\2\2\u0183\u0184\7\66\2\2\u0184"+
		"\u0185\7%\2\2\u0185\u0186\58\35\2\u0186\u0187\7\67\2\2\u0187\u01ce\3\2"+
		"\2\2\u0188\u0189\7\66\2\2\u0189\u018a\7%\2\2\u018a\u018b\7&\2\2\u018b"+
		"\u018c\58\35\2\u018c\u018d\7\67\2\2\u018d\u01ce\3\2\2\2\u018e\u018f\7"+
		"\66\2\2\u018f\u0190\7%\2\2\u0190\u0191\7\"\2\2\u0191\u0192\58\35\2\u0192"+
		"\u0193\7\67\2\2\u0193\u01ce\3\2\2\2\u0194\u0195\7\66\2\2\u0195\u0196\7"+
		"%\2\2\u0196\u0197\7@\2\2\u0197\u0198\5B\"\2\u0198\u0199\7\67\2\2\u0199"+
		"\u01ce\3\2\2\2\u019a\u019b\7\66\2\2\u019b\u019c\7%\2\2\u019c\u019d\7j"+
		"\2\2\u019d\u019e\79\2\2\u019e\u019f\5B\"\2\u019f\u01a0\7\67\2\2\u01a0"+
		"\u01ce\3\2\2\2\u01a1\u01a2\7\63\2\2\u01a2\u01a3\78\2\2\u01a3\u01a4\5B"+
		"\"\2\u01a4\u01a5\79\2\2\u01a5\u01ce\3\2\2\2\u01a6\u01a7\7\63\2\2\u01a7"+
		"\u01a8\7:\2\2\u01a8\u01a9\5B\"\2\u01a9\u01aa\7:\2\2\u01aa\u01ce\3\2\2"+
		"\2\u01ab\u01ac\7\63\2\2\u01ac\u01ad\78\2\2\u01ad\u01ae\58\35\2\u01ae\u01af"+
		"\79\2\2\u01af\u01ce\3\2\2\2\u01b0\u01b1\7\63\2\2\u01b1\u01b2\7:\2\2\u01b2"+
		"\u01b3\58\35\2\u01b3\u01b4\7:\2\2\u01b4\u01ce\3\2\2\2\u01b5\u01b6\7\63"+
		"\2\2\u01b6\u01b7\78\2\2\u01b7\u01b8\7&\2\2\u01b8\u01b9\58\35\2\u01b9\u01ba"+
		"\79\2\2\u01ba\u01ce\3\2\2\2\u01bb\u01bc\7\63\2\2\u01bc\u01bd\7:\2\2\u01bd"+
		"\u01be\7&\2\2\u01be\u01bf\58\35\2\u01bf\u01c0\7:\2\2\u01c0\u01ce\3\2\2"+
		"\2\u01c1\u01c2\7\63\2\2\u01c2\u01c3\78\2\2\u01c3\u01c4\7\"\2\2\u01c4\u01c5"+
		"\58\35\2\u01c5\u01c6\79\2\2\u01c6\u01ce\3\2\2\2\u01c7\u01c8\7\63\2\2\u01c8"+
		"\u01c9\7:\2\2\u01c9\u01ca\7\"\2\2\u01ca\u01cb\58\35\2\u01cb\u01cc\7:\2"+
		"\2\u01cc\u01ce\3\2\2\2\u01cd\u017f\3\2\2\2\u01cd\u0183\3\2\2\2\u01cd\u0188"+
		"\3\2\2\2\u01cd\u018e\3\2\2\2\u01cd\u0194\3\2\2\2\u01cd\u019a\3\2\2\2\u01cd"+
		"\u01a1\3\2\2\2\u01cd\u01a6\3\2\2\2\u01cd\u01ab\3\2\2\2\u01cd\u01b0\3\2"+
		"\2\2\u01cd\u01b5\3\2\2\2\u01cd\u01bb\3\2\2\2\u01cd\u01c1\3\2\2\2\u01cd"+
		"\u01c7\3\2\2\2\u01ce#\3\2\2\2\u01cf\u01d0\7\66\2\2\u01d0\u01d1\7%\2\2"+
		"\u01d1\u01d2\7\66\2\2\u01d2\u01d3\58\35\2\u01d3\u01d4\7\67\2\2\u01d4\u01d7"+
		"\5\4\3\2\u01d5\u01d6\7\65\2\2\u01d6\u01d8\5\4\3\2\u01d7\u01d5\3\2\2\2"+
		"\u01d7\u01d8\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u01da\7\67\2\2\u01da\u0267"+
		"\3\2\2\2\u01db\u01dc\7\66\2\2\u01dc\u01dd\7%\2\2\u01dd\u01de\7\66\2\2"+
		"\u01de\u01df\7&\2\2\u01df\u01e0\58\35\2\u01e0\u01e1\7\67\2\2\u01e1\u01e4"+
		"\5\4\3\2\u01e2\u01e3\7\65\2\2\u01e3\u01e5\5\4\3\2\u01e4\u01e2\3\2\2\2"+
		"\u01e4\u01e5\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01e7\7\67\2\2\u01e7\u0267"+
		"\3\2\2\2\u01e8\u01e9\7\66\2\2\u01e9\u01ea\7%\2\2\u01ea\u01eb\7\66\2\2"+
		"\u01eb\u01ec\7\"\2\2\u01ec\u01ed\58\35\2\u01ed\u01ee\7\67\2\2\u01ee\u01f1"+
		"\5\4\3\2\u01ef\u01f0\7\65\2\2\u01f0\u01f2\5\4\3\2\u01f1\u01ef\3\2\2\2"+
		"\u01f1\u01f2\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f4\7\67\2\2\u01f4\u0267"+
		"\3\2\2\2\u01f5\u01f6\7\66\2\2\u01f6\u01f7\7%\2\2\u01f7\u01f8\7\66\2\2"+
		"\u01f8\u01f9\78\2\2\u01f9\u01fa\5B\"\2\u01fa\u01fb\79\2\2\u01fb\u01fc"+
		"\7\67\2\2\u01fc\u01ff\5\4\3\2\u01fd\u01fe\7\65\2\2\u01fe\u0200\5\4\3\2"+
		"\u01ff\u01fd\3\2\2\2\u01ff\u0200\3\2\2\2\u0200\u0201\3\2\2\2\u0201\u0202"+
		"\7\67\2\2\u0202\u0267\3\2\2\2\u0203\u0204\7\66\2\2\u0204\u0205\7%\2\2"+
		"\u0205\u0206\7\66\2\2\u0206\u0207\7:\2\2\u0207\u0208\5B\"\2\u0208\u0209"+
		"\7:\2\2\u0209\u020a\7\67\2\2\u020a\u020d\5\4\3\2\u020b\u020c\7\65\2\2"+
		"\u020c\u020e\5\4\3\2\u020d\u020b\3\2\2\2\u020d\u020e\3\2\2\2\u020e\u020f"+
		"\3\2\2\2\u020f\u0210\7\67\2\2\u0210\u0267\3\2\2\2\u0211\u0212\7\66\2\2"+
		"\u0212\u0213\7%\2\2\u0213\u0214\7\66\2\2\u0214\u0215\7l\2\2\u0215\u0216"+
		"\58\35\2\u0216\u0217\7\67\2\2\u0217\u021a\5\4\3\2\u0218\u0219\7\65\2\2"+
		"\u0219\u021b\5\4\3\2\u021a\u0218\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021c"+
		"\3\2\2\2\u021c\u021d\7\67\2\2\u021d\u0267\3\2\2\2\u021e\u021f\7\66\2\2"+
		"\u021f\u0220\7%\2\2\u0220\u0221\7\66\2\2\u0221\u0222\7l\2\2\u0222\u0223"+
		"\7\67\2\2\u0223\u0226\5\4\3\2\u0224\u0225\7\65\2\2\u0225\u0227\5\4\3\2"+
		"\u0226\u0224\3\2\2\2\u0226\u0227\3\2\2\2\u0227\u0228\3\2\2\2\u0228\u0229"+
		"\7\67\2\2\u0229\u0267\3\2\2\2\u022a\u022b\7\66\2\2\u022b\u022c\7%\2\2"+
		"\u022c\u022d\7\66\2\2\u022d\u022e\7l\2\2\u022e\u022f\7@\2\2\u022f\u0230"+
		"\5B\"\2\u0230\u0231\7\67\2\2\u0231\u0234\5\4\3\2\u0232\u0233\7\65\2\2"+
		"\u0233\u0235\5\4\3\2\u0234\u0232\3\2\2\2\u0234\u0235\3\2\2\2\u0235\u0236"+
		"\3\2\2\2\u0236\u0237\7\67\2\2\u0237\u0267\3\2\2\2\u0238\u0239\7\66\2\2"+
		"\u0239\u023a\7%\2\2\u023a\u023b\7\66\2\2\u023b\u023c\7^\2\2\u023c\u023d"+
		"\7_\2\2\u023d\u023e\7`\2\2\u023e\u023f\7c\2\2\u023f\u0240\7h\2\2\u0240"+
		"\u0241\7_\2\2\u0241\u0242\7\67\2\2\u0242\u0245\5\4\3\2\u0243\u0244\7\65"+
		"\2\2\u0244\u0246\5\4\3\2\u0245\u0243\3\2\2\2\u0245\u0246\3\2\2\2\u0246"+
		"\u0247\3\2\2\2\u0247\u0248\7\67\2\2\u0248\u0267\3\2\2\2\u0249\u024a\7"+
		"\66\2\2\u024a\u024b\7%\2\2\u024b\u024c\7\66\2\2\u024c\u024d\7A\2\2\u024d"+
		"\u024e\7S\2\2\u024e\u024f\7S\2\2\u024f\u0250\7E\2\2\u0250\u0251\7R\2\2"+
		"\u0251\u0252\7T\2\2\u0252\u0253\7\67\2\2\u0253\u0256\5\4\3\2\u0254\u0255"+
		"\7\65\2\2\u0255\u0257\5\4\3\2\u0256\u0254\3\2\2\2\u0256\u0257\3\2\2\2"+
		"\u0257\u0258\3\2\2\2\u0258\u0259\7\67\2\2\u0259\u0267\3\2\2\2\u025a\u025b"+
		"\7\66\2\2\u025b\u025c\7%\2\2\u025c\u025d\7\66\2\2\u025d\u025e\5B\"\2\u025e"+
		"\u025f\7\67\2\2\u025f\u0262\5\4\3\2\u0260\u0261\7\65\2\2\u0261\u0263\5"+
		"\4\3\2\u0262\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263\u0264\3\2\2\2\u0264"+
		"\u0265\7\67\2\2\u0265\u0267\3\2\2\2\u0266\u01cf\3\2\2\2\u0266\u01db\3"+
		"\2\2\2\u0266\u01e8\3\2\2\2\u0266\u01f5\3\2\2\2\u0266\u0203\3\2\2\2\u0266"+
		"\u0211\3\2\2\2\u0266\u021e\3\2\2\2\u0266\u022a\3\2\2\2\u0266\u0238\3\2"+
		"\2\2\u0266\u0249\3\2\2\2\u0266\u025a\3\2\2\2\u0267%\3\2\2\2\u0268\u0269"+
		"\7\66\2\2\u0269\u026a\7\'\2\2\u026a\u026b\7[\2\2\u026b\u026c\7]\2\2\u026c"+
		"\u026d\7]\2\2\u026d\u026e\7_\2\2\u026e\u026f\7j\2\2\u026f\u0270\7n\2\2"+
		"\u0270\u02cd\7\67\2\2\u0271\u0272\7\66\2\2\u0272\u0273\7\'\2\2\u0273\u0277"+
		"\7`\2\2\u0274\u0275\7[\2\2\u0275\u0276\7c\2\2\u0276\u0278\7f\2\2\u0277"+
		"\u0274\3\2\2\2\u0277\u0278\3\2\2\2\u0278\u0279\3\2\2\2\u0279\u02cd\7\67"+
		"\2\2\u027a\u027b\7\66\2\2\u027b\u0280\7\'\2\2\u027c\u027d\7g\2\2\u027d"+
		"\u027e\7[\2\2\u027e\u027f\7l\2\2\u027f\u0281\7e\2\2\u0280\u027c\3\2\2"+
		"\2\u0280\u0281\3\2\2\2\u0281\u0282\3\2\2\2\u0282\u0283\7<\2\2\u0283\u0284"+
		"\7h\2\2\u0284\u0285\7[\2\2\u0285\u0286\7g\2\2\u0286\u0287\7_\2\2\u0287"+
		"\u02cd\7\67\2\2\u0288\u0289\7\66\2\2\u0289\u028a\7\'\2\2\u028a\u028b\7"+
		"]\2\2\u028b\u028c\7i\2\2\u028c\u028d\7g\2\2\u028d\u028e\7g\2\2\u028e\u028f"+
		"\7c\2\2\u028f\u0290\7n\2\2\u0290\u02cd\7\67\2\2\u0291\u0292\7\66\2\2\u0292"+
		"\u0293\7\'\2\2\u0293\u0294\7j\2\2\u0294\u0295\7l\2\2\u0295\u0296\7o\2"+
		"\2\u0296\u0297\7h\2\2\u0297\u0298\7_\2\2\u0298\u02cd\7\67\2\2\u0299\u029a"+
		"\7\66\2\2\u029a\u029b\7\'\2\2\u029b\u029c\7j\2\2\u029c\u029d\7l\2\2\u029d"+
		"\u029e\7o\2\2\u029e\u029f\7h\2\2\u029f\u02a0\7_\2\2\u02a0\u02a1\7<\2\2"+
		"\u02a1\u02a2\7h\2\2\u02a2\u02a3\7[\2\2\u02a3\u02a4\7g\2\2\u02a4\u02a5"+
		"\7_\2\2\u02a5\u02cd\7\67\2\2\u02a6\u02a7\7\66\2\2\u02a7\u02a8\7\'\2\2"+
		"\u02a8\u02a9\7m\2\2\u02a9\u02aa\7e\2\2\u02aa\u02ab\7c\2\2\u02ab\u02ac"+
		"\7j\2\2\u02ac\u02cd\7\67\2\2\u02ad\u02ae\7\66\2\2\u02ae\u02af\7\'\2\2"+
		"\u02af\u02b0\7m\2\2\u02b0\u02b1\7e\2\2\u02b1\u02b2\7c\2\2\u02b2\u02b3"+
		"\7j\2\2\u02b3\u02b4\7<\2\2\u02b4\u02b5\7h\2\2\u02b5\u02b6\7[\2\2\u02b6"+
		"\u02b7\7g\2\2\u02b7\u02b8\7_\2\2\u02b8\u02cd\7\67\2\2\u02b9\u02ba\7\66"+
		"\2\2\u02ba\u02bb\7\'\2\2\u02bb\u02bc\7n\2\2\u02bc\u02bd\7b\2\2\u02bd\u02be"+
		"\7_\2\2\u02be\u02bf\7h\2\2\u02bf\u02cd\7\67\2\2\u02c0\u02c1\7\66\2\2\u02c1"+
		"\u02c2\7\'\2\2\u02c2\u02c3\7n\2\2\u02c3\u02c4\7b\2\2\u02c4\u02c5\7_\2"+
		"\2\u02c5\u02c6\7h\2\2\u02c6\u02c7\7<\2\2\u02c7\u02c8\7h\2\2\u02c8\u02c9"+
		"\7[\2\2\u02c9\u02ca\7g\2\2\u02ca\u02cb\7_\2\2\u02cb\u02cd\7\67\2\2\u02cc"+
		"\u0268\3\2\2\2\u02cc\u0271\3\2\2\2\u02cc\u027a\3\2\2\2\u02cc\u0288\3\2"+
		"\2\2\u02cc\u0291\3\2\2\2\u02cc\u0299\3\2\2\2\u02cc\u02a6\3\2\2\2\u02cc"+
		"\u02ad\3\2\2\2\u02cc\u02b9\3\2\2\2\u02cc\u02c0\3\2\2\2\u02cd\'\3\2\2\2"+
		"\u02ce\u02cf\7\66\2\2\u02cf\u02d0\7\'\2\2\u02d0\u02d1\7]\2\2\u02d1\u02d2"+
		"\7l\2\2\u02d2\u030c\7\67\2\2\u02d3\u02d4\7\66\2\2\u02d4\u02d5\7\'\2\2"+
		"\u02d5\u02d6\7f\2\2\u02d6\u02d7\7`\2\2\u02d7\u030c\7\67\2\2\u02d8\u02d9"+
		"\7\66\2\2\u02d9\u02da\7\'\2\2\u02da\u02db\7]\2\2\u02db\u02dc\7l\2\2\u02dc"+
		"\u02dd\7f\2\2\u02dd\u02de\7`\2\2\u02de\u030c\7\67\2\2\u02df\u02e0\7\66"+
		"\2\2\u02e0\u02e1\7\'\2\2\u02e1\u02e2\7[\2\2\u02e2\u02e3\7h\2\2\u02e3\u02e4"+
		"\7s\2\2\u02e4\u02e5\7]\2\2\u02e5\u02e6\7l\2\2\u02e6\u02e7\7f\2\2\u02e7"+
		"\u02e8\7`\2\2\u02e8\u030c\7\67\2\2\u02e9\u02ea\7\66\2\2\u02ea\u02eb\7"+
		"\'\2\2\u02eb\u02ec\7[\2\2\u02ec\u02ed\7h\2\2\u02ed\u02ee\7s\2\2\u02ee"+
		"\u030c\7\67\2\2\u02ef\u02f0\7\66\2\2\u02f0\u02f1\7\'\2\2\u02f1\u02f2\7"+
		"\\\2\2\u02f2\u02f3\7m\2\2\u02f3\u02f4\7l\2\2\u02f4\u02f5\7;\2\2\u02f5"+
		"\u02f6\7[\2\2\u02f6\u02f7\7h\2\2\u02f7\u02f8\7s\2\2\u02f8\u02f9\7]\2\2"+
		"\u02f9\u02fa\7l\2\2\u02fa\u02fb\7f\2\2\u02fb\u02fc\7`\2\2\u02fc\u030c"+
		"\7\67\2\2\u02fd\u02fe\7\66\2\2\u02fe\u02ff\7\'\2\2\u02ff\u0300\7\\\2\2"+
		"\u0300\u0301\7m\2\2\u0301\u0302\7l\2\2\u0302\u0303\7;\2\2\u0303\u0304"+
		"\7o\2\2\u0304\u0305\7h\2\2\u0305\u0306\7c\2\2\u0306\u0307\7]\2\2\u0307"+
		"\u0308\7i\2\2\u0308\u0309\7^\2\2\u0309\u030a\7_\2\2\u030a\u030c\7\67\2"+
		"\2\u030b\u02ce\3\2\2\2\u030b\u02d3\3\2\2\2\u030b\u02d8\3\2\2\2\u030b\u02df"+
		"\3\2\2\2\u030b\u02e9\3\2\2\2\u030b\u02ef\3\2\2\2\u030b\u02fd\3\2\2\2\u030c"+
		")\3\2\2\2\u030d\u030e\7\66\2\2\u030e\u030f\7%\2\2\u030f\u0310\7]\2\2\u0310"+
		"\u0318\7\67\2\2\u0311\u0312\7\66\2\2\u0312\u0313\7%\2\2\u0313\u0314\7"+
		"]\2\2\u0314\u0315\58\35\2\u0315\u0316\7\67\2\2\u0316\u0318\3\2\2\2\u0317"+
		"\u030d\3\2\2\2\u0317\u0311\3\2\2\2\u0318+\3\2\2\2\u0319\u0334\5\"\22\2"+
		"\u031a\u0334\5\60\31\2\u031b\u0334\5\62\32\2\u031c\u0334\5\16\b\2\u031d"+
		"\u0334\5\24\13\2\u031e\u0334\5\26\f\2\u031f\u0334\5\30\r\2\u0320\u0334"+
		"\5\32\16\2\u0321\u0334\5 \21\2\u0322\u0334\5\20\t\2\u0323\u0334\5$\23"+
		"\2\u0324\u0334\5&\24\2\u0325\u0334\5(\25\2\u0326\u0334\5*\26\2\u0327\u0334"+
		"\7\16\2\2\u0328\u0334\7!\2\2\u0329\u0334\7-\2\2\u032a\u0334\7+\2\2\u032b"+
		"\u0334\7,\2\2\u032c\u0334\7.\2\2\u032d\u0334\7/\2\2\u032e\u0334\7\60\2"+
		"\2\u032f\u0334\7\61\2\2\u0330\u0334\7\62\2\2\u0331\u0334\7\17\2\2\u0332"+
		"\u0334\7\36\2\2\u0333\u0319\3\2\2\2\u0333\u031a\3\2\2\2\u0333\u031b\3"+
		"\2\2\2\u0333\u031c\3\2\2\2\u0333\u031d\3\2\2\2\u0333\u031e\3\2\2\2\u0333"+
		"\u031f\3\2\2\2\u0333\u0320\3\2\2\2\u0333\u0321\3\2\2\2\u0333\u0322\3\2"+
		"\2\2\u0333\u0323\3\2\2\2\u0333\u0324\3\2\2\2\u0333\u0325\3\2\2\2\u0333"+
		"\u0326\3\2\2\2\u0333\u0327\3\2\2\2\u0333\u0328\3\2\2\2\u0333\u0329\3\2"+
		"\2\2\u0333\u032a\3\2\2\2\u0333\u032b\3\2\2\2\u0333\u032c\3\2\2\2\u0333"+
		"\u032d\3\2\2\2\u0333\u032e\3\2\2\2\u0333\u032f\3\2\2\2\u0333\u0330\3\2"+
		"\2\2\u0333\u0331\3\2\2\2\u0333\u0332\3\2\2\2\u0334-\3\2\2\2\u0335\u0336"+
		"\5\64\33\2\u0336\u0337\7\"\2\2\u0337\u0338\5\64\33\2\u0338\u033d\3\2\2"+
		"\2\u0339\u033d\5\60\31\2\u033a\u033d\5\64\33\2\u033b\u033d\5\22\n\2\u033c"+
		"\u0335\3\2\2\2\u033c\u0339\3\2\2\2\u033c\u033a\3\2\2\2\u033c\u033b\3\2"+
		"\2\2\u033d/\3\2\2\2\u033e\u033f\t\3\2\2\u033f\61\3\2\2\2\u0340\u0343\5"+
		"\66\34\2\u0341\u0343\7 \2\2\u0342\u0340\3\2\2\2\u0342\u0341\3\2\2\2\u0343"+
		"\63\3\2\2\2\u0344\u0351\5\66\34\2\u0345\u0351\7\16\2\2\u0346\u0351\7\37"+
		"\2\2\u0347\u0351\7!\2\2\u0348\u0351\7%\2\2\u0349\u0351\7&\2\2\u034a\u0351"+
		"\7\'\2\2\u034b\u0351\7+\2\2\u034c\u0351\7.\2\2\u034d\u0351\7\65\2\2\u034e"+
		"\u0351\7\66\2\2\u034f\u0351\7\67\2\2\u0350\u0344\3\2\2\2\u0350\u0345\3"+
		"\2\2\2\u0350\u0346\3\2\2\2\u0350\u0347\3\2\2\2\u0350\u0348\3\2\2\2\u0350"+
		"\u0349\3\2\2\2\u0350\u034a\3\2\2\2\u0350\u034b\3\2\2\2\u0350\u034c\3\2"+
		"\2\2\u0350\u034d\3\2\2\2\u0350\u034e\3\2\2\2\u0350\u034f\3\2\2\2\u0351"+
		"\65\3\2\2\2\u0352\u036d\5:\36\2\u0353\u036d\5J&\2\u0354\u036d\5@!\2\u0355"+
		"\u036d\7\5\2\2\u0356\u036d\7\7\2\2\u0357\u036d\7\b\2\2\u0358\u036d\7\t"+
		"\2\2\u0359\u036d\7\n\2\2\u035a\u036d\7\13\2\2\u035b\u036d\7\r\2\2\u035c"+
		"\u036d\7\3\2\2\u035d\u036d\7\4\2\2\u035e\u036d\7(\2\2\u035f\u036d\7)\2"+
		"\2\u0360\u036d\7*\2\2\u0361\u036d\7\"\2\2\u0362\u036d\78\2\2\u0363\u036d"+
		"\79\2\2\u0364\u036d\7:\2\2\u0365\u036d\7;\2\2\u0366\u036d\7<\2\2\u0367"+
		"\u036d\7=\2\2\u0368\u036d\7>\2\2\u0369\u036d\7?\2\2\u036a\u036d\7@\2\2"+
		"\u036b\u036d\7\177\2\2\u036c\u0352\3\2\2\2\u036c\u0353\3\2\2\2\u036c\u0354"+
		"\3\2\2\2\u036c\u0355\3\2\2\2\u036c\u0356\3\2\2\2\u036c\u0357\3\2\2\2\u036c"+
		"\u0358\3\2\2\2\u036c\u0359\3\2\2\2\u036c\u035a\3\2\2\2\u036c\u035b\3\2"+
		"\2\2\u036c\u035c\3\2\2\2\u036c\u035d\3\2\2\2\u036c\u035e\3\2\2\2\u036c"+
		"\u035f\3\2\2\2\u036c\u0360\3\2\2\2\u036c\u0361\3\2\2\2\u036c\u0362\3\2"+
		"\2\2\u036c\u0363\3\2\2\2\u036c\u0364\3\2\2\2\u036c\u0365\3\2\2\2\u036c"+
		"\u0366\3\2\2\2\u036c\u0367\3\2\2\2\u036c\u0368\3\2\2\2\u036c\u0369\3\2"+
		"\2\2\u036c\u036a\3\2\2\2\u036c\u036b\3\2\2\2\u036d\67\3\2\2\2\u036e\u036f"+
		"\5> \2\u036f9\3\2\2\2\u0370\u0371\7\f\2\2\u0371\u0372\t\4\2\2\u0372\u0373"+
		"\5<\37\2\u0373\u0374\5<\37\2\u0374\u037a\3\2\2\2\u0375\u0376\7\f\2\2\u0376"+
		"\u0377\5<\37\2\u0377\u0378\5<\37\2\u0378\u037a\3\2\2\2\u0379\u0370\3\2"+
		"\2\2\u0379\u0375\3\2\2\2\u037a;\3\2\2\2\u037b\u037c\t\5\2\2\u037c=\3\2"+
		"\2\2\u037d\u037f\5@!\2\u037e\u037d\3\2\2\2\u037f\u0380\3\2\2\2\u0380\u037e"+
		"\3\2\2\2\u0380\u0381\3\2\2\2\u0381?\3\2\2\2\u0382\u0383\t\6\2\2\u0383"+
		"A\3\2\2\2\u0384\u0385\5D#\2\u0385C\3\2\2\2\u0386\u0389\5J&\2\u0387\u0389"+
		"\7;\2\2\u0388\u0386\3\2\2\2\u0388\u0387\3\2\2\2\u0389\u038f\3\2\2\2\u038a"+
		"\u038e\5J&\2\u038b\u038e\7;\2\2\u038c\u038e\5@!\2\u038d\u038a\3\2\2\2"+
		"\u038d\u038b\3\2\2\2\u038d\u038c\3\2\2\2\u038e\u0391\3\2\2\2\u038f\u038d"+
		"\3\2\2\2\u038f\u0390\3\2\2\2\u0390E\3\2\2\2\u0391\u038f\3\2\2\2\u0392"+
		"\u0394\5H%\2\u0393\u0392\3\2\2\2\u0394\u0395\3\2\2\2\u0395\u0393\3\2\2"+
		"\2\u0395\u0396\3\2\2\2\u0396G\3\2\2\2\u0397\u0398\n\7\2\2\u0398I\3\2\2"+
		"\2\u0399\u039a\t\b\2\2\u039aK\3\2\2\2\62TZ_y~\u0087\u0091\u009a\u00a4"+
		"\u00ad\u00b5\u00b9\u00e4\u00e9\u0108\u011c\u015a\u015f\u017d\u01cd\u01d7"+
		"\u01e4\u01f1\u01ff\u020d\u021a\u0226\u0234\u0245\u0256\u0262\u0266\u0277"+
		"\u0280\u02cc\u030b\u0317\u0333\u033c\u0342\u0350\u036c\u0379\u0380\u0388"+
		"\u038d\u038f\u0395";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}