package UtopiaCore;

public class UtopiaMain {


    public static Controller myController ; // will be accessible from everywhere with UtopiaMain.myController
    public static GlobalStacker myGlobalStacker;  // will be accessible from everywhere with UtopiaMain.myGlobalStacker




    public static void main(String[] args) throws InterruptedException {
            myController = new Controller();
            myGlobalStacker = new GlobalStacker();
            myController.start(); // here we can init the tester.

            System.out.println("UtopiaCore.Controller is alive since "+myController.getStartedAT().toString());



            // this is an endless loop. It will run so long as the controller returns true. It will return false on the next tick() after we call UtopiaCore.GlobalStacker.stopUtopia()
            boolean endlessloop = true;

            while (endlessloop) {
                endlessloop = myController.cycle();
            }// end of endless loop

        } // end of psvm
    }

