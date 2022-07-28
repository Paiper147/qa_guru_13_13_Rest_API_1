package home.models;

public class CreateRequestBody {
    private String name;
    private String job;

    public CreateRequestBody(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
