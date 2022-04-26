// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/PCRE\JavaScriptRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.PCRERegex;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PCREParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PCREVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PCREParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(PCREParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#alternation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlternation(PCREParser.AlternationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(PCREParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(PCREParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#quantifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier(PCREParser.QuantifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#quantifier_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier_type(PCREParser.Quantifier_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#character_class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter_class(PCREParser.Character_classContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#backreference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackreference(PCREParser.BackreferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackreference_or_octal(PCREParser.Backreference_or_octalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#capture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCapture(PCREParser.CaptureContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#non_capture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_capture(PCREParser.Non_captureContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(PCREParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(PCREParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#option_flags}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_flags(PCREParser.Option_flagsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#option_flag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_flag(PCREParser.Option_flagContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#look_around}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLook_around(PCREParser.Look_aroundContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#subroutine_reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubroutine_reference(PCREParser.Subroutine_referenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#conditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional(PCREParser.ConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#backtrack_control}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBacktrack_control(PCREParser.Backtrack_controlContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#newline_convention}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewline_convention(PCREParser.Newline_conventionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#callout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout(PCREParser.CalloutContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(PCREParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#cc_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCc_atom(PCREParser.Cc_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#shared_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShared_atom(PCREParser.Shared_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(PCREParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#cc_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCc_literal(PCREParser.Cc_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#shared_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShared_literal(PCREParser.Shared_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(PCREParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#octal_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctal_char(PCREParser.Octal_charContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#octal_digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctal_digit(PCREParser.Octal_digitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#digits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigits(PCREParser.DigitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit(PCREParser.DigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(PCREParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#alpha_nums}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlpha_nums(PCREParser.Alpha_numsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#non_close_parens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_close_parens(PCREParser.Non_close_parensContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#non_close_paren}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_close_paren(PCREParser.Non_close_parenContext ctx);
	/**
	 * Visit a parse tree produced by {@link PCREParser#letter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetter(PCREParser.LetterContext ctx);
}