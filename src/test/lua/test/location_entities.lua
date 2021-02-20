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

local location = getAutomationLink("location_link")

print("Location: " .. location.getX() .. ", " .. location.getY() .. ", " .. location.getZ())

local entities = location.getEntities(false)
print("Number of entities at location: " .. entities.size())

local entitiesBoundingBox = location.getEntities(true)
print("Number of entities at location (bounding box): " .. entitiesBoundingBox.size())

local entity = entitiesBoundingBox.asList()[1]
print("UUID of first entity: " .. entity.getUUID())

local entityLocation = entity.getLocation()
print("Location of first entity: " .. entityLocation.getX() .. ", " .. entityLocation.getY() .. ", " .. entityLocation.getZ())
