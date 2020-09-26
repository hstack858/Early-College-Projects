import java.util.ArrayList;

import tester.*;

import javalib.impworld.*;

import java.awt.Color;

import javalib.worldimages.*;

import java.util.Random;



// An interface to represent a tile or an out of bounds tile in minesweeper

interface ITile {



  // EFFECT: Sets this ITile's right Tile to be the given ITile

  void setRight(ITile right);



  // EFFECT: Sets this ITile's downright Tile to be the given ITile

  void setDownRight(ITile downright);



  // EFFECT: Sets this ITile's down Tile to be the given ITile

  void setDown(ITile down);



  // EFFECT: Sets this ITile's down left Tile to be the given ITile

  void setDownLeft(ITile downleft);



  // Determines if the given Itile has a mine

  boolean isMine();



  // Determines if the given Itile has been clicked yet

  boolean isClicked();



  // Determines if the given ITile has been flagged yet

  boolean isFlagged();


  // EFFECT: Sets this tile to clicked and calls clickTileLeft with floodFill
  void clickTileLeft();

  // EFFECT: Changes this tile's flagged parameter to the opposite of what it was
  void clickTileRight(); 
}




// A class to represent a tile that is outOfBounds (not actually drawn)

class OutOfBounds implements ITile {



  OutOfBounds() {



  }



  // EFFECT: OOB tile's cant have a right tile so nothing occurs

  public void setRight(ITile right) {
    // Empty for above reason
  }



  // EFFECT: OOB tile's cant have a downright tile so nothing occurs

  public void setDownRight(ITile downright) {
    // Empty for above reason
  }



  // EFFECT: OOB tile's cant have a down tile so nothing occurs

  public void setDown(ITile down) {
    // Empty for above reason
  }

  // EFFECT: OOB tile's cant have a downleft tile so nothing occurs

  public void setDownLeft(ITile downleft) {
    // Empty for above reason
  }



  // OOB tile's cant be mines so always returns false

  public boolean isMine() {

    return false;

  }



  // Always returns true because OOB tiles cant be clicked to reveal something

  public boolean isClicked() {

    return true;

  }



  // Always returns false because OOB tiles cant be flagged

  public boolean isFlagged() {

    return false;

  }

  // EFFECT: Sets this tile to clicked and calls clickTileLeft with floodFill
  public void clickTileLeft() {
    // Empty because an OOB tile can't be clicked
  }

  //EFFECT: Changes this tile's flagged parameter to the opposite of what it was
  public void clickTileRight() {
    // Empty because an OOB tile can't be clicked
  }

}



// A class to represent a tile with its Itile neighbors

class Tile implements ITile {

  ArrayList<ITile> neighbors;

  /* Index #s:

   * 0 - left, 1 - upleft, 2 - up, 3 - upright

   * 4 - right, 5 - downright, 6 - down, 7 - downleft

   */

  boolean isMine;

  boolean isClicked;

  boolean isFlagged;

  // Regular Constructor (isClicked defaults to false)

  Tile(ITile left, ITile upleft, ITile up, ITile upright) {

    neighbors = new ArrayList<ITile>();



    neighbors.add(left);

    neighbors.add(upleft);

    neighbors.add(up);

    neighbors.add(upright);

    neighbors.add(new OutOfBounds());

    neighbors.add(new OutOfBounds());

    neighbors.add(new OutOfBounds());

    neighbors.add(new OutOfBounds());

    this.isMine = false;

    this.isClicked = false;

    this.isFlagged = false;



    this.neighbors.get(0).setRight(this);

    this.neighbors.get(1).setDownRight(this);

    this.neighbors.get(2).setDown(this);

    this.neighbors.get(3).setDownLeft(this);

  }



  // Constructor for testing that allows us to choose if this tile is clicked and flagged

  Tile(ITile left, ITile upleft, ITile up, ITile upright, boolean isClicked, boolean isFlagged) {

    neighbors = new ArrayList<ITile>();



    neighbors.add(left);

    neighbors.add(upleft);

    neighbors.add(up);

    neighbors.add(upright);

    neighbors.add(new OutOfBounds());

    neighbors.add(new OutOfBounds());

    neighbors.add(new OutOfBounds());

    neighbors.add(new OutOfBounds());

    this.isMine = false;

    this.isClicked = isClicked;

    this.isFlagged = isFlagged;



    this.neighbors.get(0).setRight(this);

    this.neighbors.get(1).setDownRight(this);

    this.neighbors.get(2).setDown(this);

    this.neighbors.get(3).setDownLeft(this);

  }



