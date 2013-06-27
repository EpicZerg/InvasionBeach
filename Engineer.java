
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Engineer extends PlayerUnit
/*    */ {
/*  4 */   public String name = "Engineer";
/*    */ 
/*    */   public Engineer(int x, int y) {
/*  7 */     super(x, y, "Engineer");
/*    */   }
/*    */ 
/*    */   public Engineer(int x, int y, int speedX, int speedY)
/*    */   {
/* 13 */     super(x, y, speedX, speedY, "Engineer");
/*    */   }
/*    */ }