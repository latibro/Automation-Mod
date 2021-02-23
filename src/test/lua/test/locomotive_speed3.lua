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
    print("Current speed: " .. tostring(currentSpeed))
    local currentSpeedDiff = (currentSpeed - firstCurrentSpeed) * 10
    print("Current speed diff: " .. tostring(currentSpeedDiff))
    local speedDiff = speed - currentSpeed
    print("Speed diff: " .. tostring(speedDiff))
    local speedDiffFactor = speedDiff / currentSpeedDiff
    print("Speed diff factor: " .. tostring(speedDiffFactor))

    local speedTolerance = 1
    local speedFactor = 10

    local speedChangeFactor = speedDiffFactor / speedFactor
    print("Level factor: " .. tostring(speedChangeFactor))

    local newLevel = math.max(-1, math.min(1, speedChangeFactor))
    print("Level change: " .. tostring(newLevel))

    if (currentSpeed < (speed - speedTolerance)) then
        -- Going too slow
        print("Going too slow")
        rollingStock.setThrottleLevel(newLevel)
    elseif (currentSpeed > (speed + speedTolerance)) then
        -- Going too fast
        print("Going too fast")
        rollingStock.setThrottleLevel(newLevel)
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

for i = 1, 30 do
    adjustSpeed(rollingStock, 40)
    print("---")
    os.sleep(1)
end

rollingStock.setAirBrakeLevel(1)
rollingStock.setThrottleLevel(0)

os.sleep(1)
rollingStock.soundHorn()

rollingStock.stopEngine()
print("Current speed: " .. tostring(rollingStock.getCurrentSpeed()))
print("Current location: " .. rollingStockLocation.getX() .. ", " .. rollingStockLocation.getY() .. ", " .. rollingStockLocation.getZ())
