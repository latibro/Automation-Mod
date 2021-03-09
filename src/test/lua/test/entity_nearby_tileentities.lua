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

local location = entity.getLocation()

local nearbyTileEntities = location.getNearbyTileEntities(5)

local nearbyDataBoxes = nearbyTileEntities.whereProperty("type", "automation:data_box")

for i = 1, 600 do
    --print("Number of nearby tile entities: " .. nearbyTileEntities.count())
    --print("Number of nearby data boxes: " .. nearbyDataBoxes.count())

    local tileEntities = nearbyDataBoxes.asList()
    if tileEntities then
        --print("Number of nearby tile entities: " .. nearbyTileEntities.count())
        --print("Number of nearby data boxes: " .. nearbyDataBoxes.count())

        print("Number of nearby data boxes: " .. #tileEntities)

        local tileEntities = nearbyDataBoxes.asList()

        for i, tileEntity in ipairs(tileEntities) do
            print("--- Tile Entity #" .. i .. " ---")

            print("Type of tile entity: " .. tileEntity.getType())

            local location = tileEntity.getLocation()
            print("Location of tile entity: " .. location.getX() .. ", " .. location.getY() .. ", " .. location.getZ())

            local dataBox = tileEntity.getAPI("automation:data_box")
            --dataBox.setData("Hello world")
            print(dataBox.getData())
        end

        print("---------")
    end

    os.sleep(0.1)
end