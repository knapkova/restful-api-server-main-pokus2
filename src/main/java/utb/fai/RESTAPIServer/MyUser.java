package utb.fai.RESTAPIServer;


public class MyUser {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public MyUser() {}

    public MyUser(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public boolean isUserDataValid() {
        // Add your validation logic here (e.g., email and phone number format
        // validation)
        return true;
    }

    // TODO: Getters and setters
    
}
