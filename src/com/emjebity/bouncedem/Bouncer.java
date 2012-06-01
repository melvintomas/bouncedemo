package com.emjebity.bouncedem;

import android.widget.ImageView;

/**
 * A bouncing image.
 * @author Christopher Foo
 *
 */
public class Bouncer {
  
  /**
   * The image that will be bouncing on the screen.
   */
  private ImageView image;
  
  /**
   * The height of the screen.
   */
  private int screenHeight;
  
  /**
   * The width of the screen.
   */
  private int screenWidth;
  
  /**
   * The current horizontal speed of the bouncing image.
   */
  private int horizontalSpeed = 0;
  
  /**
   * The current vertical speed of the bouncing image.
   */
  private int currentVerticalSpeed = 0;
  
  /**
   * The vertical acceleration of the bouncing image (i.e. gravity).
   */
  private final int accel;
  
  /**
   * The maximum vertical velocity that can be achieved by the image.
   */
  private final int terminalVelocity;
  
  /**
   * Location of the bouncing image.
   */
  private int top, bottom, left, right;
  
  /**
   * Creates a new Bouncer and initializes its fields.
   * @param image The image to be displayed.
   * @param screenHeight The height of the screen.
   * @param screenWidth The width of the screen.
   * @param accel The vertical acceleration or gravity.
   * @param terminalVelocity The maximum vertical velocity of the image.
   */
  public Bouncer (ImageView image, int screenHeight, int screenWidth, int accel, int terminalVelocity) {
    this.image = image;
    this.screenHeight = screenHeight;
    this.screenWidth = screenWidth;
    this.accel = accel;
    this.terminalVelocity = terminalVelocity;
  }
  
  /**
   * Handle the falling and bouncing animation.
   */
  public void bounce() {
    
    // Get current location of the image
    this.top = this.image.getTop();
    this.bottom = this.image.getBottom();
    this.left = this.image.getLeft();
    this.right = this.image.getRight();
    
    // Bounce
    if (this.bottom >= this.screenHeight) {
      this.currentVerticalSpeed = -Math.abs(this.currentVerticalSpeed);
    }
    
    else {  
      // Fall
      int magnitude = Math.abs(this.currentVerticalSpeed);
      if(magnitude < this.terminalVelocity) {
        if(this.terminalVelocity - magnitude == this.accel) {
          this.currentVerticalSpeed = this.terminalVelocity;
        }
      
        else {
          this.currentVerticalSpeed += Math.abs(this.accel);
        }
      }
    }

    // Update location.
    this.top += this.currentVerticalSpeed;
    this.bottom += this.currentVerticalSpeed;
    this.left -= this.horizontalSpeed;
    this.right -= this.horizontalSpeed;
    
    // Adjust new location for screen boundaries.
    if (this.left < 0) {
      this.left = 0;
    } else if (this.right > this.screenWidth) {
      this.right = this.screenWidth;
    }
    
    if(this.top < 0) {
      this.top = 0;
    } else if (this.bottom > this.screenHeight) {
      this.bottom = this.screenHeight;
    }
    
    // Update image with new location.
    this.image.layout(this.left, this.top, this.right, this.bottom);
  }
  
  
  /**
   * Sets the horizontal speed of the bouncing image.
   * @param newSpeed The new horizontal speed.
   */
  public void setHorizontalSpeed (int newSpeed) {
    this.horizontalSpeed = newSpeed;
  }
  
  /**
   * Gets the image.
   * @return The image.
   */
  public ImageView getImage() {
    return this.image;
  }
}
