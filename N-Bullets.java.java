import java.awt.Color;
import java.util.Random;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.CircleImage;
import javalib.worldimages.OutlineMode;
import javalib.worldimages.TextImage;
import javalib.worldimages.WorldImage;
import tester.*;


// to represents a GamePiece
interface IGamePiece {
  WorldScene draw();

  // Takes the distance between the center of two GamePieces
  double distance(AGamePiece other);

  // Determines if two game pieces are in contact with eachother
  boolean isContacting(AGamePiece other);

  // Creates each of the bullets from an explosion
  ILoGamePiece explodeHelp(int newBullets, int og, int angle);

  // Calls explode help with its baseline values
  ILoGamePiece explode(ILoGamePiece list);

  // Determines if a bullet is collided with a Game Piece
  boolean collideBullet(AGamePiece b);

  // Determines if a Ship has collided with a given Game Piece
  boolean collideShip(AGamePiece s);
}

// to represent a GamePiece 
abstract class AGamePiece {

  double x;
  double y;
  int radius;
  Color color;
  double xSpeed;
  double ySpeed;

  AGamePiece(double x, double y, int radius, Color color, double xSpeed, double ySpeed) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.color = color;
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  /*
   * Template:
   * 
   * Fields:
   * this.x ... double
   * this.y ... double
   * this.radius ... int
   * this.color ... Color
   * this.xSpeed ... double
   * this.ySpeed ... double
   * 
   * Methods:
   * this.move() ... GamePiece
   * this.distance() ... double
   * this.isContacting(GamePiece other) ... GamePiece
   * this.draw() ... WorldImage
   * this.collideBullet(GamePiece b) ... boolean
   * this.collideShip(GamePiece s) ... boolean
   * this.explode(ILoGamePiece gamePieces) ... ILoGamePiece
   */
  
  // Moves a IGamePiece
  AGamePiece move() {
    /*
     * Template: same as class template
     */
    return this;
  }
 
  // Takes the distance between the center of two GamePieces
  double distance(AGamePiece other) {
    /*
     * Template: same as AGamePiece plus
     * 
     * Parameters:
     * other ... AGamePiece
     * 
     * Methods for Parameters
     * 
     * other.move() ... GamePiece
     * other.distance() ... double
     * other.isContacting(GamePiece other) ... GamePiece
     * other.draw() ... WorldImage
     * other.collideBullet(GamePiece b) ... boolean
     * other.collideShip(GamePiece s) ... boolean
     * other.explode(ILoGamePiece gamePieces) ... ILoGamePiece
     * 
     */
    double tempx = (this.x - other.x) * (this.x - other.x);
    double tempy = (this.y - other.y) * (this.y - other.y);
    return Math.sqrt(tempx + tempy);
  }

  // Determines if two game pieces are in contact with eachother
  boolean isContacting(AGamePiece other) {
    /*
     * Template: same as AGamePiece plus
     * 
     * Parameters:
     * other ... AGamePiece
     * 
     * Methods for Parameters
     * 
     * other.move() ... GamePiece
     * other.distance() ... double
     * other.isContacting(GamePiece other) ... GamePiece
     * other.draw() ... WorldImage
     * other.collideBullet(GamePiece b) ... boolean
     * other.collideShip(GamePiece s) ... boolean
     * other.explode(ILoGamePiece gamePieces) ... ILoGamePiece
     * 
     */
    return (this.distance(other) < this.radius + other.radius);
  }

  // draw this ball as a circular image in the provided scene
  WorldImage draw() {
    /*
     * Template: same as class template
     */
    return new CircleImage(this.radius, OutlineMode.SOLID, this.color);
  }

  // Determines if a bullet is collided with a Game Piece
  boolean collideBullet(AGamePiece b) {
    /*
     * Template: same as AGamePiece plus
     * 
     * Parameters:
     * b ... AGamePiece
     * 
     * Methods for Parameters
     * 
     * b.move() ... GamePiece
     * b.distance() ... double
     * b.isContacting(GamePiece other) ... GamePiece
     * b.draw() ... WorldImage
     * b.collideBullet(GamePiece b) ... boolean
     * b.collideShip(GamePiece s) ... boolean
     * b.explode(ILoGamePiece gamePieces) ... ILoGamePiece
     * 
     */
    return false;
  }

  // Determines if a Ship has collided with a given Game Piece
  boolean collideShip(AGamePiece s) {
    /*
     * Template: same as AGamePiece plus
     * 
     * Parameters:
     * a ... AGamePiece
     * 
     * Methods for Parameters
     * 
     * s.move() ... GamePiece
     * s.distance() ... double
     * s.isContacting(GamePiece other) ... GamePiece
     * s.draw() ... WorldImage
     * s.collideBullet(GamePiece b) ... boolean
     * s.collideShip(GamePiece s) ... boolean
     * s.explode(ILoGamePiece gamePieces) ... ILoGamePiece
     * 
     */
    return false;
  }

