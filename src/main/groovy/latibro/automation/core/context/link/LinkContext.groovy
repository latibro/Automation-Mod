package latibro.automation.core.context.link

import latibro.automation.core.LinkType
import latibro.automation.core.context.OldContext

interface LinkContext extends OldContext {

    boolean isLinked()

    LinkType getLinkType()

}
