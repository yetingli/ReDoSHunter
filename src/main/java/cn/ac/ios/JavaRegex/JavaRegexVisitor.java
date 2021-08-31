// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/JavaRegex\JavaRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.JavaRegex;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JavaRegexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JavaRegexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(JavaRegexParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#alternation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlternation(JavaRegexParser.AlternationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(JavaRegexParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(JavaRegexParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#quantifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier(JavaRegexParser.QuantifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#quantifier_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier_type(JavaRegexParser.Quantifier_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#character_class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter_class(JavaRegexParser.Character_classContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#character_class_intersection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter_class_intersection(JavaRegexParser.Character_class_intersectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#backreference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackreference(JavaRegexParser.BackreferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackreference_or_octal(JavaRegexParser.Backreference_or_octalContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#capture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCapture(JavaRegexParser.CaptureContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#non_capture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_capture(JavaRegexParser.Non_captureContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(JavaRegexParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(JavaRegexParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#option_flags}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_flags(JavaRegexParser.Option_flagsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#option_flag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_flag(JavaRegexParser.Option_flagContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#look_around}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLook_around(JavaRegexParser.Look_aroundContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#subroutine_reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubroutine_reference(JavaRegexParser.Subroutine_referenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#conditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional(JavaRegexParser.ConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#backtrack_control}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBacktrack_control(JavaRegexParser.Backtrack_controlContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#newline_convention}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewline_convention(JavaRegexParser.Newline_conventionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#callout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout(JavaRegexParser.CalloutContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(JavaRegexParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#cc_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCc_atom(JavaRegexParser.Cc_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#shared_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShared_atom(JavaRegexParser.Shared_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(JavaRegexParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#cc_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCc_literal(JavaRegexParser.Cc_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#shared_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShared_literal(JavaRegexParser.Shared_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(JavaRegexParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#octal_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctal_char(JavaRegexParser.Octal_charContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#octal_digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctal_digit(JavaRegexParser.Octal_digitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#digits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigits(JavaRegexParser.DigitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit(JavaRegexParser.DigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(JavaRegexParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#alpha_nums}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlpha_nums(JavaRegexParser.Alpha_numsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#non_close_parens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_close_parens(JavaRegexParser.Non_close_parensContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#non_close_paren}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_close_paren(JavaRegexParser.Non_close_parenContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaRegexParser#letter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetter(JavaRegexParser.LetterContext ctx);
}