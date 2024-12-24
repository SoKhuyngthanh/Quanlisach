package quan_ly_tai_lieu;

public class Newspaper extends Document 
{
    private String title;
    private int day_release;
    private int month_release;
    private int year_release;
    
    public Newspaper() {}
    public Newspaper(String id, String name_nxb, int release_num, String title, int day_release, int month_release, int year_release) 
    {
        super(id, name_nxb, release_num);
        this.title = title;
        this.day_release = day_release;
        this.month_release = month_release;
        this.year_release = year_release;
    }
    public void setDay_release(int day_release)
    {
        this.day_release = day_release;
    }
    public int getDay_release()
    {
        return this.day_release;
    }
    public void setMonth_release(int month_release)
    {
        this.month_release = month_release;
    }
    public int getMonth_release()
    {
        return this.month_release;
    }
    public void setYear_release(int year_release)
    {
        this.year_release = year_release;
    }
    public int getYear_release()
    {
        return this.year_release;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getTitle()
    {
        return this.title;
    }

    @Override
    public void display()
    {
        System.out.println("Newspaper: - Id: " + getId());
        System.out.println("           - NXB: " + getName_nxb());
        System.out.println("           - So ban phat hanh: " + getRealease_num());
        System.out.println("           - Tieu de: " + getTitle());
        System.out.println("           - Ngay phat hanh: " + getDay_release());
        System.out.println("           - Thang phat hanh: " + getMonth_release());
        System.out.println("           - Nam phat hanh: " + getYear_release());
    }
} 

