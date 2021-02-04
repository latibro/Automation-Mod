local interfaceBox
if peripheral then
    -- ComputerCraft
    interfaceBox = peripheral.find("interface_box")
else
    -- OpenComputers
    local component = require("component")
    interfaceBox = component.interface_box
end

local coordinate = { x = 756, y = 4, z = -325 }

----------

local world = interfaceBox.getWorld()

local location = world.getLocationByCoordinate(coordinate.x, coordinate.y, coordinate.z)
print("Location: " .. location.getX() .. ", " .. location.getY() .. ", " .. location.getZ())

local entities = location.getEntities()
print("Number of entities at location: " .. entities.size())

local entity = entities.getAt(1)
print("UUID of first entity: " .. entity.getUUID())

local entityLocation = entity.getLocation()
print("Location of first entity: " .. entityLocation.getX() .. ", " .. entityLocation.getY() .. ", " .. entityLocation.getZ())
