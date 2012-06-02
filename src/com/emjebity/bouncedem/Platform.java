package com.emjebity.bouncedem;

import android.widget.ImageView;

/**
 * A platform in the bouncing game.
 * @author Christopher Foo
 *
 */
public class Platform {
  
  /**
   * The image representing the platform on the screen.
   */
  private ImageView image;
  
  /**
   * The width of the platform
   */
  private int platformWidth;
  
  /**
   * The height of the platform;
   */
  private int platformHeight;
  
  /**
   * Location of platform.
   */
  private int top, bottom, left, right;
  
  /**
   * Creates a new platform from the given image.
   * @param image
   */
  public Platform(ImageView image) {
    this.image = image;
  }
  
  /**
   * Move the platform on the screen.
   * @param verticalChange The amount to move the platform vertically (positive values indicate a downward movement).
   * @param horizontalChange The amount to move the platform horizontally (positive values indicate a rightward movement).
   */
  public void move(int verticalChange, int horizontalChange) {
    this.top = this.image.getTop();
    this.bottom = this.image.getBottom();
    this.left = this.image.getLeft();
    this.right = this.image.getRight();
    this.platformHeight = this.image.getHeight();
    this.platformWidth = this.image.getWidth();
    
    this.left += horizontalChange;
    this.right += horizontalChange;
    this.top += verticalChange;
    this.bottom+= verticalChange;
    
    this.image.layout(this.left, this.top, this.right, this.bottom);
  }
}
