package julian.TurnosBarber.Dto;

public class AuthResponse {

    private String token;
    private String email;
    private String name;
    private String lastName;
    private String role;

    public AuthResponse() {}

    public AuthResponse(String token, String email, String name, String lastName, String role) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.role = role;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
