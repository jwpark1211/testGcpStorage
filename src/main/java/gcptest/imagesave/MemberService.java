package gcptest.imagesave;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final imageService imageService;

    //회원 정보 조회
    public MemberDTO.InfoResponse getMember (Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("회원 아이디가 잘못 되었습니다."));
        String imageUrl = imageService.processImage(member.getImage());
        return MemberDTO.InfoResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .image(imageUrl)
                .build();
    }

    //회원가입
    @Transactional
    public MemberDTO.idResponse joinMember(MemberDTO.JoinRequest request, MultipartFile multipartFile) throws IOException {
        String uuid = imageService.uploadImage(multipartFile);
        Member member = Member.builder().name(request.getName()).image(uuid).build();
        System.out.println(member.getName());
        memberRepository.save(member);
        return new MemberDTO.idResponse(member.getId());
    }

}
