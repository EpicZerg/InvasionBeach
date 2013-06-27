
/*     */ import java.awt.Component;
/*     */ import java.awt.Point;
/*     */ import java.awt.PointerInfo;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Unit
/*     */ {
/*  17 */   public float speedX = 0.0F; public float speedY = 0.0F;
/*  18 */   public int x = 100; public int y = 100;
/*     */   private Barricade barricade;
/*     */   public Rectangle collision;
/*  21 */   public boolean leader = false;
/*  22 */   public String name = "Unit";
/*  23 */   private boolean moveTo = false;
/*  24 */   public int maxHP = 100; public int hp = 100;
/*     */   public int bestPosX;
/*     */   public int bestPosY;
/*     */   public double hpPerPixel;
/*     */   public Rectangle r_maxHP;
/*     */   public Rectangle r_hp;
/*  28 */   public Unit.Status status = Unit.Status.Alive;
/*  29 */   public boolean alive = true;
/*  30 */   public boolean autoFire = false;
/*  31 */   public int nextShot = 150; public int elapsed = 0;
/*  32 */   public int nextBarbedDamage = 150; public int elapsedBarbedDamage = 0;
/*  33 */   public int tX = 0; public int tY = 0;
/*     */   public PointerInfo mouseInfo;
/*     */   public Point mouseLocation;
/*     */   public Point centerUnit;
/*     */   public Component comp;
/*  37 */   public int magazine = 30;
/*     */   public int reloadTimer;
/*  37 */   public int maxMagazine = 30;
/*  38 */   final int RELOADTIME = 3000;
/*  39 */   public boolean reloading = false; public boolean speech = true;
/*  40 */   public boolean goingUp = false; public boolean goingRight = false; public boolean goingLeft = false;
/*  41 */   public boolean goingDown = false;
/*  42 */   final int SPEECHCOOLDOWN = 5000;
/*  43 */   public int speechTimer = 0;
/*     */   public BufferedImage image;
/*     */   public Rectangle attackRange;
/*     */   public Tileset tilesetMovement;
/*  47 */   public Polygon fieldUp = new Polygon(); public Polygon fieldDown = new Polygon();
/*  48 */   public Polygon fieldRight = new Polygon(); public Polygon fieldLeft = new Polygon();
/*  49 */   public Line2D hitCheck = new Line2D.Double();
/*  50 */   public ArrayList<Item> inventory = new ArrayList();
/*  51 */   Random r = new Random();
/*     */ 
/*     */   public Unit(int x, int y) {
/*  54 */     initializeInventory();
/*  55 */     this.tilesetMovement = Core.unitDirections;
/*  56 */     this.x = x;
/*  57 */     this.y = y;
/*     */ 
/*  59 */     this.collision = new Rectangle(x + 6, y, 20, 32);
/*  60 */     sethpPerPixel();
/*  61 */     createHealthBar();
/*  62 */     this.image = this.tilesetMovement.getTileImage(0);
/*     */   }
/*     */ 
/*     */   public Unit(int x, int y, int speedX, int speedY) {
/*  66 */     initializeInventory();
/*  67 */     this.tilesetMovement = Core.unitDirections;
/*  68 */     this.x = x;
/*  69 */     this.y = y;
/*  70 */     this.speedX = speedX;
/*  71 */     this.speedY = speedY;
/*     */ 
/*  74 */     this.collision = new Rectangle(x + 6, y, 20, 32);
/*  75 */     sethpPerPixel();
/*  76 */     createHealthBar();
/*  77 */     this.image = this.tilesetMovement.getTileImage(0);
/*     */   }
/*     */ 
/*     */   public void shoutClass() {
/*  81 */     System.out.println(this.name);
/*     */   }
/*     */ 
/*     */   public void sethpPerPixel()
/*     */   {
/*  86 */     this.hpPerPixel = (32.0D / this.maxHP);
/*     */   }
/*     */ 
/*     */   public void setDamage(int damage) {
/*  90 */     this.hp -= damage;
/*  91 */     Core.groundEffects.add(new Blood(2000, this.x, this.y));
/*  92 */     Core.combatText.add(new CombatText(damage, this.x, this.y));
/*  93 */     if (this.speech) {
/*  94 */       Core.effects.add(new SpeechBubble(1000, this.x, this.y, this));
/*  95 */       this.speech = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void specialAbility()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void checkDeath() {
/* 104 */     if ((this.hp <= 0) && (this.status == Unit.Status.Alive)) {
/* 105 */       System.out.println(this.name + " died.");
/* 106 */       this.status = Unit.Status.Dead;
/* 107 */       this.alive = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void createHealthBar() {
/* 112 */     this.r_maxHP = new Rectangle(this.x, this.y - 10, 32, 5);
/* 113 */     this.r_hp = new Rectangle(this.x, this.y - 10, (int)(this.hp * this.hpPerPixel), 5);
/*     */   }
/*     */ 
/*     */   public void update(int time) {
/* 117 */     if ((!this.leader) && (isMoveTo())) {
/* 118 */       moveToBest();
/*     */     }
/* 120 */     move(2);
/* 121 */     handleCoverCollision(time);
/* 122 */     this.x = ((int)(this.x + this.speedX));
/* 123 */     this.y = ((int)(this.y + this.speedY));
/* 124 */     checkOutOfBounds();
/* 125 */     handleCollision();
/* 126 */     this.collision.setBounds(this.x + 6, this.y, 20, 32);
/* 127 */     createHealthBar();
/* 128 */     checkDeath();
/* 129 */     this.elapsed += time;
/* 130 */     fire(this.tX, this.tY);
/* 131 */     reloadCheck(time);
/* 132 */     updateSpeech(time);
/*     */   }
/*     */ 
/*     */   private void checkOutOfBounds()
/*     */   {
/* 137 */     if (this.x < 0) {
/* 138 */       this.x = 0;
/*     */     }
/* 140 */     if (this.x + 32 > 800) {
/* 141 */       this.x = 768;
/*     */     }
/* 143 */     if (this.y > 448) {
/* 144 */       this.y = 448;
/*     */     }
/* 146 */     if (this.y < -1400)
/* 147 */       this.y = -1400;
/*     */   }
/*     */ 
/*     */   private void checkDirection(int tx, int ty)
/*     */   {
/* 153 */     if (Core.activeClass == this)
/* 154 */       if (this.y < ty) {
/* 155 */         if (this.x > tx) {
/* 156 */           if (Math.abs(this.x - tx) > Math.abs(this.y - ty))
/* 157 */             this.image = Core.unitDirections.getTileImage(3);
/*     */           else {
/* 159 */             this.image = Core.unitDirections.getTileImage(2);
/*     */           }
/*     */         }
/* 162 */         else if (Math.abs(this.x - tx) > Math.abs(this.y - ty))
/* 163 */           this.image = Core.unitDirections.getTileImage(1);
/*     */         else {
/* 165 */           this.image = Core.unitDirections.getTileImage(2);
/*     */         }
/*     */ 
/*     */       }
/* 169 */       else if (this.x > tx) {
/* 170 */         if (Math.abs(this.x - tx) > Math.abs(this.y - ty))
/* 171 */           this.image = Core.unitDirections.getTileImage(3);
/*     */         else {
/* 173 */           this.image = Core.unitDirections.getTileImage(0);
/*     */         }
/*     */       }
/* 176 */       else if (Math.abs(this.x - tx) > Math.abs(this.y - ty))
/* 177 */         this.image = Core.unitDirections.getTileImage(1);
/*     */       else
/* 179 */         this.image = Core.unitDirections.getTileImage(0);
/*     */   }
/*     */ 
/*     */   public void reloadCheck(int time)
/*     */   {
/* 188 */     if (this.reloading) {
/* 189 */       this.reloadTimer += time;
/* 190 */       if (this.reloadTimer > 3000) {
/* 191 */         this.reloading = false;
/* 192 */         this.reloadTimer = 0;
/* 193 */         this.magazine = 30;
/* 194 */         System.out.println("Reloaded!");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void fire(int targetX, int targetY) {
/* 200 */     if ((this.magazine > 0) && (this.autoFire) && (this.elapsed >= this.nextShot)) {
/* 201 */       Core.projectiles.add(new Projectiles(this.x + 8, this.y + 8, targetX, targetY - 
/* 202 */         Core.offsetY, 16, this.r.nextInt(5) + 16, this));
/* 203 */       this.magazine -= 1;
/* 204 */       this.elapsed = 0;
/*     */     }
/* 206 */     checkDirection(targetX, targetY - Core.offsetY);
/*     */   }
/*     */ 
/*     */   public void moveToBest()
/*     */   {
/* 211 */     Random r = new Random();
/* 212 */     if ((Math.abs(this.x - this.bestPosX) < 2) && (Math.abs(this.y - this.bestPosY) < 2)) {
/* 213 */       this.speedX = 0.0F;
/* 214 */       this.speedY = 0.0F;
/* 215 */       setMoveTo(false);
/* 216 */       return;
/*     */     }
/*     */ 
/* 219 */     if (this.x - this.bestPosX > 0)
/* 220 */       this.speedX = -1.0F;
/*     */     else {
/* 222 */       this.speedX = 1.0F;
/*     */     }
/*     */ 
/* 225 */     if (this.y - this.bestPosY > 0)
/* 226 */       this.speedY = -1.0F;
/*     */     else {
/* 228 */       this.speedY = 1.0F;
/*     */     }
/*     */ 
/* 231 */     if (this.x - this.bestPosX > 40)
/* 232 */       this.speedX = (-2 + r.nextInt(2) * -1);
/* 233 */     else if (this.x - this.bestPosX < -40) {
/* 234 */       this.speedX = (2 + r.nextInt(2));
/*     */     }
/*     */ 
/* 237 */     if (this.y - this.bestPosY > 40)
/* 238 */       this.speedY = (-2 + r.nextInt(2) * -1);
/* 239 */     else if (this.y - this.bestPosY < -40)
/* 240 */       this.speedX = (2 + r.nextInt(2));
/*     */   }
/*     */ 
/*     */   public Barricade getBarricade()
/*     */   {
/* 245 */     return this.barricade;
/*     */   }
/*     */ 
/*     */   public void setBarricade(Barricade barricade) {
/* 249 */     this.barricade = barricade;
/*     */   }
/*     */ 
/*     */   public Rectangle getCollision() {
/* 253 */     return this.collision;
/*     */   }
/*     */ 
/*     */   public void setLeader(boolean leader) {
/* 257 */     this.leader = leader;
/*     */   }
/*     */ 
/*     */   public void moveUp(int speed) {
/* 261 */     if ((this.goingUp) && (!this.goingDown))
/*     */     {
/* 263 */       this.speedY = (-speed);
/* 264 */       this.moveTo = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void moveDown(int speed) {
/* 269 */     if ((this.goingDown) && (!this.goingUp))
/*     */     {
/* 271 */       this.speedY = speed;
/* 272 */       this.moveTo = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void moveLeft(int speed) {
/* 277 */     if ((this.goingLeft) && (!this.goingRight))
/*     */     {
/* 279 */       this.speedX = (-speed);
/* 280 */       this.moveTo = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void moveRight(int speed) {
/* 285 */     if ((this.goingRight) && (!this.goingLeft))
/*     */     {
/* 287 */       this.speedX = speed;
/* 288 */       this.moveTo = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void stop() {
/* 293 */     this.speedX = 0.0F;
/* 294 */     this.speedY = 0.0F;
/* 295 */     this.moveTo = false;
/*     */   }
/*     */ 
/*     */   public void handleCollision()
/*     */   {
/* 300 */     for (int i = 0; i < Core.bunkers.size(); i++) {
/* 301 */       Bunker bunker = (Bunker)Core.bunkers.get(i);
/* 302 */       if (bunker.collision.intersects(this.collision))
/*     */       {
/* 304 */         if (bunker.collision.x + bunker.collision.width < this.collision.x + 
/* 304 */           this.collision.width)
/* 305 */           this.x = (this.collision.x - 5);
/* 306 */         else if (bunker.collision.x > this.collision.x)
/* 307 */           this.x = (bunker.collision.x - 32 + 5);
/* 308 */         else if (bunker.collision.y > this.collision.y)
/* 309 */           this.y = (this.collision.y - 1);
/*     */         else
/* 311 */           this.y = (bunker.collision.y + bunker.collision.height + 1);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void handleCoverCollision(int time)
/*     */   {
/* 318 */     for (int i = 0; i < Core.barricades.size(); i++) {
/* 319 */       Barricade b = (Barricade)Core.barricades.get(i);
/* 320 */       if (b.getBox().intersects(this.collision)) {
/* 321 */         if (this.speedX > 0.0F)
/* 322 */           this.speedX = 1.0F;
/* 323 */         else if (this.speedX < 0.0F) {
/* 324 */           this.speedX = -1.0F;
/*     */         }
/*     */ 
/* 327 */         if (this.speedY > 0.0F)
/* 328 */           this.speedY = 1.0F;
/* 329 */         else if (this.speedY < 0.0F) {
/* 330 */           this.speedY = -1.0F;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 336 */     this.elapsedBarbedDamage += time;
/*     */ 
/* 338 */     for (int i = 0; i < Core.barbedWire.size(); i++) {
/* 339 */       BarbedWire b = (BarbedWire)Core.barbedWire.get(i);
/* 340 */       if (b.collision.intersects(this.collision)) {
/* 341 */         this.speedX /= 2.0F;
/* 342 */         this.speedY /= 2.0F;
/* 343 */         if (this.elapsedBarbedDamage > this.nextBarbedDamage) {
/* 344 */           setDamage(3);
/* 345 */           this.elapsedBarbedDamage = 0;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 351 */     for (int i = 0; i < Core.sandbags.size(); i++) {
/* 352 */       Sandbag b = (Sandbag)Core.sandbags.get(i);
/* 353 */       if (b.collision.intersects(this.collision)) {
/* 354 */         this.speedX /= 2.0F;
/* 355 */         this.speedY /= 2.0F;
/* 356 */         if (this.elapsedBarbedDamage > this.nextBarbedDamage)
/*     */         {
/* 358 */           this.elapsedBarbedDamage = 0;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isGoingUp()
/*     */   {
/* 366 */     return this.goingUp;
/*     */   }
/*     */ 
/*     */   public void move(int speed) {
/* 370 */     moveDown(speed);
/* 371 */     moveUp(speed);
/* 372 */     moveLeft(speed);
/* 373 */     moveRight(speed);
/*     */   }
/*     */ 
/*     */   public void updateSpeech(int time) {
/* 377 */     if (!this.speech) {
/* 378 */       this.speechTimer += time;
/* 379 */       if (this.speechTimer > 5000) {
/* 380 */         this.speech = true;
/* 381 */         this.speechTimer = 0;
/* 382 */         System.out.println("Speech enabled");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isGoingLeft() {
/* 388 */     return this.goingLeft;
/*     */   }
/*     */ 
/*     */   public boolean isGoingDown() {
/* 392 */     return this.goingDown;
/*     */   }
/*     */ 
/*     */   public void setGoingUp(boolean goingUp) {
/* 396 */     this.goingUp = goingUp;
/*     */   }
/*     */ 
/*     */   public void setGoingRight(boolean goingRight) {
/* 400 */     this.goingRight = goingRight;
/*     */   }
/*     */ 
/*     */   public void setGoingLeft(boolean goingLeft) {
/* 404 */     this.goingLeft = goingLeft;
/*     */   }
/*     */ 
/*     */   public void setGoingDown(boolean goingDown) {
/* 408 */     this.goingDown = goingDown;
/*     */   }
/*     */ 
/*     */   public int getX() {
/* 412 */     return this.x;
/*     */   }
/*     */ 
/*     */   public int getY() {
/* 416 */     return this.y;
/*     */   }
/*     */ 
/*     */   public float getSpeedX() {
/* 420 */     return this.speedX;
/*     */   }
/*     */ 
/*     */   public float getSpeedY() {
/* 424 */     return this.speedY;
/*     */   }
/*     */ 
/*     */   public void setX(int x) {
/* 428 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public void setY(int y) {
/* 432 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public void setSpeedX(int speedX) {
/* 436 */     this.speedX = speedX;
/*     */   }
/*     */ 
/*     */   public void setSpeedY(int speedY) {
/* 440 */     this.speedY = speedY;
/*     */   }
/*     */ 
/*     */   public int getBestPosX() {
/* 444 */     return this.bestPosX;
/*     */   }
/*     */ 
/*     */   public int getBestPosY() {
/* 448 */     return this.bestPosY;
/*     */   }
/*     */ 
/*     */   public void setBestPosX(int bestPosX) {
/* 452 */     this.bestPosX = bestPosX;
/*     */   }
/*     */ 
/*     */   public void setBestPosY(int bestPosY) {
/* 456 */     this.bestPosY = bestPosY;
/*     */   }
/*     */ 
/*     */   public String getName() {
/* 460 */     return this.name;
/*     */   }
/*     */ 
/*     */   public boolean isMoveTo() {
/* 464 */     return this.moveTo;
/*     */   }
/*     */ 
/*     */   public void setMoveTo(boolean moveTo) {
/* 468 */     this.moveTo = moveTo;
/*     */   }
/*     */ 
/*     */   public Rectangle getR_maxHP() {
/* 472 */     return this.r_maxHP;
/*     */   }
/*     */ 
/*     */   public Rectangle getR_hp() {
/* 476 */     return this.r_hp;
/*     */   }
/*     */ 
/*     */   public void setR_maxHP(Rectangle r_maxHP) {
/* 480 */     this.r_maxHP = r_maxHP;
/*     */   }
/*     */ 
/*     */   public void setR_hp(Rectangle r_hp) {
/* 484 */     this.r_hp = r_hp;
/*     */   }
/*     */ 
/*     */   public Unit.Status getStatus() {
/* 488 */     return this.status;
/*     */   }
/*     */ 
/*     */   public BufferedImage getImage() {
/* 492 */     return this.image;
/*     */   }
/*     */ 
/*     */   public void setImage(BufferedImage image) {
/* 496 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public int gettX()
/*     */   {
/* 502 */     return this.tX;
/*     */   }
/*     */ 
/*     */   public int gettY() {
/* 506 */     return this.tY;
/*     */   }
/*     */ 
/*     */   public double getHpPerPixel() {
/* 510 */     return this.hpPerPixel;
/*     */   }
/*     */ 
/*     */   public Line2D getHitCheck() {
/* 514 */     return this.hitCheck;
/*     */   }
/*     */ 
/*     */   public void setHitCheck(Line2D hitCheck) {
/* 518 */     this.hitCheck = hitCheck;
/*     */   }
/*     */ 
/*     */   public void printInventory() {
/* 522 */     for (int i = 0; i < this.inventory.size(); i++) {
/* 523 */       Item item = (Item)this.inventory.get(i);
/* 524 */       System.out.println(item.amount + "x " + item.name);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addItem(Item itemAdded) {
/* 529 */     for (int i = 0; i < this.inventory.size(); i++) {
/* 530 */       Item item = (Item)this.inventory.get(i);
/* 531 */       if (item.name == itemAdded.name) {
/* 532 */         item.amount += itemAdded.amount;
/* 533 */         if (item.amount < 0) {
/* 534 */           item.amount = 0;
/* 535 */           itemAdded = null;
/*     */         }
/* 537 */         return;
/*     */       }
/*     */     }
/* 540 */     this.inventory.add(itemAdded);
/*     */   }
/*     */ 
/*     */   public void initializeInventory() {
/* 544 */     this.inventory.add(new Item("Grenade", 1));
/*     */   }
/*     */ 
/*     */   public int inventoryCheckAmount(String itemCheck) {
/* 548 */     for (int i = 0; i < this.inventory.size(); i++) {
/* 549 */       Item item = (Item)this.inventory.get(i);
/* 550 */       if (item.name == itemCheck) {
/* 551 */         return item.amount;
/*     */       }
/*     */     }
/* 554 */     return -1;
/*     */   }
/*     */ 
/*     */   static enum Status
/*     */   {
/*  14 */     Alive, Dead;
/*     */   }
/*     */ }