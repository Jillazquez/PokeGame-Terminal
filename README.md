# PokeAPI Hangman Game

I've developed a terminal-based game using the [PokeAPI](https://pokeapi.co/), where you can enjoy a classic hangman challenge. Your task is to guess a randomly selected Pokémon from the first 898, excluding those introduced in the Scarlet and Violet updates. Don't blame me; blame the PokeAPI. I leverage the PokeAPI to generate the random Pokémon for you. You're given only 6 attempts, and remember, you can't enter the full name or more than a single letter because the game will only consider the first letter.

![Example](https://imgur.com/OKOGiJ1.png)

Once you've successfully guessed the Pokémon, you'll need to fill in all the letters, and each letter should only be entered once. Even if the Pokémon's name contains duplicated letters, such as Torterra, 'r' will be displayed like this: `_r__rr_`.

### Quick Start Guide

To play this game, ensure that you have the Java Development Kit (JDK) installed on your computer. To start the game, navigate to the directory where you've downloaded the `.jar` file and run the following command:

```shell
java -jar "nameFile.jar"
