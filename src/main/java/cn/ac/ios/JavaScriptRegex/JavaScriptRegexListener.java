// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/JavaScriptRegex\JavaScriptRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.JavaScriptRegex;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavaScriptRegexParser}.
 */
public interface JavaScriptRegexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(JavaScriptRegexParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(JavaScriptRegexParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#alternation}.
	 * @param ctx the parse tree
	 */
	void enterAlternation(JavaScriptRegexParser.AlternationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#alternation}.
	 * @param ctx the parse tree
	 */
	void exitAlternation(JavaScriptRegexParser.AlternationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(JavaScriptRegexParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(JavaScriptRegexParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(JavaScriptRegexParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(JavaScriptRegexParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier(JavaScriptRegexParser.QuantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier(JavaScriptRegexParser.QuantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#quantifier_type}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier_type(JavaScriptRegexParser.Quantifier_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#quantifier_type}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier_type(JavaScriptRegexParser.Quantifier_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#character_class}.
	 * @param ctx the parse tree
	 */
	void enterCharacter_class(JavaScriptRegexParser.Character_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#character_class}.
	 * @param ctx the parse tree
	 */
	void exitCharacter_class(JavaScriptRegexParser.Character_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#backreference}.
	 * @param ctx the parse tree
	 */
	void enterBackreference(JavaScriptRegexParser.BackreferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#backreference}.
	 * @param ctx the parse tree
	 */
	void exitBackreference(JavaScriptRegexParser.BackreferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 */
	void enterBackreference_or_octal(JavaScriptRegexParser.Backreference_or_octalContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 */
	void exitBackreference_or_octal(JavaScriptRegexParser.Backreference_or_octalContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#capture}.
	 * @param ctx the parse tree
	 */
	void enterCapture(JavaScriptRegexParser.CaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#capture}.
	 * @param ctx the parse tree
	 */
	void exitCapture(JavaScriptRegexParser.CaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#non_capture}.
	 * @param ctx the parse tree
	 */
	void enterNon_capture(JavaScriptRegexParser.Non_captureContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#non_capture}.
	 * @param ctx the parse tree
	 */
	void exitNon_capture(JavaScriptRegexParser.Non_captureContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(JavaScriptRegexParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(JavaScriptRegexParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(JavaScriptRegexParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(JavaScriptRegexParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#option_flags}.
	 * @param ctx the parse tree
	 */
	void enterOption_flags(JavaScriptRegexParser.Option_flagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#option_flags}.
	 * @param ctx the parse tree
	 */
	void exitOption_flags(JavaScriptRegexParser.Option_flagsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#option_flag}.
	 * @param ctx the parse tree
	 */
	void enterOption_flag(JavaScriptRegexParser.Option_flagContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#option_flag}.
	 * @param ctx the parse tree
	 */
	void exitOption_flag(JavaScriptRegexParser.Option_flagContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#look_around}.
	 * @param ctx the parse tree
	 */
	void enterLook_around(JavaScriptRegexParser.Look_aroundContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#look_around}.
	 * @param ctx the parse tree
	 */
	void exitLook_around(JavaScriptRegexParser.Look_aroundContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#subroutine_reference}.
	 * @param ctx the parse tree
	 */
	void enterSubroutine_reference(JavaScriptRegexParser.Subroutine_referenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#subroutine_reference}.
	 * @param ctx the parse tree
	 */
	void exitSubroutine_reference(JavaScriptRegexParser.Subroutine_referenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(JavaScriptRegexParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(JavaScriptRegexParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#backtrack_control}.
	 * @param ctx the parse tree
	 */
	void enterBacktrack_control(JavaScriptRegexParser.Backtrack_controlContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#backtrack_control}.
	 * @param ctx the parse tree
	 */
	void exitBacktrack_control(JavaScriptRegexParser.Backtrack_controlContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#newline_convention}.
	 * @param ctx the parse tree
	 */
	void enterNewline_convention(JavaScriptRegexParser.Newline_conventionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#newline_convention}.
	 * @param ctx the parse tree
	 */
	void exitNewline_convention(JavaScriptRegexParser.Newline_conventionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#callout}.
	 * @param ctx the parse tree
	 */
	void enterCallout(JavaScriptRegexParser.CalloutContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#callout}.
	 * @param ctx the parse tree
	 */
	void exitCallout(JavaScriptRegexParser.CalloutContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(JavaScriptRegexParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(JavaScriptRegexParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#cc_atom}.
	 * @param ctx the parse tree
	 */
	void enterCc_atom(JavaScriptRegexParser.Cc_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#cc_atom}.
	 * @param ctx the parse tree
	 */
	void exitCc_atom(JavaScriptRegexParser.Cc_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#shared_atom}.
	 * @param ctx the parse tree
	 */
	void enterShared_atom(JavaScriptRegexParser.Shared_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#shared_atom}.
	 * @param ctx the parse tree
	 */
	void exitShared_atom(JavaScriptRegexParser.Shared_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(JavaScriptRegexParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(JavaScriptRegexParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#cc_literal}.
	 * @param ctx the parse tree
	 */
	void enterCc_literal(JavaScriptRegexParser.Cc_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#cc_literal}.
	 * @param ctx the parse tree
	 */
	void exitCc_literal(JavaScriptRegexParser.Cc_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#shared_literal}.
	 * @param ctx the parse tree
	 */
	void enterShared_literal(JavaScriptRegexParser.Shared_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#shared_literal}.
	 * @param ctx the parse tree
	 */
	void exitShared_literal(JavaScriptRegexParser.Shared_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(JavaScriptRegexParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(JavaScriptRegexParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#octal_char}.
	 * @param ctx the parse tree
	 */
	void enterOctal_char(JavaScriptRegexParser.Octal_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#octal_char}.
	 * @param ctx the parse tree
	 */
	void exitOctal_char(JavaScriptRegexParser.Octal_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#octal_digit}.
	 * @param ctx the parse tree
	 */
	void enterOctal_digit(JavaScriptRegexParser.Octal_digitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#octal_digit}.
	 * @param ctx the parse tree
	 */
	void exitOctal_digit(JavaScriptRegexParser.Octal_digitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#digits}.
	 * @param ctx the parse tree
	 */
	void enterDigits(JavaScriptRegexParser.DigitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#digits}.
	 * @param ctx the parse tree
	 */
	void exitDigits(JavaScriptRegexParser.DigitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(JavaScriptRegexParser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(JavaScriptRegexParser.DigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(JavaScriptRegexParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(JavaScriptRegexParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#alpha_nums}.
	 * @param ctx the parse tree
	 */
	void enterAlpha_nums(JavaScriptRegexParser.Alpha_numsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#alpha_nums}.
	 * @param ctx the parse tree
	 */
	void exitAlpha_nums(JavaScriptRegexParser.Alpha_numsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#non_close_parens}.
	 * @param ctx the parse tree
	 */
	void enterNon_close_parens(JavaScriptRegexParser.Non_close_parensContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#non_close_parens}.
	 * @param ctx the parse tree
	 */
	void exitNon_close_parens(JavaScriptRegexParser.Non_close_parensContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#non_close_paren}.
	 * @param ctx the parse tree
	 */
	void enterNon_close_paren(JavaScriptRegexParser.Non_close_parenContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#non_close_paren}.
	 * @param ctx the parse tree
	 */
	void exitNon_close_paren(JavaScriptRegexParser.Non_close_parenContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaScriptRegexParser#letter}.
	 * @param ctx the parse tree
	 */
	void enterLetter(JavaScriptRegexParser.LetterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaScriptRegexParser#letter}.
	 * @param ctx the parse tree
	 */
	void exitLetter(JavaScriptRegexParser.LetterContext ctx);
}