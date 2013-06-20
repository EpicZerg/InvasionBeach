/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Radio extends Unit
/*    */ {
/*  4 */   public String name = "Radio";
/*    */ 
/*    */   public Radio(int x, int y) {
/*  7 */     super(x, y);
/*  8 */     this.name = "Radio";
/*    */   }
/*    */ 
/*    */   public Radio(int x, int y, int speedX, int speedY) {
/* 12 */     super(x, y, speedX, speedY);
/* 13 */     this.name = "Radio";
/*    */   }
/*    */ 
/*    */   public void shoutClass()
/*    */   {
/* 19 */     System.out.println(this.name);
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 25 */     return this.name;
/*    */   }
/*    */ }
