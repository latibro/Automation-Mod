package latibro.automation.api.link.entity


import latibro.automation.api.link.FilterableLinkAPI
import latibro.automation.api.link.MultiLinkAPI

interface EntityMultiLinkAPI extends MultiLinkAPI<EntityLinkAPI>, FilterableLinkAPI<EntityMultiLinkAPI> {

}