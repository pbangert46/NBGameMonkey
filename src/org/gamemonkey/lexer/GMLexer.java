/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.gamemonkey.lexer;

import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.netbeans.spi.lexer.LexerInput;
import org.gamemonkey.jcc.lexer.SimpleCharStream;
import org.gamemonkey.jcc.lexer.GameMonkeyLexerTokenManager;
import org.gamemonkey.jcc.lexer.Token;

class GMLexer implements Lexer<GMTokenId> {

    private LexerRestartInfo<GMTokenId> info;
    private GameMonkeyLexerTokenManager javaParserTokenManager;


    GMLexer (LexerRestartInfo<GMTokenId> info) {
        this.info = info;
        SimpleCharStream stream = new SimpleCharStream (info.input());
        javaParserTokenManager = new GameMonkeyLexerTokenManager (stream);
    }

    public org.netbeans.api.lexer.Token<GMTokenId> nextToken () {
        Token token = javaParserTokenManager.getNextToken ();
        if (info.input ().readLength () < 1) return null;
        return info.tokenFactory ().createToken (GMLanguageHierarchy.getToken (token.kind));
    }

    public Object state () {
        return null;
    }

    public void release () {
    }
}
