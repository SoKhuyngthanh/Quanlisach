package quan_ly_tai_lieu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        DocumentManagement xaptailieu1 = new DocumentManagement();

        // Tao Map chua danh sach nguoi dung voi thong tin ten dang nhap, mat khau va quyen
        Map<String, User> users = new HashMap<>();
        users.put("NhatHuy", new User("NhatHuy", "nhathuy123", Role.ADMIN));  // Quan tri vien
        users.put("BaoHuy", new User("BaoHuy", "1", Role.ADMIN));      // Quan tri vien
        users.put("Trang", new User("Trang", "trang123", Role.USER));            // Nguoi dung
        users.put("Vu", new User("Vu", "vu123", Role.USER));                    // Nguoi dung
        users.put("Ha", new User("Ha", "ha123", Role.USER));                    // Nguoi dung
        users.put("Hao", new User("Hao", "hao123", Role.USER));                // Nguoi dung

        // Quan ly dang nhap
        User currentUser = null;
        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            System.out.println("Dang nhap");
            System.out.print(" - Nhap ten nguoi dung: ");
            String username = scan.nextLine();
            System.out.print(" - Nhap mat khau: ");
            String password = scan.nextLine();

            // Kiem tra thong tin dang nhap
            if (users.containsKey(username)) {
                User user = users.get(username);
                if (user.kiemtraPassword(password)) {
                    currentUser = user;
                    isLoggedIn = true;
                    System.out.println("Dang nhap thanh cong voi quyen " + (currentUser.getRole() == Role.ADMIN ? "Quan tri vien" : "Nguoi dung"));
                } else {
                    System.out.println("Mat khau khong dung. Vui long thu lai!");
                    System.out.println("-------------------------------------------");
                }
            } else {
                System.out.println("Ten nguoi dung khong ton tai. Vui long thu lai!");
                System.out.println("-------------------------------------------");
            }
        }

        System.out.println();

        // Chuong trinh menu chinh
        boolean exit = false;
        while (!exit) {
            System.out.println("1. Them tai lieu");
            System.out.println("2. Xoa tai lieu");
            System.out.println("3. Sua tai lieu");
            System.out.println("4. Hien thi tai lieu");
            System.out.println("5. Thoat");

            System.out.println();
            System.out.print("Chon chuc nang: ");
            int option = scan.nextInt();
            scan.nextLine();  // Consume newline character

            switch (option) {
                case 1: // Them tai lieu
                    if (currentUser.getRole() == Role.ADMIN) {
                        addDocument(scan, xaptailieu1);
                    } else {
                        System.out.println("Ban khong co quyen them tai lieu!");
                    }
                    break;
    
                case 2: // Xoa tai lieu
                    if (currentUser.getRole() == Role.ADMIN) {
                        deleteDocument(scan, xaptailieu1);
                    } else {
                        System.out.println("Ban khong co quyen xoa tai lieu!");
                    }
                    break;

                case 3: // Sửa tài liệu
                    if (currentUser.getRole() == Role.ADMIN) {
                        editDocument(scan, xaptailieu1);
                    } else {
                        System.out.println("Ban khong co quyen sua tai lieu!");
                    }
                    break;
                case 4: // Hien thi thong tin tai lieu
                    displayDocuments(scan, xaptailieu1);
                    break;

                case 5: // Thoat
                    exit = true;
                    break;

                case 6: // Quan ly tai khoan nguoi dung (Admin only)
                    if (currentUser.getRole() == Role.ADMIN) {
                        manageUsers(scan);
                    } else {
                        System.out.println("Ban khong co quyen quan ly tai khoan nguoi dung.");
                    }
                    break;

                default:
                    System.out.println("Lua chon khong hop le. Vui long thu lai.");
                    break;
            }
        }
        scan.close();
    }

    private static void editDocument(Scanner scan, DocumentManagement xaptailieu1) {
        System.out.print("Nhap ma tai lieu muon sua: ");
        String id = scan.nextLine(); // Nhập id tài liệu

        // Tìm tài liệu theo id
        Document doc = xaptailieu1.getDocuments().stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (doc == null) {
            System.out.println("Khong tim thay tai lieu voi ID: " + id);
            return; // Nếu không tìm thấy tài liệu, kết thúc hàm
        }

        System.out.println("Tai lieu hien tai:");
        doc.display(); // Hiển thị thông tin tài liệu hiện tại

        // Hỏi người dùng có muốn sửa tài liệu không
        System.out.println("Ban co muon sua tai lieu nay? (y/n): ");
        String confirmation = scan.nextLine();

        if ("y".equalsIgnoreCase(confirmation)) {
            // Tạo một tài liệu mới để thay thế
            Document newDoc = null;

            if (doc instanceof Book) {
                // Cập nhật cho sách
                System.out.print(" - Nhap nha xuat ban moi: ");
                String name_nxb = scan.nextLine();
                System.out.print(" - Nhap so luong phat hanh moi: ");
                int release_num = Integer.parseInt(scan.nextLine());
                System.out.print(" - Nhap ten sach moi: ");
                String name_book = scan.nextLine();
                System.out.print(" - Nhap tac gia moi: ");
                String name_author = scan.nextLine();
                System.out.print(" - Nhap so trang moi: ");
                int num_page = scan.nextInt();
                scan.nextLine();

                newDoc = new Book(id, name_nxb, release_num, name_book, name_author, num_page);
            } else if (doc instanceof Magezine) {
                // Cập nhật cho tạp chí
                System.out.print(" - Nhap nha xuat ban moi: ");
                String name_nxb = scan.nextLine();
                System.out.print(" - Nhap so luong phat hanh moi: ");
                int release_num = scan.nextInt();
                System.out.print(" - Nhap ma phat hanh moi: ");
                int id_release = scan.nextInt();
                System.out.print(" -  - Nhap thang phat hanh moi: ");
                int month_release = scan.nextInt();
                scan.nextLine();

                newDoc = new Magezine(id, name_nxb, release_num, id_release, month_release);
            } else if (doc instanceof Newspaper) {
                // Cập nhật cho báo
                System.out.print(" - Nhap nha xuat ban moi: ");
                String name_nxb = scan.nextLine();
                System.out.print(" - Nhap so luong phat hanh moi: ");
                int release_num = scan.nextInt();
                System.out.print(" - Nhap ngay phat hanh moi: ");
                int day_release = scan.nextInt();
                scan.nextLine();

                newDoc = new Newspaper(id, name_nxb, release_num, day_release);
            }

            // Thực hiện cập nhật tài liệu mới vào danh sách
            if (newDoc != null) {
                xaptailieu1.editDocument(id, newDoc);  // Gọi phương thức sửa tài liệu
                System.out.println("Tai lieu da duoc sua thanh cong!");
            } else {
                System.out.println("Sua tai lieu khong thanh cong!");
            }
        } else {
            System.out.println("Huy sua tai lieu!");
        }
        System.out.println("-------------------------------------------");
    }

    private static void addDocument(Scanner scan, DocumentManagement xaptailieu1) {
        boolean exitAdd = false; // Biến điều khiển vòng lặp cho menu thêm tài liệu

        while (!exitAdd) {
            System.out.println("a. Them sach vao tai lieu");
            System.out.println("b. Them tap chi vao tai lieu");
            System.out.println("c. Them bao vao tai lieu");
            System.out.println("d. Thoat");
            System.out.println();
            System.out.print("Chon chuc nang: ");
            String optionAdd = scan.nextLine();
    
            switch (optionAdd) {
                case "a": {
                    System.out.print(" - Nhap ma tai lieu: ");
                    String id = scan.nextLine();
                    System.out.print(" - Nhap nha xuat ban: ");
                    String name_nxb = scan.nextLine();
                    System.out.print(" - Nhap so luong phat hanh: ");
                    int release_num = scan.nextInt(); // Đọc số nguyên từ người dùng
                    scan.nextLine();
                    System.out.print(" - Nhap ten sach: ");
                    String name_book = scan.nextLine();
                    System.out.print(" - Nhap tac gia: ");
                    String name_author = scan.nextLine();
                    System.out.print(" - Nhap so trang: ");
                    int num_page = scan.nextInt();  // Đọc số nguyên từ người dùng
                    scan.nextLine();
    
                    Document doc = new Book(id, name_nxb, release_num, name_book, name_author, num_page);
                    xaptailieu1.addDocument(doc);  // Thêm tài liệu vào danh sách quản lý
                    System.out.println("Them sach thanh cong!");
                    break;
                }
                case "b": { 
                    System.out.print(" - Nhap ma tai lieu: ");
                    String id = scan.nextLine();
                    System.out.print(" - Nhap nha xuat ban: ");
                    String name_nxb = scan.nextLine();
                    System.out.print(" - Nhap so luong phat hanh: ");
                    int release_num = scan.nextInt(); // Đọc số nguyên từ người dùng
                    System.out.print(" - Nhap ma phat hanh: ");
                    int id_release = scan.nextInt(); // Đọc số nguyên từ người dùng
                    System.out.print(" - Nhap thang phat hanh: ");
                    int month_release = scan.nextInt(); // Đọc số nguyên từ người dùng
                    scan.nextLine();
    
                    Document doc = new Magezine(id, name_nxb, release_num, id_release, month_release);
                    xaptailieu1.addDocument(doc);  // Thêm tài liệu vào danh sách quản lý
                    System.out.println("Them tap chi thanh cong!");
                    break;
                }
                case "c": {
                    System.out.print(" - Nhap ma tai lieu: ");
                    String id = scan.nextLine();
                    System.out.print(" - Nhap nha xuat ban: ");
                    String name_nxb = scan.nextLine();
                    System.out.print(" - Nhap so luong phat hanh: ");
                    int release_num = scan.nextInt();  // Đọc số nguyên từ người dùng
                    System.out.print(" - Nhap ngay phat hanh: ");
                    int day_release = scan.nextInt();  // Đọc số nguyên từ người dùng
                    scan.nextLine();
    
                    Document doc = new Newspaper(id, name_nxb, release_num, day_release);
                    xaptailieu1.addDocument(doc);  // Thêm tài liệu vào danh sách quản lý
                    System.out.println("Them bao thanh cong!");
                    break;
                }
                case "d": {
                    exitAdd = true;  // Thoát khỏi menu thêm tài liệu
                    break;
                }
                default: {
                    System.out.println("Lua chon khong hop le, chon lai!");
                    break;
                }
            }
            System.out.println("-------------------------------------------");
        }
    }
    

    private static void deleteDocument(Scanner scan, DocumentManagement xaptailieu1) {
        System.out.print("Nhap ma tai lieu muon xoa: ");
        String id = scan.nextLine();
        if (xaptailieu1.removeDocument(id)) {
            System.out.println("Xoa tai lieu thanh cong!");
        } else {
            System.out.println("Xoa tai lieu that bai!");
        }
        System.out.println("-------------------------------------------");
    }

    private static void displayDocuments(Scanner scan, DocumentManagement xaptailieu1) {
        boolean exitDisplay = false;
        
        while (!exitDisplay)
        {
            System.out.println("a. Hien thi toan bo tai lieu");
            System.out.println("b. Hien thi tai lieu theo loai");
            System.out.println("c. Thoat");
            System.out.println();
            System.out.print("Chon chuc nang: ");
            String optionDisplay = scan.nextLine();

            switch (optionDisplay)
            {
                case "a": {
                    xaptailieu1.displayDocument();
                    break;
                }

                case "b": {
                    boolean exitDisplayType = false;

                    while (!exitDisplayType)
                    {
                        System.out.println("b1. Hien thi sach");
                        System.out.println("b2. Hien thi bao");
                        System.out.println("b3. Hien thi tap chi");
                        System.out.println("b4. Thoat");
                        System.out.println();
                        System.out.print("Chon chuc nang: ");
                        String optionDisplayType = scan.nextLine();

                        switch (optionDisplayType) {
                            case "b1": {
                                xaptailieu1.searchByBooks();
                                break;
                            }
                            case "b2": {
                                xaptailieu1.searchByNewspaper();
                                break;
                            }
                            case "b3": {
                                xaptailieu1.searchByMagezine();
                                break;
                            }
                            case "b4": {
                                exitDisplayType = true;
                                break;
                            }
                            default: {
                                System.out.println("Cu phap khong hop le. Vui long nhap lai!");
                                break;
                            }
                        }
                        System.out.println("-------------------------------------------");
                    }
                }

                case "c": {
                    exitDisplay = true;
                    break;
                }

                default : {
                    System.out.println("Cu phap khong hop le. Vui long nhap lai!");
                    break;
                }
            }
            System.out.println("-------------------------------------------");
        }
    }
}
