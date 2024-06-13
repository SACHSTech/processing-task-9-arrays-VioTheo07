import processing.core.PApplet;

/**
 * Sketch is a Processing-based game where the player controls a character
 * to avoid falling snowflakes. The game ends when the player loses all lives.
 * 
 * Author: Vio
 */
public class Sketch extends PApplet {

    final int maxSnowflakes = 35;
    final int playerSize = 25;
    final int snowflakeDiameter = 60;
    final int initialSnowflakeSpeed = 2;
    int snowflakeSpeed = initialSnowflakeSpeed;

    float[] snowflakeX = new float[maxSnowflakes];
    float[] snowflakeY = new float[maxSnowflakes];
    float[] snowflakeDiameterArray = new float[maxSnowflakes];
    boolean[] ballHideStatus = new boolean[maxSnowflakes];
    int playerX;
    int playerY;
    int lives = 3;
    boolean gameOver = false;
    int score = 0;
    int startTime;

    boolean moveUp = false;
    boolean moveDown = false;
    boolean moveLeft = false;
    boolean moveRight = false;

    /**
     * Sets the size of the game window.
     */
    public void settings() {
        size(550, 550);
    }

    /**
     * Initializes the game by setting up snowflakes and player position.
     */
    public void setup() {
        initializeSnowflakes();
        playerX = width / 2;
        playerY = 350;
        startTime = millis();
    }

    /**
     * Initializes snowflakes' positions and sizes.
     */
    void initializeSnowflakes() {
        for (int i = 0; i < maxSnowflakes; i++) {
            snowflakeX[i] = random(width);
            snowflakeY[i] = random(-height, 0);
            snowflakeDiameterArray[i] = snowflakeDiameter;
            ballHideStatus[i] = false;
        }
    }

    /**
     * Main game loop. Updates and draws game elements.
     */
    public void draw() {
        if (!gameOver) {
            background(167, 194, 204);
            updatePlayerPosition();
            drawPlayer();
            moveSnowflakes();
            drawSnowflakes();
            checkCollisions();
            drawLives();
            updateScore();
        } else {
            showGameOver();
        }
    }

    /**
     * Updates the player's position based on key inputs.
     */
    void updatePlayerPosition() {
        if (moveUp && playerY > 0) {
            playerY -= 3;
        }
        if (moveDown && playerY < height - playerSize) {
            playerY += 3;
        }
        if (moveLeft && playerX > 0) {
            playerX -= 3;
        }
        if (moveRight && playerX < width - playerSize) {
            playerX += 3;
        }
    }

    /**
     * Draws the player character.
     */
    void drawPlayer() {
        fill(0, 0, 255);
        ellipse(playerX + playerSize / 2, playerY + playerSize / 2, playerSize, playerSize);
    }

    /**
     * Moves the snowflakes down the screen and resets them if they move out of bounds.
     */
    void moveSnowflakes() {
        for (int i = 0; i < maxSnowflakes; i++) {
            if (!ballHideStatus[i]) { 
                snowflakeY[i] += snowflakeSpeed;
                if (snowflakeY[i] > height) {
                    resetSnowflake(i);
                }
            }
        }
        snowflakeSpeed = (int) constrain(initialSnowflakeSpeed + score / 10, 2, 10); // Increase snowflake speed gradually
    }

    /**
     * Draws the snowflakes.
     */
    void drawSnowflakes() {
        fill(255);
        for (int i = 0; i < maxSnowflakes; i++) {
            if (!ballHideStatus[i]) { 
                ellipse(snowflakeX[i], snowflakeY[i], snowflakeDiameterArray[i], snowflakeDiameterArray[i]);
            }
        }
    }

    /**
     * Checks for collisions between the player and snowflakes.
     */
    void checkCollisions() {
        for (int i = 0; i < maxSnowflakes; i++) {
            if (!ballHideStatus[i] && dist(playerX + playerSize / 2, playerY + playerSize / 2, snowflakeX[i], snowflakeY[i]) < playerSize / 2 + snowflakeDiameterArray[i] / 2) {
                resetSnowflake(i); 
                lives--;
                if (lives <= 0) {
                    gameOver = true;
                }
            }
        }
    }

    /**
     * Draws the player's remaining lives.
     */
    void drawLives() {
        fill(255, 0, 0);
        for (int i = 0; i < lives; i++) {
            int x = 20 + i * 30;
            int y = 30;
            beginShape();
            vertex(x, y + 20);
            bezierVertex(x - 15, y + 10, x - 15, y - 10, x, y);
            bezierVertex(x + 15, y - 10, x + 15, y + 10, x, y + 20);
            endShape(CLOSE);
        }
    }

    /**
     * Updates the player's score based on elapsed time.
     */
    void updateScore() {
        int currentTime = millis();
        int elapsedTime = currentTime - startTime;
        score = elapsedTime / 1000; 
        fill(255);
        textSize(20);
        textAlign(RIGHT, TOP);
        text("Score: " + score, width - 10, 10); 
    }

    /**
     * Displays the game over screen.
     */
    void showGameOver() {
        background(255);
        fill(0);
        textSize(50);
        textAlign(CENTER, CENTER);
        text("Game Over", width / 2, height / 2 - 30);
        textSize(30);
        text("Score: " + score, width / 2, height / 2 + 10);
        textSize(20);
        text("Press 'R' to restart", width / 2, height / 2 + 50);
    }

    /**
     * Handles key press events.
     */
    public void keyPressed() {
        if (key == 'w') {
            moveUp = true;
        }
        if (key == 's') {
            moveDown = true;
        }
        if (key == 'a') {
            moveLeft = true;
        }
        if (key == 'd') {
            moveRight = true;
        }
    }

    /**
     * Handles key release events.
     */
    public void keyReleased() {
        if (key == 'w') {
            moveUp = false;
        }
        if (key == 's') {
            moveDown = false;
        }
        if (key == 'a') {
            moveLeft = false;
        }
        if (key == 'd') {
            moveRight = false;
        }
        if (key == 'r' && gameOver) {
            resetGame();
        }
    }

    /**
     * Handles mouse press events.
     */
    public void mousePressed() {
        for (int i = 0; i < maxSnowflakes; i++) {
            if (!ballHideStatus[i] && dist(mouseX, mouseY, snowflakeX[i], snowflakeY[i]) < snowflakeDiameterArray[i] / 2) {
                resetSnowflake(i);
                break;
            }
        }
    }

    /**
     * Resets a snowflake's position and hide status.
     * @param i The index of the snowflake to reset
     */
    void resetSnowflake(int i) {
        snowflakeX[i] = random(width);
        snowflakeY[i] = random(-height, 0);
        snowflakeDiameterArray[i] = snowflakeDiameter;
        ballHideStatus[i] = false;
    }

    /**
     * Resets the game to its initial state.
     */
    void resetGame() {
        lives = 3;
        score = 0;
        gameOver = false;
        initializeSnowflakes();
        snowflakeSpeed = initialSnowflakeSpeed; 
        startTime = millis();
        moveUp = false;
        moveDown = false;
        moveLeft = false;
        moveRight = false;
    }

    /**
     * Main method to start the game.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        PApplet.main("Sketch");
    }
}
