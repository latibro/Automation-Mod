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

local entities = world.getLoadedEntities()
print("Number of loaded entities in world: " .. entities.size())

local cows = entities.filterByName("Cow")

for i=1, 30 do
    print("Number of loaded cows in world: " .. cows.size())
    os.sleep(1)
end