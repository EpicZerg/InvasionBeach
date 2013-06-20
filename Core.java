/*     */ import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
/*     */ 
/*     */ public class Core extends Applet
/*     */   implements Runnable, KeyListener, MouseListener, MouseMotionListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int res = 1;
/*  25 */   public static int offsetY = 0; public static int offsetX = 0;
/*  26 */   public static Dimension screenSize = new Dimension(800, 480);
/*  27 */   public static Dimension pixel = new Dimension(screenSize.width, 
/*  28 */     screenSize.height);
/*     */   public static Dimension size;
/*  30 */   public static String name = "Invasion Beach";
/*     */   public static URL base;
/*     */   public static JFrame frame;
/*  33 */   public boolean grenadeSelected = false;
/*     */   public static Unit commander;
/*     */   public static Unit radio;
/*     */   public static Unit engineer;
/*     */   public static Unit machinegunner;
/*  37 */   public static Tileset shooting = new Tileset(
/*  38 */     "data/Effects/MuzzleFlash.png", 16, 16);
/*     */ 
/*  39 */   public static Tileset explosion = new Tileset("data/Effects/Explosion.png", 
/*  40 */     150, 150);
/*     */ 
/*  41 */   public static Tileset aimingCircle = new Tileset("data/Effects/target.png", 
/*  42 */     100, 100);
/*     */ 
/*  43 */   public static Tileset unitGotHit = new Tileset(
/*  44 */     "data/Effects/HitBubbles.png", 64, 16);
/*     */ 
/*  45 */   public static Tileset bloodImage = new Tileset("data/Effects/Blood.png", 
/*  46 */     32, 32);
/*     */ 
/*  47 */   public static Tileset unitDirections = new Tileset("data/Units/Unit.png", 
/*  48 */     32, 32);
/*     */ 
/*  49 */   public static Tileset enemyRifleManDirections = new Tileset(
/*  50 */     "data/Units/NaziUnit.png", 32, 32);
/*     */ 
/*  51 */   public static Tileset guiWeaponIcons = new Tileset(
/*  52 */     "data/GUI/guiWeaponIcons.png", 64, 64);
/*     */ 
/*  53 */   public static Tileset gameGUI = new Tileset("data/GUI/GameGUI.png", 800, 
/*  54 */     480);
/*     */ 
/*  55 */   public static Tileset guiUnits = new Tileset("data/GUI/GUIUnits.png", 51, 
/*  56 */     183);
/*     */ 
/*  57 */   public static Tileset grenade = new Tileset("data/Effects/Grenade.png", 16, 
/*  58 */     16);
/*     */ 
/*  59 */   public static Tileset handgrenadeExplosion = new Tileset(
/*  60 */     "data/Effects/Explosion4.png", 48, 48);
/*     */ 
/*  61 */   public static Tileset imageBarbedWire = new Tileset("data/Units/BarbedWire.png", 64, 32);
/*  62 */   public static Tileset imageSandbags = new Tileset("data/Units/SandBags.png", 32, 32);
/*     */   public static Image image;
/*     */   public static Image background;
/*     */   public static Image water;
/*     */   public static Image landingBoat;
/*     */   public static Image unit;
/*     */   public static Image tankBarricadeSand;
/*  70 */   public static ArrayList<Unit> units = new ArrayList<Unit>();
/*  71 */   public static ArrayList<EnemyRifle> enemies = new ArrayList<EnemyRifle>();
/*  72 */   public static ArrayList<Barricade> barricades = new ArrayList<Barricade>();
/*  73 */   public static ArrayList<LandingBoat> landingboats = new ArrayList<LandingBoat>();
/*  74 */   public static ArrayList<Bunker> bunkers = new ArrayList<Bunker>();
/*  75 */   public static ArrayList<Effect> effects = new ArrayList<Effect>();
/*  76 */   public static ArrayList<Projectiles> projectiles = new ArrayList<Projectiles>();
/*  77 */   public static ArrayList<CombatText> combatText = new ArrayList<CombatText>();
/*  78 */   public static ArrayList<Blood> groundEffects = new ArrayList<Blood>();
/*  79 */   public static ArrayList<Grenade> grenades = new ArrayList<Grenade>();
/*  80 */   public static ArrayList<Unit> deadUnits = new ArrayList<Unit>();
/*  81 */   public static ArrayList<String> speech = new ArrayList<String>();
/*  82 */   public static ArrayList<Item> inventory = new ArrayList<Item>();
/*  83 */   public static ArrayList<Sandbag> sandbags = new ArrayList<Sandbag>();
/*  84 */   public static ArrayList<BarbedWire> barbedWire = new ArrayList<BarbedWire>();
/*     */   public Graphics2D second;
/*  88 */   public Color red = new Color(1.0F, 0.0F, 0.0F, 0.5F); public Color green = new Color(0.0F, 1.0F, 0.0F, 
/*  89 */     0.5F);
/*     */ 
/*  91 */   public Random r = new Random();
/*  92 */   public static Core.State state = Core.State.Pause;
/*  93 */   public static boolean unitsDeployed = false;
/*     */ 
/*  95 */   public Font font = new Font(null, 1, 20);
/*  96 */   public boolean debugMode = false;
/*  97 */   public static ArrayList<Blood> blood = new ArrayList<Blood>();
/*  98 */   public double hpPerPixel = 0.4D;
/*     */   public static Unit activeClass;
/*     */ 
/*     */   public Core()
/*     */   {
/* 103 */     setPreferredSize(screenSize);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 111 */     Core core = new Core();
/* 112 */     core.init();
/*     */ 
/* 114 */     frame = new JFrame();
/* 115 */     frame.add(core);
/* 116 */     frame.pack();
/* 117 */     size = new Dimension(frame.getWidth(), frame.getHeight());
/* 118 */     frame.setTitle(name);
/* 119 */     frame.setResizable(false);
/* 120 */     frame.setLocationRelativeTo(null);
/* 121 */     frame.setDefaultCloseOperation(3);
/* 122 */     frame.setVisible(true);
/* 123 */     core.start();
/*     */   }
/*     */ 
/*     */   public void start()
/*     */   {
/* 128 */     landingboats.add(new LandingBoat(400, 500));
/* 129 */     Thread thread = new Thread(this);
/* 130 */     thread.start();
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/* 137 */     super.stop();
/*     */   }
/*     */ 
/*     */   public void init()
/*     */   {
/* 142 */     setSize(800, 480);
/* 143 */     setBackground(Color.BLACK);
/* 144 */     setFocusable(true);
/* 145 */     addKeyListener(this);
/* 146 */     addMouseListener(this);
/* 147 */     addMouseMotionListener(this);
/* 148 */     getPictures();
/* 149 */     createCover();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     while (true)
/*     */     {
/*     */       try {
/* 157 */         Thread.sleep(17L);
/*     */       } catch (Exception e) {
/* 159 */         e.printStackTrace();
/*     */       }
/*     */ 
/* 162 */       for (int i = 0; i < landingboats.size(); i++) {
/* 163 */         LandingBoat l = (LandingBoat)landingboats.get(i);
/* 164 */         l.update();
/*     */       }
/* 166 */       for (int i = 0; i < units.size(); i++) {
/* 167 */         Unit u = (Unit)units.get(i);
/* 168 */         u.update(17);
/*     */       }
/* 170 */       repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void update(Graphics g)
/*     */   {
/* 176 */     if (image == null) {
/* 177 */       image = createImage(getWidth(), getHeight());
/* 178 */       this.second = ((Graphics2D)image.getGraphics());
/*     */     }
/* 180 */     if ((state == Core.State.Pause) && (unitsDeployed)) {
/* 181 */       state = Core.State.Running;
/*     */     }
/* 183 */     if (state == Core.State.Running) {
/* 184 */       offsetY = -1 * activeClass.getY() + 240;
/*     */     }
/* 186 */     this.second.setColor(getBackground());
/* 187 */     this.second.fillRect(0, 0, getWidth(), getHeight());
/* 188 */     this.second.setColor(getForeground());
/* 189 */     paint(this.second);
/*     */ 
/* 191 */     g.drawImage(image, 0, 0, this);
/*     */   }
/*     */ 
/*     */   public void toggleDebug() {
/* 195 */     this.debugMode = (!this.debugMode);
/*     */   }
/*     */ 
/*     */   public void paint(Graphics g)
/*     */   {
/* 200 */     g.drawImage(background, 0 + offsetX, -1400 + offsetY, this);
/* 201 */     g.drawImage(water, 0 + offsetX, 280 + offsetY, this);
/* 202 */     updateBarricades(g);
/* 203 */     updateLandingBoats(g);
/* 204 */     g.setColor(Color.RED);
/* 205 */     updateGroundEffects(g);
/* 206 */     updateGrenades(g);
/* 207 */     updateBunkers(g);
/* 208 */     updateEffects(g);
/* 209 */     updateSandbags(g);
/* 210 */     updateUnits(g);
/* 211 */     updateEnemyUnits(g);
/* 212 */     updateBarbedWire(g);
/* 213 */     animate(g);
/* 214 */     paintHealthBars(g);
/* 215 */     updateProjectiles(g);
/* 216 */     updateCombatText(g);
/*     */ 
/* 218 */     paintGUI(g);
/*     */ 
/* 220 */     g.setColor(Color.BLACK);
/*     */   }
/*     */ 
/*     */   public void getPictures() {
/*     */     try {
/* 225 */       base = getClass().getResource(
/* 226 */         "data/Background/BackgroundNormandyBeach.png");
/* 227 */       background = ImageIO.read(base);
/* 228 */       base = getClass().getResource("data/Background/Water.png");
/* 229 */       water = ImageIO.read(base);
/* 230 */       base = getClass().getResource("data/Units/Landing.png");
/* 231 */       landingBoat = ImageIO.read(base);
/* 232 */       base = getClass().getResource("data/Units/TankBarricade_Sand.png");
/* 233 */       tankBarricadeSand = ImageIO.read(base);
/* 234 */       base = getClass().getResource("data/Units/unit.png");
/* 235 */       unit = ImageIO.read(base);
/*     */     } catch (Exception e) {
/* 237 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 243 */     if (activeClass != null)
/* 244 */       switch (e.getKeyCode()) {
/*     */       case 87:
/* 246 */         activeClass.setGoingUp(true);
/* 247 */         activeClass.setGoingDown(false);
/* 248 */         break;
/*     */       case 83:
/* 250 */         activeClass.setGoingUp(false);
/* 251 */         activeClass.setGoingDown(true);
/* 252 */         break;
/*     */       case 68:
/* 254 */         activeClass.setGoingRight(true);
/* 255 */         activeClass.setGoingLeft(false);
/* 256 */         break;
/*     */       case 65:
/* 258 */         activeClass.setGoingRight(false);
/* 259 */         activeClass.setGoingLeft(true);
/* 260 */         break;
/*     */       case 71:
/* 263 */         if (activeClass.inventoryCheckAmount("Grenade") > 0) {
/* 264 */           activeClass.addItem(new Item("Grenade", -1));
/* 265 */           grenades.add(new Grenade(activeClass.getX(), 
/* 266 */             activeClass.getY(), activeClass.gettX(), activeClass
/* 267 */             .gettY() - offsetY, 20));
/*     */         }
/*     */ 
/* 270 */         break;
/*     */       case 82:
/* 272 */         activeClass.reloading = true;
/* 273 */         break;
/*     */       case 70:
/* 275 */         activeClass.specialAbility();
/* 276 */         break;
/*     */       case 97:
/* 278 */         new EnemyRow(activeClass.y - 300, 1);
/* 279 */         break;
/*     */       case 104:
/* 281 */         activeClass.addItem(new Item("Grenade", 3));
/* 282 */         break;
/*     */       case 98:
/* 284 */         activeClass.addItem(new Item("Grenade", -1));
/* 285 */         break;
/*     */       case 99:
/* 287 */         activeClass.printInventory();
/* 288 */         break;
/*     */       case 102:
/* 290 */         System.out.println("Y: " + activeClass.getY());
/* 291 */         break;
/*     */       case 49:
/* 293 */         activeClass = getNewActive("Commander");
/* 294 */         activeClass.shoutClass();
/* 295 */         break;
/*     */       case 50:
/* 297 */         activeClass = getNewActive("Radio");
/* 298 */         activeClass.shoutClass();
/* 299 */         break;
/*     */       case 51:
/* 301 */         activeClass = getNewActive("Engineer");
/* 302 */         activeClass.shoutClass();
/* 303 */         break;
/*     */       case 52:
/* 305 */         activeClass = getNewActive("Machine Gunner");
/* 306 */         activeClass.shoutClass();
/* 307 */         break;
/*     */       case 53:
/* 309 */         for (int i = 0; i < units.size(); i++) {
/* 310 */           Unit u = (Unit)units.get(i);
/* 311 */           u.shoutClass();
/*     */         }
/* 313 */         break;
/*     */       case 81:
/* 315 */         toggleDebug();
/*     */       }
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent e)
/*     */   {
/* 323 */     if (activeClass != null)
/* 324 */       switch (e.getKeyCode()) {
/*     */       case 87:
/* 326 */         activeClass.setGoingUp(false);
/* 327 */         activeClass.stop();
/* 328 */         break;
/*     */       case 83:
/* 330 */         activeClass.setGoingDown(false);
/* 331 */         activeClass.stop();
/* 332 */         break;
/*     */       case 68:
/* 334 */         activeClass.setGoingRight(false);
/* 335 */         activeClass.stop();
/* 336 */         break;
/*     */       case 65:
/* 338 */         activeClass.setGoingLeft(false);
/* 339 */         activeClass.stop();
/* 340 */         break;
/*     */       case 96:
/*     */       }
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void createCover()
/*     */   {
/* 353 */     Random r = new Random();
/* 354 */     for (int j = 0; j < 7; j++)
/*     */     {
/* 356 */       new EnemyRow((j * 200 + 100) * -1, j);
/*     */ 
/* 358 */       for (int i = 0; i < 3; i++)
/* 359 */         switch (i) {
/*     */         case 0:
/* 361 */           barricades.add(new Barricade(50 + r.nextInt(20), j * 200 * 
/* 362 */             -1));
/* 363 */           break;
/*     */         case 1:
/* 365 */           barricades.add(new Barricade(320 + r.nextInt(50), 
/* 366 */             (j * 200 + r.nextInt(50)) * -1));
/* 367 */           break;
/*     */         case 2:
/* 369 */           barricades.add(new Barricade(600 + r.nextInt(50), j * 200 * 
/* 370 */             -1));
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Unit getNewActive(String name)
/*     */   {
/* 378 */     for (int i = 0; i < units.size(); i++) {
/* 379 */       Unit u = (Unit)units.get(i);
/* 380 */       if (u.getName() == name) {
/* 381 */         return (Unit)units.get(i);
/*     */       }
/*     */     }
/* 384 */     return (Unit)units.get(0);
/*     */   }
/*     */ 
/*     */   public static ArrayList<Unit> getUnits() {
/* 388 */     return units;
/*     */   }
/*     */ 
/*     */   public static ArrayList<Barricade> getBarricades() {
/* 392 */     return barricades;
/*     */   }
/*     */ 
/*     */   public static Core.State getState() {
/* 396 */     return state;
/*     */   }
/*     */ 
/*     */   public static void setState(Core.State state) {
/* 400 */     Core.state = state;
/*     */   }
/*     */ 
/*     */   public static void setUnitsDeployed(boolean unitsDeployed) {
/* 404 */     Core.unitsDeployed = unitsDeployed;
/*     */   }
/*     */ 
/*     */   public void animate(Graphics g) {
/* 408 */     for (int i = 0; i < effects.size(); i++) {
/* 409 */       Effect e = (Effect)effects.get(i);
/* 410 */       e.update(17);
/* 411 */       if ((e.isElapsed()) || (!e.alive)) {
/* 412 */         effects.remove(i);
/* 413 */         e = null;
/*     */       } else {
/* 415 */         g.drawImage(e.getImage(), e.getX() + offsetX, e.getY() + 
/* 416 */           offsetY, this);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateGroundEffects(Graphics g) {
/* 422 */     for (int i = 0; i < groundEffects.size(); i++) {
/* 423 */       Effect e = (Effect)groundEffects.get(i);
/* 424 */       e.update(17);
/* 425 */       if ((e.isElapsed()) || (!e.alive)) {
/* 426 */         groundEffects.remove(i);
/* 427 */         e = null;
/*     */       } else {
/* 429 */         g.drawImage(e.getImage(), e.getX() + offsetX, e.getY() + 
/* 430 */           offsetY, this);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent m)
/*     */   {
/* 438 */     switch (m.getButton())
/*     */     {
/*     */     case 1:
/* 441 */       break;
/*     */     case 2:
/* 445 */       break;
/*     */     case 3:
/* 448 */       break;
/*     */     case 4:
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseEntered(MouseEvent m)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseExited(MouseEvent m)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent m)
/*     */   {
/* 468 */     switch (m.getButton()) {
/*     */     case 1:
/* 470 */       if (activeClass != null) {
/* 471 */         if (!this.grenadeSelected)
/* 472 */           activeClass.autoFire = true;
/*     */         else {
/* 474 */           System.out.println("Grenade throw");
/*     */         }
/*     */       }
/* 477 */       break;
/*     */     case 2:
/* 480 */       if (activeClass != null) {
/* 481 */         bunkers.add(new Bunker(m.getX() - offsetX, m.getY() - offsetY));
/*     */       }
/*     */ 
/* 484 */       break;
/*     */     case 3:
/* 486 */       if ((activeClass != null) && 
/* 487 */         (activeClass.getName() == "Radio"))
/*     */       {
/* 489 */         ArtilleryCircle circ = new ArtilleryCircle(2000, 
/* 490 */           m.getX() - 50, m.getY() - offsetY - 50);
/* 491 */         effects.add(circ);
/*     */       }
/*     */ 
/* 494 */       break;
/*     */     case 4:
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseReleased(MouseEvent m)
/*     */   {
/* 505 */     switch (m.getButton()) {
/*     */     case 1:
/* 507 */       if (activeClass != null)
/* 508 */         activeClass.autoFire = false;
/*     */       break;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void paintHealthBars(Graphics g)
/*     */   {
/* 516 */     for (int i = 0; i < units.size(); i++) {
/* 517 */       Unit u = (Unit)units.get(i);
/* 518 */       g.setColor(Color.RED);
/* 519 */       g.fillRect(u.getR_maxHP().x + offsetX, u.getR_maxHP().y + offsetY, 
/* 520 */         u.getR_maxHP().width, u.getR_maxHP().height);
/* 521 */       g.setColor(Color.GREEN);
/* 522 */       g.fillRect(u.getR_hp().x + offsetX, u.getR_hp().y + offsetY, 
/* 523 */         u.getR_hp().width, u.getR_hp().height);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateProjectiles(Graphics g) {
/* 528 */     g.setColor(Color.BLACK);
/* 529 */     for (int i = 0; i < projectiles.size(); i++) {
/* 530 */       Projectiles p = (Projectiles)projectiles.get(i);
/* 531 */       p.update();
/* 532 */       if (!p.alive)
/*     */       {
/* 534 */         projectiles.remove(p);
/* 535 */         p = null;
/*     */       } else {
/* 537 */         g.drawRect((int)p.x + offsetX, (int)p.y + offsetY, 3, 3);
/* 538 */         g.setColor(Color.WHITE);
/* 539 */         g.fillRect((int)p.x + offsetX + 1, (int)p.y + offsetY + 1, 1, 
/* 540 */           1);
/* 541 */         g.setColor(Color.BLACK);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void paintGUI(Graphics g)
/*     */   {
/* 548 */     int offY = 280;
/* 549 */     g.setColor(Color.WHITE);
/* 550 */     g.setFont(this.font);
/* 551 */     g.drawImage(guiUnits.getTileImage(0), 12, 12 + offY, this);
/*     */ 
/* 553 */     if (units.contains(commander)) {
/* 554 */       g.setColor(this.green);
/* 555 */       g.fillRect(17, 17 + offY, (int)(commander.hp * this.hpPerPixel), 40);
/*     */     } else {
/* 557 */       g.setColor(this.red);
/* 558 */       g.fillRect(17, 17 + offY, 40, 40);
/*     */     }
/* 560 */     if (units.contains(radio)) {
/* 561 */       g.setColor(this.green);
/* 562 */       g.fillRect(17, 61 + offY, (int)(radio.hp * this.hpPerPixel), 40);
/*     */     } else {
/* 564 */       g.setColor(this.red);
/* 565 */       g.fillRect(17, 61 + offY, 40, 40);
/*     */     }
/* 567 */     if (units.contains(engineer)) {
/* 568 */       g.setColor(this.green);
/* 569 */       g.fillRect(17, 105 + offY, (int)(engineer.hp * this.hpPerPixel), 40);
/*     */     } else {
/* 571 */       g.setColor(this.red);
/* 572 */       g.fillRect(17, 105 + offY, 40, 40);
/*     */     }
/* 574 */     if (units.contains(machinegunner)) {
/* 575 */       g.setColor(this.green);
/* 576 */       g.fillRect(17, 149 + offY, (int)(machinegunner.hp * this.hpPerPixel), 40);
/*     */     } else {
/* 578 */       g.setColor(this.red);
/* 579 */       g.fillRect(17, 149 + offY, 40, 40);
/*     */     }
/* 581 */     g.setFont(new Font("Arial", 0, 12));
/*     */   }
/*     */ 
/*     */   public void updateEnemyUnits(Graphics g) {
/* 587 */     for (int i = 0; i < enemies.size(); i++) {
/* 588 */       Unit u = (Unit)enemies.get(i);
/* 589 */       if (u.alive) {
/* 590 */         u.update(17);
/* 591 */         g.drawImage(u.getImage(), u.getX() + offsetX, u.getY() + 
/* 592 */           offsetY, this);
/* 593 */         if (this.debugMode) {
/* 594 */           g.drawRect(u.collision.x + offsetX, 
/* 595 */             u.collision.y + offsetY, u.collision.width, 
/* 596 */             u.collision.height);
/* 597 */           g.drawRect(u.attackRange.getBounds().x + offsetX, 
/* 598 */             u.attackRange.getBounds().y + offsetY, 
/* 599 */             u.attackRange.getBounds().width, 
/* 600 */             u.attackRange.getBounds().height);
/* 601 */           g.drawLine((int)u.getHitCheck().getX1(), (int)u.getHitCheck().getY1() + offsetY, (int)u.getHitCheck().getX2(), (int)u.getHitCheck().getY2() + offsetY);
/*     */         }
/*     */       } else {
/* 604 */         enemies.remove(u);
/* 605 */         u = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateCombatText(Graphics g)
/*     */   {
/* 618 */     for (int i = 0; i < combatText.size(); i++) {
/* 619 */       CombatText c = (CombatText)combatText.get(i);
/* 620 */       if (c.isVisible()) {
/* 621 */         c.update(17);
/* 622 */         g.setColor(Color.WHITE);
/* 623 */         g.setFont(this.font);
/* 624 */         g.drawString(c.getsDamage(), c.getX(), c.getY() + offsetY);
/*     */       } else {
/* 626 */         combatText.remove(c);
/* 627 */         c = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateBunkers(Graphics g)
/*     */   {
/* 634 */     for (int i = 0; i < bunkers.size(); i++) {
/* 635 */       Bunker bunker = (Bunker)bunkers.get(i);
/* 636 */       g.drawImage(bunker.image, bunker.x + offsetX, bunker.y + offsetY, 
/* 637 */         this);
/* 638 */       int[] newy = new int[bunker.firingAngle.ypoints.length];
/* 639 */       for (int j = 0; j < bunker.firingAngle.ypoints.length; j++) {
/* 640 */         Integer n = Integer.valueOf(Integer.valueOf(bunker.firingAngle.ypoints[j]).intValue() + offsetY);
/* 641 */         newy[j] = n.intValue();
/*     */       }
/* 643 */       bunker.update(17);
/* 644 */       if (this.debugMode) {
/* 645 */         g.drawPolygon(bunker.firingAngle.xpoints, newy, 
/* 646 */           bunker.firingAngle.npoints);
/*     */ 
/* 648 */         g.drawRect(bunker.collision.x + offsetX, bunker.collision.y + 
/* 649 */           offsetY, bunker.collision.width, 
/* 650 */           bunker.collision.height);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateBarricades(Graphics g)
/*     */   {
/* 657 */     g.setColor(Color.GREEN);
/* 658 */     for (int i = 0; i < barricades.size(); i++) {
/* 659 */       Barricade b = (Barricade)barricades.get(i);
/* 660 */       g.drawImage(tankBarricadeSand, b.getX() + offsetX, b.getY() + 
/* 661 */         offsetY, this);
/* 662 */       if (this.debugMode)
/* 663 */         g.drawRect(b.getBox().x + offsetX, b.getBox().y + offsetY, 
/* 664 */           b.getBox().width, b.getBox().height);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateEffects(Graphics g)
/*     */   {
/* 670 */     for (int i = 0; i < effects.size(); i++) {
/* 671 */       Effect eff = (Effect)effects.get(i);
/* 672 */       if (this.debugMode) {
/* 673 */         g.drawRect(eff.getLowDamage().x + offsetX, eff.getLowDamage().y + 
/* 674 */           offsetY, eff.getLowDamage().width, 
/* 675 */           eff.getLowDamage().height);
/* 676 */         g.drawRect(eff.getHighDamage().x + offsetX, 
/* 677 */           eff.getHighDamage().y + offsetY, 
/* 678 */           eff.getHighDamage().width, eff.getHighDamage().height);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateLandingBoats(Graphics g) {
/* 684 */     for (int i = 0; i < landingboats.size(); i++) {
/* 685 */       LandingBoat l = (LandingBoat)landingboats.get(i);
/* 686 */       g.drawImage(landingBoat, l.getX() + offsetX, l.getY() + offsetY, 
/* 687 */         this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateGrenades(Graphics g) {
/* 692 */     for (int i = 0; i < grenades.size(); i++) {
/* 693 */       Grenade gren = (Grenade)grenades.get(i);
/* 694 */       gren.update(17);
/* 695 */       if (gren.visible) {
/* 696 */         g.drawImage(gren.getImage(), (int)gren.getX(), 
/* 697 */           (int)gren.getY() + offsetY, this);
/*     */       } else {
/* 699 */         grenades.remove(gren);
/* 700 */         gren = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateUnits(Graphics g)
/*     */   {
/* 707 */     for (int i = 0; i < units.size(); i++) {
/* 708 */       Unit u = (Unit)units.get(i);
/* 709 */       if (!u.alive) {
/* 710 */         units.remove(u);
/*     */       }
/*     */       else {
/* 713 */         g.drawImage(u.getImage(), u.getX() + offsetX, u.getY() + 
/* 714 */           offsetY, this);
/* 715 */         if (this.debugMode) {
/* 716 */           g.drawRect(u.collision.x + offsetX, 
/* 717 */             u.collision.y + offsetY, u.collision.width, 
/* 718 */             u.collision.height);
/* 719 */           if (activeClass == u) {
/* 720 */             g.drawPolygon(u.fieldUp);
/* 721 */             g.drawPolygon(u.fieldDown);
/* 722 */             g.drawPolygon(u.fieldLeft);
/* 723 */             g.drawPolygon(u.fieldRight);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 732 */     if (activeClass != null) {
/* 733 */       activeClass.tX = e.getX();
/* 734 */       activeClass.tY = e.getY();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseMoved(MouseEvent e)
/*     */   {
/* 740 */     if (activeClass != null) {
/* 741 */       activeClass.tX = e.getX();
/* 742 */       activeClass.tY = e.getY();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void spawnUnits(int x, int y) {
/* 747 */     units.add(new Commander(x - 10, y - 15, 0, 0));
/* 748 */     commander = (Unit)units.get(0);
/* 749 */     units.add(new Radio(x - 10, y - 15, 0, 0));
/* 750 */     radio = (Unit)units.get(1);
/* 751 */     units.add(new Engineer(x - 10, y - 15, 0, 0));
/* 752 */     engineer = (Unit)units.get(2);
/* 753 */     units.add(new MachineGunner(x - 10, y - 15, 0, 0));
/* 754 */     machinegunner = (Unit)units.get(3);
/*     */ 
/* 757 */    
/* 758 */     activeClass = (Unit)units.get(0);
/* 759 */     Squad.deployment();
/* 760 */     setUnitsDeployed(true);
/*     */   }
/*     */ 
/*     */   public void updateSandbags(Graphics g) {
/* 764 */     for (int i = 0; i < sandbags.size(); i++) {
/* 765 */       Sandbag s = (Sandbag)sandbags.get(i);
/* 766 */       if (this.debugMode) {
/* 767 */         g.drawRect((int)s.getCollision().getX(), (int)s.getCollision().getY() + offsetY, (int)s.getCollision().getWidth(), (int)s.getCollision().getHeight());
/*     */       }
/* 769 */       g.drawImage(s.getImage(), s.getX(), s.getY() + offsetY, this);
/*     */     }
/*     */   }
/*     */ 
/* 773 */   public void updateBarbedWire(Graphics g) { for (int i = 0; i < barbedWire.size(); i++) {
/* 774 */       BarbedWire b = (BarbedWire)barbedWire.get(i);
/* 775 */       b.update();
/* 776 */       if (this.debugMode) {
/* 777 */         g.drawRect((int)b.getCollision().getX(), (int)b.getCollision().getY() + offsetY, (int)b.getCollision().getWidth(), (int)b.getCollision().getHeight());
/*     */       }
/*     */ 
/* 780 */       g.drawImage(b.getImage(), b.getX(), b.getY() + offsetY, this);
/*     */     }
/*     */   }
/*     */ 
/*     */   static enum State
/*     */   {
/* 107 */     Pause, Running;
/*     */   }
/*     */ }
