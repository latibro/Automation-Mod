local component = require("component")

local sourceBox = component.source_box


-- Entiry by UUID
local provider = sourceBox.getProvider("entity.uuid")
local link = provider.get(uuid) -- Link

-- Loaded entities
local provider = sourceBox.getProvider("entity.loaded")
local multiLink = provider.getList() -- MultiLink
local link = multiLink.get(1) -- Link