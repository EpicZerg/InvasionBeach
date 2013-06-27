
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class EnemyRifle extends Unit
/*     */ {
/*   8 */   public int enemySizeX = 32;
/*   9 */   public int enemySizeY = 32;
/*  10 */   public int attackRangeX = 700; public int attackRangeY = 700;
/*  11 */   Random r = new Random();
/*     */   public Line2D hitCheck;
/*  13 */   public boolean stationary = true;
/*     */ 
/*     */   public EnemyRifle(int x, int y)
/*     */   {
/*  17 */     super(x, y);
/*  18 */     this.hitCheck = new Line2D.Double();
/*  19 */     this.hitCheck.setLine(x, y, x, y);
/*  20 */     this.tilesetMovement = Core.enemyRifleManDirections;
/*  21 */     this.image = this.tilesetMovement.getTileImage(2);
/*  22 */     this.name = "Enemy Rifleman";
/*  23 */     this.attackRange = new Rectangle(x + this.enemySizeX / 2 - this.attackRangeX / 2, y + 
/*  24 */       this.enemySizeY / 2 - this.attackRangeY / 2, this.attackRangeX, this.attackRangeY);
/*     */   }
/*     */ 
/*     */   public EnemyRifle(int x, int y, int speedX, int speedY)
/*     */   {
/*  29 */     super(x, y, speedX, speedY);
/*  30 */     this.tilesetMovement = Core.enemyRifleManDirections;
/*  31 */     this.image = this.tilesetMovement.getTileImage(2);
/*  32 */     this.name = "Enemy Rifleman";
/*  33 */     this.attackRange = new Rectangle(x + this.enemySizeX / 2 - this.attackRangeX / 2, y + 
/*  34 */       this.enemySizeY / 2 - this.attackRangeY / 2, this.attackRangeX, this.attackRangeY);
/*  35 */     this.hitCheck.setLine(x, y, x, y);
/*     */   }
/*     */ 
/*     */   public void update(int time)
/*     */   {
/*  41 */     super.update(time);
/*  42 */     this.attackRange = new Rectangle(this.x + this.enemySizeX / 2 - this.attackRangeX / 2, this.y + 
/*  43 */       this.enemySizeY / 2 - this.attackRangeY / 2, this.attackRangeX, this.attackRangeY);
/*     */   }
/*     */ 
/*     */   public BufferedImage getImage()
/*     */   {
/*  48 */     return this.image;
/*     */   }
/*     */ 
/*     */   public void setImage(BufferedImage image) {
/*  52 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public void handleCollision()
/*     */   {
/*  58 */     super.handleCollision();
/*  59 */     for (int i = 0; i < Core.units.size(); i++) {
/*  60 */       Unit u = (Unit)Core.units.get(i);
/*  61 */       if (this.attackRange.intersects(u.collision)) {
/*  62 */         this.hitCheck.setLine(this.x + 8, this.y + 8, u.getX() + 16, u.getY() + 16);
/*  63 */         this.autoFire = true;
/*  64 */         checkFire();
/*  65 */         fire(u.x - 32 + this.r.nextInt(64), u.y - 32 + this.r.nextInt(64));
/*     */       } else {
/*  67 */         this.autoFire = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void handleDirection(int targetX, int targetY)
/*     */   {
/*  75 */     double hitX = this.hitCheck.getX2();
/*  76 */     double hitY = this.hitCheck.getY2();
/*     */ 
/*  78 */     if (this.y < hitY) {
/*  79 */       if (this.x > hitX) {
/*  80 */         if (Math.abs(this.x - hitX) > Math.abs(this.y - 
/*  81 */           hitY))
/*  82 */           this.image = Core.enemyRifleManDirections.getTileImage(3);
/*     */         else {
/*  84 */           this.image = Core.enemyRifleManDirections.getTileImage(2);
/*     */         }
/*     */       }
/*  87 */       else if (Math.abs(this.x - hitX) > Math.abs(this.y - 
/*  88 */         hitY))
/*  89 */         this.image = Core.enemyRifleManDirections.getTileImage(1);
/*     */       else {
/*  91 */         this.image = Core.enemyRifleManDirections.getTileImage(2);
/*     */       }
/*     */ 
/*     */     }
/*  95 */     else if (this.x > hitX) {
/*  96 */       if (Math.abs(this.x - hitX) > Math.abs(this.y - 
/*  97 */         hitY))
/*  98 */         this.image = Core.enemyRifleManDirections.getTileImage(3);
/*     */       else {
/* 100 */         this.image = Core.enemyRifleManDirections.getTileImage(0);
/*     */       }
/*     */     }
/* 103 */     else if (Math.abs(this.x - hitX) > Math.abs(this.y - 
/* 104 */       hitY))
/* 105 */       this.image = Core.enemyRifleManDirections.getTileImage(1);
/*     */     else
/* 107 */       this.image = Core.enemyRifleManDirections.getTileImage(0);
/*     */   }
/*     */ 
/*     */   public void checkFire()
/*     */   {
/* 114 */     for (int i = 0; i < Core.barricades.size(); i++) {
/* 115 */       Barricade b = (Barricade)Core.barricades.get(i);
/* 116 */       if (this.hitCheck.intersects(b.getBox())) {
/* 117 */         this.autoFire = false;
/*     */       }
/*     */     }
/* 120 */     for (int i = 0; i < Core.enemies.size(); i++) {
/* 121 */       EnemyRifle e = (EnemyRifle)Core.enemies.get(i);
/* 122 */       if ((this.hitCheck.intersects(e.collision)) && (e != this))
/* 123 */         this.autoFire = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void fire(int targetX, int targetY)
/*     */   {
/* 130 */     handleDirection(targetX, targetY);
/* 131 */     if ((this.magazine > 0) && (this.autoFire) && (this.elapsed >= this.nextShot)) {
/* 132 */       Core.projectiles.add(new Projectiles(this.x + 8, this.y + 8, targetX, 
/* 133 */         targetY, 12, 9 + this.r.nextInt(6), this));
/* 134 */       this.magazine -= 1;
/* 135 */       this.elapsed = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   public Line2D getHitCheck() {
/* 140 */     return this.hitCheck;
/*     */   }
/*     */ 
/*     */   public void setHitCheck(Line2D hitCheck) {
/* 144 */     this.hitCheck = hitCheck;
/*     */   }
}