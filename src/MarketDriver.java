import javax.sound.midi.SysexMessage;
import java.util.*;
public class MarketDriver{
    public MarketDriver(){}

    public void play(Market m, Hero h){

            //each hero gets a chance to shop in the market
                Scanner input = new Scanner(System.in);
                System.out.println("Welcome to the Market " + h.getName() + "!");
                System.out.println("Markets can be used to either buy items or sell currently owned items");
                boolean keepShopping = true;
                do {

                    System.out.println("Would you like to buy an item (enter B) , sell an item (enter S) or exit the market (enter E)?");
                    System.out.println("In order to see your inventory and stats, enter I");

                    try {
                        String nextStep = input.next();
                        if (nextStep.toUpperCase().equals("B") ){
                            buying(h, m);
                        } else if (nextStep.toUpperCase().equals("S")) {
                            selling(h, m);

                        } else if (nextStep.toUpperCase().equals("E")) {
                            exitRemark();
                            keepShopping = false;
                        }
                        else if(nextStep.toUpperCase().equals("I")){
                            System.out.println(h);
                            System.out.println("Current Inventory of " + h.getName() + ": ");
                            System.out.println(h.getBackpack());
                        }  else{
                            System.out.println("Else Invalid input. Please try again.");
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid input. Please try again.");
                    }


                } while (keepShopping);
            }

    //check if hero has anything to even sell
    private boolean sellingPossible(Hero h){
        return(!h.getBP().isEmpty());
    }

    //simulate buying driver
    private void buying(Hero h, Market m){
        Scanner input = new Scanner(System.in);

        boolean invalidInput = true;
        do{
            System.out.println("Please enter the corresponding number next to the type of item you would like to buy. Or -1 to go back");
            ArrayList<String> all = Buyable.allGeneralTypes;
            for(int i = 0; i <all.size(); i++){
                System.out.println(i + ": " + all.get(i));
            }
            try{
                String in = input.next();
                int num = Integer.parseInt(in);
                if(num >= -1 && num < all.size()){
                    if(num == -1){
                        invalidInput = false;
                    }
                    else if(num == 0){
                        Potion temp = (listOfItems(Potion.getAll()));
                        if(temp.isPossibleToBuy(h)){
                            makePurchasePotion(h, temp);
                            h.getBP().addPotion(temp);
                            invalidInput = false;
                        }
                        else{
                            System.out.println("Insufficient funds or level");
                        }
                    }
                    else if(num == 1){
                        Spell temp = (listOfItems(m.getSpells()));
                        if(temp.isPossibleToBuy(h)){
                            makePurchaseSpells(h, temp);
                            h.getBP().addSpell(temp);
                            invalidInput = false;
                        }
                        else{
                            System.out.println("Insufficient funds or level");
                        }
                    }
                    else if(num == 2){
                        Weaponry temp = listOfItems(m.getWeapons());
                        if(temp.isPossibleToBuy(h)){
                            makePurchaseWeapons(h, temp);
                            h.getBP().addWeapon(temp);
                            invalidInput = false;
                        }
                        else{
                            System.out.println("Insufficient funds or level");
                        }
                    }
                    else if(num == 3){
                        Armory temp = listOfItems(m.getArmories());
                        if(temp.isPossibleToBuy(h)){
                            makePurchaseArmor(h, temp);
                            h.getBP().addArmor(temp);
                            invalidInput = false;
                        }
                        else{
                            System.out.println("Insufficient funds or level");
                        }
                    }
                }
                else{
                    System.out.println("Invalid input. Please try again.");
                }

            }catch (Exception e){
                System.out.println("Invalid input. Please try again.");
            }

        }while(invalidInput);
    }

    //prints all elements of list and asks user to choose
    private <T>T listOfItems(ArrayList<T> list){
        Scanner input = new Scanner(System.in);
        boolean validInput = true;

        //user chooses an input from chosen hero list
        do{
            System.out.println("Please choose a number corresponding to the item you want");
            int counter = 0;
            for(T obj: list){
                System.out.println(counter + ": " + obj);
                counter++;
            }
            String in = input.next();
            try{
                int inputted = Integer.parseInt(in);
                if(inputted < list.size() && inputted >= 0){
                    T h = (list.get(inputted));
                    return h;
                }
                else{
                    System.out.println("Invalid input. Please enter a number listed.");
                }
            }catch(Exception e){
                System.out.println("Invalid input. Please enter a number listed.");
            }
        }while(validInput);
        return(list.get(0));

    }
    private void exitRemark(){
        System.out.println("Thanks for visiting the Market!");
    }

    //updates hero after purchase of potion
    private void makePurchasePotion(Hero h, Potion p){
        h.decreaseWalletAmt(p.getCost());
    }

    //updates hero after purchase of spells
    private void makePurchaseSpells(Hero h, Spell s){
        h.decreaseWalletAmt(s.getCost());
    }

    //updates hero after purchase of weapons
    private void makePurchaseWeapons(Hero h, Weaponry w){
        h.decreaseWalletAmt(w.getCost());
    }

    //updates hero after purchase of armory
    private void makePurchaseArmor(Hero h, Armory a){
        h.decreaseWalletAmt(a.getCost());
    }

    private void receiveMoney(Hero h, int m){
        h.increaseWalletAmt(m);
    }

    //simulate selling driver
    private void selling(Hero h, Market m){
        Scanner input = new Scanner(System.in);
        if(sellingPossible(h)){
            boolean invalidInput = true;
            do{
                System.out.println("Please choose a corresponding number to the item you wish to sell or -1 to go back");
                ArrayList<String> all = Buyable.allGeneralTypes;
                for(int i = 0; i <all.size(); i++){
                    System.out.println(i + ": " + all.get(i));
                }
                try{
                    String in = input.next();
                    int num = Integer.parseInt(in);
                    if(num >= -1 && num < all.size()){
                        if(num == -1){
                            invalidInput = false;
                        }
                        else if(num == 0){
                            Potion temp = listOfItems(h.getBP().getPotions());
                            receiveMoney(h, temp.sellPrice());
                            h.getBP().deletePotion(temp);
                        }
                        else if(num == 1){
                            Spell temp = listOfItems(h.getBP().getSpells());
                            receiveMoney(h, temp.sellPrice());
                            h.getBP().deleteSpell(temp);
                        }
                        else if(num == 2){
                            Weaponry temp = listOfItems(h.getBP().getWeapons());
                            receiveMoney(h, temp.sellPrice());
                            h.getBP().deleteWeapon(temp);
                        }
                        else if(num == 3){
                            Armory temp = listOfItems(h.getBP().getArmory());
                            receiveMoney(h, temp.sellPrice());
                            h.getBP().deleteArmor(temp);
                        }
                    }
                    else{
                        System.out.println("Invalid input. Please try again.");
                    }

                }catch (Exception e){
                    System.out.println("Invalid input. Please try again.");
                }

            }while(invalidInput);

        }
        else{
            System.out.println("No items to sell!");
        }

    }
}
