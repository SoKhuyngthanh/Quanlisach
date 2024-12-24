package quan_ly_tai_lieu;

// File: User.java
public class User {
    private String username; // Tên người dùng
    private String password; // Mật khẩu
    private Role role;       // Quyền của người dùng

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Phương thức xác thực người dùng
    public boolean kiemtraPassword(String password) {
        return this.password.equals(password);
    }

    // Lấy quyền của người dùng
    public Role getRole() {
        return role;
    }

    // Lấy tên người dùng
    public String getUsername() {
        return username;
    }
}


