package Models;

public class Warranty {
    int warrantyId;
    String warrantyName;
    String warrantyDescription;
    byte[] warrantyPhoto;

    public Warranty(int warrantyId, String warrantyName, String warrantyDescription, byte[] warrantyPhoto) {
        this.warrantyId = warrantyId;
        this.warrantyName = warrantyName;
        this.warrantyDescription = warrantyDescription;
        this.warrantyPhoto = warrantyPhoto;
    }
    //getter & Setter
    public int getWarrantyId() {
        return warrantyId;
    }

    public void setWarrantyId(int warrantyId) {
        this.warrantyId = warrantyId;
    }

    public String getWarrantyName() {
        return warrantyName;
    }

    public void setWarrantyName(String warrantyName) {
        this.warrantyName = warrantyName;
    }

    public String getWarrantyDescription() {
        return warrantyDescription;
    }

    public void setWarrantyDescription(String warrantyDescription) {
        this.warrantyDescription = warrantyDescription;
    }

    public byte[] getWarrantyPhoto() {
        return warrantyPhoto;
    }

    public void setWarrantyPhoto(byte[] warrantyPhoto) {
        this.warrantyPhoto = warrantyPhoto;
    }
}
