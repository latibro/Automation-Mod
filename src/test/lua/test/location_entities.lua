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

local coordinate = { x = 756, y = 4, z = -325 }

----------

local world = getAutomationLink("world_link")

local location = world.getLocationByCoordinates(coordinate.x, coordinate.y, coordinate.z)
print("Location: " .. location.getX() .. ", " .. location.getY() .. ", " .. location.getZ())

local entities = location.getEntities()
print("Number of entities at location: " .. entities.size())

local entity = entities.get(1)
print("UUID of first entity: " .. entity.getUUID())

local entityLocation = entity.getLocation()
print("Location of first entity: " .. entityLocation.getX() .. ", " .. entityLocation.getY() .. ", " .. entityLocation.getZ())
