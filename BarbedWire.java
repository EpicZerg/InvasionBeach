
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.BufferedImage;
/*    */ 
/*    */ public class BarbedWire
/*    */ {
/*    */   public int health;
/*    */   public int x;
/*    */   public int y;
/*  7 */   public boolean destroyed = false;
/*    */   public BufferedImage image;
/*    */   public Rectangle collision;
/*    */ 
/*    */   public BarbedWire(int x, int y)
/*    */   {
/* 12 */     this.x = x;
/* 13 */     this.y = y;
/* 14 */     this.destroyed = false;
/* 15 */     this.image = Core.imageBarbedWire.getTileImage(0);
/* 16 */     this.collision = new Rectangle(x, y + this.image.getHeight() / 2, this.image.getWidth(), this.image.getHeight() / 2);
/*    */   }
/*    */ 
/*    */   public void update()
/*    */   {
/* 21 */     checkDestroyed();
/* 22 */     this.collision.setBounds(this.x, this.y + this.image.getHeight() / 2, this.image.getWidth(), this.image.getHeight() / 2);
/*    */   }
/*    */ 
/*    */   public void setDamage(int damage)
/*    */   {
/* 29 */     this.health -= damage;
/*    */   }
/*    */ 
/*    */   public void checkDestroyed() {
/* 33 */     if (this.health <= 0)
/* 34 */       this.destroyed = true;
/*    */   }
/*    */ 
/*    */   public int getX()
/*    */   {
/* 39 */     return this.x;
/*    */   }
/*    */ 
/*    */   public int getY() {
/* 43 */     return this.y;
/*    */   }
/*    */ 
/*    */   public BufferedImage getImage() {
/* 47 */     return this.image;
/*    */   }
/*    */ 
/*    */   public Rectangle getCollision()
/*    */   {
/* 52 */     return this.collision;
/*    */   }
/*    */ }