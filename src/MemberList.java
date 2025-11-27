import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class MemberList {
    private static ArrayList<Member> allMembers = new ArrayList<>();
    private ArrayList<Member> under18 = new ArrayList<>();
    private ArrayList<Member> over18 = new ArrayList<>();
    private final Member member; // reference der lader MemberList

    MemberList(Member member){
        this.member = member;
    }




    public static void main(String[] args) {
        Member t = new Member();
        MemberList n = new MemberList(t);
        System.out.println("Age of person is "+n.calculateAge(LocalDate.of(2004, 4, 4)));
    }
}