  // Calls explode help with its baseline values
  ILoGamePiece explode(ILoGamePiece gamePieces) {
    /*
     * Template: same as AGamePiece plus
     * 
     * Parameters:
     * gamePieces ... ILoGamePiece
     * 
     * Methods for Parameters
     * 
     * gamePieces.buildShipList(int numLeft) ... ILoGamePiece
     * gamePieces.moveAll() ... ILoGamePiece
     * gamePieces.drawAll() ... WorldImage
     * gamePieces.append(ILoGamePiece items) ... ILoGamePiece
     * gamePieces.collideBullet(GamePiece b) ... boolean
     * gamePieces.collideShip(GamePiece s) ... boolean
     * gamePieces.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
     * gamePieces.pop() ... ILoGamePiece
     * gamePieces.pop(AGamePiece item) ... ILoGamePiece
     */
    return new ConsLoGamePiece(this, new MtLoGamePiece());
  }

  public boolean isBullet() {
    /*
    * Template: same as class template
    */
    return false;
  }
  
}

// to represent a Ship
class Ship extends AGamePiece {

  Ship(double x, double y, int radius, double xSpeed, double ySpeed) {
    super(x, y, radius, Color.CYAN, xSpeed, ySpeed);
  }

  /*
   * Template:
   * 
   * Fields:
   * this.x ... double
   * this.y ... double
   * this.radius ... int
   * this.color ... Color
   * this.xSpeed ... double
   * this.ySpeed ... double
   * 
   * Methods:
   * this.move() ... GamePiece
   * this.distance() ... double
   * this.isContacting(GamePiece other) ... GamePiece
   * this.draw() ... WorldImage
   * this.collideBullet(GamePiece b) ... boolean
   * this.collideShip(GamePiece s) ... boolean
   * this.explode(ILoGamePiece gamePieces) ... ILoGamePiece
   */
  
  // Moves a Ship by the specified parameters
  AGamePiece move() {
    /*
     * Template: same as class template
     */
    return new Ship(
      this.x + this.xSpeed, 
      this.y + this.ySpeed, 
      this.radius, 
      this.xSpeed,
      this.ySpeed);
  }

  // Determines if a bullet is collided with a Game Piece
  public boolean collideBullet(AGamePiece b) {
    /*
     * Template: same as class template plus
     * 
     * Parameters:
     * b ... AGamePiece
     * 
     * Methods for Parameters
     * 
     * b.move() ... GamePiece
     * b.distance() ... double
     * b.isContacting(GamePiece other) ... GamePiece
     * b.draw() ... WorldImage
     * b.collideBullet(GamePiece b) ... boolean
     * b.collideShip(GamePiece s) ... boolean
     * b.explode(ILoGamePiece gamePieces) ... ILoGamePiece
     */
    return this.isContacting(b);
  }

  // Calls explode help with its baseline values
  public ILoGamePiece explode(ILoGamePiece bullets) {
    /*
     * Template: same as class template plus
     * 
     * Parameters:
     * bullets ... ILoGamePiece
     * 
     * Methods for Parameters
     * 
     * bullets.buildShipList(int numLeft) ... ILoGamePiece
     * bullets.moveAll() ... ILoGamePiece
     * bullets.drawAll() ... WorldImage
     * bullets.append(ILoGamePiece items) ... ILoGamePiece
     * bullets.collideBullet(GamePiece b) ... boolean
     * bullets.collideShip(GamePiece s) ... boolean
     * bullets.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
     * bullets.pop() ... ILoGamePiece
     * bullets.pop(AGamePiece item) ... ILoGamePiece
     */
    if (bullets.collideShip(this)) {
      return new MtLoGamePiece();
    }
    return new ConsLoGamePiece(this, new MtLoGamePiece());
  }

}

// to represent a Bullet
class Bullet extends AGamePiece {
  int bulletValue;

  Bullet(double x, double y, int radius, double xSpeed, double ySpeed, int bulletValue) {
    super(x, y, radius, Color.PINK, xSpeed, ySpeed);
    this.bulletValue = bulletValue;
  }
  
  /*
   * Template:
   * 
   * Fields:
   * this.x ... double
   * this.y ... double
   * this.radius ... int
   * this.color ... Color
   * this.xSpeed ... double
   * this.ySpeed ... double
   * this.bulletValue ... int
   * 
   * Methods:
   * this.move() ... GamePiece
   * this.distance() ... double
   * this.isContacting(GamePiece other) ... GamePiece
   * this.draw() ... WorldImage
   * this.collideBullet(GamePiece b) ... boolean
   * this.collideShip(GamePiece s) ... boolean
   * this.explode(ILoGamePiece gamePieces) ... ILoGamePiece
   */

