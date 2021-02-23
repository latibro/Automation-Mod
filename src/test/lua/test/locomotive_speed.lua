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
    local speedFactorToSlow = 5
    local speedFactorToFast = speedFactorToSlow / 2

    local levelChange = 0.1
    --local levelChange = math.abs(math.max(0.1, (speedDiff/100)))
    print("Level change: " .. tostring(levelChange))

    if (currentSpeed < (speed - speedTolerance)) then
        -- Going too slow
        print("Going too slow")
        if (currentBrake > 0) then
            print("Brake off")
            rollingStock.setAirBrakeLevel(0)
        end
        if (speedDiffFactor > speedFactorToSlow) then
            print("Need more throttle")
            local newThrottle = math.min(1, currentThrottle + levelChange)
            if (newThrottle ~= currentThrottle) then
                print("Increase thottle")
                rollingStock.setThrottleLevel(newThrottle)
            end
        elseif (speedDiffFactor < speedFactorToFast) then
            print("Need less throttle")
            local newThrottle = math.max(0, currentThrottle - levelChange)
            if (newThrottle ~= currentThrottle) then
                print("Decrease thottle")
                rollingStock.setThrottleLevel(newThrottle)
            end
        else
            print("Speeding up")
        end
    elseif (currentSpeed > (speed + speedTolerance)) then
        -- Going too fast
        print("Going too fast")
        if (currentThrottle > 0) then
            print("Throttle off")
            rollingStock.setThrottleLevel(0)
        end
        if (speedDiffFactor > speedFactorToSlow) then
            print("Need more brake")
            local newBrake = math.min(1, currentBrake + levelChange)
            if (newBrake ~= currentBrake) then
                print("Increase brake")
                rollingStock.setAirBrakeLevel(newBrake)
            end
        elseif (speedDiffFactor < speedFactorToFast) then
            print("Need less brake")
            local newBrake = math.max(0, currentBrake - levelChange)
            if (newBrake ~= currentBrake) then
                print("Decrease brake")
                rollingStock.setAirBrakeLevel(newBrake)
            end
        else
            print("Slowing down")
        end
    else
        print("Cruising")
        rollingStock.setAirBrakeLevel(0)
        rollingStock.setThrottleLevel(0)
    end
    print("New brake: " .. tostring(rollingStock.getAirBrakeLevel()))
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
    os.sleep(0.1)
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
