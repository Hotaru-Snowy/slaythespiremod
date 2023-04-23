package asimplemodforsts;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class Main {
    public Main(){
        System.out.println("hello world");
    }
    public static void initialize(){
        new Main();
    }
}