  // Return a new ball created by moving this ball by its speed.
  AGamePiece move() {
    /*
     * Template: same as class template
     */
    return new Bullet(
      this.x + this.xSpeed, 
      this.y + this.ySpeed, 
      this.radius, 
      this.xSpeed,
      this.ySpeed, 
      this.bulletValue);
  }
  
  // returns the same radius if it is 10 or increases it by 2
  int newRadius() {
    /*
     * Template: same as class template
     */
    if (this.radius == 10) {
      return this.radius;
    }
    else {
      return this.radius + 2;
    }
  }

  // returns a list of new exploded bullets with different trajectories
  ILoGamePiece explodeHelp(int newBullets, int og, double angle) {
    /*
     * Template: same as class template
     */
    double xx;
    double yy;
    if (angle == 0) {
      xx = 4;
      yy = 0;
    }
    else if (angle == (Math.PI / 2)) {
      xx = 0;
      yy = -4;
    }
    else if (angle == (Math.PI)) {
      xx = -4;
      yy = 0;
    }
    else if (angle == (3 * Math.PI / 2)) {
      xx = 0;
      yy = 4;
    }
    else if (angle > 0 && angle < Math.PI / 2) {
      xx = 4 * Math.cos(angle);
      yy = -4 * Math.sin(angle);
    }
    else if (angle > Math.PI / 2 && angle < Math.PI) {
      xx = -4 * Math.cos(Math.PI - angle);
      yy = -4 * Math.sin(Math.PI - angle);
    }
    else if (angle > Math.PI && angle < Math.PI * 3 / 2) {
      xx = -4 * Math.cos(angle - Math.PI);
      yy = 4 * Math.sin(angle - Math.PI);
    }
    else {
      xx = 4 * Math.cos(Math.PI * 2 - angle);
      yy = 4 * Math.sin(Math.PI * 2 - angle);
    }

    if (newBullets > 0) {
      return new ConsLoGamePiece(
          new Bullet(this.x, this.y, this.newRadius(), xx, yy, this.bulletValue + 1),
          this.explodeHelp(newBullets - 1, og, angle + ((2 * Math.PI) / this.bulletValue)));
    }
    else {
      return new MtLoGamePiece();
    }
  }

  // Calls explode help with its baseline values
  public ILoGamePiece explode(ILoGamePiece ships) {
    /*
     * Template: same as class template plus
     * 
     * Parameters:
     * ships ... ILoGamePiece
     * 
     * Methods for Parameters
     * 
     * ships.buildShipList(int numLeft) ... ILoGamePiece
     * ships.moveAll() ... ILoGamePiece
     * ships.drawAll() ... WorldImage
     * ships.append(ILoGamePiece items) ... ILoGamePiece
     * ships.collideBullet(GamePiece b) ... boolean
     * ships.collideShip(GamePiece s) ... boolean
     * ships.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
     * ships.pop() ... ILoGamePiece
     * ships.pop(AGamePiece item) ... ILoGamePiece
     */
    if (ships.collideBullet(this)) {
      return this.explodeHelp(this.bulletValue, this.bulletValue, 0);
    }

    return new ConsLoGamePiece(this, new MtLoGamePiece());
  }

  // Determines if a Ship has collided with a given Game Piece
  public boolean collideShip(AGamePiece s) {
    /*
     * Template: same as class template plus
     * 
     * Parameters:
     * s ... AGamePiece
     * 
     * Methods for Parameters
     * 
     * s.move() ... GamePiece
     * s.distance() ... double
     * s.isContacting(GamePiece other) ... GamePiece
     * s.draw() ... WorldImage
     * s.collideBullet(GamePiece b) ... boolean
     * s.collideShip(GamePiece s) ... boolean
     * s.explode(ILoGamePiece gamePieces) ... ILoGamePiece
     * 
     */
    return this.isContacting(s);
  }
}

// to represent Big Bang
class GameWorld extends World {
  
  ILoGamePiece ships;
  ILoGamePiece bullets;
  ILoGamePiece gamePieces;
  int shipsDestroyed;
  int bulletsLeft;
  int ticks;
  Random rand = new Random();

  GameWorld(ILoGamePiece s) {
    this.ships = s;
    this.bullets = new MtLoGamePiece();
    this.gamePieces = this.bullets.append(this.ships);
    this.bulletsLeft = 10;
    this.ticks = ticks;
    this.shipsDestroyed = shipsDestroyed;
  }

