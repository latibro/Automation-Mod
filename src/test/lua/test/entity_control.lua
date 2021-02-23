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

--local uuid = "29071f68-1c73-45e8-bf42-144fa0336e3f" -- cow
--local uuid = "a0842826-17d8-47bb-b6df-2bbf7b1eca9d" -- vilager
--local uuid = "f8d87e35-1f36-4cbf-b105-42c3126cb78d" -- horse
local uuid = "3c3e872f-7779-4957-a60e-7914c891e3d8" -- pig

local red = { x = 747, y = 4, z = -315 } -- front small / red
local yellow = { x = 737, y = 4, z = -315 } -- back small / yellow
local green = { x = 747, y = 4, z = -319 } -- front large / green
local blue = { x = 737, y = 4, z = -325 } -- back large / blue
local orange = { x = 751, y = 4, z = -317 } -- outside / orange

local locations = { red, yellow, green, blue, orange }

----------

local world = getAutomationLink("world_link")

local loadedEntities = world.getLoadedEntities()

local entity = loadedEntities.whereProperty("UUID", uuid).asList()[1]

local livingEntity = entity.getAPI("minecraft:entityliving")

local route = { red, yellow, green, blue, green, red }

local entityLocation = livingEntity.getLocation()
print("    Location of entity: " .. entityLocation.getX() .. ", " .. entityLocation.getY() .. ", " .. entityLocation.getZ())

for i = 1, #route do
    local location = route[i]
    print("Navigating to : " .. location.x .. ", " .. location.y .. ", " .. location.z)
    while entityLocation.getX() ~= location.x or entityLocation.getY() ~= location.y or entityLocation.getZ() ~= location.z do
        print("    Location of entity: " .. entityLocation.getX() .. ", " .. entityLocation.getY() .. ", " .. entityLocation.getZ())
        livingEntity.navigateTo(location.x, location.y, location.z)
        print("  Navigating...")
        os.sleep(1)
    end
    print("Done navigating")
    print("    Location of entity: " .. entityLocation.getX() .. ", " .. entityLocation.getY() .. ", " .. entityLocation.getZ())
end
print("Route complete")
print("    Location of entity: " .. entityLocation.getX() .. ", " .. entityLocation.getY() .. ", " .. entityLocation.getZ())
