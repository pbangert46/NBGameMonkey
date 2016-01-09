/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.gamemonkey.lexer;

import java.io.BufferedInputStream;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.netbeans.spi.lexer.LexerInput;
import org.gamemonkey.jcc.lexer.SimpleCharStream;
import org.gamemonkey.jcc.lexer.GameMonkeyLexerTokenManager;
import org.gamemonkey.jcc.lexer.Token;
import org.gamemonkey.jcc.lexer.GameMonkeyLexerConstants;

class GMLexer implements Lexer<GMTokenId> {

    private LexerRestartInfo<GMTokenId> info;
    private GameMonkeyLexerTokenManager javaParserTokenManager;


    GMLexer (LexerRestartInfo<GMTokenId> info) {
        this.info = info;
        
        //JavaCharStream stream = new JavaCharStream (new BufferedInputStream(new InputWrapper(info.input())));
        SimpleCharStream stream = new SimpleCharStream (new BufferedInputStream(new InputWrapper(info.input())));
        javaParserTokenManager = new GameMonkeyLexerTokenManager (stream);
        
    }

    public org.netbeans.api.lexer.Token<GMTokenId> nextToken () {
        Token token = javaParserTokenManager.getNextToken ();
        
        System.out.print(
                    "nt(" + token.kind + "/" + GMLanguageHierarchy.getToken(token.kind).name() + "): " 
                    + token.toString() 
                    + "\t\t[" + GameMonkeyLexerConstants.tokenImage[token.kind] + "]\n");
        if (info.input ().readLength () < 1) return null;
        return info.tokenFactory ().createToken (GMLanguageHierarchy.getToken (token.kind));
    }

    public Object state () {
        return null;
    }

    public void release () {
    }

    private static class InputWrapper extends java.io.InputStream {
        LexerInput input;
        
        InputWrapper (LexerInput i)
        {
            input = i;
        }
        
        public int read()
        {
            return input.read();
        }
        
    }

}
