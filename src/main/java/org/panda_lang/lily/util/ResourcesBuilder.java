package org.panda_lang.lily.util;

import org.panda_lang.lily.Lily;

public class ResourcesBuilder
{

    private final StringBuilder stringBuilder;

    public ResourcesBuilder()
    {
        this.stringBuilder = new StringBuilder();
    }

    public void importCss(String file)
    {
        stringBuilder.append("<link rel=\"stylesheet\" href=\"");
        stringBuilder.append(Lily.class.getResource(file).toExternalForm());
        stringBuilder.append("\">");
    }

    public void importScript(String file)
    {
        stringBuilder.append("<script src=\"");
        stringBuilder.append(Lily.class.getResource(file).toExternalForm());
        stringBuilder.append("\"></script>");
    }

    @Override
    public String toString()
    {
        return stringBuilder.toString();
    }

}
