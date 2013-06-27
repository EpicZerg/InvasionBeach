
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class Explosion extends Effect
/*     */ {
/*   8 */   public Animation anim = new Animation();
/*   9 */   public ArrayList<Image> pictures = new ArrayList();
/*     */   public Tileset tileset;
/*  11 */   private ArrayList<Unit> hitUnits = new ArrayList();
/*     */   private Rectangle lowDamage;
/*     */   private Rectangle highDamage;
/*     */ 
/*     */   public Explosion(int duration, int x, int y)
/*     */   {
/*  15 */     super(duration, x, y);
/*  16 */     this.tileset = Core.explosion;
/*  17 */     loadPictures();
/*  18 */     for (int i = 0; i < this.pictures.size(); i++) {
/*  19 */       this.anim.addFrame((Image)this.pictures.get(i), duration / 4);
/*     */     }
/*  21 */     this.lowDamage = new Rectangle(x, y, 150, 150);
/*  22 */     this.highDamage = new Rectangle(x + 50, y + 50, 50, 50);
/*     */   }
/*     */ 
/*     */   public void update(int time)
/*     */   {
/*  27 */     this.timeElapsed += time;
/*  28 */     if (this.timeElapsed >= this.duration) {
/*  29 */       this.elapsed = true;
/*  30 */       return;
/*     */     }
/*  32 */     this.anim.update(time);
/*  33 */     damageCheck();
/*     */   }
/*     */ 
/*     */   public void damageCheck() {
/*  37 */     Random r = new Random();
/*  38 */     for (int i = 0; i < Core.units.size(); i++) {
/*  39 */       Unit u = (Unit)Core.units.get(i);
/*     */ 
/*  41 */       if (!this.hitUnits.contains(u)) {
/*  42 */         if (u.collision.intersects(this.highDamage)) {
/*  43 */           u.setDamage(60 + r.nextInt(50));
/*  44 */           System.out.println("High");
/*  45 */           this.hitUnits.add(u);
/*  46 */         } else if (u.collision.intersects(this.lowDamage)) {
/*  47 */           u.setDamage(20 + r.nextInt(20));
/*  48 */           System.out.println("Low");
/*  49 */           this.hitUnits.add(u);
/*     */         }
/*     */       }
/*     */     }
/*  53 */     for (int i = 0; i < Core.enemies.size(); i++) {
/*  54 */       Unit u = (Unit)Core.enemies.get(i);
/*     */ 
/*  56 */       if (!this.hitUnits.contains(u))
/*  57 */         if (u.collision.intersects(this.highDamage)) {
/*  58 */           u.setDamage(60 + r.nextInt(50));
/*  59 */           System.out.println("High");
/*  60 */           this.hitUnits.add(u);
/*  61 */         } else if (u.collision.intersects(this.lowDamage)) {
/*  62 */           u.setDamage(20 + r.nextInt(20));
/*  63 */           System.out.println("Low");
/*  64 */           this.hitUnits.add(u);
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Image getImage()
/*     */   {
/*  72 */     return this.anim.getImage();
/*     */   }
/*     */ 
/*     */   public void setImage(Image image) {
/*  76 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public void loadPictures() {
/*  80 */     for (int i = 0; i < 4; i++)
/*     */       try {
/*  82 */         this.pictures.add(this.tileset.getTileImage(i));
/*     */       } catch (Exception e) {
/*  84 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public Rectangle getLowDamage()
/*     */   {
/*  90 */     return this.lowDamage;
/*     */   }
/*     */ 
/*     */   public Rectangle getHighDamage() {
/*  94 */     return this.highDamage;
/*     */   }
/*     */ 
/*     */   public void setLowDamage(Rectangle lowDamage) {
/*  98 */     this.lowDamage = lowDamage;
/*     */   }
/*     */ 
/*     */   public void setHighDamage(Rectangle highDamage) {
/* 102 */     this.highDamage = highDamage;
/*     */   }
/*     */ }