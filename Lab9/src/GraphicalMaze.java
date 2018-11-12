import java.io.File;
import java.util.Random;
import java.util.Scanner;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GraphicalMaze extends Application{
	public static void main(String[] args) {
		
		
		
		launch(args);
	}
	String fileName;
	int columns = 10;
	int rows = 5;
	Scanner scan;
	File file;
	int wallpositionx=0;
	int wallpositiony=0;
	Group root;
	double moveleft=0;
	double moveright=0;
	double moveup=0;
	double movedown=0;
	Image wall;
	ImageView walls;
	Random rand;
	int randomobj;
	Rectangle items;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		root = new Group();
		Scene firstScene = new Scene(root, 1000,500);
		primaryStage.setTitle("Maze");
		primaryStage.setScene(firstScene);
		primaryStage.show();
		
		int [][] maze = new int[rows][columns];
		
		rand = new Random();
		int whichMaze = rand.nextInt(2);
		if (whichMaze == 0) {
			fileName = "./src/Maze1";
		}
		else if (whichMaze == 1) {
			fileName = "./src/Maze2";
		}
		
        file = new File(fileName);
        scan = new Scanner(file).useDelimiter(",");
		
		
		for (int i = 0; i < rows; i++) {
	        for (int j = 0; j <  columns; j++) {
	            try {
	                if (scan.hasNextInt()) {
	                    maze[i][j] = scan.nextInt();
	                   // System.out.println(i + "," + j +  maze[i][j]);
	                }
	            } catch (Exception e) {
	               // System.err.println("Error discovered at " + i + "," + j + ": " + e);
	            }
	        }
	        if (scan.hasNextLine()) {
	            scan.nextLine();
	        }
	    }
		double xcords[][] = new double[rows][columns];
		double ycords[][] = new double[rows][columns];
		double xcordsitems[][] = new double[rows][columns];
		double ycordsitems[][] = new double[rows][columns];
		
		for (int k=0; k< rows; k++) {
			wallpositiony =0;
			wallpositionx +=50;
			for(int l=0; l<columns; l++) {
				if (maze[k][l]== 1) {
					xcords[k][l]=wallpositionx;
					ycords[k][l]=wallpositiony;
					wallpositiony +=40;

					rand = new Random();
					randomobj =  rand.nextInt(3);
					if (randomobj == 2) {
						items = new Rectangle(xcords[k][l]+20,ycords[k][l]+20, 10, 10);
						items.setFill(Color.PURPLE);
						root.getChildren().add(items);
						xcordsitems[k][l] = xcords[k][l]+20;
						ycordsitems[k][l] = ycords[k][l]+20;
						
					}
					if (randomobj >2) {
						xcordsitems[k][l] = 0;
						ycordsitems[k][l] = 0;
					}
				}
				else if (maze[k][l]==0) {
					wall = new Image("wall.png");
					walls = new ImageView(wall);
					walls.setFitWidth(50);
					walls.setFitHeight(50);
					walls.setPreserveRatio(true);
					walls.setY(wallpositiony);
					walls.setX(wallpositionx);
					xcords[k][l]=walls.getX();
					ycords[k][l]=walls.getY();
					xcordsitems[k][l] = 0;
					ycordsitems[k][l] = 0;
					//System.out.println(k + "," + l + "Xcord: " + xcords[k][l]);
					//System.out.println(k + "," + l + "," + "Ycord: " + ycords[k][l]);
					root.getChildren().add(walls);
					
					wallpositiony+=40;
				}
			}
		}
		
		Image icon = new Image("footballplayer.jpg");
		Player player1 = new Player();
		ImageView player = new ImageView(icon);
		player.setFitHeight(30);
		player.setFitWidth(30);
		player.setPreserveRatio(true);
		player.setX(50);
		player.setY(280);
		root.getChildren().add(player);
		moveleft = player.getX();
		moveright = player.getX();
		moveup=player.getY();
		movedown=player.getY();
		

		
		firstScene.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.A) {
		        moveleft = player1.move("left");
		        player.setX(moveleft + moveright);
		        for (int m = 0; m< rows; m++) {
					for (int n=0; n<columns; n++) {
						if(maze[m][n] == 0) {
							if(player.getY()> (ycords[m][n]) && player.getY()<(ycords[m][n]+50) && player.getX()>(xcords[m][n]) && player.getX()<(xcords[m][n]+50)) {
								
								
								player.setX(xcords[m][n]+50);
							}
							else if(player.getY()+30> (ycords[m][n]) && player.getY()+30<(ycords[m][n]+50) && player.getX()+30>(xcords[m][n]) && player.getX()+30<(xcords[m][n]+50)) {
								
								
								player.setX(xcords[m][n]+50);
							}
						}
						else if(maze[m][n] ==1) {
							if (xcordsitems[m][n]>0 && ycordsitems[m][n]>0) {
								if(player.getY()< (ycordsitems[m][n]) && player.getY()+30>(ycordsitems[m][n]+10) && player.getX()<(xcordsitems[m][n]) && player.getX()+30>(xcordsitems[m][n]+10)) {
									items = new Rectangle(xcords[m][n]+20,ycords[m][n]+20, 10, 10);
									items.setFill(Color.WHITE);
									root.getChildren().add(items);
									player.toFront();

									System.out.println("white rectangle");
								}

							}
							
						}
					}
				}
			    if (player.getX()>300) {
			    	Text win = new Text("You Win!!");
			    	win.setX(300);
			    	win.setY(300);
			    	root.getChildren().add(win);
			    	System.out.println("win");
			    }
		  
				
		    }
		    moveleft = player.getX();
		    moveright = player.getX();
		    moveup = player.getY();
		    movedown = player.getY();
		    
		    if (e.getCode() == KeyCode.D) {
		    	moveright =player1.move("right");
		        player.setX(moveright + moveleft);
		        for (int m = 0; m< rows; m++) {
					for (int n=0; n<columns; n++) {
						if(maze[m][n] == 0) {
							if(player.getY()> (ycords[m][n]) && player.getY()<(ycords[m][n]+50) && player.getX()>(xcords[m][n]) && player.getX()<(xcords[m][n]+50)) {
								
								
								player.setX(xcords[m][n]-30);
							}
							else if(player.getY()+30> (ycords[m][n]) && player.getY()+30<(ycords[m][n]+50) && player.getX()+30>(xcords[m][n]) && player.getX()+30<(xcords[m][n]+50)) {
								
								
								player.setX(xcords[m][n]-30);
							}
						}
						else if(maze[m][n] ==1) {
							if (xcordsitems[m][n]>0 && ycordsitems[m][n]>0) {
								if(player.getY()< (ycordsitems[m][n]) && player.getY()+30>(ycordsitems[m][n]+10) && player.getX()<(xcordsitems[m][n]) && player.getX()+30>(xcordsitems[m][n]+10)) {
									items = new Rectangle(xcords[m][n]+20,ycords[m][n]+20, 10, 10);
									items.setFill(Color.WHITE);
									root.getChildren().add(items);
									player.toFront();

									System.out.println("white rectangle");
								}

							}
							
						}
					}
				}
			    if (player.getX()>300) {
			    	Text win = new Text("You Win!!");
			    	win.setX(300);
			    	win.setY(300);
			    	root.getChildren().add(win);
			    	System.out.println("win");
			    }
				
		    }
		    moveleft = player.getX();
		    moveright = player.getX();
		    moveup = player.getY();
		    movedown = player.getY();
		    
		    if (e.getCode() == KeyCode.W) {
		    	moveup = player1.move("up");
		    	player.setY(moveup +movedown);
		    	 for (int m = 0; m< rows; m++) {
						for (int n=0; n<columns; n++) {
							if(maze[m][n] == 0) {
								if(player.getY()> (ycords[m][n]) && player.getY()<(ycords[m][n]+50) && player.getX()>(xcords[m][n]) && player.getX()<(xcords[m][n]+50)) {
									
									player.setY(ycords[m][n]+50);
									
								}
								else if(player.getY()+30> (ycords[m][n]) && player.getY()+30<(ycords[m][n]+50) && player.getX()+30>(xcords[m][n]) && player.getX()+30<(xcords[m][n]+50)) {
									
									player.setY(ycords[m][n]+50);
									
								}
								
							}
							else if(maze[m][n] ==1) {
								if (xcordsitems[m][n]>0 && ycordsitems[m][n]>0) {
									if(player.getY()< (ycordsitems[m][n]) && player.getY()+30>(ycordsitems[m][n]+10) && player.getX()<(xcordsitems[m][n]) && player.getX()+30>(xcordsitems[m][n]+10)) {
										items = new Rectangle(xcords[m][n]+20,ycords[m][n]+20, 10, 10);
										items.setFill(Color.WHITE);
										root.getChildren().add(items);
										player.toFront();

										System.out.println("white rectangle");
									}

								}
								
							}
							
						}
					}
		 	    if (player.getX()>300) {
			    	Text win = new Text("You Win!!");
			    	win.setX(300);
			    	win.setY(300);
			    	root.getChildren().add(win);
			    	System.out.println("win");
			    }
				
		    }
		    moveleft = player.getX();
		    moveright = player.getX();
		    moveup = player.getY();
		    movedown = player.getY();
		    
		    if (e.getCode() == KeyCode.S) {
		    	movedown = player1.move("down");
		    	player.setY(movedown + moveup);
		    	for (int m = 0; m< rows; m++) {
					for (int n=0; n<columns; n++) {
						if(maze[m][n] == 0) {
							if(player.getY()> (ycords[m][n]) && player.getY()<(ycords[m][n]+50) && player.getX()>(xcords[m][n]) && player.getX()<(xcords[m][n]+50)) {
								
								player.setY(ycords[m][n]-30);
								
							}
							else if(player.getY()+30> (ycords[m][n]) && player.getY()+30<(ycords[m][n]+50) && player.getX()+30>(xcords[m][n]) && player.getX()+30<(xcords[m][n]+50)) {
								
								player.setY(ycords[m][n]-30);
								
							}
						}
						else if(maze[m][n] ==1) {
							if (xcordsitems[m][n]>0 && ycordsitems[m][n]>0) {
								if(player.getY()< (ycordsitems[m][n]) && player.getY()+30>(ycordsitems[m][n]+10) && player.getX()<(xcordsitems[m][n]) && player.getX()+30>(xcordsitems[m][n]+10)) {
									items = new Rectangle(xcords[m][n]+20,ycords[m][n]+20, 10, 10);
									items.setFill(Color.WHITE);
									root.getChildren().add(items);
									player.toFront();

									System.out.println("white rectangle");
								}

							}
							
						}
					}
				}
			    if (player.getX()>300) {
			    	Text win = new Text("You Win!!");
			    	win.setX(300);
			    	win.setY(300);
			    	root.getChildren().add(win);
			    	System.out.println("win");
			    }
		    }
		    moveleft = player.getX();
		    moveright = player.getX();
		    moveup = player.getY();
		    movedown = player.getY();
		    
		});
	    moveleft = player.getX();
	    moveright = player.getX();
	    moveup = player.getY();
	    movedown = player.getY();
	    
	    Text movement = new Text("To move press a,s,d,and w (If you are stuck, try moving down).");
	    movement.setX(300);
	    movement.setY(250);
	    root.getChildren().add(movement);
	    


		
	}
		

		
		
}	


