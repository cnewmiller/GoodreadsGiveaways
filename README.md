# GoodreadsGiveaways
Uses Selenium WebDriver to automatically enter giveaways on GoodReads


This is a Java Selenium program which automatically enters giveaways for books on GoodReads.com.

How to use:
  -Download the "Runnable JAR" folder and extract it.
  
  -Download and install the most recent JDK for your OS: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
  
  -Make sure Chrome is installed on your machine: https://www.google.com/chrome/browser/desktop/index.html
  
  -In your GoodReads account, you should have just one address available to ship books to. I have no idea what will happen if you have more than one, but it'll probably crash if you have none.
  
  
  
  -Using the terminal or command line, navigate to wherever you extracted the Runnable JAR folder.
  
  -If you don't know how to do this, try clicking and dragging the folder to the "terminal" or "command line" application on your toolbar. If you still can't figure this out, ask someone for help. Don't worry, you're doing something simple using complicated tools.

-Type into the terminal/command line, without the quotes: "java -jar GoodreadsScript.jar"
 
-You will be prompted for your GoodReads email and password. This is so the script can log in as you.

-Fixed: your password will no longer show up as plaintext!

-You will also be prompted for the number of pages of giveaways it should enter (there are 30 books to a page). Enter it using digits (e.g., '1', '10', etc).

--NEW FEATURE!

   After winning a few books that I don't really want, and at a friend of mine's suggestion, I've made it so that you can enter giveaways by tag. This works because GoodReads will allow you to filter the giveaways by single tags (not multiple, as far as I can tell). So if you only want fantasy, science fiction, and art books, you can only enter giveaways for those tags. Note that this is a union operation, not an intersection. The way the script reads that input is like this:
   
    User: I want to enter fantasy, science fiction, and art books. I want 3 pages of fantasy, 2 pages of science fiction, and 1 page of art. These are tags I found on GoodReads, and I know already what sorts of tags are useable there. Here's how I'll format it so that the script can understand it.
   
   User types in: "fantasy,3,science-fiction,2,art,1"

-Let it run!


------------------ Using the Mac Startup Script ----------------------

I've included a .command file in the RunnableJar folder. This is a script for Mac OS that you can edit to include your username and your personal set of tags, then whenever you want to enter giveaways, double click the script, type your GoodReads password (it WON'T show up on the screen, and it won't be saved!), and the rest will run for you.

I've included a .bat file, for Windows users, but I don't have a Windows computer set up to test this on. No guarantees, but it should work.






