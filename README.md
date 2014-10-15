README for Group 20-
Dylan Macdonald, Jarred Hone, Chris McIntosh, Greg Oswald, Venkata Peesapali

How to Run:
On seperate instances of the JVM:

Start the program,
Open a server (The local IP will be grabbed)

Once the server is started on one computer, open the program on another
Click new game, then select 'Join Server'
Type in the local ip of the computer the server is hosted on and it should let you in. It failed if the console throws a connectionExeption, the game will still let you through


Obviously a lot in this game is buggy or unimplemented. There is a LOT in the code that has not been tied into the game. Game view on network will not sync up, even though the network controls the turns system correctly. There are numerous bugs in the UI, and in the Graphics Rendering implementations. If the above steps are followed, you should get into what is implemented on the front end, however.


CONTROLS:
The controls are fairly simple,
arrow keys will move the cursor, then ENTER will set the character onto the cursor. You can do this twice, then it switches to the next persons turn. Obviously graphics are broken and random playerCharacters keep spawning all around the map so just disregard them.
