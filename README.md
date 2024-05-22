[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-718a45dd9cf7e7f842a935f5ebbe5719a5e09af4491e668f4dbf3b35d5cca122.svg)](https://classroom.github.com/online_ide?assignment_repo_id=15128333&assignment_repo_type=AssignmentRepo)
# Processing Task 9 - Arrays

## Learning Objectives
In this task, you will learn about defining arrays to animate a collection of objects.


## Step 1 - Lesson
Acquire the learning objectives by reviewing [this page in happycoding.io](https://happycoding.io/tutorials/processing/arrays)

## Step 2 - Task
Demonstrate your learning objectives by implementing the following:
  
### Level 2
Create a sketch that simulates snow falling. When the down arrow is pressed on the keyboard, the snow falls faster.  Likewise, the snow falls slower when the up arrow is pressed.  Note: instead of setting your x-values of the snowballs to be a function of the randomly generated y (see the example [in happycoding.io](https://happycoding.io/tutorials/processing/arrays), you can have a related array of x values for your snowflakes and initialize them to be at randoml generated locations (just like the y values).
  
### Level 3
Extend your snowfall progam by adding a blue player circle that is controlled by the user with the asdw keys.  (left, down, right , up).  Add a three squares at the top right of the screen to indicate player lives.  The player loses a life everytime it collides with snowflake.  The game ends when all lives are lost and the screen clears to white.
  
### Level 4
Extend the Level 3 program with mouse control such that while the player is controlled with the left hand (with keyboard), the mouse can be used to click on snowflakes and make them disappear if they are clicked on.  You may need to make the snowflakes bigger so they are easier to click on.    **HINT:** you will need to introduce an additional "ballHideStatus" related array that stores the state of each snowball.  The elements of this array will be defaulted to `false`. When a snowball is clicked on, the snowball's corresponding ballHideStatus will be set to `true` and the snowball will no longer be drawn to the screen.


## Submission
1. Commit and push your code to this repository
2. Take a screen recording of your work and upload it to the Google Classroom assignment post.
3. Javadoc comment all methods
4. Apply appropriate style and variable naming conventions.
