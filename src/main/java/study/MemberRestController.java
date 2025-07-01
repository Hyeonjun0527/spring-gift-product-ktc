package study;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MemberRestController {
    private final MemberDao memberDao;
    private final MemberDao2 memberDao2;
    public MemberRestController(MemberDao memberDao, MemberDao2 memberDao2) {
        this.memberDao = memberDao;
        this.memberDao2 = memberDao2;
    }

    @GetMapping("/api/members")
    public void insertMember() {

        Member member = new Member(1L, "최현준", 20, "test@email.com");
        memberDao.insertMember(member);
    }

    @GetMapping("/api/members2")
    public void getMember() {
        Member member = new Member(1L, "최현준", 20, "test@email.com");
        memberDao2.insertMember(member);
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<Member> selectMember(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(memberDao2.selectMember(id).orElseThrow());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
