/**
 * This file defines a basic interface for any object which one may draw and
 * update.
 * 
 * @version 1.0
 */

package edu.sdsu.cs.prog3;

/**
 * Objects of type IRenderable indicate they will produce output to the screen
 * when the user calls the appropriate methods.
 */
public interface IRenderable {

  /**
   * renders the object to the screen.
   */
  void draw();

  /**
   * performs a periodic update of the model.
   */
  void update();

}