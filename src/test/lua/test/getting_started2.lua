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

local loadedTileEntities = world.getLoadedTileEntities()
print("Number of loaded tile entities in world: " .. loadedTileEntities.count())

local x = loadedTileEntities.whereProperty("type", "automation:world_link_box")
print("Number of loaded tile entities in world: " .. x.count())

local tileEntities = x.asList(10)

for i, tileEntity in ipairs(tileEntities) do
    print("--- Tile Entity #" .. i .. " ---")

    print("Type of tile entity: " .. tileEntity.getType())

    local location = tileEntity.getLocation()
    print("Location of tile entity: " .. location.getX() .. ", " .. location.getY() .. ", " .. location.getZ())
end
