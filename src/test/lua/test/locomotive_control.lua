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
