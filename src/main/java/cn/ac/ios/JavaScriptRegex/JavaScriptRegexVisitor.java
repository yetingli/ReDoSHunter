// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/JavaScriptRegex\JavaScriptRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.JavaScriptRegex;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JavaScriptRegexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JavaScriptRegexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(JavaScriptRegexParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#alternation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlternation(JavaScriptRegexParser.AlternationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(JavaScriptRegexParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(JavaScriptRegexParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#quantifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier(JavaScriptRegexParser.QuantifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#quantifier_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier_type(JavaScriptRegexParser.Quantifier_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#character_class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter_class(JavaScriptRegexParser.Character_classContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#backreference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackreference(JavaScriptRegexParser.BackreferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackreference_or_octal(JavaScriptRegexParser.Backreference_or_octalContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#capture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCapture(JavaScriptRegexParser.CaptureContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#non_capture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_capture(JavaScriptRegexParser.Non_captureContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(JavaScriptRegexParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(JavaScriptRegexParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#option_flags}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_flags(JavaScriptRegexParser.Option_flagsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#option_flag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_flag(JavaScriptRegexParser.Option_flagContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#look_around}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLook_around(JavaScriptRegexParser.Look_aroundContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#subroutine_reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubroutine_reference(JavaScriptRegexParser.Subroutine_referenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#conditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional(JavaScriptRegexParser.ConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#backtrack_control}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBacktrack_control(JavaScriptRegexParser.Backtrack_controlContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#newline_convention}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewline_convention(JavaScriptRegexParser.Newline_conventionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#callout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout(JavaScriptRegexParser.CalloutContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(JavaScriptRegexParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#cc_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCc_atom(JavaScriptRegexParser.Cc_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#shared_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShared_atom(JavaScriptRegexParser.Shared_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(JavaScriptRegexParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#cc_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCc_literal(JavaScriptRegexParser.Cc_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#shared_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShared_literal(JavaScriptRegexParser.Shared_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(JavaScriptRegexParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#octal_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctal_char(JavaScriptRegexParser.Octal_charContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#octal_digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctal_digit(JavaScriptRegexParser.Octal_digitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#digits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigits(JavaScriptRegexParser.DigitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit(JavaScriptRegexParser.DigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(JavaScriptRegexParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#alpha_nums}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlpha_nums(JavaScriptRegexParser.Alpha_numsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#non_close_parens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_close_parens(JavaScriptRegexParser.Non_close_parensContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#non_close_paren}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_close_paren(JavaScriptRegexParser.Non_close_parenContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaScriptRegexParser#letter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetter(JavaScriptRegexParser.LetterContext ctx);
}