package study;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.net.URI;

@RestController
public class WishController {

    private final WishService wishService;
    private final MemberService memberService;

    public WishController(WishService wishService, MemberService memberService) {
        this.wishService = wishService;
        this.memberService = memberService;
    }

    @GetMapping("/api/wishes")
    public ResponseEntity<String> wishes() {
        return ResponseEntity.ok().body(memberService.member());
    }

    @PostMapping("/api/wishes")
    public ResponseEntity<Object> createWish(@RequestBody CreateWishRequest request) {
        var id = 1L;
        var memberId = 1L; // 임시로 하드코딩 (실제로는 request에서 추출하거나 토큰에서 추출)
        var response = wishService.create(id, request, memberId);
        return ResponseEntity.created(URI.create("/api/wishes/" + id)).body(response);
    }
    
    @PostMapping("/api/wishes/test")
    public ResponseEntity<String> createWishTest(@RequestBody CreateWishRequest request) {
        return ResponseEntity.ok("Test successful");
    }
    
    @GetMapping("/api/wishes/simple")
    public String simpleTest() {
        return "simple success";
    }

}
