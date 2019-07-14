# tinceptor_public
RULES :-

1) EACH PLAYER CAN SHOOT ONLY 1 BULLET TO A PARTICULAR OPPONENT PLAYER WITHIN THE ANGLE OF SIGHT OF PLAYER IN EACH SINGLE - TURN
2) EACH PLAYER CAN MOVE ONLY UNIT - DISTANCE ( = SPEED OF THE PLAYER ) IN EACH SINGLE - TURN
3) IN MOVING EACH UNIT - DISTANCE PLAYER WILL LOSE ENERGY = ENERGY - LOST - PER - STEP
4) DEATH OF PLAYER :-
    a) AS SOON AS ENERGY OF PLAYER ZEROES - DOWN, PLAYER DIES THAT IS GET REMOVED FROM GAME - ENVIRONMENT
    b) AS SOON AS PLAYER GETS HIT BY NO. OF BULLETS = HITS - TO - DIE, PLAYER DIES THAT IS REMOVED FROM GAME - ENVIRONMENT

ALGORITHMIC STEPS FOR GIVING SEQUENTIAL - INPUTS TO EXECUTE CODE :-

INPUT - FORMAT :-

1st - Line :- 
NO. OF TERRORISTS(m) AND COUNTER - TERRORISTS(n) respectively, separated by space 

2nd - Line :-
POSITION OF BOMB - SITE as ( x - coordinate followed by y - coordinate)

3rd - Line :-
ORDER OF PLAYING OF TERRORISTS

4th - Line :-
ORDER OF PLAYING OF COUNTER - TERRORISTS

5th - Line :-
STORAGE - TYPE ("array" or "list")

Next 7*m - Lines :-

1) PLAYER - TYPE OF i-th TERRORIST
2) STRATEGY OF i-th TERRORIST
3) ANGLE OF SIGHT OF i-th TERRORIST FROM LINE OF ORIENTATION
4) INITIAL POSITION OF i-th TERRORIST
5) ANGLE OF LINE OF ORIENTATION OF i-th TERRORITST FROM '+' X - AXIS
6) INITIAL - ENERGY - LEVEL OF i-th TERRORIST
7) SPEED ( DISTANCE PER MOVE ) OF i-th TERRORIST 

Next 7*n - Lines :-

1) PLAYER - TYPE OF i-th COUNTER - TERRORIST
2) STRATEGY OF i-th COUNTER - TERRORIST
3) ANGLE OF SIGHT OF i-th COUNTER - TERRORIST FROM LINE OF ORIENTATION
4) INITIAL POSITION OF i-th COUNTER - TERRORIST
5) ANGLE OF LINE OF ORIENTATION OF i-th COUNTER - TERRORITST FROM '+' X - AXIS
6) INITIAL - ENERGY - LEVEL OF i-th COUNTER - TERRORIST
7) SPEED ( DISTANCE PER MOVE ) OF i-th COUNTER - TERRORIST

SAMPLE - TSET - CASES :-

1) SAMPLE - INPUT :-
1 1
0 0
circular
circular
list
aggressive
bomb
90
3 0
180
10
1
cautious
bomb
90
4 0
180
10
1
SAMPLE - OUTPUT :-
COUNTER - TERRORISTS WON

2) SAMPLE - INPUT :-
1 1
0 0 
circular
circular
list
aggressive 
bomb 
50.0
1 0 
180.0 
10 
1 
cautious 
bomb 
50.0
0 1
315.0 
10 
1
SAMPLE - OUTPUT :-
TERRORISTS WON

3) SAMPLE - INPUT :-
1 1
0 0
circular
circular
array
aggressive
bomb
90
3 0
180
10
1
cautious
bomb
90
4 0
180
10
1
SAMPLE - OUTPUT :-
COUNTER - TERRORISTS WON

4) SAMPLE - INPUT :-
1 1
0 0 
circular
circular
array
aggressive 
bomb 
50.0
1 0 
180.0 
10 
1 
cautious 
bomb 
50.0
0 1
315.0 
10 
1
SAMPLE - OUTPUT :-
TERRORISTS WON

DETAIL DESCRIPTION :

There are two teams: “Terrorists” and “Counter-Terrorists”. The aim of the terrorists is to go to a special pre-determined site called as “Bomb”. The aim of the counter-terrorists is to ensure that none of the terrorists can go to the site called “Bomb”. Because playing this game manually is very boring, you have an AI Engine that automatically plays this game for you. There
are three kinds of players in both teams possible:

 AggressivePlayers, who tend to run fast. Their energy level reduces
by 2 at every step of move. They need to be hit twice to be dead.

 CautiousPlayers, who tend to go very slow. Their energy level reduces by 1
at every step of move. They need to be hit once to be dead.

 BlindPlayer, who run very fast, and do not observe around at
all. Their energy level reduces by 3 at every step. They need to be hit 5 times to be dead.

Each player of counter-terrorists selects an opponent player and goes to kill the same. A player can have
any of three strategies. It is assumed that everyone knows each other’s position.

 Go to nearest terrorist

 Go to a random terrorist

 Go to a terrorist ‘ahead’ in the map

A terrorist has all the strategies of the counter-terrorists, with one additional strategy: Go to bomb. Any
number of terrorists can select any counter-terrorist and vice versa. A terrorist can be the target of any
number of counter-terrorist and vice versa. A bomb may be aimed by any number of terrorists. The
strategies are constant, however the selected opponent will change as the players move. So the nearest
terrorist strategy followed by a player will remain as it is, however the specific terrorist will change as the
terrorists move around.

The game is sequential in nature. All players make a move one after the other. Hence the order in which
the players move can be critical to the game. The order may be circular (one chance to every player in the
same order by which they entered the arena), by energy level (most fit player moves first), by success (the
player who killed the maximum opponents moves first). However, first a terrorist moves, then a counter-
terrorist, then a terrorist and so on, till both the sets expire, and a new turn starts. There is a single order.

Assume function ‘AImove’ exists for each player of every type that takes the positions of all players and
goal, and makes a move as per some computation. Also assume that a function site exists for every player,
that returns the player currently in the line of sight, and a fire action on the player may kill the other
player. If the other player is an opponent, the opponent must be killed.

The class InputHandler takes the number of two opponents (terrorists and counter-terrorists) as input,
along with their types (aggressive, cautious, blind) and selection strategies (nearest, random, ahead,
bomb). The class also takes the playing order for the two teams (circular, energy level, success) and the
mechanism by which the storage will happen (array, linked list, sorted/uunsorted). Everything is stored in
the class Environment that has all information related to the game. The class GameEngine can access
environment and has a function play to play the game. The function has a loop till the game completes,
selects a player, computes the goal as per strategy, computes the move using AI logic given, fires (if
necessary), and updates the changes in the environment (success metric, positon, and killing of player).
