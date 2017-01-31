
/**
 * Copyright (c) 2015, Johan Ofverstedt <johan.ofverstedt@gmail.com>
 *
 * Permission to use, copy, modify, and/or distribute this software for any purpose with or without fee is hereby granted,
 * provided that the above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE INCLUDING ALL
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN
 * AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  World is a class representing an environment for Turtles.
 *
 *  The world is visualized as a window, and its size can be
 *  selected upon construction.
 *
 *  As the turtles move around, they are redrawn and they leave
 *  tracks on the background in the form of their linear path.
 */
public class World {
  //
  //  World attributes
  //
  
  private int width;
  private int height;
  
  private CopyOnWriteArrayList<Turtle> turtles;
  private boolean updateOnChange;

  private JFrame frame;
  private WorldCanvas canvas;
  
  private static int worldCount = 0;
  
  /**
   *  Constructs a world with default size of 400 by 400 pixels.
   */
  public World() {
    this(400, 400);
  }
  
  /**
   *  Constructs a world with a specified width and height.
   *
   *  @param width The width of the world in pixels.
   *  @param height The height of the world in pixels.
   */
  public World(int width, int height) {
    if(width < 1)
      throw new RuntimeException("Invalid world width.");
    if(height < 1)
      throw new RuntimeException("Invalid world height.");

    this.width = width;
    this.height = height;

    this.updateOnChange = true;
    
    this.turtles = new CopyOnWriteArrayList<Turtle>();
    
    createWindow();
  }

  /**
   *  Removes a turtle from the world.
   *
   *  @param t A reference to the turtle to remove.
   */
  public void remove(Turtle t) {
    boolean result = this.turtles.remove(t);
    if(result)
      this.turtleUpdate(); 
  }
  
  /**
   *  Returns the width of the world in pixels.
   *
   *  @return The width of the world in pixels.
   */
  public int getWidth() {
    return this.width;
  }
  
  /**
   *  Returns the height of the world in pixels.
   *
   *  @return The height of the world in pixels.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   *  Returns the current update on change flag.
   *  
   *  <p>Update on change causes the world to be redrawn for every
   *  state change of a Turtle connected to this World.</p>
   *
   *  @return The update on change flag.
   */
  public boolean getUpdateOnChange() {
    return this.updateOnChange;
  }

  /**
   *  Enables update on change.
   *  
   *  <p>Update on change causes the world to be redrawn for every
   *  state change of a Turtle connected to this World.</p>
   */
  public void enableUpdateOnChange() {
    this.updateOnChange = true;
  }

  /**
   *  Disables update on change.
   *  
   *  <p>Update on change causes the world to be redrawn for every
   *  state change of a Turtle connected to this World.</p>
   */
  public void disableUpdateOnChange() {
    this.updateOnChange = false;
  }
  
  /**
   *  Forces a repaint of the world.
   */
  public void update() {
    this.canvas.repaint();
  }
  
  /**
   *  Generates a string representation of the world
   *  and all the turtles in it.
   *
   *  @return The string representation.
   */
  public String toString() {
    StringBuilder str = new StringBuilder(4096);
    str.append("World(");
    str.append(this.width);
    str.append(", ");
    str.append(this.height);
    str.append(") : [");

    if(!this.turtles.isEmpty()) {
      //Add the first turtle's string rep. to the string
      str.append(this.turtles.get(0).toString());

      //Add the rest of the turtles' string rep. to the string, comma separated
      for(int i = 1; i < this.turtles.size(); ++i) {
        str.append(", ");
        str.append(this.turtles.get(i).toString());
      }
    }

    str.append("]");
    return str.toString();
  }
  
  //
  //  Implementation details below here...
  //  Not intended for the faint of heart!
  //

  /**
   *  Package local method which adds
   *  a turtle to this world.
   *
   *  @param t Turtle to be added to the world.
   */
  void add(Turtle t) {
    if(t == null)
      throw new RuntimeException("Can't add a null turtle reference to the world.");

    //Only add the turtle if it doesn't already exist
    //in the world to bulletproof the class against
    //surprising behavior.
    if(!this.turtles.contains(t))
      this.turtles.add(t);
  }

  /**
   *  Signals that a Turtle has been updated.
   */
  void turtleUpdate() {
    if(updateOnChange)
      update();
  }

  /**
   *  Package local method which draws a line from
   *  an old position to the new for a given turtle.
   */
  void drawPath(Turtle t, int xOld, int yOld, int xNew, int yNew) {
    this.canvas.drawLine(xOld, yOld, xNew, yNew, t.getColor());
  }
  
  /**
   *  Creates the world window and sets up the canvas
   *  which is used to draw the world and turtles.
   */
  private void createWindow() {
    String worldTitle = "World";
    if(++worldCount > 1)
      worldTitle += (" " + worldCount);
    
    this.frame = new JFrame(worldTitle);
    
    this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.frame.setResizable(false);
    this.frame.setLocation(32, 32);

    this.canvas = new WorldCanvas(this.width, this.height, this.turtles);
    
    this.frame.add(this.canvas);
    this.frame.pack();
    this.frame.setVisible(true);

    this.frame.repaint();
  }
  
