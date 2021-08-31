// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/JavaRegex\JavaRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.JavaRegex;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavaRegexParser}.
 */
public interface JavaRegexListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(JavaRegexParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(JavaRegexParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#alternation}.
	 * @param ctx the parse tree
	 */
	void enterAlternation(JavaRegexParser.AlternationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#alternation}.
	 * @param ctx the parse tree
	 */
	void exitAlternation(JavaRegexParser.AlternationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(JavaRegexParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(JavaRegexParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(JavaRegexParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(JavaRegexParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier(JavaRegexParser.QuantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier(JavaRegexParser.QuantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#quantifier_type}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier_type(JavaRegexParser.Quantifier_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#quantifier_type}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier_type(JavaRegexParser.Quantifier_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#character_class}.
	 * @param ctx the parse tree
	 */
	void enterCharacter_class(JavaRegexParser.Character_classContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#character_class}.
	 * @param ctx the parse tree
	 */
	void exitCharacter_class(JavaRegexParser.Character_classContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#character_class_intersection}.
	 * @param ctx the parse tree
	 */
	void enterCharacter_class_intersection(JavaRegexParser.Character_class_intersectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#character_class_intersection}.
	 * @param ctx the parse tree
	 */
	void exitCharacter_class_intersection(JavaRegexParser.Character_class_intersectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#backreference}.
	 * @param ctx the parse tree
	 */
	void enterBackreference(JavaRegexParser.BackreferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#backreference}.
	 * @param ctx the parse tree
	 */
	void exitBackreference(JavaRegexParser.BackreferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 */
	void enterBackreference_or_octal(JavaRegexParser.Backreference_or_octalContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 */
	void exitBackreference_or_octal(JavaRegexParser.Backreference_or_octalContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#capture}.
	 * @param ctx the parse tree
	 */
	void enterCapture(JavaRegexParser.CaptureContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#capture}.
	 * @param ctx the parse tree
	 */
	void exitCapture(JavaRegexParser.CaptureContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#non_capture}.
	 * @param ctx the parse tree
	 */
	void enterNon_capture(JavaRegexParser.Non_captureContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#non_capture}.
	 * @param ctx the parse tree
	 */
	void exitNon_capture(JavaRegexParser.Non_captureContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(JavaRegexParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(JavaRegexParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(JavaRegexParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(JavaRegexParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#option_flags}.
	 * @param ctx the parse tree
	 */
	void enterOption_flags(JavaRegexParser.Option_flagsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#option_flags}.
	 * @param ctx the parse tree
	 */
	void exitOption_flags(JavaRegexParser.Option_flagsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#option_flag}.
	 * @param ctx the parse tree
	 */
	void enterOption_flag(JavaRegexParser.Option_flagContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#option_flag}.
	 * @param ctx the parse tree
	 */
	void exitOption_flag(JavaRegexParser.Option_flagContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#look_around}.
	 * @param ctx the parse tree
	 */
	void enterLook_around(JavaRegexParser.Look_aroundContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#look_around}.
	 * @param ctx the parse tree
	 */
	void exitLook_around(JavaRegexParser.Look_aroundContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#subroutine_reference}.
	 * @param ctx the parse tree
	 */
	void enterSubroutine_reference(JavaRegexParser.Subroutine_referenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#subroutine_reference}.
	 * @param ctx the parse tree
	 */
	void exitSubroutine_reference(JavaRegexParser.Subroutine_referenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#conditional}.
	 * @param ctx the parse tree
	 */
	void enterConditional(JavaRegexParser.ConditionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#conditional}.
	 * @param ctx the parse tree
	 */
	void exitConditional(JavaRegexParser.ConditionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#backtrack_control}.
	 * @param ctx the parse tree
	 */
	void enterBacktrack_control(JavaRegexParser.Backtrack_controlContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#backtrack_control}.
	 * @param ctx the parse tree
	 */
	void exitBacktrack_control(JavaRegexParser.Backtrack_controlContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#newline_convention}.
	 * @param ctx the parse tree
	 */
	void enterNewline_convention(JavaRegexParser.Newline_conventionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#newline_convention}.
	 * @param ctx the parse tree
	 */
	void exitNewline_convention(JavaRegexParser.Newline_conventionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#callout}.
	 * @param ctx the parse tree
	 */
	void enterCallout(JavaRegexParser.CalloutContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#callout}.
	 * @param ctx the parse tree
	 */
	void exitCallout(JavaRegexParser.CalloutContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(JavaRegexParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(JavaRegexParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#cc_atom}.
	 * @param ctx the parse tree
	 */
	void enterCc_atom(JavaRegexParser.Cc_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#cc_atom}.
	 * @param ctx the parse tree
	 */
	void exitCc_atom(JavaRegexParser.Cc_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#shared_atom}.
	 * @param ctx the parse tree
	 */
	void enterShared_atom(JavaRegexParser.Shared_atomContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#shared_atom}.
	 * @param ctx the parse tree
	 */
	void exitShared_atom(JavaRegexParser.Shared_atomContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(JavaRegexParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(JavaRegexParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#cc_literal}.
	 * @param ctx the parse tree
	 */
	void enterCc_literal(JavaRegexParser.Cc_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#cc_literal}.
	 * @param ctx the parse tree
	 */
	void exitCc_literal(JavaRegexParser.Cc_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#shared_literal}.
	 * @param ctx the parse tree
	 */
	void enterShared_literal(JavaRegexParser.Shared_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#shared_literal}.
	 * @param ctx the parse tree
	 */
	void exitShared_literal(JavaRegexParser.Shared_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(JavaRegexParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(JavaRegexParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#octal_char}.
	 * @param ctx the parse tree
	 */
	void enterOctal_char(JavaRegexParser.Octal_charContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#octal_char}.
	 * @param ctx the parse tree
	 */
	void exitOctal_char(JavaRegexParser.Octal_charContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#octal_digit}.
	 * @param ctx the parse tree
	 */
	void enterOctal_digit(JavaRegexParser.Octal_digitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#octal_digit}.
	 * @param ctx the parse tree
	 */
	void exitOctal_digit(JavaRegexParser.Octal_digitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#digits}.
	 * @param ctx the parse tree
	 */
	void enterDigits(JavaRegexParser.DigitsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#digits}.
	 * @param ctx the parse tree
	 */
	void exitDigits(JavaRegexParser.DigitsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(JavaRegexParser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(JavaRegexParser.DigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(JavaRegexParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(JavaRegexParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#alpha_nums}.
	 * @param ctx the parse tree
	 */
	void enterAlpha_nums(JavaRegexParser.Alpha_numsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#alpha_nums}.
	 * @param ctx the parse tree
	 */
	void exitAlpha_nums(JavaRegexParser.Alpha_numsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#non_close_parens}.
	 * @param ctx the parse tree
	 */
	void enterNon_close_parens(JavaRegexParser.Non_close_parensContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#non_close_parens}.
	 * @param ctx the parse tree
	 */
	void exitNon_close_parens(JavaRegexParser.Non_close_parensContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#non_close_paren}.
	 * @param ctx the parse tree
	 */
	void enterNon_close_paren(JavaRegexParser.Non_close_parenContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#non_close_paren}.
	 * @param ctx the parse tree
	 */
	void exitNon_close_paren(JavaRegexParser.Non_close_parenContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaRegexParser#letter}.
	 * @param ctx the parse tree
	 */
	void enterLetter(JavaRegexParser.LetterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaRegexParser#letter}.
	 * @param ctx the parse tree
	 */
	void exitLetter(JavaRegexParser.LetterContext ctx);
}