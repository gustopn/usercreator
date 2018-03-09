package work.schotte.usercreator;

import java.util.Random;

public class UserCreator {

  public static void main(String[] args) {
    boolean generatepassword = false;
    boolean numbersinusername = false;
    int generatedpasswordlength = 12;
    int generatedusernamelength = 8;
    char lastparameter = ' ';
    
    String usagehelp = "Usage:";
    usagehelp += "\n\t-n -> use numbers in username";
    usagehelp += "\n\t-p -> also generate password";
    usagehelp += "\n\t-P (n) -> generated password length (>12)";
    usagehelp += "\n\t-L (n) -> generated username length (>8)";
    
    for (String argument : args) {
      int parameternumber = 0;
      if (argument.charAt(0) != '-') {
        try { 
          parameternumber = Integer.parseInt(argument);
        } catch (NumberFormatException ex) {
          System.err.println("Some weird argument number given, exiting");
          System.exit(1);
        }
        switch (lastparameter) {
        case 'P':
          if (parameternumber > generatedpasswordlength) generatedpasswordlength = parameternumber;
          break;
        case 'L':
          if (parameternumber > generatedusernamelength) generatedusernamelength = parameternumber; 
          break;
        }
      }
      if (argument.equals("-p")) generatepassword = true;
      if (argument.equals("-n")) numbersinusername = true;
      if (argument.equals("-h")) {
        System.out.println(usagehelp);
        System.exit(0);
      }
      if (argument.charAt(0) == '-') { 
        lastparameter = argument.charAt(1);
      } else {
        lastparameter = ' ';
      }
    }
    System.out.println(createUsername(generatedusernamelength, numbersinusername));
    if (generatepassword) System.out.println(createPassword(generatedpasswordlength));
  }

  private static String createPassword(int passwordlength) {
    String createdpassword = "";
    while (createdpassword.length() < passwordlength) {
      switch ((new Random()).nextInt(3)) {
      case 0:
        createdpassword += (new Character(createSmallChar())).toString();
        break;
      case 1:
        createdpassword += (new Character(createBigChar())).toString();
        break;
      case 2:
        createdpassword += (new Character(createNumChar())).toString();
        break;
      }
    }
    return createdpassword;
  }
  
  private static String createUsername(int usernamelength, boolean includenumbers) {
    String createdusername = "";
    while (createdusername.length() < usernamelength) {
      if (includenumbers && createdusername.length() > usernamelength / 2) {
        createdusername += (new Character(createNumChar())).toString();
        continue;
      }
      createdusername += (new Character(createSmallChar())).toString();
    }
    return createdusername;
  }
  
  private static char createSmallChar() {
    int rangeofchars = 122 - 97;
    int charactervalue = 97 + (new Random()).nextInt(rangeofchars + 1);
    return (char)charactervalue;
  }
  
  @SuppressWarnings("unused")
  private static char createBigChar() {
    int rangeofchars = 90 - 65;
    int charactervalue = 65 + (new Random()).nextInt(rangeofchars + 1);
    return (char)charactervalue;
  }
  
  private static char createNumChar() {
    int rangeofchars = 57 - 48;
    int charactervalue = 48 + (new Random()).nextInt(rangeofchars + 1);
    return (char)charactervalue;
  }
}
