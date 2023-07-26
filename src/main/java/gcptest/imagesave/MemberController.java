package gcptest.imagesave;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController   {

    private final MemberService memberService;

    //회원가입
    @PostMapping(path = "/join",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public MemberDTO.idResponse join(
            @RequestPart @Valid MemberDTO.JoinRequest request,
            @RequestPart MultipartFile image
    ) throws IOException {
        return memberService.joinMember(request,image);
    }

    // 회원 정보 조회
    @GetMapping(value = "/id/{id}", produces = APPLICATION_JSON_VALUE)
    public MemberDTO.InfoResponse getMember(
            @PathVariable("id") Long id
    ) {
       return memberService.getMember(id);
    }

}
