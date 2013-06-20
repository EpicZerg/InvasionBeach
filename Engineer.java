/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Engineer extends Unit
/*    */ {
/*  4 */   public String name = "Engineer";
/*    */ 
/*    */   public Engineer(int x, int y) {
/*  7 */     super(x, y);
/*  8 */     this.name = "Engineer";
/*    */   }
/*    */ 
/*    */   public Engineer(int x, int y, int speedX, int speedY)
/*    */   {
/* 13 */     super(x, y, speedX, speedY);
/* 14 */     this.name = "Engineer";
/*    */   }
/*    */ 
/*    */   public void shoutClass()
/*    */   {
/* 21 */     System.out.println(this.name);
/*    */   }
/*    */ 
/*    */   public String getName()
/*    */   {
/* 27 */     return this.name;
/*    */   }
/*    */ }