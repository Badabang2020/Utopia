package Generator;

import java.util.ArrayList;
import java.util.Random;

public class CitizenName {
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
        lastSyl.add("inger");
        lastSyl.add("man");
        lastSyl.add("ner");
        lastSyl.add("son");
        lastSyl.add("is");
        lastSyl.add("ger");
        lastSyl.add("mon");
        lastSyl.add("");
        return lastSyl;
    }

    public static String generate(int lenght) {
        String name = "";
        for (int i = 0; i < lenght; i++) {
            name += nameSyl.get(rnd.nextInt(nameSyl.size()));
        }
        if (lenght < 3) {
            name += lastSyl.get(rnd.nextInt(lastSyl.size()));
        }
        String generatedName = name.substring(0,1).toUpperCase() + name.substring(1);
        return generatedName;
    }
}