  GameWorld(ILoGamePiece s, ILoGamePiece b, int ticks, int bulletsLeft) {
    this.ships = s;
    this.bullets = b;
    this.ticks = ticks;
    this.gamePieces = this.bullets.append(this.ships);
    this.bulletsLeft = bulletsLeft;
    this.shipsDestroyed = shipsDestroyed;
  }

  /*
   * Template:
   * 
   * Fields:
   * this.ships ... ILoGamePiece
   * this.bullets ... ILoGamePiece
   * this.gamePieces ... ILoGamePiece
   * this.shipsDesroyed ... int
   * this.bulletsleft ... int
   * this.ticks ... int
   * 
   * Methods:
   * this.onTick() ... World
   * this.addShips() ... ILoGamePiece
   * this.onKeyEvent() ... World
   * this.makeScene() ... World
   *
   * Methods for fields:
   * s.buildShipList(int numLeft) ... ILoGamePiece
   * s.moveAll() ... ILoGamePiece
   * s.drawAll() ... WorldImage
   * s.append(ILoGamePiece items) ... ILoGamePiece
   * s.collideBullet(GamePiece b) ... boolean
   * s.collideShip(GamePiece s) ... boolean
   * s.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
   * s.pop() ... ILoGamePiece
   * s.pop(AGamePiece item) ... ILoGamePiece
   * b.buildShipList(int numLeft) ... ILoGamePiece
   * b.moveAll() ... ILoGamePiece
   * b.drawAll() ... WorldImage
   * b.append(ILoGamePiece items) ... ILoGamePiece
   * b.collideBullet(GamePiece b) ... boolean
   * b.collideShip(GamePiece s) ... boolean
   * b.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
   * b.pop() ... ILoGamePiece
   * b.pop(AGamePiece item) ... ILoGamePiece
   * gamePieces.moveAll() ... ILoGamePiece
   * gamePieces.drawAll() ... WorldImage
   * gamePieces.append(ILoGamePiece items) ... ILoGamePiece
   * gamePieces.collideBullet(GamePiece b) ... boolean
   * gamePieces.collideShip(GamePiece s) ... boolean
   * gamePieces.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
   * gamePieces.pop() ... ILoGamePiece
   * gamePieces.pop(AGamePiece item) ... ILoGamePiece
   *
   */
  
  // progresses the world by one tick
  @Override
  public World onTick() {
    /*
     * Template: same as class template
     */
    if (this.bulletsLeft <= 0) {
      if (!this.gamePieces.containsBullet()) {
        return this.endOfWorld("Game over");
      }
      return new GameWorld(
          this.addShips().explodeAll(this.bullets).moveAll(), 
          new MtLoGamePiece(),
          this.ticks + 1, 
          this.bulletsLeft);
    }
    return new GameWorld(
        this.addShips().explodeAll(this.bullets).moveAll(), 
        this.bullets.explodeAll(this.ships).moveAll(), 
        this.ticks + 1, 
        this.bulletsLeft);

  }

  // Returns a list of 1-3 new ships appeneded onto the previous ship list
  ILoGamePiece addShips() {
    /*
     * Template: same as class template
     */
    if (this.ticks % 28 == 0) {
      return this.ships.append(new MtLoGamePiece().buildShipList(new Random().nextInt(3) + 1));
    }
    return this.ships;
  }

  // Shoots a bullet when a key is pressed
  public World onKeyEvent(String key) {
    /*
     * Template: same as class template
     */
    if (this.bulletsLeft <= 0) {
      return new GameWorld(
          this.ships, 
          this.bullets, 
          this.ticks, 
          this.bulletsLeft); 
    }
    
    return new GameWorld(
        this.ships, 
        new ConsLoGamePiece(new Bullet(250.0, 295.0, 2, 0.0, -4.0, 2), this.bullets), 
        this.ticks, 
        this.bulletsLeft - 1); 

  }

  // Draws text keeping track of the bullets left and ships destroyed
  @Override
  public WorldScene makeScene() {
    /*
     * Template: same as class template
     */
    return gamePieces.drawAll()
        .placeImageXY(new TextImage(
            "Bullets left: " + Integer.toString(bulletsLeft) + "; ships destroyed: all of them", 13,
            Color.BLACK), 250, 21);
  }
}

// to represent a ship 
class RandomShip {
  
  // Creates a new ship with a new random y value and the side that it will appear on 
  public AGamePiece create() {
    int startPos = new Random().nextInt(2);
    return new Ship(
        new Utils().chooseXPos(startPos), 
        (double)new Random().nextInt(300 - 86) + 43, 
        10, 
        new Utils().chooseDir(startPos) * 4.0, 
        0.0);
  }
}

// to represent a list of Game Pieces
interface ILoGamePiece {
  
  // builds the specified amount of ships in a list
  ILoGamePiece buildShipList(int numLeft);

  // moves all of the GamePieces in a list
  ILoGamePiece moveAll();

