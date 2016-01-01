
package org.gamemonkey.parser;

import java.util.Collection;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.ParserFactory;

public class GMParserFactory extends ParserFactory {
    @Override
    public Parser createParser (Collection<Snapshot> snapshots) {
        return new GMParser();
    }
}