  Tile(ITile left) {

    neighbors = new ArrayList<ITile>();

    neighbors.add(left);



    for (int x = 0; x < 7; x++) {

      neighbors.add(new OutOfBounds());

    }

    this.isMine = false;

    this.isFlagged = false;



    this.neighbors.get(0).setRight(this);

  }



  Tile() {

    neighbors = new ArrayList<ITile>();



    for (int x = 0; x < 8; x++) {

      neighbors.add(new OutOfBounds());

    }

    this.isMine = false;

    this.isFlagged = false;

  }



  // Constructor for testing purposes

  Tile(ITile left, ITile upleft, ITile up, ITile upright, ITile right, 
      ITile downright, ITile down, ITile downleft, 
      boolean isMine, boolean isClicked, boolean isFlagged) {

    neighbors = new ArrayList<ITile>();

    neighbors.add(left);

    neighbors.add(upleft);

    neighbors.add(up);

    neighbors.add(upright);

    neighbors.add(right);

    neighbors.add(downright);

    neighbors.add(down);

    neighbors.add(downleft);

    this.isMine = isMine;

    this.isClicked = isClicked;

    this.isFlagged = isFlagged;

  }



  // EFFECT: Sets the right of this tile to be the given ITile

  public void setRight(ITile right) {

    this.neighbors.set(4,right);

  }



  // EFFECT: Sets the down right of this tile to be the given ITile

  public void setDownRight(ITile downright) {

    this.neighbors.set(5,downright);

  }



  // EFFECT: Sets the down of this tile to be the given ITile

  public void setDown(ITile down) {

    this.neighbors.set(6,down);

  }



  // EFFECT: Sets the downleft of this tile to be the given ITile

  public void setDownLeft(ITile downleft) {

    this.neighbors.set(7,downleft);

  }



  // Determines if this tile is a mine

  public boolean isMine() {

    return this.isMine;

  }



  // Determines if this tile has been clicked

  public boolean isClicked() {

    return this.isClicked;

  }



  // Determines if this tile has been flagged

  public boolean isFlagged() {

    return this.isFlagged;

  }



  // EFFECT: Turns this tile into a mine tile

  void addMine() {

    this.isMine = true;

  }



  // Counts the number of mines surrounding this tile

  public int countMines() {

    int count = 0;



    for (ITile x : this.neighbors) {

      if (x.isMine()) {

        count++;

      }

    }



    return count;

  }



  // Draws a single tile based on its characteristics

  WorldImage drawTile() {

    WorldImage squareGrid = new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.BLACK);

    if (!this.isClicked()) {

      if (!this.isFlagged()) {

        return new OverlayImage(squareGrid, 
            new RectangleImage(25, 25, OutlineMode.SOLID, Color.cyan));

      }

      else {
        return new OverlayImage(squareGrid, 
            (new OverlayImage(new EquilateralTriangleImage(
                10, OutlineMode.SOLID, Color.YELLOW), 
                (new RectangleImage(25, 25, OutlineMode.SOLID, Color.cyan)))));
      }

    }

    if (this.countMines() > 0) {

      if (this.countMines() == 1) {

        return new OverlayImage(squareGrid, 
            (new OverlayImage(new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY),

                new TextImage(Integer.toString(this.countMines()), 12, Color.blue))));

      }

