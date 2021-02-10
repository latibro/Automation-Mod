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

for i=1, 10 do
    print("--- Entity #" .. i .. " ---")

    local entity = entities.get(i)
    print("UUID of entity: " .. entity.getUUID())
    print("Name of entity: " .. entity.getName())

    local entityLocation = entity.getLocation()
    print("Location of entity: " .. entityLocation.getX() .. ", " .. entityLocation.getY() .. ", " .. entityLocation.getZ())
end
