package src.model.dto;
import java.sql.Timestamp;
public class Refund {
    private int refundId;
    private int paymentId;
    private double refundAmount;
    private String refundReason;
    private String refundStatus;
    private Timestamp refundDate;
   
    public Refund()
    {

    }
    public Refund(int refundId, int paymentId, double refundAmount, String refundReason, String refundStatus,
            Timestamp refundDate) {
        this.refundId = refundId;
        this.paymentId = paymentId;
        this.refundAmount = refundAmount;
        this.refundReason = refundReason;
        this.refundStatus = refundStatus;
        this.refundDate = refundDate;
    }
 public int getRefundId() {
        return refundId;
    }
    public void setRefundId(int refundId) {
        this.refundId = refundId;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public double getRefundAmount() {
        return refundAmount;
    }
    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }
    public String getRefundReason() {
        return refundReason;
    }
    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }
    public String getRefundStatus() {
        return refundStatus;
    }
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
    public Timestamp getRefundDate() {
        return refundDate;
    }
    public void setRefundDate(Timestamp refundDate) {
        this.refundDate = refundDate;
    }
}
