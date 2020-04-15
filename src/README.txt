Group #20
Sarah Rieger (U47427637)
Catherine Kim (U51788489)

Compilation Instructions:
javac GameDriver.java
java GameDriver

Usability of each class:
Armory: class that has the general attributes and constructor that any specific armory pieces will invoke using the keyword super (assumed that characters can only use one piece of armory at a time)
	Subclasses that allow for scalability should specific armor pieces have other effects in updated versions of the game:
	-Breastplate (duplicate ignored in provided file)
	-Full_Body_Armor
	-Platinum_Shield
	-Wizard_Shield
	-Speed_Boots

Characters: Abstract class that provides hierarchy to the heroes and monsters
Monster: Abstract class to Dragons, Exoskeletons, and spirits (noted that in gameplay, monsters cannot win unless hero teleports out of their lane)
Dragons: class that has the general attributes and constructor that any specific dragon characters will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Desghidorrah
	-Chrysophylax
	-BunsenBurner
	-Natsunomeryu
	-TheScaleless
	-Kas_Ethelinh
	-Alexstraszan
	-Phaarthurnax
	-D_Maleficent
	-TheWeatherbe
Exoskeletons: superclass that has the general attributes and constructor that any specific dragon characters will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Cyrrollalee
	-Brandobaris
	-BigBad-Wolf
	-WickedWitch
	-Aasterinian
	-Chronepsish
	-Kiaransalee
	-St_Shargaas
	-Merrshaullk
	-St_Yeenoghu
Spirits: superclass that has the general attributes and constructor that any specific dragon characters will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Andrealphus
	-Aim-Haborym
	-Andromalius
	-Chiang-shih
	-FallenAngel
	-Ereshkigall
	-Melchiresas
	-Jormunngand
	-Rakkshasass
	-Taltecuhtli

Hero: Abstract class to Paladins, Warriors and Sorcerers (assumed that heroes can only teleport to lanes that where there is another hero or back to their own nexus)

Paladins: class that has the general attributes and constructor that any specific paladin characters will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Solonor_Thelandira
	-Sehanine_Moonbow
	-Skoraeus_Stonebones
Sorcerer: class that has the general attributes and constructor that any specific sorcerer characters will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Carl_Clittercold (renamed due to duplicate)
	-Rillifane_Rallathil 
	-Segojan_Earthcaller
	-Wkoraeus_Wtonebones (renamed due to duplicate)
Warrior: class that has the general attributes and constructor that any specific warrior characters will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Gaerdal_Ironhand
	-Mehanine_Sonnbow
	-Muamman_Duathall
	-Flandal_Steelskin

Items: abstract class to spells, potions, weapons and armory (items that can be stored in our backpack)

Spells: abstract class to fire, ice, and lightning spells, important to note that spells are one-use only
FireSpell: superclass that has general attributes and constructor that any specific fire spells will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Flame_Tornado
	-Breath_of_Fire (duplicate removed)
	-Heat_Wave(duplicate removed)
	-Lava_Commet
IceSpell: superclass that has general attributes and constructor that any specific ice spells will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Snow_Canon
	-Ice_Blade (duplicate removed)
	-Frost_Blizzard(duplicate removed)
	-Arctic_storm 
LightningSpell: superclass that has general attributes and constructor that any specific ice spells will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-LightningDagger (duplicate removed)
	-Thunder_Blast 
	-Spark_Needles(duplicate removed)
	-Electric_Arrows

Potion: superclass that has general attributes and constructor that any specific potion will invoke using the keyword super
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Healing_Potion
	-Strength_Potion
	-Magic_Potion
	-Luck_Elixir
	-Mermaid_Tears
	-Ambrosia 
Weaponry: superclass that has general attributes and constructor that any specific potion will invoke using the keyword super (assumed that only one weapon at a time can be held)
	Subclasses that allow for scalability should specific dragon characters have other effects/powers in updated versions of the game:
	-Sword
	-Bow
	-Scythe
	-Axe
	-Shield
	-TSwords
	-Dagger
Team: superclass to HeroTeam and MonsterTeam

Hero Team: team of user chosen heroes and appropriate functions to create, perform actions that are meant for entire team (e.g. health increase to heroes every round)

Monster Team: team of randomly generated monsters (spawning 3 every 8 rounds)

BackPack: to keep current list of items that heroes have (e.g. potions, spells, weapons and armory)

Buyable Interface: keeps a running list of items that can be bought, for objects that can be sold and whether an object can be bought

Quest driver: initializes a lot of pre game items (like heroes, etc).

Game Driver: simply starting point to lead to other drivers

Board Driver: deals with board movements (note: movement like hitting the wall is like bouncing back to the square you are currently in which means you get a chance at fighting in a common tile or going into the market, assume b goes back to their starting nexus only)
Board class: physical board
Board cell: tells us what kind of tile (market, common, etc) and allows actions such as knowing what is currently in cells (characters) and actions (special cell types give powers)
Lanes: physical lanes of board (top, mid, bot though numbered in this implementation)


