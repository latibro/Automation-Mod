package latibro.automation.core.context.link

interface MultiLinkContext<T extends LinkContext> extends LinkContext {

    int count()

    List<T> asList()

    List<T> asList(int maxCount)

    MultiLinkContext whereProperty(String property, Object expected)

}