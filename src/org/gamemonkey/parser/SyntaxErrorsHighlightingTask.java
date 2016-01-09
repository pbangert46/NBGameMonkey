/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.gamemonkey.parser;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import org.netbeans.modules.parsing.spi.Parser.Result;
import org.netbeans.modules.parsing.spi.ParserResultTask;
import org.netbeans.modules.parsing.spi.Scheduler;
import org.netbeans.modules.parsing.spi.SchedulerEvent;
import org.netbeans.spi.editor.hints.ErrorDescription;
import org.netbeans.spi.editor.hints.ErrorDescriptionFactory;
import org.netbeans.spi.editor.hints.HintsController;
import org.netbeans.spi.editor.hints.Severity;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.gamemonkey.jcc.parser.ParseException;
import org.gamemonkey.jcc.parser.Token;
import org.gamemonkey.parser.GMParser.GMParserResult;

/**
 *
 * @author geertjan
 */
class SyntaxErrorsHighlightingTask extends ParserResultTask {

    public SyntaxErrorsHighlightingTask () {
    }

    @Override
    public void run (Result result, SchedulerEvent event) {
        try {
            GMParserResult gmResult = (GMParserResult) result;
            List<ParseException> syntaxErrors = gmResult.getJavaParser ().syntaxErrors;
            org.netbeans.modules.parsing.api.Snapshot s = result.getSnapshot();
            org.netbeans.modules.parsing.api.Source src = s.getSource();
            Document document = src.getDocument(false);
            
            List<ErrorDescription> errors = new ArrayList<ErrorDescription> ();
            for (ParseException syntaxError : syntaxErrors) {

                Token token = syntaxError.currentToken;
                
                System.out.print(
                        "error token: " + token
                        + " - HL line/col [" 
                            + token.beginLine + "-" 
                            + token.endLine 
                            + "/" 
                            + token.beginColumn + "-" 
                            + token.endColumn 
                            + "]"
                        + "\n");
                
                System.out.print("syn error: " + syntaxError + "\n");
                
                int start = NbDocument.findLineOffset ((StyledDocument) document, (token.beginLine>0?token.beginLine-1:0));
                int end = NbDocument.findLineOffset ((StyledDocument) document, (token.endLine>0?token.endLine-1:0));
    
//                System.out.print("errHL: " + start + "-" + end + "\n");
    
                start += token.beginColumn;
                end += token.endColumn;
                
//                System.out.print("errHL+Col: " + start + "-" + end + "\n");
                
                ErrorDescription errorDescription = ErrorDescriptionFactory.createErrorDescription (
                    Severity.ERROR,
                    syntaxError.getMessage (),
                    document,
                    document.createPosition (start),
                    document.createPosition (end)
                );
                errors.add (errorDescription);
            }
            HintsController.setErrors (document, "GameMonkey", errors);
            
        } catch (BadLocationException ex1) {
            Exceptions.printStackTrace (ex1);
        } catch (org.netbeans.modules.parsing.spi.ParseException ex1) {
            Exceptions.printStackTrace (ex1);
        }
    }

    @Override
    public int getPriority () {
        return 100;
    }

    @Override
    public Class<? extends Scheduler> getSchedulerClass () {
        return Scheduler.EDITOR_SENSITIVE_TASK_SCHEDULER;
    }

    @Override
    public void cancel () {
    }
}
