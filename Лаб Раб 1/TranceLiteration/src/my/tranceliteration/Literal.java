
package my.tranceliteration;

import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import java.util.List;
import static javax.management.Query.value;
import static javax.swing.UIManager.get;

public class Literal {
    static List <String> Litterax = new ArrayList<>();
    static List <Liter> LitList = new ArrayList<>();
    
    static Liter litera;
    static String message="";
    private static String[] strFSource; // массив строк исходного текста
        public static State  enumFState;
        private static int intFSourceRowSelection;
        private static int intFSourceColSelection;
         static int errorstr=0;                   
        public static enum State {
            Start,      //«К исходному тексту обращаемся впервые»
            Continie,   //К исходному тексту обращаемся повторно (не впервые)»
            Finish;     //«Обработка текста завершена, указатель на текущую литеру находится за пределами текста»
    }              
       public static enum LType {
        Letter,         // Строчная Буква
        Digit,          // Цифра
        EndRow,         //Конец строки
        EndText,        //Конец текста
        ReservedSymbol, //Зарезервированный спецсивол 
        SPACE,
        Unknown;          //Неизвесный
    }
    public static class Liter {
        public final LType t;
        public final String c; // contents mainly for atom tokens
        public Liter(LType t, String c) {
            this.t = t;
            this.c = c;
        }    
    }
    public static Liter Get_Symbol(char x) { 
//        System.out.println(intFSourceColSelection +"intFSourceColSelection");
        if(enumFState==null){ 
            enumFState= Literal.State.Start;
           }else{
               enumFState=Literal.State.Continie;
           }           
            if(intFSourceColSelection>(strFSource[intFSourceRowSelection].length()-1)){
                errorstr=0;
                intFSourceRowSelection++;
                if(intFSourceRowSelection<=strFSource.length-1){
                   intFSourceColSelection=0;
                   litera = new Literal.Liter(LType.EndRow, "\n");
                return litera;
                }else{
                    litera = new Literal.Liter(LType.EndText, "\n");
                    enumFState=Literal.State.Finish;
                return litera;
                }
            }else{       
                if (x==' '){
                    litera =new Literal.Liter(LType.SPACE, " ");
                    intFSourceColSelection++;
                    return litera;
                //Character.toString(x) - переводим char в String для консруктора Liter                   
                }else if(x>='a'&&x<='d') {
                    litera =  new Literal.Liter(LType.Letter, Character.toString(x));
                    intFSourceColSelection++;
                    return litera;
                }else if (x >= '0' && x <= '1'){
                    litera = new Literal.Liter(LType.Digit, Character.toString(x));
                    intFSourceColSelection++;
                    return litera;
                }else if (x == '+' || x=='*'){      
                      litera = new Literal.Liter(Literal.LType.ReservedSymbol, Character.toString(x));
                      intFSourceColSelection++;
                    return litera;
                }else{ 
                    
                    errorstr=1;
                    intFSourceColSelection++;
                    return null;               
                }
             }        
    }
    public static String Text_Liter(){ // отделяем символ от текста и передаем в транслитератор  
            String slou = strFSource [intFSourceRowSelection];
            char x;
                if(intFSourceColSelection< slou.length()){
                     x = slou.charAt(intFSourceColSelection);
                }else{     
                    x = '\n';
                }
                String mas =Message(Get_Symbol(x));
                return mas;     
    }
    public static String Message (Liter litera){      
           // message+=" ("+litera.c+" - "+(litera.t.toString())+") ";  
          if(litera!=null){  
           message+=" ("+(litera.t.toString())+" - "+litera.c+") "; 
            return message;
          }else{
              return message;
          }
    } 
    public static void mainText (String text) { // Чтение тескта из окна ввода, при необходимости можно прочесть из файла       
            if(enumFState==null){
            if(text.length() < 1) {
            System.out.println("Usage: java Lexer \"((some Scheme) (code to) lex)\".");            
            } else{
               for (String list : text.split("\n")){ //перенос исходного текста в список
                    Litterax.add(list);       
            }
                 strFSource = new String [Litterax.size()]; // переносим текст в мссив строк
            for(int i=0;i<Litterax.size();i++){
                 strFSource[i]=Litterax.get(i);
       }                 
    }
     }else{
         return;
     }
 }
}
 