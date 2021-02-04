local interfaceBox
if peripheral then
    -- ComputerCraft
    interfaceBox = peripheral.find("interface_box")
else
    -- OpenComputers
    local component = require("component")
    interfaceBox = component.interface_box
end

local coordinate_A = { x = 756, y = 4, z = -325 }
local coordinate_B = { x = coordinate_A.x+3, y = coordinate_A.y+4, z = coordinate_A.z+0 }
-- Using the 3-4-5 triangle to validate
----------

local world = interfaceBox.getWorld()

local location_A = world.getLocationByCoordinate(coordinate_A.x, coordinate_A.y, coordinate_A.z)
print("Location A: " .. location_A.getX() .. ", " .. location_A.getY() .. ", " .. location_A.getZ())

local location_B = world.getLocationByCoordinate(coordinate_B.x, coordinate_B.y, coordinate_B.z)
print("Location B: " .. location_B.getX() .. ", " .. location_B.getY() .. ", " .. location_B.getZ())

local distance = location_A.getDistanceToCoordinate(coordinate_B.x, coordinate_B.y, coordinate_B.z)
print("Distance between A and B locations: " .. distance)