  // draws all of the GamePieces in a list
  WorldScene drawAll();

  // appends given ILoGamePiece to the called ILoGamePiece
  ILoGamePiece append(ILoGamePiece items);

  // calls explode on every item in the list
  ILoGamePiece explodeAll(ILoGamePiece bullets);

  // removes the first item off of the list
  ILoGamePiece pop();

  // removes all instances of item from the list
  ILoGamePiece pop(AGamePiece item);

  // determines if the bullet collided with the given gamepiece
  boolean collideBullet(AGamePiece b);

  // determines if the ship collided with the given gamepiece
  boolean collideShip(AGamePiece b);

  // determines if there is a bullet in this list
  boolean containsBullet();
}

// representing a empty list of gamepieces
class MtLoGamePiece implements ILoGamePiece {
  
  
  // builds the specified amount of ships in a list
  public ILoGamePiece buildShipList(int numLeft) {
    if (numLeft > 1) {
      return new ConsLoGamePiece(new RandomShip().create(), this.buildShipList(numLeft - 1));
    }
    else {
      return new ConsLoGamePiece(new RandomShip().create(), this);
    }
  }

  // returns a MtLoGamePieces
  public ILoGamePiece moveAll() {
    return this;
  }

  // draws the blank world scene
  public WorldScene drawAll() {
    return new WorldScene(500, 300);
  }

  // returns the given list of items
  public ILoGamePiece append(ILoGamePiece items) {
    return items;
  }
  
  // returns an empty list of gamepieces
  public ILoGamePiece explodeAll(ILoGamePiece gamePieces) {
    return this;
  }
  
  // returns an empty list of gamepieces
  public ILoGamePiece pop() {
    return this;
  }

  // returns an empty list of gamepieces
  public ILoGamePiece pop(AGamePiece item) {
    return this;
  }

  // returns an empty list of gamepieces
  public ILoGamePiece removeAll(ILoGamePiece items) {
    return items;
  }

  // returns false
  public boolean collideBullet(AGamePiece b) {
    return false;
  }

  // returns false
  public boolean collideShip(AGamePiece s) {
    return false;
  }

  public boolean containsBullet() {
    return false;
  }
}

// to represent an non-empty list of gamepieces
class ConsLoGamePiece implements ILoGamePiece {
  AGamePiece first;
  ILoGamePiece rest;

  ConsLoGamePiece(AGamePiece first, ILoGamePiece rest) {
    this.first = first;
    this.rest = rest;
  }
  
  /*
   * Template:
   * 
   * Fields:
   * this.first ... AGamePiece
   * this.rest ... ILoGamePiece
   * 
   * Methods:
   * this.buildShipList(int numLeft) ... ILoGamePiece
   * this.moveAll() ... ILoGamePiece
   * this.drawAll() ... WorldImage
   * this.append(ILoGamePiece items) ... ILoGamePiece
   * this.collideBullet(GamePiece b) ... boolean
   * this.collideShip(GamePiece s) ... boolean
   * this.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
   * this.pop() ... ILoGamePiece
   * this.pop(AGamePiece item) ... ILoGamePiece
   *
   * Methods for fields:
   * first.move() ... GamePiece
   * first.distance() ... double
   * first.isContacting(GamePiece other) ... GamePiece
   * first.draw() ... WorldImage
   * first.collideBullet(GamePiece b) ... boolean
   * first.collideShip(GamePiece s) ... boolean
   * first.explode(ILoGamePiece gamePieces) ... ILoGamePiece
   * rest.buildShipList(int numLeft) ... ILoGamePiece
   * rest.moveAll() ... ILoGamePiece
   * rest.drawAll() ... WorldImage
   * rest.append(ILoGamePiece items) ... ILoGamePiece
   * rest.collideBullet(GamePiece b) ... boolean
   * rest.collideShip(GamePiece s) ... boolean
   * rest.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
   * rest.pop() ... ILoGamePiece
   * rest.pop(AGamePiece item) ... ILoGamePiece
   */

  // builds the specified amount of ships in a list
  public ILoGamePiece buildShipList(int numLeft) {
    /*
     * Template: same as class template
     */
    if (numLeft > 1) {
      return new ConsLoGamePiece(new RandomShip().create(), this.buildShipList(numLeft - 1));
    }
    else {
      return new ConsLoGamePiece(new RandomShip().create(), this);
    }
  }

  // moves all of the GamePieces in a list
  public ILoGamePiece moveAll() {
    /*
     * Template: same as class template
     */
    if (this.first.x < 0 || this.first.x > 500) {
      return this.rest.moveAll();
    }
    return new ConsLoGamePiece(this.first.move(), this.rest.moveAll());
  }

