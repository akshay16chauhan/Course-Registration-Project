class Complaint {
    private String description;
    private ComplaintStatus status;

    @Override
    public String toString() {
        return "Complaint: " + description + " | Status: " + status;
    }

    public Complaint(String description) {
        this.description = description;
        this.status = ComplaintStatus.PENDING; 
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    enum ComplaintStatus {
        PENDING,
        RESOLVED
    }
}
