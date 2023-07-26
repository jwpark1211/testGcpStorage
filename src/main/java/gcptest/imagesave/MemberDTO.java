package gcptest.imagesave;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {

    @Getter
    @NoArgsConstructor
    public static class JoinRequest {
        private String name;

        @Builder
        public JoinRequest(String name) {
            this.name = name;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class InfoResponse{
        private Long id;
        private String name;
        private String image;
        public static InfoResponse of(Member member){
            return new InfoResponse(member.getId(),member.getName(),member.getImage());
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class idResponse{
        private Long id;
    }
}