  // draws all of the GamePieces in a list
  public WorldScene drawAll() {
    /*
     * Template: same as class template
     */
    return this.rest.drawAll().placeImageXY(this.first.draw(), (int) this.first.x,
        (int) this.first.y);
  }

  // appends given ILoGamePiece to the called ILoGamePiece
  public ILoGamePiece append(ILoGamePiece items) {
    /*
     * Template: same as class template plus
     * 
     * Parameters:
     * items ... ILoGamePiece
     * 
     * Methods for Parameters
     * 
     * items.buildShipList(int numLeft) ... ILoGamePiece
     * items.moveAll() ... ILoGamePiece
     * items.drawAll() ... WorldImage
     * items.append(ILoGamePiece items) ... ILoGamePiece
     * items.collideBullet(GamePiece b) ... boolean
     * items.collideShip(GamePiece s) ... boolean
     * items.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
     * items.pop() ... ILoGamePiece
     * items.pop(AGamePiece item) ... ILoGamePiece
     */
    return new ConsLoGamePiece(this.first, this.rest.append(items));
  }

  // calls explode on every item in the list
  public ILoGamePiece explodeAll(ILoGamePiece otherType) {
    /*
     * Template: same as class template plus
     * 
     * Parameters:
     * otherType ... ILoGamePiece
     * 
     * Methods for Parameters
     * 
     * otherType.buildShipList(int numLeft) ... ILoGamePiece
     * otherType.moveAll() ... ILoGamePiece
     * otherType.drawAll() ... WorldImage
     * otherType.append(ILoGamePiece items) ... ILoGamePiece
     * otherType.collideBullet(GamePiece b) ... boolean
     * otherType.collideShip(GamePiece s) ... boolean
     * otherType.explodeAll(ILoGamePiece gamePieces) ... ILoGamePiece
     * otherType.pop() ... ILoGamePiece
     * otherType.pop(AGamePiece item) ... ILoGamePiece
     */
    return (this.first.explode(otherType).append(this.rest.explodeAll(otherType)));
  }

  // determines if the bullet collided with the given gamepiece
  public boolean collideBullet(AGamePiece b) {
    /*
     * Template: same as class template plus
     * 
     * Parameters:
     * b ... AGamePiece
     * 
     * Methods for Parameters
     * 
     * b.move() ... GamePiece
     * b.distance() ... double
     * b.isContacting(GamePiece other) ... GamePiece
     * b.draw() ... WorldImage
     * b.collideBullet(GamePiece b) ... boolean
     * b.collideShip(GamePiece s) ... boolean
     * b.explode(ILoGamePiece gamePieces) ... ILoGamePiece
     * 
     */
    if (this.first.collideBullet(b)) {
      return true;
    }
    else {
      return this.rest.collideBullet(b);
    }
  }

  // determines if the bullet collided with the given gamepiece
  public boolean collideShip(AGamePiece s) {
    /*
     * Template: same as class template plus
     * 
     * Parameters:
     * s ... AGamePiece
     * 
     * Methods for Parameters
     * 
     * s.move() ... GamePiece
     * s.distance() ... double
     * s.isContacting(GamePiece other) ... GamePiece
     * s.draw() ... WorldImage
     * s.collideBullet(GamePiece b) ... boolean
     * s.collideShip(GamePiece s) ... boolean
     * s.explode(ILoGamePiece gamePieces) ... ILoGamePiece
     * 
     */
    if (this.first.collideShip(s)) {
      return true;
    }
    else {
      return this.rest.collideShip(s);
    }
  }

  // removes the first item off of the list
  public ILoGamePiece pop() {
    /*
     * Template: same as class template
     */
    return this.rest;
  }

  // removes all instances of item from the list
  public ILoGamePiece pop(AGamePiece item) {
    /*
     * Template: same as class template plus
     * 
     * Parameters:
     * item ... AGamePiece
     * 
     * Methods for Parameters
     * 
     * items.move() ... GamePiece
     * items.distance() ... double
     * items.isContacting(GamePiece other) ... GamePiece
     * items.draw() ... WorldImage
     * items.collideBullet(GamePiece b) ... boolean
     * items.collideShip(GamePiece s) ... boolean
     * items.explode(ILoGamePiece gamePieces) ... ILoGamePiece
     * 
     */
    if (this.first.equals(item)) {
      return this.rest.pop();
    }
    else {
      return new ConsLoGamePiece(this.first, this.rest.pop());
    }
  }
  
  // determines the first is a bullet
  public boolean containsBullet() {
    /*
     * Template: same as class template plus
     */

    if (this.first.isBullet()) {
      return true;
    } else {
      return this.rest.containsBullet();
    }
  }
}

// examples class
class ExamplesMyWorldProgram {

  ILoGamePiece ships5 = new MtLoGamePiece().buildShipList(new Random().nextInt(3) + 1);
  
  
  