  /**
   *  Private class which implements the drawing
   *  facilities of the World class and maintains
   *  the current persistent turtle tracks.
   */
  private class WorldCanvas extends JPanel {
    private BufferedImage img;
    private Color bgrColor;
    private CopyOnWriteArrayList<Turtle> turtles;
    
    WorldCanvas(int width, int height, CopyOnWriteArrayList<Turtle> turtles) {
      this.img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      this.bgrColor = new Color(255, 255, 255);
      this.turtles = turtles;
      
      this.setPreferredSize(new Dimension(width, height));
      
      clear();
    }
    
    //
    //  Persistent drawing methods
    //
    
    public void clear() {
      Graphics2D g = this.img.createGraphics();
      
      g.setColor(this.bgrColor);
      g.fillRect(0, 0, this.img.getWidth(), this.img.getHeight());
      
      g.dispose();
    }
    
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
      Graphics2D g = this.img.createGraphics();
      
      //Enable anti-aliasing to make the lines look pretty
      Object previousAntiAliasHint = g.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      g.setColor(color);
      g.drawLine(x1, y1, x2, y2);
      
      //Restore previous anti-aliasing mode
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, previousAntiAliasHint);
      
      g.dispose();
    }
    
    //
    //  Methods for redrawing to the screen
    //
    
    private double circularXOffset(double angle, double radius) {
      return Math.cos(angle) * radius;
    }
    private double circularYOffset(double angle, double radius) {
      return Math.sin(angle) * radius;
    }
    
    private void fillCenteredCircle(Graphics2D g, double x, double y, double radius) {
      double diameter = 2.0 * radius;
      g.fill(new Ellipse2D.Double(x-radius, y-radius, diameter, diameter));
      //g.fillOval(x - radius, y - radius, diameter, diameter);
    }
    
    private void paintTurtle(Graphics g, Turtle t) {
      final double RADIUS = Turtle.RADIUS * t.getSize();
      final double HEAD_RADIUS = (5.0/Turtle.RADIUS) * RADIUS;
      final double LEG_RADIUS = (3.0/Turtle.RADIUS) * RADIUS;
      
      if(!t.isVisible())
        return;

      Graphics2D g2 = (Graphics2D)g;
      
      int xPos = t.getXPos();
      int yPos = t.getYPos();
      double dirRads = (Math.PI/180.0) * t.getDirection();
      
      Color color = t.getColor();
      Color limbColor = t.getLimbColor();
      
      g.setColor(limbColor);

      //Draw legs
      for(int i = 0; i < 4; ++i) {
        double legAngle = dirRads + 2.0 * Math.PI * ((i+1)/5.0);
        double legXPos = xPos + circularXOffset(legAngle, RADIUS+(1.0/Turtle.RADIUS) * RADIUS);
        double legYPos = yPos + circularYOffset(legAngle, RADIUS+(1.0/Turtle.RADIUS) * RADIUS);
        fillCenteredCircle(g2, legXPos, legYPos, LEG_RADIUS);
      }
      
      //Draw head
      double headXPos = xPos + circularXOffset(dirRads, RADIUS+(2.0/Turtle.RADIUS) * RADIUS);
      double headYPos = yPos + circularYOffset(dirRads, RADIUS+(2.0/Turtle.RADIUS) * RADIUS);
      fillCenteredCircle(g2, headXPos, headYPos, HEAD_RADIUS);

      //Draw eyes
      g.setColor(Color.BLACK);
      for(int i = 1; i <= 2; ++i) {
        double angleFraction = (i == 1) ? (-1.0) : (1.0);
        double eyeAngle = dirRads + 2.0 * Math.PI * (angleFraction / 8.0);
        double eyeXPos = headXPos + circularXOffset(eyeAngle, HEAD_RADIUS * 0.75);
        double eyeYPos = headYPos + circularYOffset(eyeAngle, HEAD_RADIUS * 0.75);
        fillCenteredCircle(g2, eyeXPos, eyeYPos, HEAD_RADIUS * 0.15);
      }

      //Draw body
      g.setColor(color);
      fillCenteredCircle(g2, xPos, yPos, RADIUS);
    }
    
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      //Need to manually cast the Graphics reference to a Graphics2D reference
      //to make use of better drawing facilities
      Graphics2D g2 = (Graphics2D)g;
      
      //Draw back-buffer
      g2.drawRenderedImage(this.img, null);
      
      //Enable anti-aliasing to make the turtles look pretty
      Object previousAntiAliasHint = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      
      for(Turtle t : this.turtles) {
        paintTurtle(g, t);
      }
      
      //Restore previous anti-aliasing mode
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, previousAntiAliasHint);
    }
  }
}
