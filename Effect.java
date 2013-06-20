/*    */ import java.awt.Image;
/*    */ import java.awt.Rectangle;
/*    */ 
/*    */ public class Effect
/*    */ {
/*    */   public Animation anim;
/*  8 */   public int timeElapsed = 0;
/*    */   public int duration;
/* 10 */   public boolean elapsed = false;
/*    */   public int x;
/*    */   public int y;
/*    */   public Image image;
/*    */   private Rectangle lowDamage;
/*    */   private Rectangle highDamage;
/* 14 */   public boolean alive = true;
/*    */ 
/*    */   public Effect(int duration, int x, int y) {
/* 17 */     this.duration = duration;
/* 18 */     this.x = x;
/* 19 */     this.y = y;
/* 20 */     this.lowDamage = new Rectangle(0, 0, 0, 0);
/* 21 */     this.highDamage = new Rectangle(0, 0, 0, 0);
/*    */   }
/*    */ 
/*    */   public void update(int time) {
/* 25 */     this.timeElapsed += time;
/* 26 */     if (this.timeElapsed >= this.duration) {
/* 27 */       this.elapsed = true;
/* 28 */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean isElapsed()
/*    */   {
/* 34 */     return this.elapsed;
/*    */   }
/*    */ 
/*    */   public void setElapsed(boolean elapsed) {
/* 38 */     this.elapsed = elapsed;
/*    */   }
/*    */ 
/*    */   public int getX() {
/* 42 */     return this.x;
/*    */   }
/*    */ 
/*    */   public int getY() {
/* 46 */     return this.y;
/*    */   }
/*    */ 
/*    */   public void setX(int x) {
/* 50 */     this.x = x;
/*    */   }
/*    */ 
/*    */   public void setY(int y) {
/* 54 */     this.y = y;
/*    */   }
/*    */ 
/*    */   public Image getImage() {
/* 58 */     return this.image;
/*    */   }
/*    */ 
/*    */   public void setImage(Image image) {
/* 62 */     this.image = image;
/*    */   }
/*    */ 
/*    */   public Rectangle getLowDamage() {
/* 66 */     return this.lowDamage;
/*    */   }
/*    */ 
/*    */   public Rectangle getHighDamage() {
/* 70 */     return this.highDamage;
/*    */   }
/*    */ 
/*    */   public void setLowDamage(Rectangle lowDamage) {
/* 74 */     this.lowDamage = lowDamage;
/*    */   }
/*    */ 
/*    */   public void setHighDamage(Rectangle highDamage) {
/* 78 */     this.highDamage = highDamage;
/*    */   }
/*    */ }
