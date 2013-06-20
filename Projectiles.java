/*     */ import java.awt.Rectangle;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Projectiles
/*     */ {
/*     */   public float x;
/*     */   public float y;
/*     */   public float speedX;
/*     */   public float speedY;
/*     */   public float aimX;
/*     */   public float aimY;
/*     */   public float bulletSpeed;
/*     */   public float xPercent;
/*     */   public float yPercent;
/*     */   public int damage;
/*  11 */   public boolean alive = true;
/*     */   public Rectangle r;
/*  13 */   public ArrayList<Barricade> hitBarricades = new ArrayList();
/*  14 */   public Random rand = new Random();
/*     */   public Unit owner;
/*     */ 
/*     */   public Projectiles(int x, int y, int aimX, int aimY, int bulletSpeed, int damage, Unit owner)
/*     */   {
/*  19 */     this.x = x;
/*  20 */     this.y = y;
/*  21 */     this.bulletSpeed = bulletSpeed;
/*  22 */     this.aimX = aimX;
/*  23 */     this.aimY = aimY;
/*  24 */     this.damage = damage;
/*  25 */     this.owner = owner;
/*  26 */     this.alive = true;
/*  27 */     this.r = new Rectangle(x, y, 3, 3);
/*     */ 
/*  30 */     float diffX = Math.abs(aimX - x);
/*  31 */     float diffY = Math.abs(aimY - y);
/*  32 */     float totalXY = diffX + diffY;
/*     */ 
/*  34 */     if (diffX >= diffY) {
/*  35 */       this.xPercent = (diffX / totalXY);
/*  36 */       this.yPercent = (1.0F - this.xPercent);
/*     */     } else {
/*  38 */       this.yPercent = (diffY / totalXY);
/*  39 */       this.xPercent = (1.0F - this.yPercent);
/*     */     }
/*  41 */     this.speedX = (this.xPercent * bulletSpeed);
/*  42 */     this.speedY = (this.yPercent * bulletSpeed);
/*     */ 
/*  45 */     if ((x < aimX) && (y > aimY)) {
/*  46 */       this.speedY *= -1.0F;
/*     */     }
/*  48 */     if ((x > aimX) && (y < aimY)) {
/*  49 */       this.speedX *= -1.0F;
/*     */     }
/*  51 */     if ((x > aimX) && (y > aimY)) {
/*  52 */       this.speedX *= -1.0F;
/*  53 */       this.speedY *= -1.0F;
/*     */     }
/*     */ 
/*  56 */     Core.effects.add(new MuzzleFlash(120, x, y));
/*     */   }
/*     */ 
/*     */   public void update()
/*     */   {
/*  61 */     this.x += this.speedX;
/*  62 */     this.y += this.speedY;
/*  63 */     this.r.setBounds((int)this.x, (int)this.y, 3, 3);
/*  64 */     checkOutOfBounds();
/*  65 */     checkCollision();
/*     */   }
/*     */ 
/*     */   public void checkOutOfBounds()
/*     */   {
/*  70 */     if ((this.x < 0.0F) || (this.x > 850.0F)) {
/*  71 */       this.alive = false;
/*     */     }
/*  73 */     if ((this.y < -2000.0F) || (this.y > 480.0F))
/*  74 */       this.alive = false;
/*     */   }
/*     */ 
/*     */   public void checkCollision()
/*     */   {
/*  79 */     for (int i = 0; i < Core.units.size(); i++) {
/*  80 */       Unit u = (Unit)Core.units.get(i);
/*  81 */       if ((this.r.intersects(u.getCollision())) && (u != this.owner)) {
/*  82 */         u.setDamage(this.damage);
/*  83 */         this.alive = false;
/*     */       }
/*     */     }
/*     */ 
/*  87 */     for (int i = 0; i < Core.enemies.size(); i++) {
/*  88 */       Unit u = (Unit)Core.enemies.get(i);
/*  89 */       if ((this.r.intersects(u.getCollision())) && (u != this.owner)) {
/*  90 */         u.setDamage(this.damage);
/*  91 */         this.alive = false;
/*     */       }
/*     */     }
/*     */ 
/*  95 */     for (int i = 0; i < Core.barricades.size(); i++) {
/*  96 */       Barricade b = (Barricade)Core.barricades.get(i);
/*  97 */       if ((!this.hitBarricades.contains(b)) && 
/*  98 */         (this.r.intersects(b.getBox()))) {
/*  99 */         int chance = this.rand.nextInt(7);
/* 100 */         if (chance == 6) {
/* 101 */           this.hitBarricades.add(b);
/*     */         } else {
/* 103 */           this.alive = false;
/* 104 */           Core.effects.add(new MuzzleFlash(200, (int)this.x, (int)this.y));
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 110 */     for (int i = 0; i < Core.bunkers.size(); i++) {
/* 111 */       Bunker b = (Bunker)Core.bunkers.get(i);
/* 112 */       if (this.r.intersects(b.collision)) {
/* 113 */         this.alive = false;
/* 114 */         Core.effects.add(new MuzzleFlash(200, (int)this.x, (int)this.y));
/*     */       }
/*     */     }
/*     */   }
/*     */ }