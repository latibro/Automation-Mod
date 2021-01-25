./gradlew clean build
rm /Users/rickirunge/Twitch/Minecraft/Instances/Automation\ Test/mods/automation-*.jar
find build/libs/automation-*.jar -type f -not -iname '*-sources.jar' -exec cp '{}' '/Users/rickirunge/Twitch/Minecraft/Instances/Automation Test/mods/' ';'