  World w = new GameWorld(ships5);
  
  Ship ship0 = new Ship(600.0, 250.0, 5, 4, 4);
  Ship ship1 = new Ship(250.0, 250.0, 5, 4, 4);
  Ship ship1moved = new Ship(254.0, 254.0, 5, 4, 4);
  Ship ship2 = new Ship(100.0, 200.0, 3, 10, 2);
  Ship ship3 = new Ship(100.0, 201.0, 3, 10, 2);
  Ship ship4 = new Ship(300.0, 100.0, 10, 10, 2);
  Ship ship5 = new Ship(300.0, 100.0, 10, -10, -2);
  Ship ship5moved = new Ship(290.0, 98.0, 10, -10, -2);
  
  
  Bullet bullet1 = new Bullet(240.0, 250.0, 5, 4.0, 4.0, 6);
  Bullet bullet2 = new Bullet(300.0, 100.0, 4, 5.0, 3.0, 4);
  Bullet bullet3 = new Bullet(300.0, 100.0, 4, 5.0, 2.0, 2);
  
  Bullet bullet4 = new Bullet(300.0, 100.0, 6, 4.0, 0.0, 3);
  Bullet bullet5 = new Bullet(300.0, 100.0, 6, -4.0, 0.0, 3);
   
  Bullet bullet6 = new Bullet(300.0, 100.0, 8, 4.0, 0.0, 4);
  Bullet bullet7 = new Bullet(300.0, 100.0, 8, -4 * Math.cos(Math.PI - (2 * Math.PI / 3)),
      -4 * Math.sin(Math.PI - (2 * Math.PI / 3)), 4);
  Bullet bullet8 = new Bullet(300.0, 100.0, 8, -4 * Math.cos((4 * Math.PI / 3) - Math.PI),
      4 * Math.sin((4 * Math.PI / 3) - Math.PI), 4);
  Bullet bullet9 = new Bullet(300, 100.0, 10, 4.0, 0.0, 2);

  
  ILoGamePiece list1 = new ConsLoGamePiece(ship1, new ConsLoGamePiece(ship0, new MtLoGamePiece()));
  ILoGamePiece list2 = new ConsLoGamePiece(bullet1, new ConsLoGamePiece(bullet2,
      new ConsLoGamePiece(bullet3, new MtLoGamePiece())));
  ILoGamePiece list3 = new ConsLoGamePiece(ship1, new ConsLoGamePiece(ship0, list2));
  ILoGamePiece list4 = new ConsLoGamePiece(ship0, new MtLoGamePiece());
  ILoGamePiece list5 = new ConsLoGamePiece(bullet1,
      new ConsLoGamePiece(bullet3, new MtLoGamePiece()));
  ILoGamePiece list6 = new ConsLoGamePiece(ship1, new ConsLoGamePiece(ship4, new MtLoGamePiece()));
  
  boolean testBigBang(Tester t) {

    return w.bigBang(500, 300, 1 / 28.0);

  }
  
  //tests for Draw
  boolean testDraw(Tester t) {
    return t.checkExpect(bullet1.draw(),
        new CircleImage(bullet1.radius, OutlineMode.SOLID, bullet1.color))
        && t.checkExpect(ship1.draw(),
            new CircleImage(ship1.radius, OutlineMode.SOLID, ship1.color));
  }
  
  //tests for distance
  boolean testDistance(Tester t) {
    return t.checkExpect(ship1.distance(bullet1), 10.0)
        && t.checkInexact(bullet1.distance(bullet2), 161.55, .1);
  }
  
  //tests for IsContacting
  boolean testIsContacting(Tester t) {
    return t.checkExpect(ship1.isContacting(ship2), false)
        && t.checkExpect(ship2.isContacting(ship3), true);
  }
  
  //tests for ExplodeHelp
  boolean testExplodeHelp(Tester t) {
    return t.checkExpect(bullet3.explodeHelp(2, 2, 0),
        new ConsLoGamePiece(bullet4, new ConsLoGamePiece(bullet5, new MtLoGamePiece())))
        && t.checkInexact(bullet5.explodeHelp(3, 3, 0),
            new ConsLoGamePiece(bullet6, new ConsLoGamePiece(
                bullet7, new ConsLoGamePiece(bullet8, new MtLoGamePiece()))), .5);
    
  }
  
  //tests for Explode
  boolean testExplode(Tester t) {
    return t.checkExpect(ship1.explode(list2), new ConsLoGamePiece(ship1, new MtLoGamePiece()))
        && t.checkExpect(ship4.explode(list2), new MtLoGamePiece());
  }
  
