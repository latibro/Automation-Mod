package latibro.automation.core.context

interface ContextProvider {

    List<String> getSubContextNames(Context context)

    Context findSubContext(String name, Context context)

}