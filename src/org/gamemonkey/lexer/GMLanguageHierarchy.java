/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gamemonkey.lexer;

/**
 *
 * @author geertjan
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
*
* @author eppleton
*/
public class GMLanguageHierarchy extends LanguageHierarchy<GMTokenId> {

   private static List<GMTokenId> tokens;
   private static Map<Integer, GMTokenId> idToToken;

   private static void init() {
       tokens = Arrays.<GMTokenId>asList(new GMTokenId[]{
                   new GMTokenId("EOF", "whitespace", 0),
                   new GMTokenId("WHITESPACE", "whitespace", 1),
                   new GMTokenId("SINGLE_LINE_COMMENT", "comment", 4),
                   new GMTokenId("FORMAL_COMMENT", "comment", 5),
                   new GMTokenId("MULTI_LINE_COMMENT", "comment", 6),
                   new GMTokenId("ABSTRACT", "keyword", 8),
                   new GMTokenId("ASSERT", "keyword", 9),
                   new GMTokenId("BOOLEAN", "keyword", 10),
                   new GMTokenId("BREAK", "keyword", 11),
                   new GMTokenId("BYTE", "keyword", 12),
                   new GMTokenId("CASE", "keyword", 13),
                   new GMTokenId("CATCH", "keyword", 14),
                   new GMTokenId("CHAR", "keyword", 15),
                   new GMTokenId("CLASS", "keyword", 16),
                   /** RegularExpression Id. */
                   new GMTokenId("CONST", "keyword", 17),
                   /** RegularExpression Id. */
                   new GMTokenId("CONTINUE", "keyword", 18),
                   /** RegularExpression Id. */
                   new GMTokenId("_DEFAULT", "keyword", 19),
                   /** RegularExpression Id. */
                   new GMTokenId("DO", "keyword", 20),
                   /** RegularExpression Id. */
                   new GMTokenId("DOUBLE", "keyword", 21),
                   /** RegularExpression Id. */
                   new GMTokenId("ELSE", "keyword", 22),
                   /** RegularExpression Id. */
                   new GMTokenId("ENUM", "keyword", 23),
                   /** RegularExpression Id. */
                   new GMTokenId("EXTENDS", "keyword", 24),
                   /** RegularExpression Id. */
                   new GMTokenId("FALSE", "keyword", 25),
                   /** RegularExpression Id. */
                   new GMTokenId("FINAL", "keyword", 26),
                   /** RegularExpression Id. */
                   new GMTokenId("FINALLY", "keyword", 27),
                   /** RegularExpression Id. */
                   new GMTokenId("FLOAT", "keyword", 28),
                   /** RegularExpression Id. */
                   new GMTokenId("FOR", "keyword", 29),
                   /** RegularExpression Id. */
                   new GMTokenId("GOTO", "keyword", 30),
                   /** RegularExpression Id. */
                   new GMTokenId("IF", "keyword", 31),
                   /** RegularExpression Id. */
                   new GMTokenId("IMPLEMENTS", "keyword", 32),
                   /** RegularExpression Id. */
                   new GMTokenId("IMPORT", "keyword", 33),
                   /** RegularExpression Id. */
                   new GMTokenId("INSTANCEOF", "keyword", 34),
                   /** RegularExpression Id. */
                   new GMTokenId("INT", "keyword", 35),
                   /** RegularExpression Id. */
                   new GMTokenId("INTERFACE", "keyword", 36),
                   /** RegularExpression Id. */
                   new GMTokenId("LONG", "keyword", 37),
                   /** RegularExpression Id. */
                   new GMTokenId("NATIVE", "keyword", 38),
                   /** RegularExpression Id. */
                   new GMTokenId("NEW", "keyword", 39),
                   /** RegularExpression Id. */
                   new GMTokenId("NULL", "keyword", 40),
                   /** RegularExpression Id. */
                   new GMTokenId("PACKAGE", "keyword", 41),
                   /** RegularExpression Id. */
                   new GMTokenId("PRIVATE", "keyword", 42),
                   /** RegularExpression Id. */
                   new GMTokenId("PROTECTED", "keyword", 43),
                   /** RegularExpression Id. */
                   new GMTokenId("PUBLIC", "keyword", 44),
                   /** RegularExpression Id. */
                   new GMTokenId("RETURN", "keyword", 45),
                   /** RegularExpression Id. */
                   new GMTokenId("SHORT", "keyword", 46),
                   /** RegularExpression Id. */
                   new GMTokenId("STATIC", "keyword", 47),
                   /** RegularExpression Id. */
                   new GMTokenId("STRICTFP", "keyword", 48),
                   /** RegularExpression Id. */
                   new GMTokenId("SUPER", "keyword", 49),
                   /** RegularExpression Id. */
                   new GMTokenId("SWITCH", "keyword", 50),
                   /** RegularExpression Id. */
                   new GMTokenId("SYNCHRONIZED", "keyword", 51),
                   /** RegularExpression Id. */
                   new GMTokenId("THIS", "keyword", 52),
                   /** RegularExpression Id. */
                   new GMTokenId("THROW", "keyword", 53),
                   /** RegularExpression Id. */
                   new GMTokenId("THROWS", "keyword", 54),
                   /** RegularExpression Id. */
                   new GMTokenId("TRANSIENT", "keyword", 55),
                   /** RegularExpression Id. */
                   new GMTokenId("TRUE", "keyword", 56),
                   /** RegularExpression Id. */
                   new GMTokenId("TRY", "keyword", 57),
                   /** RegularExpression Id. */
                   new GMTokenId("VOID", "keyword", 58),
                   /** RegularExpression Id. */
                   new GMTokenId("VOLATILE", "keyword", 59),
                   /** RegularExpression Id. */
                   new GMTokenId("WHILE", "keyword", 60),
                   /** RegularExpression Id. */
                   new GMTokenId("INTEGER_LITERAL", "literal", 61),
                   /** RegularExpression Id. */
                   new GMTokenId("DECIMAL_LITERAL", "literal", 62),
                   /** RegularExpression Id. */
                   new GMTokenId("HEX_LITERAL", "literal", 63),
                   /** RegularExpression Id. */
                   new GMTokenId("OCTAL_LITERAL", "literal", 64),
                   /** RegularExpression Id. */
                   new GMTokenId("FLOATING_POINT_LITERAL", "literal", 65),
                   /** RegularExpression Id. */
                   new GMTokenId("DECIMAL_FLOATING_POINT_LITERAL", "literal", 66),
                   /** RegularExpression Id. */
                   new GMTokenId("DECIMAL_EXPONENT", "number", 67),
                   /** RegularExpression Id. */
                   new GMTokenId("HEXADECIMAL_FLOATING_POINT_LITERAL", "literal", 68),
                   /** RegularExpression Id. */
                   new GMTokenId("HEXADECIMAL_EXPONENT", "number", 69),
                   /** RegularExpression Id. */
                   new GMTokenId("CHARACTER_LITERAL", "literal", 70),
                   /** RegularExpression Id. */
                   new GMTokenId("STRING_LITERAL", "literal", 71),
                   /** RegularExpression Id. */
                   new GMTokenId("IDENTIFIER", "identifier", 72),
                   /** RegularExpression Id. */
                   new GMTokenId("LETTER", "literal", 73),
                   /** RegularExpression Id. */
                   new GMTokenId("PART_LETTER", "literal", 74),
                   /** RegularExpression Id. */
                   new GMTokenId("LPAREN", "operator", 75),
                   /** RegularExpression Id. */
                   new GMTokenId("RPAREN", "operator", 76),
                   /** RegularExpression Id. */
                   new GMTokenId("LBRACE", "operator", 77),
                   /** RegularExpression Id. */
                   new GMTokenId("RBRACE", "operator", 78),
                   /** RegularExpression Id. */
                   new GMTokenId("LBRACKET", "operator", 79),
                   /** RegularExpression Id. */
                   new GMTokenId("RBRACKET", "operator", 80),
                   /** RegularExpression Id. */
                   new GMTokenId("SEMICOLON", "operator", 81),
                   /** RegularExpression Id. */
                   new GMTokenId("COMMA", "operator", 82),
                   /** RegularExpression Id. */
                   new GMTokenId("DOT", "operator", 83),
                   /** RegularExpression Id. */
                   new GMTokenId("AT", "operator", 84),
                   /** RegularExpression Id. */
                   new GMTokenId("ASSIGN", "operator", 85),
                   /** RegularExpression Id. */
                   new GMTokenId("LT", "operator", 86),
                   /** RegularExpression Id. */
                   new GMTokenId("BANG", "operator", 87),
                   /** RegularExpression Id. */
                   new GMTokenId("TILDE", "operator", 88),
                   /** RegularExpression Id. */
                   new GMTokenId("HOOK", "operator", 89),
                   /** RegularExpression Id. */
                   new GMTokenId("COLON", "operator", 90),
                   /** RegularExpression Id. */
                   new GMTokenId("EQ", "operator", 91),
                   /** RegularExpression operator. */
                   new GMTokenId("LE", "operator", 92),
                   /** RegularExpression Id. */
                   new GMTokenId("GE", "operator", 93),
                   /** RegularExpression Id. */
                   new GMTokenId("NE", "operator", 94),
                   /** RegularExpression Id. */
                   new GMTokenId("SC_OR", "operator", 95),
                   /** RegularExpression Id. */
                   new GMTokenId("SC_AND", "operator", 96),
                   /** RegularExpression Id. */
                   new GMTokenId("INCR", "operator", 97),
                   /** RegularExpression Id. */
                   new GMTokenId("DECR", "operator", 98),
                   /** RegularExpression Id. */
                   new GMTokenId("PLUS", "operator", 99),
                   /** RegularExpression Id. */
                   new GMTokenId("MINUS", "operator", 100),
                   /** RegularExpression Id. */
                   new GMTokenId("STAR", "operator", 101),
                   /** RegularExpression Id. */
                   new GMTokenId("SLASH", "operator", 102),
                   /** RegularExpression Id. */
                   new GMTokenId("BIT_AND", "operator", 103),
                   /** RegularExpression Id. */
                   new GMTokenId("BIT_OR", "operator", 104),
                   /** RegularExpression Id. */
                   new GMTokenId("XOR", "operator", 105),
                   /** RegularExpression Id. */
                   new GMTokenId("REM", "operator", 106),
                   /** RegularExpression Id. */
                   new GMTokenId("LSHIFT", "operator", 107),
                   /** RegularExpression Id. */
                   new GMTokenId("PLUSASSIGN", "operator", 108),
                   /** RegularExpression Id. */
                   new GMTokenId("MINUSASSIGN", "operator", 109),
                   /** RegularExpression Id. */
                   new GMTokenId("STARASSIGN", "operator", 110),
                   /** RegularExpression Id. */
                   new GMTokenId("SLASHASSIGN", "operator", 111),
                   /** RegularExpression Id. */
                   new GMTokenId("ANDASSIGN", "operator", 112),
                   /** RegularExpression Id. */
                   new GMTokenId("ORASSIGN", "operator", 113),
                   /** RegularExpression Id. */
                   new GMTokenId("XORASSIGN", "operator", 114),
                   /** RegularExpression Id. */
                   new GMTokenId("REMASSIGN", "operator", 115),
                   /** RegularExpression Id. */
                   new GMTokenId("LSHIFTASSIGN", "operator", 116),
                   /** RegularExpression Id. */
                   new GMTokenId("RSIGNEDSHIFTASSIGN", "operator", 117),
                   /** RegularExpression Id. */
                   new GMTokenId("RUNSIGNEDSHIFTASSIGN", "operator", 118),
                   /** RegularExpression Id. */
                   new GMTokenId("ELLIPSIS", "operator", 119),
                   /** RegularExpression Id. */
                   new GMTokenId("RUNSIGNEDSHIFT", "operator", 120),
                   /** RegularExpression Id. */
                   new GMTokenId("RSIGNEDSHIFT", "operator", 121),
                   /** RegularExpression Id. */
                   new GMTokenId("GT", "operator", 122),
                     /** Lexical state. */
                    new GMTokenId("GLOBAL", "operator", 123),
                    new GMTokenId("LOCAL", "OPERATOR", 124),
                    new GMTokenId("MEMBER", "Operator", 125),

               });
       idToToken = new HashMap<Integer, GMTokenId>();
       for (GMTokenId token : tokens) {
           idToToken.put(token.ordinal(), token);
       }
   }

   static synchronized GMTokenId getToken(int id) {
       if (idToToken == null) {
           init();
       }
       return idToToken.get(id);
   }

   @Override
   protected synchronized Collection<GMTokenId> createTokenIds() {
       if (tokens == null) {
           init();
       }
       return tokens;
   }

   @Override
   protected synchronized Lexer<GMTokenId> createLexer(LexerRestartInfo<GMTokenId> info) {
       return new GMLexer(info);
   }

   
   @Override
   protected String mimeType() {
       return "text/x-gamemonkey";
   }
}
