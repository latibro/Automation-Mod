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

local loadedEntities = world.getLoadedEntities()

local entity = loadedEntities.getByUUID("6924eb11-49d8-40e0-8006-6f8dfc930e78")

local rollingStock = entity.asType("immersiverailroading.rollingstock.locomotive.diesel")

if rollingStock.getTag() then
    print("tag: " .. tostring(rollingStock.getTag()))
end
rollingStock.setTag(tostring(math.random()))
print("tag: " .. tostring(rollingStock.getTag()))

rollingStock.startEngine()

os.sleep(1)
rollingStock.soundHorn()

rollingStock.setAirBrakeLevel(0)
rollingStock.setThrottleLevel(0.5)

print("current speed: " .. tostring(rollingStock.getCurrentSpeed()))
os.sleep(5)
print("current speed: " .. tostring(rollingStock.getCurrentSpeed()))
os.sleep(5)
print("current speed: " .. tostring(rollingStock.getCurrentSpeed()))

rollingStock.setThrottleLevel(0)
rollingStock.setAirBrakeLevel(1)

os.sleep(2)
rollingStock.startBell()
os.sleep(1)

rollingStock.setAirBrakeLevel(0)
rollingStock.setThrottleLevel(-0.5)

print("current speed: " .. tostring(rollingStock.getCurrentSpeed()))
os.sleep(5)
print("current speed: " .. tostring(rollingStock.getCurrentSpeed()))
os.sleep(5)
print("current speed: " .. tostring(rollingStock.getCurrentSpeed()))

rollingStock.setThrottleLevel(0)
rollingStock.setAirBrakeLevel(1)

rollingStock.stopBell()
os.sleep(1)

rollingStock.soundHorn()
os.sleep(2)
rollingStock.stopEngine()