      if (this.countMines() == 2) {

        return new OverlayImage(squareGrid, 
            (new OverlayImage(new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY),

                new TextImage(Integer.toString(this.countMines()), 12, Color.GREEN))));

      }

      if (this.countMines() == 3) {

        return new OverlayImage(squareGrid, 
            (new OverlayImage(new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY),

                new TextImage(Integer.toString(this.countMines()), 12, Color.RED))));

      }

      if (this.countMines() == 4) {

        return new OverlayImage(squareGrid, 
            (new OverlayImage(new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY),

                new TextImage(Integer.toString(this.countMines()), 12, Color.PINK))));

      }

      if (this.countMines() == 5) {

        return new OverlayImage(squareGrid, 
            (new OverlayImage(new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY),

                new TextImage(Integer.toString(this.countMines()), 12, Color.MAGENTA))));

      }

      if (this.countMines() == 6) {

        return new OverlayImage(squareGrid, 
            (new OverlayImage(new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY),

                new TextImage(Integer.toString(this.countMines()), 12, Color.ORANGE))));

      }

      if (this.countMines() == 7) {

        return new OverlayImage(squareGrid, 
            (new OverlayImage(new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY),

                new TextImage(Integer.toString(this.countMines()), 12, Color.BLACK))));

      }

      if (this.countMines() == 8) {

        return new OverlayImage(squareGrid, 
            (new OverlayImage(new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY),

                new TextImage(Integer.toString(this.countMines()), 12, Color.lightGray))));

      }

    }

    else {

      return new OverlayImage(squareGrid, 
          (new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY)));

    }

    return null;

  }

  //EFFECT: Sets this tile to clicked and calls clickTileLeft with floodFill
  public void clickTileLeft() {
    if ((!this.isClicked) && !(this.isFlagged)) {
      this.isClicked = true;
      this.isFlagged = false;
      if (this.countMines() == 0) {
        for (int i = 0; i < 8; i++) {
          this.neighbors.get(i).clickTileLeft();
        }
      }
    }
  }

  //EFFECT: Changes this tile's flagged parameter to the opposite of what it was
  public void clickTileRight() {
    if (!(this.isClicked)) {
      this.isFlagged = !(this.isFlagged);
    }
  }

}



// A class that reperesents the gameboard along with all of its tiles, mines, and width/height

class Board {

  ArrayList<Tile> tiles;

  int width;

  int height;

  int flags;

  Board(int width, int height, int flags) {

    this.tiles = new ArrayList<Tile>();

    this.height = Math.max(height,3);

    this.width = Math.max(width, 3);

    this.flags = Math.min(flags, width * height);



    this.assembleBoard();

    this.addFlags();

  }



  // EFFECT: Assembles the board by connecting all of the 
  // tiles together through their neighbor lists

  void assembleBoard() {

    assembleTopRow();



    for (int i = 2;i <= height;i++) {

      assembleMiddleRow(i);

    }



  }



  // EFFECT: Assembles the top rop of the board

  void assembleTopRow() {

    this.tiles.add(new Tile());



    for (int x = 1; x < this.width;x++) {

      this.tiles.add(new Tile(this.tiles.get(x - 1)));

    }



  }



  // EFFECT: Assembles a middle row of the board

  void assembleMiddleRow(int rownum) {

    this.tiles.add(new Tile(new OutOfBounds(), new OutOfBounds(), tiles.get((rownum - 2) * width), 
        tiles.get((rownum - 2) * width + 1)));



    for (int x = (width * (rownum - 1) + 1) ;x < width * rownum - 1; x++) {

      this.tiles.add(new Tile(tiles.get(x - 1), 
          tiles.get(x - width - 1), tiles.get(x - width), tiles.get(x - width + 1)));

    }



    int i = width * rownum - 1;

    this.tiles.add(new Tile(tiles.get(i - 1), tiles.get(i - width - 1), 
        tiles.get(i - width), new OutOfBounds()));

  }



  // EFFECT: Adds the amount of mines this board has to the tiles in the board

  void addFlags() {

    ArrayList<Tile> bank = new ArrayList<Tile>();



    for (int x = 0; x < tiles.size();x++) {

      bank.add(tiles.get(x));

    }



    Random rand = new Random();



    for (int i = 0; i < this.flags; i++) {

      int index = rand.nextInt(bank.size());



      bank.get(index).addMine();

      bank.remove(index);

    }



  }



  // Draws all of the tiles in this board

  public WorldImage drawAllTiles() {

    WorldImage board = drawRow(1);





    for (int y = 2; y <= this.height; y++) {

      board = new AboveImage(board, drawRow(y));

    }



    return board;

  }



  // Draws a row if tiles in this board

