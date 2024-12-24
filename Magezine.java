package quan_ly_tai_lieu;

public class Magezine extends Document
{
    private int id_release;
    private String name_magezine;
    private int month_release;
    
    public Magezine() {}
    public Magezine(String id, String name_nxb, int num_release, int id_release, String name_magezie, int month_release)
    {
        super(id, name_nxb, num_release);
        this.id_release = id_release;
        this.name_magezine = name_magezine;
        this.month_release = month_release;
    }
    public void setId_release(int id_release)
    {
        this.id_release = id_release;
    }
    public int getId_release()
    {
        return this.id_release;
    }
    public void setMonth_release(int month_release)
    {
        this.month_release = month_release;
    }
    public int getMonth_release()
    {
        return this.month_release;
    }
    public void setName_magezine(String name_magezine)
    {
        this.name_magezine = name_magezine;
    }
    public String getName_magezine()
    {
        return this.name_magezine;
    }

    @Override
    public void display()
    {
        System.out.println("Magezine: - Id: " + getId());
        System.out.println("          - NXB: " + getName_nxb());
        System.out.println("          - So ban phat hanh: " + getRealease_num());
        System.out.println("          - Ten tap chi " + getName_magezine());
        System.out.println("          - So phat hanh: " + getId_release());
        System.out.println("          - Thang phat hanh: " + getMonth_release());
    }
} 
