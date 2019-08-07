package com.citi.regex.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
  @Test
  public void testEmailReceipts() {
    String emailPattern = "^\\w+(.+?)\\w+@\\w+(.+?)\\w+";
    String emailReceipts =
        "dl.gt.global.simpliciti.timemachine.support@imceu.eu.ssmb.com;dlgt.global.timemachine.core.development@imceu.eu.ssmb.com";

    Pattern compile = Pattern.compile(emailPattern);
    Matcher matcher = compile.matcher(emailReceipts);
    System.out.println(matcher.matches());
  }

  @Test
  public void testSourceSystem() {
    String sourceSystem = "STELLAR:90:100";
    //    String sourceSystem = "TPSDERIV:365:360,TPSDERIVNY:90:100,TPSDERIV_ALEX,TPSDERIVAU";
    //    String sourceSystemPattern = "^(?:[\\w+_:,])+$";
    String s = "^((?:[\\w+_:,])+)*$";
//    String sourceSystemPattern = "^((?:[\\w+_:,])+)*$";

    Pattern compile = Pattern.compile(s);
    Matcher matcher = compile.matcher(sourceSystem);
    System.out.println(matcher.matches());
  }

  @Test
  public void testQuantifier() {
    String greedyQuantifierPattern = "a?";
    String reluctantQuantifierPattern = "a*";
    String possesiveQuantifierPattern = "a+";

    String input = "aaaaa";

    commonPatternAndInputString(greedyQuantifierPattern, input);
    commonPatternAndInputString(reluctantQuantifierPattern, input);
    commonPatternAndInputString(possesiveQuantifierPattern, input);
  }

  private void commonPatternAndInputString(String greedyQuantifierPattern, String input) {
    System.out.println("Pattern is: " + greedyQuantifierPattern);
    System.out.println("Input string is: " + input);
    Pattern pattern = Pattern.compile(greedyQuantifierPattern);
    Matcher matcher = pattern.matcher(input);
    if (matcher.matches()) {
      int i = 0;
      matcher.group();
      System.out.printf(
          "I found  the text \"%s\" starting at index %d and ending at index %d\n",
          input, matcher.start(i), matcher.end());
    } else {
      System.out.println("No match found");
    }
  }
}
