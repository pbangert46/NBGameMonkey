
package org.gamemonkey.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ChangeListener;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.api.Task;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.SourceModificationEvent;
import org.gamemonkey.jcc.parser.GameMonkeyParser;

public class GMParser extends Parser {

    private Snapshot snapshot;
    private GameMonkeyParser javaParser;

    @Override
    public void parse (Snapshot snapshot, Task task, SourceModificationEvent event) {
        this.snapshot = snapshot;
        Reader reader = new StringReader (snapshot.getText ().toString ());
        javaParser = new GameMonkeyParser (reader);
        try {
            javaParser.CompilationUnit ();
        } catch (org.gamemonkey.jcc.parser.ParseException ex) {
            Logger.getLogger (GameMonkeyParser.class.getName()).log (Level.WARNING, null, ex);
        }
    }

    @Override
    public Result getResult (Task task) {
        return new GMParserResult (snapshot, javaParser);
    }

    @Override
    public void cancel () {
    }

    @Override
    public void addChangeListener (ChangeListener changeListener) {
    }

    @Override
    public void removeChangeListener (ChangeListener changeListener) {
    }


    public static class GMParserResult extends Result {

        private final GameMonkeyParser javaParser;
        private boolean valid = true;

        GMParserResult (Snapshot snapshot, GameMonkeyParser javaParser) {
            super (snapshot);
            this.javaParser = javaParser;
        }

        public GameMonkeyParser getJavaParser () throws org.netbeans.modules.parsing.spi.ParseException {
            if (!valid) throw new org.netbeans.modules.parsing.spi.ParseException ();
            return javaParser;
        }

        @Override
        protected void invalidate () {
            valid = false;
        }
    }
}
