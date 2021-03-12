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

local entity = getAutomationLink("entity_link")

local location = entity.getLocation()
print("Location of tile entity: " .. location.getX() .. ", " .. location.getY() .. ", " .. location.getZ())

local nearbyEntities = entity.getNearbyEntities(0)

print("Number of nearby etities: " .. nearbyEntities.count())

local nearbyEntitiesList = nearbyEntities.asList(10)

for i, nearbyEntity in ipairs(nearbyEntitiesList) do
    print("--- Entity #" .. i .. " ---")

    print("Type of name: " .. nearbyEntity.getName())
    print("Type of entity: " .. nearbyEntity.getType())

    local nearbyLlocation = nearbyEntity.getLocation()
    print("Location of entity: " .. nearbyLlocation.getX() .. ", " .. nearbyLlocation.getY() .. ", " .. nearbyLlocation.getZ())
end
