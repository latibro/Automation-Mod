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

local cows = entities.filterByName("Cow")

for i=1, 30 do
    print("Number of loaded cows in world: " .. cows.size())
    os.sleep(1)
end