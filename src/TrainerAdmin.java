public class TrainerAdmin {


    public static void addDiscipline() {
        System.out.print("Skriv medlems ID: ");
        String id = UI.scn.nextLine();

        System.out.print("Vælg disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
        String d = UI.scn.nextLine().toUpperCase();

        try {
            Discipline discipline = Discipline.valueOf(d);
            addDisciplineToCompetitive(id, discipline);
        } catch (IllegalArgumentException e) {
            System.out.println("Ugyldig disciplin.");
        }
    }


    static void addDisciplineToCompetitive(String memberID, Discipline discipline){

        Member fundet = null;
        for(Member m : MemberList.allMembers){
            if (m.getMemberID().equals(memberID)){
                fundet = m;
                break;
            }
        }

        if (fundet == null){
            System.out.println("Medlem ikke fundet.");
            return;
        }

        if (!(fundet instanceof Competitive)){
            System.out.println("Medlemmet er ikke konkurrencesvømmer.");
            return;
        }

        Competitive comp = (Competitive) fundet;
        comp.addDiscipline(discipline);
    }


    static void removeDiscipline(){
        String ID;
        Discipline discipline;
        System.out.println("Skriv medlems ID:");
        ID = UI.scn.nextLine();
        while (true){ // loop of doom and despair
            System.out.println("Vælg disciplin (BUTTERFLY/BACKSTROKE/BREASTSTROKE/FREESTYLE): ");
            String d = UI.scn.nextLine().toUpperCase();

            try{
                discipline = Discipline.valueOf(d);
                break;
            } catch (IllegalArgumentException e){
                System.out.println("Ugyldig disciplin.");
                return;
            }
        }
        Member fundet = null;
        for(Member m : MemberList.allMembers){
            if (m.getMemberID().equals(ID)){
                fundet = m;
                break;
            }
        }

        if (fundet == null){
            System.out.println("Medlem ikke fundet.");
            return;
        }

        Competitive comp = (Competitive) fundet;
        comp.removeDiscipline(discipline);
    }
}