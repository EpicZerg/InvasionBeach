/*    */ import java.awt.image.BufferedImage;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class Grenade
/*    */ {
/*    */   public float x;
/*    */   public float y;
/*    */   public float targetX;
/*    */   public float targetY;
/*  6 */   public float speed = 6.0F;
/*    */   public float speedX;
/*    */   public float speedY;
/*    */   public float xPercent;
/*    */   public float yPercent;
/*    */   public int damage;
/*    */   public int timer;
/*    */   public int duration;
/*    */   public BufferedImage image;
/*    */   public boolean active;
/*    */   public boolean visible;
/* 12 */   public Random r = new Random();
/*    */ 
/*    */   public Grenade(float x, float y, int targetX, int targetY, int damage)
/*    */   {
/* 16 */     this.x = x;
/* 17 */     this.y = y;
/* 18 */     this.targetX = targetX;
/* 19 */     this.targetY = targetY;
/* 20 */     this.damage = damage;
/* 21 */     this.timer = 0;
/* 22 */     this.duration = 2000;
/* 23 */     this.active = true;
/* 24 */     this.visible = true;
/*    */ 
/* 26 */     float diffX = Math.abs(targetX - x);
/* 27 */     float diffY = Math.abs(targetY - y);
/* 28 */     float totalXY = diffX + diffY;
/*    */ 
/* 30 */     if (diffX >= diffY) {
/* 31 */       this.xPercent = (diffX / totalXY);
/* 32 */       this.yPercent = (1.0F - this.xPercent);
/*    */     } else {
/* 34 */       this.yPercent = (diffY / totalXY);
/* 35 */       this.xPercent = (1.0F - this.yPercent);
/*    */     }
/*    */ 
/* 39 */     this.speedX = (this.xPercent * this.speed);
/* 40 */     this.speedY = (this.yPercent * this.speed);
/*    */ 
/* 42 */     if ((x < targetX) && (y > targetY)) {
/* 43 */       this.speedY *= -1.0F;
/*    */     }
/* 45 */     if ((x > targetX) && (y < targetY)) {
/* 46 */       this.speedX *= -1.0F;
/*    */     }
/* 48 */     if ((x > targetX) && (y > targetY)) {
/* 49 */       this.speedX *= -1.0F;
/* 50 */       this.speedY *= -1.0F;
/*    */     }
/*    */ 
/* 53 */     this.image = Core.grenade.getTileImage(0);
/*    */   }
/*    */ 
/*    */   public void update(int time)
/*    */   {
/* 58 */     if ((Math.abs(this.x - this.targetX) < 5.0F) && (Math.abs(this.y - this.targetY) < 5.0F)) {
/* 59 */       this.speedX = 0.0F;
/* 60 */       this.speedY = 0.0F;
/* 61 */       this.active = true;
/*    */     }
/* 63 */     this.x += this.speedX;
/* 64 */     this.y += this.speedY;
/*    */ 
/* 66 */     if (this.active)
/* 67 */       if (this.timer < this.duration) {
/* 68 */         this.timer += time;
/*    */       } else {
/* 70 */         Core.effects.add(new GrenadeExplosion(300, (int)this.x - 24, (int)this.y - 24));
/* 71 */         this.visible = false;
/*    */       }
/*    */   }
/*    */ 
/*    */   public float getX()
/*    */   {
/* 79 */     return this.x;
/*    */   }
/*    */ 
/*    */   public float getY() {
/* 83 */     return this.y;
/*    */   }
/*    */ 
/*    */   public int getDamage() {
/* 87 */     return this.damage;
/*    */   }
/*    */ 
/*    */   public BufferedImage getImage() {
/* 91 */     return this.image;
/*    */   }
/*    */ }