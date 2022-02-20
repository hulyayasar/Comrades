# Comrades
This project is made by Bayram Furkan Bayar, Hulya Yasar, Muhammed Erdem Calikoglu and Spencer Samra

Guess It! will be a game that purely depends on the user trying to guess the number selected depending on the different levels of difficulty. 
For instance, the easy mode will be in a range from 0 to 100, moderate from 0 to 500, and hard will be from 0 to 1000 (bounds are subject to change depending on how testing goes). 
In each guess, the computer will give hints to the user depending on how close the guess was to the selected number. 
The user will have a limited amount of attempts to guess the number, if the user cannot make a right guess until the last attempt the program will narrow the bounds down 
and will try to help the user as much as it can. This number of attempts will change according to the level of difficulty. In each game, the program will keep counting 
the number of guesses and the time elapsed. All relevant information will be stored in a database, on which the username, amount of guesses, and the time elapsed. 
This database will be used to generate a leaderboard at the beginning of the program and will get refreshed at the end of the game.
