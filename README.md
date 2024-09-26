<div align="center">
<img 
    style="display: block; height: 30px;"
    src="https://img.shields.io/modrinth/dt/Uqqp9wzq?style=flat&logo=modrinth&label=Downloads&labelColor=%23111&color=%231bd96a&link=https%3A%2F%2Fmodrinth.com%2Fmod%2Fanti_creeper"
    alt="Modrinth Downloads">
</img>
<img 
    style="display: block; height: 30px;"
    src="https://img.shields.io/modrinth/followers/Uqqp9wzq?style=flat&logo=modrinth&label=Followers&labelColor=%23111&color=%231bd96a&link=https%3A%2F%2Fmodrinth.com%2Fmod%2Fanti_creeper"
    alt="Modrinth Downloads">
</img>
<img 
    style="display: block; height: 30px;"
    src="https://img.shields.io/modrinth/game-versions/Uqqp9wzq?style=flat&logo=modrinth&labelColor=%23111&color=%231bd96a&link=https%3A%2F%2Fmodrinth.com%2Fmod%2Fanti_creeper"
    alt="Modrinth Downloads">
</img>
</div>
<h1 align="center"> Prevent Creeper Block and Entity Damage </h1>

![Creeper exploding Meme](https://github.com/user-attachments/assets/7d2541a3-0bb8-4885-a80a-e4134c8b11f1)


## There are 3 Gamerules:

``` 
/gamerule doCreeperExplosionsBlockDamage (true/false) 
```
``` 
/gamerule doChargedCreeperExplosionsBlockDamage (true/false) 
```
``` 
/gamerule doExplosionsDecorationDamage (true/false) 
```


These gamerules allow fine control over Creeper explosion behavior:

- **doCreeperExplosionsBlockDamage**: Controls whether regular Creeper explosions cause block damage, while still allowing them to damage entities.
  
- **doChargedCreeperExplosionsBlockDamage**: Manages block damage specifically for Charged Creepers, keeping their ability to damage entities and drop mob heads intact when exploding other mobs.

- **doChargedCreeperExplosionsBlockDamage**: Do Explosions break
  - Armor Stands
  - Item Frames
  - Glow Item Frames
  - Paintings


---

Each rule operates independently. For example:
```
doCreeperExplosionsBlockDamage: false
doChargedCreeperExplosionsBlockDamage: true
```
In this case, normal Creepers won’t break blocks, but Charged Creepers still will.

---

Inspired by the no longer maintained project [Anti-Creeper by PlainsVillager](https://github.com/PlainsVillager/Anti-Creeper)

