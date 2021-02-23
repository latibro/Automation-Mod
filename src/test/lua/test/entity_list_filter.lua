function getAutomationLink(name)
    if peripheral then
        -- ComputerCraft
        return peripheral.find(name)
    else
        -- OpenComputers
        local component = require("component")
        return component.getPrimary(name)
    end
end

----------

local world = getAutomationLink("world_link")

local loadedEntities = world.getLoadedEntities()
print("Number of loaded entities in world: " .. loadedEntities.count())

local cows = loadedEntities.whereProperty("name", "Cow")
print("Number of loaded cows in world: " .. cows.count())
