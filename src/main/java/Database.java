import java.io.IOException;
import java.util.ArrayList;
public class Database {
    Filehandler filehandler = new Filehandler();
    private final ArrayList<Member> membersArrayList = new ArrayList<>(1);
    public ArrayList<Member> getMembersArrayList() {
        return membersArrayList;
    }
    public Database() throws IOException {
        setMembersArrayList(filehandler.loadData());
    }

    public String generateMemberID(String name, String surName) {
        String prefix = name.substring(0, 2).toLowerCase() + surName.substring(0, 2).toLowerCase();
        int count = 1;

        for (Member member : membersArrayList) {
            if (member.getMemberID().startsWith(prefix)) {
                count++;
            }
        }

        String countString = String.format("%04d", count);

        return prefix + countString;
    }

    public void addMember(String name, String surName, String email, int phoneNumber, String dateOfBirth, String dateJoined, boolean isActive, boolean isCompetitor) {
        String memberID = generateMemberID(name, surName);
        membersArrayList.add(0, new Member(name, surName, email, phoneNumber, dateOfBirth, dateJoined, isActive, isCompetitor, memberID));
    }

    public void setMembersArrayList(ArrayList<Member> liste) {
        membersArrayList.addAll(liste);
    }

    public void saveMembers() {
        filehandler.saveMembers(membersArrayList);
    }

    public String showMembers() {
        StringBuilder stringBuilder = new StringBuilder();
        if (membersArrayList.isEmpty()) {
            return "Der er ikke nogen medlemmer i databasen,";
        } else {
            stringBuilder.append("""
                    ~~~~~~~~~~~~~~~~~~~~~
                    Medlemmer i databasen
                    ~~~~~~~~~~~~~~~~~~~~~
                    """);
            for (Member member : membersArrayList) {
                stringBuilder.append("\n").append(member).append("\n--------------------\n");
            }
            return stringBuilder.toString();
        }
    }
        public Member findMemberByID(String MemberID) {
            for (Member member : membersArrayList) {
                if (member.getMemberID().equals(MemberID)) {
                    return member;
                }
            }
            return null;
        }
        public void removeMember(Member member) {
            membersArrayList.remove(member);

        }
    }