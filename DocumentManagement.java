package quan_ly_tai_lieu;
import java.util.ArrayList;

public class DocumentManagement
{
    private ArrayList<Document> documents;

    public DocumentManagement()
    {
        documents = new ArrayList<>();
    }

    // Thêm phương thức này
    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void addDocument(Document document)
    {
        documents.add(document);
    }

    public boolean removeDocument(String id)
    {
        Document document = this.documents.stream().filter(doc->doc.getId().equals(id)).findFirst().orElse(null);

        if (document == null) {
            return false;
        }
        this.documents.remove(document);

        return true;
    }

    public void searchDocument(String id)
    {
        Document document = this.documents.stream().filter(doc->doc.getId().equals(id)).findFirst().orElse(null);

        if (document == null) {
            System.out.println("Khong tim thay tai lieu!");
            return;
        }
        System.out.println("Tai lieu da duoc tim thay!");
        document.display();
    }

    public boolean editDocument(String id, Document newDocument) {
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getId().equals(id)) {
                if (newDocument != null) {
                    documents.set(i, newDocument);
                    System.out.println("Tài liệu với ID " + id + " đã được cập nhật thành công.");
                    return true;
                } else {
                    System.out.println("Tài liệu mới không hợp lệ.");
                    return false;
                }
            }
        }
        System.out.println("Không tìm thấy tài liệu với ID " + id);
        return false;
    }

    public void displayDocument()
    {
        this.documents.forEach(doc->doc.display());
    }

    public void searchByBooks()
    {
        this.documents.stream().filter(doc->doc instanceof Book).forEach(doc->doc.display());
    }

    public void searchByMagezine()
    {
        this.documents.stream().filter(doc->doc instanceof Magezine).forEach(doc->doc.display());
    }

    public void searchByNewspaper()
    {
        this.documents.stream().filter(doc->doc instanceof Newspaper).forEach(doc->doc.display());
    }
}
 
