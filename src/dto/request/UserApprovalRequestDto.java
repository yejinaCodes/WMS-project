package dto.request;

import common.Status;
import library.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserApprovalRequestDto {

    private int id;
    private int userId;
    private int approverId;
    private Status approvalStatus;
    private String rejectionReason;
    private String createdAt;

    /**
     * 승인 상태
     */
    public UserApprovalRequestDto(Status approvalStatus, String rejectionReason) {
        this.approvalStatus = approvalStatus;
        this.rejectionReason = rejectionReason;
        this.createdAt = LocalDateTime.getTime();
    }
}
