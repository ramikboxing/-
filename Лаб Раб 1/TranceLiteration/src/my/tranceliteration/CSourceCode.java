package my.tranceliteration;


import java.util.List;
import static jdk.nashorn.internal.objects.NativeString.charAt;
import static my.tranceliteration.Literal.enumFState;
import static my.tranceliteration.Literal.errorstr;

public class CSourceCode {
        private  String[] strFSource ; // массив строк исходного текста
        public   State  enumFState;
        private  int intFSourceRowSelection=0;
        private  int intFSourceColSelection=-1;
        public String [] strPSource;      
    public  enum State {
            Start,      //«К исходному тексту обращаемся впервые»
            Continie,   //К исходному тексту обращаемся повторно (не впервые)»
            Finish;     //«Обработка текста завершена, указатель на текущую литеру находится за пределами текста»
    }   
    
     public  enum LitType {
        Letter,         // Строчная Буква
        Digit,          // Цифра
        EndRow,         //Конец строки
        EndText,        //Конец текста
        ReservedSymbol, //Зарезервированный спецсивол 
        SPACE,
        Unknown;          //Неизвесный
    }
      public  class CLiteral {
        public final LitType t;
        public final char c; // contents mainly for atom tokens
        public CLiteral(LitType t, char c) {
            this.t = t;
            this.c = c;
        } 
      }
      
       public  CLiteral Get_Symbol() { 
         CLiteral litVSelected; 
         intFSourceColSelection++;   
            if(intFSourceColSelection>(strFSource[intFSourceRowSelection].length()-1)){
               intFSourceRowSelection++;
                if(intFSourceRowSelection<=strFSource.length-1){
                   intFSourceColSelection=-1;
                   litVSelected = new CLiteral (LitType.EndRow, '\n');
                   enumFState=State.Continie;
                   return litVSelected;
                }else{
                    litVSelected = new CLiteral (LitType.EndText, '\0');
                    enumFState=State.Finish;
                    return litVSelected;
                }  
            }else{
               char x =  strFSource[intFSourceRowSelection].charAt(intFSourceColSelection);
               litVSelected = new CLiteral (LitType.Unknown,x );
               enumFState=State.Continie; 
               return litVSelected;
             }        
        }
           public  void ReadText (String text) { // Чтение тескта из окна ввода, при необходимости можно прочесть из файла       
            if(enumFState==null){
            if(text.length() < 1) {
            System.out.println("Usage: java Lexer \"((some Scheme) (code to) lex)\".");            
            } else{
                int j = 0;
               for (String list : text.split("\n")){ //вычисляем кол-во строк
                   j++;                    
            }
                 strFSource = new String [j]; // создаем массив нужного размера
                    int i=0;
                for (String list : text.split("\n")){
                strFSource[i]=list;
                i++;
                     System.out.println(list);
            }
    }
     }else{
         return;
     }
 }
           public String PrintText (List list){
               String printText = " ";
               for(int i=0;i<list.size();i++){
                 printText+=list.get(i);
       }                              
               return printText;
               
           }
                 
       
}
