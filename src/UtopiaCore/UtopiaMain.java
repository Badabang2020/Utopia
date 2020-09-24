package UtopiaCore;

public class UtopiaMain {

    public static Controller myController = new Controller();




        public static void main(String[] args) {
            System.out.println("UtopiaCore.Controller is alive since "+myController.getStartedAT().toString());



            // this is an endless loop. It will run so long as the controller returns true. It will return false on the next tick() after we call UtopiaCore.GlobalStacker.stopUtopia()
            boolean endlessloop = true;

            while (endlessloop) {
                endlessloop = myController.cycle();
            }// end of endless loop

        } // end of psvm
    }

