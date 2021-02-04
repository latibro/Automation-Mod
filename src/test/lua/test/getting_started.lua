local interfaceBox
if peripheral then
    -- ComputerCraft
    interfaceBox = peripheral.find("interface_box")
else
    -- OpenComputers
    local component = require("component")
    interfaceBox = component.interface_box
end

local world = interfaceBox.getWorld()

local entities = world.getLoadedEntities()
print("Number of loaded entities in world: " .. entities.size())

for i=1, 10 do
    print("--- Entity #" .. i .. " ---")

    local entity = entities.getAt(i)
    print("UUID of entity: " .. entity.getUUID())
    print("Name of entity: " .. entity.getName())

    local entityLocation = entity.getLocation()
    print("Location of entity: " .. entityLocation.getX() .. ", " .. entityLocation.getY() .. ", " .. entityLocation.getZ())
end
