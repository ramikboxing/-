
package my.tranceliteration;

import java.util.ArrayList;
import java.util.List;

public class CLex extends CSourceCode {
        List <String> ListLitter = new ArrayList<>();
        public String [] strFMessage;
        private CLiteral litSelected;

     
    public  CLiteral NextLiteral() throws Exception{
      litSelected=Get_Symbol();
        //System.out.println("Symbol: "+Character.toString(litSelected.c));
        if(litSelected.t==LitType.Unknown ||litSelected.t==LitType.EndRow ||litSelected.t==LitType.EndText ){ 
          if( litSelected.c==' '){ 
              litSelected = new CLiteral (LitType.SPACE, litSelected.c);
              return litSelected;
          }else if (litSelected.c>='a'&& litSelected.c<='d'){
              litSelected = new CLiteral (LitType.Letter, litSelected.c);
              return litSelected;
          }else if (litSelected.c=='0'||litSelected.c=='1'){
               litSelected = new CLiteral (LitType.Digit, litSelected.c);
               return litSelected;
          }else if (litSelected.c=='+'||litSelected.c=='*'){
               litSelected = new CLiteral (LitType.ReservedSymbol, litSelected.c);
               return litSelected;
          } else if (litSelected.t==LitType.EndRow ||litSelected.t==LitType.EndText){
                return litSelected;           
          }else {
             throw new Exception  (Character.toString(litSelected.c)+" Символ вне алфавита");
          }               
        }else return null;    
    }
}


    
