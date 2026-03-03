
 class gadget{
  String name;
  double price;
   
  gadget(String name,double price){
    this.name=name;
    this.price=price;
  }
  void displaygadget(){
    System.out.println("The name of gadget is " + name);
     System.out.println("The name of price is " + price);
  }
}






public class Main {




public static void printwelcome(){
    System.out.println(" ************************");
    System.out.println(" Welcome to JAVA");
    System.out.println(" ************************");
        
}












public static void main(String[] args) {
        System.out.println("Java is running on VS Code!");
        //enhanced for loop
String [] inventory ={"swords","sswords","swords"};
for(String item :inventory){
    System.out.println("You are carrying a:" + item);
}
printwelcome();

gadget [] gadgets= new gadget(3);



}

    
}


