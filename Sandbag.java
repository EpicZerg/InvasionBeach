
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class Sandbag
/*    */ {
/*    */   public int health;
/*    */   public int x;
/*    */   public int y;
/*  7 */   public boolean destroyed = false;
/*    */   public BufferedImage image;
/*    */   public Rectangle collision;
/*    */ 
/*    */   public Sandbag(int x, int y)
/*    */   {
/* 12 */     this.x = x;
/* 13 */     this.y = y;
/* 14 */     this.destroyed = true;
/* 15 */     this.image = Core.imageSandbags.getTileImage(0);
/* 16 */     this.collision = new Rectangle(x, y + this.image.getHeight() / 2, this.image.getWidth(), this.image.getHeight() / 2);
/*    */   }
/*    */ 
/*    */   public void update() {
/* 20 */     checkDestroyed();
/*    */   }
/*    */ 
/*    */   public void setDamage(int damage) {
/* 24 */     this.health -= damage;
/*    */   }
/*    */ 
/*    */   public void checkDestroyed() {
/* 28 */     if (this.health <= 0)
/* 29 */       this.destroyed = true;
/*    */   }
/*    */ 
/*    */   public int getX()
/*    */   {
/* 34 */     return this.x;
/*    */   }
/*    */ 
/*    */   public int getY() {
/* 38 */     return this.y;
/*    */   }
/*    */ 
/*    */   public BufferedImage getImage() {
/* 42 */     return this.image;
/*    */   }
/*    */ 
/*    */   public Rectangle getCollision() {
/* 46 */     return this.collision;
/*    */   }
/*    */ }
