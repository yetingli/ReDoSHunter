// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/PCRE\JavaScriptRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.PCRERegex;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PCREParser}.
 */
public interface PCREListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PCREParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(PCREParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(PCREParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#alternation}.
	 * @param ctx the parse tree
	 */
	void enterAlternation(PCREParser.AlternationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#alternation}.
	 * @param ctx the parse tree
	 */
	void exitAlternation(PCREParser.AlternationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(PCREParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(PCREParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(PCREParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(PCREParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier(PCREParser.QuantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier(PCREParser.QuantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#quantifier_type}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier_type(PCREParser.Quantifier_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#quantifier_type}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier_type(PCREParser.Quantifier_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#character_class}.
	 * @param ctx the parse tree
	 */
	void enterCharacter_class(PCREParser.Character_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#character_class}.
	 * @param ctx the parse tree
	 */
	void exitCharacter_class(PCREParser.Character_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#backreference}.
	 * @param ctx the parse tree
	 */
	void enterBackreference(PCREParser.BackreferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#backreference}.
	 * @param ctx the parse tree
	 */
	void exitBackreference(PCREParser.BackreferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 */
	void enterBackreference_or_octal(PCREParser.Backreference_or_octalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 */
	void exitBackreference_or_octal(PCREParser.Backreference_or_octalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#capture}.
	 * @param ctx the parse tree
	 */
	void enterCapture(PCREParser.CaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#capture}.
	 * @param ctx the parse tree
	 */
	void exitCapture(PCREParser.CaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#non_capture}.
	 * @param ctx the parse tree
	 */
	void enterNon_capture(PCREParser.Non_captureContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#non_capture}.
	 * @param ctx the parse tree
	 */
	void exitNon_capture(PCREParser.Non_captureContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(PCREParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(PCREParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(PCREParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(PCREParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#option_flags}.
	 * @param ctx the parse tree
	 */
	void enterOption_flags(PCREParser.Option_flagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#option_flags}.
	 * @param ctx the parse tree
	 */
	void exitOption_flags(PCREParser.Option_flagsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#option_flag}.
	 * @param ctx the parse tree
	 */
	void enterOption_flag(PCREParser.Option_flagContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#option_flag}.
	 * @param ctx the parse tree
	 */
	void exitOption_flag(PCREParser.Option_flagContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#look_around}.
	 * @param ctx the parse tree
	 */
	void enterLook_around(PCREParser.Look_aroundContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#look_around}.
	 * @param ctx the parse tree
	 */
	void exitLook_around(PCREParser.Look_aroundContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#subroutine_reference}.
	 * @param ctx the parse tree
	 */
	void enterSubroutine_reference(PCREParser.Subroutine_referenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#subroutine_reference}.
	 * @param ctx the parse tree
	 */
	void exitSubroutine_reference(PCREParser.Subroutine_referenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(PCREParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(PCREParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#backtrack_control}.
	 * @param ctx the parse tree
	 */
	void enterBacktrack_control(PCREParser.Backtrack_controlContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#backtrack_control}.
	 * @param ctx the parse tree
	 */
	void exitBacktrack_control(PCREParser.Backtrack_controlContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#newline_convention}.
	 * @param ctx the parse tree
	 */
	void enterNewline_convention(PCREParser.Newline_conventionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#newline_convention}.
	 * @param ctx the parse tree
	 */
	void exitNewline_convention(PCREParser.Newline_conventionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#callout}.
	 * @param ctx the parse tree
	 */
	void enterCallout(PCREParser.CalloutContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#callout}.
	 * @param ctx the parse tree
	 */
	void exitCallout(PCREParser.CalloutContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(PCREParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(PCREParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#cc_atom}.
	 * @param ctx the parse tree
	 */
	void enterCc_atom(PCREParser.Cc_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#cc_atom}.
	 * @param ctx the parse tree
	 */
	void exitCc_atom(PCREParser.Cc_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#shared_atom}.
	 * @param ctx the parse tree
	 */
	void enterShared_atom(PCREParser.Shared_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#shared_atom}.
	 * @param ctx the parse tree
	 */
	void exitShared_atom(PCREParser.Shared_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(PCREParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(PCREParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#cc_literal}.
	 * @param ctx the parse tree
	 */
	void enterCc_literal(PCREParser.Cc_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#cc_literal}.
	 * @param ctx the parse tree
	 */
	void exitCc_literal(PCREParser.Cc_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#shared_literal}.
	 * @param ctx the parse tree
	 */
	void enterShared_literal(PCREParser.Shared_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#shared_literal}.
	 * @param ctx the parse tree
	 */
	void exitShared_literal(PCREParser.Shared_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(PCREParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(PCREParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#octal_char}.
	 * @param ctx the parse tree
	 */
	void enterOctal_char(PCREParser.Octal_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#octal_char}.
	 * @param ctx the parse tree
	 */
	void exitOctal_char(PCREParser.Octal_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#octal_digit}.
	 * @param ctx the parse tree
	 */
	void enterOctal_digit(PCREParser.Octal_digitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#octal_digit}.
	 * @param ctx the parse tree
	 */
	void exitOctal_digit(PCREParser.Octal_digitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#digits}.
	 * @param ctx the parse tree
	 */
	void enterDigits(PCREParser.DigitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#digits}.
	 * @param ctx the parse tree
	 */
	void exitDigits(PCREParser.DigitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(PCREParser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(PCREParser.DigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(PCREParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(PCREParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#alpha_nums}.
	 * @param ctx the parse tree
	 */
	void enterAlpha_nums(PCREParser.Alpha_numsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#alpha_nums}.
	 * @param ctx the parse tree
	 */
	void exitAlpha_nums(PCREParser.Alpha_numsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#non_close_parens}.
	 * @param ctx the parse tree
	 */
	void enterNon_close_parens(PCREParser.Non_close_parensContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#non_close_parens}.
	 * @param ctx the parse tree
	 */
	void exitNon_close_parens(PCREParser.Non_close_parensContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#non_close_paren}.
	 * @param ctx the parse tree
	 */
	void enterNon_close_paren(PCREParser.Non_close_parenContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#non_close_paren}.
	 * @param ctx the parse tree
	 */
	void exitNon_close_paren(PCREParser.Non_close_parenContext ctx);
	/**
	 * Enter a parse tree produced by {@link PCREParser#letter}.
	 * @param ctx the parse tree
	 */
	void enterLetter(PCREParser.LetterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PCREParser#letter}.
	 * @param ctx the parse tree
	 */
	void exitLetter(PCREParser.LetterContext ctx);
}