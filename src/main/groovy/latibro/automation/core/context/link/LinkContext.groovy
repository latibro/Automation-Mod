package latibro.automation.core.context.link

import latibro.automation.core.LinkType
import latibro.automation.core.context.Context

interface LinkContext extends Context {

    boolean isLinked()

    LinkType getLinkType()

}
