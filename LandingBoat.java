/*    */ import java.awt.Image;
/*    */ 
/*    */ public class LandingBoat
/*    */ {
/*  5 */   public int speedX = 0; public int speedY = -1;
/*  6 */   public int x = 500; public int y = 700;
/*    */   public Image image;
/*  8 */   public boolean triggered = false;
/*  9 */   public final int MAX = 350;
/*    */ 
/*    */   public LandingBoat(int x, int y) {
/* 12 */     this.x = x;
/* 13 */     this.y = y;
/*    */   }
/*    */ 
/*    */   public void update()
/*    */   {
/* 18 */     if (this.y <= 350) {
/* 19 */       this.speedX = 0;
/* 20 */       this.speedY = 0;
/* 21 */       if (!this.triggered)
/* 22 */         createUnits(4);
/*    */     }
/*    */     else {
/* 25 */       this.x += this.speedX;
/* 26 */       this.y += this.speedY;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void createUnits(int amount)
/*    */   {
/* 32 */     Core.spawnUnits(this.x, this.y);
/* 33 */     this.triggered = true;
/*    */   }
/*    */ 
/*    */   public int getSpeedX()
/*    */   {
/* 38 */     return this.speedX;
/*    */   }
/*    */ 
/*    */   public int getSpeedY() {
/* 42 */     return this.speedY;
/*    */   }
/*    */ 
/*    */   public int getX() {
/* 46 */     return this.x;
/*    */   }
/*    */ 
/*    */   public int getY() {
/* 50 */     return this.y;
/*    */   }
/*    */ 
/*    */   public Image getImage() {
/* 54 */     return this.image;
/*    */   }
/*    */ 
/*    */   public void setSpeedX(int speedX) {
/* 58 */     this.speedX = speedX;
/*    */   }
/*    */ 
/*    */   public void setSpeedY(int speedY) {
/* 62 */     this.speedY = speedY;
/*    */   }
/*    */ 
/*    */   public void setX(int x) {
/* 66 */     this.x = x;
/*    */   }
/*    */ 
/*    */   public void setY(int y) {
/* 70 */     this.y = y;
/*    */   }
/*    */ }