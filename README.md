# Useful-Dragon-Eggs

[
![Curseforge Downloads](http://cf.way2muchnoise.eu/useful-dragon-eggs.svg)
![Curseforge Versions](http://cf.way2muchnoise.eu/versions/useful-dragon-eggs.svg)
](https://www.curseforge.com/minecraft/bukkit-plugins/useful-dragon-eggs)
[
![Discord](https://img.shields.io/discord/297104769649213441?label=Discord)
](https://discordapp.com/invite/QXbWS36)

### This plugin adds a command to give dragon eggs the use of bedrock breaking again.

- Download on [curseforge](https://www.curseforge.com/minecraft/bukkit-plugins/useful-dragon-eggs).  
- Find more information on our [website](https://u-team.info/plugins/usefuldragoneggs).
- Updates can be found in the [changelog](CHANGELOG.md).

### How to build this mod

#### Setup Spigot
- ``./gradlew setupWorkspace``
- Import project as gradle project

#### Run the server
- ``./gradlew startDevServer``

#### Build
- ``./gradlew build``

### How to include this mod

- Repository: [repo.u-team.info](https://repo.u-team.info)
- Artifact: **info.u-team.bukkit:useful_dragon_eggs-bukkit:${config.usefuldragoneggs.version}** 
- *{config.usefuldragoneggs.version}* is the usefuldragoneggs version.

#### Include in gradle:
```gradle
repositories {
    maven { url = "https://repo.u-team.info" }
}

dependencies {
  compileOnly "info.u-team.bukkit:useful_dragon_eggs-bukkit:${config.usefuldragoneggs.version}"
}
```

### License

- This plugin is licensed under apache 2 license. For more information see [here](LICENSE).

### Issues

- Please report issues to the [github issues](../../issues).
- Include your minecraft version, bukkit / spigot / paper version and plugin version.
- Upload your log on [gist](https://gist.github.com) or [pastebin](https://pastebin.com) and include link in your report.