  public WorldImage drawRow(int rownum) {

    WorldImage row = tiles.get(width * (rownum -  1)).drawTile();



    for (int x = (width * (rownum - 1) + 1); x < width * rownum; x++) {

      row = new BesideImage(row, tiles.get(x).drawTile());

    }



    return row;

  }

  // Returns the amount of mines that have been clicked on the board
  public int countMinesClicked() {
    int mc = 0;
    for (Tile t : this.tiles) {
      if (t.isClicked && t.isMine) {
        mc = mc + 1;
      }
    }
    return mc;
  }

  // Returns the amount of tiles clicked on the board
  public int countTilesClicked() {
    int mc = 0;
    for (Tile t : this.tiles) {
      if (t.isClicked) {
        mc = mc + 1;
      }
    }
    return mc;
  }

  // Returns the total amount of mines on the board
  public int countTotalMines() {
    int mc = 0;
    for (Tile t : this.tiles) {
      if (t.isMine) {
        mc = mc + 1;
      }
    }
    return mc;
  }


}



// A WorldClass with only a game board used to make the scene for big bang to animate

class TileWorld extends World {

  Board board;



  TileWorld(Board board) {

    this.board = board;

  }

  TileWorld() {
    this.board = null;
  }

  // Takes the images created by drawAllTiles and puts them on a scene for big bang to display

  public WorldScene makeScene() {
    WorldImage image = this.board.drawAllTiles();
    WorldScene scene = getEmptyScene();
    scene.placeImageXY(image, (this.board.width * 25) / 2, (this.board.height * 25) / 2);
    return scene;
  }

  // Clicks the proper tile with the proper click method depending on which
  // side of the mouse is clicked and the Posn on the screen it is at when it
  // is clicked
  public void onMouseClicked(Posn loc, String buttonName) {
    int xPos = loc.x;
    int yPos = loc.y;
    int xTiles = xPos / 25;
    int yTiles = yPos / 25;
    int tileIndex = Math.min((xTiles) + (this.board.width * yTiles), this.board.tiles.size() - 1);

    if (buttonName.equals("RightButton")) {
      this.board.tiles.get(tileIndex).clickTileRight();
    }

    if (buttonName.contentEquals("LeftButton")) {
      this.board.tiles.get(tileIndex).clickTileLeft();
    }
  }


  // Returns a WorldEnd if either of the end conditions are met
  // Otherwise the game continues as normal
  public WorldEnd worldEnds() {
    if (this.board.countMinesClicked() > 0) {
      return new WorldEnd(true, this.makeFinalScene("Game Over!"));
    }
    if (this.board.countTilesClicked() == 
        (this.board.width * this.board.height) - this.board.countTotalMines()) {
      return new WorldEnd(true, this.makeFinalScene("You Win!"));
    }
    else {
      return new WorldEnd(false, this.makeScene());
    }
  }

  // Returns a finalScene with the given string being displayed
  public WorldScene makeFinalScene(String s) {
    WorldScene scene = getEmptyScene();
    scene.placeImageXY(new TextImage(s, 50, Color.blue), 250, 250);
    return scene;
  }
}

class Utilities {
  Utilities(){}

  // Returns the amount of tiles with mines in an arrayList<Tile>
  public int minesInList(ArrayList<Tile> arr) {
    return this.minesInListHelper(arr, 0);
  }

  // Returns the amount of tiles with mines in an arrayList<Tile>
  public int minesInListHelper(ArrayList<Tile> arr, int currIdx) {
    if (arr.get(currIdx).isMine() && (currIdx < arr.size() - 1)) {
      return 1 + this.minesInListHelper(arr, currIdx + 1);
    }
    if (currIdx < arr.size() - 1) {
      return this.minesInListHelper(arr, currIdx + 1);
    }
    else {
      return 0; }
  }
}



// Examples Class

class ExamplesMinesweeper {

  ExamplesMinesweeper(){}

  ArrayList<Tile> example;

  Tile t1;

  Tile t2;

  Tile t3;

  Tile t4;

  Tile t5;

  Tile t6;

  Tile t7;

  Tile t8;

  Tile t9;

  Tile t10;

  Board b1;

  TileWorld tw1;

