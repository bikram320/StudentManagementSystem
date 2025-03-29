package StudentManagementSystem;

public class StudentData {

    private final int Std_SymbolNo;
    private final String Std_Name;
    private final String Std_Course;
    private final String Std_Phone;

    public StudentData(int symbolNo, String name, String course, String Phone) {
        this.Std_Name = name ;
        this.Std_SymbolNo = symbolNo ;
        this.Std_Course = course;
        this.Std_Phone = Phone ;
    }
    public int getSymbolNo(){
        return Std_SymbolNo;
    }
    public String getName(){
        return Std_Name;
    }
    public String getCourse(){
        return Std_Course;
    }
    public String getPhone(){
        return Std_Phone;
    }
}
