package org.panda_lang.editor;

public class ResourcesBuilder {

    private final Class clazz;
    private final StringBuilder stringBuilder;

    public ResourcesBuilder(Class clazz) {
        this.clazz = clazz;
        this.stringBuilder = new StringBuilder();
    }

    public void importCss(String file) {
        stringBuilder.append("<link rel=\"stylesheet\" href=\"");
        stringBuilder.append(Lily.class.getResource(file).toExternalForm());
        stringBuilder.append("\">");
    }

    public void importScript(String file) {
        stringBuilder.append("<script src=\"");
        stringBuilder.append(Lily.class.getResource(file).toExternalForm());
        stringBuilder.append("\"></script>");
    }

    @Override
    public String toString() {
        return this.stringBuilder.toString();
    }

}