  // EFFECT: Initializes the lists of tiles for the examples

  void initData() {



    example = new ArrayList<Tile>();



    t1 = new Tile();

    t2 = new Tile(t1, new OutOfBounds(), new OutOfBounds(), new OutOfBounds());

    t3 = new Tile(t2, new OutOfBounds(), new OutOfBounds(), new OutOfBounds());

    t4 = new Tile(new OutOfBounds(), new OutOfBounds(), t1, t2);

    t5 = new Tile(t4, t1, t2, t3);

    t6 = new Tile(t5, t2, t3, new OutOfBounds());

    t7 = new Tile(new OutOfBounds(), new OutOfBounds(), t4, t5);

    t8 = new Tile(t7, t4, t5, t6);

    t9 = new Tile(t8, t5, t6, new OutOfBounds());



    t10 = new Tile(new OutOfBounds(), new OutOfBounds(), 
        new OutOfBounds(), new OutOfBounds(), new OutOfBounds(), 
        new OutOfBounds(), new OutOfBounds(),
        new OutOfBounds(), true, true, true);



    b1 = new Board(10, 10, 10);



    tw1 = new TileWorld(b1);



    example.add(t1);

    example.add(t2);

    example.add(t3);

    example.add(t4);

    example.add(t5);

    example.add(t6);

    example.add(t7);

    example.add(t8);

    example.add(t9);

  }



  // Tests the assemble board methods

  boolean testAssembleBoardAndAssembleTopRowAndAssembleMiddleRow(Tester t) {

    initData();

    Board exampleBoard = new Board(3,3,0);

    return t.checkExpect(exampleBoard.tiles, example);

  }



  // Tests that our tile constructor does its job properly

  boolean testTileConstructor(Tester t) {

    initData();

    return t.checkExpect(t1.neighbors.get(4), t2)

        && t.checkExpect(t1.neighbors.get(5), t5)

        && t.checkExpect(t1.neighbors.get(6), t4);

  }


  // Tests setright
  boolean testSetRight(Tester t) {

    initData();

    boolean bool1 = t.checkExpect(t1.neighbors.get(4), t2);

    t1.setRight(new OutOfBounds());

    return t.checkExpect(t1.neighbors.get(4), new OutOfBounds())

        && bool1;

  }


  // Tests setdownRight
  boolean testSetDownRight(Tester t) {

    initData();

    boolean bool2 = t.checkExpect(t1.neighbors.get(5), t5);

    t1.setDownRight(new OutOfBounds());

    return t.checkExpect(t1.neighbors.get(5), new OutOfBounds())

        && bool2;

  }


  // Tests setDown
  boolean testSetDown(Tester t) {

    initData();

    boolean bool3 = t.checkExpect(t1.neighbors.get(6), t4);

    t1.setDown(new OutOfBounds());

    return t.checkExpect(t1.neighbors.get(6), new OutOfBounds())

        && bool3;

  }


  // tests setdownleft
  boolean testSetDownLeft(Tester t) {

    initData();

    boolean bool4 = t.checkExpect(t5.neighbors.get(7), t7);

    t5.setDownLeft(new OutOfBounds());

    return t.checkExpect(t5.neighbors.get(7), new OutOfBounds())

        && bool4;

  }


  // Tests isMine
  boolean testIsMine(Tester t) {

    initData();

    return t.checkExpect(t10.isMine(), true)

        && t.checkExpect(t1.isMine(), false)

        && t.checkExpect(new OutOfBounds().isMine(), false);

  }


  // Tests isClicked
  boolean testIsClicked(Tester t) {

    initData();

    return t.checkExpect(t10.isClicked(), true)

        && t.checkExpect(t1.isClicked(), false)

        && t.checkExpect(new OutOfBounds().isClicked(), true);

  }


  // Tests isFlagged
  boolean testIsFlagged(Tester t) {

    initData();

    return t.checkExpect(t10.isFlagged(), true)

        && t.checkExpect(t1.isFlagged(), false)

        && t.checkExpect(new OutOfBounds().isFlagged(), false);

  }


  // Tests addmine
  boolean testAddMine(Tester t) {

    initData();

    boolean boolA = t1.isMine;

    t1.addMine();

    return t.checkExpect(t1.isMine(), true)

        && t.checkExpect(boolA, false);

  }


