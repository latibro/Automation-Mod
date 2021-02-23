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

local coordinate_A = { x = 756, y = 4, z = -325 }
local coordinate_B = { x = coordinate_A.x + 3, y = coordinate_A.y + 4, z = coordinate_A.z + 0 }
-- Using the 3-4-5 triangle to validate (distance should be 5)

----------

local world = getAutomationLink("world_link")

local location_A = world.getLocationByCoordinates(coordinate_A.x, coordinate_A.y, coordinate_A.z)
print("Location A: " .. location_A.getX() .. ", " .. location_A.getY() .. ", " .. location_A.getZ())

local location_B = world.getLocationByCoordinates(coordinate_B.x, coordinate_B.y, coordinate_B.z)
print("Location B: " .. location_B.getX() .. ", " .. location_B.getY() .. ", " .. location_B.getZ())

local distance = location_A.getDistanceToCoordinates(coordinate_B.x, coordinate_B.y, coordinate_B.z)
print("Distance between A and B locations: " .. distance)
