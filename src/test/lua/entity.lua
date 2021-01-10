local component = require("component")

local interfaceBox = component.interface_box

local entityAPI = interfaceBox.api("entity")

local entity = entityAPI.linkByUUID("f07b3081-f98f-4d37-a4e7-15cd015b5efc")

entity.getUUID()
entity.isAvailable()
entity.getType()
entity.getPosition()

entity.api("control")
entity.as("rail.rolling_stock")
