local interfaceBox
if peripheral then
    -- ComputerCraft
    interfaceBox = peripheral.find("interface_box")
else
    -- OpenComputers
    local component = require("component")
    interfaceBox = component.interface_box
end

function waitForDistance(location, x, y, z, distance)
    print("----")
    local currentDistance = location.getDistanceToCoordinate(x, y, z)
    print("Distance to location: " .. tostring(currentDistance))
    repeat
        os.sleep(0.1)
        currentDistance = location.getDistanceToCoordinate(x, y, z)
        --print("Distance to location: " .. tostring(currentDistance))
    until (currentDistance <= distance)
    currentDistance = location.getDistanceToCoordinate(x, y, z)
    print("Distance to location: " .. tostring(currentDistance))
    print("----")
end

local world = interfaceBox.getWorld()

local loadedEntities = world.getLoadedEntities()

local entity = loadedEntities.getByUUID("6924eb11-49d8-40e0-8006-6f8dfc930e78")

local rollingStock = entity.asType("immersiverailroading.rollingstock.locomotive.diesel")
local rollingStockLocation = rollingStock.getLocation()

rollingStock.startEngine()

os.sleep(1)
rollingStock.soundHorn()

rollingStock.setAirBrakeLevel(0)
rollingStock.setThrottleLevel(0.5)

os.sleep(1)

print("current speed: " .. tostring(rollingStock.getCurrentSpeed()))

waitForDistance(rollingStockLocation, 848, 4, -254, 10)
rollingStock.soundHorn()
rollingStock.setThrottleLevel(1)
print("Current speed: " .. tostring(rollingStock.getCurrentSpeed()))
print("Current location: " .. rollingStockLocation.getX() .. ", " .. rollingStockLocation.getY() .. ", " .. rollingStockLocation.getZ())

waitForDistance(rollingStockLocation, 699, 4, -254, 10)
rollingStock.soundHorn()
rollingStock.setThrottleLevel(0.1)
rollingStock.setAirBrakeLevel(0.1)
print("Current speed: " .. tostring(rollingStock.getCurrentSpeed()))
print("Current location: " .. rollingStockLocation.getX() .. ", " .. rollingStockLocation.getY() .. ", " .. rollingStockLocation.getZ())

waitForDistance(rollingStockLocation, 767, 4, -303, 20)
rollingStock.soundHorn()
rollingStock.setThrottleLevel(0)
rollingStock.setAirBrakeLevel(0.5)
print("Current speed: " .. tostring(rollingStock.getCurrentSpeed()))
print("Current location: " .. rollingStockLocation.getX() .. ", " .. rollingStockLocation.getY() .. ", " .. rollingStockLocation.getZ())

waitForDistance(rollingStockLocation, 767, 4, -303, 10)
rollingStock.soundHorn()
rollingStock.setThrottleLevel(0)
rollingStock.setAirBrakeLevel(1)
print("Current speed: " .. tostring(rollingStock.getCurrentSpeed()))
print("Current location: " .. rollingStockLocation.getX() .. ", " .. rollingStockLocation.getY() .. ", " .. rollingStockLocation.getZ())

os.sleep(1)
rollingStock.soundHorn()

rollingStock.stopEngine()
print("Current speed: " .. tostring(rollingStock.getCurrentSpeed()))
print("Current location: " .. rollingStockLocation.getX() .. ", " .. rollingStockLocation.getY() .. ", " .. rollingStockLocation.getZ())
