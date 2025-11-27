import java.time.LocalDate;

//public class Subscription {
//  public static void updateAgeStatus(Member member){
//      int age = member.calculateAge();
//      if(member.getCurrentAge() < 18){
//          member.setAgeStatus(AgeStatus.Under18);
//      } else if (member.getCurrentAge() < 60) {
//          member.setAgeStatus(AgeStatus.Over18);
//      } else{
//          member.setAgeStatus(AgeStatus.Over60);
//      }
//  }
//  public static double getYearlyQuota(Member member){
//      switch (member.getAgeStatus()){
//          case Under18: return 1000;
//          case Over18: return 1600;
//          case Over60: return 1200;
//          default:
//      }
//
//      return 0;
//  }
//}