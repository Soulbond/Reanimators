package com.unco.reanimators.command.syntax.parsers;


import com.unco.reanimators.command.syntax.SyntaxChunk;
import com.unco.reanimators.command.syntax.SyntaxParser;

public abstract class AbstractParser implements SyntaxParser {

    @Override
    public abstract String getChunk(SyntaxChunk[] chunks, SyntaxChunk thisChunk, String[] values, String chunkValue);
    protected String getDefaultChunk(SyntaxChunk chunk){
        return (chunk.isHeadless() ? "" : chunk.getHead()) + (chunk.isNecessary() ? "<" : "[") + chunk.getType() + (chunk.isNecessary() ? ">" : "]");
    }

}
