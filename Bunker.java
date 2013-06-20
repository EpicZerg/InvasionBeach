/*    */ import java.awt.Polygon;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Random;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class Bunker
/*    */ {
/*    */   public Rectangle collision;
/* 12 */   public Polygon firingAngle = new Polygon();
/*    */   public BufferedImage image;
/* 14 */   public ArrayList<Unit> inRange = new ArrayList<Unit>();
/*    */   public int x;
/*    */   public int y;
/* 16 */   public int nextShot = 150; public int elapsed = 0;
/* 17 */   private Random r = new Random();
/*    */ 
/*    */   public Bunker(int x, int y) {
/* 20 */     this.x = x;
/* 21 */     this.y = y;
/* 22 */     this.collision = new Rectangle(x + 13, y + 6, 102, 38);
/* 23 */     this.firingAngle.addPoint(x + 101, y + 54);
/* 24 */     this.firingAngle.addPoint(x + 26, y + 54);
/* 25 */     this.firingAngle.addPoint(x - 192, y + 400);
/* 26 */     this.firingAngle.addPoint(x + 320, y + 400);
/*    */     try {
/* 28 */       this.image = ImageIO.read(getClass()
/* 29 */         .getResource("data/Units/Bunker.png"));
/*    */     } catch (Exception e) {
/* 31 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void update(int time)
/*    */   {
/* 38 */     this.elapsed += time;
/* 39 */     checkFiring();
/* 40 */     checkDeath();
/* 41 */     if (this.elapsed >= this.nextShot) {
/* 42 */       this.elapsed = 0;
/* 43 */       fireAtUnit();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void checkFiring()
/*    */   {
/* 50 */     for (int i = 0; i < Core.units.size(); i++) {
/* 51 */       Unit u = (Unit)Core.units.get(i);
/* 52 */       if ((this.firingAngle.intersects(u.collision)) && (!this.inRange.contains(u))) {
/* 53 */         this.inRange.add(u);
/* 54 */         System.out.println("Unit added " + u);
/*    */       }
/* 56 */       else if ((this.inRange.contains(u)) && (!this.firingAngle.intersects(u.collision))) {
/* 57 */         this.inRange.remove(u);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public void fireAtUnit()
/*    */   {
/* 64 */     if (this.inRange.size() > 0)
/* 65 */       Core.projectiles.add(new Projectiles(this.x + 64, this.y + 64, ((Unit)this.inRange.get(0)).x + this.r.nextInt(32), ((Unit)this.inRange.get(0)).y + this.r.nextInt(32), 16, this.r.nextInt(3) + 2, null));
/*    */   }
/*    */ 
/*    */   public void checkDeath()
/*    */   {
/* 70 */     for (int i = 0; i < this.inRange.size(); i++)
/* 71 */       if (!((Unit)this.inRange.get(i)).alive)
/* 72 */         this.inRange.remove(this.inRange.get(i));
/*    */   }
/*    */ }