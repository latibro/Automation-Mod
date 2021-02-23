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
function adjustSpeed(rollingStock, speed)
    local firstCurrentSpeed = rollingStock.getCurrentSpeed()
    os.sleep(0.1)
    local currentSpeed = rollingStock.getCurrentSpeed()
    local currentThrottle = rollingStock.getThrottleLevel()
    local currentBrake = rollingStock.getAirBrakeLevel()
    print("Current speed: " .. tostring(currentSpeed))
    print("Current throttle: " .. tostring(currentThrottle))
    print("Current brake: " .. tostring(currentBrake))
    local currentSpeedDiff = (currentSpeed - firstCurrentSpeed) * 10
    print("Current speed diff: " .. tostring(currentSpeedDiff))
    local speedDiff = speed - currentSpeed
    print("Speed diff: " .. tostring(speedDiff))
    local speedDiffFactor = math.abs(speedDiff / currentSpeedDiff)
    print("Speed diff factor: " .. tostring(speedDiffFactor))

    local speedTolerance = 1

    if (currentSpeed < (speed - speedTolerance)) then
        -- Going too slow
        print("Going too slow")
        local level = math.max(-1, math.min(1, (1 - (currentSpeed / speed))))
        rollingStock.setThrottleLevel(level)
    elseif (currentSpeed > (speed + speedTolerance)) then
        print("Going too fast")
        local level = math.max(-1, math.min(1, (1 - (currentSpeed / speed))))
        rollingStock.setThrottleLevel(level)
    else
        print("Cruising")
        rollingStock.setThrottleLevel(0)
    end
    print("New throttle: " .. tostring(rollingStock.getThrottleLevel()))
end

----------

local entity = getAutomationLink("entity_link")

local rollingStock = entity.getAPI("immersiverailroading:locomotivediesel")
local rollingStockLocation = rollingStock.getLocation()

rollingStock.startEngine()

os.sleep(1)
rollingStock.soundHorn()
rollingStock.setAirBrakeLevel(0)

os.sleep(1)
print("**** new speed: 40")

for i = 1, 60 do
    adjustSpeed(rollingStock, 40)
    print("---")
    os.sleep(0.2)
end

rollingStock.soundHorn()
print("**** new speed: 80")

for i = 1, 60 do
    adjustSpeed(rollingStock, 80)
    print("---")
    os.sleep(0.1)
end

rollingStock.soundHorn()
print("**** new speed: 40")

for i = 1, 60 do
    adjustSpeed(rollingStock, 40)
    print("---")
    os.sleep(0.1)
end

rollingStock.setAirBrakeLevel(1)
rollingStock.setThrottleLevel(0)

os.sleep(1)
rollingStock.soundHorn()

rollingStock.stopEngine()
print("Current speed: " .. tostring(rollingStock.getCurrentSpeed()))
print("Current location: " .. rollingStockLocation.getX() .. ", " .. rollingStockLocation.getY() .. ", " .. rollingStockLocation.getZ())
