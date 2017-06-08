/*
 * A project by Charles Gouraud
 * This program is designed to simulate a rover maneuvering on a plateau on Mars.
 * Feel free to contact the writer of this project with questions.
 */
package cgouraudrover;

import java.util.Scanner;


public class CgouraudRover {

    
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean sizetrue;
        int xsize=1;
        int ysize=1;
        //I'm going to be using a lot of different loops for this, mostly because I want everything to run at least once, and these are the kinds of loops that let that happen
        do{
        System.out.print("Hello and welcome to the NASA Rover Plateau exploration program. \nPlease enter the dimensions of the plateau you wish to land on, in this format: \"X Y\": ");
        Scanner sizescan = new Scanner(keyboard.nextLine()).useDelimiter(" ");
        sizetrue = true;
        //This little bit of code is a precursor to the huge nest of if statements and loops that takes place once we actually get underway. This part is making sure that the plateau instructions match what is needed
        if(sizescan.hasNextInt()){
            xsize = sizescan.nextInt();
            if(sizescan.hasNextInt()){
                ysize = sizescan.nextInt();
            }
            else 
                sizetrue= false;
        }
        else
            sizetrue = false;
        if(!sizetrue)
            System.out.println("Plateau size entered wrong, restarting program.");
        else
            //I had to put this line in to make sure that sizetrue eventually got set to true, otherwise it wouldn't get past this loop.
            sizetrue = true;
        }while(!sizetrue);
        String roverinfo;//this is initialized out here becuase the loop yelled at me if I didn't.
        do{//This lets the user send as many rovers as they would like to send, while also allowing me to run it at least once, in case they just want to imagine making a plateau
            System.out.print("To land a new Rover on the plateau, please enter its entry coordinates and starting facing. If you wish to land no more rovers, enter X. \n"
                    + "Use this formatting: \"X Y F\" where X and Y are its starting coordinates, and F is N, E, S, or W, for the cardinal directions: ");//The flavor lines of dialogue here are good for building the world.
            roverinfo = keyboard.nextLine();
            Scanner roverscan = new Scanner(roverinfo).useDelimiter(" ");
            //this is a really dense thing here, mostly so that we can get all of the information all at once, and it reads it one at a time. I probably should have the if statements terminate the loop if they are false, but this is what I went with.
            if(!roverinfo.equals("X")){
                if(roverscan.hasNextInt()){
                    int xstart = roverscan.nextInt();
                    if(roverscan.hasNextInt()){
                        int ystart = roverscan.nextInt();
                        if(roverscan.hasNext()){
                            String startface = roverscan.next();
                            //This statement will return false if the user enters the full word of their direction, like North, but I wanted the inputs to be very precise
                            if(startface.equals("N")||startface.equals("E")||startface.equals("S")||startface.equals("W")){
                                Rover curiosity = new Rover(xstart, ystart, xsize, ysize, startface.charAt(0)); //I liked the idea of naming the rover curiosity. No one is ever going to see it, so why not?
                                if(curiosity.inbounds){
                                    System.out.println("Your Rover has successfully touched down on the plateau. Please enter a set of instructions for the Rover\nYour instructions should be exclusively the characters \"L\", \"R\", and \"M\" for turn left, turn right, and move forward respectively."
                                            + "\nIf your rover successfully navigates around the plateau, you will receive a readout of its final position and facing.");
                                    String rovercommands = keyboard.nextLine();
                                    int commandNo = 0;
                                    while(curiosity.inbounds&&commandNo<rovercommands.length()){
                                        //this part recommended changing the if statements to a switch command, but I felt like this was fine for just 3 thins and one else.
                                        if(rovercommands.charAt(commandNo)=='L')
                                            curiosity.TurnLeft();
                                        else if(rovercommands.charAt(commandNo)=='R')
                                            curiosity.TurnRight();
                                        else if (rovercommands.charAt(commandNo)=='M')
                                            curiosity.move();
                                        else{
                                            //Apparently the Rover is very picky about what kinds of inputs it gets.
                                            System.out.println("Command not recognized, Rover shut down. Please try a new Rover");
                                            commandNo=rovercommands.length();
                                        }
                                        commandNo++;
                                    }
                                    //The second part of this if statement is to make sure that the rover doesn't return its position if the commands were wrong.
                                    if(curiosity.inbounds&&commandNo==rovercommands.length())
                                        System.out.println(curiosity.position());
                                }
                            }
                            else
                                //all of these else statements serve the same purpose: making sure that the boolean I used above to test the size, sizetrue, continues to be useful in case the user meses up in their entering of the data
                                sizetrue = false;
                        }
                        else sizetrue = false;
                    }
                    else sizetrue = false;
                }
                else
                    sizetrue = false;
            }
            if(!sizetrue)
                System.out.println("Rover instructions did not match needed parameters. Please try again.");
        }while(!roverinfo.equals("X"));
        System.out.println("Thank you for using NASA's Rover Plateau Exploration Program. We hope to see you again soon.");
    }

}
//When designing this program, I knew from the start that the Rover had to be its own class, to allow the user to send as many rovers as they liked to the plateau.
class Rover{
    int facingpos = -1;
    //This bit was another idea I had, thinking about how to demonstrate my knowledge of Object Oriented Programming.
    Direction[] cardinal = new Direction[4];
    int xpos;
    int ypos;
    int xsize;
    int ysize;
    boolean inbounds=true;
    Rover(){
        xpos=0;
        ypos=0;
        xsize=ysize=1;
        facingpos=0;
        //Originally, I had the directions as an array of characters in my designs, with variables in Rover that would be changed based on the facing
        cardinal[0]=new Direction();
        cardinal[1]=new Direction('E', 1, 0);
        cardinal[2]=new Direction('S', 0, -1);
        cardinal[3]=new Direction('W', -1, 0);
    }
    Rover(int x1, int y1, int x2, int y2, char dir){
        if(x1<x2&&y1<y2){
        xpos=x1;
        ypos=y1;
        xsize=x2;
        ysize=y2;
        cardinal[0]=new Direction();
        //But then I decided that just making the directions an object could make for less lines of code, as well as demonstrating my creative thinking.
        cardinal[1]=new Direction('E', 1, 0);
        cardinal[2]=new Direction('S', 0, -1);
        cardinal[3]=new Direction('W', -1, 0);
        for(int i =0; i<4; i++){
        if(cardinal[i].NSEW==dir){
            facingpos=i;
            i=4;}
        }
        }
        else{
            //these lines of output are little bits of flavor, to make the user feel like a NASA technician
            System.out.println("Rover failed to land on Plateau, please try a new Rover");
        inbounds=false;}
    }
    void TurnLeft(){
        //because of how the directions work (IE an array) I had to make sure that the directions looped around
        if(facingpos==0)
            facingpos+=4;
        facingpos-=1;
    }
    void TurnRight(){
        //this ensures that a rover facing west will end up facing north, because it'll set facingpos to -1, then increment it, making it 0, which is the Direction object associated with north.
        if(facingpos==3)
            facingpos-=4;
        facingpos+=1;
    }
    void move(){
        
        //This, like the line of dialogue above, is a failsafe, in case the user doesn't count their movements of the rover.
        //the inbounds boolean above used to only be in this method, but I decided it would be more useful if everything could access it.
        if((xpos+cardinal[facingpos].xinc<xsize)&&(ypos+cardinal[facingpos].yinc<ysize)&&(xpos+cardinal[facingpos].xinc>=0)&&(ypos+cardinal[facingpos].yinc>=0)){
            xpos+=cardinal[facingpos].xinc;
            ypos+=cardinal[facingpos].yinc;
        }
        else{
            System.out.println("Rover fell off of Plateau, please try a new rover.");
        inbounds= false;
        }
    }
    String position(){
        return ""+xpos+" "+ypos+" "+cardinal[facingpos].NSEW;
    }
}
class Direction{
    char NSEW;
    int xinc;
    int yinc;
    Direction(){
        NSEW = 'N';
        xinc = 0;
        yinc = 1;
    }
    Direction(char v, int x, int y){
        NSEW = v;
        xinc = x;
        yinc = y;
    }
}