// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/PythonRegex\JavaScriptRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.PythonRegex;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PythonRegexParser}.
 */
public interface PythonRegexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(PythonRegexParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(PythonRegexParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#alternation}.
	 * @param ctx the parse tree
	 */
	void enterAlternation(PythonRegexParser.AlternationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#alternation}.
	 * @param ctx the parse tree
	 */
	void exitAlternation(PythonRegexParser.AlternationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(PythonRegexParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(PythonRegexParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(PythonRegexParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(PythonRegexParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier(PythonRegexParser.QuantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier(PythonRegexParser.QuantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#quantifier_type}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier_type(PythonRegexParser.Quantifier_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#quantifier_type}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier_type(PythonRegexParser.Quantifier_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#character_class}.
	 * @param ctx the parse tree
	 */
	void enterCharacter_class(PythonRegexParser.Character_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#character_class}.
	 * @param ctx the parse tree
	 */
	void exitCharacter_class(PythonRegexParser.Character_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#backreference}.
	 * @param ctx the parse tree
	 */
	void enterBackreference(PythonRegexParser.BackreferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#backreference}.
	 * @param ctx the parse tree
	 */
	void exitBackreference(PythonRegexParser.BackreferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 */
	void enterBackreference_or_octal(PythonRegexParser.Backreference_or_octalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 */
	void exitBackreference_or_octal(PythonRegexParser.Backreference_or_octalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#capture}.
	 * @param ctx the parse tree
	 */
	void enterCapture(PythonRegexParser.CaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#capture}.
	 * @param ctx the parse tree
	 */
	void exitCapture(PythonRegexParser.CaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#non_capture}.
	 * @param ctx the parse tree
	 */
	void enterNon_capture(PythonRegexParser.Non_captureContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#non_capture}.
	 * @param ctx the parse tree
	 */
	void exitNon_capture(PythonRegexParser.Non_captureContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(PythonRegexParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(PythonRegexParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(PythonRegexParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(PythonRegexParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#option_flags}.
	 * @param ctx the parse tree
	 */
	void enterOption_flags(PythonRegexParser.Option_flagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#option_flags}.
	 * @param ctx the parse tree
	 */
	void exitOption_flags(PythonRegexParser.Option_flagsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#option_flag}.
	 * @param ctx the parse tree
	 */
	void enterOption_flag(PythonRegexParser.Option_flagContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#option_flag}.
	 * @param ctx the parse tree
	 */
	void exitOption_flag(PythonRegexParser.Option_flagContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#look_around}.
	 * @param ctx the parse tree
	 */
	void enterLook_around(PythonRegexParser.Look_aroundContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#look_around}.
	 * @param ctx the parse tree
	 */
	void exitLook_around(PythonRegexParser.Look_aroundContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#subroutine_reference}.
	 * @param ctx the parse tree
	 */
	void enterSubroutine_reference(PythonRegexParser.Subroutine_referenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#subroutine_reference}.
	 * @param ctx the parse tree
	 */
	void exitSubroutine_reference(PythonRegexParser.Subroutine_referenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(PythonRegexParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(PythonRegexParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#backtrack_control}.
	 * @param ctx the parse tree
	 */
	void enterBacktrack_control(PythonRegexParser.Backtrack_controlContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#backtrack_control}.
	 * @param ctx the parse tree
	 */
	void exitBacktrack_control(PythonRegexParser.Backtrack_controlContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#newline_convention}.
	 * @param ctx the parse tree
	 */
	void enterNewline_convention(PythonRegexParser.Newline_conventionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#newline_convention}.
	 * @param ctx the parse tree
	 */
	void exitNewline_convention(PythonRegexParser.Newline_conventionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#callout}.
	 * @param ctx the parse tree
	 */
	void enterCallout(PythonRegexParser.CalloutContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#callout}.
	 * @param ctx the parse tree
	 */
	void exitCallout(PythonRegexParser.CalloutContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(PythonRegexParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(PythonRegexParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#cc_atom}.
	 * @param ctx the parse tree
	 */
	void enterCc_atom(PythonRegexParser.Cc_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#cc_atom}.
	 * @param ctx the parse tree
	 */
	void exitCc_atom(PythonRegexParser.Cc_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#shared_atom}.
	 * @param ctx the parse tree
	 */
	void enterShared_atom(PythonRegexParser.Shared_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#shared_atom}.
	 * @param ctx the parse tree
	 */
	void exitShared_atom(PythonRegexParser.Shared_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(PythonRegexParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(PythonRegexParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#cc_literal}.
	 * @param ctx the parse tree
	 */
	void enterCc_literal(PythonRegexParser.Cc_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#cc_literal}.
	 * @param ctx the parse tree
	 */
	void exitCc_literal(PythonRegexParser.Cc_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#shared_literal}.
	 * @param ctx the parse tree
	 */
	void enterShared_literal(PythonRegexParser.Shared_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#shared_literal}.
	 * @param ctx the parse tree
	 */
	void exitShared_literal(PythonRegexParser.Shared_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(PythonRegexParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(PythonRegexParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#octal_char}.
	 * @param ctx the parse tree
	 */
	void enterOctal_char(PythonRegexParser.Octal_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#octal_char}.
	 * @param ctx the parse tree
	 */
	void exitOctal_char(PythonRegexParser.Octal_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#octal_digit}.
	 * @param ctx the parse tree
	 */
	void enterOctal_digit(PythonRegexParser.Octal_digitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#octal_digit}.
	 * @param ctx the parse tree
	 */
	void exitOctal_digit(PythonRegexParser.Octal_digitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#digits}.
	 * @param ctx the parse tree
	 */
	void enterDigits(PythonRegexParser.DigitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#digits}.
	 * @param ctx the parse tree
	 */
	void exitDigits(PythonRegexParser.DigitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(PythonRegexParser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(PythonRegexParser.DigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(PythonRegexParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(PythonRegexParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#alpha_nums}.
	 * @param ctx the parse tree
	 */
	void enterAlpha_nums(PythonRegexParser.Alpha_numsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#alpha_nums}.
	 * @param ctx the parse tree
	 */
	void exitAlpha_nums(PythonRegexParser.Alpha_numsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#non_close_parens}.
	 * @param ctx the parse tree
	 */
	void enterNon_close_parens(PythonRegexParser.Non_close_parensContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#non_close_parens}.
	 * @param ctx the parse tree
	 */
	void exitNon_close_parens(PythonRegexParser.Non_close_parensContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#non_close_paren}.
	 * @param ctx the parse tree
	 */
	void enterNon_close_paren(PythonRegexParser.Non_close_parenContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#non_close_paren}.
	 * @param ctx the parse tree
	 */
	void exitNon_close_paren(PythonRegexParser.Non_close_parenContext ctx);
	/**
	 * Enter a parse tree produced by {@link PythonRegexParser#letter}.
	 * @param ctx the parse tree
	 */
	void enterLetter(PythonRegexParser.LetterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PythonRegexParser#letter}.
	 * @param ctx the parse tree
	 */
	void exitLetter(PythonRegexParser.LetterContext ctx);
}