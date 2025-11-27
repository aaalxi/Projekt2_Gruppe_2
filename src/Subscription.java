import java.time.LocalDate;

public class Subscription {
  public static double getYearlyQuota(Member member){
      switch (member.getAgeStatus()){
          case Under18: return 1000;
          case Over18: return 1600;
          case Over60: return 1200;
          case Dummystatus: return 0;
          default:
      }
      return 0;
  }
}