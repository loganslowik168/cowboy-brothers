This project was completed in December of 2024 for UAH's CS321-02 SP24
Project authors:
* Ace Wheatley
* Joshua Logue
* Logan Slowik
* Matthew Reed


Read Me Text
This is built for JDK 22

You can double click the JAR file to run the program out of the box.

Comment: When loading JAR File it takes up to 5 seconds to load any level.
Step A: compile and run the program with all the class files (See 1)
Step B: click on the level select button to take you to level (See 3)
Step C: Read the Game Logic(See 12)
Step D: play each level from 1-5 (See 5)
Step E: (Optional) Replay any level (See 5)
Step F: maneuver back to the main menu and click the reset progress button
Step G: Go to the level select and try to play any level to see that the progress was reset
Step H: Exit program

1. This will take you to the Main Menu screen
   here you will click on one of the three buttons available(see 2)

2. MAIN MENU OPTIONS
* Quit Game Button
- This will exit the game when you are finished/ conversely you can hit the X on the window to quit the program

* Reset Progress Button
- This will reset your player's level progress

* Level Select Button
- This will change the display to the Level Select Menu show the levels that you can play

3. Level Select Menu
This has 6 buttons, the first 4 are the levels that are based on getting to the end of the map
The next button is for the boss level where you will complete it by defeating the boss
The last button is the back button that will take you back to the main menu
(see 4)

4. LEVEL SELECT OPTIONS
* Level 1 Logan
- This will switch to a gameplay display with the player at the start of the level

* Level 2 Matt
- This will switch to a gameplay display with the player at the start of the level

* Level 3 Ace
- This will switch to a gameplay display with the player at the start of the level

* Level 4 Josh
- This will switch to a gameplay display with the player at the start of the level

* Level 5 Boss
- This will switch to a gameplay display with the player at the start of the level

* Back
- This button will take you to the Main Menu display(See 1)

5. Game Play Display
This panel will have a loaded map from the level select menu
To leave this panel, you will either need to win(see 7), lose(see 8), or pause the game(see 9)

6. CONTROLS
* W Key
- This will make your charater jump while the character is on the ground
* D Key
- This will move the map to the left to simulate your character moving to the right
* A Key
- This will move the map to the right to simulate your character moving to the left
* Space Key
- This will fire a bullet in the direction you face while you still have ammo

7.  Win Conditions
to win the level you either need to touch the flag at the end of the level or to defeat the boss
Upon Win condition being met the game display will switch to the Win Menu(see 10) 

* Flag
- Using the controls(see 6) make the player collide with the flag at the end of the level

* Boss
- Using the controls(see 6) either shoot the boss until his health reaches zero or make the boss throw his bombs onto himself

8. Lose Condition
to lose the game you will either fall off the map or run out of health
Upon loss the display will switch to the Lose Menu(see 11)

* Fall off map
- Using the controls(See 6) walk the player in between the ground so they fall below the bottom of the screen 

* Out of health
- Let the player get hit by the enemy's bullet or the boss's bombs 3 times before death condition is met

9. Pause Menu
In this menu you will stop the gameplay from continuing, and have 3 button choices
* Retry Button
- Upon clicking this you will restart the level at the beginning like when you loaded it from level select

* Continue Button
- The display will switch back to the gameplay from the moment you paused

* Level Select Button
- Will return you to the level select menu(see 3)

10. Win Menu 
Win menu will say that you won and will have 2 buttons 

* Main Menu Button
- Will return you to the main menu(see 1)

* Next Level
- Will return you to the Level Select Menu(see 3)

11. Lose Menu
Win menu will say that you lost and will have 2 buttons

* Main Menu Button
- Will return you to the main menu(see 1)

* Next Level
- Will return you to the Level Select Menu(see 3)

12. Game Logic
For levels 1-4 you will start on the left end of the map. 
You need to traverse to the right end while jumping over the gaps in the ground and shooting the enemies.

For the Boss level you will start to the left of the boss.
In order to bring the boss down off of his saloon you will need to stand over the saloon when the boss throws dynamite in order to blow up the saloon.
The boss has 10 health and can be damaged by shooting him or making his own bombs explode on himself