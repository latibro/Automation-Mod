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

local entity = getAutomationLink("entity_link")

local rollingStock = entity.getAPI("immersiverailroading:locomotive")
local rollingStockLocation = rollingStock.getLocation()

local component = require("component")
local gpu = component.gpu -- get primary gpu component
local w, h = gpu.getResolution()

----------
function updateScreen()
    gpu.set(15, 1, string.format("% 10s", string.format("%.2f", rollingStock.getCurrentSpeed())))

    gpu.set(15, 3, string.format("% 10s", string.format("%.2f", rollingStock.getThrottleLevel())))
    gpu.set(15, 4, string.format("% 10s", string.format("%.2f", rollingStock.getAirBrakeLevel())))

    gpu.set(15, 6, string.format("% 8s", string.format("%d", rollingStockLocation.getX())))
    gpu.set(25, 6, string.format("% 8s", string.format("%d", rollingStockLocation.getY())))
    gpu.set(35, 6, string.format("% 7s", string.format("%d", rollingStockLocation.getZ())))
end

----------

gpu.fill(1, 1, w, h, " ") -- clears the screen
gpu.set(1, 1, "Speed:")

gpu.set(1, 3, "Thottle:")
gpu.set(1, 4, "Brake:")

gpu.set(1, 6, "Location:")
gpu.set(24, 6, ",")
gpu.set(34, 6, ",")

while true do
    updateScreen()
    os.sleep(0.1)
end