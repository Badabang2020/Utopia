package Generator;

import UtopiaCore.Category;
import java.util.ArrayList;
import java.util.Random;

public class FacilityName {
    private static Random rnd = new Random();
    private static ArrayList<String> nameSyl = fillNameSyl();
    private static ArrayList<String> lastSyl = fillLastSyl();

    // fill nameSyl
    private static ArrayList fillNameSyl() {
        nameSyl = new ArrayList<>();
        ////// A //////
        nameSyl.add("ab");
        nameSyl.add("at");
        nameSyl.add("an");
        nameSyl.add("ar");
        nameSyl.add("ag");
        nameSyl.add("as");
        ////// B //////
        nameSyl.add("bo");
        nameSyl.add("ben");
        nameSyl.add("bas");
        ////// C //////
        nameSyl.add("cha");
        ////// D //////
        ////// E //////
        nameSyl.add("el");
        nameSyl.add("er");
        nameSyl.add("en");
        ////// F //////
        nameSyl.add("fa");
        ////// G //////
        nameSyl.add("go");
        ////// H //////
        nameSyl.add("ho");
        ////// I //////
        nameSyl.add("il");
        ////// J //////
        nameSyl.add("ja");
        ////// K //////
        ////// L //////
        nameSyl.add("lo");
        nameSyl.add("lu");
        ////// M //////
        nameSyl.add("mi");
        nameSyl.add("min");
        nameSyl.add("ma");
        ////// N //////
        nameSyl.add("no");
        ////// O //////
        nameSyl.add("om");
        nameSyl.add("or");
        nameSyl.add("ok");
        ////// P //////
        nameSyl.add("po");
        ////// Q //////
        ////// R //////
        nameSyl.add("ra");
        ////// S //////
        nameSyl.add("se");
        ////// T //////
        nameSyl.add("ti");
        nameSyl.add("to");
        ////// U //////
        ////// V //////
        ////// W //////
        ////// X //////
        ////// Y //////
        ////// Z //////
        return nameSyl;
    }

    // fill lastSyl
    private static ArrayList fillLastSyl() {
        lastSyl = new ArrayList<>();
        lastSyl.add(" Fabrik");
        lastSyl.add("'s Laden");
        lastSyl.add(" Bedarf");
        return lastSyl;
    }

    public static String generate(int length) {
        String name = "";
        for (int i = 0; i < length; i++) {
            name += nameSyl.get(rnd.nextInt(nameSyl.size()));
        }
            name += lastSyl.get(rnd.nextInt(lastSyl.size()));
        String generatedName = name.substring(0,1).toUpperCase() + name.substring(1);
        return generatedName;
    }

    public static String generate(int length, Category category) {
        String name = "";
        for (int i = 0; i < length; i++) {
            name += nameSyl.get(rnd.nextInt(nameSyl.size()));
        }
        switch (category) {
            case Health -> {
                name += "'s Praxis";
            }
            case Money -> {
                name += " Bank";
            }
            case Food -> {
                String[] ending = {" Restaurant", " Burgers"};
                name += ending[rnd.nextInt(ending.length)];
            }
            case Sleep -> {
                name += " Hotel";
            }
            default -> {
                name += lastSyl.get(rnd.nextInt(lastSyl.size()));
            }
        }
        String generatedName = name.substring(0,1).toUpperCase() + name.substring(1);
        return generatedName;
    }
}
