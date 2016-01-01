package org.gamemonkey.lexer;

import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.TokenId;

public class GMTokenId implements TokenId {

    private final String name;
    private final String primaryCategory;
    private final int id;

    private static final Language<GMTokenId> language = new GMLanguageHierarchy().language();

    public static final Language<GMTokenId> getLanguage() {
        return language;
    }

    GMTokenId(
            String name,
            String primaryCategory,
            int id) {
        this.name = name;
        this.primaryCategory = primaryCategory;
        this.id = id;
    }

    @Override
    public String primaryCategory() {
        return primaryCategory;
    }

    @Override
    public int ordinal() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }
}
