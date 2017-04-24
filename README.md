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

-You should change your password to use this script, because when you enter it in the terminal, it will show up as plaintext
[I briefly looked into input-masking, but I'll leave that to "issues to fix" for now]

-You will also be prompter for the number of pages of giveaways it should enter (there are 30 books to a page). Enter it using digits (e.g., '1', '10', etc).

-Let it run!
