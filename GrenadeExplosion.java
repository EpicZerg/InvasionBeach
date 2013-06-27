
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class GrenadeExplosion extends Effect
/*     */ {
/*   9 */   public Animation anim = new Animation();
/*  10 */   public ArrayList<Image> pictures = new ArrayList();
/*     */   public Tileset tileset;
/*  12 */   private ArrayList<Unit> hitUnits = new ArrayList();
/*     */   private Rectangle lowDamage;
/*     */   private Rectangle highDamage;
/*     */ 
/*     */   public GrenadeExplosion(int duration, int x, int y)
/*     */   {
/*  16 */     super(duration, x, y);
/*  17 */     this.tileset = Core.handgrenadeExplosion;
/*  18 */     loadPictures();
/*  19 */     for (int i = 0; i < this.pictures.size(); i++) {
/*  20 */       this.anim.addFrame((Image)this.pictures.get(i), duration / 4);
/*     */     }
/*  22 */     this.lowDamage = new Rectangle(x - 10, y - 10, 68, 68);
/*  23 */     this.highDamage = new Rectangle(x + 10, y + 10, 28, 28);
/*     */   }
/*     */ 
/*     */   public void update(int time)
/*     */   {
/*  30 */     this.timeElapsed += time;
/*  31 */     if (this.timeElapsed >= this.duration) {
/*  32 */       this.elapsed = true;
/*  33 */       return;
/*     */     }
/*  35 */     this.anim.update(time);
/*  36 */     damageCheck();
/*     */   }
/*     */ 
/*     */   public void damageCheck() {
/*  40 */     Random r = new Random();
/*  41 */     for (int i = 0; i < Core.units.size(); i++) {
/*  42 */       Unit u = (Unit)Core.units.get(i);
/*     */ 
/*  44 */       if (!this.hitUnits.contains(u)) {
/*  45 */         if (u.collision.intersects(this.highDamage)) {
/*  46 */           u.setDamage(50 + r.nextInt(30));
/*  47 */           System.out.println("High");
/*  48 */           this.hitUnits.add(u);
/*  49 */         } else if (u.collision.intersects(this.lowDamage)) {
/*  50 */           u.setDamage(20 + r.nextInt(10));
/*  51 */           System.out.println("Low");
/*  52 */           this.hitUnits.add(u);
/*     */         }
/*     */       }
/*     */     }
/*  56 */     for (int i = 0; i < Core.enemies.size(); i++) {
/*  57 */       Unit u = (Unit)Core.enemies.get(i);
/*     */ 
/*  59 */       if (!this.hitUnits.contains(u))
/*  60 */         if (u.collision.intersects(this.highDamage)) {
/*  61 */           u.setDamage(90 + r.nextInt(45));
/*  62 */           System.out.println("High");
/*  63 */           this.hitUnits.add(u);
/*  64 */         } else if (u.collision.intersects(this.lowDamage)) {
/*  65 */           u.setDamage(50 + r.nextInt(20));
/*  66 */           System.out.println("Low");
/*  67 */           this.hitUnits.add(u);
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   public Image getImage()
/*     */   {
/*  75 */     return this.anim.getImage();
/*     */   }
/*     */ 
/*     */   public void setImage(Image image) {
/*  79 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public void loadPictures() {
/*  83 */     for (int i = 0; i < 4; i++)
/*     */       try {
/*  85 */         this.pictures.add(this.tileset.getTileImage(i));
/*     */       } catch (Exception e) {
/*  87 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public Rectangle getLowDamage()
/*     */   {
/*  93 */     return this.lowDamage;
/*     */   }
/*     */ 
/*     */   public Rectangle getHighDamage() {
/*  97 */     return this.highDamage;
/*     */   }
/*     */ 
/*     */   public void setLowDamage(Rectangle lowDamage) {
/* 101 */     this.lowDamage = lowDamage;
/*     */   }
/*     */ 
/*     */   public void setHighDamage(Rectangle highDamage) {
/* 105 */     this.highDamage = highDamage;
/*     */   }
/*     */ }