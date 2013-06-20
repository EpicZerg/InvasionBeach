/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class Tileset
/*    */ {
/*    */   public int height;
/*    */   public int width;
/* 13 */   public ArrayList<BufferedImage> tileset = new ArrayList();
/*    */ 
/*    */   public Tileset(String path, int p_width, int p_height) {
/*    */     try {
/* 17 */       BufferedImage pictureTileset = ImageIO.read(getClass().getResource(path));
/* 18 */       this.width = (pictureTileset.getWidth() / p_width);
/* 19 */       this.height = (pictureTileset.getHeight() / p_height);
/* 20 */       System.out.println("Height:Width " + this.height + ":" + this.width);
/* 21 */       for (int x = 0; x < this.width; x++)
/* 22 */         for (int y = 0; y < this.height; y++) {
/* 23 */           BufferedImage tile = pictureTileset.getSubimage(x * p_width, 
/* 24 */             y * p_height, p_width, p_height);
/* 25 */           this.tileset.add(tile);
/*    */         }
/*    */     }
/*    */     catch (IOException e) {
/* 29 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public BufferedImage getTileImage(int x, int y) {
/* 34 */     int i = x + y * 9;
/* 35 */     return (BufferedImage)this.tileset.get(i);
/*    */   }
/*    */ 
/*    */   public BufferedImage getTileImage(int number) {
/* 39 */     return (BufferedImage)this.tileset.get(number);
/*    */   }
/*    */ 
/*    */   public int getHeight() {
/* 43 */     return this.height;
/*    */   }
/*    */ 
/*    */   public int getWidth() {
/* 47 */     return this.width;
/*    */   }
/*    */ }