
/*    */ import java.awt.Image;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class SpeechBubble extends Effect
/*    */ {
/*  7 */   Random r = new Random();
/*  8 */   Animation animSpeech = new Animation();
/*  9 */   boolean speech = false;
/*    */   Tileset unitGotHit;
/*    */   Unit owner;
/* 12 */   public int offsetX = -16; public int offsetY = 30;
/*    */ 
/*    */   public SpeechBubble(int duration, int x, int y, Unit u) {
/* 15 */     super(duration, x, y);
/* 16 */     this.unitGotHit = Core.unitGotHit;
/* 17 */     this.owner = u;
/* 18 */     throwSpeech(duration);
/*    */   }
/*    */ 
/*    */   public void update(int time)
/*    */   {
/* 23 */     if (!this.owner.alive) {
/* 24 */       this.alive = false;
/*    */     } else {
/* 26 */       this.timeElapsed += time;
/* 27 */       if (this.timeElapsed >= this.duration) {
/* 28 */         this.elapsed = true;
/* 29 */         return;
/*    */       }
/* 31 */       this.x = (this.owner.x + this.offsetX);
/* 32 */       this.y = (this.owner.y - this.offsetY);
/* 33 */       this.animSpeech.update(time);
/*    */     }
/*    */   }
/*    */ 
/*    */   public Image getImage() {
/* 38 */     return this.animSpeech.getImage();
/*    */   }
/*    */ 
/*    */   public void throwSpeech(int duration) {
/* 42 */     this.animSpeech = new Animation();
/* 43 */     this.animSpeech.addFrame(this.unitGotHit.getTileImage(this.r.nextInt(3)), duration);
/* 44 */     System.out.println("Frame added");
/*    */   }
/*    */ }
