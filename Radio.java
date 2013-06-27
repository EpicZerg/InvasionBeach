
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Radio extends PlayerUnit
/*    */ {
/*  4 */   public String name = "Radio";
/*    */ 
/*    */   public Radio(int x, int y) {
/*  7 */     super(x, y, "Radio");
/*    */   }
/*    */ 
/*    */   public Radio(int x, int y, int speedX, int speedY) {
/* 12 */     super(x, y, speedX, speedY, "Radio");
/*    */   }
/*    */ 
/*    */ }
