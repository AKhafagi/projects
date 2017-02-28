/**
 * A heavily modified version of the <code>StdDraw</code> class created for
 * Princeton's standard libraries. The existing solution provided more
 * functionality than necessary for this assignment, and if somewhat obfuscated
 * the rendering process itself. This file should serve well in this context,
 * for it only includes methods necessary for this assignment, yet it remains
 * close enough to the original to allow you to transition back to it should you
 * wish to further explore the solution.
 * 
 * @see http://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
 */

package edu.sdsu.cs.prog3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class remains intentionally comment free, for one should really study
 * the original.
 * 
 * @see http://introcs.cs.princeton.edu/java/stdlib/StdDraw.java.html
 * @author Shawn Healey
 *
 */
public final class SimpleDraw {

  private static final Color DEFAULT_CLEAR_COLOR = Color.BLACK;
  private static final Color DEFAULT_PEN_COLOR = Color.GREEN;
  private static final double DEFAULT_PEN_RADIUS = 0.002;

  private static final int DEFAULT_SIZE = 512;
  private static final double DEFAULT_XMAX = 1.0;
  private static final double DEFAULT_XMIN = 0.0;

  private static final double DEFAULT_YMAX = 1.0;

  private static final double DEFAULT_YMIN = 0.0;
  private static boolean defer = false;
  private static JFrame frame;
  private static int height = DEFAULT_SIZE;

  private static long nextDraw = -1;

  private static Graphics2D offscreen;
  private static BufferedImage offscreenImage;
  private static Graphics2D onscreen;

  private static Color penColor;
  private static SimpleDraw std = new SimpleDraw();
  private static int width = DEFAULT_SIZE;

  private static double xmin, ymin, xmax, ymax;

  public static void clear() {
    clear(DEFAULT_CLEAR_COLOR);
  }

  public static void clear(Color color) {
    offscreen.setColor(color);
    offscreen.fillRect(0, 0, width, height);
    offscreen.setColor(penColor);
    draw();
  }

  private static void draw() {
    if (defer) {
      return;
    }

    onscreen.drawImage(offscreenImage, 0, 0, null);
    frame.repaint();
  }

  private static double factorX(double w) {
    return w * width / Math.abs(xmax - xmin);
  }

  private static double factorY(double h) {
    return h * height / Math.abs(ymax - ymin);
  }

  public static void filledCircle(double x, double y, double radius) {

    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * radius);
    double hs = factorY(2 * radius);
    if (ws <= 1 && hs <= 1) {
      pixel(x, y);
    } else {
      offscreen.fill(new  Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs));
    }
    draw();
  }

  private static void init() {
    if (frame != null) {
      frame.setVisible(false);
    }
    frame = new JFrame();
    frame.setLocationRelativeTo(null);
    offscreenImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_ARGB);
    BufferedImage onscreenImage = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_ARGB);
    offscreen = offscreenImage.createGraphics();
    onscreen = onscreenImage.createGraphics();

    setPenColor();
    setPenRadius();

    clear();

    ImageIcon icon = new ImageIcon(onscreenImage);
    JLabel draw = new JLabel(icon);

    frame.setContentPane(draw);

    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    frame.setTitle("Standard Draw");

    frame.pack();
    frame.requestFocusInWindow();
    frame.setVisible(true);
  }

  public static void line(double x0, double y0, double x1, double y1) {
    offscreen.draw(
        new Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
    draw();
  }

  private static void pixel(double x, double y) {
    offscreen.fillRect((int) Math.round(scaleX(x)), (int) Math.round(scaleY(y)),
        1, 1);
  }

  private static double scaleX(double x) {
    return width * (x - xmin) / (xmax - xmin);
  }

  private static double scaleY(double y) {
    return height * (ymax - y) / (ymax - ymin);
  }

  public static void setCanvasSize() {
    setCanvasSize(DEFAULT_SIZE, DEFAULT_SIZE);
  }

  public static void setCanvasSize(int canvasWidth, int canvasHeight) {
    width = canvasWidth;
    height = canvasHeight;
    init();
  }

  public static void setPenColor() {
    setPenColor(DEFAULT_PEN_COLOR);
  }

  public static void setPenColor(Color color) {
    penColor = color;
    offscreen.setColor(penColor);
  }

  public static void setPenRadius() {
    setPenRadius(DEFAULT_PEN_RADIUS);
  }

  public static void setPenRadius(double radius) {
    if (!(radius >= 0)) {
      throw new IllegalArgumentException("pen radius must be nonnegative");
    }
    float scaledPenRadius = (float) (radius * DEFAULT_SIZE);
    BasicStroke stroke = new BasicStroke(scaledPenRadius, BasicStroke.CAP_ROUND,
        BasicStroke.JOIN_ROUND);
    // BasicStroke stroke = new BasicStroke(scaledPenRadius);
    offscreen.setStroke(stroke);
  }

  public static void setScale() {
    setXscale();
    setYscale();
  }

  public static void setXscale() {
    setXscale(DEFAULT_XMIN, DEFAULT_XMAX);
  }

  public static void setXscale(double min, double max) {
    xmin = min;
    xmax = max;
  }

  public static void setYscale() {
    setYscale(DEFAULT_YMIN, DEFAULT_YMAX);
  }

  public static void setYscale(double min, double max) {
    ymin = min;
    ymax = max;
  }

  public static void show() {
    defer = false;
    nextDraw = -1;
    draw();
  }

  public static void show(int t) {

    final long wakeTime = sleepUntilNextDraw(nextDraw);

    defer = false;
    draw();
    defer = true;

    nextDraw = wakeTime + t;
  }

  private static long sleepUntilNextDraw(long nextDraw) {

    long millis = System.currentTimeMillis();
    if (millis < nextDraw) {
      try {
        Thread.sleep(nextDraw - millis);
      } catch (InterruptedException e) {
        System.out.println("Error sleeping");
      }
      millis = nextDraw;
    }
    return millis;
  }

  private SimpleDraw() {

  }
}