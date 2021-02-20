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

for i=1, 30 do
    print("Number of loaded entities in world: " .. loadedEntities.size())
    os.sleep(1)
end