  // Tests countmines
  boolean testCountMines(Tester t) {

    initData();
    t1.addMine();

    return t.checkExpect(t2.countMines(), 1)

        && t.checkExpect(t8.countMines(), 0);

  }


  // tests drawTile
  boolean testDrawTile(Tester t) {

    initData();

    WorldImage squareGrid = new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.BLACK);

    t10.isClicked = false;

    t1.addMine();

    t2.isClicked = true;

    return t.checkExpect(t1.drawTile(), 
        new OverlayImage(squareGrid, 
            new RectangleImage(25, 25, OutlineMode.SOLID, Color.cyan)))

        && t.checkExpect(t10.drawTile(), 
            new OverlayImage(squareGrid, 
                (new OverlayImage(new EquilateralTriangleImage(
                    10, OutlineMode.SOLID, Color.YELLOW), 
                    (new RectangleImage(25, 25, OutlineMode.SOLID, Color.cyan))))))

        && t.checkExpect(t2.drawTile(), 
            new OverlayImage(squareGrid, 
                (new OverlayImage(new RectangleImage(25, 25, OutlineMode.OUTLINE, Color.DARK_GRAY),

                    new TextImage("1", 12, Color.blue)))));

  }

  // Tests minesInList
  boolean testMinesInList(Tester t) {
    initData();
    int banana = new Utilities().minesInList(example);
    t1.addMine();
    return t.checkExpect(new Utilities().minesInList(example), 1)
        && t.checkExpect(banana, 0);
  }

  // Tests addFlags
  boolean testAddFlags(Tester t) {
    initData();
    Board b2 = new Board(3, 3, 3);
    return t.checkExpect(new Utilities().minesInList(example), 0)
        && t.checkExpect(new Utilities().minesInList(b2.tiles), 3);
  }

  // Tests countMinesClicked
  boolean testCountMinesClicked(Tester t) {
    initData();
    b1.tiles.get(0).isMine = true;
    b1.tiles.get(0).isClicked = true;
    b1.tiles.get(1).isMine = true;
    b1.tiles.get(1).isClicked = true;
    Board b2 = new Board(3, 3, 3);

    return t.checkExpect(b1.countMinesClicked(), 2)
        && t.checkExpect(b2.countMinesClicked(), 0);
  }

  // Tests countTilesClicked
  boolean testCountTilesClicked(Tester t) {
    initData();
    b1.tiles.get(0).isClicked = true;
    b1.tiles.get(1).isClicked = true;
    Board b2 = new Board(3, 3, 3);
    return t.checkExpect(b2.countTilesClicked(), 0)
        && t.checkExpect(b1.countTilesClicked(), 2);
  }

  // Tests countTotalMines
  boolean testCountTotalMines(Tester t) {
    initData();
    Board b2 = new Board(3, 3, 3);
    return t.checkExpect(b1.countTotalMines(), 10)
        && t.checkExpect(b2.countTotalMines(), 3);
  }

  // Tests clickTileLeft
  boolean testClickTileLeft(Tester t) {
    initData();
    Board b2 = new Board(3, 3, 3);
    boolean notClicked = b2.tiles.get(0).isClicked;
    boolean notClickedTwo = b2.tiles.get(1).isClicked;
    boolean notClickedThree = b2.tiles.get(3).isClicked;
    boolean notClickedFour = b2.tiles.get(4).isClicked;
    b2.tiles.get(1).isMine = false;
    b2.tiles.get(3).isMine = false;
    b2.tiles.get(4).isMine = false;
    b2.tiles.get(0).isMine = false;
    b2.tiles.get(0).clickTileLeft();

    return t.checkExpect(notClicked, false)
        && t.checkExpect(notClickedTwo, false)
        && t.checkExpect(notClickedThree, false)
        && t.checkExpect(notClickedFour, false)
        && t.checkExpect(b2.tiles.get(1).isClicked, true)
        && t.checkExpect(b2.tiles.get(3).isClicked, true)
        && t.checkExpect(b2.tiles.get(4).isClicked, true)
        && t.checkExpect(b2.tiles.get(0).isClicked, true);
  }

  // Tests clickTileRight
  boolean testClickTileRight(Tester t) {
    initData();
    Board b2 = new Board(3, 3, 3);
    boolean notFlagged = b2.tiles.get(0).isFlagged;
    b2.tiles.get(1).isFlagged = true;
    b2.tiles.get(0).clickTileRight();
    b2.tiles.get(1).clickTileRight();
    return t.checkExpect(notFlagged, false)
        && t.checkExpect(b2.tiles.get(0).isFlagged, true)
        && t.checkExpect(b2.tiles.get(1).isFlagged, false);
  }

  // Tests onMouseClicked
  boolean testOnMouseClicked(Tester t) {
    initData();
    Board b2 = new Board(3, 3, 3);
    TileWorld tw2 = new TileWorld(b2);
    b2.tiles.get(1).isMine = false;
    b2.tiles.get(3).isMine = false;
    b2.tiles.get(4).isMine = false;
    b2.tiles.get(0).isMine = false;
    tw2.onMouseClicked(new Posn(10, 10), "RightButton");
    boolean isFlagged = b2.tiles.get(0).isFlagged;
    tw2.onMouseClicked(new Posn(10, 10), "RightButton");
    tw2.onMouseClicked(new Posn(10, 10), "LeftButton");

    return t.checkExpect(isFlagged, true)
        && t.checkExpect(b2.tiles.get(0).isFlagged, false)
        && t.checkExpect(b2.tiles.get(1).isClicked, true)
        && t.checkExpect(b2.tiles.get(3).isClicked, true)
        && t.checkExpect(b2.tiles.get(4).isClicked, true)
        && t.checkExpect(b2.tiles.get(0).isClicked, true);

  }

  // Tests whether the worldEnds correctly for a loss
  boolean testWorldEndsLose(Tester t) {
    initData();
    Board b2 = new Board(3, 3, 3);
    TileWorld tw2 = new TileWorld(b2);


    b2.tiles.get(0).isMine = true;
    b2.tiles.get(0).isClicked = true;



    return t.checkExpect(tw2.worldEnds(), new WorldEnd(true, tw2.makeFinalScene("Game Over!")));
  }

  // Tests whether the worldEnds correctly for A win
  boolean testWorldEndsWin(Tester t) {
    initData();
    Board b3 = new Board(3, 3, 0);
    TileWorld tw3 = new TileWorld(b3);
    b3.tiles.get(0).clickTileLeft();
    return t.checkExpect(tw3.worldEnds(), new WorldEnd(true, tw3.makeFinalScene("You Win!")));
  }

  // Tests minesInListHelper
  boolean testMinesInListHelper(Tester t) {
    initData();
    int banana = new Utilities().minesInListHelper(example, 0);
    t1.addMine();
    return t.checkExpect(new Utilities().minesInListHelper(example, 0), 1)
        && t.checkExpect(banana, 0);
  }

  // Tests drawAllTiles
  boolean testDrawAllTiles(Tester t) {
    initData();
    Board b3 = new Board(3, 3, 0);
    TileWorld tw3 = new TileWorld(b3);
    WorldImage rowOne = b3.drawRow(1);
    WorldImage rowTwo = b3.drawRow(2);
    WorldImage rowThree = b3.drawRow(3);
    WorldImage firstRows = new AboveImage(rowOne, rowTwo);

    return t.checkExpect(b3.drawAllTiles(), new AboveImage(firstRows, rowThree));

  }

  // Tests drawRow
  boolean testDrawRow(Tester t) {
    initData();
    Board b3 = new Board(3, 3, 0);
    TileWorld tw3 = new TileWorld(b3);

    WorldImage tileOne = b3.tiles.get(0).drawTile();
    WorldImage tileTwo = b3.tiles.get(1).drawTile();
    WorldImage tileThree = b3.tiles.get(2).drawTile();
    WorldImage firstTiles = new BesideImage(tileOne, tileTwo);

    return t.checkExpect(b3.drawRow(1), new BesideImage(firstTiles, tileThree));
  }


  // Runs the game/Tests draw all tiles
  void testRunGame(Tester t) {
    this.tw1.bigBang(500, 500, 1);
  }
}