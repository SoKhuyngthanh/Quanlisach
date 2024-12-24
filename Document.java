package quan_ly_tai_lieu;

public class Document 
{
    private String id;
    private String name_nxb;
    private int release_num;

    public Document() {}
    public Document(String id, String name_nxb, int release_num)
    {
        this.id = id;
        this.name_nxb = name_nxb;
        this.release_num = release_num;
    }
    // Cac getter va settet
    public void setId(String id)
    {
        this.id = id;
    }
    public String getId()
    {
        return this.id;
    }
    public void setName_nxb(String name_nxb)
    {
        this.name_nxb = name_nxb;
    }
    public String getName_nxb()
    {
        return this.name_nxb;
    }
    public void setRealease_num(int release_num)
    {
        this.release_num = release_num;
    }
    public int getRealease_num()
    {
        return this.release_num;
    }
    public void display() {}
} 


