// Generated from C:/Users/liyt/Desktop/RHunter/src/main/java/cn/ac/ios/PythonRegex\JavaScriptRegex.g4 by ANTLR 4.9.1
package cn.ac.ios.PythonRegex;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PythonRegexParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PythonRegexVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(PythonRegexParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#alternation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlternation(PythonRegexParser.AlternationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(PythonRegexParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(PythonRegexParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#quantifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier(PythonRegexParser.QuantifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#quantifier_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier_type(PythonRegexParser.Quantifier_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#character_class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharacter_class(PythonRegexParser.Character_classContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#backreference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackreference(PythonRegexParser.BackreferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#backreference_or_octal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBackreference_or_octal(PythonRegexParser.Backreference_or_octalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#capture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCapture(PythonRegexParser.CaptureContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#non_capture}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_capture(PythonRegexParser.Non_captureContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(PythonRegexParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(PythonRegexParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#option_flags}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_flags(PythonRegexParser.Option_flagsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#option_flag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption_flag(PythonRegexParser.Option_flagContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#look_around}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLook_around(PythonRegexParser.Look_aroundContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#subroutine_reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubroutine_reference(PythonRegexParser.Subroutine_referenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#conditional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditional(PythonRegexParser.ConditionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#backtrack_control}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBacktrack_control(PythonRegexParser.Backtrack_controlContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#newline_convention}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewline_convention(PythonRegexParser.Newline_conventionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#callout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallout(PythonRegexParser.CalloutContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(PythonRegexParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#cc_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCc_atom(PythonRegexParser.Cc_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#shared_atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShared_atom(PythonRegexParser.Shared_atomContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(PythonRegexParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#cc_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCc_literal(PythonRegexParser.Cc_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#shared_literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShared_literal(PythonRegexParser.Shared_literalContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(PythonRegexParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#octal_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctal_char(PythonRegexParser.Octal_charContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#octal_digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctal_digit(PythonRegexParser.Octal_digitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#digits}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigits(PythonRegexParser.DigitsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit(PythonRegexParser.DigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(PythonRegexParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#alpha_nums}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlpha_nums(PythonRegexParser.Alpha_numsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#non_close_parens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_close_parens(PythonRegexParser.Non_close_parensContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#non_close_paren}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNon_close_paren(PythonRegexParser.Non_close_parenContext ctx);
	/**
	 * Visit a parse tree produced by {@link PythonRegexParser#letter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetter(PythonRegexParser.LetterContext ctx);
}