  //tests for ExplodeAll
  boolean testExplodeAll(Tester t) {
    return t.checkExpect(list6.explodeAll(list2), new ConsLoGamePiece(ship1, new MtLoGamePiece()))
        && t.checkExpect(list1.explodeAll(list2), list1);
  }
  
  //tests for CollideBullet
  boolean testCollideBullet(Tester t) {
    return t.checkExpect(new ConsLoGamePiece(ship4,
        new MtLoGamePiece()).collideBullet(bullet4), true) 
        && t.checkExpect(new ConsLoGamePiece(ship1,
            new MtLoGamePiece()).collideBullet(bullet4), false);
  }
  
  //tests for CollideShip
  boolean testCollideShip(Tester t) {
    return t.checkExpect(new ConsLoGamePiece(bullet4,
        new MtLoGamePiece()).collideShip(ship4), true) 
        && t.checkExpect(new ConsLoGamePiece(bullet4,
            new MtLoGamePiece()).collideShip(ship1), false);
  }
  
  //tests for NewRadius
  boolean testNewRadius(Tester t) {
    return t.checkExpect(bullet9.newRadius(), 10)
        && t.checkExpect(bullet1.newRadius(), 7);
  }
  
  //tests for OnTick
  boolean testOnTick(Tester t) {
    return t.checkExpect(new GameWorld(new ConsLoGamePiece(ship1,
        new MtLoGamePiece())).onTick(), new GameWorld(new ConsLoGamePiece(
            ship1moved, new MtLoGamePiece())))
        && t.checkExpect(new GameWorld(new ConsLoGamePiece(ship5,
            new MtLoGamePiece())).onTick(), new GameWorld(new ConsLoGamePiece(
                ship5moved, new MtLoGamePiece())));
  }
  
  //tests for AddShips
  boolean testAddShips(Tester t) {
    return t.checkExpect(new GameWorld(new ConsLoGamePiece(ship1, new MtLoGamePiece()))
        .addShips(), new GameWorld(new ConsLoGamePiece(ship1, new MtLoGamePiece())));
       
  }

  // Do not know how to seed random
  
  //  boolean testOnKeyEvent(Tester t) {
  //    
  //  }
  //  
  //  boolean testMakeScene(Tester t) {
  //    
  //  }
  //  
  //  boolean testCreate(Tester t) {
  //    
  //  }
  //  
  //  boolean testBuildShipList(Tester t) {
  //    
  //  }
  //  
  //  boolean testRandomShip(Tester t) {
  //    
  //  }
  
  //tests for MoveAll
  boolean testMoveAll(Tester t) {
    return t.checkExpect(list1.moveAll(), new ConsLoGamePiece(
        ship1.move(), new MtLoGamePiece()))
        && t.checkExpect(list2.moveAll(), new ConsLoGamePiece(
            bullet1.move(), new ConsLoGamePiece(bullet2.move(),
                new ConsLoGamePiece(bullet3.move(), new MtLoGamePiece()))));
  }
  
  //  boolean testDrawAll(Tester t) {
  //    return t.checkExpect(new GameWorld(new ConsLoGamePiece(
  //        ship1, new MtLoGamePiece())).onTick(), new GameWorld());
  //  }
  
  //tests for Append
  boolean testAppend(Tester t) {
    return t.checkExpect(list1.append(list2), list3)
        && t.checkExpect(new MtLoGamePiece().append(list1), list1);
  }
  
  //tests for Pop
  boolean testPop(Tester t) {
    return t.checkExpect(list1.pop(), list4)
        && t.checkExpect(list1.pop(ship1), new ConsLoGamePiece(ship0, new MtLoGamePiece()))
        && t.checkExpect(new MtLoGamePiece().pop(ship2), new MtLoGamePiece())
        && t.checkExpect(list2.pop(bullet2), list5);
    
  }
  
  //tests for ChooseXPos
  boolean testChooseXPos(Tester t) {
    return t.checkExpect(new Utils().chooseXPos(0), 0.0)
        && t.checkExpect(new Utils().chooseXPos(1), 500.0)
        && t.checkExpect(new Utils().chooseXPos(420), 250.0);
  }
  
  //tests for ChooseDir
  boolean testChooseDir(Tester t) {
    return t.checkExpect(new Utils().chooseDir(0), 1)
        && t.checkExpect(new Utils().chooseDir(1), -1)
        && t.checkExpect(new Utils().chooseDir(420), 0);
  }
}


// to represent Utils 
class Utils {
  double chooseXPos(int startVal) {
    if (startVal == 0) {
      return 0;
    }
    else if (startVal == 1) {
      return 500;
    }
    else {
      return 250;
    }
  }

  int chooseDir(int startVal) {
    if (startVal == 0) {
      return 1;
    }
    else if (startVal == 1) {
      return -1;
    }
    else {
      return 0;
    }
  }
}
